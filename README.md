# VITRide — Campus Cab-Sharing Matcher

**VITRide** is a terminal-based **Core Java application** designed to help students find and join shared cab rides with other students traveling along the same route.

The system matches students based on their **pickup location, destination, travel date, and departure time**. Rides are considered compatible when the departure times are within a **30-minute window**. The application also manages group members and automatically calculates the **equal fare share** for each member.

---

## 🚕 Problem Statement

Students frequently travel between the VIT campus and nearby locations. Booking a cab individually can increase the cost, while finding other students traveling at a similar time and route is difficult.

**VITRide** provides a simple command-line solution where students can:

* Create and view rides
* Find students traveling on similar routes
* Join existing shared rides
* Leave rides when required
* View ride groups
* Automatically calculate the fare per person

The project demonstrates how **Object-Oriented Programming, collections, file handling, exception handling, and basic matching algorithms** can be combined to solve a practical problem.

---

## ✨ Features

* 👤 **Student Registration**

  * Add students to the system
  * Store student information locally

* 🚕 **Ride Creation**

  * Create a ride with pickup location
  * Specify destination, date, time, and fare

* 🔎 **Ride Matching**

  * Match rides using:

    * Pickup location
    * Destination
    * Date
    * Departure time

* ⏱️ **30-Minute Time Window**

  * Rides are considered compatible when their departure times differ by no more than 30 minutes.

* 👥 **Shared Ride Groups**

  * Students can join compatible rides
  * Group members can be viewed

* 💰 **Automatic Fare Splitting**

  * Total fare is divided equally among group members.

* ↩️ **Leave Ride**

  * Members can leave a shared ride.

* 💾 **Local Data Persistence**

  * Student and ride information is stored in text files.
  * Previously saved data is loaded when the application starts.

* ⚠️ **Exception Handling**

  * Custom exception handling is used for invalid ride-related operations.

---

## 🧠 Matching Logic

A ride is considered a match when the following conditions are satisfied:

1. Pickup locations are the same.
2. Destinations are the same.
3. Travel dates are the same.
4. The students are different.
5. Departure times differ by **30 minutes or less**.

### Time Difference

Time is converted into minutes:

```text
total minutes = hours × 60 + minutes
```

The difference between two departure times is then calculated:

```text
difference = |student time - ride time|
```

A match exists when:

```text
difference <= 30
```

### Example

```text
Student A: 10:00 AM
Student B: 10:25 AM

Difference = 25 minutes

Result: MATCH
```

But:

```text
Student A: 10:00 AM
Student B: 10:40 AM

Difference = 40 minutes

Result: NO MATCH
```

---

## 💰 Fare Calculation

When multiple students share a ride, the fare is divided equally.

```text
Fare per person = Total Fare / Number of Members
```

### Example

```text
Total Fare = ₹600
Members    = 3

Fare per person = ₹600 / 3
                = ₹200
```

---

## 🛠️ Technologies Used

| Technology         | Purpose                                     |
| ------------------ | ------------------------------------------- |
| Java               | Core application development                |
| OOP                | Classes, objects, encapsulation and methods |
| ArrayList          | Storing students, rides and groups          |
| Scanner            | Command-line input                          |
| Exception Handling | Handling invalid operations                 |
| File Handling      | Local data persistence                      |
| Git                | Version control                             |
| GitHub             | Source-code hosting                         |

### Project Characteristics

* **Language:** Java
* **JDK:** 8+
* **Interface:** Command Line / Terminal
* **Database:** None
* **External Libraries:** None
* **Framework:** None
* **Build Tool:** None

---

## 📁 Project Structure

```text
VITRide/
│
├── data/
│   ├── students.txt
│   └── rides.txt
│
├── src/
│   ├── Main.java
│   ├── Student.java
│   ├── Ride.java
│   ├── Matcher.java
│   ├── Rides.java
│   ├── Data.java
│   └── RideException.java
│
├── tests/
│   └── MatcherTest.java
│
├── README.md
├── statement.md
└── .gitignore
```

---

## 🏗️ System Architecture

```text
                ┌────────────────────┐
                │      Student       │
                │ Registration/Input │
                └─────────┬──────────┘
                          │
                          ▼
                ┌────────────────────┐
                │        Main        │
                │   CLI Controller   │
                └─────────┬──────────┘
                          │
             ┌────────────┼────────────┐
             ▼            ▼            ▼
        ┌─────────┐  ┌──────────┐  ┌─────────┐
        │  Ride   │  │ Matcher  │  │  Rides  │
        │  Model  │  │  Logic   │  │ Manager │
        └─────────┘  └──────────┘  └─────────┘
                          │
                          ▼
                  ┌───────────────┐
                  │     Data      │
                  │ File Handling │
                  └───────┬───────┘
                          │
                          ▼
                    ┌──────────┐
                    │  data/   │
                    │   .txt    │
                    └──────────┘
```

---

## 📋 Application Menu

When the program starts, the following menu is displayed:

```text
===== VITRIDE =====

1. Add Student
2. Create Ride
3. View Rides
4. Find Match
5. Join Ride
6. View Group
7. Leave Ride
8. Exit

Choice:
```

---

## 🚀 Getting Started

### 1. Install Java

Install **JDK 8 or newer**.

Verify the installation:

```bash
java -version
javac -version
```

---

### 2. Clone the Repository

```bash
git clone https://github.com/jahiruddincse/VITRide.git
```

Move into the project directory:

```bash
cd VITRide
```

---

### 3. Compile the Project

Create an output directory:

```bash
mkdir -p out
```

Compile all source files:

```bash
javac -d out src/*.java
```

---

### 4. Run the Application

```bash
java -cp out Main
```

The application will start directly in the terminal.

---

## 🧪 Testing

VITRide includes a test class for validating the core matching functionality.

Compile the source files and test:

```bash
mkdir -p testout
javac -d testout src/*.java tests/MatcherTest.java
```

Run the test:

```bash
java -cp testout MatcherTest
```

Expected output:

```text
All tests passed.
```

The tests verify important functionality including:

* Ride matching
* Time difference calculation
* Destination validation
* Date validation
* Fare calculation

---

## 💾 Data Storage

VITRide does not require a database.

Application data is stored locally:

```text
data/
├── students.txt
└── rides.txt
```

The application loads previously saved information when it starts.

This keeps the project lightweight and demonstrates **Java file handling** without requiring an external database system.

---

## 🧩 Core Java Concepts Demonstrated

The project applies several concepts from **Object-Oriented Programming and Java programming**:

### Classes and Objects

Separate classes represent students, rides, matching logic and data management.

### Encapsulation

Data and related methods are organized within appropriate classes.

### ArrayList

Dynamic collections are used to manage students and rides.

### Methods

Application operations are separated into reusable methods.

### Exception Handling

Invalid operations are handled using Java exception mechanisms and a custom `RideException`.

### File Handling

Text files are used to persist application data between executions.

### Modular Design

Different responsibilities are separated into individual classes instead of placing the entire application inside one class.

---

## 🔄 Application Workflow

```text
Start
  │
  ▼
Load Saved Data
  │
  ▼
Display Main Menu
  │
  ├── Add Student
  │
  ├── Create Ride
  │
  ├── View Rides
  │
  ├── Find Match
  │      │
  │      ▼
  │   Compare Route
  │      │
  │      ▼
  │   Compare Date
  │      │
  │      ▼
  │   Compare Time
  │      │
  │      ▼
  │   Show Matches
  │
  ├── Join Ride
  │
  ├── View Group
  │
  ├── Leave Ride
  │
  └── Exit
         │
         ▼
      Save Data
         │
         ▼
        End
```

---

## 🔐 Design Decisions

### Why Command Line?

The CLI keeps the application simple and focuses on demonstrating the underlying Java concepts and business logic without requiring GUI frameworks.

### Why ArrayList?

`ArrayList` provides a simple dynamic structure for storing students and rides whose number can change during execution.

### Why Text Files?

Text files provide lightweight local persistence without requiring database configuration or external dependencies.

### Why a 30-Minute Window?

Students traveling on the same route do not necessarily leave at exactly the same time. A 30-minute matching window allows compatible rides to be identified while keeping the matching condition straightforward.

### Why Separate Classes?

Separating responsibilities improves readability, maintainability and demonstrates proper object-oriented design.

---

## 📸 Screenshots

Screenshots of the application can be added below.

### Main Menu

```text
Add screenshot here
```

### Ride Creation

```text
Add screenshot here
```

### Ride Matching

```text
Add screenshot here
```

### Group & Fare Splitting

```text
Add screenshot here
```

### Testing

```text
Add screenshot here
```

---

## 🔮 Future Improvements

Possible extensions include:

* GUI or web interface
* Database integration
* Student authentication
* VIT email verification
* Real-time ride notifications
* Google Maps integration
* Distance-based fare estimation
* Online ride booking
* Rating and feedback system
* Mobile application
* Better matching based on multiple nearby pickup locations

---

## 📚 Academic Context
**MD JAHIRUDDIN AHMED, 25BAI11468**
**Course:** Programming in Java (CSE2006)
**Institution:** VIT Bhopal University
**Project:** VITRide — Campus Cab-Sharing Matcher
**Year:** 2026

---
