package olzhas106.jpa.lesson;

import olzhas106.jpa.lesson.entity.Category;
import olzhas106.jpa.lesson.entity.Product;
import olzhas106.jpa.lesson.entity.SpecValue;
import olzhas106.jpa.lesson.entity.Specification;

import javax.persistence.*;
import java.util.List;
import java.util.Scanner;


public class Application {

    private static final EntityManagerFactory FACTORY = Persistence.createEntityManagerFactory("default");

    private static final Scanner IN = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("-Создание товара [1]");
        System.out.println("-Редактировнаие товара [2]");
        System.out.println("-Удаление товара [3]");
        System.out.println("Выберите действие: ");
        String actionNum = IN.nextLine();
        switch (actionNum) {
            case "1" -> createProduct();
            case "2" -> update();
            case "3" -> delete();
            default -> System.out.println("Такого действия не существует.");
        }
    }

    private static void createProduct() {
        EntityManager manager = FACTORY.createEntityManager();
        try {
            manager.getTransaction().begin();

            Product product = new Product();

            TypedQuery<Category> categoryTypedQuery = manager.createQuery(
                    "select c from Category c", Category.class);
            List<Category> categoryList = categoryTypedQuery.getResultList();

            for (Category category : categoryList) {
                System.out.println(category.getId() + " " + category.getName());
            }
            System.out.println("Введите номер категории:");
            String id = IN.nextLine();
            Long idLong = Long.parseLong(id);

            Category category2 = manager.find(Category.class, idLong);
            product.setCategory(category2);

            System.out.println("Введите наименование товара и модель:");
            String product1 = IN.nextLine();
            product.setName(product1);

            System.out.println("Введите стоимость товара:");
            String productPrice = IN.nextLine();
            int price = Integer.parseInt(productPrice);
            product.setPrice(price);
            manager.persist(product);

            System.out.println("Заполните характеристики для категории " + category2.getName() + ":");
            for (Specification specification : category2.getSpecifications()) {
                System.out.println(specification.getName());
                String specifications = IN.nextLine();

                SpecValue specValue = new SpecValue();
                specValue.setValue(specifications);
                specValue.setProduct(product);
                specValue.setSpecification(specification);
                manager.persist(specValue);
            }
            manager.getTransaction().commit();
        } catch (Exception e) {
            manager.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            manager.close();
        }
    }

    private static void update() {
        EntityManager manager = FACTORY.createEntityManager();
        try {
            manager.getTransaction().begin();

            TypedQuery<Product> productTypedQuery = manager.createQuery(
                    "select p from Product p", Product.class
            );
            List<Product> productList = productTypedQuery.getResultList();
            System.out.println("Введите номер товара, который хотите обновить:");
            for (Product product : productList) {
                System.out.println(product.getId() + " " + product.getName());
            }

            String id = IN.nextLine();
            Long idLong = Long.parseLong(id);
            Product product = manager.find(Product.class, idLong);

            System.out.print("Введите новое название" + "[текущее название - " + product.getName() + "]: ");
            String productName = IN.nextLine();
            if (!productName.isEmpty()) {
                product.setName(productName);
            }

            System.out.print("Введите новую стоимость" + "[текущая стоимость - " + product.getPrice() + "]: ");
            String productPrice = IN.nextLine();
            if (!productPrice.isEmpty()) {
                int priceInt = Integer.parseInt(productPrice);
                product.setPrice(priceInt);
            }

            System.out.println("Добавьте характеристики для товара [" + productName + "]:");
            for (Specification specification : product.getCategory().getSpecifications()) {
                TypedQuery<SpecValue> specValueTypedQuery = manager.createQuery(
                        "select s from SpecValue s where s.product.id = ?1 and s.specification.id = ?2", SpecValue.class
                );
                specValueTypedQuery.setParameter(1, idLong);
                specValueTypedQuery.setParameter(2, specification.getId());
                List<SpecValue> specValueList = specValueTypedQuery.getResultList();
                if (specValueList.isEmpty()) {
                    System.out.println(specification.getName() + "(добавление)"); //создание, если пусто
                    String productSpecValue = IN.nextLine();

                    SpecValue specValue1 = new SpecValue();
                    specValue1.setValue(productSpecValue);
                    specValue1.setProduct(product);
                    specValue1.setSpecification(specification);

                    manager.persist(specValue1);
                } else {
                    System.out.println(specification.getName() + "(обновление)"); //обновление существующих
                    String productSpecValue = IN.nextLine();
                    specValueList.get(0).setValue(productSpecValue);
                }
            }
            manager.getTransaction().commit();
        } catch (Exception e) {
            manager.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            manager.close();
        }
    }

    private static void delete() {
        EntityManager manager = FACTORY.createEntityManager();
        try {
            manager.getTransaction().begin();

            TypedQuery<Product> productTypedQuery = manager.createQuery(
                    "select p from Product p order by p.id", Product.class
            );
            List<Product> productList = productTypedQuery.getResultList();
            for (Product product : productList) {
                System.out.println(product.getId() + " " + product.getName());
            }
            System.out.println("Введите номер товара:");
            String id = IN.nextLine();
            Long idLong = Long.parseLong(id);

            Product product = manager.find(Product.class, idLong);
            manager.remove(product);

            manager.getTransaction().commit();
        } catch (Exception e) {
            manager.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            manager.close();
        }
    }
}

