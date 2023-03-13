package driver;

import entity.Course;
import entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public class InsertStudentAndCourses {

    public static void main(String[] args) {

        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Student.class)
                .addAnnotatedClass(Course.class)
                .buildSessionFactory();

        Session session = factory.getCurrentSession();

        try {
            session.beginTransaction();

            Student student = new Student("John", "Doe", "john.doe@gmail.com");

            Course course1 = new Course("Java");
            Course course2 = new Course("Python");

            student.add(course1);
            student.add(course2);

            session.save(student);

            session.save(course1);
            session.save(course2);

            session.getTransaction().commit();

            System.out.println("Completed Successfully");

        } finally {
            factory.close();
        }
    }
}
