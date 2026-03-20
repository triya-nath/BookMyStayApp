import java.util.*;

/**
 * UseCase11ConcurrentBookingSimulation
 *
 * Demonstrates thread-safe booking using synchronization
 * to prevent race conditions and ensure consistent state.
 *
 * Version: 11.0
 *
 * @author Triya
 * @version 11.0
 */

// Reservation class
class Reservation {
    private String guestName;
    private String roomType;

    public Reservation(String guestName, String roomType) {
        this.guestName = guestName;
        this.roomType = roomType;
    }

    public String getGuestName() {
        return guestName;
    }

    public String getRoomType() {
        return roomType;
    }
}

// Thread-safe Inventory
class RoomInventory {

    private Map<String, Integer> inventory;

    public RoomInventory() {
        inventory = new HashMap<>();
        inventory.put("Single Room", 2);
        inventory.put("Double Room", 1);
    }

    // Synchronized method (critical section)
    public synchronized boolean allocateRoom(String roomType) {

        int available = inventory.getOrDefault(roomType, 0);

        if (available > 0) {
            inventory.put(roomType, available - 1);
            return true;
        }

        return false;
    }

    public void displayInventory() {
        System.out.println("\nFinal Inventory State:");
        for (Map.Entry<String, Integer> entry : inventory.entrySet()) {
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }
    }
}

// Shared Booking Queue (Thread-safe access)
class BookingQueue {

    private Queue<Reservation> queue = new LinkedList<>();

    // synchronized enqueue
    public synchronized void addRequest(Reservation r) {
        queue.offer(r);
        System.out.println("Request added: " + r.getGuestName());
    }

    // synchronized dequeue
    public synchronized Reservation getRequest() {
        return queue.poll();
    }
}

// Booking Processor (Thread)
class BookingProcessor extends Thread {

    private BookingQueue queue;
    private RoomInventory inventory;

    public BookingProcessor(String name, BookingQueue queue, RoomInventory inventory) {
        super(name);
        this.queue = queue;
        this.inventory = inventory;
    }

    @Override
    public void run() {

        while (true) {

            Reservation r;

            // synchronized block for safe retrieval
            synchronized (queue) {
                r = queue.getRequest();
            }

            if (r == null) {
                break;
            }

            System.out.println(getName() + " processing " + r.getGuestName());

            // Critical section (inventory update)
            boolean success = inventory.allocateRoom(r.getRoomType());

            if (success) {
                System.out.println("Booking SUCCESS for " + r.getGuestName()
                        + " (" + r.getRoomType() + ")");
            } else {
                System.out.println("Booking FAILED for " + r.getGuestName()
                        + " (No availability)");
            }
        }
    }
}

// Main class
public class UseCase11ConcurrentBookingSimulation {

    public static void main(String[] args) {

        System.out.println("=========================================");
        System.out.println("     Book My Stay - Version 11.0         ");
        System.out.println("=========================================");

        // Shared resources
        BookingQueue queue = new BookingQueue();
        RoomInventory inventory = new RoomInventory();

        // Simulate concurrent requests
        queue.addRequest(new Reservation("Alice", "Single Room"));
        queue.addRequest(new Reservation("Bob", "Single Room"));
        queue.addRequest(new Reservation("Charlie", "Single Room")); // may fail
        queue.addRequest(new Reservation("David", "Double Room"));
        queue.addRequest(new Reservation("Eve", "Double Room")); // may fail

        // Multiple threads (simulating concurrent users)
        BookingProcessor t1 = new BookingProcessor("Thread-1", queue, inventory);
        BookingProcessor t2 = new BookingProcessor("Thread-2", queue, inventory);

        // Start threads
        t1.start();
        t2.start();

        // Wait for completion
        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Final inventory state
        inventory.displayInventory();

        System.out.println("\nAll concurrent bookings processed safely.");
    }
}