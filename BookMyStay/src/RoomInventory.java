import java.util.HashMap;
import java.util.Map;

/**
 * UseCase3InventorySetup
 *
 * Demonstrates centralized room inventory management using HashMap.
 * This replaces scattered availability variables with a single source of truth.
 *
 * Version: 3.1 (Refactored)
 *
 * @author Triya
 * @version 3.1
 */

// Inventory class (Encapsulates all inventory logic)
class RoomInventory {

    private HashMap<String, Integer> inventory;

    // Constructor to initialize inventory
    public RoomInventory() {
        inventory = new HashMap<>();

        // Initialize room availability
        inventory.put("Single Room", 5);
        inventory.put("Double Room", 3);
        inventory.put("Suite Room", 2);
    }

    // Get availability of a specific room type
    public int getAvailability(String roomType) {
        return inventory.getOrDefault(roomType, 0);
    }

    // Update availability (controlled modification)
    public void updateAvailability(String roomType, int newCount) {
        if (inventory.containsKey(roomType)) {
            inventory.put(roomType, newCount);
        } else {
            System.out.println("Room type not found: " + roomType);
        }
    }

    // Display entire inventory
    public void displayInventory() {
        System.out.println("----- Current Room Inventory -----");

        for (Map.Entry<String, Integer> entry : inventory.entrySet()) {
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }
    }
}

// Main class
public class UseCase3InventorySetup {

    public static void main(String[] args) {

        System.out.println("=======================================");
        System.out.println("     Book My Stay - Version 3.1        ");
        System.out.println("=======================================\n");

        // Initialize inventory
        RoomInventory inventory = new RoomInventory();

        // Display initial inventory
        inventory.displayInventory();

        // Demonstrate controlled update
        System.out.println("\nUpdating Single Room availability...\n");
        inventory.updateAvailability("Single Room", 4);

        // Display updated inventory
        inventory.displayInventory();

        // Retrieve specific availability
        System.out.println("\nAvailable Double Rooms: " +
                inventory.getAvailability("Double Room"));

        System.out.println("\nApplication terminated successfully.");
    }
}