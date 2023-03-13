package com.example.HibernateCRUDOperations;

import com.example.HibernateCRUDOperations.config.HibernateConfig;
import com.example.HibernateCRUDOperations.entity.Teacher;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

public class App {
    private static SessionFactory factory = HibernateConfig.getSessionFactory();

    public static void main(String[] args) {

        Teacher teacher1 = new Teacher("aditya", "sharma", "aditya.sharma@gmail.com");
        insertTeacher(teacher1);

        Teacher teacher2 = new Teacher("akash", "gupta", "akash.gupta@gmail.com");
        insertTeacher(teacher2);

        Teacher teacher3 = new Teacher("karthik", "sharma", "akash.gupta@gmail.com");
        insertTeacher(teacher3);

        System.out.println("\n");
        List<Teacher> teacherList = getAllTeachers();
        for (Teacher teacher : teacherList) {
            System.out.println(teacher);
        }
        System.out.println();

        int id = 2;
        System.out.println("Update the teacher details with id = " + id);
        Teacher teacher4 = getTeacherById(id);
        teacher4.setLast_name("g");
        teacher4.setEmail("akash.g@yahoo.com");

        System.out.println("Updated teacher details are : ");
        Teacher updatedTeacher = updateTeacher(teacher4);
        System.out.println(updatedTeacher);
        System.out.println();

        id = 3;
        System.out.println("Update the teacher with id = " + id);
        Teacher teacher5 = getTeacherById(id);
        deleteTeacher(teacher5);

        for (Teacher teacher : getAllTeachers()) {
            System.out.println(teacher);
        }
        System.out.println();

        System.out.println("Teachers whose email ending with '@gmail.com' are : ");
        for (Teacher teacher : getAllTeachersWithEmailQuery()) {
            System.out.println(teacher);
        }
        System.out.println();
    }

    public static void insertTeacher(Teacher teacher) {
        Session session = factory.openSession();
        Transaction tx = session.beginTransaction();
        session.persist(teacher);
        tx.commit();
        session.close();
    }

    public static Teacher updateTeacher(Teacher teacher) {
        Session session = factory.openSession();
        Transaction tx = session.beginTransaction();
        Teacher updatedTeacher = session.merge(teacher);
        tx.commit();
        session.close();
        return updatedTeacher;
    }

    public static void deleteTeacher(Teacher teacher) {
        Session session = factory.openSession();
        Transaction tx = session.beginTransaction();
        session.remove(teacher);
        tx.commit();
        session.close();
    }

    public static Teacher getTeacherById(int teacherId) {
        Session session = factory.openSession();
        Teacher teacher = session.get(Teacher.class, teacherId);
        session.close();
        return teacher;
    }

    public static List<Teacher> getAllTeachersWithEmailQuery() {
        Session session = factory.openSession();
        List<Teacher> teachers = session.createQuery("select a from Teacher a where email like '%gmail.com'", Teacher.class).getResultList();
        session.close();
        return teachers;
    }

    public static List<Teacher> getAllTeachers() {
        Session session = factory.openSession();
        List<Teacher> teachers = session.createQuery("select a from Teacher a", Teacher.class).getResultList();
        session.close();
        return teachers;
    }
}
