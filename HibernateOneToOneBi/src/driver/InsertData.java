package driver;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import entity.Address;
import entity.Student;

public class InsertData {

    public static void main(String[] args) {

        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Student.class)
                .addAnnotatedClass(Address.class)
                .buildSessionFactory();

        Session session = factory.getCurrentSession();

        try {
            Student student = new Student("John", "Doe", "john.doe@gmail.com");

            Address address = new Address("Karnataka", "Bangalore");

            student.setAddress(address);

            session.beginTransaction();

            session.save(student);

            session.getTransaction().commit();

        } finally {
            factory.close();
        }
    }
}
