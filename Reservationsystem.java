import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Reservationsystem {

    // Simulating a database for user credentials
    private static final Map<String, String> userCredentials = new HashMap<>();

    // Simulating a database for reservations using PNR as a key
    private static final Map<String, Reservation> reservations = new HashMap<>();

    private static int pnrCounter = 1000;

    public static void main(String[] args) {
        // Initializing some dummy data
        userCredentials.put("john.doe", "password123");
        userCredentials.put("jane.smith", "123456");

        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to the Online Reservation System!");

        if (login(scanner)) {
            showMainMenu(scanner);
        } else {
            System.out.println("Login failed. Exiting system.");
        }
    }

    // --- Login Form Module ---
    public static boolean login(Scanner scanner) {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        if (userCredentials.containsKey(username) && userCredentials.get(username).equals(password)) {
            System.out.println("Login successful! Welcome, " + username + ".");
            return true;
        } else {
            System.out.println("Invalid username or password.");
            return false;
        }
    }

    public static void showMainMenu(Scanner scanner) {
        boolean exit = false;
        while (!exit) {
            System.out.println("\n--- Main Menu ---");
            System.out.println("1. Make a Reservation");
            System.out.println("2. Cancel a Reservation");
            System.out.println("3. Exit");
            System.out.print("Choose an option: ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    makeReservation(scanner);
                    break;
                case "2":
                    cancelReservation(scanner);
                    break;
                case "3":
                    exit = true;
                    System.out.println("Thank you for using the system. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    // --- Reservation System Module ---
    public static void makeReservation(Scanner scanner) {
        System.out.println("\n--- Make a New Reservation ---");
        System.out.print("Enter your name: ");
        String passengerName = scanner.nextLine();
        System.out.print("Enter Train Name: ");
        String trainName = scanner.nextLine();
        System.out.print("Enter Class Type (e.g., AC, Sleeper): ");
        String classType = scanner.nextLine();
        System.out.print("Enter Date of Journey (YYYY-MM-DD): ");
        String dateOfJourney = scanner.nextLine();
        System.out.print("Enter From (Place): ");
        String fromPlace = scanner.nextLine();
        System.out.print("Enter To (Destination): ");
        String toDestination = scanner.nextLine();

        String pnr = "PNR" + pnrCounter++;
        Reservation newReservation = new Reservation(passengerName, trainName, classType, dateOfJourney, fromPlace, toDestination);
        reservations.put(pnr, newReservation);

        System.out.println("\nReservation successful!");
        System.out.println("Your PNR number is: " + pnr);
    }

    // --- Cancellation Form Module ---
    public static void cancelReservation(Scanner scanner) {
        System.out.println("\n--- Cancel a Reservation ---");
        System.out.print("Enter your PNR number: ");
        String pnr = scanner.nextLine().toUpperCase();

        if (reservations.containsKey(pnr)) {
            Reservation res = reservations.get(pnr);
            System.out.println("\nReservation Details for PNR " + pnr + ":");
            System.out.println(res);
            System.out.print("Do you want to confirm cancellation? (yes/no): ");
            String confirmation = scanner.nextLine();

            if (confirmation.equalsIgnoreCase("yes")) {
                reservations.remove(pnr);
                System.out.println("Reservation with PNR " + pnr + " has been successfully cancelled.");
            } else {
                System.out.println("Cancellation aborted.");
            }
        } else {
            System.out.println("Invalid PNR number. No reservation found.");
        }
    }
}

// A simple class to hold reservation details
class Reservation {
    private String passengerName;
    private String trainName;
    private String classType;
    private String dateOfJourney;
    private String fromPlace;
    private String toDestination;

    public Reservation(String passengerName, String trainName, String classType, String dateOfJourney, String fromPlace, String toDestination) {
        this.passengerName = passengerName;
        this.trainName = trainName;
        this.classType = classType;
        this.dateOfJourney = dateOfJourney;
        this.fromPlace = fromPlace;
        this.toDestination = toDestination;
    }

    @Override
    public String toString() {
        return "Passenger Name: " + passengerName +
               "\nTrain Name: " + trainName +
               "\nClass Type: " + classType +
               "\nDate of Journey: " + dateOfJourney +
               "\nFrom: " + fromPlace +
               "\nTo: " + toDestination;
    }
}