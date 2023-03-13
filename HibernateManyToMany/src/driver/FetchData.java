package driver;

import entity.Employee;
import entity.Project;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class FetchData {

    public static void main(String[] args) {

        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Employee.class)
                .addAnnotatedClass(Project.class)
                .buildSessionFactory();

        Session session = factory.getCurrentSession();

        try {
            int projectId = 1;

            session.beginTransaction();

            Project project = session.get(Project.class, projectId);

            System.out.println("Project is: " + project);
            System.out.println("Assigned Employee are: " + project.getEmployees());
            System.out.println();

            int employeeId = 1;
            Employee employee = session.get(Employee.class, employeeId);
            System.out.println("Employee is: " + employee);

            session.getTransaction().commit();

        } finally {
            factory.close();
        }
    }
}
