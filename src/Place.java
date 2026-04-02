/**
 * Represents a general place with a zip code, town name, and state name.
 * This is the base class for other more detailed types of places.
 */
public class Place {
	// Stores the zip code of the place. Helps uniquely identify locations.
	private String zipcode;

	// The name of the town for this place.
	private String town;

	// The name of the state for this place.
	private String state;

	/**
	 * Default constructor.
	 * Initializes the fields with default messages indicating no data provided.
	 */
	public Place() {
		this.zipcode = "No zipcode entered.";
		this.town = "No town entered.";
		this.state = "No stated entered.";
	}

	/**
	 * Parameterized constructor.
	 * @param zipcode the zip code of the place
	 * @param town the town name of the place
	 * @param state the state name of the place
	 */
	public Place(String zipcode, String town, String state) {
		this.zipcode = zipcode;
		this.town = town;
		this.state = state;
	}

	/**
	 * Copy constructor.
	 * Creates a new Place by copying the data from another Place object.
	 * Useful when a duplicate object is needed.
	 * @param other the Place object to copy
	 */
	public Place(Place other) {
		this.zipcode = other.zipcode;
		this.town = other.town;
		this.state = other.state;
	}

	/** @return the zip code of the place */
	public String getZipcode() {
		return zipcode;
	}

	/** @return the town name of the place */
	public String getTown() {
		return town;
	}

	/** @return the state name of the place */
	public String getState() {
		return state;
	}

	/** @param zipcode sets a new zip code */
	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	/** @param town sets a new town name */
	public void setTown(String town) {
		this.town = town;
	}

	/** @param state sets a new state name */
	public void setState(String state) {
		this.state = state;
	}

	/**
	 * Returns a human-readable string representation of the place,
	 * combining the zip code, town, and state.
	 * @return formatted string in "zipcode:town,state" format
	 */
	@Override
	public String toString() {
		return zipcode + ":" + town + "," + state;
	}
}
