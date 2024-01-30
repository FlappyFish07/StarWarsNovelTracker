import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import static java.lang.Math.max;

public class Menu extends JPanel implements ItemListener, KeyListener {
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

    UIManager Manager;
    public Menu(UIManager Manager){
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
    }
    public List<Media> FilterAndSort(Media[] Data){
        List<Media> DisplayData = new ArrayList<>(Arrays.asList(Data));

        DisplayData.removeIf(s ->
                        (!NameString.equals("") && !s.Name.toUpperCase().contains(NameString.toUpperCase())) ||
                                (!AuthorString.equals("") && !s.Author.toUpperCase().contains(AuthorString.toUpperCase())) ||
            //Release Date TODO
            //Timeline Date
            (!CategoryString.equals("") && !s.Category.equals(UTILS.NameToCategory(CategoryString))) ||
                                (OwnedBool && !s.Owned) ||
            (ReadBool && !s.Read));

        return DisplayData;
    }
    public void itemStateChanged(ItemEvent e){
        Object source = e.getItemSelectable();

        if (source == CategoryChoice){
            CategoryString = UTILS.NameToCategory((String) ((JComboBox) source).getSelectedItem());
        } else if (source == OwnedChoice){
            OwnedBool = !OwnedBool;
        }else if (source == ReadChoice){
            ReadBool = !ReadBool;
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
}
