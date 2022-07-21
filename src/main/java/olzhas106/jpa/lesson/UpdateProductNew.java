package olzhas106.jpa.lesson;

import olzhas106.jpa.lesson.entity.Product;
import olzhas106.jpa.lesson.entity.SpecValue;
import olzhas106.jpa.lesson.entity.Specification;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Scanner;

public class UpdateProductNew {
    public static void main(String[] args) {

        // Введите новое название [Old name]: ___
        // Введите новую стоимость [Old price]: ___

//        String text = "";
//        System.out.println(text.length() == 0);
//        System.out.println(text.isEmpty());

        EntityManagerFactory factory = Persistence.createEntityManagerFactory("default");
        EntityManager manager = factory.createEntityManager();
        Scanner scanner = new Scanner(System.in);
        TypedQuery<Product> productTypedQuery = manager.createQuery(
                "select p from Product p", Product.class
        );
        List<Product> productList = productTypedQuery.getResultList();
        System.out.println("Введите номер товара, который хотите обновить:");
        for (Product product : productList) {
            System.out.println(product.getId() + " " + product.getName());
        }
        String id = scanner.nextLine();
        int idInt = Integer.parseInt(id);
        Long idLong = Long.parseLong(id);
        try {
            manager.getTransaction().begin();
            Product product = manager.find(Product.class, idLong);
            System.out.println("Введите новое название" + "[текущее название - " + product.getName() + "]:");
            String productName = scanner.nextLine();
            if (!productName.isEmpty()) {
                product.setName(productName);
            }

            System.out.println("Введите новую стоимость" + "[текущая стоимость - " + product.getPrice() + "]:");
            String productPrice = scanner.nextLine();
            if (!productPrice.isEmpty()) {
                int priceInt = Integer.parseInt(productPrice);
                product.setPrice(priceInt);
            }

            System.out.println("Добавьте характеристики для товара [" + productName + "]:");
            for (Specification specification : product.getCategory().getSpecifications()) {
                TypedQuery<SpecValue> specValueTypedQuery = manager.createQuery(
                        "select s from SpecValue s where s.product.id = ?1 and s.specification.id = ?2", SpecValue.class
                );
                specValueTypedQuery.setParameter(1, id);
                specValueTypedQuery.setParameter(2, specification.getId());
                List<SpecValue> specValueList = specValueTypedQuery.getResultList();
                if (product.getSpecValues().isEmpty()){
                    System.out.println(specification);
                    String productSpecValue = scanner.nextLine();
                    SpecValue specValue1 = new SpecValue();
                    specValue1.setValue(productSpecValue);
                    specValue1.getProduct().setCategory(product.getCategory());
                }
            }
           manager.getTransaction().commit();
        } catch (Exception e) {
            manager.getTransaction().rollback();
            e.printStackTrace();
        }


    }
}
