import java.util.*;

// Train class
class Train {
    private int trainNo;
    private String trainName;
    private String source;
    private String destination;
    private int totalSeats;
    private int availableSeats;

    public Train(int trainNo, String trainName, String source, String destination, int totalSeats) {
        this.trainNo = trainNo;
        this.trainName = trainName;
        this.source = source;
        this.destination = destination;
        this.totalSeats = totalSeats;
        this.availableSeats = totalSeats;
    }

    public int getTrainNo() { return trainNo; }
    public String getTrainName() { return trainName; }
    public String getSource() { return source; }
    public String getDestination() { return destination; }
    public int getTotalSeats() { return totalSeats; }
    public int getAvailableSeats() { return availableSeats; }

    public boolean bookSeat(int count) {
        if (availableSeats >= count) {
            availableSeats -= count;
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "Train No: " + trainNo + ", Name: " + trainName + ", From: " + source +
                ", To: " + destination + ", Seats: " + availableSeats + "/" + totalSeats;
    }
}

// Ticket class
class Ticket {
    private static int ticketCounter = 1;
    private int ticketId;
    private String username;
    private Train train;
    private int seatCount;

    public Ticket(String username, Train train, int seatCount) {
        this.ticketId = ticketCounter++;
        this.username = username;
        this.train = train;
        this.seatCount = seatCount;
    }

    @Override
    public String toString() {
        return "Ticket ID: " + ticketId + ", User: " + username + 
               ", Train: " + train.getTrainName() + " (" + train.getTrainNo() + ")" +
               ", Seats Booked: " + seatCount;
    }
}

// BookingSystem class
class BookingSystem {
    private List<Train> trains = new ArrayList<>();
    private List<Ticket> tickets = new ArrayList<>();

    public void addTrain(Train train) {
        trains.add(train);
    }

    public void viewTrains() {
        System.out.println("\nAvailable Trains:");
        if (trains.isEmpty()) {
            System.out.println("No trains available at the moment.");
            return;
        }
        for (Train t : trains) {
            System.out.println(t);
        }
    }

    public Train findTrain(int trainNo) {
        for (Train t : trains) {
            if (t.getTrainNo() == trainNo) return t;
        }
        return null;
    }

    public Ticket bookTicket(String username, int trainNo, int seatCount) {
        Train train = findTrain(trainNo);
        if (train == null) {
            System.out.println("❌ Train not found.");
            return null;
        }
        if (train.bookSeat(seatCount)) {
            Ticket ticket = new Ticket(username, train, seatCount);
            tickets.add(ticket);
            System.out.println("✅ Ticket booked successfully!");
            return ticket;
        } else {
            System.out.println("❌ Not enough seats available.");
            return null;
        }
    }

    public void viewUserTickets(String username) {
        System.out.println("\nYour Bookings:");
        boolean found = false;
        for (Ticket t : tickets) {
            if (t.toString().contains("User: " + username)) {
                System.out.println(t);
                found = true;
            }
        }
        if (!found) {
            System.out.println("No bookings found.");
        }
    }
}

// Main Class
public class RAILWAY_BOOKING_SYSTEM {
    private static Map<String, String> userCredentials = new HashMap<>();
    private static String loggedInUser = null;
    private static boolean isAdminLoggedIn = false;

    private static BookingSystem bookingSystem = new BookingSystem();

    private static final String ADMIN_USERNAME = "admin";
    private static final String ADMIN_PASSWORD = "admin123";

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n===== Railway Management System =====");
            System.out.println("1. Admin Login");
            System.out.println("2. User Login");
            System.out.println("3. User Registration");
            System.out.println("4. View Available Trains");
            System.out.println("0. Exit");
            System.out.print("Enter choice: ");
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1: // Admin login
                    System.out.print("Enter admin username: ");
                    String adminUser = sc.nextLine();
                    System.out.print("Enter admin password: ");
                    String adminPass = sc.nextLine();
                    if (ADMIN_USERNAME.equals(adminUser) && ADMIN_PASSWORD.equals(adminPass)) {
                        isAdminLoggedIn = true;
                        System.out.println(" Admin login successful!");
                        boolean adminMenu = true;
                        while (adminMenu) {
                            System.out.println("\n--- Admin Panel ---");
                            System.out.println("1. Add Train");
                            System.out.println("2. View All Trains");
                            System.out.println("0. Logout");
                            System.out.print("Enter choice: ");
                            int adminChoice = sc.nextInt();
                            sc.nextLine();
                            switch (adminChoice) {
                                case 1:
                                    System.out.print("Enter Train No: ");
                                    int trainNo = sc.nextInt();
                                    sc.nextLine();
                                    System.out.print("Enter Train Name: ");
                                    String trainName = sc.nextLine();
                                    System.out.print("Enter Source: ");
                                    String source = sc.nextLine();
                                    System.out.print("Enter Destination: ");
                                    String destination = sc.nextLine();
                                    System.out.print("Enter Total Seats: ");
                                    int totalSeats = sc.nextInt();
                                    sc.nextLine();

                                    Train existingTrain = bookingSystem.findTrain(trainNo);
                                    if (existingTrain != null) {
                                        System.out.println(" Train number already exists!");
                                        break;
                                    }

                                    Train newTrain = new Train(trainNo, trainName, source, destination, totalSeats);
                                    bookingSystem.addTrain(newTrain);

                                    System.out.println(" Train added successfully!");
                                    System.out.println("Train Details: " + newTrain);
                                    break;

                                case 2:
                                    bookingSystem.viewTrains();
                                    break;

                                case 0:
                                    isAdminLoggedIn = false;
                                    adminMenu = false;
                                    System.out.println("Admin logged out.");
                                    break;
                                default:
                                    System.out.println("Invalid choice.");
                            }
                        }
                    } else {
                        System.out.println(" Invalid admin credentials.");
                    }
                    break;

                case 2: // User login
                    if (loggedInUser != null) {
                        System.out.println("Already logged in as user: " + loggedInUser);
                        break;
                    }
                    System.out.print("Enter username: ");
                    String username = sc.nextLine();
                    System.out.print("Enter password: ");
                    String password = sc.nextLine();
                    if (userCredentials.containsKey(username) && userCredentials.get(username).equals(password)) {
                        loggedInUser = username;
                        System.out.println(" User login successful!");

                        // User Menu
                        boolean userMenu = true;
                        while (userMenu) {
                            System.out.println("\n--- User Menu ---");
                            System.out.println("1. View Available Trains");
                            System.out.println("2. Book Ticket");
                            System.out.println("3. View My Tickets");
                            System.out.println("0. Logout");
                            System.out.print("Enter choice: ");
                            int userChoice = sc.nextInt();
                            sc.nextLine();
                            switch (userChoice) {
                                case 1:
                                    bookingSystem.viewTrains();
                                    break;

                                case 2:
                                    bookingSystem.viewTrains();
                                    System.out.print("Enter Train No to book: ");
                                    int trainNo = sc.nextInt();
                                    System.out.print("Enter number of seats: ");
                                    int seats = sc.nextInt();
                                    sc.nextLine();
                                    Ticket ticket = bookingSystem.bookTicket(loggedInUser, trainNo, seats);
                                    if (ticket != null) {
                                        System.out.println(ticket);
                                    }
                                    break;

                                case 3:
                                    bookingSystem.viewUserTickets(loggedInUser);
                                    break;

                                case 0:
                                    loggedInUser = null;
                                    userMenu = false;
                                    System.out.println("User logged out.");
                                    break;

                                default:
                                    System.out.println("Invalid choice.");
                            }
                        }

                    } else {
                        System.out.println(" Invalid username or password.");
                    }
                    break;

                case 3: // User registration
                    System.out.print("Choose a username: ");
                    String newUser = sc.nextLine();
                    if (userCredentials.containsKey(newUser)) {
                        System.out.println(" Username already exists.");
                        break;
                    }
                    System.out.print("Choose a password: ");
                    String newPass = sc.nextLine();
                    userCredentials.put(newUser, newPass);
                    System.out.println("Registration successful! You can now log in.");
                    break;

                case 4: // View trains
                    bookingSystem.viewTrains();
                    break;

                case 0: // Exit
                    System.out.println(" Thank you for using the Railway Management System!");
                    sc.close();
                    return;

                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }
}
