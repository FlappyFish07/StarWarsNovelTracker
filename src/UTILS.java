import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.util.Date;
import java.nio.file.*;
public class UTILS {
    public static Media[] ParseData(String Filename) {
        Media[] ReturnList;
        try {
            File Data = new File(Filename);
            Scanner Reader = new Scanner(Data);

            long SizeFile = Files.lines(Paths.get(Filename)).count();
            ReturnList = new Media[(int) SizeFile];

            int i = 0;
            while (Reader.hasNextLine()) {
                String CurrentData = Reader.nextLine();
                ReturnList[i] = new Media(CurrentData.split(",")[1],CurrentData.split(",")[2],new Date(CurrentData.split(",")[3]),new LegendsDate(CurrentData.split(",")[4]),CurrentData.split(",")[5],CurrentData.split(",")[6],CurrentData.split(",")[7]);
                i++;
            }
            return ReturnList;

        } catch (FileNotFoundException e) {
            System.out.println("File not found");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("File not found");
            e.printStackTrace();
        }
        return new Media[0];
    }
}
