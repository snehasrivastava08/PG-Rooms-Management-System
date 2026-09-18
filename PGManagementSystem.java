import java.util.ArrayList;
import java.util.Scanner;

// ================= ROOM CLASS =================
class Room {
    int roomNo;
    String roomType;
    double rent;
    boolean occupied;
    String tenantName;

    Room(int roomNo, String roomType, double rent) {
        this.roomNo = roomNo;
        this.roomType = roomType;
        this.rent = rent;
        this.occupied = false;
        this.tenantName = "";
    }

    void displayRoom() {
        System.out.println("--------------------------------");
        System.out.println("Room No       : " + roomNo);
        System.out.println("Room Type     : " + roomType);
        System.out.println("Monthly Rent  : Rs." + rent);

        if (occupied) {
            System.out.println("Status        : Occupied");
            System.out.println("Tenant        : " + tenantName);
        } else {
            System.out.println("Status        : Available");
        }
    }
}

// ================= TENANT CLASS =================
class Tenant {
    String name;
    int age;
    String phone;
    int roomNo;

    Tenant(String name, int age, String phone, int roomNo) {
        this.name = name;
        this.age = age;
        this.phone = phone;
        this.roomNo = roomNo;
    }

    void displayTenant() {
        System.out.println("--------------------------------");
        System.out.println("Tenant Name   : " + name);
        System.out.println("Age           : " + age);
        System.out.println("Phone Number  : " + phone);
        System.out.println("Room Number   : " + roomNo);
    }
}

// ================= PAYMENT CLASS =================
class Payment {
    int roomNo;
    String tenantName;
    String month;
    double amount;

    Payment(int roomNo, String tenantName, String month, double amount) {
        this.roomNo = roomNo;
        this.tenantName = tenantName;
        this.month = month;
        this.amount = amount;
    }

    void displayPayment() {
        System.out.println("--------------------------------");
        System.out.println("Room Number   : " + roomNo);
        System.out.println("Tenant        : " + tenantName);
        System.out.println("Month         : " + month);
        System.out.println("Amount        : Rs." + amount);
        System.out.println("Status        : PAID");
    }
}

// ================= MAIN CLASS =================
public class PGManagementSystem {

    static Scanner sc = new Scanner(System.in);

    static ArrayList<Room> rooms = new ArrayList<>();
    static ArrayList<Tenant> tenants = new ArrayList<>();
    static ArrayList<Payment> payments = new ArrayList<>();

    // ================= ADD ROOM =================
    static void addRoom() {

        System.out.println("\n========== ADD ROOM ==========");

        System.out.print("Enter Room Number: ");
        int roomNo = sc.nextInt();
        sc.nextLine();

        // Check duplicate room
        for (Room room : rooms) {
            if (room.roomNo == roomNo) {
                System.out.println("Room already exists!");
                return;
            }
        }

        System.out.print("Enter Room Type (Single/Double/Triple): ");
        String roomType = sc.nextLine();

        System.out.print("Enter Monthly Rent: ");
        double rent = sc.nextDouble();
        sc.nextLine();

        Room room = new Room(roomNo, roomType, rent);
        rooms.add(room);

        System.out.println("\nRoom added successfully!");
    }

    // ================= VIEW ROOMS =================
    static void viewRooms() {

        System.out.println("\n========== ALL ROOMS ==========");

        if (rooms.isEmpty()) {
            System.out.println("No rooms available.");
            return;
        }

        for (Room room : rooms) {
            room.displayRoom();
        }

        System.out.println("--------------------------------");
    }

    // ================= ADD TENANT =================
    static void addTenant() {

        System.out.println("\n========== ADD TENANT ==========");

        if (rooms.isEmpty()) {
            System.out.println("Please add a room first.");
            return;
        }

        System.out.print("Enter Tenant Name: ");
        String name = sc.nextLine();

        System.out.print("Enter Age: ");
        int age = sc.nextInt();
        sc.nextLine();

        System.out.print("Enter Phone Number: ");
        String phone = sc.nextLine();

        System.out.print("Enter Room Number: ");
        int roomNo = sc.nextInt();
        sc.nextLine();

        // Find room
        Room selectedRoom = null;

        for (Room room : rooms) {
            if (room.roomNo == roomNo) {
                selectedRoom = room;
                break;
            }
        }

        // Room doesn't exist
        if (selectedRoom == null) {
            System.out.println("Room not found!");
            return;
        }

        // Room already occupied
        if (selectedRoom.occupied) {
            System.out.println("Room is already occupied!");
            return;
        }

        // Create tenant
        Tenant tenant = new Tenant(name, age, phone, roomNo);
        tenants.add(tenant);

        // Update room
        selectedRoom.occupied = true;
        selectedRoom.tenantName = name;

        System.out.println("\nTenant added successfully!");
        System.out.println("Room " + roomNo + " has been assigned to " + name);
    }

    // ================= VIEW TENANTS =================
    static void viewTenants() {

        System.out.println("\n========== ALL TENANTS ==========");

        if (tenants.isEmpty()) {
            System.out.println("No tenants available.");
            return;
        }

        for (Tenant tenant : tenants) {
            tenant.displayTenant();
        }

        System.out.println("--------------------------------");
    }

    // ================= VACATE ROOM =================
    static void vacateRoom() {

        System.out.println("\n========== VACATE ROOM ==========");

        System.out.print("Enter Room Number to Vacate: ");
        int roomNo = sc.nextInt();
        sc.nextLine();

        Room selectedRoom = null;

        for (Room room : rooms) {
            if (room.roomNo == roomNo) {
                selectedRoom = room;
                break;
            }
        }

        if (selectedRoom == null) {
            System.out.println("Room not found!");
            return;
        }

        if (!selectedRoom.occupied) {
            System.out.println("Room is already available.");
            return;
        }

        String oldTenant = selectedRoom.tenantName;

        // Remove tenant
        tenants.removeIf(tenant -> tenant.roomNo == roomNo);

        // Make room available
        selectedRoom.occupied = false;
        selectedRoom.tenantName = "";

        System.out.println("\nRoom vacated successfully!");
        System.out.println("Previous Tenant: " + oldTenant);
        System.out.println("Room " + roomNo + " is now available.");
    }

    // ================= SEARCH ROOM =================
    static void searchRoom() {

        System.out.println("\n========== SEARCH ROOM ==========");

        System.out.print("Enter Room Number: ");
        int roomNo = sc.nextInt();
        sc.nextLine();

        for (Room room : rooms) {

            if (room.roomNo == roomNo) {
                room.displayRoom();
                System.out.println("--------------------------------");
                return;
            }
        }

        System.out.println("Room not found!");
    }

    // ================= PAY RENT =================
    static void payRent() {

        System.out.println("\n========== PAY RENT ==========");

        System.out.print("Enter Room Number: ");
        int roomNo = sc.nextInt();
        sc.nextLine();

        Room selectedRoom = null;

        for (Room room : rooms) {
            if (room.roomNo == roomNo) {
                selectedRoom = room;
                break;
            }
        }

        if (selectedRoom == null) {
            System.out.println("Room not found!");
            return;
        }

        if (!selectedRoom.occupied) {
            System.out.println("This room is currently vacant.");
            return;
        }

        System.out.println("Tenant: " + selectedRoom.tenantName);

        System.out.print("Enter Month: ");
        String month = sc.nextLine();

        System.out.print("Enter Rent Amount: ");
        double amount = sc.nextDouble();
        sc.nextLine();

        Payment payment = new Payment(
                roomNo,
                selectedRoom.tenantName,
                month,
                amount
        );

        payments.add(payment);

        System.out.println("\n================================");
        System.out.println("        PAYMENT RECEIPT");
        System.out.println("================================");

        payment.displayPayment();

        System.out.println("================================");
        System.out.println("Payment successful!");
        System.out.println("================================");
    }

    // ================= PAYMENT HISTORY =================
    static void paymentHistory() {

        System.out.println("\n========== PAYMENT HISTORY ==========");

        if (payments.isEmpty()) {
            System.out.println("No payment records available.");
            return;
        }

        for (Payment payment : payments) {
            payment.displayPayment();
        }

        System.out.println("--------------------------------");
    }

    // ================= MAIN METHOD =================
    public static void main(String[] args) {

        int choice;

        do {

            System.out.println("\n");
            System.out.println("======================================");
            System.out.println("        PG MANAGEMENT SYSTEM");
            System.out.println("======================================");
            System.out.println("1. Add Room");
            System.out.println("2. View Rooms");
            System.out.println("3. Add Tenant");
            System.out.println("4. View Tenants");
            System.out.println("5. Vacate Room");
            System.out.println("6. Search Room");
            System.out.println("7. Pay Rent");
            System.out.println("8. Payment History");
            System.out.println("9. Exit");
            System.out.println("======================================");

            System.out.print("Enter your choice: ");
            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {

                case 1:
                    addRoom();
                    break;

                case 2:
                    viewRooms();
                    break;

                case 3:
                    addTenant();
                    break;

                case 4:
                    viewTenants();
                    break;

                case 5:
                    vacateRoom();
                    break;

                case 6:
                    searchRoom();
                    break;

                case 7:
                    payRent();
                    break;

                case 8:
                    paymentHistory();
                    break;

                case 9:
                    System.out.println("\nThank you for using PG Management System!");
                    break;

                default:
                    System.out.println("\nInvalid choice! Please try again.");
            }

        } while (choice != 9);

        sc.close();
    }
}