package apps;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;

public class Calculator {

    private static final int X_LOC = 100;
    private static final int Y_LOC = 100;
    private static final int WIDTH = 400;
    private static final int HEIGHT = 400;
    private static final String NAME = "calculator";
    private static final String RESULT_PREAMBLE = "result = ";
    private static final String ERROR_MESSAGE = "error";
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
        frame.setLayout(new BorderLayout());
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
        /*frame.add(leftOpField);
        frame.add(rightOpField);
        frame.add(resultLabel);*/
        //frame.setLayout(new BorderLayout());
        frame.setVisible(true);
    }

    private void initializeInputs() {
        leftOpField = new JTextField(5);
        rightOpField = new JTextField(5);
        leftOpField.setName("leftOperand");
        rightOpField.setName("rightOperand");
        JPanel inputPanel = new JPanel(new BorderLayout());
        inputPanel.setName("inputPanel");
        inputPanel.add(leftOpField, BorderLayout.EAST);
        inputPanel.add(rightOpField, BorderLayout.WEST);
        frame.add(inputPanel, BorderLayout.NORTH);
    }

    private void initializeResults() {
        resultLabel = new JLabel(RESULT_PREAMBLE);
        resultLabel.setName("resultLabel");
        JPanel resultPanel = new JPanel(new BorderLayout());
        resultPanel.setName("resultPanel");
        resultPanel.add(resultLabel, BorderLayout.CENTER);
        frame.add(resultPanel, BorderLayout.AFTER_LINE_ENDS);
    }

    private void initializeButtons() {
        JButton addButton = new JButton("ADD");
            addButton.setName("addButton");
        JButton subButton = new JButton("SUB");
            subButton.setName("subButton");
        JButton multButton = new JButton("MULT");
        multButton.setName("multButton");
        JButton divButton = new JButton("DIV");
        divButton.setName("divButton");
        
        addButton.addActionListener(e -> addButtonPressed(e));
        subButton.addActionListener(e -> subButtonPressed(e));
        multButton.addActionListener(e -> multButtonPressed(e));
        divButton.addActionListener(e -> divButtonPressed(e));

        JPanel buttonPanel = new JPanel(new BorderLayout());
        buttonPanel.setName("buttonPanel");
        buttonPanel.add(addButton, BorderLayout.EAST);
        buttonPanel.add(subButton, BorderLayout.NORTH);
        buttonPanel.add(multButton, BorderLayout.SOUTH);
        buttonPanel.add(divButton, BorderLayout.WEST);
        frame.add(buttonPanel, BorderLayout.SOUTH);
    }

    public void addButtonPressed(ActionEvent e) {
        updateResult(getLeftNum() + getRightNum());
        if(resultLabel.getText().equals("result = NaN")){resultLabel.setText(ERROR_MESSAGE);}
    }
    public void subButtonPressed(ActionEvent e) {
        updateResult(getLeftNum() - getRightNum());
        if(resultLabel.getText().equals("result = NaN")){resultLabel.setText(ERROR_MESSAGE);}
    }
    public void multButtonPressed(ActionEvent e) {
        updateResult(getLeftNum() * getRightNum());
        if(resultLabel.getText().equals("result = NaN")){resultLabel.setText(ERROR_MESSAGE);}
    }
    public void divButtonPressed(ActionEvent e) { 
        if (getRightNum() == 0) {updateResult(Double.NaN); resultLabel.setText(RESULT_PREAMBLE + ERROR_MESSAGE);}
        else {updateResult(getLeftNum() / getRightNum());}
        if(resultLabel.getText().equals("result = NaN")){resultLabel.setText(ERROR_MESSAGE);}
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
        resultLabel.setText(RESULT_PREAMBLE + String.valueOf(result));
    }
}
