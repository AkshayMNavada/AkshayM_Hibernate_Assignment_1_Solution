package driver;

import entity.Address;
import entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class DeleteData {
    public static void main(String[] args) {
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Student.class)
                .addAnnotatedClass(Address.class)
                .buildSessionFactory();

        Session session = factory.getCurrentSession();

        try {
            int studentId = 2;
            session.beginTransaction();

            Student student = session.get(Student.class, studentId);
            if (student != null) {
                System.out.println("Deleting student with id : " + student.getId());
                session.delete(student);
            }

            session.getTransaction().commit();
        } finally {
            factory.close();
        }
    }
}
