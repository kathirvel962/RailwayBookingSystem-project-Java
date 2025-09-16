🚆 #Railway Booking System (Java OOP Project)

📌 Project Description

The Railway Booking System is a beginner-friendly console-based Java project that demonstrates the use of Object-Oriented Programming (OOP) concepts in a real-world application.
It allows users (passengers) to view available trains, book tickets, and manage basic railway reservations in a simplified manner.

🎯 Objective

The main goal of this project is to provide a basic ticket booking system while showcasing the four pillars of OOP:

Encapsulation

Inheritance

Polymorphism

Abstraction

🧩 Features

 View Available Trains – Displays all trains along with train number, name, route, and available seats.
 Book Tickets – Passengers can enter their details and book tickets if seats are available.
 Auto Ticket Generation – Each booking generates a unique ticket ID with passenger and train details.
 Seat Management – Once a booking is done, available seats are reduced automatically.
 Error Handling – If the train is not found or no seats are available, the system shows a proper message.

⚙️ OOP Concepts Used

Encapsulation → Train, Passenger, and Ticket classes use private fields with getters/setters.

Inheritance → Passenger class inherits from the User class.

Polymorphism → The displayUser() method is overridden in Passenger class.

Abstraction → The BookingSystem class manages trains and tickets, hiding internal details from the main class.

🏗️ Project Structure

Train Class – Stores train details (train number, name, source, destination, seats).

User Class – A base class for common user details.

Passenger Class – Inherits from User and represents passengers booking tickets.

Ticket Class – Generates unique ticket IDs and stores booking details.

BookingSystem Class – Manages trains, bookings, and ticket storage.

Main Class – Provides a menu-driven console interface for interaction.

📌 Example Usage

User views available trains.

User enters passenger details and train number.

System checks seat availability.

If available → A ticket is generated and displayed.

If full → Shows a "Booking Failed" message.

✅ Advantages

Simple and easy to use.

Demonstrates core OOP concepts in Java.

Beginner-friendly for students and learners.

Can be extended with features like:

Ticket cancellation

File/Database storage


