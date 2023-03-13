package driver;

import entity.Employee;
import entity.Project;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.HashSet;
import java.util.Set;

public class InsertEmployeeAndProjects {

    public static void main(String[] args) {

        SessionFactory factory = new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Employee.class)
                .addAnnotatedClass(Project.class)
				.buildSessionFactory();

        Session session = factory.getCurrentSession();

        try {
            session.beginTransaction();

            Employee employee1 = new Employee("John", "Doe", "john.doe@gmail.com");

            Employee employee2 = new Employee("Aditya", "Sharma", "aditya.sharma@gmail.com");

            Project project1 = new Project("CustomerManagement");
            Project project2 = new Project("Automation");

            session.save(project1);
            session.save(project2);

            Set<Project> projectList1 = new HashSet<>();
            projectList1.add(project1);
            projectList1.add(project2);

            employee1.setProjects(projectList1);

            session.save(employee1);

            Set<Project> projectList2 = new HashSet<>();
            projectList2.add(project1);

            employee2.setProjects(projectList2);

            session.save(employee2);

            session.getTransaction().commit();

        } finally {
            factory.close();
        }
    }
}
