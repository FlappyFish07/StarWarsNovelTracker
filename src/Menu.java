import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.List;

public class Menu extends JPanel implements ItemListener, KeyListener, ActionListener {
    JTextField NameChoice;
    String NameString = "";

    JTextField AuthorChoice;
    String AuthorString = "";

    JSlider TimelineChoice;
    int TimelineInt;

    JComboBox CategoryChoice;
    String CategoryString = "";

    JCheckBox OwnedChoice;
    boolean OwnedBool;

    JCheckBox ReadChoice;
    boolean ReadBool;

    JComboBox SortChoice;
    String SortType = "";

    JCheckBox SortDirection;
    boolean SortBool;

    JButton SaveButton;

    GraphicsManager Manager;
    public Menu(GraphicsManager Manager){
        this.Manager = Manager;


        //Book Name Entry
        JPanel NamePanel = new JPanel();
        NamePanel.setLayout(new BoxLayout(NamePanel, BoxLayout.Y_AXIS));

        JTextField NameLabel = new JTextField("Book Name");
        NameLabel.setEditable(false);
        NamePanel.add(NameLabel, Component.CENTER_ALIGNMENT);

        NameChoice = new JTextField("");
        NameChoice.addKeyListener(this);
        NamePanel.add(NameChoice, Component.CENTER_ALIGNMENT);

        this.add(NamePanel, BorderLayout.CENTER);


        // Author Name Entry
        JPanel AuthorPanel = new JPanel();
        AuthorPanel.setLayout(new BoxLayout(AuthorPanel, BoxLayout.Y_AXIS));

        JTextField AuthorLabel = new JTextField("Author Name");
        AuthorLabel.setEditable(false);
        AuthorPanel.add(AuthorLabel, Component.CENTER_ALIGNMENT);

        AuthorChoice = new JTextField("");
        AuthorChoice.addKeyListener(this);
        AuthorPanel.add(AuthorChoice, Component.CENTER_ALIGNMENT);

        this.add(AuthorPanel, BorderLayout.CENTER);

        // Release Date Selection TODO

        /* Timeline Date Selection TODO
        TimelineChoice = new JSlider(Arrays.stream(Manager.Data).min(Comparator.comparingInt(s -> s.TimelineDate.toInt())).get().TimelineDate.toInt(),Arrays.stream(Manager.Data).max(Comparator.comparingInt(s -> s.TimelineDate.toInt())).get().TimelineDate.toInt());
        TimelineChoice.setPaintTrack(true);
        TimelineChoice.setPaintTicks(true);
        TimelineChoice.setPaintLabels(true);
        TimelineChoice.setMajorTickSpacing((TimelineChoice.getMaximum()-TimelineChoice.getMinimum())/2);
        TimelineChoice.setMinorTickSpacing(TimelineChoice.getMajorTickSpacing()/10);
        this.add(TimelineChoice, Component.TOP_ALIGNMENT); */

        // Category Selection
        CategoryChoice = new JComboBox(UTILS.CategoryToName.values().toArray());
        CategoryChoice.addItemListener(this);
        this.add(CategoryChoice, BorderLayout.CENTER);

        // Filter Owned
        OwnedChoice = new JCheckBox("Owned");
        OwnedChoice.addItemListener(this);
        this.add(OwnedChoice, BorderLayout.CENTER);

        // Filter Read
        ReadChoice = new JCheckBox("Read");
        ReadChoice.addItemListener(this);
        this.add(ReadChoice, BorderLayout.CENTER);

        //Sort Selection
        String[] SortChoices = new String[] {"", "Novel Name", "Author Name", "Timeline Date", "Release Date", "Novel Category", "Owned", "Read"};
        SortChoice = new JComboBox(SortChoices);
        SortChoice.addItemListener(this);
        this.add(SortChoice, BorderLayout.CENTER);

        //Sort Inverse
        SortDirection = new JCheckBox("Inverse Sort");
        SortDirection.addItemListener(this);
        this.add(SortDirection, BorderLayout.CENTER);

        //Save button
        SaveButton = new JButton("Save");
        SaveButton.addActionListener(this);
        this.add(SaveButton,BorderLayout.SOUTH);
    }
    public List<Media> FilterAndSort(Media[] Data){
        List<Media> DisplayData = new ArrayList<>(Arrays.asList(Data));

        // Filters the data
        DisplayData.removeIf(s ->
                        (!NameString.equals("") && !s.Name.toUpperCase().contains(NameString.toUpperCase())) ||
                                (!AuthorString.equals("") && !s.Author.toUpperCase().contains(AuthorString.toUpperCase())) ||
            //Release Date TODO
            //Timeline Date TODO
            (!CategoryString.equals("") && !s.Category.equals(CategoryString)) ||
                                (OwnedBool && !s.Owned) ||
            (ReadBool && !s.Read));

        // Sorts the data
        switch(SortType){
            case("Novel Name") -> {DisplayData.sort((s,t) -> String.CASE_INSENSITIVE_ORDER.compare(s.Name,t.Name));}
            case("Author Name") -> {DisplayData.sort((s,t) -> String.CASE_INSENSITIVE_ORDER.compare(s.Author,t.Author));}
            case("Timeline Date") -> {DisplayData.sort(Comparator.comparingInt(s -> s.TimelineDate.toInt()));}
            case("Release Date") -> {DisplayData.sort(Comparator.comparing(s -> s.ReleaseDate));}
            case("Novel Category") -> {DisplayData.sort((s,t) -> String.CASE_INSENSITIVE_ORDER.compare(s.Category,t.Category));}
            case("Owned") -> {DisplayData.sort((s,t) -> Boolean.compare(s.Owned,t.Owned));}
            case("Read") -> {DisplayData.sort((s,t) -> Boolean.compare(s.Read,t.Read));}
            default -> {}
        }

        if (SortBool) {Collections.reverse(DisplayData);}

        return DisplayData;
    }
    public void itemStateChanged(ItemEvent e){
        Object source = e.getSource();

        if (source == CategoryChoice){
            CategoryString = ((String) ((JComboBox) source).getSelectedItem());
        } else if (source == OwnedChoice){
            OwnedBool = !OwnedBool;
        }else if (source == ReadChoice){
            ReadBool = !ReadBool;
        } else if (source == SortChoice){
            SortType = (String) ((JComboBox) source).getSelectedItem();
        } else if (source == SortDirection){
            SortBool = !SortBool;
        }

        Manager.ListSetUp(Manager.Data, Manager.frame);
    }

    @Override
    public void keyTyped(KeyEvent e) {
        JTextField textField = (JTextField) e.getSource();
        String text = textField.getText();
        textField.setText(text);

        if (textField == NameChoice){
            NameString = text;
        } else if (textField == AuthorChoice){
            AuthorString = text;
        }

        Manager.ListSetUp(Manager.Data, Manager.frame);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        JTextField textField = (JTextField) e.getSource();
        String text = textField.getText();
        textField.setText(text);


        Manager.ListSetUp(Manager.Data, Manager.frame);
        if (textField == NameChoice){
            NameString = text;
        } else if (textField == AuthorChoice){
            AuthorString = text;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        JTextField textField = (JTextField) e.getSource();
        String text = textField.getText();
        textField.setText(text);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();

        if (source == SaveButton){
            UTILS.SaveJSONData(Manager.Data);
        }
    }
}
