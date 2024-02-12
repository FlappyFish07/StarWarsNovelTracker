import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;

import java.lang.reflect.Type;

public class LegendsDate {
    public boolean ABY;
    public int StartDate;
    public boolean EndABY;
    public int EndDate;
    public boolean OneDate;

    public LegendsDate(boolean ABY, int Date) {
        this.ABY = ABY;
        this.StartDate = Date;
        this.OneDate = true;
    }
    public LegendsDate(boolean StartABY, int StartDate, boolean EndABY, int EndDate){
        this.ABY = StartABY;
        this.StartDate = StartDate;
        this.EndABY = EndABY;
        this.EndDate = EndDate;
        this.OneDate = false;
    }
    public LegendsDate(String Date) {
        String[] SplitDate = Date.split("-");
        if(SplitDate.length == 2){
            StartDate = Integer.parseInt(SplitDate[0].split(" ")[0]);
            ABY = SplitDate[0].split(" ")[1].equals("ABY") ? true : false;
            EndDate = Integer.parseInt(SplitDate[1].strip().split(" ")[0]);
            EndABY = SplitDate[1].strip().split(" ")[1].equals("ABY") ? true : false;
            this.OneDate = false;
        } else {
            StartDate = Integer.parseInt(Date.split(" ")[0]);
            ABY = Date.split(" ")[1].equals("ABY") ? true : false;
            this.OneDate = true;
        }
    }
    public boolean isWithin(LegendsDate Bounds){
        if(Bounds.OneDate){
            return false;
        }
        System.out.println(this.toInt() + " " + Bounds.toInt() + "," + Bounds.toEndInt());
        if(this.toInt() < Bounds.toInt() || this.toInt() > Bounds.toEndInt()){
            return false;
        }
        System.out.println(this.OneDate);
        if (this.OneDate){
            return true;
        }
        if (this.toEndInt() < Bounds.toInt() && this.toEndInt() > Bounds.toEndInt()){
            return false;
        }
        return true;
    }
    public int toInt(){
        return ABY?StartDate:-StartDate;
    }
    public int toEndInt(){
        return EndABY?EndDate:-EndDate;
    }

    @Override
    public String toString() {
        if (OneDate){
            return StartDate  + " " + (ABY?"ABY":"BBY");
        } else {
            return StartDate + " " + (ABY?"ABY":"BBY") + " - " + EndDate + " " + (EndABY?"ABY":"BBY");
        }
    }
}

