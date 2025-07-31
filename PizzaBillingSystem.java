import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class PizzaBillingSystem extends JFrame implements ActionListener {
    // GUI components
    private Label lblSize, lblToppings, lblQuantity, lblTotal;
    private CheckboxGroup sizeGroup;
    private Checkbox small, medium, large;
    private Checkbox toppingCheese, toppingOlives, toppingMushrooms;
    private TextField txtQuantity, txtTotal;
    private Button btnCalculate, btnClear, btnExit;

    private double totalPrice = 0.0;

    public PizzaBillingSystem() {
        setTitle("Pizza Billing System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // Enable proper full-screen mode
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setUndecorated(true); // Hide window borders

        // Background panel with image
        BackgroundPanel backgroundPanel = new BackgroundPanel("C:\\Users\\91888\\Desktop\\java\\.vscode\\img\\pizza-pizza-filled-with-tomatoes-salami-olives.jpg");
        backgroundPanel.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(15, 15, 15, 15);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Size selection
        lblSize = new Label("Select Pizza Size:");
        lblSize.setFont(new Font("Arial", Font.BOLD, 22));
        sizeGroup = new CheckboxGroup();
        small = new Checkbox("Small", sizeGroup, false);
        medium = new Checkbox("Medium", sizeGroup, false);
        large = new Checkbox("Large", sizeGroup, false);

        gbc.gridx = 0;
        gbc.gridy = 0;
        backgroundPanel.add(lblSize, gbc);

        gbc.gridx = 1;
        backgroundPanel.add(small, gbc);

        gbc.gridx = 2;
        backgroundPanel.add(medium, gbc);

        gbc.gridx = 3;
        backgroundPanel.add(large, gbc);

        // Toppings selection
        lblToppings = new Label("Select Toppings:");
        lblToppings.setFont(new Font("Arial", Font.BOLD, 22));
        toppingCheese = new Checkbox("Cheese");
        toppingOlives = new Checkbox("Olives");
        toppingMushrooms = new Checkbox("Mushrooms");

        gbc.gridx = 0;
        gbc.gridy = 1;
        backgroundPanel.add(lblToppings, gbc);

        gbc.gridx = 1;
        backgroundPanel.add(toppingCheese, gbc);

        gbc.gridx = 2;
        backgroundPanel.add(toppingOlives, gbc);

        gbc.gridx = 3;
        backgroundPanel.add(toppingMushrooms, gbc);

        // Quantity input
        lblQuantity = new Label("Enter Quantity:");
        lblQuantity.setFont(new Font("Arial", Font.BOLD, 22));
        txtQuantity = new TextField(5);
        txtQuantity.setFont(new Font("Arial", Font.PLAIN, 20));

        gbc.gridx = 0;
        gbc.gridy = 2;
        backgroundPanel.add(lblQuantity, gbc);

        gbc.gridx = 1;
        backgroundPanel.add(txtQuantity, gbc);

        // Total price display
        lblTotal = new Label("Total Price:");
        lblTotal.setFont(new Font("Arial", Font.BOLD, 22));
        txtTotal = new TextField(10);
        txtTotal.setFont(new Font("Arial", Font.PLAIN, 20));
        txtTotal.setEditable(false);

        gbc.gridx = 0;
        gbc.gridy = 3;
        backgroundPanel.add(lblTotal, gbc);

        gbc.gridx = 1;
        backgroundPanel.add(txtTotal, gbc);

        // Buttons
        btnCalculate = new Button("Calculate");
        btnClear = new Button("Clear");
        btnExit = new Button("Exit");

        btnCalculate.setFont(new Font("Arial", Font.BOLD, 18));
        btnClear.setFont(new Font("Arial", Font.BOLD, 18));
        btnExit.setFont(new Font("Arial", Font.BOLD, 18));

        btnCalculate.addActionListener(this);
        btnClear.addActionListener(this);
        btnExit.addActionListener(this);

        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 1;
        backgroundPanel.add(btnCalculate, gbc);

        gbc.gridx = 2;
        backgroundPanel.add(btnClear, gbc);

        gbc.gridx = 3;
        backgroundPanel.add(btnExit, gbc);

        // Add panel to frame
        add(backgroundPanel, BorderLayout.CENTER);
        setVisible(true);
    }

    // Calculate total price
    private void calculateTotal() {
        totalPrice = 0.0;
        int quantity = 1;

        try {
            quantity = Integer.parseInt(txtQuantity.getText());
        } catch (NumberFormatException e) {
            txtTotal.setText("Invalid Quantity");
            return;
        }

        if (small.getState()) {
            totalPrice = 100;
        } else if (medium.getState()) {
            totalPrice = 150;
        } else if (large.getState()) {
            totalPrice = 200;
        } else {
            txtTotal.setText("Select Size");
            return;
        }

        if (toppingCheese.getState()) totalPrice += 40;
        if (toppingOlives.getState()) totalPrice += 50;
        if (toppingMushrooms.getState()) totalPrice += 20;

        totalPrice *= quantity;
        txtTotal.setText(String.format("%.2f Rs", totalPrice));
    }

    // Action handling
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnCalculate) {
            calculateTotal();
        }
        if (e.getSource() == btnClear) {
            sizeGroup.setSelectedCheckbox(null);
            toppingCheese.setState(false);
            toppingOlives.setState(false);
            toppingMushrooms.setState(false);
            txtQuantity.setText("");
            txtTotal.setText("");
        }
        if (e.getSource() == btnExit) {
            System.exit(0);
        }
    }

    // Main method
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new PizzaBillingSystem());
    }

    // Background panel for image display
    class BackgroundPanel extends JPanel {
        private Image backgroundImage;

        public BackgroundPanel(String imagePath) {
            backgroundImage = new ImageIcon(imagePath).getImage();
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            // Scale the image properly
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        }
    }
}


