# Student Grade Management System Project

## Project Overview
A comprehensive student grade management system that handles student enrollment, course management, grade recording, and academic reporting. The system demonstrates advanced Java concepts including collections, generics, file I/O, and complex data relationships.

## Project Specifications

### Core Features
- Student enrollment and profile management
- Course creation and management
- Grade recording and calculation
- GPA calculation and academic standing
- Report generation (transcripts, grade reports)
- Search and filtering capabilities
- Data import/export functionality
- Academic analytics and statistics

### Technical Requirements
- **Language**: Java 11 or higher
- **Dependencies**: Standard Java libraries only
- **Platforms**: Linux, Windows, macOS
- **Data Storage**: XML or JSON files
- **Build Tool**: Maven or Gradle
- **Testing Framework**: JUnit 5

## Project Structure
```
student-grade-management/
├── src/main/java/
│   ├── gradebook/
│   │   ├── model/
│   │   │   ├── Student.java
│   │   │   ├── Course.java
│   │   │   ├── Enrollment.java
│   │   │   ├── Grade.java
│   │   │   ├── Assignment.java
│   │   │   └── AcademicRecord.java
│   │   ├── service/
│   │   │   ├── StudentService.java
│   │   │   ├── CourseService.java
│   │   │   ├── GradeService.java
│   │   │   ├── ReportService.java
│   │   │   └── AnalyticsService.java
│   │   ├── dao/
│   │   │   ├── StudentDAO.java
│   │   │   ├── CourseDAO.java
│   │   │   └── GradeDAO.java
│   │   ├── exception/
│   │   │   ├── GradebookException.java
│   │   │   ├── StudentNotFoundException.java
│   │   │   ├── CourseNotFoundException.java
│   │   │   └── InvalidGradeException.java
│   │   ├── util/
│   │   │   ├── GradeCalculator.java
│   │   │   ├── FileManager.java
│   │   │   └── ReportGenerator.java
│   │   └── GradebookApplication.java
├── src/main/resources/
│   ├── students.xml
│   ├── courses.xml
│   ├── grades.xml
│   └── config.properties
├── src/test/java/
│   └── gradebook/
├── pom.xml
└── README.md
```

## Detailed Specifications

### 1. Model Classes

#### Student Class
```java
package gradebook.model;

import java.time.LocalDate;
import java.util.*;

public class Student {
    private String studentId;
    private String firstName;
    private String lastName;
    private String email;
    private LocalDate dateOfBirth;
    private String major;
    private AcademicLevel academicLevel;
    private List<Enrollment> enrollments;
    private AcademicRecord academicRecord;
    
    public enum AcademicLevel {
        FRESHMAN, SOPHOMORE, JUNIOR, SENIOR, GRADUATE
    }
    
    public Student(String studentId, String firstName, String lastName, String email) {
        this.studentId = studentId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.enrollments = new ArrayList<>();
        this.academicRecord = new AcademicRecord();
    }
    
    public String getFullName() {
        return firstName + " " + lastName;
    }
    
    public void addEnrollment(Enrollment enrollment) {
        if (!enrollments.contains(enrollment)) {
            enrollments.add(enrollment);
            academicRecord.addEnrollment(enrollment);
        }
    }
    
    public void removeEnrollment(Enrollment enrollment) {
        enrollments.remove(enrollment);
        academicRecord.removeEnrollment(enrollment);
    }
    
    public List<Course> getCurrentCourses() {
        return enrollments.stream()
            .filter(Enrollment::isCurrent)
            .map(Enrollment::getCourse)
            .collect(Collectors.toList());
    }
    
    public List<Course> getCompletedCourses() {
        return enrollments.stream()
            .filter(enrollment -> enrollment.getStatus() == Enrollment.Status.COMPLETED)
            .map(Enrollment::getCourse)
            .collect(Collectors.toList());
    }
    
    public double calculateOverallGPA() {
        return academicRecord.calculateOverallGPA();
    }
    
    public double calculateSemesterGPA(String semester) {
        return academicRecord.calculateSemesterGPA(semester);
    }
    
    public AcademicStanding getAcademicStanding() {
        double gpa = calculateOverallGPA();
        if (gpa >= 3.5) {
            return AcademicStanding.HONORS;
        } else if (gpa >= 3.0) {
            return AcademicStanding.GOOD;
        } else if (gpa >= 2.0) {
            return AcademicStanding.SATISFACTORY;
        } else {
            return AcademicStanding.PROBATION;
        }
    }
    
    public enum AcademicStanding {
        HONORS, GOOD, SATISFACTORY, PROBATION
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return Objects.equals(studentId, student.studentId);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(studentId);
    }
    
    // Getters and setters
}
```

#### Course Class
```java
package gradebook.model;

import java.util.*;

public class Course {
    private String courseId;
    private String courseName;
    private String courseCode;
    private String description;
    private int credits;
    private CourseType courseType;
    private String department;
    private String instructor;
    private List<Assignment> assignments;
    private Map<String, Student> enrolledStudents;
    
    public enum CourseType {
        LECTURE, LAB, SEMINAR, INDEPENDENT_STUDY, ONLINE
    }
    
    public Course(String courseId, String courseName, String courseCode, int credits) {
        this.courseId = courseId;
        this.courseName = courseName;
        this.courseCode = courseCode;
        this.credits = credits;
        this.assignments = new ArrayList<>();
        this.enrolledStudents = new HashMap<>();
    }
    
    public void addAssignment(Assignment assignment) {
        if (!assignments.contains(assignment)) {
            assignments.add(assignment);
        }
    }
    
    public void removeAssignment(Assignment assignment) {
        assignments.remove(assignment);
    }
    
    public void enrollStudent(Student student) {
        enrolledStudents.put(student.getStudentId(), student);
    }
    
    public void dropStudent(String studentId) {
        enrolledStudents.remove(studentId);
    }
    
    public boolean isStudentEnrolled(String studentId) {
        return enrolledStudents.containsKey(studentId);
    }
    
    public List<Student> getEnrolledStudents() {
        return new ArrayList<>(enrolledStudents.values());
    }
    
    public int getEnrollmentCount() {
        return enrolledStudents.size();
    }
    
    public double calculateAverageGrade() {
        return assignments.stream()
            .mapToDouble(Assignment::getAverageGrade)
            .average()
            .orElse(0.0);
    }
    
    public Map<Grade.LetterGrade, Long> getGradeDistribution() {
        return assignments.stream()
            .flatMap(assignment -> assignment.getGrades().values().stream())
            .collect(Collectors.groupingBy(
                Grade::getLetterGrade,
                Collectors.counting()
            ));
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Course course = (Course) o;
        return Objects.equals(courseId, course.courseId);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(courseId);
    }
    
    // Getters and setters
}
```

#### Enrollment Class
```java
package gradebook.model;

import java.time.LocalDate;
import java.util.Objects;

public class Enrollment {
    private String enrollmentId;
    private Student student;
    private Course course;
    private String semester;
    private LocalDate enrollmentDate;
    private Status status;
    private Grade finalGrade;
    
    public enum Status {
        ENROLLED, DROPPED, WITHDRAWN, COMPLETED
    }
    
    public Enrollment(Student student, Course course, String semester) {
        this.enrollmentId = generateEnrollmentId();
        this.student = student;
        this.course = course;
        this.semester = semester;
        this.enrollmentDate = LocalDate.now();
        this.status = Status.ENROLLED;
    }
    
    private String generateEnrollmentId() {
        return "ENR" + System.currentTimeMillis();
    }
    
    public boolean isCurrent() {
        return status == Status.ENROLLED;
    }
    
    public void drop() {
        if (status == Status.ENROLLED) {
            status = Status.DROPPED;
            course.dropStudent(student.getStudentId());
        }
    }
    
    public void withdraw() {
        if (status == Status.ENROLLED) {
            status = Status.WITHDRAWN;
            course.dropStudent(student.getStudentId());
        }
    }
    
    public void complete(Grade finalGrade) {
        if (status == Status.ENROLLED) {
            status = Status.COMPLETED;
            this.finalGrade = finalGrade;
        }
    }
    
    public double getEarnedCredits() {
        if (status == Status.COMPLETED && finalGrade != null) {
            return finalGrade.getNumericValue() >= 2.0 ? course.getCredits() : 0.0;
        }
        return 0.0;
    }
    
    public double getQualityPoints() {
        if (status == Status.COMPLETED && finalGrade != null) {
            return finalGrade.getNumericValue() * course.getCredits();
        }
        return 0.0;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Enrollment that = (Enrollment) o;
        return Objects.equals(enrollmentId, that.enrollmentId);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(enrollmentId);
    }
    
    // Getters and setters
}
```

#### Grade Class
```java
package gradebook.model;

import java.util.Objects;

public class Grade {
    private String gradeId;
    private Student student;
    private Assignment assignment;
    private double pointsEarned;
    private double pointsPossible;
    private LetterGrade letterGrade;
    private String comments;
    
    public enum LetterGrade {
        A_PLUS(4.0), A(4.0), A_MINUS(3.7),
        B_PLUS(3.3), B(3.0), B_MINUS(2.7),
        C_PLUS(2.3), C(2.0), C_MINUS(1.7),
        D_PLUS(1.3), D(1.0), D_MINUS(0.7),
        F(0.0);
        
        private final double numericValue;
        
        LetterGrade(double numericValue) {
            this.numericValue = numericValue;
        }
        
        public double getNumericValue() {
            return numericValue;
        }
        
        public static LetterGrade fromPercentage(double percentage) {
            if (percentage >= 97) return A_PLUS;
            if (percentage >= 93) return A;
            if (percentage >= 90) return A_MINUS;
            if (percentage >= 87) return B_PLUS;
            if (percentage >= 83) return B;
            if (percentage >= 80) return B_MINUS;
            if (percentage >= 77) return C_PLUS;
            if (percentage >= 73) return C;
            if (percentage >= 70) return C_MINUS;
            if (percentage >= 67) return D_PLUS;
            if (percentage >= 65) return D;
            if (percentage >= 60) return D_MINUS;
            return F;
        }
    }
    
    public Grade(Student student, Assignment assignment, double pointsEarned, double pointsPossible) {
        this.gradeId = generateGradeId();
        this.student = student;
        this.assignment = assignment;
        this.pointsEarned = pointsEarned;
        this.pointsPossible = pointsPossible;
        this.letterGrade = LetterGrade.fromPercentage(getPercentage());
    }
    
    private String generateGradeId() {
        return "GRD" + System.currentTimeMillis();
    }
    
    public double getPercentage() {
        if (pointsPossible == 0) return 0.0;
        return (pointsEarned / pointsPossible) * 100.0;
    }
    
    public double getNumericValue() {
        return letterGrade.getNumericValue();
    }
    
    public boolean isPassing() {
        return letterGrade.getNumericValue() >= 2.0;
    }
    
    public void updateGrade(double pointsEarned, double pointsPossible) {
        this.pointsEarned = pointsEarned;
        this.pointsPossible = pointsPossible;
        this.letterGrade = LetterGrade.fromPercentage(getPercentage());
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Grade grade = (Grade) o;
        return Objects.equals(gradeId, grade.gradeId);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(gradeId);
    }
    
    // Getters and setters
}
```

### 2. Service Classes

#### GradeService Class
```java
package gradebook.service;

import gradebook.model.*;
import gradebook.exception.*;
import gradebook.dao.*;
import gradebook.util.GradeCalculator;
import java.util.*;
import java.util.stream.Collectors;

public class GradeService {
    private StudentDAO studentDAO;
    private CourseDAO courseDAO;
    private GradeDAO gradeDAO;
    private GradeCalculator gradeCalculator;
    private Map<String, Grade> grades;
    
    public GradeService(StudentDAO studentDAO, CourseDAO courseDAO, GradeDAO gradeDAO) {
        this.studentDAO = studentDAO;
        this.courseDAO = courseDAO;
        this.gradeDAO = gradeDAO;
        this.gradeCalculator = new GradeCalculator();
        this.grades = new HashMap<>();
        loadGrades();
    }
    
    public Grade recordGrade(String studentId, String assignmentId, 
                           double pointsEarned, double pointsPossible, String comments) 
            throws StudentNotFoundException, AssignmentNotFoundException {
        
        Student student = studentDAO.findById(studentId);
        if (student == null) {
            throw new StudentNotFoundException("Student not found: " + studentId);
        }
        
        Assignment assignment = findAssignment(assignmentId);
        if (assignment == null) {
            throw new AssignmentNotFoundException("Assignment not found: " + assignmentId);
        }
        
        if (pointsEarned < 0 || pointsEarned > pointsPossible) {
            throw new InvalidGradeException("Invalid points: earned=" + pointsEarned + 
                ", possible=" + pointsPossible);
        }
        
        Grade grade = new Grade(student, assignment, pointsEarned, pointsPossible);
        grade.setComments(comments);
        
        grades.put(grade.getGradeId(), grade);
        gradeDAO.save(grade);
        
        return grade;
    }
    
    public void updateGrade(String gradeId, double pointsEarned, double pointsPossible, String comments) 
            throws GradeNotFoundException {
        
        Grade grade = getGrade(gradeId);
        grade.updateGrade(pointsEarned, pointsPossible);
        grade.setComments(comments);
        
        gradeDAO.save(grade);
    }
    
    public void deleteGrade(String gradeId) throws GradeNotFoundException {
        Grade grade = getGrade(gradeId);
        grades.remove(gradeId);
        gradeDAO.delete(gradeId);
    }
    
    public Grade getGrade(String gradeId) throws GradeNotFoundException {
        Grade grade = grades.get(gradeId);
        if (grade == null) {
            throw new GradeNotFoundException("Grade not found: " + gradeId);
        }
        return grade;
    }
    
    public List<Grade> getGradesByStudent(String studentId) {
        return grades.values().stream()
            .filter(grade -> grade.getStudent().getStudentId().equals(studentId))
            .collect(Collectors.toList());
    }
    
    public List<Grade> getGradesByAssignment(String assignmentId) {
        return grades.values().stream()
            .filter(grade -> grade.getAssignment().getAssignmentId().equals(assignmentId))
            .collect(Collectors.toList());
    }
    
    public List<Grade> getGradesByCourse(String courseId) {
        return grades.values().stream()
            .filter(grade -> grade.getAssignment().getCourse().getCourseId().equals(courseId))
            .collect(Collectors.toList());
    }
    
    public Map<String, Grade> getGradesByStudentAndCourse(String studentId, String courseId) {
        return grades.values().stream()
            .filter(grade -> grade.getStudent().getStudentId().equals(studentId) &&
                           grade.getAssignment().getCourse().getCourseId().equals(courseId))
            .collect(Collectors.toMap(
                grade -> grade.getAssignment().getAssignmentId(),
                grade -> grade
            ));
    }
    
    public Grade calculateCourseGrade(String studentId, String courseId) 
            throws StudentNotFoundException, CourseNotFoundException {
        
        Student student = studentDAO.findById(studentId);
        if (student == null) {
            throw new StudentNotFoundException("Student not found: " + studentId);
        }
        
        Course course = courseDAO.findById(courseId);
        if (course == null) {
            throw new CourseNotFoundException("Course not found: " + courseId);
        }
        
        Map<String, Grade> studentGrades = getGradesByStudentAndCourse(studentId, courseId);
        
        return gradeCalculator.calculateCourseGrade(course, studentGrades);
    }
    
    public List<Grade> getGradeDistribution(String courseId) {
        return getGradesByCourse(courseId).stream()
            .sorted(Comparator.comparing(Grade::getPercentage).reversed())
            .collect(Collectors.toList());
    }
    
    public Map<Grade.LetterGrade, Long> getLetterGradeDistribution(String courseId) {
        return getGradesByCourse(courseId).stream()
            .collect(Collectors.groupingBy(
                Grade::getLetterGrade,
                Collectors.counting()
            ));
    }
    
    private Assignment findAssignment(String assignmentId) {
        // Implementation to find assignment by ID
        // This would typically involve searching through courses
        return null; // Placeholder
    }
    
    private void loadGrades() {
        grades = gradeDAO.loadAll().stream()
            .collect(Collectors.toMap(Grade::getGradeId, grade -> grade));
    }
}
```

#### AnalyticsService Class
```java
package gradebook.service;

import gradebook.model.*;
import java.util.*;
import java.util.stream.Collectors;

public class AnalyticsService {
    private StudentService studentService;
    private CourseService courseService;
    private GradeService gradeService;
    
    public AnalyticsService(StudentService studentService, CourseService courseService, 
                          GradeService gradeService) {
        this.studentService = studentService;
        this.courseService = courseService;
        this.gradeService = gradeService;
    }
    
    public Map<String, Double> getDepartmentGPAs() {
        return studentService.getAllStudents().stream()
            .collect(Collectors.groupingBy(
                Student::getMajor,
                Collectors.averagingDouble(Student::calculateOverallGPA)
            ));
    }
    
    public Map<String, Double> getCourseAverageGrades() {
        return courseService.getAllCourses().stream()
            .collect(Collectors.toMap(
                Course::getCourseId,
                Course::calculateAverageGrade
            ));
    }
    
    public Map<Student.AcademicStanding, Long> getAcademicStandingDistribution() {
        return studentService.getAllStudents().stream()
            .collect(Collectors.groupingBy(
                Student::getAcademicStanding,
                Collectors.counting()
            ));
    }
    
    public List<Student> getTopPerformingStudents(int limit) {
        return studentService.getAllStudents().stream()
            .filter(student -> student.calculateOverallGPA() > 0)
            .sorted(Comparator.comparing(Student::calculateOverallGPA).reversed())
            .limit(limit)
            .collect(Collectors.toList());
    }
    
    public List<Student> getStudentsOnProbation() {
        return studentService.getAllStudents().stream()
            .filter(student -> student.getAcademicStanding() == Student.AcademicStanding.PROBATION)
            .collect(Collectors.toList());
    }
    
    public Map<String, Double> getSemesterGPAs(String semester) {
        return studentService.getAllStudents().stream()
            .collect(Collectors.toMap(
                Student::getStudentId,
                student -> student.calculateSemesterGPA(semester)
            ));
    }
    
    public CourseStatistics getCourseStatistics(String courseId) {
        List<Grade> grades = gradeService.getGradesByCourse(courseId);
        
        if (grades.isEmpty()) {
            return new CourseStatistics(courseId, 0, 0.0, 0.0, 0.0, 0.0);
        }
        
        double average = grades.stream()
            .mapToDouble(Grade::getPercentage)
            .average()
            .orElse(0.0);
        
        double median = calculateMedian(grades);
        double min = grades.stream().mapToDouble(Grade::getPercentage).min().orElse(0.0);
        double max = grades.stream().mapToDouble(Grade::getPercentage).max().orElse(0.0);
        
        return new CourseStatistics(courseId, grades.size(), average, median, min, max);
    }
    
    private double calculateMedian(List<Grade> grades) {
        List<Double> percentages = grades.stream()
            .map(Grade::getPercentage)
            .sorted()
            .collect(Collectors.toList());
        
        int size = percentages.size();
        if (size % 2 == 0) {
            return (percentages.get(size / 2 - 1) + percentages.get(size / 2)) / 2.0;
        } else {
            return percentages.get(size / 2);
        }
    }
    
    public static class CourseStatistics {
        private String courseId;
        private int totalGrades;
        private double average;
        private double median;
        private double minimum;
        private double maximum;
        
        public CourseStatistics(String courseId, int totalGrades, double average, 
                               double median, double minimum, double maximum) {
            this.courseId = courseId;
            this.totalGrades = totalGrades;
            this.average = average;
            this.median = median;
            this.minimum = minimum;
            this.maximum = maximum;
        }
        
        // Getters and setters
    }
}
```

### 3. Utility Classes

#### GradeCalculator Class
```java
package gradebook.util;

import gradebook.model.*;
import java.util.*;

public class GradeCalculator {
    
    public Grade calculateCourseGrade(Course course, Map<String, Grade> studentGrades) {
        List<Assignment> assignments = course.getAssignments();
        
        if (assignments.isEmpty() || studentGrades.isEmpty()) {
            return createDefaultGrade();
        }
        
        double totalWeightedPoints = 0.0;
        double totalWeight = 0.0;
        
        for (Assignment assignment : assignments) {
            Grade grade = studentGrades.get(assignment.getAssignmentId());
            if (grade != null) {
                double assignmentWeight = assignment.getWeight();
                double percentage = grade.getPercentage();
                
                totalWeightedPoints += (percentage / 100.0) * assignmentWeight;
                totalWeight += assignmentWeight;
            }
        }
        
        if (totalWeight == 0) {
            return createDefaultGrade();
        }
        
        double finalPercentage = (totalWeightedPoints / totalWeight) * 100.0;
        return createGradeFromPercentage(finalPercentage);
    }
    
    public double calculateGPA(List<Enrollment> enrollments) {
        if (enrollments.isEmpty()) {
            return 0.0;
        }
        
        double totalQualityPoints = 0.0;
        double totalCredits = 0.0;
        
        for (Enrollment enrollment : enrollments) {
            if (enrollment.getStatus() == Enrollment.Status.COMPLETED && 
                enrollment.getFinalGrade() != null) {
                
                double qualityPoints = enrollment.getQualityPoints();
                double credits = enrollment.getCourse().getCredits();
                
                totalQualityPoints += qualityPoints;
                totalCredits += credits;
            }
        }
        
        return totalCredits > 0 ? totalQualityPoints / totalCredits : 0.0;
    }
    
    public Grade calculateAssignmentAverage(List<Grade> grades) {
        if (grades.isEmpty()) {
            return createDefaultGrade();
        }
        
        double averagePercentage = grades.stream()
            .mapToDouble(Grade::getPercentage)
            .average()
            .orElse(0.0);
        
        return createGradeFromPercentage(averagePercentage);
    }
    
    private Grade createGradeFromPercentage(double percentage) {
        // Create a dummy student and assignment for the grade
        Student dummyStudent = new Student("DUMMY", "Dummy", "Student", "dummy@example.com");
        Assignment dummyAssignment = new Assignment("DUMMY", "Dummy Assignment", 100.0, 1.0, null);
        
        double pointsEarned = percentage;
        double pointsPossible = 100.0;
        
        Grade grade = new Grade(dummyStudent, dummyAssignment, pointsEarned, pointsPossible);
        return grade;
    }
    
    private Grade createDefaultGrade() {
        return createGradeFromPercentage(0.0);
    }
}
```

## User Interface

### Console Application
```java
package gradebook;

import gradebook.model.*;
import gradebook.service.*;
import gradebook.dao.*;
import java.util.Scanner;

public class GradebookApplication {
    private Scanner scanner;
    private StudentService studentService;
    private CourseService courseService;
    private GradeService gradeService;
    private ReportService reportService;
    private AnalyticsService analyticsService;
    
    public static void main(String[] args) {
        GradebookApplication app = new GradebookApplication();
        app.run();
    }
    
    public void run() {
        scanner = new Scanner(System.in);
        initializeServices();
        
        boolean running = true;
        while (running) {
            displayMainMenu();
            int choice = getMenuChoice();
            
            switch (choice) {
                case 1:
                    manageStudents();
                    break;
                case 2:
                    manageCourses();
                    break;
                case 3:
                    manageGrades();
                    break;
                case 4:
                    generateReports();
                    break;
                case 5:
                    viewAnalytics();
                    break;
                case 6:
                    running = false;
                    System.out.println("Thank you for using the Grade Management System!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
        
        scanner.close();
    }
    
    private void displayMainMenu() {
        System.out.println("\n=== Student Grade Management System ===");
        System.out.println("1. Student Management");
        System.out.println("2. Course Management");
        System.out.println("3. Grade Management");
        System.out.println("4. Reports");
        System.out.println("5. Analytics");
        System.out.println("6. Exit");
        System.out.print("Enter your choice: ");
    }
    
    private void manageGrades() {
        boolean back = false;
        while (!back) {
            System.out.println("\n--- Grade Management ---");
            System.out.println("1. Record Grade");
            System.out.println("2. Update Grade");
            System.out.println("3. Delete Grade");
            System.out.println("4. View Student Grades");
            System.out.println("5. View Course Grades");
            System.out.println("6. Calculate Course Grade");
            System.out.println("7. Back to Main Menu");
            System.out.print("Enter your choice: ");
            
            int choice = getMenuChoice();
            switch (choice) {
                case 1:
                    recordGrade();
                    break;
                case 2:
                    updateGrade();
                    break;
                case 3:
                    deleteGrade();
                    break;
                case 4:
                    viewStudentGrades();
                    break;
                case 5:
                    viewCourseGrades();
                    break;
                case 6:
                    calculateCourseGrade();
                    break;
                case 7:
                    back = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
    
    private void recordGrade() {
        try {
            System.out.print("Enter Student ID: ");
            String studentId = scanner.nextLine();
            
            System.out.print("Enter Assignment ID: ");
            String assignmentId = scanner.nextLine();
            
            System.out.print("Enter Points Earned: ");
            double pointsEarned = Double.parseDouble(scanner.nextLine());
            
            System.out.print("Enter Points Possible: ");
            double pointsPossible = Double.parseDouble(scanner.nextLine());
            
            System.out.print("Enter Comments (optional): ");
            String comments = scanner.nextLine();
            
            Grade grade = gradeService.recordGrade(studentId, assignmentId, 
                pointsEarned, pointsPossible, comments);
            
            System.out.println("Grade recorded successfully!");
            System.out.println("Grade ID: " + grade.getGradeId());
            System.out.println("Percentage: " + String.format("%.2f%%", grade.getPercentage()));
            System.out.println("Letter Grade: " + grade.getLetterGrade());
            
        } catch (Exception e) {
            System.out.println("Error recording grade: " + e.getMessage());
        }
    }
    
    // Additional methods for other menu options...
}
```

## Cross-Platform Considerations

### Maven Configuration
```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 
         http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>
    
    <groupId>com.gradebook</groupId>
    <artifactId>student-grade-management</artifactId>
    <version>1.0.0</version>
    <packaging>jar</packaging>
    
    <properties>
        <maven.compiler.source>11</maven.compiler.source>
        <maven.compiler.target>11</maven.compiler.target>
        <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
        <junit.version>5.8.2</junit.version>
    </properties>
    
    <dependencies>
        <dependency>
            <groupId>org.junit.jupiter</groupId>
            <artifactId>junit-jupiter</artifactId>
            <version>${junit.version}</version>
            <scope>test</scope>
        </dependency>
        <dependency>
            <groupId>com.fasterxml.jackson.dataformat</groupId>
            <artifactId>jackson-dataformat-xml</artifactId>
            <version>2.13.3</version>
        </dependency>
    </dependencies>
    
    <build>
        <plugins>
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-compiler-plugin</artifactId>
                <version>3.8.1</version>
                <configuration>
                    <source>11</source>
                    <target>11</target>
                </configuration>
            </plugin>
        </plugins>
    </build>
</project>
```

## Testing Strategy

### Unit Tests
```java
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

public class GradeCalculatorTest {
    private GradeCalculator gradeCalculator;
    
    @BeforeEach
    void setUp() {
        gradeCalculator = new GradeCalculator();
    }
    
    @Test
    void testCalculateGPA() {
        // Test GPA calculation with sample enrollments
        List<Enrollment> enrollments = createSampleEnrollments();
        double gpa = gradeCalculator.calculateGPA(enrollments);
        
        assertTrue(gpa >= 0.0 && gpa <= 4.0);
    }
    
    @Test
    void testCalculateCourseGrade() {
        Course course = createSampleCourse();
        Map<String, Grade> grades = createSampleGrades();
        
        Grade courseGrade = gradeCalculator.calculateCourseGrade(course, grades);
        
        assertNotNull(courseGrade);
        assertTrue(courseGrade.getPercentage() >= 0.0 && courseGrade.getPercentage() <= 100.0);
    }
}
```

## Performance Requirements
- Support for up to 10,000 students
- Grade calculations complete in < 1 second
- Report generation complete in < 5 seconds
- Search operations complete in < 2 seconds
- Memory usage < 150MB for maximum dataset

## Extension Ideas
- Database integration (MySQL, PostgreSQL)
- Web interface with Spring Boot
- REST API for external integrations
- Email notifications for grade updates
- Mobile application for students
- Integration with Learning Management Systems
- Advanced analytics and data visualization
- Multi-language support

## Deliverables
1. Complete source code with proper package structure
2. Maven configuration with dependencies
3. Comprehensive unit tests
4. Documentation (README, JavaDoc)
5. Sample data files
6. Build and deployment scripts
7. User manual and administrator guide

## Success Criteria
- All grade management operations work correctly
- GPA calculations are accurate
- Report generation produces correct results
- Data persists between application sessions
- Exception handling prevents system crashes
- Code achieves 90% test coverage
- Application compiles and runs on all target platforms
