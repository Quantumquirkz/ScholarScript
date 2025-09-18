# Student Management System Project

## Project Overview
A comprehensive student management system that demonstrates Java collections, file I/O, and advanced object-oriented programming concepts. The system manages student records, courses, grades, and generates reports.

## Project Specifications

### Core Features
- Student enrollment and profile management
- Course creation and assignment
- Grade management and GPA calculation
- Report generation (transcripts, class lists)
- Data persistence using file I/O
- Search and filtering capabilities
- Input validation and exception handling
- Menu-driven user interface

### Technical Requirements
- **Language**: Java 11 or higher
- **Dependencies**: Standard Java libraries only
- **Platforms**: Linux, Windows, macOS
- **Data Storage**: File-based (CSV/JSON format)

## Project Structure
```
student-management/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   ├── com/
│   │   │   │   └── student/
│   │   │   │       ├── StudentManagementSystem.java
│   │   │   │       ├── model/
│   │   │   │       │   ├── Student.java
│   │   │   │       │   ├── Course.java
│   │   │   │       │   ├── Enrollment.java
│   │   │   │       │   └── Grade.java
│   │   │   │       ├── service/
│   │   │   │       │   ├── StudentService.java
│   │   │   │       │   ├── CourseService.java
│   │   │   │       │   └── ReportService.java
│   │   │   │       ├── exception/
│   │   │   │       │   ├── StudentNotFoundException.java
│   │   │   │       │   └── DuplicateEnrollmentException.java
│   │   │   │       └── util/
│   │   │   │           ├── DataManager.java
│   │   │   │           └── GradeCalculator.java
│   │   │   └── Main.java
├── data/
│   ├── students.csv
│   ├── courses.csv
│   └── enrollments.csv
└── reports/
    └── transcripts/
```

## Detailed Implementation

### Core Model Classes

#### Student.java
```java
package com.student.model;

import java.time.LocalDate;
import java.util.*;

public class Student {
    private String studentId;
    private String firstName;
    private String lastName;
    private String email;
    private LocalDate dateOfBirth;
    private String major;
    private String address;
    private List<String> enrolledCourses;
    private Map<String, Double> grades;
    
    public Student(String studentId, String firstName, String lastName, 
                   String email, LocalDate dateOfBirth, String major) {
        this.studentId = studentId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.dateOfBirth = dateOfBirth;
        this.major = major;
        this.enrolledCourses = new ArrayList<>();
        this.grades = new HashMap<>();
    }
    
    // Getters and setters
    public String getStudentId() { return studentId; }
    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public String getEmail() { return email; }
    public LocalDate getDateOfBirth() { return dateOfBirth; }
    public String getMajor() { return major; }
    public String getAddress() { return address; }
    
    public void setEmail(String email) { this.email = email; }
    public void setMajor(String major) { this.major = major; }
    public void setAddress(String address) { this.address = address; }
    
    // Business methods
    public String getFullName() {
        return firstName + " " + lastName;
    }
    
    public int getAge() {
        return LocalDate.now().getYear() - dateOfBirth.getYear();
    }
    
    public void enrollCourse(String courseId) {
        if (!enrolledCourses.contains(courseId)) {
            enrolledCourses.add(courseId);
        }
    }
    
    public void dropCourse(String courseId) {
        enrolledCourses.remove(courseId);
        grades.remove(courseId);
    }
    
    public void addGrade(String courseId, double grade) {
        grades.put(courseId, grade);
    }
    
    public double getGrade(String courseId) {
        return grades.getOrDefault(courseId, 0.0);
    }
    
    public double calculateGPA() {
        if (grades.isEmpty()) return 0.0;
        
        double totalPoints = 0.0;
        for (double grade : grades.values()) {
            totalPoints += grade;
        }
        return totalPoints / grades.size();
    }
    
    public String getStudentSummary() {
        return String.format("ID: %s | Name: %s | Major: %s | GPA: %.2f | Courses: %d",
                           studentId, getFullName(), major, calculateGPA(), enrolledCourses.size());
    }
}
```

#### Course.java
```java
package com.student.model;

import java.util.*;

public class Course {
    private String courseId;
    private String courseName;
    private String instructor;
    private int credits;
    private String description;
    private Set<String> enrolledStudents;
    
    public Course(String courseId, String courseName, String instructor, int credits) {
        this.courseId = courseId;
        this.courseName = courseName;
        this.instructor = instructor;
        this.credits = credits;
        this.enrolledStudents = new HashSet<>();
    }
    
    // Getters and setters
    public String getCourseId() { return courseId; }
    public String getCourseName() { return courseName; }
    public String getInstructor() { return instructor; }
    public int getCredits() { return credits; }
    public String getDescription() { return description; }
    public Set<String> getEnrolledStudents() { return enrolledStudents; }
    
    public void setInstructor(String instructor) { this.instructor = instructor; }
    public void setDescription(String description) { this.description = description; }
    
    // Business methods
    public void enrollStudent(String studentId) {
        enrolledStudents.add(studentId);
    }
    
    public void dropStudent(String studentId) {
        enrolledStudents.remove(studentId);
    }
    
    public int getEnrollmentCount() {
        return enrolledStudents.size();
    }
    
    public String getCourseSummary() {
        return String.format("ID: %s | Name: %s | Instructor: %s | Credits: %d | Students: %d",
                           courseId, courseName, instructor, credits, enrolledStudents.size());
    }
}
```

### Service Classes

#### StudentService.java
```java
package com.student.service;

import com.student.model.Student;
import com.student.model.Course;
import com.student.exception.StudentNotFoundException;
import java.util.*;
import java.util.stream.Collectors;

public class StudentService {
    private Map<String, Student> students;
    private Map<String, Course> courses;
    
    public StudentService() {
        this.students = new HashMap<>();
        this.courses = new HashMap<>();
    }
    
    public void addStudent(Student student) {
        students.put(student.getStudentId(), student);
    }
    
    public Student getStudent(String studentId) throws StudentNotFoundException {
        Student student = students.get(studentId);
        if (student == null) {
            throw new StudentNotFoundException("Student not found: " + studentId);
        }
        return student;
    }
    
    public List<Student> getAllStudents() {
        return new ArrayList<>(students.values());
    }
    
    public List<Student> getStudentsByMajor(String major) {
        return students.values().stream()
                .filter(student -> student.getMajor().equalsIgnoreCase(major))
                .collect(Collectors.toList());
    }
    
    public List<Student> getStudentsByGPA(double minGPA) {
        return students.values().stream()
                .filter(student -> student.calculateGPA() >= minGPA)
                .collect(Collectors.toList());
    }
    
    public void enrollStudentInCourse(String studentId, String courseId) 
            throws StudentNotFoundException {
        Student student = getStudent(studentId);
        student.enrollCourse(courseId);
        
        if (courses.containsKey(courseId)) {
            courses.get(courseId).enrollStudent(studentId);
        }
    }
    
    public void addGrade(String studentId, String courseId, double grade) 
            throws StudentNotFoundException {
        Student student = getStudent(studentId);
        student.addGrade(courseId, grade);
    }
    
    public void generateStudentReport(String studentId) throws StudentNotFoundException {
        Student student = getStudent(studentId);
        
        System.out.println("\n=== Student Report ===");
        System.out.println("Student ID: " + student.getStudentId());
        System.out.println("Name: " + student.getFullName());
        System.out.println("Major: " + student.getMajor());
        System.out.println("GPA: " + String.format("%.2f", student.calculateGPA()));
        System.out.println("Enrolled Courses: " + student.getEnrolledCourses().size());
        
        if (!student.getEnrolledCourses().isEmpty()) {
            System.out.println("\nCourse Grades:");
            for (String courseId : student.getEnrolledCourses()) {
                double grade = student.getGrade(courseId);
                String courseName = courses.containsKey(courseId) ? 
                                  courses.get(courseId).getCourseName() : "Unknown";
                System.out.printf("  %s (%s): %.2f%n", courseName, courseId, grade);
            }
        }
    }
}
```

## Cross-Platform Build and Run

### Linux/macOS
```bash
# Compile
javac -d bin -cp src src/**/*.java

# Run
java -cp bin com.student.Main
```

### Windows
```cmd
# Compile
javac -d bin -cp src src\**\*.java

# Run
java -cp bin com.student.Main
```

## Key Features Demonstrated
- Object-oriented programming principles
- Java Collections Framework usage
- File I/O operations
- Exception handling
- Input validation
- Report generation
- Search and filtering capabilities

## Learning Outcomes
- Understanding Java collections and data structures
- Learning file I/O operations in Java
- Implementing business logic in service classes
- Creating comprehensive management systems
- Cross-platform Java development
