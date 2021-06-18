import javax.swing.*;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.InputMismatchException;


public class InputWindow extends JFrame implements ActionListener{

    // Array and variable declarations
    int values[];
    int weights[];
    int items = 0;
    int i;
    int j;
    int k;
    int maxWeight = 0;
    String tempWeights;
    String tempValues;
    String splitWeights [];
    String splitValues [];
    String displayResults [][];

    //Instantiate GUI objects
    JFrame frame = new JFrame("Knapsack Problem");
    JPanel panel = new JPanel();
    JLabel labelItems = new JLabel("Number of Items:");
    JLabel labelMaxWeight = new JLabel("Maximum Weight:");
    JLabel labelWeight = new JLabel("Weights:");
    JLabel labelValues = new JLabel("Values:");
    JTextField itemNum = new JTextField();
    JTextField itemMaxWeight = new JTextField();
    JTextField itemWeights = new JTextField();
    JTextField itemValues = new JTextField();
    JButton button = new JButton("Apply");
    JButton solve = new JButton("Solve");
    GridBagConstraints gbc = new GridBagConstraints();
    ImageIcon napSack;

    // Constructor
    InputWindow() {
        // frame design and measurement
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setBounds(400,100,800,600);


        // labels size and measurement
        labelItems.setBounds(40,40,140,30);
        labelMaxWeight.setBounds(40,100,140,30);
        labelWeight.setBounds(250,40,50,30);
        labelValues.setBounds(250,100,50,30);
        frame.add(labelItems);
        frame.add(labelMaxWeight);
        frame.add(labelWeight);
        frame.add(labelValues);

        // text fields size and measurement
        itemNum.setBounds(160,40,50,30);
        itemMaxWeight.setBounds(160,100,50,30);
        itemWeights.setBounds(300,40,150,30);
        itemValues.setBounds(300,100,150,30);
        frame.add(itemNum);
        frame.add(itemMaxWeight);
        frame.add(itemWeights);
        frame.add(itemValues);

        // button sizes and measurement
        button.setBounds(130,140,100,30);
        button.addActionListener(this);
        frame.add(button);

        // Image setup

        panel.setLayout(new GridBagLayout());
        gbc.fill =GridBagConstraints.HORIZONTAL;
        frame.add(panel);
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == button)
        {
            try {
                // Get user's input for size and maximum weight
                items = Integer.parseInt(itemNum.getText());
                maxWeight = Integer.parseInt(itemMaxWeight.getText());
                tempWeights = itemWeights.getText();
                tempValues = itemValues.getText();

                // Array size
                values = new int[items];
                weights = new int[items];
                displayResults = new String[15][3];

                // Split values inside the text field
                splitWeights = tempWeights.split(" ");
                splitValues = tempValues.split(" ");

                for(k = 0;k < items;k++)
                {
                    if(Integer.parseInt(splitWeights[k]) <= 0 || Integer.parseInt(splitValues[k]) <= 0)
                    {
                        ErrorWindow w = new ErrorWindow(-1);
                    }
                }

                // Store values inside the weights array
                for (i = 0; i < items; i++) {
                    weights[i] = Integer.parseInt(splitWeights[i]);
                }

                // Store values inside the values array
                for (j = 0; j < items; j++) {
                    values[j] = Integer.parseInt(splitValues[j]);
                }
            }catch (ArrayIndexOutOfBoundsException ae)
            {
                    ErrorWindow indexError = new ErrorWindow(2);
            }catch (NumberFormatException in)
            {
                    ErrorWindow inputError = new ErrorWindow(1);
            }
            knapSack(items,weights,values,maxWeight,items);
            DisplayTable display = new DisplayTable(displayResults);

            display.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            display.setSize(600,500);
            display.setVisible(true);

        }

    }

    // Method to display user's values
    public void knapSack(int n, int[] wt, int[] val, int m, int length) {
        int optimalValue = 0;
        String optimalValueString;

        for (int counter = 0; counter < (1<<n); ++counter) {
            int totalWeight = 0;
            int totalValue = 0;
            String totalWeightString;
            String totalValueString;
            String subsets = " ";

            for (int counter2 = 0; counter2 < n; ++counter2) {
                if((counter & (1<<counter2)) > 0) {
                    totalWeight += wt[counter2];
                    totalValue += val[counter2];
                    subsets += String.valueOf(counter2 + 1 + " ");
                    displayResults[counter-1][0] = subsets;
                }
            }

            if (counter != 0) {
                totalWeightString = String.valueOf(totalWeight);
                totalValueString = String.valueOf(totalValue);
                if(totalWeight > m)
                    totalValueString = "not feasible";
                displayResults[counter-1][1] = totalWeightString;
                displayResults[counter-1][2] = totalValueString;
            }

            if(totalWeight <= m && optimalValue < totalValue) {
                optimalValue = totalValue;
                optimalValueString = String.valueOf(totalValue);
            }
        }

    }
}



