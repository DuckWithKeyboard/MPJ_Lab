class Employee {
    String employeeName;
    double salary;

    Employee(String employeeName, double salary) {
        this.employeeName = employeeName;
        this.salary = salary;
    }

    void displayDetails() {
        System.out.println("Employee Name: " + employeeName);
        System.out.println("Current Salary: " + salary);
    }
}

class FullTimeEmployee extends Employee {

    FullTimeEmployee(String name, double salary) {
        super(name, salary);
    }

    void calculateSalary() {
        salary = salary + (salary * 0.50);
    }
}

class InternEmployee extends Employee {

    InternEmployee(String name, double salary) {
        super(name, salary);
    }

    void calculateSalary() {
        salary = salary + (salary * 0.25);
    }
}

public class Main {
    public static void main(String[] args) {

        FullTimeEmployee full = new FullTimeEmployee("Arjun", 60000);
        InternEmployee intern = new InternEmployee("Meera", 25000);

        System.out.println("Full Time Employee:");
        full.displayDetails();
        full.calculateSalary();
        System.out.println("After 50% Increment:");
        full.displayDetails();

        System.out.println("\nIntern Employee:");
        intern.displayDetails();
        intern.calculateSalary();
        System.out.println("After 25% Increment:");
        intern.displayDetails();
    }
}
