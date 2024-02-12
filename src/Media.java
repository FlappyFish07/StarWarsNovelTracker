import com.formdev.flatlaf.FlatDarculaLaf;
import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.FlatLightLaf;
import com.google.gson.annotations.Expose;

import javax.swing.*;
import javax.swing.plaf.basic.BasicArrowButton;
import java.awt.*;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;


public class Media extends JPanel implements ItemListener, ActionListener {
    @Expose
    public int index;
    @Expose
    public String Name;
    @Expose
    public String Author;
    @Expose
    public Calendar ReleaseDate;
    @Expose
    public LegendsDate TimelineDate;
    @Expose
    public String Category;
    @Expose
    public boolean Owned;
    @Expose
    public boolean Read;
    @Expose
    public Media[] Children;
    public boolean ShowChildren = false;

    public Media(int index, String Name, String Author, String ReleaseDate, String TimelineDate, String Category, boolean Owned, boolean Read, Media[] Children){
        this.index = index;
        this.Name = Name;
        this.Author = Author;
        this.ReleaseDate = new GregorianCalendar(Integer.parseInt(ReleaseDate.split("/")[2]),Integer.parseInt(ReleaseDate.split("/")[0])-1,Integer.parseInt(ReleaseDate.split("/")[1]));
        this.TimelineDate = new LegendsDate(TimelineDate);
        this.Category = Category.strip().toUpperCase().replaceAll("[^A-Z]","");
        this.Owned = Owned;
        this.Read = Read;
        this.Children = Children;
    }
    public Media(){}

    public String toString() {
        return Name + ", " + Author + ", " + ReleaseDate + ", " + TimelineDate + ", " + Category + ", " + (Owned?"Owned":"Not owned") + ", " + (Owned?"Read":"Unread");
    }

    private JTextArea DisplayName;
    private JTextArea DisplayAuthor;
    private JTextArea DisplayDate;
    private JTextArea DisplayTimelineDate;
    private JTextArea DisplayCategory;
    private JCheckBox DisplayOwned;
    private JCheckBox DisplayRead;
    private JButton DisplayChildren;
    private JSeparator DisplayChild;

    public void DisplayDetails() {
        this.removeAll();

        FlatDarkLaf.setup();

        this.setLayout(new GridLayout((ShowChildren?Children.length:0) + 1, 1));

        JPanel rootPanel = new JPanel();

        DisplayChild = new JSeparator(SwingConstants.VERTICAL);
        rootPanel.add(DisplayChild, BorderLayout.CENTER);

        DisplayName = new JTextArea(this.Name);
        DisplayName.setEditable(false);
        rootPanel.add(DisplayName, BorderLayout.CENTER);

        DisplayAuthor = new JTextArea(this.Author);
        DisplayAuthor.setEditable(false);
        rootPanel.add(DisplayAuthor, BorderLayout.CENTER);

        DisplayDate = new JTextArea(new SimpleDateFormat("d MMMM y").format(this.ReleaseDate.getTime()));
        DisplayDate.setEditable(false);
        rootPanel.add(DisplayDate, BorderLayout.CENTER);

        DisplayTimelineDate = new JTextArea(this.TimelineDate.toString());
        DisplayTimelineDate.setEditable(false);
        rootPanel.add(DisplayTimelineDate, BorderLayout.CENTER);

        DisplayCategory = new JTextArea(this.Category);
        DisplayCategory.setEditable(false);
        rootPanel.add(DisplayCategory, BorderLayout.CENTER);

        DisplayOwned = new JCheckBox("Owned", Owned);
        DisplayOwned.addItemListener(this);
        rootPanel.add(DisplayOwned, BorderLayout.CENTER);

        DisplayRead = new JCheckBox("Read", Read);
        DisplayRead.addItemListener(this);
        rootPanel.add(DisplayRead, BorderLayout.CENTER);

        if (Children.length != 0) {
            DisplayChildren = new BasicArrowButton(ShowChildren?BasicArrowButton.NORTH:BasicArrowButton.SOUTH);
            DisplayChildren.addActionListener(this);
            rootPanel.add(DisplayChildren, BorderLayout.CENTER);
        }

        this.add(rootPanel);

        if (ShowChildren) {
            for (Media Child : Children) {
                Child.setVisible(true);
                this.add(Child, BorderLayout.CENTER);
            }
        }
    }

    private transient GraphicsManager GM;

    public void setGM(GraphicsManager GM){
        this.GM = GM;
    }

    public void itemStateChanged(ItemEvent e){
        Object source = e.getItemSelectable();

        if (source == DisplayOwned){
            Owned = !Owned;
        } else if (source == DisplayRead) {
            Read = !Read;
        }
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();

        if (source == DisplayChildren){
            ShowChildren = !ShowChildren;
            GM.AddItem(index);
        }
    }
}
