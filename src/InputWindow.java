import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;
import java.util.Random;


public class InputWindow extends JFrame implements ActionListener{

    // Array and variable declarations
    int values[];
    int weights[];
    int items = 0;
    int i;
    int j;
    int k;
    int maxWeight = 0;
    boolean temp = false;
    boolean app = false;
    String tempWeights;
    String tempValues;
    String splitWeights [];
    String splitValues [];
    String displayResults [][];
    String [] names = {"Subsets" , "Total Weight", "Total Value"};

    // Instantiate GUI objects
    JFrame frame = new JFrame("Knapsack Problem");
    JPanel panel = new JPanel();
    JPanel panelTwo = new JPanel();
    JLabel labelItems = new JLabel("Number of Items:");
    JLabel labelMaxWeight = new JLabel("Maximum Weight:");
    JLabel labelWeight = new JLabel("Weights:");
    JLabel labelValues = new JLabel("Values:");
    JTextField itemNum = new JTextField();
    JTextField itemMaxWeight = new JTextField();
    JTextField itemWeights = new JTextField();
    JTextField itemValues = new JTextField();
    JButton solve = new JButton("Solve");
    JButton randomize = new JButton("Random");
    JButton apply = new JButton("Apply");
    GridBagConstraints gbc = new GridBagConstraints();
    ImageIcon napSack = new ImageIcon(Objects.requireNonNull(getClass().getResource("bag.png")));
    JLabel labelImage = new JLabel(napSack);
    JTable table;

    // Constructor
    InputWindow() {

        // frame design and measurement
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setBounds(100,100,1000,600);

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
        solve.setBounds(300,170,100,30);
        solve.addActionListener(this);
        randomize.setBounds(250,10,100,20);
        randomize.addActionListener(this);
        apply.setBounds(40,140,90,20);
        apply.addActionListener(this);
        frame.add(solve);
        frame.add(randomize);
        frame.add(apply);

        // Image setup
        labelImage.setBounds(20,220,300,300);

        // Panel setups and sizes
        panel.setBounds(20,220,300,300);
        panel.add(labelImage);
        frame.add(panel);
        frame.add(panelTwo);

        // Frame layout and visible
        frame.setLayout(null);
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        // Action for apply button
        if (e.getSource() == apply) {
            app = true;
            try {
                // Get user's input for size and maximum weight
                items = Integer.parseInt(itemNum.getText());
                maxWeight = Integer.parseInt(itemMaxWeight.getText());
                values = new int[items];
                weights = new int[items];

                if(items <= 0 || maxWeight <= 0){
                    ErrorWindow error = new ErrorWindow(-1);
                }
            }catch (NumberFormatException inn){
                ErrorWindow error = new ErrorWindow(1);
            }
        }

        // Action for randomize button
        if (e.getSource() == randomize) {
            temp = true;
            try {
                randomizeArrays(weights, values, maxWeight);
            }catch(NullPointerException n){
                ErrorWindow error = new ErrorWindow(0);
            }
        }

        // Action for solve button
        if (e.getSource() == solve) {
            if (app == false) {
                ErrorWindow error = new ErrorWindow(0);
            }else{

            try {

                // Get users weights and values for conversion
                tempWeights = itemWeights.getText();
                tempValues = itemValues.getText();

                // Array size
                displayResults = new String[15][3];

                // Split values inside the text field
                splitWeights = tempWeights.split(" ");
                splitValues = tempValues.split(" ");

                // Checking of negative and zero value for each values input by the user
                for (k = 0; k < items; k++) {
                    if (Integer.parseInt(splitWeights[k]) <= 0 || Integer.parseInt(splitValues[k]) <= 0) {
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


            } catch (ArrayIndexOutOfBoundsException ae) {
                ErrorWindow indexError = new ErrorWindow(2);
            } catch (NumberFormatException in) {
                if (temp == false) {
                    ErrorWindow inputError = new ErrorWindow(1);
                }
            }

            // Calling the method for knapsack algorithm
            knapSack(items, weights, values, maxWeight, items);

            // Calling the method for the display result
            displayTable(displayResults, names);
            }
        }

    }

    // Method for randomize values
    public static void randomizeArrays(int[] weightArray, int[] valuesArray, int maxCapacity) {

        Random randomNumber = new Random();
        for (int arrayCounter = 0; arrayCounter < weightArray.length; ++arrayCounter) {
            weightArray[arrayCounter] = randomNumber.nextInt((int) (maxCapacity + (maxCapacity * .10)));
            valuesArray[arrayCounter] = randomNumber.nextInt(maxCapacity * 2);
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

    // Method for calling the result table
    public void displayTable(String [][] datasets, String []n){

        // Instantiation of the table for JTable object
        table = new JTable(datasets,n);
        table.setPreferredScrollableViewportSize(new Dimension(400,400));
        panelTwo.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Results", TitledBorder.CENTER, TitledBorder.RIGHT ));
        panelTwo.setBounds(450,10,500,500);
        panelTwo.add(new JScrollPane(table));

    }


}
