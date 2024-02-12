import com.google.gson.*;
import java.lang.reflect.Member;
import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;

public class CalendarSerializer implements JsonSerializer<Calendar> {
    @Override
    public JsonElement serialize(Calendar cl, Type calendar, JsonSerializationContext context) {
        JsonObject obj = new JsonObject();

        obj.addProperty("Day", new SimpleDateFormat("d").format(cl.getTime()));
        obj.addProperty("Month", new SimpleDateFormat("M").format(cl.getTime()));
        obj.addProperty("Year", new SimpleDateFormat("y").format(cl.getTime()));

        return obj;
    }
}

