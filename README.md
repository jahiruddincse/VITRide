# VITRide — Campus Cab-Sharing Matcher by Time Window

A command-line Java application that helps VIT students share cab rides to common destinations (airport, railway station, city) by matching their available time windows. Students register their travel window; the system finds who else is going to the same place at an overlapping time and shows the driver, car, and all ride members.

---

## Problem Being Solved

VIT Bhopal is located outside the city. Students travelling to the airport, railway station, or city independently book separate cabs — wasting money and seats. There is no system to find others going the same way at the same time.

VITRide lets students post their travel window, find matches with time-window intersection logic, and share cabs — with full driver and car details shown instantly on joining.

---

## Main Features

- Register students with contact number
- Create a ride with pickup, destination, date, time window, driver name and contact, car name and number plate, and cab type (4-seater or 6-seater)
- Seat count automatically reserves 1 for the driver; remaining seats are for passengers
- Search and find rides that match your destination, date, and overlapping time window
- Match result shows driver details, car details, and common time window
- Join a ride — see driver details, car details, and all ride members instantly
- Leave a ride (seat restored)
- View all members of a ride including owner and passengers
- Cancel, complete, or remove a ride (owner only)
- Full data persistence — saves to local text files; reloads on next startup

---

## Technologies Used

- Java 8 or higher (Core Java only — no external libraries)
- `java.io.BufferedWriter`, `java.io.BufferedReader`, `java.io.FileWriter`, `java.io.FileReader` — file persistence
- `java.util.ArrayList` — in-memory data storage
- `java.util.Scanner` — console input
- Custom exception `RideException` with `throw` and `throws`
- Manual time parsing — converts HH:MM strings to integer minutes for interval arithmetic

---

## Required Java Version

Java 8 or higher.

Verify:

```bash
java -version
javac -version
```

---

## Environment Setup

No installation beyond JDK is needed. No build tools, no frameworks, no databases.

**macOS:**

```bash
brew install openjdk@17
```

Or download from: https://adoptium.net

**Windows:**

Download and install from: https://adoptium.net

---

## Installation

```bash
git clone https://github.com/jahiruddincse/VITRide.git
cd VITRide
```

---

## Compile

Run from inside the `VITRide` folder:

```bash
mkdir -p out
javac -d out src/*.java
```

No output means success.

---

## Run

```bash
java -cp out Main
```

You will see:

```
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

Data saves automatically after every change. On the next startup, all previous data reloads automatically.

---

## Test

```bash
mkdir -p testout
javac -d testout src/*.java tests/MatcherTest.java
java -cp testout MatcherTest
```

Expected output:

```
All tests passed.
```

The test covers five cases:
1. Overlapping time windows → match detected
2. Common time window is computed correctly (17:20 - 17:30)
3. Non-overlapping time windows → no match
4. Same time but different destination → no match
5. Same time and destination but different date → no match

---

## Project Structure

```
VITRide/
├── README.md
├── statement.md
├── .gitignore
├── src/
│   ├── Main.java           <- Entry point and menu (14 options)
│   ├── Student.java        <- Student entity (id, name, branch, contact)
│   ├── Ride.java           <- Ride entity (driver, car, seats, passengers)
│   ├── Matcher.java        <- Time-window intersection logic
│   ├── Rides.java          <- All business operations
│   ├── Data.java           <- File save/load using BufferedWriter/Reader
│   └── RideException.java  <- Custom checked exception
├── tests/
│   └── MatcherTest.java    <- 5-case unit test for Matcher
└── data/                   <- Auto-created at runtime
    ├── students.txt
    └── rides.txt
```

---

## Matching Logic

When a student searches for a ride, they enter pickup, destination, date, and their available time window (HH:MM to HH:MM).

`Matcher.match()` converts both time strings to integer minutes and computes the intersection:

```
commonStart = max(rideA.start, rideB.start)
commonEnd   = min(rideA.end,   rideB.end)
match       = commonStart < commonEnd
```

A match is returned only when:
- Same date
- Same pickup (case-insensitive)
- Same destination (case-insensitive)
- Both rides are ACTIVE
- The found ride has at least 1 seat available
- Not the same owner

---

## Seat Calculation

| Car Type | Total Seats | Driver | Student Seats |
|----------|------------|--------|---------------|
| 4 Seater | 4 | 1 | 3 |
| 6 Seater | 6 | 1 | 5 |

Each time a student joins, the available seat count decreases by 1. When they leave, it increases by 1.

---

## Example Walkthrough

### 1. Add two students

```
Choice: 1
Student ID: S1 | Name: Arjun | Branch: CSE | Contact: 9876540001

Choice: 1
Student ID: S2 | Name: Priya | Branch: ECE | Contact: 9876540002
```

### 2. S1 creates a ride

```
Choice: 4
Ride ID: R1
Owner ID: S1
Pickup: VIT Bhopal
Destination: Bhopal Airport
Date: 20-09-2026
Start: 17:00 | End: 17:30
Driver Name: Aman | Driver Contact: 9876543210
Car: Swift | Car No: MP09AB1234
Car Type (4/6): 4
→ Ride created. Available student seats: 3
```

### 3. S2 finds a match

```
Choice: 6
Your ID: S2
Pickup: VIT Bhopal | Destination: Bhopal Airport
Date: 20-09-2026 | Start: 17:20 | End: 17:50

Match found
R1 | VIT Bhopal -> Bhopal Airport | 20-09-2026 | 17:00-17:30 | 4 Seater | Seats: 3 | ACTIVE
Driver: Aman
Driver Contact: 9876543210
Car: Swift
Car No: MP09AB1234
Common Time: 17:20 - 17:30
```

### 4. S2 joins the ride

```
Choice: 7
Ride ID: R1 | Student ID: S2
→ Ride joined.

----- Ride Details -----
Driver: Aman
Driver Contact: 9876543210
Car: Swift | Car No: MP09AB1234
Car Type: 4 Seater | Seats Left: 2

----- Your Details -----
ID: S2 | Name: Priya | Branch: ECE | Contact: 9876540002

----- Ride Members -----
Driver: Aman | Driver Contact: 9876543210
Car: Swift | Car No: MP09AB1234
Car Type: 4 Seater | Seats Left: 2
Owner: Arjun | CSE | 9876540001
Passengers:
S2 | Priya | ECE | 9876540002
```

---

## Data Storage

Files are saved in the `data/` folder (created automatically):

| File | Format per line |
|------|----------------|
| `students.txt` | `id\|name\|branch\|contact` |
| `rides.txt` | `id\|owner\|pickup\|dest\|date\|start\|end\|driver\|driverContact\|car\|carNo\|carType\|totalSeats\|seats\|status\|p1,p2,...` |

---

## Author

Md Jahiruddin Ahmed
VIT Bhopal University — Programming in Java (CSE1021)
September 2026
