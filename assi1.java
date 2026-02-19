class Student {

    String name;
    int rollNumber;
    double marks;
    String course;

    Student(String name, int rollNumber, double marks, String course) {
        this.name = name;
        this.rollNumber = rollNumber;
        this.marks = marks;
        this.course = course;
    }

    void displayInfo() {
        System.out.println("Name: " + name);
        System.out.println("Roll Number: " + rollNumber);
        System.out.println("Course: " + course);
        System.out.println("Marks: " + marks);
    }

    String calculateResult() {
        if (marks >= 50)
            return "Pass";
        else
            return "Fail";
    }
}

public class Main {
    public static void main(String[] args) {

        Student s1 = new Student("Rahul", 201, 72.5, "Computer Science");
        Student s2 = new Student("Sneha", 202, 45.0, "Information Technology");

        System.out.println("Student 1 Details:");
        s1.displayInfo();
        System.out.println("Result: " + s1.calculateResult());

        System.out.println("\nStudent 2 Details:");
        s2.displayInfo();
        System.out.println("Result: " + s2.calculateResult());
    }
}
