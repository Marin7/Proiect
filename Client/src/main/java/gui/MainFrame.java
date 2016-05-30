package gui;

import main.Client;
import models.Order;
import models.Truck;

import javax.swing.*;
import java.util.List;
import java.util.Map;

/**
 * Created by Marin on 21-May-16.
 */
public class MainFrame extends JFrame {

    private JTabbedPane tabbedPane1;
    private JPanel panel1;
    private JPanel TrucksPositions;
    private JButton cancelButton;
    private JButton addTruckButton;
    private JButton removeTruckButton;
    private JComboBox comboBox1;
    private JTextField textField1;
    private JButton addOrderButton;
    private JButton exitButton;
    private JPanel panel2;
    private JTextArea textArea1;
    private JButton updateButton;
    private Client client;

    public MainFrame(Client client) {
        super("Main");
        this.client = client;
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(panel1);
        this.setSize(1100,600);
        this.setLocationRelativeTo(null);

        addTruckButton.addActionListener(e->{
            AddTruckFrame addTruckFrame = new AddTruckFrame(client);
        });

        removeTruckButton.addActionListener(e->{
            RemoveTruckForm removeTruckForm = new RemoveTruckForm(client);
        });

        addOrderButton.addActionListener(e-> {
            Map.Entry<Truck, String> entry = (Map.Entry<Truck, String>)comboBox1.getSelectedItem();
            client.placeOrder(entry.getKey(), entry.getValue(), textField1.getText());
        });

        cancelButton.addActionListener(e-> System.exit(1));

        updateButton.addActionListener( e -> {
            updateComboBox();
            addTrucks();
        });

        updateComboBox();
        addTrucks();

        setVisible(true);
        this.revalidate();
        this.repaint();
    }

    private void updateComboBox() {
        comboBox1.removeAllItems();
        Map<Truck, String> trucks = client.getAvailableTrucks();
        for(Map.Entry<Truck, String> entry : trucks.entrySet()) {
            comboBox1.addItem(entry);
        }
    }

    private void addTrucks() {
        StringBuilder builder = new StringBuilder();
        Map<Truck, String> trucks = client.getAvailableTrucks();
        builder.append("Available Trucks and their location: \n");
        for(Truck truck : trucks.keySet()) {
            builder.append(truck + " - " + trucks.get(truck) + '\n');
        }
        builder.append("\n");

        List<Order> orders = client.getOrders();
        builder.append("Busy trucks: \n");
        for(Order order : orders) {
            builder.append(order.getTruck() + ": " + order.getStartingPoint() + " -> " +
            order.getEndPoint()).append('\n');
        }
        textArea1.setText(builder.toString());
    }
}
