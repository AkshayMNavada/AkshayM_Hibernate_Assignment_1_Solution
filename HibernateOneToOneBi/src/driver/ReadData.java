package driver;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import entity.Address;
import entity.Student;

public class ReadData {
    public static void main(String[] args) {

        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Student.class)
                .addAnnotatedClass(Address.class)
                .buildSessionFactory();

        Session session = factory.getCurrentSession();

        try {
            session.beginTransaction();

            int addressId = 1;
            Address address = session.get(Address.class, addressId);

            System.out.println("Address Details : " + address);

            System.out.println("Associated Student : " + address.getStudent());

            session.getTransaction().commit();

        } finally {
            factory.close();
        }
    }
}
