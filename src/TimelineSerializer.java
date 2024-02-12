import com.google.gson.*;
import java.lang.reflect.Member;
import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;

public class TimelineSerializer implements JsonSerializer<LegendsDate> {
    @Override
    public JsonElement serialize(LegendsDate LD, Type calendar, JsonSerializationContext context) {
        JsonObject obj = new JsonObject();

        if(LD.OneDate){
            obj.addProperty("Start Date", LD.StartDate);
            obj.addProperty("Start ABY", LD.ABY);
            obj.addProperty("One Date", true);
        } else{
            obj.addProperty("Start Date", LD.StartDate);
            obj.addProperty("Start ABY", LD.ABY);
            obj.addProperty("End Date", LD.EndDate);
            obj.addProperty("End ABY", LD.EndABY);
            obj.addProperty("One Date", false);
        }

        return obj;
    }
}

