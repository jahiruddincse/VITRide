# VITRide — Campus Ride Sharing Matcher

VITRide is a Core Java command-line application for VIT students who want to find and share rides to common destinations such as the airport, railway station, or city.

The system compares a student's travel details with existing rides using:

* Date
* Pickup location
* Destination
* Overlapping time window
* Available seats

When a compatible ride is found, the system displays the ride, driver, vehicle and common travel time.

---

## Screenshots

### Screenshot 1 — Ride Matching

**ADD SCREENSHOT HERE**

*Figure 1: VITRide finding compatible rides using date, route and overlapping time.*

---

### Screenshot 2 — Group Joining

**ADD SCREENSHOT HERE**

*Figure 2: Palak joining Arjun's ride along with other passengers.*

---

### Screenshot 3 — Ride Members

**ADD SCREENSHOT HERE**

*Figure 3: Ride owner and passenger details after students join the ride.*

---

### Screenshot 4 — Testing

**ADD SCREENSHOT HERE**

*Figure 4: Successful compilation and automated testing.*

---

# Project Overview

Students travelling from VIT Bhopal to the same destination often arrange separate rides even when their travel times are similar.

VITRide provides a simple command-line solution where students can register themselves, create rides, search for matching rides, join or leave rides, view ride members, and manage ride status.

The main feature is the **time-window matching system**.

---

# Command-Line Executability & Environment Requirements

This project is strictly developed as a **terminal-based Command-Line Interface (CLI)** application to comply with the VITyarthi project executability requirements:

* **Fully Executable via Terminal:** The entire program runs directly within the user's terminal environment (macOS Terminal, Linux Bash, Windows Command Prompt / PowerShell).
* **Zero GUI Dependencies:** It does not use AWT, Swing, or JavaFX. It runs reliably in headless automated evaluation environments and grading scripts without requiring a graphical display server.
* **Zero Build Tools / Frameworks:** Does not require Maven, Gradle, IDE configurations, or external libraries. Standard `javac` and `java` commands are all that are needed.

### System Prerequisites:
* **Java Development Kit:** JDK 8, 11, 17, or 21 installed.
* **Terminal:** Standard command-line terminal.

Verify Java in your local terminal:
```bash
java -version
javac -version
```

---

# Main Features

## Student Management

* Add student
* View students
* Search student
* Store contact information

## Ride Management

* Create ride
* View rides
* Cancel ride
* Complete ride
* Remove ride

## Ride Matching

* Compare pickup location
* Compare destination
* Compare date
* Check overlapping travel times
* Check available seats
* Display common travel time

## Ride Participation

* Join a ride
* Leave a ride
* View ride members
* View driver information
* View vehicle information
* Automatically update available seats

## Data Persistence

* Student data is stored locally
* Ride data is stored locally
* Saved data is loaded when the application starts

---

# Technologies Used

* Core Java
* `ArrayList`
* `Scanner`
* Custom checked exception
* `BufferedReader`
* `BufferedWriter`
* `FileReader`
* `FileWriter`
* Basic time-window matching algorithm
* Git and GitHub

No external Java libraries are required.

---

# Requirements

* JDK 8 or newer
* Terminal / command line

The application does not require:

* GUI
* IDE
* Database
* Maven
* Gradle
* External libraries

---

# How to Run in Your Local Terminal

## Step 1 — Clone the Repository

Open your local terminal and run:

```bash
git clone https://github.com/jahiruddincse/VITRide.git
```

Move into the project directory:

```bash
cd VITRide
```

---

## Step 2 — Check Java Installation

```bash
java -version
javac -version
```

Make sure Java 8 or newer is installed on your machine.

---

## Step 3 — Compile the Project from Terminal

```bash
mkdir -p out
javac -d out src/*.java
```

If there are no compiler errors, the project has compiled successfully.

---

## Step 4 — Run the Application from Terminal

```bash
java -cp out Main
```

The application starts immediately inside your terminal window:

```text
===== VITRIDE =====
1. Add Student
2. View Students
3. Search Student
4. Create Ride
5. View Rides
6. Find Ride Match
7. Join Ride
8. Leave Ride
9. View Ride Members
10. Cancel Ride
11. Complete Ride
12. Remove Ride
13. Save
14. Exit
Choice:
```

---

# Example Walkthrough

## Step 1 — Add Students

Create three students.

### Student 1

```text
ID: S1
Name: Arjun
Branch: CSE
Contact: 9876540001
```

### Student 2

```text
ID: S2
Name: Palak
Branch: CSE
Contact: 9876540002
```

### Student 3

```text
ID: S3
Name: Rahul
Branch: AIML
Contact: 9876540003
```

---

## Step 2 — Arjun Creates a Ride

Select:

```text
Choice: 4
```

Enter:

```text
Ride ID: R1
Owner ID: S1
Pickup: VIT Bhopal
Destination: Bhopal Airport
Date: 20-09-2026
Start: 17:00
End: 17:30
Driver Name: Arjun
Driver Contact: 9876540001
Car: Swift
Car No: MP09AB1234
Car Type: 4
```

The system creates the ride and reserves one seat for the driver.

Available student seats:

```text
3
```

---

## Step 3 — Palak Searches for a Ride

Select:

```text
Choice: 6
```

Enter:

```text
Your ID: S2
Pickup: VIT Bhopal
Destination: Bhopal Airport
Date: 20-09-2026
Start: 17:20
End: 17:50
```

VITRide checks:

```text
Same date
Same pickup
Same destination
Active ride
Available seat
Overlapping time
```

The common time is:

```text
17:20 - 17:30
```

The system displays Arjun's ride as a match.

---

## Step 4 — Palak Joins

Select:

```text
Choice: 7
```

Enter:

```text
Ride ID: R1
Student ID: S2
```

Palak joins the ride.

Available student seats become:

```text
2
```

The program then displays the ride and member details.

---

## Step 5 — Rahul Joins

Select:

```text
Choice: 7
```

Enter:

```text
Ride ID: R1
Student ID: S3
```

Rahul joins the same ride.

Available student seats become:

```text
1
```

Now the ride contains:

```text
Driver:
Arjun

Passengers:
Palak
Rahul
```

---

# Ride Matching Logic

A ride is considered compatible when:

1. Both rides are active.
2. The dates are the same.
3. Pickup locations match.
4. Destinations match.
5. The found ride has an available seat.
6. The users are different.
7. Their travel time windows overlap.

The overlap is calculated using:

```text
commonStart = later start time
commonEnd   = earlier end time
```

A match exists when:

```text
commonStart < commonEnd
```

### Example

Arjun:

```text
17:00 - 17:30
```

Palak:

```text
17:20 - 17:50
```

Common time:

```text
17:20 - 17:30
```

Therefore, the rides are compatible.

---

# Seat Management

One seat is reserved for the driver.

| Car Type | Total Seats | Driver | Student Seats |
| -------- | ----------: | -----: | ------------: |
| 4 Seater |           4 |      1 |             3 |
| 6 Seater |           6 |      1 |             5 |

When a student joins:

```text
Available seats - 1
```

When a student leaves:

```text
Available seats + 1
```

---

# Ride Status

A ride can have different states:

```text
ACTIVE
CANCELLED
COMPLETED
```

Only active rides are considered for matching.

---

# Testing

The project includes `MatcherTest.java`.

Run in your terminal:

```bash
mkdir -p testout
javac -d testout src/*.java tests/MatcherTest.java
java -cp testout MatcherTest
```

Expected output:

```text
All tests passed.
```

The test cases check:

* overlapping time windows
* correct common-time calculation
* non-overlapping time windows
* different destinations
* different dates

---

# Data Persistence

VITRide stores data locally in the `data` directory.

```text
data/
├── students.txt
└── rides.txt
```

Student records are stored as:

```text
id|name|branch|contact
```

Ride records contain the ride ID, owner, route, date, time window, driver, vehicle, seat information, status and passengers.

When the application starts, previously saved data is loaded automatically.

---

# Project Structure

```text
VITRide/
├── README.md
├── statement.md
├── .gitignore
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
└── data/
    ├── students.txt
    └── rides.txt
```

---

# Java Concepts Demonstrated

## Classes and Objects

Separate classes are used for students, rides, matching, data management and exception handling.

## Encapsulation

Important fields are private and accessed through methods.

## Collections

`ArrayList` is used to store students, rides and passengers.

## Exception Handling

The project uses the custom checked exception `RideException` with:

* `throw`
* `throws`
* `try-catch`

## File Handling

`BufferedReader`, `BufferedWriter`, `FileReader` and `FileWriter` are used for persistence.

## Algorithmic Logic

The `Matcher` class performs time-window intersection and determines ride compatibility.

---

# Basic Workflow

```text
Register Students
       ↓
Create Ride
       ↓
Search for Ride
       ↓
Compare Route + Date + Time
       ↓
Compatible Ride?
     /       \
   No         Yes
   ↓           ↓
No Match    View Ride
               ↓
           Join Ride
               ↓
        Available Seat Updated
               ↓
          View Members
```

---

# Example Final Ride

After Arjun creates the ride and Palak and Rahul join:

```text
Ride ID: R1
Route: VIT Bhopal -> Bhopal Airport
Date: 20-09-2026
Time: 17:00 - 17:30
Car: Swift
Car No: MP09AB1234
Car Type: 4 Seater
Seats Left: 1

Owner:
Arjun

Passengers:
Palak
Rahul
```

---

# Project Objective

The objective of VITRide is to provide a simple Java-based solution for helping VIT students identify compatible shared rides while demonstrating practical use of object-oriented programming, collections, exception handling, file handling and time-window matching.

---

# Author

**Md Jahiruddin Ahmed**

VIT Bhopal University

Programming in Java (CSE1021)

September 2026
