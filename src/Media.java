import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class Media extends JPanel implements ItemListener{
    public String Name;
    public String Author;
    public RealDate ReleaseDate;
    public LegendsDate TimelineDate;
    public String Category;
    public boolean Owned;
    public boolean Read;

    public Media(String Name, String Author, RealDate ReleaseDate, LegendsDate TimelineDate, String Category, boolean Owned, boolean Read){
        this.Name = Name;
        this.Author = Author;
        this.ReleaseDate = ReleaseDate;
        this.TimelineDate = TimelineDate;
        this.Category = Category.strip().toUpperCase().replaceAll("[^A-Z]","");
        this.Owned = Owned;
        this.Read = Read;

        this.DisplayDetails();
    }

    public String toString() {
        return Name + ", " + Author + ", " + ReleaseDate + ", " + TimelineDate + ", " + Category + ", " + (Owned?"Owned":"Not owned") + ", " + (Owned?"Read":"Unread");
    }

    public JTextArea DisplayName;
    public JTextArea DisplayAuthor;
    public JTextArea DisplayDate;
    public JTextArea DisplayTimelineDate;
    public JTextArea DisplayCategory;
    public JCheckBox DisplayOwned;
    public JCheckBox DisplayRead;

    public void DisplayDetails(){
        DisplayName = new JTextArea(this.Name);
        DisplayName.setEditable(false);
        this.add(DisplayName, BorderLayout.CENTER);

        DisplayAuthor = new JTextArea(this.Author);
        DisplayAuthor.setEditable(false);
        this.add(DisplayAuthor, BorderLayout.CENTER);

        DisplayDate = new JTextArea(this.ReleaseDate.toString());
        DisplayDate.setEditable(false);
        this.add(DisplayDate, BorderLayout.CENTER);

        DisplayTimelineDate = new JTextArea(this.TimelineDate.toString());
        DisplayTimelineDate.setEditable(false);
        this.add(DisplayTimelineDate, BorderLayout.CENTER);

        DisplayCategory = new JTextArea(UTILS.CategoryToName.get(this.Category));
        DisplayCategory.setEditable(false);
        this.add(DisplayCategory,BorderLayout.CENTER);

        DisplayOwned = new JCheckBox("Owned",Owned);
        DisplayOwned.addItemListener(this);
        this.add(DisplayOwned, BorderLayout.CENTER);

        DisplayRead = new JCheckBox("Read",Read);
        DisplayRead.addItemListener(this);
        this.add(DisplayRead, BorderLayout.CENTER);
    }

    public void itemStateChanged(ItemEvent e){
        Object source = e.getItemSelectable();

        if (source == DisplayOwned){
            Owned = !Owned;
        } else if (source == DisplayRead){
            Read = !Read;
        }
    }
}
