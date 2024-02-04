import java.util.Scanner;

public class OnlineReservationSystem {

    // Simulated central database (you would replace this with a real database)
    private static Database database = new Database();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to the Online Reservation System");

        // Login
        User loggedInUser = login(scanner);

        if (loggedInUser != null) {
            // Proceed to reservation system
            reservationSystem(scanner, loggedInUser);

            // Proceed to cancellation system
            cancellationSystem(scanner);
        } else {
            System.out.println("Invalid login credentials. Exiting...");
        }

        scanner.close();
    }

    private static User login(Scanner scanner) {
        System.out.println("Login Form");

        System.out.print("Enter your username: ");
        String username = scanner.nextLine();
        System.out.print("Enter your password: ");
        String password = scanner.nextLine();

        User user = database.getUser(username, password);

        if (user != null) {
            System.out.println("Login successful. Welcome, " + user.getName() + "!");
            return user;
        } else {
            System.out.println("Invalid login credentials.");
            return null;
        }
    }

    private static void reservationSystem(Scanner scanner, User user) {
        System.out.println("Reservation System");

        // Get user input for reservation
        System.out.print("Enter your name: ");
        String name = scanner.nextLine();
        System.out.print("Enter train number: ");
        int trainNumber = scanner.nextInt();
        // Add more fields as needed

        // Process reservation (you can save this information to the central database)
        Reservation reservation = new Reservation(name, trainNumber);
        database.saveReservation(user, reservation);

        System.out.println("Reservation successful. Thank you, " + name + "!");
    }

    private static void cancellationSystem(Scanner scanner) {
        System.out.println("Cancellation Form");

        // Get user input for cancellation
        System.out.print("Enter your PNR number: ");
        String pnrNumber = scanner.nextLine();

        // Process cancellation (you can retrieve information from the central database using the PNR)
        Reservation reservation = database.getReservationByPNR(pnrNumber);

        if (reservation != null) {
            // Display information related to the PNR
            System.out.println("Details for PNR: " + pnrNumber);
            System.out.println(reservation);

            // Ask for confirmation
            System.out.print("Do you want to confirm the cancellation? (Press OK to confirm): ");
            String confirmation = scanner.nextLine();

            if (confirmation.equalsIgnoreCase("OK")) {
                database.cancelReservation(reservation);
                System.out.println("Cancellation successful for PNR: " + pnrNumber);
            } else {
                System.out.println("Cancellation not confirmed. Exiting...");
            }
        } else {
            System.out.println("Invalid PNR number. Exiting...");
        }
    }
}

class Database {
    // Simulated database operations
    // Implement methods to interact with your database
    public User getUser(String username, String password) {
        // Implement logic to retrieve user from the database
        // Return null if not found
        return new User("John Doe", "john123");
    }

    public void saveReservation(User user, Reservation reservation) {
        // Implement logic to save reservation to the database
    }

    public Reservation getReservationByPNR(String pnrNumber) {
        // Implement logic to retrieve reservation by PNR from the database
        // Return null if not found
        return new Reservation("John Doe", 123);
    }

    public void cancelReservation(Reservation reservation) {
        // Implement logic to cancel reservation in the database
    }
}

class User {
    private String name;
    private String username;

    public User(String name, String username) {
        this.name = name;
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public String getUsername() {
        return username;
    }
}

class Reservation {
    private String passengerName;
    private int trainNumber;

    public Reservation(String passengerName, int trainNumber) {
        this.passengerName = passengerName;
        this.trainNumber = trainNumber;
    }

    @Override
    public String toString() {
        return "Passenger Name: " + passengerName + ", Train Number: " + trainNumber;
    }
}
