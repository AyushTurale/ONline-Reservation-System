import java.util.*;

class User {
    String username;
    String password;
    User(String username, String password) {
        this.username = username;
        this.password = password;
    }
}

class Reservation {
    static int counter = 1000;
    int pnr;
    String name, trainNo, trainName, classType, doj, from, to;

    Reservation(String name, String trainNo, String trainName, String classType, String doj, String from, String to) {
        this.pnr = counter++;
        this.name = name;
        this.trainNo = trainNo;
        this.trainName = trainName;
        this.classType = classType;
        this.doj = doj;
        this.from = from;
        this.to = to;
    }

    void display() {
        System.out.println("PNR: " + pnr);
        System.out.println("Name: " + name);
        System.out.println("Train No: " + trainNo);
        System.out.println("Train Name: " + trainName);
        System.out.println("Class: " + classType);
        System.out.println("Date of Journey: " + doj);
        System.out.println("From: " + from);
        System.out.println("To: " + to);
    }
}

public class OnlineReservationSystem {
    static Scanner sc = new Scanner(System.in);
    static List<User> users = new ArrayList<>();
    static List<Reservation> reservations = new ArrayList<>();

    public static void main(String[] args) {
        users.add(new User("Ayush", "1234")); // Default login

        System.out.println("=== Online Reservation System ===");

        if (login()) {
            while (true) {
                System.out.println("\n1. Make Reservation");
                System.out.println("2. Cancel Reservation");
                System.out.println("3. Exit");
                System.out.print("Enter choice: ");
                int choice = sc.nextInt();
                sc.nextLine();

                switch (choice) {
                    case 1:
                        makeReservation();
                        break;
                    case 2:
                        cancelReservation();
                        break;
                    case 3:
                        System.out.println("Thank you for using the Reservation System.");
                        return;
                    default:
                        System.out.println("Invalid choice.");
                }
            }
        } else {
            System.out.println("Login failed. Exiting.");
        }
    }

    static boolean login() {
        System.out.print("Enter username: ");
        String uname = sc.nextLine();
        System.out.print("Enter password: ");
        String pass = sc.nextLine();

        for (User u : users) {
            if (u.username.equals(uname) && u.password.equals(pass)) {
                System.out.println("Login successful.");
                return true;
            }
        }
        return false;
    }

    static void makeReservation() {
        System.out.print("Enter your name: ");
        String name = sc.nextLine();
        System.out.print("Enter train number: ");
        String trainNo = sc.nextLine();

        // For simplicity, fetch train name automatically based on train number
        String trainName = getTrainName(trainNo);

        System.out.print("Enter class type (Sleeper/AC): ");
        String classType = sc.nextLine();
        System.out.print("Enter date of journey (DD-MM-YYYY): ");
        String doj = sc.nextLine();
        System.out.print("From: ");
        String from = sc.nextLine();
        System.out.print("To: ");
        String to = sc.nextLine();

        Reservation r = new Reservation(name, trainNo, trainName, classType, doj, from, to);
        reservations.add(r);
        System.out.println("Reservation successful! Your PNR is: " + r.pnr);
    }

    static void cancelReservation() {
        System.out.print("Enter your PNR number: ");
        int pnr = sc.nextInt();
        sc.nextLine();

        for (Reservation r : reservations) {
            if (r.pnr == pnr) {
                System.out.println("Reservation Found:");
                r.display();
                System.out.print("Do you want to cancel? (yes/no): ");
                String confirm = sc.nextLine();
                if (confirm.equalsIgnoreCase("yes")) {
                    reservations.remove(r);
                    System.out.println("Reservation cancelled successfully.");
                } else {
                    System.out.println("Cancellation aborted.");
                }
                return;
            }
        }
        System.out.println("No reservation found with that PNR.");
    }

    static String getTrainName(String trainNo) {
        // Simple simulation, can be replaced with database
        switch (trainNo) {
            case "12345": return "Shatabdi Express";
            case "54321": return "Rajdhani Express";
            case "11111": return "Duronto Express";
            default: return "Generic Express";
        }
    }
}
