**VITRide** is a command-line Java application for VIT students to find and share rides to common destinations. It matches students based on **pickup location, destination, date, and overlapping travel time**.

The system also manages ride seats, passengers, driver details, car details, and local data storage.

---

## Features

* Student registration and search
* Create and view rides
* Match rides using route, date, and time-window overlap
* Calculate common available travel time
* Join and leave rides
* Automatic seat management
* View ride members and driver/car details
* Cancel, complete, and remove rides
* Local file-based data persistence

---

## SCREENSHOTS

---

## Technologies Used

* Java
* Object-Oriented Programming
* `ArrayList`
* `Scanner`
* Exception Handling
* File Handling (`BufferedReader`, `BufferedWriter`)
* Basic time parsing and interval calculation
* Git and GitHub

**External dependencies:** None

**Database:** Not required

**GUI:** Not required

---

# Setup and Execution

## 1. Requirements

Install **JDK 8 or newer**.

Verify the installation:

```bash
java -version
javac -version
```

The project can run on macOS, Linux, or Windows using a standard terminal.

### macOS

If Java is installed but `java` or `javac` is not available in the terminal:

```bash
export JAVA_HOME=$(/usr/libexec/java_home)
export PATH="$JAVA_HOME/bin:$PATH"
```

Then verify again:

```bash
java -version
javac -version
```

---

## 2. Clone the Repository

Run:

```bash
git clone https://github.com/jahiruddincse/VITRide.git
```

Then enter the project directory:

```bash
cd VITRide
```

---

## 3. Dependencies and Configuration

VITRide uses only standard Java libraries.

**No additional dependency installation is required.**

There is also **no configuration file, database, framework, or environment variable required** to run the application.

At runtime, the program uses the local `data/` directory for storing student and ride information.

---

## 4. Compile the Project

From the `VITRide` folder:

```bash
mkdir -p out
javac -d out src/*.java
```

If no error is displayed, compilation was successful.

---

## 5. Run the Project

```bash
java -cp out Main
```

The application starts directly in the terminal.

Example menu:

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

The complete application can be operated through terminal input. No IDE or GUI is required.

---

# Testing

VITRide includes a test class for the ride-matching algorithm.

## Compile Tests

```bash
mkdir -p testout
javac -d testout src/*.java tests/MatcherTest.java
```

## Run Tests

```bash
java -cp testout MatcherTest
```

Expected output:

```text
All tests passed.
```

The test covers:

* Overlapping time windows
* Common-time calculation
* Non-overlapping time windows
* Different destinations
* Different dates

---

# Matching Logic

A ride is considered compatible when:

* The date is the same
* Pickup location is the same
* Destination is the same
* Both rides are active
* A seat is available
* The users are different
* Their travel time windows overlap

The common time is calculated using:

```text
common start = later start time
common end   = earlier end time
```

A match exists when the common start time is earlier than the common end time.

Example:

```text
Ride 1: 17:00 - 17:30
Ride 2: 17:20 - 17:50

Common time: 17:20 - 17:30
```

---

# Data Storage

The application stores data locally in:

```text
data/students.txt
data/rides.txt
```

Previously saved data is loaded when the application starts.

---

# Project Structure

```text
VITRide/
├── README.md
├── statement.md
├── .gitignore
├── src/
│   ├── Main.java
│   ├── Student.java
│   ├── Ride.java
│   ├── Matcher.java
│   ├── Rides.java
│   ├── Data.java
│   └── RideException.java
├── tests/
│   └── MatcherTest.java
└── data/
    ├── students.txt
    └── rides.txt
```

---

# Command-Line Executability

VITRide is designed to be completely executable from a terminal using standard Java commands:

```bash
javac -d out src/*.java
java -cp out Main
```
