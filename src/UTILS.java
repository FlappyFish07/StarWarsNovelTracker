import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.chrono.Era;
import java.util.*;
import java.nio.file.*;
import com.google.gson.*;
import com.sun.source.tree.Tree;

public class UTILS {
    public static final String Filename = "out/production/StarWarsNovelTracker/Data/SaveData.json";
    public static final HashMap<String, String> CategoryToName = new HashMap<>();
    static {
        CategoryToName.put("", "");
        CategoryToName.put("CHILDRENSBOOK", "Children's Book");
        CategoryToName.put("SHORTSTORY", "Short Story");
        CategoryToName.put("JUNIORREADER", "Junior Reader");
        CategoryToName.put("ADULTNOVEL", "Adult Novel");
    }
    public static final LinkedHashMap<String, LegendsDate> EraList = new LinkedHashMap<>();
    static {
        EraList.put("", new LegendsDate(false, 0));
        EraList.put("Dawn of the Jedi", new LegendsDate(false, 25793, false, 5001));
        EraList.put("The Old Republic", new LegendsDate(false, 5000, false, 1001));
        EraList.put("Rise of the Sith", new LegendsDate(false, 1000, false, 1));
        EraList.put("The Rebellion", new LegendsDate(true, 0, true, 3));
        EraList.put("The New Republic", new LegendsDate(true, 4, true, 24));
        EraList.put("New Jedi Order", new LegendsDate(true, 25, true, 36));
        EraList.put("Legacy", new LegendsDate(true, 37, true, 140));
    }
    public static boolean InEra(String EraString, LegendsDate Year){
        for(Map.Entry<String, LegendsDate> bound : EraList.entrySet()){
            if (EraString == bound.getKey()){
                if (Year.isWithin(bound.getValue())){
                    return true;
                }
                return false;
            }
        }
        return false;
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
                ReturnList[i] = new Media(i, CurrentData.split(",")[1], CurrentData.split(",")[2], CurrentData.split(",")[3], CurrentData.split(",")[4], CurrentData.split(",")[5], CurrentData.split(",")[6].equals("True"), CurrentData.split(",")[7].equals("True"), new Media[0]);
                i++;
            }
            return ReturnList;

        } catch (IOException e) {
            System.out.println("File not found");
            e.printStackTrace();
        }
        return new Media[0];
    }
    public static Media[] ParseJSONData(){
        try {
            Media[] ReturnList;
            String FileData = Files.readString(Paths.get(Filename));
            GsonBuilder builder = new GsonBuilder().excludeFieldsWithoutExposeAnnotation();
            builder.registerTypeAdapter(Calendar.class, new CalendarDeserializer());
            builder.registerTypeAdapter(LegendsDate.class, new TimelineDeserializer());

            Gson gson = builder.create();

            ReturnList = gson.fromJson(FileData, Media[].class);
            return ReturnList;
        } catch (IOException e){
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

    public static void SaveJSONData(Media[] Data){
        try {
            FileWriter Saver = new FileWriter(Filename);

            GsonBuilder builder = new GsonBuilder().setPrettyPrinting().excludeFieldsWithoutExposeAnnotation();
            builder.registerTypeAdapter(Calendar.class, new CalendarSerializer());
            builder.registerTypeAdapter(LegendsDate.class, new TimelineSerializer());

            Gson gson = builder.create();

            Saver.write(gson.toJson(Data));
            Saver.close();
        } catch (IOException e){
            System.out.println("File not found");
            e.printStackTrace();
        }
    }

}
