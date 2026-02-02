package com.airtribe.learntrack;

import com.airtribe.learntrack.constants.AppConstants;
import com.airtribe.learntrack.entity.Course;
import com.airtribe.learntrack.entity.Student;
import com.airtribe.learntrack.entity.Enrollment;
import com.airtribe.learntrack.exception.EntityNotFoundException;
import com.airtribe.learntrack.repository.CourseRepository;
import com.airtribe.learntrack.repository.EnrollmentRepository;
import com.airtribe.learntrack.repository.StudentRepository;
import com.airtribe.learntrack.service.CourseService;
import com.airtribe.learntrack.service.EnrollmentService;
import com.airtribe.learntrack.service.StudentService;

import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static StudentService studentService;
    private static CourseService courseService;
    private static EnrollmentService enrollmentService;

    public static void main(String[] args) {
        initializeServices();
        runMenu();
    }

    private static void initializeServices() {
        StudentRepository studentRepo = new StudentRepository();
        CourseRepository courseRepo = new CourseRepository();
        EnrollmentRepository enrollmentRepo = new EnrollmentRepository();

        studentService = new StudentService(studentRepo);
        courseService = new CourseService(courseRepo);
        enrollmentService = new EnrollmentService(enrollmentRepo, studentService, courseService);
    }

    private static void runMenu() {
        while (true) {
            System.out.println("\n" + AppConstants.DIVIDER);
            System.out.println(AppConstants.APP_NAME);
            System.out.println(AppConstants.DIVIDER);
            System.out.println("1. Student Management");
            System.out.println("2. Course Management");
            System.out.println("3. Enrollment Management");
            System.out.println("0. Exit");
            System.out.print("Select an option: ");

            String choice = scanner.nextLine();
            switch (choice) {
                case "1":
                    studentMenu();
                    break;
                case "2":
                    courseMenu();
                    break;
                case "3":
                    enrollmentMenu();
                    break;
                case "0":
                    System.out.println("Exiting... Goodbye!");
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private static void studentMenu() {
        System.out.println("\n--- Student Management ---");
        System.out.println("1. Add Student");
        System.out.println("2. View All Students");
        System.out.println("3. Search Student by ID");
        System.out.println("4. Deactivate Student");
        System.out.println("0. Back");
        System.out.print("Select an option: ");

        String choice = scanner.nextLine();
        try {
            switch (choice) {
                case "1":
                    System.out.print("Enter First Name: ");
                    String fName = scanner.nextLine();
                    System.out.print("Enter Last Name: ");
                    String lName = scanner.nextLine();
                    System.out.print("Enter Email: ");
                    String email = scanner.nextLine();
                    System.out.print("Enter Batch: ");
                    String batch = scanner.nextLine();
                    Student s = studentService.addStudent(fName, lName, email, batch);
                    System.out.println("Student added successfully! ID: " + s.getId());
                    break;
                case "2":
                    studentService.getAllStudents().forEach(System.out::println);
                    break;
                case "3":
                    System.out.print("Enter Student ID: ");
                    int id = Integer.parseInt(scanner.nextLine());
                    System.out.println(studentService.getStudentById(id));
                    break;
                case "4":
                    System.out.print("Enter Student ID to deactivate: ");
                    int dId = Integer.parseInt(scanner.nextLine());
                    studentService.deactivateStudent(dId);
                    System.out.println("Student deactivated.");
                    break;
                case "0":
                    return;
                default:
                    System.out.println("Invalid option.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Error: Please enter a valid numeric ID.");
        } catch (EntityNotFoundException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void courseMenu() {
        System.out.println("\n--- Course Management ---");
        System.out.println("1. Add Course");
        System.out.println("2. View All Courses");
        System.out.println("3. Deactivate Course");
        System.out.println("0. Back");
        System.out.print("Select an option: ");

        String choice = scanner.nextLine();
        try {
            switch (choice) {
                case "1":
                    System.out.print("Enter Course Name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter Description: ");
                    String desc = scanner.nextLine();
                    System.out.print("Enter Duration (weeks): ");
                    int duration = Integer.parseInt(scanner.nextLine());
                    Course c = courseService.addCourse(name, desc, duration);
                    System.out.println("Course added successfully! ID: " + c.getId());
                    break;
                case "2":
                    courseService.getAllCourses().forEach(System.out::println);
                    break;
                case "3":
                    System.out.print("Enter Course ID to deactivate: ");
                    int dId = Integer.parseInt(scanner.nextLine());
                    courseService.deactivateCourse(dId);
                    System.out.println("Course deactivated.");
                    break;
                case "0":
                    return;
                default:
                    System.out.println("Invalid option.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Error: Please enter valid input.");
        } catch (EntityNotFoundException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void enrollmentMenu() {
        System.out.println("\n--- Enrollment Management ---");
        System.out.println("1. Enroll Student in Course");
        System.out.println("2. View All Enrollments");
        System.out.println("3. View Enrollments by Student ID");
        System.out.println("0. Back");
        System.out.print("Select an option: ");

        String choice = scanner.nextLine();
        try {
            switch (choice) {
                case "1":
                    System.out.print("Enter Student ID: ");
                    int sId = Integer.parseInt(scanner.nextLine());
                    System.out.print("Enter Course ID: ");
                    int cId = Integer.parseInt(scanner.nextLine());
                    Enrollment e = enrollmentService.enrollStudent(sId, cId);
                    System.out.println("Enrolled successfully! ID: " + e.getId());
                    break;
                case "2":
                    enrollmentService.getAllEnrollments().forEach(System.out::println);
                    break;
                case "3":
                    System.out.print("Enter Student ID: ");
                    int qId = Integer.parseInt(scanner.nextLine());
                    enrollmentService.getEnrollmentsByStudent(qId).forEach(System.out::println);
                    break;
                case "0":
                    return;
                default:
                    System.out.println("Invalid option.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Error: Please enter valid numeric ID.");
        } catch (EntityNotFoundException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
