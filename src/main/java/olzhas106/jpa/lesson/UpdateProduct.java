package olzhas106.jpa.lesson;

import olzhas106.jpa.lesson.entity.Product;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Scanner;

public class UpdateProduct {
    public static void main(String[] args) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("default");
        EntityManager manager = factory.createEntityManager();
        Scanner scanner = new Scanner(System.in);
        TypedQuery<Product> productTypedQuery = manager.createQuery(
                "select p from Product p", Product.class
        );
        List<Product> productList = productTypedQuery.getResultList();
        System.out.println("Введите номер товара, который хотите обноваить:");
        for (Product product : productList) {
            System.out.println(product.getId() + " " + product.getName());
        }
        String id = scanner.nextLine();
        int idInt = Integer.parseInt(id);
        Long idLong = Long.parseLong(id);

        try {
            System.out.println("Введите новое название:");
            String productName = scanner.nextLine();

            manager.getTransaction().begin();
            Product product1 = manager.find(Product.class, idLong);
            product1.setName(productName);

            System.out.println("Введите новую стоимость:");
            String productPrice = scanner.nextLine();
            int productPriceItn = Integer.parseInt(productPrice);

            product1.setPrice(productPriceItn);

            manager.getTransaction().commit();
        } catch (Exception e){
            manager.getTransaction().rollback();
            e.printStackTrace();
        }





    }
}
