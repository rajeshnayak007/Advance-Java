import entity.Student;
import jakarta.persistence.*;

import javax.sound.midi.Soundbank;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

import static java.awt.AWTEventMulticaster.add;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
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

//Student s1=new Student(101,"rajesh","java");
//et.begin();
//em.persist(s1);
//et.commit();
        int choice = 0;
        do {
            System.out.println("1. Add Student\n2. Update Student\n3. Find by Id\n4. Delete Student\n5. Get All");
            choice = sc.nextInt();
            switch (choice) {
                case 1:
                    Student student = new Student();
                    System.out.print("Enter student id ");
                    student.setId(sc.nextInt());
                    sc.nextLine();
                    System.out.print("Enter student name ");
                    student.setName(sc.nextLine());
                    System.out.print("Enter student course ");
                    student.setCourse(sc.nextLine());
                    add(student);
                    break;
                    case 2:
                    System.out.print("Enter student id you want to update ");
                    int id = sc.nextInt();
                    update(id);
                    break;
                    case 3:
                        System.out.print("Enter student id you want to Find ");
                        int findId = sc.nextInt();
                        sc.nextLine();
                        Student find = em.find(Student.class, findId);
                        if(find != null){
                            System.out.println(find);
                        } else {
                            System.out.println("Student not found");
                        }
                        break;
                case 4:
                    System.out.print("Enter student id you want to delete ");
                    int deleteId = sc.nextInt();
                    sc.nextLine();
                    System.out.println("Deleted student id " + deleteId);
                    break;
                case 5:
                    Queue query= em.createQuery("select s from Student s");
                    List<Student> stdList=query.getResultList();
                    System.out.println(stdList);
                    break;
                case 6:
                    Query qry= em.createQuery("select s from Student s where s.name LIKE :match");
                    qry.setParameter("match","r%");
                    List<Student> stdList2=qry.getResultList();
                    System.out.println(stdList2);
                    break;
            }
        } while (choice != 0);
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
            System.out.println("Enter the new course");
            sc.nextLine();
            std.setCourse(sc.nextLine());
            System.out.println("Enter the new name");
            std.setName(sc.nextLine());
            et.begin();
            em.merge(std);
            et.commit();
            return "Success";
        } else {
            return "Student not found";
        }
    }
}