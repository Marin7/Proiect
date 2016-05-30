package gui;

import main.Client;
import models.Truck;

import javax.swing.*;

/**
 * Created by Marin on 21-May-16.
 */
public class AddTruckFrame extends JFrame {

    private JPanel panel1;
    private JButton addTruckButton;
    private JButton cancelButton;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JTextField textField4;
    private Client client;

    public AddTruckFrame(Client client) {
        super("Add Truck");
        this.client = client;
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(panel1);
        this.setSize(400,300);
        this.setLocationRelativeTo(null);

        addTruckButton.addActionListener(e -> {
            String model = textField1.getText();
            String number = textField2.getText();
            Double capacity = Double.parseDouble(textField3.getText());
            Double maxQuantity = Double.parseDouble(textField4.getText());
            Truck truck = new Truck(number, model, capacity, maxQuantity);
            client.addTruck(truck);
        });

        cancelButton.addActionListener(e-> this.dispose());

        setVisible(true);
        this.repaint();
    }
}
