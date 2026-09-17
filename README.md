# VITRide — Campus Cab-Sharing Matcher

VITRide is a **terminal-based Core Java application** that helps students find co-passengers and form shared cab groups for common destinations.

It matches rides using **pickup location, destination, date, and a 30-minute departure time window**, with automatic equal fare splitting among group members.

---

## Screenshots

### Main Menu

![VITRide Main Menu](https://github.com/user-attachments/assets/1bee5735-b110-4cb0-8b8a-45ac55e7df3e)

### Ride Matching

**ADD SCREENSHOT HERE**

### Group Joining & Fare Splitting

**ADD SCREENSHOT HERE**

### Testing

**ADD SCREENSHOT HERE**

---

## Features

* Student registration
* Ride creation and viewing
* Ride matching by route, date, and time
* Join and leave shared rides
* Automatic fare division
* Group member viewing
* Local data persistence

---

## Technologies

* Java (JDK 8+)
* OOP
* `ArrayList`
* `Scanner`
* Exception Handling
* File Handling
* Git and GitHub

**External dependencies:** None  
**Database:** None  
**GUI:** None  

---

## Setup & Execution

### 1. Requirements

Install **JDK 8 or newer**.

```bash
java -version
javac -version
```

### 2. Clone

```bash
git clone https://github.com/jahiruddincse/VITRide.git
cd VITRide
```

### 3. Dependencies & Configuration

No external libraries, database, framework, build tool, or configuration file is required.

The application uses the local `data/` directory for storage.

### 4. Compile

```bash
mkdir -p out
javac -d out src/*.java
```

### 5. Run

```bash
java -cp out Main
```

The application runs completely through the terminal.

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

## Testing

Compile and run the test:

```bash
mkdir -p testout
javac -d testout src/*.java tests/MatcherTest.java
java -cp testout MatcherTest
```

Expected:

```text
All tests passed.
```

The tests cover ride matching, time difference, destination/date checks, and fare calculation.

---

## Matching Logic

A ride matches when the pickup, destination, and date are the same, the users are different, and the departure-time difference is within **30 minutes**.

```text
minutes = (hours × 60) + minutes
diff = |student_time - ride_time|

match = diff <= 30
```

Fare is divided equally:

```text
fare per person = total fare / number of members
```

---

## Data Storage

```text
data/
├── students.txt
└── rides.txt
```

Saved data is loaded automatically when the application starts.

---

## Project Structure

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
```

---

## Command-Line Execution

VITRide is fully executable from a terminal using:

```bash
javac -d out src/*.java
java -cp out Main
```

No GUI or IDE is required.

---

## Author

**Md Jahiruddin Ahmed**  
Programming in Java (CSE2006)  
VIT Bhopal University  
September 2026
