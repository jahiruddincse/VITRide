# VITRide — Campus Cab-Sharing Matcher

A command-line Core Java application designed for university students to find co-passengers, form shared cab groups to common destinations (Airport, Railway Station, City), and split the total fare equally.

---

## 📸 Application Execution Screenshots

> *Add your terminal execution screenshots here for evaluation.*

### 1. Match Discovery & Fare Comparison (30-Minute Proximity Algorithm)
```text
[ INSERT SCREENSHOT HERE: Option 4 - Find Match output showing matched rides, time difference, and split fares ]
```
*Demonstrates the proximity algorithm finding active rides on the same route and date within a 30-minute departure difference.*

---

### 2. Group Joining & Dynamic Fare Splitting
```text
[ INSERT SCREENSHOT HERE: Option 5 - Join Ride output showing member list and updated fare per person ]
```
*Demonstrates a student joining a ride group, dynamically reducing the fare per person (e.g., from ₹300 to ₹200).*

---

### 3. Compilation & Automated Unit Tests Passing
```text
[ INSERT SCREENSHOT HERE: Terminal output running tests with "All tests passed." ]
```
*Demonstrates clean compilation with zero warnings and 100% automated test suite pass rate.*

---

## 🚀 Quick Start — How to Run

### Step 1: Clone the Repository
```bash
git clone https://github.com/jahiruddincse/VITRide.git
cd VITRide
```

### Step 2: Compile the Project
```bash
mkdir -p out
javac -d out src/*.java
```

### Step 3: Run the Application
```bash
java -cp out Main
```

### Step 4: Run Automated Tests
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

## 📖 Problem Being Solved

Students studying at residential campuses like VIT Bhopal regularly travel to transit hubs (such as Bhopal Airport and Rani Kamlapati / Bhopal Railway Station) during weekends, holidays, and semester breaks. Individually booking cabs leads to higher travel costs and uncoordinated journeys. 

**VITRide** provides a structured, terminal-based platform where:
1. Students register their origin, destination, date, and preferred departure time.
2. A student creates a shared ride with an estimated total fare.
3. Other students with matching routes and a nearby start time (within a 30-minute window) can discover and join the ride.
4. Total cab fare is dynamically divided equally among all joined members.

---

## 🛠️ System Requirements & Environment Setup

- **Operating System:** macOS, Windows 10/11, or Linux.
- **Java Development Kit (JDK):** JDK 8 or higher (JDK 11 / JDK 17 / JDK 21 recommended).
- **Dependencies:** None (Pure Core Java — uses only standard Java libraries).
- **Build / Packaging Tool:** None required; runs directly using standard `javac` and `java` commands.

### Verify Java Installation:
```bash
java -version
javac -version
```

---

## 📂 Project Structure

```text
VITRide/
├── README.md              # Project documentation and setup guide
├── statement.md           # Problem statement and scope
├── .gitignore             # Git ignore rules for build artifacts
├── src/
│   ├── Main.java          # CLI entry point and menu interaction
│   ├── Student.java       # Student entity (name, location, destination, date, time)
│   ├── Ride.java          # Ride entity (route, time, fare, member list)
│   ├── Matcher.java       # Time-window proximity matching algorithm
│   ├── Rides.java         # Business logic manager for all ride operations
│   ├── Data.java          # File persistence layer using BufferedReader/Writer
│   └── RideException.java # Custom checked exception for input & business validation
├── tests/
│   └── MatcherTest.java   # Automated unit tests covering all edge cases
└── data/
    ├── students.txt       # Local persistent storage for registered students
    └── rides.txt          # Local persistent storage for rides and members
```

---

## ⚙️ Core Matching & Fare-Splitting Logic

1. **Route & Date Matching:**
   - Pickup location and destination must match (case-insensitive).
   - Date must match exactly (`DD-MM-YYYY`).

2. **Time-Proximity Window ($\le 30$ Minutes):**
   - Departure time format `HH:MM` is converted into absolute minutes from midnight:
     $$\text{minutes} = (\text{hour} \times 60) + \text{minute}$$
   - The absolute difference between rider time and ride start time must satisfy:
     $$|\text{studentTime} - \text{rideTime}| \le 30 \text{ minutes}$$

3. **Equal Fare Division:**
   $$\text{Fare per person} = \frac{\text{Total Fare}}{\text{Total Joined Members}}$$

---

## 🎮 Interactive Menu Walkthrough

When running `java -cp out Main`, you will see:

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

### Step-by-Step Usage Example:

1. **Add Students (Option 1):**
   - Student 1: `Arjun`, Location: `VIT Bhopal`, Going To: `Airport`, Date: `20-09-2026`, Time: `17:00`
   - Student 2: `Palak`, Location: `VIT Bhopal`, Going To: `Airport`, Date: `20-09-2026`, Time: `17:20`

2. **Create Ride (Option 2):**
   - Ride ID: `R1`, Name: `Arjun`, Total Fare: `600`
   - Initial state: 1 member (Arjun), Fare per person = ₹600.0.

3. **Find Match (Option 4):**
   - Palak searches for rides.
   - The system detects `R1` with a **20-minute difference** and recommends joining with a projected fare of ₹300.0.

4. **Join Ride (Option 5):**
   - Palak enters Ride ID `R1`.
   - Both members are listed and the fare per person automatically updates to **₹300.0**.

5. **Leave Ride (Option 7):**
   - Any passenger can leave before departure; the fare splits back across remaining members.

6. **Exit & Persist (Option 8):**
   - All data is flushed to `data/students.txt` and `data/rides.txt`, reloading automatically upon restart.

---

## 👤 Author Information

- **Student Name:** Md Jahiruddin Ahmed
- **Course:** Programming in Java (CSE1021)
- **Institution:** VIT Bhopal University
- **Submission Date:** September 2026
