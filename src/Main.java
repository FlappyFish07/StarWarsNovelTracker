// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        Media[] Data = UTILS.ParseJSONData();
        javax.swing.SwingUtilities.invokeLater(() -> new GraphicsManager(Data)); // Schedule a job for the event-dispatching thread, creating and showing this application's GUI.
    }
}