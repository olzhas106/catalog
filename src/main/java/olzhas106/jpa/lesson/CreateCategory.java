package olzhas106.jpa.lesson;

import olzhas106.jpa.lesson.entity.Category;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Scanner;

public class CreateCategory {
    public static void main(String[] args) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("default");
        EntityManager manager = factory.createEntityManager();
        Scanner scanner = new Scanner(System.in);

//        System.out.print("Введите имя: ");
//        String firstName = scanner.nextLine();
//
//        System.out.print("Введите фамилию: ");
//        String lastName = scanner.nextLine();
//
//        System.out.println("Полное имя:" + firstName + " " + lastName);

        System.out.print("Введите название категории: ");
        String category = scanner.nextLine();
        TypedQuery <Category> categoryTypedQuery = manager.createQuery(
                "select c from Category c where c.name = :categoryName", Category.class
        );
        categoryTypedQuery.setParameter("categoryName", category);
        List<Category> categories = categoryTypedQuery.getResultList();

        if (categories.isEmpty()){
            try {
                manager.getTransaction().begin();
                Category category1 = new Category();
                category1.setName(category);
                manager.persist(category1);
                manager.getTransaction().commit();
            } catch (Exception e){
                manager.getTransaction().rollback();
            }
            System.out.println("Категория успешно добавлена!");
        } else {
            System.out.println("Категория с таким названием существует!");
        }
    }
}
