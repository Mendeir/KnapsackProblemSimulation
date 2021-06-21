import javax.swing.*;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DisplayTable extends JFrame{

    //Instantiate table object
    JTable table;

    // Constructor
    public DisplayTable(String [][] dataset)
    {
        setLayout(new FlowLayout());

        // Titles for columns
        String [] names = {"Subset" , "Total Weight", "Total Value"};


        table = new JTable(dataset,names);
        table.setPreferredScrollableViewportSize(new Dimension(500,300));
        table.setFillsViewportHeight(true);

        JScrollPane pane = new JScrollPane(table);
        add(pane);

    }

}
