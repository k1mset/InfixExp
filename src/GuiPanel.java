/**
 * File: GuiFrame.java
 * Date: 8/31/2018
 * Author: Dillan Cobb
 * Purpose: Create the GUI panels for the application, as well as handle what 
 * occurs when the button is pressed.
 */

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class GuiPanel extends JPanel {
    // Creates the GUI items to be used for the GUI
    private JLabel enterLbl = new JLabel("Enter Infix Expression: ");
    private JLabel resultLbl = new JLabel("Result:");
    
    private JTextField expressionTxt = new JTextField();
    private JTextField resultTxt = new JTextField();
    
    private JButton submitBtn = new JButton("Evaluate");
    
    public GuiPanel() {
        // Creates the Panels used to organize the GUI
        JPanel mainPanel = new JPanel();
        JPanel entryPanel = new JPanel();
        JPanel submitPanel = new JPanel();
        JPanel resultPanel = new JPanel();
        
        // Sets up the entryPanel for the GUI
        entryPanel.setLayout(new GridLayout(1,2));
        entryPanel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        
        entryPanel.add(enterLbl);
        entryPanel.add(expressionTxt);
        
        // Sets up the submitPanel for the GUI
        submitPanel.setLayout(new FlowLayout());
        
        submitPanel.add(submitBtn);
        
        // Sets up the resultPanel for the GUI
        resultPanel.setLayout(new GridLayout(1,2));
        resultPanel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        
        resultPanel.add(resultLbl);
        resultPanel.add(resultTxt);
        resultTxt.setEnabled(false);
        
        // Sets up the mainPanel for the GUI. Holds the other panels
        mainPanel.setLayout(new GridLayout(3,1));
        
        mainPanel.add(entryPanel);
        mainPanel.add(submitPanel);
        mainPanel.add(resultPanel);
        
        add(mainPanel);
        
        // ActionListener used to determine what to do when the Evaluate button
        // pressed.
        submitBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String expressionStr = expressionTxt.getText();
                
                // Validate is the string is correct by using stack
                // use InfixEval to calculate
                
                InfixEval stringText = new InfixEval();
                
                int finalResult = stringText.checkString(expressionStr);
                
                resultTxt.setText(Integer.toString(finalResult));
            }
        });
    }
}
