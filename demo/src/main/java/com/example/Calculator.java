package com.example;
import java.awt.*;
import java.awt.event.*;

import java.net.MalformedURLException;
import java.net.URL;

import java.util.*;
import javax.swing.*;


/**
 * @author Prem Agarwal
 * @since 11/28/2022
 * Calculator application
 */
public class Calculator extends JFrame implements ActionListener
{
 
    private JTextField outputField;
    private ArrayList<JButton> digits;
    private JButton plusSign;
    private JButton minusSign;
    private JButton divSign;
    private JButton multSign;

    private String currentOperation;
    private Double firstOperand;
    private JMenuItem item_a = new JMenuItem("ADD");
    private   JMenuItem item_mi = new JMenuItem("MINUS");
    private    JMenuItem item_m= new JMenuItem("MULTIPLY");
    private    JMenuItem item_d = new JMenuItem("DIVIDE");
    
    
    /**
     * @throws MalformedURLException
     */
    public Calculator() throws MalformedURLException 
    {
        ImageIcon eqImageIcon = new ImageIcon("./blueEq.png");
  
        Image eqImg = eqImageIcon.getImage();
        Image eqImage = eqImg.getScaledInstance(80, 80, java.awt.Image.SCALE_SMOOTH);
        eqImageIcon = new ImageIcon(eqImage);
        this.currentOperation = "";
        this.firstOperand = 0.0;

        JPanel displayPanel = new JPanel();
        displayPanel.setSize(900,900);
    
        outputField = new JTextField("0", 20);
        displayPanel.add(outputField);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(1, 2));

        JPanel digitButtonPanel = new JPanel();
        digitButtonPanel.setLayout(new GridLayout(4, 3));
        digits = new ArrayList<JButton>();
        digits.add(new JButton("0"));
        digits.add(new JButton("1"));
        digits.add(new JButton("2"));
        digits.add(new JButton("3"));
        digits.add(new JButton("4"));
        digits.add(new JButton("5"));
        digits.add(new JButton("6"));
        digits.add(new JButton("7"));
        digits.add(new JButton("8"));
        digits.add(new JButton("9"));
        digits.add(new JButton("."));
        digits.add(new JButton("AC"));

        digits.get(10).setForeground(Color.MAGENTA);
        digits.get(11).setForeground(Color.MAGENTA);

        digitButtonPanel.add(digits.get(0));
        digitButtonPanel.add(digits.get(1));
        digitButtonPanel.add(digits.get(2));
        digitButtonPanel.add(digits.get(3));
        digitButtonPanel.add(digits.get(4));
        digitButtonPanel.add(digits.get(5));
        digitButtonPanel.add(digits.get(6));
        digitButtonPanel.add(digits.get(7));
        digitButtonPanel.add(digits.get(8));
        digitButtonPanel.add(digits.get(9));
        digitButtonPanel.add(digits.get(10));
        digitButtonPanel.add(digits.get(11));
        buttonPanel.add(digitButtonPanel);

        JPanel operatorButtonPanel = new JPanel();
        operatorButtonPanel.setLayout(new GridLayout(5,1));
        plusSign = new JButton("+");
        minusSign = new JButton("-");
        multSign = new JButton("*");
        divSign = new JButton("/");
        
        
        JButton eqSign = new JButton();
        URL url = new URL("https://upload.wikimedia.org/wikipedia/commons/thumb/9/90/Equals.svg/120px-Equals.svg.png");
        ImageIcon eicon = new ImageIcon(url);
    
       
        eqSign.setIcon(eicon);

        plusSign = new JButton();
        url = new URL("https://upload.wikimedia.org/wikipedia/commons/thumb/9/9e/Plus_symbol.svg/100px-Plus_symbol.svg.png?20081122110508");
        eicon = new ImageIcon(url);
     
        plusSign.setIcon(eicon);

        minusSign = new JButton();
        url = new URL("https://www.i2symbol.com/images/symbols/math/minus_sign_u2212_icon_48x48.png");
        eicon = new ImageIcon(url);

      
        minusSign.setIcon(eicon);

        

        divSign = new JButton();
        url = new URL("https://img.favpng.com/3/18/0/long-division-obelus-mathematics-clip-art-png-favpng-dUDAsQDvBzb9mGExnwDMXcLyH_t.jpg");
        eicon = new ImageIcon(url);
  
        divSign.setIcon(eicon);
        
    
        
        eqSign.setVerticalTextPosition(AbstractButton.CENTER);
        eqSign.setHorizontalTextPosition(AbstractButton.CENTER);
        eqSign.setPreferredSize(new Dimension(100, 120));
        plusSign.setForeground(Color.BLUE);
        minusSign.setForeground(Color.BLUE);
        multSign.setForeground(Color.BLUE);
        divSign.setForeground(Color.BLUE);
        

        operatorButtonPanel.add(plusSign);
        operatorButtonPanel.add(minusSign);
        operatorButtonPanel.add(multSign);
        operatorButtonPanel.add(divSign);
        operatorButtonPanel.add(eqSign);
        buttonPanel.add(operatorButtonPanel);

        displayPanel.add(buttonPanel);
        add(displayPanel);
        
        digits.get(11).addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent event)
            {
                resetValues();
            }
        });
 

        digits.get(10).addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent event)
            {
                String currentText = outputField.getText();
                if(currentText.indexOf(".") < 0)
                {
                    outputField.setText(currentText + ".");
                }
            }
        });

        JMenuBar menuBar = new JMenuBar();
        JMenu menuTop = new JMenu("OPerations");
        
        
        menuTop.add(item_a);
        menuTop.add(item_mi);
        menuTop.add(item_m);
        menuTop.add(item_d);


        menuBar.add(menuTop);
        displayPanel.add(menuBar);
        OperatorListener opListener = new OperatorListener();
        plusSign.addActionListener(opListener);
        minusSign.addActionListener(opListener);
        multSign.addActionListener(opListener);
        divSign.addActionListener(opListener);
        eqListener eListener = new eqListener();
        eqSign.addActionListener(eListener);
        OperatorMListener opListener2 = new OperatorMListener();
        /* Menu item start  */
        item_a.addActionListener(opListener2); 
        item_mi.addActionListener(opListener2);
        item_m.addActionListener(opListener2);
        item_d.addActionListener(opListener2);
        JMenuItem reset = new JMenuItem("Reset Values");
        menuTop.add(reset);
        reset.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent event)
            {
                resetValues();
            }
        });
   
        /* Menu item end  */

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
        digits.get(2).addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent event)
            {
                String currentText = outputField.getText();
                    JButton source = (JButton)event.getSource();
                    String newDigit = "";
                    if (source == digits.get(2))
                    {
                        newDigit = "2";
                    }
                    currentText = currentText + newDigit;
                    currentText = currentText.replaceFirst("^0+(?!$)", "");
                    outputField.setText(currentText);
            }
        });
        digits.get(3).addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent event)
            {
                String currentText = outputField.getText();
                    JButton source = (JButton)event.getSource();
                    String newDigit = "";
                    if (source == digits.get(3))
                    {
                        newDigit = "3";
                    }
                    currentText = currentText + newDigit;
                    currentText = currentText.replaceFirst("^0+(?!$)", "");
                    outputField.setText(currentText);
            }
        });
        digits.get(4).addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent event)
            {
                String currentText = outputField.getText();
                    JButton source = (JButton)event.getSource();
                    String newDigit = "";
                    if (source == digits.get(4))
                    {
                        newDigit = "4";
                    }
                    currentText = currentText + newDigit;
                    currentText = currentText.replaceFirst("^0+(?!$)", "");
                    outputField.setText(currentText);
            }
        });
        digits.get(5).addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent event)
            {
                String currentText = outputField.getText();
                    JButton source = (JButton)event.getSource();
                    String newDigit = "";
                    if (source == digits.get(5))
                    {
                        newDigit = "5";
                    }
                    currentText = currentText + newDigit;
                    currentText = currentText.replaceFirst("^0+(?!$)", "");
                    outputField.setText(currentText);
            }
        });
        digits.get(6).addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent event)
            {
                String currentText = outputField.getText();
                    JButton source = (JButton)event.getSource();
                    String newDigit = "";
                    if (source == digits.get(6))
                    {
                        newDigit = "6";
                    }
                    currentText = currentText + newDigit;
                    currentText = currentText.replaceFirst("^0+(?!$)", "");
                    outputField.setText(currentText);
            }
        });
        digits.get(7).addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent event)
            {
                String currentText = outputField.getText();
                    JButton source = (JButton)event.getSource();
                    String newDigit = "";
                    if (source == digits.get(7))
                    {
                        newDigit = "7";
                    }
                    currentText = currentText + newDigit;
                    currentText = currentText.replaceFirst("^0+(?!$)", "");
                    outputField.setText(currentText);
            }
        });
        digits.get(8).addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent event)
            {
                String currentText = outputField.getText();
                    JButton source = (JButton)event.getSource();
                    String newDigit = "";
                    if (source == digits.get(8))
                    {
                        newDigit = "8";
                    }
                    currentText = currentText + newDigit;
                    currentText = currentText.replaceFirst("^0+(?!$)", "");
                    outputField.setText(currentText);
            }
        });
        digits.get(9).addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent event)
            {
                String currentText = outputField.getText();
                    JButton source = (JButton)event.getSource();
                    String newDigit = "";
                    if (source == digits.get(9))
                    {
                        newDigit = "9";
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
    private void resetValues()
    {
        currentOperation = "";
        firstOperand = 0.0;
        outputField.setText("0");
        outputField.setBackground(Color.WHITE);
    }
    private class eqListener implements ActionListener
    {
        @Override
            public void actionPerformed(ActionEvent event)
            {
                Double result = 0.0;
                String currentText = outputField.getText();
                try
                {
                    Double secondOperand = Double.parseDouble(currentText);
                    
                    if (currentOperation == "+")
                    {
                        result = firstOperand + secondOperand;
                    }
                    else if (currentOperation == "-")
                    {
                        result = firstOperand - secondOperand;
                    }
                    else if (currentOperation == "*")
                    {
                        result = firstOperand * secondOperand;
                    }
                    else if (currentOperation == "/")
                    {
                        if (secondOperand != 0.0)
                        {
                            result = firstOperand / secondOperand;
                        }
                        else
                        {
                            resetValues();
                            outputField.setBackground(Color.PINK);
                        }
                    }

                    outputField.setText(result.toString());
                    firstOperand = result;
                }

                catch(NumberFormatException e)
                {
                    resetValues();
                }
            }
    }
    private class OperatorListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent event)
        {
            JButton source = (JButton)event.getSource();
          
            if (source == plusSign) 
            {
                currentOperation = "+";
            }
            else if (source == minusSign) 
            {
                currentOperation = "-";
            }
            else if (source == multSign) 
            {
                currentOperation = "*";
            }
            else if (source == divSign) 
            {
                currentOperation = "/";
            }

            String currentText = outputField.getText();
            try
            {
                Double currentTextDouble = Double.parseDouble(currentText);
                firstOperand = currentTextDouble;
                outputField.setText("0");
            }
            catch(NumberFormatException e)
            {
                System.out.println("H");
                resetValues();
            }
        }
    }

    private class OperatorMListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent event)
        {
            
            JMenuItem menu = (JMenuItem)event.getSource();
            if (menu == item_a )
            {
                currentOperation = "+";
            }
            else if  (menu == item_mi)
            {
                currentOperation = "-";
            }
            else if  (menu == item_m)
            {
                currentOperation = "*";
            }
            else if (menu == item_d)
            {
                currentOperation = "/";
            }

            String currentText = outputField.getText();
            try
            {
                Double currentTextDouble = Double.parseDouble(currentText);
                firstOperand = currentTextDouble;
                outputField.setText("0");
            }
            catch(NumberFormatException e)
            {
                System.out.println("H");
                resetValues();
            }
        }
    }


    public static void main( String[] args )
    {
        JFrame myApp;
        try {
            myApp = new Calculator();
            myApp.setSize(new Dimension(1200,1200));
        myApp.setVisible(true);
        myApp.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        myApp.setLayout(new FlowLayout());
        } catch (MalformedURLException e) {
         
            e.printStackTrace();
        }
        
    }
    @Override
    public void actionPerformed(ActionEvent e) {
    
        
    }
}
