package driver;

import entity.Course;
import entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public class DeleteData {

    public static void main(String[] args) {

        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Student.class)
                .addAnnotatedClass(Course.class)
                .buildSessionFactory();

        Session session = factory.getCurrentSession();

        try {

            int courseId = 2;

            session.beginTransaction();

            Course course = session.get(Course.class, courseId);

            if (course != null) {
                System.out.println("Deleting : " + course);
                session.delete(course);
            }

            session.getTransaction().commit();

        } finally {
            factory.close();
        }
    }
}
