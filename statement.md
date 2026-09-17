# Problem Statement — VITRide

## Problem Statement

VIT Bhopal University is located on the outskirts of the city. Students frequently travel to Bhopal Airport, Habibganj Railway Station, and the city centre — especially at the end of semesters, during holidays, and for personal travel. Each student independently books a cab, resulting in:

- Higher individual travel costs
- Multiple partially-filled cabs going to the same destination at the same time
- No way for students to discover others with compatible travel plans

VITRide addresses this by providing a structured, command-line system where students can post their travel window, discover others heading to the same destination at an overlapping time, and coordinate shared cab rides.

---

## Scope of the Project

**In scope:**

- Student registration within a session
- Posting a ride with pickup, destination, date, available time window, and seat count
- Time-window intersection matching to find compatible rides
- Joining and leaving rides with seat tracking
- Ride status management (ACTIVE, CANCELLED, COMPLETED)
- Data persistence across sessions using local text files

**Out of scope:**

- Real cab booking or payment
- Mobile app or web interface
- GPS or live tracking
- Authentication or passwords
- Network communication

---

## Target Users

| User | Role |
|------|------|
| VIT Bhopal students | Post rides, search for matches, join shared cabs |
| Ride owners | Create, manage, cancel, and complete rides |
| Passengers | Search for matching rides, join, and leave rides |

---

## High-Level Features

| # | Feature | Description |
|---|---------|-------------|
| 1 | Student Management | Add and view registered students |
| 2 | Ride Creation | Register a ride with location, date, time window, and seats |
| 3 | Time-Window Matching | Find rides with overlapping time windows for same route and date |
| 4 | Join / Leave Ride | Add or remove yourself from a ride; seat count updates automatically |
| 5 | Passenger View | See who has joined a ride |
| 6 | Ride Lifecycle | Owner can cancel or mark a ride complete |
| 7 | File Persistence | Data saves to local text files; reloads automatically at next startup |
| 8 | Validation | Input validation and custom exception handling throughout |

---

## Core Algorithm — Time Window Intersection

Given two rides A and B:

```
commonStart = max(A.startTime, B.startTime)
commonEnd   = min(A.endTime,   B.endTime)
```

A match exists when `commonStart < commonEnd` — meaning at least one minute of overlap exists between the two travel windows, on the same date, to the same destination from the same pickup.
