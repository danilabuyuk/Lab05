package apps;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;

public class Calculator {

    private static final int X_LOC = 100;
    private static final int Y_LOC = 100;
    private static final int WIDTH = 400;
    private static final int HEIGHT = 400;
    private static final String NAME = "calculator";
    private static final String RESULT_PREAMBLE = "Result = ";
    private static final String ERROR_MESSAGE = "Error";
    private JFrame frame;
    private JTextField leftOpField;
    private JTextField rightOpField;
    private JLabel resultLabel;
    
    public Calculator() {
        createFrame();
        initializeComponents();
        displayFrame();
    }

    public JFrame getFrame() {
        return frame;
    }

    private void createFrame() {
        frame = new JFrame(NAME);
        frame.setSize(WIDTH, HEIGHT);
        frame.setLocation(X_LOC, Y_LOC);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void initializeComponents() {
        initializeInputs();
        initializeResults();
        initializeButtons();
    }

    private void displayFrame() {
        frame.add(leftOpField);
        frame.add(rightOpField);
        frame.add(resultLabel);
        frame.setLayout(new GridLayout());
        frame.setVisible(true);
    }

    private void initializeInputs() {
        leftOpField = new JTextField(5);
        rightOpField = new JTextField(5);
        leftOpField.setName("leftOperand");
        rightOpField.setName("rightOperand");
        JPanel inputPanel = new JPanel();
        inputPanel.add(leftOpField);
        inputPanel.add(rightOpField);
        frame.add(inputPanel);
    }

    private void initializeResults() {
        resultLabel = new JLabel();
        JPanel resultPanel = new JPanel();
        resultPanel.add(resultLabel);
        frame.add(resultPanel);
    }

    private void initializeButtons() {
        JButton addButton = new JButton("ADD");
        JButton subButton = new JButton("SUB");
        JButton multButton = new JButton("MULT");
        JButton divButton = new JButton("DIV");
        
        addButton.addActionListener(e -> addButtonPressed(e));
        subButton.addActionListener(e -> subButtonPressed(e));
        multButton.addActionListener(e -> multButtonPressed(e));
        divButton.addActionListener(e -> divButtonPressed(e));

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(addButton);
        buttonPanel.add(subButton);
        buttonPanel.add(multButton);
        buttonPanel.add(divButton);
        frame.add(buttonPanel);
    }

    public void addButtonPressed(ActionEvent e) {updateResult(getLeftNum() + getRightNum());}
    public void subButtonPressed(ActionEvent e) {updateResult(getLeftNum() - getRightNum());}
    public void multButtonPressed(ActionEvent e) {updateResult(getLeftNum() * getRightNum());}
    public void divButtonPressed(ActionEvent e) { 
        if (getRightNum() == 0) {updateResult(Double.NaN); resultLabel.setText(ERROR_MESSAGE);}
        else {updateResult(getLeftNum() / getRightNum());}
    }

    private double getLeftNum() {
        try {return Double.parseDouble(leftOpField.getText());}
        catch(NullPointerException e) {return Double.NaN;}
        catch(NumberFormatException e) {return Double.NaN;}
    }
    private double getRightNum() {
        try {return Double.parseDouble(rightOpField.getText());}
        catch(NullPointerException e) {return Double.NaN;}
        catch(NumberFormatException e) {return Double.NaN;}
    }

    private void updateResult(double result) {
        resultLabel.setText(String.valueOf(result));
    }
}
