import java.util.*;

/** This class is a possible user interface for the Place Database
 *   that uses the console to display the menu of command choices.
 */
public class PDConsoleIO {

  /** A reference to the PlaceDB object to be processed.
      Globally available to the command-processing methods.
   */
  private PlaceDB theDatabase = null;

  /** Scanner to read from input console. */
  private Scanner scIn = null;

  // Constructor
  /** Default constructor. */
  public PDConsoleIO() {
    scIn = new Scanner(System.in);
  }

  // Methods
  /** Method to display the command choices and process user
      commands.
      @param thePlaceDatabase A reference to the PlaceDB
             to be processed
   */
  public void processCommands(PlaceDB thePlaceDatabase) {
    String[] commands = {
        "Add Place",
        "Look Up by Zipcode",
	"List All Places by Zipcode Prefix",
	"Distance Between Zipcodes",
        "Exit"};

    theDatabase = thePlaceDatabase;
    int choice;
    do {
      for (int i = 0; i < commands.length; i++) {
        System.out.println("Select " + i + ": "
                           + commands[i]);
      }
      
        choice = scIn.nextInt(); // Read the next choice.
        scIn.nextLine(); // Skip trailing newline.
        switch (choice) {
          case 0:
            doAddPlace();
            break;
          case 1:
            doLookupByZipcode();
            break;
	  case 2:
	    doListAllPlaces();
            break;
	  case 3:
	    doDistance();
          case 4:
            break;  
          default:
            System.out.println("*** Invalid choice "
                               + choice
                               + " - try again!");
        }
     
    }
    while (choice != commands.length - 1);
    System.exit(0);
  }

  /** Method to add a place.
      pre:  The database exists. 
      post: A new place is added.
   */
  private void doAddPlace() {
    // Asking about place information
    System.out.println("Enter zipcode: ");
    String zipcodeInput = scIn.nextLine();
    System.out.println("Enter town: ");
    String townInput = scIn.nextLine();
    System.out.println("Enter state: ");
    String stateInput = scIn.nextLine();

    // Asking about location information
    System.out.println("Enter latitude (or enter 'None' if it's unknown): ");
    String latitudeInput = scIn.nextLine();
    System.out.println("Enter longtitude (or enter 'None' if it's unknown): ");
    String longtitudeInput = scIn.nextLine();

    Place newPlace = null;

    if (latitudeInput.equalsIgnoreCase("None") || longtitudeInput.equalsIgnoreCase("None")) {
      // No location info → base Place
      newPlace = new Place(zipcodeInput, townInput, stateInput);
    } else if (!isNumeric(latitudeInput) || !isNumeric(longtitudeInput)) {
      System.out.println("Latitude and longitude must be valid numbers like 40.5 or -73.9.");
      return;
    } else {
      // Valid latitude and longitude
      double latitude = Double.parseDouble(latitudeInput);
      double longtitude = Double.parseDouble(longtitudeInput);

      System.out.print("Enter population (or 'None' if unknown): ");
      String populationInput = scIn.nextLine();

      boolean valid = true;
      for (int i = 0; i < populationInput.length(); i++) {
        if (!Character.isDigit(populationInput.charAt(i))) {
          valid = false;
          break;
        }
      }

      if (populationInput.equalsIgnoreCase("None")) {
        newPlace = new LocatedPlace(latitude, longtitude, zipcodeInput, townInput, stateInput);
      } else if (!valid) {
        System.out.println("Population must be a whole number like 1000, not words like '1 million'.");
        return;
      } else {
        int population = Integer.parseInt(populationInput);
        newPlace = new PopulatedPlace(latitude, longtitude, zipcodeInput, townInput, stateInput, population);
      }
    }

    // Add to database
    theDatabase.addPlace(newPlace);
  }

  /**
   * Checks whether the given string is a valid number (double).
   * Used to validate latitude and longitude input without crashing.
   */
  private boolean isNumeric(String str) {
    try {
      Double.parseDouble(str);
      return true;
    } catch (NumberFormatException e) {
      return false;
    }
  }


  /** Method to lookup a place by zipcode.
      pre:  The database exists.
      post: No changes made to the database.
   */
  private void doLookupByZipcode() {
    // Request the zipcode.
    System.out.println("Enter zipcode");
    String theZip = scIn.nextLine();
    if (theZip.equals("")) {
      return; // Dialog was cancelled.
    }
    // Look up the zipcode.
    Place p = theDatabase.lookupByZipcode(theZip);
    if (p != null) { // Zipcode was found.
    	System.out.println(p.toString());
    }
    else { // not found.
      // Display the result.
      System.out.println("No such zipcode");

    }
  }

  /** Method to list all places whose zipcodes start with entered prefix.
      pre:  The database exists.
      post: No changes made to the database.
   */
  private void doListAllPlaces() {
    System.out.println("Enter zipcode prefix");
    String prefix = scIn.nextLine();
    if (prefix.equals("")) {
      return;
    }

    boolean found = false;

    for (int i = 0; i < ((MyPlaceDatabase) theDatabase).getSize(); i++) {
      Place p = ((MyPlaceDatabase) theDatabase).getPlaceAt(i);
      if (p.getZipcode().startsWith(prefix)) {
        System.out.println(p);
        found = true;
      }
    }

    if (!found) {
      System.out.println("No places found with that prefix.");
    }
  }


  /** Method to compute the distance between two zipcodes.
      pre:  The database exists.
      post: No changes made to the database.
   */
  private void doDistance() {
    System.out.println("Enter two zipcodes");
  //Part that I wrote starts from here:
    System.out.println("Enter first zipcode: ");
    String zip1=scIn.nextLine();
    System.out.println("Enter second zipcode: ");
    String zip2=scIn.nextLine();
    double dist =theDatabase.distance(zip1,zip2);
    if (dist==-1){
      System.out.println("One or two of the zipcodes don't have the location information.");
    }else{
      System.out.println("Distance: " +dist);
    }
    
  }

}
