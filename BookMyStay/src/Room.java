/**
 * UseCase2RoomInitialization
 *
 * This class demonstrates basic room type modeling using abstraction,
 * inheritance, and static availability variables.
 *
 * Version: 2.1 (Refactored version)
 *
 * @author Triya
 * @version 2.1
 */

// Abstract class
abstract class Room {

    protected String roomType;
    protected int beds;
    protected double price;

    // Constructor
    public Room(String roomType, int beds, double price) {
        this.roomType = roomType;
        this.beds = beds;
        this.price = price;
    }

    // Abstract method
    public abstract void displayDetails();
}

// Single Room class
class SingleRoom extends Room {

    public SingleRoom() {
        super("Single Room", 1, 2000.0);
    }

    @Override
    public void displayDetails() {
        System.out.println("Room Type: " + roomType);
        System.out.println("Beds: " + beds);
        System.out.println("Price: ₹" + price);
    }
}

// Double Room class
class DoubleRoom extends Room {

    public DoubleRoom() {
        super("Double Room", 2, 3500.0);
    }

    @Override
    public void displayDetails() {
        System.out.println("Room Type: " + roomType);
        System.out.println("Beds: " + beds);
        System.out.println("Price: ₹" + price);
    }
}

// Suite Room class
class SuiteRoom extends Room {

    public SuiteRoom() {
        super("Suite Room", 3, 6000.0);
    }

    @Override
    public void displayDetails() {
        System.out.println("Room Type: " + roomType);
        System.out.println("Beds: " + beds);
        System.out.println("Price: ₹" + price);
    }
}

// Main class
public class UseCase2RoomInitialization {

    public static void main(String[] args) {

        System.out.println("=======================================");
        System.out.println("      Book My Stay - Version 2.1       ");
        System.out.println("=======================================\n");

        // Create room objects (Polymorphism)
        Room single = new SingleRoom();
        Room doubleRoom = new DoubleRoom();
        Room suite = new SuiteRoom();

        // Static availability variables
        int singleAvailable = 5;
        int doubleAvailable = 3;
        int suiteAvailable = 2;

        // Display details
        System.out.println("----- Room Details & Availability -----\n");

        single.displayDetails();
        System.out.println("Available: " + singleAvailable);
        System.out.println("-----------------------------------");

        doubleRoom.displayDetails();
        System.out.println("Available: " + doubleAvailable);
        System.out.println("-----------------------------------");

        suite.displayDetails();
        System.out.println("Available: " + suiteAvailable);
        System.out.println("-----------------------------------");

        System.out.println("\nApplication terminated successfully.");
    }
}