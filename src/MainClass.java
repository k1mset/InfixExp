/**
 * File: MainClass.java
 * Date: 8/31/2018
 * Author: Dillan Cobb
 * Purpose: Handles the frames and executes the program for the user. 
 */

// Imports
import javax.swing.*;

public class MainClass extends JFrame {
    
    // Static window frame elements
    static final int WIDTH = 400, HEIGHT = 200;
    static final String FRAMETITLE = "CMSC 350 - Project 1";
    
    public MainClass() {
        super(FRAMETITLE);
        setFrame(WIDTH,HEIGHT);
        add(new GuiPanel());
    }
    
    // main method that runs the application
    static public void main(String[] args) {
        MainClass application = new MainClass();
        application.display();
    }
    
    // displays the frame for the user
    public void display() {
        setVisible(true);
    }
    
    // setFrame sets the frame size
    public void setFrame(int width, int height) {
        setSize(width, height);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
}
