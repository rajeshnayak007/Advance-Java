import entity.Student;
import jakarta.persistence.*;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static EntityManagerFactory emf;
    private static EntityTransaction et;
    private static EntityManager em;
    private static Scanner sc;

    static {
        emf = Persistence.createEntityManagerFactory("HND");
        em = emf.createEntityManager();
        et = em.getTransaction();
        sc = new Scanner(System.in);
    }

    public static void main(String[] args) {

        int choice = 0;

        do {
            System.out.println("1. Add Student");
            System.out.println("2. Update Student");
            System.out.println("3. Find Student by Id");
            System.out.println("4. Delete Student");
            System.out.println("5. Get All Students");
            System.out.println("6. Find Student by Name");
            System.out.println("0. Exit");

            System.out.print("Enter your choice: ");
            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    Student student = new Student();
                    System.out.print("Enter student id: ");
                    student.setId(sc.nextInt());
                    sc.nextLine();
                    System.out.print("Enter student name: ");
                    student.setName(sc.nextLine());
                    System.out.print("Enter student course: ");
                    student.setCourse(sc.nextLine());
                    if (add(student)) {
                        System.out.println("Student added successfully");
                    }
                    break;
                case 2:
                    System.out.print("Enter student id you want to update: ");
                    int id = sc.nextInt();
                    System.out.println(update(id));
                    break;
                case 3:
                    System.out.print("Enter student id you want to find: ");
                    int findId = sc.nextInt();
                    Student find = read(findId);
                    if (find != null) {
                        System.out.println("Student Found:");
                        System.out.println(find);
                    } else {
                        System.out.println("student not found");
                    }
                    break;
                case 4:
                    System.out.print("Enter student id you want to delete ");
                    int deleteId = sc.nextInt();
                    System.out.println(delete(deleteId));
                    break;
                case 5:
                    getAll();
                    break;
                case 6:
                    sc.nextLine();
                    System.out.print("Enter starting name: ");
                    String name = sc.nextLine();
                    findByName(name);
                    break;
                case 0:
                    System.out.println(" Exited");
                    break;
                default:
                    System.out.println("invalid choice");
            }
        } while (choice != 0);
        em.close();
        emf.close();
        sc.close();
    }
    private static boolean add(Student st) {
        if (st != null) {
            et.begin();
            em.persist(st);
            et.commit();
            return true;
        }
        return false;
    }
    private static String update(int id) {
        Student std = em.find(Student.class, id);
        if (std != null) {
            sc.nextLine();
            System.out.print("Enter the new course: ");
            std.setCourse(sc.nextLine());
            System.out.print("Enter the new name: ");
            std.setName(sc.nextLine());
            et.begin();
            em.merge(std);
            et.commit();
            return "Student updated successfully";
        } else {
            return "Student not found";
        }
    }
    private static Student read(int id) {
        return em.find(Student.class, id);
    }
    private static String delete(int id) {
        Student std = em.find(Student.class, id);
        if (std != null) {
            et.begin();
            em.remove(std);
            et.commit();
            return "Student deleted successfully";
        } else {
            return "Student not found";
        }
    }
    private static void getAll() {
        Query query;
        query = em.createQuery("SELECT s FROM Student s", Student.class);
        List<Student> students = query.getResultList();
        if (students.isEmpty()) {
            System.out.println("No student found");

        } else {
            System.out.println("\n all students ");
            for (Student student : students) {
                System.out.println(student);
            }
        }
    }
    private static void findByName(String name) {
        Query query;
        query = em.createQuery(
                "SELECT s FROM Student s WHERE s.name LIKE :match",
                Student.class
        );
        query.setParameter("match", name + "%");
        List<Student> students = query.getResultList();
        if (students.isEmpty()) {
            System.out.println("No student found");
        } else {
            for (Student student : students) {
                System.out.println(student);
            }
        }
    }
}