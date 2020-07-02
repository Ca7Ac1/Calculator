import javax.swing.JButton;
import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Calculator extends JPanel implements ActionListener {

    private final int WIDTH = 300;
    private final int HEIGHT = 350;

    private JButton[] numbers;
    private JButton[] operators;

    private JButton clear;

    private int numOne;
    private int numTwo;
    private String operator;

    private Display display;

    public Calculator(Display display) {
        numOne = 0;
        numTwo = 0;
        operator = "+";
        this.display = display;
        numbers = new JButton[10];
        operators = new JButton[5];

        initLayout();
        initButtons();
    }

    private void initLayout() {
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setLayout(new GridLayout(4, 4));
    }

    private void initButtons() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                numbers[(i * 3) + j] = new JButton(Integer.toString((i * 3) + j + 1));

                numbers[(i * 3) + j].addActionListener(this);
                numbers[(i * 3) + j].setBackground(Color.gray);

                add(numbers[(i * 3) + j]);
            }

            switch (i) {
                case 0:
                    operators[0] = new JButton("+");

                    operators[0].addActionListener(this);
                    operators[0].setBackground(Color.gray);

                    add(operators[0]);
                    break;
                case 1:
                    operators[1] = new JButton("-");

                    operators[1].addActionListener(this);
                    operators[1].setBackground(Color.gray);

                    add(operators[1]);
                    break;
                case 2:
                    operators[2] = new JButton("x");

                    operators[2].addActionListener(this);
                    operators[2].setBackground(Color.gray);

                    add(operators[2]);
                    break;
            }
        }

        clear = new JButton("Cl");

        clear.addActionListener(this);
        clear.setBackground(Color.gray);

        add(clear);

        numbers[9] = new JButton("0");

        numbers[9].addActionListener(this);
        numbers[9].setBackground(Color.gray);

        add(numbers[9]);

        operators[4] = new JButton("=");

        operators[4].addActionListener(this);
        operators[4].setBackground(Color.gray);

        add(operators[4]);

        operators[3] = new JButton("/");

        operators[3].addActionListener(this);
        operators[3].setBackground(Color.gray);

        add(operators[3]);
    }

    private void getNumber(ActionEvent e) {
        for (int i = 0; i < 10; i++) {
            if (numbers[i] == e.getSource()) {
                if (i == 9) {
                    numOne = Integer.parseInt(Integer.toString(numOne) + "0");
                } else {
                    numOne = Integer.parseInt(Integer.toString(numOne) + Integer.toString(i + 1));
                }
            }
        }
    }

    private void getClear(ActionEvent e) {
        if (e.getSource() == clear) {
            String num = Integer.toString(numOne);

            if (num.length() > 1) {
                numOne = Integer.parseInt(num.substring(0, num.length() - 1));
            } else {
                numOne = 0;
            }
        }
    }

    private void getOperator(ActionEvent e) {
        for (int i = 0; i < operators.length - 1; i++) {
            if (operators[i] == e.getSource()) {
                numTwo = numOne;
                numOne = 0;

                switch (i) {
                    case 0:
                        operator = "+";
                        break;
                    case 1:
                        operator = "-";
                        break;
                    case 2:
                        operator = "x";
                        break;
                    case 3:
                        operator = "/";
                        break;
                }
            }
        }
    }

    private void equal(ActionEvent e) {
        if (operators[4] == e.getSource()) {
            switch (operator) {
                case "+":
                    numOne += numTwo;
                    numTwo = 0;
                    break;
                case "-":
                    numOne = numTwo - numOne;
                    numTwo = 0;
                    break;
                case "x":
                    numOne *= numTwo;
                    numTwo = 0;
                    break;
                case "/":
                    try {
                        numOne = numTwo / numOne;
                        numTwo = 0;
                    } catch (Exception err) {
                        numOne = 404;
                        numTwo = 0;
                    }
                    break;
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        getNumber(e);
        getOperator(e);
        getClear(e);
        equal(e);

        display.write(numOne);
    }
}