package driver;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import entity.Address;
import entity.Student;


public class DeleteData {

    public static void main(String[] args) {

        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Student.class)
                .addAnnotatedClass(Address.class)
                .buildSessionFactory();

        Session session = factory.getCurrentSession();

        try {
            int addressId = 1;

            session.beginTransaction();

            Address address = session.get(Address.class, addressId);

            if (address != null) {
                System.out.println("Deleting : " + address);
                session.delete(address);
            }

            session.getTransaction().commit();

        } finally {
            factory.close();
        }
    }
}
