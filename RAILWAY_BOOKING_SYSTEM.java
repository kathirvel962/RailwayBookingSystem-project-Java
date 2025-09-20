import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

public class RAILWAY_BOOKING_SYSTEM {
    private static Map<String, String> userCredentials = new HashMap<>();//used to fill the user name and password
    private static String loggedInUser = null; //to check the user is logged in or not
    private static boolean isAdminLoggedIn = false; //to check the admin is logged in or not

    private static final String ADMIN_USERNAME = "kathir";
    private static final String ADMIN_PASSWORD = "kathir123";

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        BookingSystem bookingSystem = new BookingSystem();

        while (true) {
            System.out.println("\n===== Railway Booking System =====");
            System.out.println("1. Admin Login");
            System.out.println("2. User Login");
            System.out.println("3. User Registration");
            System.out.println("0. Exit");
            System.out.print("=======================================\n");
            System.out.print("Enter choice: ");
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    if (isAdminLoggedIn) {
                        System.out.println("Already logged in as admin.");
                        break;
                    }
                    System.out.print("Enter admin username: ");
                    String adminUser = sc.nextLine();
                    System.out.print("Enter admin password: ");
                    String adminPass = sc.nextLine();
                    if (ADMIN_USERNAME.equals(adminUser) && ADMIN_PASSWORD.equals(adminPass)) {
                        isAdminLoggedIn = true;
                        System.out.println("Admin login successful!");
                        boolean adminMenu = true;
                        while (adminMenu) {
                            System.out.println("\n***** Admin Panel ******");
                            System.out.println("1. Add Train");
                            System.out.println("2. View Trains");
                            System.out.println("3. Sort Trains by Available Seats");
                            System.out.println("4. Sort Trains by Name");
                            System.out.println("5. Delete Train");
                            System.out.println("0. Logout");
                            System.out.println("***************************");
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
                                    System.out.print("Enter Destination:  ");
                                    String destination = sc.nextLine();
                                    System.out.print("Enter Total Seats: ");
                                    int totalSeats = sc.nextInt();
                                    sc.nextLine();
                                    bookingSystem.addTrain(new Train(trainNo, trainName, source, destination, totalSeats));
                                    System.out.println("Train added successfully!");
                                    break;


                                case 2:
                                    bookingSystem.viewTrains();
                                    break;
                                case 3:
                                    bookingSystem.sortTrainsByAvailableSeats();
                                    System.out.println("Trains sorted by available seats (descending).");
                                    bookingSystem.viewTrains();
                                    break;
                                case 4:
                                    bookingSystem.sortTrainsByName();
                                    System.out.println("Trains sorted by name (A-Z).");
                                    bookingSystem.viewTrains();
                                    break;
                                case 5:
                                    System.out.print("Enter Train No to delete: ");
                                    int trainNoToDelete = sc.nextInt();
                                    sc.nextLine();
                                    boolean ok = bookingSystem.removeTrain(trainNoToDelete);
                                    if (ok) {
                                        System.out.println("Train " + trainNoToDelete + " deleted successfully (associated tickets removed).");
                                    } else {
                                        System.out.println("Train not found.");
                                    }
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
                        System.out.println("Invalid admin credentials.");
                    }
                    break;

                case 2:
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
                        System.out.println("User login successful!");
                        boolean userMenu = true;
                        while (userMenu) {
                            System.out.println("\n####### User Menu #######");
                            System.out.println("1. View Available Trains");
                            System.out.println("2. Book Ticket");
                            System.out.println("3. Sort Trains by Available Seats");
                            System.out.println("4. Sort Trains by Name");
                            System.out.println("0. Logout");
                            System.out.println("############################");
                            System.out.print("Enter choice: ");
                            int userChoice = sc.nextInt();
                            sc.nextLine();
                            switch (userChoice) {
                                case 1:
                                    bookingSystem.viewTrains();
                                    break;
                                case 2:
                                    System.out.print("Enter Name: ");
                                    String name = sc.nextLine();
                                    System.out.print("Enter Age: ");
                                    int age = sc.nextInt();
                                    sc.nextLine();
                                    String phone_number = "";
                                    while (true) {
                                        System.out.print("Enter phone number: ");
                                        phone_number = sc.nextLine();
                                        if (phone_number.matches("\\d{10}")) {
                                            break;
                                        } else {
                                            System.out.println("Please enter a valid 10-digit phone number.");
                                        }
                                    }
                                    System.out.print("do you need to include meal (yes/no): ");
                                    String mealChoice = sc.nextLine();
                                    Passenger passenger = new Passenger(name, age, phone_number, mealChoice);

                                    System.out.print("Enter Train Number: ");
                                    int trainNoToBook = sc.nextInt();
                                    sc.nextLine();

                                    Train selectedTrain = bookingSystem.findTrain(trainNoToBook);
                                    if (selectedTrain == null) {
                                        System.out.println("Invalid train number.");
                                        break;
                                    }

                                    System.out.println(String.format(
                                            "Select Class: 1) First AC (%.2f)  2) Second Sleeper (%.2f)  3) Third Sleeper (%.2f)",
                                            selectedTrain.getFare(TravelClass.FIRST_AC),
                                            selectedTrain.getFare(TravelClass.SECOND_SLEEPER),
                                            selectedTrain.getFare(TravelClass.THIRD_SLEEPER)));

                                    int classChoice = sc.nextInt();
                                    sc.nextLine();
                                    TravelClass t = null;
                                    switch (classChoice) {
                                        case 1:
                                            t = TravelClass.FIRST_AC;
                                            break;
                                        case 2:
                                            t = TravelClass.SECOND_SLEEPER;
                                            break;
                                        case 3:
                                            t = TravelClass.THIRD_SLEEPER;
                                            break;
                                        default:
                                            System.out.println("Invalid class choice.");
                                            break;
                                    }

                                    if (t != null) {
                                        Ticket ticket = bookingSystem.bookTicket(passenger, trainNoToBook, t);
                                        if (ticket != null) {
                                            System.out.println("\nBooking Successful!\n" + ticket);
                                        } else {
                                            System.out.println("Booking Failed.");
                                        }
                                    }
                                    break;
                                case 3:
                                    bookingSystem.sortTrainsByAvailableSeats();
                                    System.out.println("Trains sorted by available seats (descending).");
                                    bookingSystem.viewTrains();
                                    break;
                                case 4:
                                    bookingSystem.sortTrainsByName();
                                    System.out.println("Trains sorted by name (A-Z).");
                                    bookingSystem.viewTrains();
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
                        System.out.println("Invalid username or password.");
                    }
                    break;

                case 3:
                    System.out.print("Choose a username: ");
                    String newUser = sc.nextLine();
                    if (userCredentials.containsKey(newUser)) {
                        System.out.println("Username already exists.");
                        break;
                    }
                    System.out.print("Choose a password: ");
                    String newPass = sc.nextLine();
                    userCredentials.put(newUser, newPass);
                    System.out.println("Registration successful! You can now log in.");
                    break;

                case 0:
                    System.out.println("Thank you for using the Railway Booking System!");
                    return;

                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }
}


// Train class (Encapsulation)
class Train {
    private int trainNo;
    private String trainName;
    private String source;
    private String destination;
    private int totalSeats;
    private int availableSeats;
    private Map<TravelClass, Double> fares;

    public Train(int trainNo, String trainName, String source, String destination, int totalSeats) {
        this.trainNo = trainNo;
        this.trainName = trainName;
        this.source = source;
        this.destination = destination;
        this.totalSeats = totalSeats;
        this.availableSeats = totalSeats;
        this.fares = new HashMap<>();
        this.fares.put(TravelClass.FIRST_AC, 1000.0);
        this.fares.put(TravelClass.SECOND_SLEEPER, 500.0);
        this.fares.put(TravelClass.THIRD_SLEEPER, 300.0);
    }

    public int getTrainNo() { return trainNo; }
    public String getTrainName() { return trainName; }
    public String getSource() { return source; }
    public String getDestination() { return destination; }
    public int getTotalSeats() { return totalSeats; }
    public int getAvailableSeats() { return availableSeats; }
    public void setAvailableSeats(int availableSeats) { this.availableSeats = availableSeats; }
    public double getFare(TravelClass travelClass) { return fares.get(travelClass); }

    public boolean bookSeat() {
        if (availableSeats > 0) {
            availableSeats--;
            return true;
        }
        return false;
    }

    public void cancelSeat() {
        if (availableSeats < totalSeats) {
            availableSeats++;
        }
    }

    @Override
    public String toString() {
        return "TrainNo: " + trainNo + ", Name: " + trainName + ", From: " + source +
                ", To: " + destination + ", Seats Available: " + availableSeats + "/" + totalSeats;
    }
}

// User class (Abstraction, base for Passenger)
abstract class User {
    private String name;
    private int age;

    public User(String name, int age) {
        this.name = name;
        this.age = age;

    }

    public String getName() { return name; }
    public int getAge() { return age; }

    // Polymorphism: to be overridden
    public void displayUser() {
        System.out.println("User: " + name + ", Age: " + age);
    }
}

// Passenger class (Inheritance, Polymorphism)
class Passenger extends User {
    private String idProof;
    private String mealChoice;

    public Passenger(String name, int age, String idProof, String mealChoice) {
        super(name, age);
        this.idProof = idProof;
        this.mealChoice = mealChoice;
    }

    public String getIdProof() { return idProof; }
    public String getMealChoice() { return mealChoice; }

    @Override
    public void displayUser() {
        System.out.println("Passenger: " + getName() + ", Age: " + getAge() + ", ID: " + idProof + ", Meal: " + mealChoice);
    }
}

// Ticket class (Encapsulation, auto ticket + PNR generation)
class Ticket {
    private static int counter = 500;
    private final int ticketId;
    private final String pnr;
    private Passenger passenger;
    private Train train;
    private TravelClass travelClass;

    public Ticket(Passenger passenger, Train train, TravelClass travelClass) {
        this.ticketId = ++counter;
        this.passenger = passenger;
        this.train = train;
        this.travelClass = travelClass;
        this.pnr = "PNR" + train.getTrainNo() + ticketId;
    }

    public int getTicketId() { return ticketId; }
    public String getPnr() { return pnr; }
    public Passenger getPassenger() { return passenger; }
    public Train getTrain() { return train; }
    public TravelClass getTravelClass() { return travelClass; }

    @Override
    public String toString() {
        return "PNR: " + pnr + "\n" +
                "Ticket ID: " + ticketId + "\n" +
                "Passenger: " + passenger.getName() + ", Age: " + passenger.getAge() + ", ID: " + passenger.getIdProof() + "\n" +
                "Train: " + train.getTrainNo() + " - " + train.getTrainName() + "\n" +
                "Route: " + train.getSource() + " to " + train.getDestination() + "\n" +
                "Class: " + travelClass + ", Fare: " + train.getFare(travelClass) +
                (passenger.getMealChoice().equalsIgnoreCase("yes") ? " + Meal Included" : "");
    }
}

// BookingSystem class (Abstraction)
class BookingSystem {
    public void sortTrainsByAvailableSeats() {
        trains.sort((a, b) -> Integer.compare(b.getAvailableSeats(), a.getAvailableSeats()));
    }

    public void sortTrainsByName() {
        trains.sort((a, b) -> a.getTrainName().compareToIgnoreCase(b.getTrainName()));
    }
    private List<Train> trains = new ArrayList<>();
    private List<Ticket> tickets = new ArrayList<>();

    public void addTrain(Train train) {
        trains.add(train);
    }

    public void viewTrains() {
        System.out.println("\nAvailable Trains:");
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

    public Ticket bookTicket(Passenger passenger, int trainNo, TravelClass travelClass) {
        Train train = findTrain(trainNo);
        if (train == null) {
            System.out.println("Train not found!");
            return null;
        }
        if (train.getAvailableSeats() <= 0) {
            System.out.println("No seats available on this train!");
            return null;
        }
        train.bookSeat();
        Ticket ticket = new Ticket(passenger, train, travelClass);
        tickets.add(ticket);
        return ticket;
    }

    
    public boolean removeTrain(int trainNo) {
        Train target = findTrain(trainNo);
        if (target == null) return false;
        boolean removed = trains.remove(target);
        if (removed) {
            tickets.removeIf(t -> t.getTrain().getTrainNo() == trainNo);
        }
        return removed;
    }
}

enum TravelClass {
    FIRST_AC,
    SECOND_SLEEPER,
    THIRD_SLEEPER
}


