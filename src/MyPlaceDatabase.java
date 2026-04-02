/**
 * Implementation of the PlaceDB interface.
 * Stores an array of Place objects and supports common operations
 * like adding places, looking up by zipcode, listing by prefix,
 * and computing distance between locations.
 */
public class MyPlaceDatabase implements PlaceDB {
	private Place[] places;// Array to store Place objects (can be Place, LocatedPlace, or PopulatedPlace)
	private int size;// Current number of Place objects stored in the array

	/**
	 * Default constructor.
	 * Initializes the internal array with an initial capacity.
	 */
	public MyPlaceDatabase() {
		this.places = new Place[100]; // Initial capacity (can be increased if needed)
		this.size = 0;
	}

	/**
	 * Adds a new place to the database if its zipcode is not already present.
	 *
	 * @param newPlace The new Place to add.
	 */
	@Override
	public void addPlace(Place newPlace) {
		String newZipcode = newPlace.getZipcode();

		//Checking if zipcode already exist
		for (int i = 0; i < size; i++) {
			if (places[i].getZipcode().equals(newZipcode)) {
				System.out.println("Zipcode " + newZipcode + " already exist. Place not added.");
				return;
			}
		}
		//Expand array if needed
		if (size >= places.length) {
			expandArray();
		}
		//Add new place
		places[size] = newPlace;
		size++;

	}

	/**
	 * Doubles the size of the current 'places' array.
	 * Creates a new larger array and copies all existing Place objects
	 * from the old array into the new one. Then updates the 'places' reference
	 * to point to the new array. This allows for dynamic expansion when the array is full.
	 */
	private void expandArray() {
		Place[] newArray = new Place[places.length * 2]; // Create a new array with double capacity
		for (int i = 0; i < places.length; i++) {       // Copy all elements from the old array
			newArray[i] = places[i];
		}
		places = newArray;  // Update reference to the new expanded array
	}


	/**
	 * Searches the database for a place by zipcode.
	 *
	 * @param zipcode The zip code to look for.
	 * @return The matching Place object, or null if not found.
	 */
	@Override
	public Place lookupByZipcode(String zipcode) {
		for (int i = 0; i < size; i++) {
			if (places[i].getZipcode().equals(zipcode)) {
				return places[i];
			}
		}
		return null;
	}

	/**
	 * Lists all places whose zipcodes start with the given prefix.
	 *
	 * @param prefix The zip code prefix to match.
	 */
	@Override
	public void listAllPlaces(String prefix) {
		boolean found = false;
		for (int i = 0; i < size; i++) {
			if (places[i].getZipcode().startsWith(prefix)) {
				System.out.println(places[i]);
				found = true;
			}
		}
		if (!found) {
			System.out.println("No places found with that prefix.");
		}

	}

	/**
	 * Calculates the Euclidean distance between two zipcodes
	 * using their latitude and longitude coordinates.
	 *
	 * @param zip1 First zipcode
	 * @param zip2 Second zipcode
	 * @return Distance as a double, or -1 if any place lacks coordinates
	 */
	@Override
	public double distance(String zip1, String zip2) {
		Place p1 = lookupByZipcode(zip1);
		Place p2 = lookupByZipcode(zip2);

		// Ensure both places are LocatedPlace or PopulatedPlace and check if both places have location info
		if (p1 instanceof LocatedPlace && p2 instanceof LocatedPlace) {
			LocatedPlace loc1 = (LocatedPlace) p1;
			LocatedPlace loc2 = (LocatedPlace) p2;

			double dx = loc1.getLatitude() - loc2.getLatitude();
			double dy = loc1.getLongtitude() - loc2.getLongtitude();

			return Math.sqrt(dx * dx + dy * dy);
		}

		return -1; // Location info missing
	}


	/**
	 * Returns the current number of Place objects stored in the database.
	 * This is used to iterate over the array of places externally when needed.
	 *
	 * @return the number of places stored
	 */
	public int getSize() {
		return size;
	}

	/**
	 * Returns a copy of the Place object at the specified index.
	 * Performs boundary checking and returns null if the index is invalid.
	 * Uses polymorphism to return a deep copy of the actual object type:
	 * - If the object is a PopulatedPlace, returns a new PopulatedPlace copy.
	 * - If the object is a LocatedPlace, returns a new LocatedPlace copy.
	 * - Otherwise, returns a new Place copy.
	 * This ensures the caller receives a separate instance to prevent
	 * unintended modifications to the internal array.
	 *
	 * @param index the position in the array to retrieve
	 * @return a copy of the Place object at the given index, or null if index is out of bounds
	 */
	public Place getPlaceAt(int index) {
		if (index < 0 || index >= size) return null;

		Place p = places[index];
		if (p instanceof PopulatedPlace) {
			return new PopulatedPlace((PopulatedPlace)p);
		} else if (p instanceof LocatedPlace) {
			return new LocatedPlace((LocatedPlace)p);
		} else {
			return new Place(p);
		}
	}

}



