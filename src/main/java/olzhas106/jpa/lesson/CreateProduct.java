package olzhas106.jpa.lesson;

import olzhas106.jpa.lesson.entity.Category;
import olzhas106.jpa.lesson.entity.Product;
import olzhas106.jpa.lesson.entity.SpecValue;
import olzhas106.jpa.lesson.entity.Specification;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Scanner;

public class CreateProduct {

    public static void main(String[] args) {
        // - Процессоры [1]
        // - Мониторы [2]
        // Выберите категорию по id: 1
        // Введите название товара: ___
        // Введите цену товара: ___
        // Производитель: ___
        // Сокет: ___
        // Максимальный объём ОЗУ: ___
        // Тактовая частота: ___
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("default");
        EntityManager manager = factory.createEntityManager();
        Scanner scanner = new Scanner(System.in);
        TypedQuery<Category> categoryTypedQuery = manager.createQuery(
                "select c from Category c ", Category.class);
        List<Category> categoryList = categoryTypedQuery.getResultList();

        System.out.println("Введите цифру категории, в которую хотите добавить товар:");
        for (Category category1 : categoryList) {
            System.out.println(category1.getId() + " " + category1.getName());
        }

        String id = scanner.nextLine();
        Long idLong = Long.parseLong(id);


        try {
            System.out.println("Введите наименование товара и модель:");
            String product1 = scanner.nextLine();
            manager.getTransaction().begin();
            Product product = new Product();
            product.setName(product1);


            System.out.println("Введите стоимость товара:");
            String productPrice = scanner.nextLine();
            int price = Integer.parseInt(productPrice);
            product.setPrice(price);

            Category category2 = manager.find(Category.class,idLong);
            product.setCategory(category2);

            manager.persist(product);

            System.out.println("Добавьте значения характеристик для категории " + category2.getName() + ":");
            for (Specification specification : category2.getSpecifications()) {
                System.out.println(specification.getName());
                String specifications = scanner.nextLine();

                SpecValue specValue = new SpecValue();
                specValue.setValue(specifications);


                specValue.setProduct(product);
                specValue.setSpecification(specification);

                manager.persist(specValue);

            }

            manager.getTransaction().commit();
        } catch (Exception e){
            manager.getTransaction().rollback();
            e.printStackTrace();
        }


    }
}
