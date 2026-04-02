/**
 * Represents a place with geographical coordinates (latitude and longitude).
 * Extends Place by adding location data for mapping or distance calculations.
 */
public class LocatedPlace extends Place {
	// Latitude of the location. Represents vertical coordinate.
	private double latitude;

	// Longitude of the location. Represents horizontal coordinate.
	private double longtitude;

	/**
	 * Default constructor.
	 * Initializes with default Place and coordinates set to 0.0.
	 */
	public LocatedPlace() {
		super();
		this.latitude = 0.0;
		this.longtitude = 0.0;
	}

	/**
	 * Parameterized constructor.
	 * @param latitude the latitude of the place
	 * @param longtitude the longitude of the place
	 * @param zipcode the zip code
	 * @param town the town name
	 * @param state the state name
	 */
	public LocatedPlace(double latitude, double longtitude, String zipcode, String town, String state) {
		super(zipcode, town, state);
		this.latitude = latitude;
		this.longtitude = longtitude;
	}

	/**
	 * Copy constructor.
	 * Duplicates another LocatedPlace including its coordinates.
	 * @param other the LocatedPlace to copy
	 */
	public LocatedPlace(LocatedPlace other) {
		super(other);
		this.latitude = other.latitude;
		this.longtitude = other.longtitude;
	}

	/** @return the latitude of the place */
	public double getLatitude() {
		return latitude;
	}

	/** @return the longitude of the place */
	public double getLongtitude() {
		return longtitude;
	}

	/** @param latitude sets a new latitude */
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	/** @param longtitude sets a new longitude */
	public void setLongtitude(double longtitude) {
		this.longtitude = longtitude;
	}

	/**
	 * Returns a full string representation of this LocatedPlace including inherited location info and coordinates.
	 * @return a string including zipcode, town, state, latitude, and longitude
	 */
	@Override
	public String toString() {
		return super.toString() + "," + latitude + "," + longtitude;
	}
}
