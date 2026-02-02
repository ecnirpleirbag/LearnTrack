# LearnTrack - Student & Course Management System

LearnTrack is a Core Java console application for managing students, courses, and enrollments.

## Features
- **Student Management**: Add, view, search, and deactivate students.
- **Course Management**: Add, view, and deactivate courses.
- **Enrollment Management**: Enroll students in courses and track status.

## Directory Structure
- `src/com/airtribe/learntrack`: Source code.
  - `entity`: Data models (Person, Student, Course, Enrollment).
  - `service`: Business logic.
  - `repository`: In-memory storage.
  - `exception`: Custom exceptions.
  - `util`: Helper classes.
- `docs`: Documentation and design notes.

## Class Diagram
```mermaid
classDiagram
    class Person {
        <<abstract>>
        -int id
        -String firstName
        -String lastName
        -String email
        +getDisplayName() String
    }

    class Student {
        -String batch
        -boolean active
        +isActive() boolean
    }

    class Course {
        -int id
        -String courseName
        -String description
        -int durationInWeeks
        -boolean active
        +isActive() boolean
    }

    class Enrollment {
        -int id
        -int studentId
        -int courseId
        -LocalDate enrollmentDate
        -EnrollmentStatus status
    }

    class EnrollmentStatus {
        <<enumeration>>
        ACTIVE
        COMPLETED
        CANCELLED
    }

    Person <|-- Student
    Enrollment --> EnrollmentStatus
    Enrollment ..> Student : references studentId
    Enrollment ..> Course : references courseId

    class StudentService {
        -StudentRepository repo
        +addStudent()
        +deactivateStudent()
    }

    class CourseService {
        -CourseRepository repo
        +addCourse()
        +deactivateCourse()
    }

    class EnrollmentService {
        -EnrollmentRepository repo
        -StudentService studentService
        -CourseService courseService
        +enrollStudent()
    }

    StudentService --> StudentRepository
    CourseService --> CourseRepository
    EnrollmentService --> EnrollmentRepository
    EnrollmentService --> StudentService
    EnrollmentService --> CourseService
```

## How to Run
1. Compile: `javac -d bin -sourcepath src src/com/airtribe/learntrack/Main.java`
2. Run: `java -cp bin com.airtribe.learntrack.Main`

