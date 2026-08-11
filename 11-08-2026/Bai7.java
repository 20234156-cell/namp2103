import javax.swing.*;
import java.awt.*;

public class Bai7 extends JFrame {
    private JTextField displayField;
    private StringBuilder expression;

    public Bai7() {
        setTitle("Bài 7 - Máy tính mini");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(350, 400);
        setLocationRelativeTo(null);

        expression = new StringBuilder();

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        displayField = new JTextField();
        displayField.setEditable(false);
        displayField.setFont(new Font("Arial", Font.PLAIN, 20));
        displayField.setHorizontalAlignment(JTextField.RIGHT);
        panel.add(displayField, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(4, 4, 5, 5));

        String[] buttons = {
            "7", "8", "9", "/",
            "4", "5", "6", "*",
            "1", "2", "3", "-",
            "0", ".", "=", "+"
        };

        for (String btn : buttons) {
            JButton button = new JButton(btn);
            button.setFont(new Font("Arial", Font.PLAIN, 18));
            button.addActionListener(e -> handleButtonClick(btn));
            buttonPanel.add(button);
        }

        JButton clearButton = new JButton("Clear");
        clearButton.setFont(new Font("Arial", Font.PLAIN, 16));
        clearButton.addActionListener(e -> clear());
        panel.add(clearButton, BorderLayout.SOUTH);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(panel, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        setContentPane(new JPanel(new BorderLayout()) {{
            add(displayField, BorderLayout.NORTH);
            add(buttonPanel, BorderLayout.CENTER);
            JButton clearBtn = new JButton("Clear");
            clearBtn.addActionListener(e -> clear());
            add(clearBtn, BorderLayout.SOUTH);
        }});

        setVisible(true);
    }

    private void handleButtonClick(String value) {
        if (value.equals("=")) {
            try {
                double result = eval(expression.toString());
                displayField.setText(String.valueOf(result));
                expression = new StringBuilder();
            } catch (Exception ex) {
                displayField.setText("Lỗi");
                expression = new StringBuilder();
            }
        } else {
            expression.append(value);
            displayField.setText(expression.toString());
        }
    }

    private double eval(String expression) {
        return new Object() {
            int pos = -1, ch;

            void nextChar() {
                ch = (++pos < expression.length()) ? expression.charAt(pos) : -1;
            }

            boolean eat(int charToEat) {
                while (ch == ' ') nextChar();
                if (ch == charToEat) {
                    nextChar();
                    return true;
                }
                return false;
            }

            double parse() {
                nextChar();
                double x = parseExpression();
                if (pos < expression.length()) throw new RuntimeException("Unexpected: " + (char) ch);
                return x;
            }

            double parseExpression() {
                double x = parseTerm();
                while (true) {
                    if (eat('+')) x += parseTerm();
                    else if (eat('-')) x -= parseTerm();
                    else return x;
                }
            }

            double parseTerm() {
                double x = parseFactor();
                while (true) {
                    if (eat('*')) x *= parseFactor();
                    else if (eat('/')) x /= parseFactor();
                    else return x;
                }
            }

            double parseFactor() {
                if (eat('+')) return parseFactor();
                if (eat('-')) return -parseFactor();

                double x;
                int startPos = this.pos;
                if (ch == '(') {
                    eat('(');
                    x = parseExpression();
                    eat(')');
                } else if ((ch >= '0' && ch <= '9') || ch == '.') {
                    while ((ch >= '0' && ch <= '9') || ch == '.') nextChar();
                    x = Double.parseDouble(expression.substring(startPos, this.pos));
                } else {
                    throw new RuntimeException("Unexpected: " + (char) ch);
                }

                return x;
            }
        }.parse();
    }

    private void clear() {
        displayField.setText("");
        expression = new StringBuilder();
    }

    public static void main(String[] args) {
        new Bai7();
    }
}
