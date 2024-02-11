import com.formdev.flatlaf.FlatDarculaLaf;
import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.FlatLightLaf;

import javax.swing.*;
import javax.swing.plaf.basic.BasicArrowButton;
import java.awt.*;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;


public class Media extends JPanel implements ItemListener, ActionListener {
    public int index;
    public String Name;
    public String Author;
    public Calendar ReleaseDate;
    public LegendsDate TimelineDate;
    public String Category;
    public boolean Owned;
    public boolean Read;
    public Media[] Children;
    public boolean ShowChildren = true;

    public Media(int index, String Name, String Author, Calendar ReleaseDate, LegendsDate TimelineDate, String Category, boolean Owned, boolean Read, Media[] Children){
        this.index = index;
        this.Name = Name;
        this.Author = Author;
        this.ReleaseDate = ReleaseDate;
        this.TimelineDate = TimelineDate;
        this.Category = Category.strip().toUpperCase().replaceAll("[^A-Z]","");
        this.Owned = Owned;
        this.Read = Read;
        this.Children = Children;

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
    public JButton DisplayChildren;
    public JSeparator DisplayChild;

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

        DisplayCategory = new JTextArea(UTILS.CategoryToName.get(this.Category));
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

    public GraphicsManager GM;

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
