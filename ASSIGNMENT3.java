class Geometry {
    private double rad;
    private double sideA, sideB;

    // Constructors
    public Geometry() { this.rad = 1.0; }
    public Geometry(double r) { this.rad = r; }
    public Geometry(double length, double width) {
        this.sideA = length;
        this.sideB = width;
    }
    public Geometry(double b, double h, String type) {
        this.sideA = b;
        this.sideB = h;
    }

    // Overloaded Area Methods
    public double calculateArea() {
        return 3.14159 * rad * rad;
    }

    public double calculateArea(double l, double w) {
        return l * w;
    }

    public double calculateArea(double b, double h, boolean isTri) {
        return isTri ? (0.5 * b * h) : (b * h);
    }
}

abstract class MountainRetreat {
    public void localFood() {
        System.out.println("General mountain snacks and tea.");
    }
    public abstract void attractions();
}

class Shimla extends MountainRetreat {
    @Override
    public void localFood() {
        System.out.println("Shimla Specialties: Chana Madra and Dham.");
    }
    @Override
    public void attractions() {
        System.out.println("Shimla is known for The Ridge and Mall Road.");
    }
}

class Ooty extends MountainRetreat {
    @Override
    public void localFood() {
        System.out.println("Ooty Specialties: Homemade Chocolates and Varkey.");
    }
    @Override
    public void attractions() {
        System.out.println("Ooty is known for Botanical Gardens and Nilgiri Railway.");
    }
}

class Nainital extends MountainRetreat {
    @Override
    public void localFood() {
        System.out.println("Nainital Specialties: Baadi and Bhatt ki Churkani.");
    }
    @Override
    public void attractions() {
        System.out.println("Nainital is known for Naini Lake and Snow View Point.");
    }
}

public class ASSIGNMENT3 {
    public static void main(String[] args) {
        System.out.println("--- POLYMORPHISM & OVERLOADING LAB ---\n");

        // Part 1: Geometry
        Geometry geo = new Geometry();
        System.out.println(">> Geometry Calculations:");
        System.out.printf("Circle Area (r=5): %.2f%n", geo.calculateArea(5.0, 5.0)); // reused logic
        System.out.printf("Rectangle Area (12x8): %.2f%n", geo.calculateArea(12.0, 8.0));
        System.out.printf("Triangle Area (base 10, height 5): %.2f%n", geo.calculateArea(10.0, 5.0, true));

        // Part 2: Mountain Retreats
        System.out.println("\n>> Mountain Retreat Exploration:");
        MountainRetreat place;

        place = new Shimla();
        printDetails(place, "SHIMLA");

        place = new Ooty();
        printDetails(place, "OOTY");

        place = new Nainital();
        printDetails(place, "NAINITAL");
    }

    private static void printDetails(MountainRetreat mr, String name) {
        System.out.println("[" + name + "]");
        mr.localFood();
        mr.attractions();
        System.out.println();
    }
}