import com.google.gson.*;
import java.lang.reflect.Member;
import java.lang.reflect.Type;
import java.util.Calendar;
import java.util.GregorianCalendar;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;

public class CalendarDeserializer implements JsonDeserializer<Calendar> {
    @Override
    public Calendar deserialize(JsonElement json, Type calendar, JsonDeserializationContext context) {
        int Day = json.getAsJsonObject().get("dayOfMonth").getAsInt();
        int Month = json.getAsJsonObject().get("month").getAsInt();
        int Year = json.getAsJsonObject().get("year").getAsInt();
        Calendar cl = new GregorianCalendar(Year, Month, Day);

        return cl;
    }
}
