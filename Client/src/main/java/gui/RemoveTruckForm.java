package gui;

import main.Client;
import models.Truck;

import javax.swing.*;
import java.util.List;

/**
 * Created by Marin on 21-May-16.
 */
public class RemoveTruckForm extends JFrame {

    private JPanel panel1;
    private JComboBox comboBox1;
    private JButton removeButton;
    private JButton cancelButton;
    private Client client;

    public RemoveTruckForm(Client client) {
        super("Remove Truck");
        this.client = client;
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(panel1);
        this.setSize(700,300);
        this.setLocationRelativeTo(null);
        setVisible(true);

        updateComboBox();
        removeButton.addActionListener(e->{
            Truck truck = (Truck)comboBox1.getSelectedItem();
            client.removeTruck(truck);
            updateComboBox();
        });
        cancelButton.addActionListener(e->this.dispose());

        this.repaint();
    }

    private void updateComboBox() {
        comboBox1.removeAllItems();
        List<Truck> trucks = client.getTrucks();
        for(Truck truck : trucks) {
            comboBox1.addItem(truck);
        }
    }

}
