public class Main {
  public static void main(String args[]) {
	  PDConsoleIO ui = new PDConsoleIO();
	  PlaceDB pd = new MyPlaceDatabase();
	  ui.processCommands(pd);
  }
}
