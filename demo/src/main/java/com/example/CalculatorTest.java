package com.example;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.*;
import javax.swing.*;

import org.apache.commons.io.IOUtils;

/**
 * Hello world!
 *
 */
public class CalculatorTest extends JFrame implements ActionListener
{
    private JLabel myLabel;
    private JTextField outputField;
    private ArrayList<JButton> digits;
    private JButton eqSign;
    private String currentOperation;
    private Double firstOperand;
    
    
    /**
     * @throws MalformedURLException
     */
    public CalculatorTest() throws MalformedURLException 
    {
        
       


        JPanel displayPanel = new JPanel();
  
        //displayPanel.setLayout(new FlowLayout());
        outputField = new JTextField("0", 20);
        displayPanel.add(outputField);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(1, 2));

        JPanel digitButtonPanel = new JPanel();
        digitButtonPanel.setLayout(new GridLayout(4, 3));
        digits = new ArrayList<JButton>();
        digits.add(new JButton("0"));
        digits.add(new JButton("1"));


        digitButtonPanel.add(digits.get(0));
        digitButtonPanel.add(digits.get(1));

        buttonPanel.add(digitButtonPanel);

        JPanel operatorButtonPanel = new JPanel();
        operatorButtonPanel.setLayout(new GridLayout(5,1));
 
        Icon icon = new ImageIcon("equal.png");
        //JButton button = new JButton(new ImageIcon(new URL("http://i68.tinypic.com/2itmno6.jpg")));
        //JButton eqSign = new JButton(new ImageIcon(new URL("http://i68.tinypic.com/2itmno6.jpg")));
        JButton eqSign = new JButton();
        URL url = new URL("https://www.pikpng.com/pngl/m/1-18084_gold-star-png-image-star-clipart-gold-stars.png");
        
        ImageIcon eicon = new ImageIcon(url);
        System.out.println(eicon.getImageLoadStatus());
        eqSign.setIcon(eicon);

  

        operatorButtonPanel.add(eqSign);
        buttonPanel.add(operatorButtonPanel);

        displayPanel.add(buttonPanel);
        add(displayPanel);


       // OperatorListener opListener = new OperatorListener();

        eqListener eListener = new eqListener();
        eqSign.addActionListener(eListener);
        
        digits.get(0).addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent event)
            {
                String currentText = outputField.getText();
                    JButton source = (JButton)event.getSource();
                    String newDigit = "";
                    if (source == digits.get(0))
                    {
                        newDigit = "0";
                    }
                    currentText = currentText + newDigit;
                    currentText = currentText.replaceFirst("^0+(?!$)", "");
                    outputField.setText(currentText);
            }
        });
        digits.get(1).addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent event)
            {
                String currentText = outputField.getText();
                    JButton source = (JButton)event.getSource();
                    String newDigit = "";
                    if (source == digits.get(1))
                    {
                        newDigit = "1";
                    }
                    currentText = currentText + newDigit;
                    currentText = currentText.replaceFirst("^0+(?!$)", "");
                    outputField.setText(currentText);
            }
        });


        setTitle("Calculator");
        setSize(500, 200);
        setVisible(true);
    }

    private class eqListener implements ActionListener
    {
        @Override
            public void actionPerformed(ActionEvent event)
            {
                Double result = 0.0;
                String currentText = outputField.getText();
                
            }
    }
    

    public static void main( String[] args )
    {
        JFrame myApp;
        try {
            myApp = new CalculatorTest();
            myApp.setSize(new Dimension(800,800));
        myApp.setVisible(true);
        myApp.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        myApp.setLayout(new FlowLayout());
        myApp.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        } catch (MalformedURLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        //myApp.show();
        
        System.out.println( "Hello World!" );
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        
    }
}
