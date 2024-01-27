// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        Media[] Data = UTILS.ParseData("C://Users//ArchieDraper//IdeaProjects//StarWarsNovelTracker//src//Data2.csv");
        for(Media Item:Data) {
            System.out.println(Item);
        }
    }
}