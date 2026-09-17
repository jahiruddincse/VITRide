# VITRide — Campus Cab-Sharing Matcher

A simple, command-line Java application designed for VIT students to easily find and form shared cab groups to common destinations (Airport, Railway Station, Bhopal City), automatically calculating and splitting the fare evenly across members.

---

## Problem Being Solved

VIT Bhopal students heading home or travelling to Bhopal Airport or Railway Stations often book individual cabs or struggle to coordinate rides manually. VITRide provides an intuitive command-line platform where:
1. A student registers their travel route, date, and preferred time.
2. A ride is posted with the estimated total fare.
3. Other students with matching pickup, destination, date, and nearby departure time (within 30 minutes) can discover and join the group.
4. Fare is divided evenly among all ride members automatically.

---

## Main Features

- **Add Student**: Register your name, location, destination, date, and time.
- **Create Ride**: Form a shared ride for your journey with the total cab fare.
- **View Rides**: See all active rides and member counts.
- **Find Match**: Discovers rides going to the same destination on the same date within a 30-minute departure window. Displays time difference and split fare.
- **Join Ride**: Join a matched ride group; displays all group members and updated split fare.
- **View Group**: View all joined members and the current fare per person.
- **Leave Ride**: Leave a shared ride before departure (owner cannot leave their created ride).
- **Data Persistence**: Automatic file storage (`students.txt` and `rides.txt`) across sessions.

---

## Technologies Used

- **Java (JDK 8+)**: Core Java only, no external libraries.
- **Collections**: `ArrayList` for storing students, rides, and group members.
- **File I/O**: `BufferedReader` / `BufferedWriter` for lightweight file persistence in `data/`.
- **Custom Exception**: `RideException` for structured input validation and business rules.

---

## Required Java Version

Java 8 or higher.

Verify your environment:
```bash
java -version
javac -version
```

---

## Setup and Installation

```bash
git clone https://github.com/jahiruddincse/VITRide.git
cd VITRide
```

---

## How to Compile

From the root `VITRide/` directory:

```bash
mkdir -p out
javac -d out src/*.java
```

---

## How to Run

```bash
java -cp out Main
```

Menu options:
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

## How to Test

```bash
mkdir -p testout
javac -d testout src/*.java tests/MatcherTest.java
java -cp testout MatcherTest
```

Expected output:
```text
All tests passed.
```

---

## Project Structure

```text
VITRide/
├── README.md
├── statement.md
├── .gitignore
├── src/
│   ├── Main.java           # Entry point and interactive menu
│   ├── Student.java        # Student entity (name, location, destination, date, time)
│   ├── Ride.java           # Ride entity (route, time, fare, members list)
│   ├── Matcher.java        # 30-minute window matching algorithm
│   ├── Rides.java          # Operations and collections manager
│   ├── Data.java           # Local file storage (load/save)
│   └── RideException.java  # Custom checked exception
├── tests/
│   └── MatcherTest.java    # Automated unit tests
└── data/                   # Auto-generated persistence storage
    ├── students.txt
    └── rides.txt
```

---

## Matching Logic & Fare Splitting

1. **Route & Date Matching**: Pickup location and destination must match (case-insensitive), and the date must match exactly.
2. **Time Window (±30 Minutes)**: Time format `HH:MM` is converted into total minutes from midnight. The absolute difference between student start time and ride start time must be $\le 30$ minutes.
3. **Fare Splitting**:
   $$\text{Fare per person} = \frac{\text{Total Fare}}{\text{Number of Members}}$$

---

## Example Walkthrough

1. **Add Student**:
   - Name: `Arjun`, Location: `VIT Bhopal`, Going To: `Airport`, Date: `20-09-2026`, Time: `17:00`
2. **Create Ride**:
   - Ride ID: `R1`, Name: `Arjun`, Total Fare: `600`
3. **Add Another Student**:
   - Name: `Priya`, Location: `VIT Bhopal`, Going To: `Airport`, Date: `20-09-2026`, Time: `17:20`
4. **Find Match**:
   - Priya finds Ride `R1` (Time diff: 20 minutes, Fare per person: ₹300.0)
5. **Join Ride**:
   - Priya joins `R1`. Both Arjun and Priya are listed as group members, sharing the fare equally (₹300 each).

---

## Author

Md Jahiruddin Ahmed  
VIT Bhopal University — Programming in Java (CSE1021)  
September 2026
