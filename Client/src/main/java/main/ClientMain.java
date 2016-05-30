package main;

import gui.LoginFrame;

/**
 * Created by Marin on 20-May-16.
 */
public class ClientMain {

    private static final int PORT = 5555;
    private static final String HOSTNAME = "localhost";

    public static void main(String[] args) {

        Client client = new Client(HOSTNAME, PORT);
        LoginFrame loginFrame = new LoginFrame(client);
        //MainFrame mainFrame = new MainFrame(client);
    }
}
