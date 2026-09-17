# Problem Statement — VITRide

## Problem Statement

Students studying at residential campuses like VIT Bhopal regularly travel to transit hubs (such as Bhopal Airport, Rani Kamlapati / Bhopal Railway Station) during weekends, holidays, and semester breaks. Individually booking cabs leads to higher travel costs and uncoordinated journeys. 

VITRide provides a streamlined platform where students with similar travel itineraries (same origin, destination, date, and nearby departure time within 30 minutes) can group together into a single shared cab and split the total fare equally.

---

## Scope of the Project

**In Scope:**
- Student registration with travel route and preferred time.
- Creation of shared rides with estimated total cab fare.
- Automated matching of riders based on location, destination, date, and ±30-minute time proximity.
- Joining and leaving shared rides with real-time member listing and fair fare division.
- Persistent local storage of students and rides across program restarts.

**Out of Scope:**
- Real-time GPS cab tracking or external taxi aggregator API integration.
- Online payment processing.
- Multi-threaded client-server networking.

---

## Target Users

- **VIT Bhopal Students**: Looking to find co-passengers for shared cab trips.
- **Ride Initiators**: Students planning a ride who want to reduce their travel expense by taking co-passengers.
- **Joining Students**: Students seeking an existing ride matching their itinerary.

---

## High-Level Features

1. **Student Registration**: Capture student name, origin, destination, date, and departure time.
2. **Ride Creation**: Post a ride tied to a student with the overall cab fare.
3. **Smart Proximity Matching**: Compare departure times in minutes and match students within a 30-minute window.
4. **Group Management & Fair Fare Split**: Dynamically divide total fare equally among joined passengers and display all group members.
5. **Persistence**: Save and reload all data from disk across executions.
