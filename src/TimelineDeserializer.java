import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;

import java.lang.reflect.Type;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class TimelineDeserializer implements JsonDeserializer<LegendsDate> {
    @Override
    public LegendsDate deserialize(JsonElement json, Type calendar, JsonDeserializationContext context) {
        LegendsDate ReturnDate;
        if (json.getAsJsonObject().get("One Date").getAsBoolean()){
            System.out.println(json.getAsJsonObject().get("Start Date").getAsInt());
            ReturnDate = new LegendsDate(json.getAsJsonObject().get("Start ABY").getAsBoolean(),json.getAsJsonObject().get("Start Date").getAsInt());
        } else {
            ReturnDate = new LegendsDate(json.getAsJsonObject().get("Start ABY").getAsBoolean(),json.getAsJsonObject().get("Start Date").getAsInt(),json.getAsJsonObject().get("End ABY").getAsBoolean(),json.getAsJsonObject().get("End Date").getAsInt());
        }

        return ReturnDate;
    }
}
