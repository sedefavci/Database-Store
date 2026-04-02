# Geospatial Data Store (GIS Implementation)

A Java-based Geographic Information System (GIS) engine designed to manage and query location-based data using robust **Object-Oriented Programming (OOP)** principles and dynamic memory management.

## Architectural Design (Mühendislik Mimarisi)

The core of this project lies in its strict class hierarchy, ensuring data integrity and scalability:

### 1. Class Hierarchy & Inheritance
- **`Place` (Base Class):** Handles fundamental attributes like Name, State, and Zipcode.
- **`LocatedPlace` (Derived):** Extends `Place` by integrating geographic coordinates (Latitude and Longitude).
- **`PopulatedPlace` (Leaf Class):** Extends `LocatedPlace` to include demographic data (Population).



### 2. Dynamic Memory Management
Instead of using fixed-size arrays, the `MyPlaceDatabase` implements a **Custom Dynamic Array** logic:
- **`expandArray()`:** When the internal storage reaches its capacity, the system automatically doubles the array size and migrates data, simulating how real-world database engines handle overflow.

## Key Technical Features

- **Polymorphism in Practice:** All location types (basic, located, and populated) are stored and processed within a single `Place[]` array using polymorphic method calls.
- **Geographic Utilities:** Includes logic for calculating distances and searching records by exact Zipcode or Zipcode prefixes.
- **Encapsulation:** All sensitive data fields are protected with `private` modifiers and accessed through controlled `Getters/Setters`.
- **Interface-Driven Development:** The database logic is abstracted via the `PlaceDB` interface, allowing for future expansions (e.g., switching to a real SQL database without breaking the UI).

## How to Use

1. Clone the repository.
2. Compile the source files in the `src/` directory.
3. Run `PDConsoleIO.java` to start the interactive management console.

### Sample Console Commands:
- `0`: Add a new place (automatically identifies the object type).
- `1`: Search by Zipcode.
- `2`: List places by Zipcode prefix.
- `3`: Calculate distance between two points.

---
