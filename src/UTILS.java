import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.nio.file.*;
public class UTILS {
    public static final String Filename = "C://Users//ArchieDraper//IdeaProjects//StarWarsNovelTracker//src//Data.csv";
    public static final HashMap<String, String> CategoryToName = new HashMap<>();
    static {
        CategoryToName.put("", "");
        CategoryToName.put("CHILDRENSBOOK", "Children's Book");
        CategoryToName.put("SHORTSTORY", "Short Story");
        CategoryToName.put("JUNIORREADER", "Junior Reader");
        CategoryToName.put("ADULTNOVEL", "Adult Novel");
    }
    public static String NameToCategory(String s){
        return s.strip().toUpperCase().replaceAll("[^A-Z]","");
    }
    public static Media[] ParseData() {
        Media[] ReturnList;
        try {
            File Data = new File(Filename);
            Scanner Reader = new Scanner(Data);

            long SizeFile = Files.lines(Paths.get(Filename)).count();
            ReturnList = new Media[(int) SizeFile];

            int i = 0;
            while (Reader.hasNextLine()) {
                String CurrentData = Reader.nextLine();
                ReturnList[i] = new Media(i, CurrentData.split(",")[1],CurrentData.split(",")[2],new GregorianCalendar(Integer.parseInt(CurrentData.split(",")[3].split("/")[2]),Integer.parseInt(CurrentData.split(",")[3].split("/")[0])-1,Integer.parseInt(CurrentData.split(",")[3].split("/")[1])),new LegendsDate(CurrentData.split(",")[4]),CurrentData.split(",")[5], CurrentData.split(",")[6].equals("True"), CurrentData.split(",")[7].equals("True"), new Media[0]);
                i++;
            }
            return ReturnList;

        } catch (IOException e) {
            System.out.println("File not found");
            e.printStackTrace();
        }
        return new Media[0];
    }

    public static void SaveData(Media[] Data){
        try {
            FileWriter Saver = new FileWriter(Filename);
            for (int i=0;i<Data.length;i++){
                Saver.write(i + "," + Data[i].Name + "," + Data[i].Author + "," + new SimpleDateFormat("M/d/y").format(Data[i].ReleaseDate.getTime()) + "," + Data[i].TimelineDate.toString() + "," + UTILS.CategoryToName.get(Data[i].Category) + "," + (Data[i].Owned?"True":"False") + "," + (Data[i].Read?"True":"False") + "\n");
            }
            Saver.close();
        } catch (IOException e){
            System.out.println("File not found");
            e.printStackTrace();
        }
    }

}
