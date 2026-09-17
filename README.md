# VITRide — Campus Cab-Sharing Matcher by Time Window

A command-line Java application that helps VIT students share cab rides to common destinations (airport, railway station, city) by matching their available time windows. Students register their travel window; the system finds who else is going to the same place at an overlapping time.

---

## Problem Being Solved

VIT Bhopal is located outside the city. When students travel home or to the airport or railway station, they book separate cabs — wasting money and seats. There is no shared system to find others going the same way at the same time.

VITRide solves this by letting students post their travel window and finding others whose windows overlap, so they can share a cab.

---

## Main Features

- Register students
- Create a ride with pickup, destination, date, time window, and available seats
- Find rides that match your destination, date, and time window (time-interval intersection)
- Join a ride as a passenger (seat count decreases automatically)
- Leave a ride (seat count restored)
- View passengers of a ride
- Cancel a ride (owner only)
- Mark a ride as completed (owner only)
- Remove a ride (owner only)
- Full data persistence — all data saves to local text files and reloads automatically on next startup

---

## Technologies Used

- Java 11 or higher (Core Java only — no external libraries)
- `java.time.LocalDate`, `java.time.LocalTime` — date and time handling
- `java.util.HashMap`, `java.util.ArrayList` — data storage
- `java.util.stream` — filtering and sorting ride matches
- `java.util.Iterator` — passenger list traversal
- `java.nio.file` (NIO.2) — file read/write persistence
- Custom exception (`InvalidRideException`) with `throw` and `throws`

---

## Required Java Version

Java 11 or higher.

Verify:

```bash
java -version
javac -version
```

---

## Environment Setup

No installation beyond JDK is required. No build tools, no frameworks, no databases.

**macOS / Linux:**

```bash
brew install openjdk@17
```

Or download from: https://adoptium.net

**Windows:**

Download and install from: https://adoptium.net

---

## Installation and Setup

```bash
git clone https://github.com/jahiruddincse/VITRide.git
cd VITRide
```

---

## Compile

Run this from inside the `VITRide` folder:

```bash
mkdir -p out
javac -d out src/*.java
```

No errors means compilation succeeded.

---

## Run

```bash
java -cp out Main
```

You will see:

```
========== VITRIDE ==========
1.  Add Student
2.  View Students
3.  Create Ride
4.  View Rides
5.  Find Ride Match
6.  Join Ride
7.  Leave Ride
8.  View Passengers
9.  Cancel Ride
10. Complete Ride
11. Remove Ride
12. Save Data
13. Exit
==============================
Choice:
```

Type a number and press Enter to use any feature. Data is saved automatically after every add/change. On next startup, all data reloads automatically.

---

## Test

```bash
mkdir -p testout
javac -d testout src/*.java tests/RideMatcherTest.java
java -cp testout RideMatcherTest
```

Expected output:

```
All RideMatcher tests passed.
```

The test verifies four cases:
1. Overlapping time windows → matched correctly
2. Non-overlapping windows → not matched
3. Same time but different destination → not matched
4. Same time and destination but different date → not matched

---

## Testing Instructions

The test class is `tests/RideMatcherTest.java`. It tests `RideMatcher.matches()` directly with known inputs and expected outputs, and throws `AssertionError` with a message if any case fails.

---

## Project Structure

```
VITRide/
├── README.md                      <- This file
├── statement.md                   <- Problem statement
├── .gitignore
├── src/
│   ├── Main.java                  <- Entry point and menu
│   ├── Student.java               <- Student entity
│   ├── Ride.java                  <- Ride entity with passenger management
│   ├── RideMatcher.java           <- Time-window intersection logic
│   ├── RideManager.java           <- Business logic and operations
│   ├── FileManager.java           <- NIO.2 file save/load
│   └── InvalidRideException.java  <- Custom checked exception
├── tests/
│   └── RideMatcherTest.java       <- 4-case unit test
└── data/                          <- Auto-created at runtime
    ├── students.txt
    └── rides.txt
```

---

## Matching Logic Explained

When a student searches for a ride, they enter:
- Pickup location
- Destination
- Date
- Their available start time and end time (travel window)

The system creates a temporary search ride from this input and runs `RideMatcher.matches()` against every active ride in the system.

`RideMatcher.matches()` computes the **time-window intersection**:

```
commonStart = max(rideA.startTime, rideB.startTime)
commonEnd   = min(rideA.endTime,   rideB.endTime)
match = commonStart < commonEnd   (at least 1 minute overlap)
```

Rides are also filtered by:
- Same date
- Same pickup (case-insensitive)
- Same destination (case-insensitive)
- At least 1 seat available
- Status must be ACTIVE
- Cannot match your own ride

Results are sorted by start time (earliest first).

---

## Example Walkthrough

### 1. Add two students

```
Choice: 1
Student ID: S1  |  Name: Arjun  |  Branch: CSE

Choice: 1
Student ID: S2  |  Name: Priya  |  Branch: ECE
```

### 2. S1 posts a ride

```
Choice: 3
Ride ID: R1
Owner Student ID: S1
Pickup: VIT Bhopal
Destination: Bhopal Airport
Date: 2026-09-20
Start Time: 17:00
End Time: 17:30
Available Passenger Seats: 2
→ Ride created.
```

### 3. S2 searches for a match

```
Choice: 5
Your Student ID: S2
Pickup: VIT Bhopal
Destination: Bhopal Airport
Date: 2026-09-20
Your Start Time: 17:20
Your End Time: 17:50
```

Output:

```
Compatible rides:

Ride ID     : R1
Owner       : S1
Route       : VIT Bhopal -> Bhopal Airport
Date        : 2026-09-20
Ride Time   : 17:00 - 17:30
Common Time : 17:20 - 17:30
Seats Left  : 2
```

### 4. S2 joins the ride

```
Choice: 6
Ride ID: R1
Your Student ID: S2
→ Ride joined.
```

### 5. S1 marks the ride complete

```
Choice: 10
Ride ID: R1
Your Student ID (owner): S1
→ Ride completed.
```

---

## Data Storage

All data is saved to the `data/` folder automatically:

| File | Format per line |
|------|----------------|
| `students.txt` | `id\|name\|branch` |
| `rides.txt` | `id\|ownerId\|pickup\|destination\|date\|startTime\|endTime\|seats\|status\|passenger1,passenger2,...` |

The `data/` folder is created automatically when you first save. Data reloads on the next startup with no extra steps.

---

## Author

Md Jahiruddin Ahmed
VIT Bhopal University — Programming in Java (CSE1021)
September 2026
