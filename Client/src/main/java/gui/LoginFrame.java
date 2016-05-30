package gui;

import dao.UserDAO;
import main.Client;

import javax.swing.*;

/**
 * Created by Marin on 21-May-16.
 */
public class LoginFrame extends JFrame{
    private JPanel panel1;
    private JButton cancelButton;
    private JTextField textField1;
    private JTextField textField2;
    private JButton loginButton;
    private Client client;

    public LoginFrame(Client client) {
        super("Login");
        this.client = client;
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(panel1);
        loginButton.addActionListener(e -> {
            UserDAO userDAO = UserDAO.getInstance();
            boolean isUser = userDAO.login(textField1.getText(), textField2.getText());
            if(isUser) {
                this.dispose();
                MainFrame mainFrame = new MainFrame(client);
            }
        });
        cancelButton.addActionListener(e -> System.exit(1));
        this.setSize(350,200);
        this.setLocationRelativeTo(null);

        setVisible(true);
        this.repaint();
    }

}
