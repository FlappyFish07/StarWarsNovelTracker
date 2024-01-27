import java.util.Date;
import java.util.Locale;

public class Media {
    public String Name;
    public String Author;
    public Date ReleaseDate;
    public LegendsDate TimelineDate;
    public String Category;
    public boolean Owned;
    public boolean Read;

    public Media(String Name, String Author, Date ReleaseDate, LegendsDate TimelineDate, String Category, String Owned, String Read){
        this.Name = Name;
        this.Author = Author;
        this.ReleaseDate = ReleaseDate;
        this.TimelineDate = TimelineDate;
        this.Category = Category.strip().toUpperCase().replaceAll("[^A-Z]","");
        this.Owned = Owned == "True"?true:false;
        this.Read = Read == "True"?true:false;
    }

    public String toString() {
        return Name + ", " + Author + ", " + ReleaseDate + ", " + TimelineDate + ", " + Category + ", " + (Owned?"Owned":"Not owned") + ", " + (Owned?"Read":"Unread");
    }
}
