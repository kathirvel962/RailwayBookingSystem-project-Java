import java.util.*;
// class train {
//     private int trainNo;
//     private String trainName;
//     private String source;
//     private String destination;
//     private int totalSeats;
//     private int availableSeats;

//     // Constructor
//     public train(int trainNo, String trainName, String source, String destination, int totalSeats) {
//         this.trainNo = trainNo;
//         this.trainName = trainName;
//         this.source = source;
//         this.destination = destination;
//         this.totalSeats = totalSeats;
//         this.availableSeats = totalSeats;
//     }

//     // Getters and Setters
//     public int getTrainNo() {
//         return trainNo;
//     }

//     public String getTrainName() {
//         return trainName;
//     }

//     public String getSource() {
//         return source;
//     }

//     public String getDestination() {
//         return destination;
//     }

//     public int getTotalSeats() {
//         return totalSeats;
//     }

//     public int getAvailableSeats() {
//         return availableSeats;
//     }

//     public void setAvailableSeats(int availableSeats) {
//         this.availableSeats = availableSeats;
//     }

//     // Method to book a seat
//     public boolean bookSeat() {
//         if (availableSeats > 0) {
//             availableSeats--;
//             return true;
//         }
//         return false;
//     }

//     // Method to cancel a seat
//     public void cancelSeat() {
//         if (availableSeats < totalSeats) {
//             availableSeats++;
//         }
//     }

//     @Override
//     public String toString() {
//         return "Train No: " + trainNo + ", Name: " + trainName + ", From: " + source +
//                 ", To: " + destination + ", Seats Available: " + availableSeats + "/" + totalSeats;
//     }
// }
class Admin{
    private String username="kathir";
    private String password="1234";

    public Admin(String username, String password) {
        this.username = username;
        this.password = password;
        if(this.username.equals("kathir") && this.password.equals("1234")){
            System.out.println("Login Successful");
        }
        else{
            System.out.println("Login Failed");
        }
    }
}

public class RAILWAY_BOOKING_SYSTEM {
    public static void main(String[] args) {
        System.out.println("Welcome to the Railway Booking System!");
        Scanner sc = new Scanner(System.in);
        System.out.println("\n===== Railway Booking System =====");
            System.out.println("1. Admin Login");
            System.out.println("2. User Menu");
            System.out.println("0. Exit");
            System.out.print("Enter choice: ");
            int choice = sc.nextInt();
            sc.nextLine(); 
        switch (choice)
        {
            case 1:
            System.out.print("Enter Admin Username: ");
            String username;
            username = sc.nextLine();
            System.out.print("Enter Admin Password: ");
            String password;
            password = sc.nextLine();
            Admin admin = new Admin(username, password);
            break;
            // Add other cases as needed
        }
    }
}
