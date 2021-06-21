
import javax.swing.*;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ErrorWindow extends JFrame{

    // Instantiate Objects for GUI
    JFrame errorInput = new JFrame();
    JLabel labelInput = new JLabel("Error, Input a positive number, Please Try again");
    JFrame errorIndex = new JFrame();
    JLabel labelIndex = new JLabel("Error, Please enter exact values of your size");
    JFrame zeroNegative = new JFrame();
    JLabel zeroLabel = new JLabel("Error, Please Input positive numbers");
    JFrame sizeNull = new JFrame();
    JLabel labelNull = new JLabel("Error, Please input a size and a max weight values");

    // Constructor
    ErrorWindow(int flag)
    {
        if(flag == 1) {
            InputError();
        }else if(flag == 2) {
            IndexError();
        }else if(flag == -1) {
            ZeroNegative();
        }else if(flag == 0){
            nullSize();
        }
    }

    // Window for NumberFormat exception
    public void InputError()
    {
        errorInput.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        errorInput.setBounds(500,300, 400,100);
        labelInput.setSize(50,40);
        labelInput.setHorizontalAlignment((int)JFrame.CENTER_ALIGNMENT);
        errorInput.add(labelInput);
        errorInput.setVisible(true);
    }

    // Window for ArrayIndexOutOfBound exception
    public void IndexError()
    {
        errorIndex.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        errorIndex.setBounds(500,300,400,100);
        labelIndex.setSize(50,40);
        labelIndex.setHorizontalAlignment((int)JFrame.CENTER_ALIGNMENT);
        errorIndex.add(labelIndex);
        errorIndex.setVisible(true);
    }

    // Window for zero and negative inputs
    public void ZeroNegative()
    {
        zeroNegative.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        zeroNegative.setBounds(500,300,400,100);
        zeroLabel.setSize(50,40);
        zeroLabel.setHorizontalAlignment((int)JFrame.CENTER_ALIGNMENT);
        zeroNegative.add(zeroLabel);
        zeroNegative.setVisible(true);

    }
    public void nullSize(){
        sizeNull.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        sizeNull.setBounds(500,300,400,100);
        labelNull.setSize(50,40);
        labelNull.setHorizontalAlignment((int)JFrame.CENTER_ALIGNMENT);
        sizeNull.add(labelNull);
        sizeNull.setVisible(true);
    }
}
