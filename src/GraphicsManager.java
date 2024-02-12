import javax.swing.*;
import java.awt.*;
import java.util.List;
import com.formdev.flatlaf.*;

public class GraphicsManager {
    public JPanel rootPanel;
    public JScrollPane scrollPane;
    public Menu Menu;
    public JFrame frame;
    public Media[] Data;
    public List<Media> DisplayData;
    public GraphicsManager(Media[] Data){
        FlatDarkLaf.setup();

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

        this.DisplayData = Menu.FilterAndSort(Data);

        for(Media Item : this.DisplayData) {
            Item.setVisible(true);
            Item.DisplayDetails();
            Item.setGM(this);
            rootPanel.add(Item, Component.LEFT_ALIGNMENT);
        }

        scrollPane = new JScrollPane(rootPanel,ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setVisible(true);
        frame.add(scrollPane);

        frame.pack();
        frame.setVisible(true);
    }

    public void ListSetUp(Media Data[], JFrame frame) {
        this.DisplayData = Menu.FilterAndSort(Data);
        rootPanel.removeAll();

        for(Media Item : this.DisplayData) {
            Item.setVisible(true);
            Item.DisplayDetails();
            this.rootPanel.add(Item, Component.CENTER_ALIGNMENT);
        }

        scrollPane.updateUI();
    }

    public void AddItem(int index) {
        for (Media Item : this.DisplayData){
            if(Item.index == index){
                Item.DisplayDetails();
                scrollPane.updateUI();
                break;
            }
        }
    }
}
