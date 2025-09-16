import java.util.*;
class train {
    private int trainNo;
    private String trainName;
    private String source;
    private String destination;
    private int totalSeats;
    private int availableSeats;

    // Constructor
    public train(int trainNo, String trainName, String source, String destination, int totalSeats) {
        this.trainNo = trainNo;
        this.trainName = trainName;
        this.source = source;
        this.destination = destination;
        this.totalSeats = totalSeats;
        this.availableSeats = totalSeats;
    }

    // Getters and Setters
    public int getTrainNo() {
        return trainNo;
    }

    public String getTrainName() {
        return trainName;
    }

    public String getSource() {
        return source;
    }

    public String getDestination() {
        return destination;
    }

    public int getTotalSeats() {
        return totalSeats;
    }

    public int getAvailableSeats() {
        return availableSeats;
    }

    public void setAvailableSeats(int availableSeats) {
        this.availableSeats = availableSeats;
    }

    // Method to book a seat
    public boolean bookSeat() {
        if (availableSeats > 0) {
            availableSeats--;
            return true;
        }
        return false;
    }

    // Method to cancel a seat
    public void cancelSeat() {
        if (availableSeats < totalSeats) {
            availableSeats++;
        }
    }

    @Override
    public String toString() {
        return "Train No: " + trainNo + ", Name: " + trainName + ", From: " + source +
                ", To: " + destination + ", Seats Available: " + availableSeats + "/" + totalSeats;
    }
}

public class RAILWAY_BOOKING_SYSTEM {
    public static void main(String[] args) {
        System.out.println("Welcome to the Railway Booking System!");
        // Additional code for the railway booking system would go here
        train train1 = new train();
        //(101, "Express Line", "CityA", "CityB", 100);
        
    }
}
