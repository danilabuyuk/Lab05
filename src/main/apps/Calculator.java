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
        frame.setLayout(new BorderLayout(10, 10));
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
        frame.pack();
        frame.setVisible(true);
    }

    private void initializeInputs() {
        leftOpField = new JTextField(10);
        rightOpField = new JTextField(10);
        leftOpField.setName("leftOperand");
        rightOpField.setName("rightOperand");
        JPanel inputPanel = new JPanel();
        inputPanel.setName("inputPanel");
        inputPanel.add(leftOpField);
        inputPanel.add(rightOpField);
        frame.add(inputPanel, BorderLayout.NORTH);
    }

    private void initializeResults() {
        resultLabel = new JLabel(RESULT_PREAMBLE);
        resultLabel.setName("resultLabel");
        JPanel resultPanel = new JPanel();
        resultPanel.setName("resultPanel");
        resultPanel.add(resultLabel);
        frame.add(resultPanel, BorderLayout.CENTER);
    }

    private void initializeButtons() {
        JButton addButton = new JButton("ADD");
            addButton.setName("addButton");
            addButton.setSize(100, 50);
        JButton subButton = new JButton("SUB");
            subButton.setName("subButton");
            subButton.setSize(100, 50);
        JButton multButton = new JButton("MULT");
            multButton.setName("multButton");
            multButton.setSize(100, 50);
        JButton divButton = new JButton("DIV");
            divButton.setName("divButton");
            divButton.setSize(100, 50);
        
        addButton.addActionListener(e -> addButtonPressed(e));
        subButton.addActionListener(e -> subButtonPressed(e));
        multButton.addActionListener(e -> multButtonPressed(e));
        divButton.addActionListener(e -> divButtonPressed(e));

        JPanel buttonPanel = new JPanel();
            buttonPanel.setSize(400, 50);
        //JPanel buttonPanel2 = new JPanel(new BorderLayout(10, 10));
        //JPanel buttonPanel3 = new JPanel(new BorderLayout(10, 10));
        buttonPanel.setName("buttonPanel");
        buttonPanel.add(addButton/* , BorderLayout.EAST */);
        buttonPanel.add(subButton/* , BorderLayout.CENTER */);
        buttonPanel.add(multButton/* , BorderLayout.NORTH */);
        buttonPanel.add(divButton/* , BorderLayout.WEST */);
        //buttonPanel3.add(buttonPanel, BorderLayout.EAST);
        //buttonPanel3.add(buttonPanel2, BorderLayout.WEST);
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
