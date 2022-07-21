package olzhas106.jpa.lesson.entity;

import javax.persistence.*;

public class AppClass {
    public static void main(String[] args) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("default");
        EntityManager manager = factory.createEntityManager();
//        Category category = manager.find(Category.class, 1L);
//        // System.out.println(category.getName());
//
        TypedQuery<Category> categoryTypedQuery = manager.createQuery(
                "select c from Category c", Category.class
        );
//        List<Category> categories = categoryTypedQuery.getResultList();
//        for (Category category1 : categories) {
//            //System.out.println(category1.getName());
//
//
//        }
//        TypedQuery<Product> productTypedQuery = manager.createQuery(
//                "select p from Product p", Product.class
//        );
//        List<Product> products = productTypedQuery.getResultList();
////        for (Product product: products){
////            System.out.println(product.getCategory().getName() + ": " + product.getName());
////        }
//
//
////        for (Category category1 : categories) {
////            int sum = 0;
////
////            for (Product category1Product : category1.getProducts()) {
////                sum += category1Product.getPrice();
////                //System.out.println(sum);
////            }
////            int avg = 0;
////            avg += sum / category1.getProducts().size();
////            //System.out.println(category1.getName() + " " + avg);
////        }
//
//        int maxPrice = 0;
//        for (Category category2 : categories) {
//            for (Product product2 : category2.getProducts()) {
//                if (product2.getPrice() > maxPrice) {
//                    maxPrice = product2.getPrice();
//                    //System.out.println(category2.getName() + " " + product2.getName() + " " +     maxPrice);
//                }
//            }
//        }
//
////        int minPrice1 = 100000;      ----  -----
////        int maxPrice1 = 200000;
////        TypedQuery<Product> productTypedQuery1 = manager.createQuery(
////                "select p from Product p where p.price between ?1 and ?2", Product.class);
////        productTypedQuery1.setParameter(1, minPrice1);
////        productTypedQuery1.setParameter(2, maxPrice1);
////        List<Product> products1 = productTypedQuery1.getResultList();
////        for (Product product : products1) {
////            System.out.println(product.getName() + ": " + product.getPrice());
////        }
////        int minPrice1 = 100000;   //именнованные параметры
////        int maxPrice1 = 200000;
////        TypedQuery<Product> productTypedQuery1 = manager.createQuery(
////                "select p from Product p where p.price between :minPrice1 and :maxPrice1", Product.class);
////        productTypedQuery1.setParameter("minPrice1", minPrice1);
////        productTypedQuery1.setParameter("maxPrice1", maxPrice1);
////        List<Product> products1 = productTypedQuery1.getResultList();
////        for (Product product : products1) {
////            System.out.println(product.getName() + ": " + product.getPrice());
////        }
//
////        String categoryName = "monitor";
////        TypedQuery<Category> categoryTypedQuery1 = manager.createQuery(
////                "select c from Category c where c.name = :categoryName", Category.class
////        );
////        categoryTypedQuery1.setParameter("categoryName", categoryName);
////        List<Category> categories1 = categoryTypedQuery1.getResultList();
////
////        if (categories1.isEmpty()){
////            System.out.println("Категория НЕ существует");
////        } else {
////            System.out.println("Категория существует");
////        }
////
//

        EntityTransaction transaction = manager.getTransaction();
        try {
            //manager.getTransaction().begin();
            transaction.begin();

            Category category11 = new Category();
            category11.setName("mebel");
            manager.persist(category11);          //добавление новой категории

//            Category category22 = manager.find(Category.class, 5L);
//            category22.setName("mother board");        //изменение


//            Category category33 = manager.find(Category.class, 5l);
//            manager.remove(category33);         //Удаление

//            Query query = manager.createQuery(
//                    "update Product p set p.price = p.price * 1.1 where p.category.id = ?1"
//            );                                    //обновление
//            query.setParameter(1, 2L);
//            query.executeUpdate();


            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            e.printStackTrace();
        }



    }
}
