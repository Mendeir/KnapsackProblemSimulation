import javax.swing.*;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InputTable extends JFrame{

    //Instantiate table object
    JTable table;

    // Constructor
    public InputTable(int [] dataWeights, int [] dataValues)
    {
        setLayout(new FlowLayout());

        // Titles for columns
        String [] names = {"Subset" , "Total Weight", "Total Value"};
        String [][]dataSet;

        //table = new JTable(dataSet,names);
        table.setPreferredScrollableViewportSize(new Dimension(500,50));
        table.setFillsViewportHeight(true);

        JScrollPane pane = new JScrollPane(table);
        add(pane);
    }

}
