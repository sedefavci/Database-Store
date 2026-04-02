/**
 * Represents a place that has both location and population data.
 * Extends LocatedPlace by adding the number of people living there.
 */
public class PopulatedPlace extends LocatedPlace {

	// The population of the place. Useful for demographic analysis.
	private int population;

	/**
	 * Default constructor.
	 * Initializes location info using superclass and sets population to 0.
	 */
	public PopulatedPlace() {
		super();
		this.population = 0;
	}

	/**
	 * Parameterized constructor.
	 * @param latitude latitude of the place
	 * @param longtitude longitude of the place
	 * @param zipcode zip code of the place
	 * @param town town name
	 * @param state state name
	 * @param population number of people living in the place
	 */
	public PopulatedPlace(double latitude, double longtitude, String zipcode, String town, String state, int population) {
		super(latitude, longtitude, zipcode, town, state);
		this.population = population;
	}

	/**
	 * Copy constructor.
	 * Creates a new PopulatedPlace object as a copy of the given one.
	 *
	 * @param other the PopulatedPlace object to copy from
	 */
	public PopulatedPlace(PopulatedPlace other) {
		super(other);
		this.population = other.population;
	}


	/** @return the current population of the place */
	public int getPopulation() {
		return population;
	}

	/** @param population sets a new population count */
	public void setPopulation(int population) {
		this.population = population;
	}

	/**
	 * Returns a complete string representation including location and population data.
	 * @return formatted string with all details
	 */
	@Override
	public String toString() {
		return super.toString() + "," + population;
	}
}
