import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class UIManager {
    public JPanel rootPanel;
    public JScrollPane scrollPane;
    public Menu Menu;
    public JFrame frame;
    public Media[] Data;
    public UIManager(Media[] Data){
        frame = new JFrame("Star Wars Novel Tracker");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setMinimumSize(new Dimension(100,100));
        frame.setPreferredSize(new Dimension(1000,500));
        frame.setMaximumSize(new Dimension(3000,2000));
        frame.getContentPane().setLayout(new BorderLayout());

        rootPanel = new JPanel();
        rootPanel.setLayout(new BoxLayout(rootPanel, BoxLayout.Y_AXIS));
        frame.add(rootPanel, BorderLayout.CENTER);

        this.Data = Data;

        Menu = new Menu(this);
        frame.add(Menu, BorderLayout.PAGE_START);

        List<Media> DisplayData = Menu.FilterAndSort(Data);

        for(Media Item:DisplayData) {
            Item.setVisible(true);
            rootPanel.add(Item, Component.CENTER_ALIGNMENT);
        }

        scrollPane = new JScrollPane(rootPanel,ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setVisible(true);
        frame.add(scrollPane);

        frame.pack();
        frame.setVisible(true);
    }

    public void ListSetUp(Media Data[], JFrame frame){
        List<Media> DisplayData = Menu.FilterAndSort(Data);
        rootPanel.removeAll();

        for(Media Item:DisplayData) {
            Item.setVisible(true);
            this.rootPanel.add(Item, Component.CENTER_ALIGNMENT);
        }

        scrollPane.updateUI();
    }

}
