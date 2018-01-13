package Server;

import Phrases.Bot;
import Phrases.DataBaseProcessing.Adapter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static javax.swing.JFrame.EXIT_ON_CLOSE;

/**
 * @author mercenery
 *
 */
public class MultiThreadServer {
    private static final Logger log = LogManager.getLogger(MultiThreadServer.class);

    JFrame frame;
    JTextArea text;
    Bot bot;
    ServerSocket server;

    public MultiThreadServer(Adapter adapter) {
        bot = new Bot(adapter);
    }

    public void start() {
        bot.loadPhrases();
        frame = new JFrame("ChatCot Server");
        JPanel panel = new JPanel();
        text = new JTextArea(20, 50);
        JScrollPane scroll = new JScrollPane(
                text,
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER
        );
        text.setEditable(false);

        frame.setSize(600, 400);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);

        panel.add(scroll);
        panel.setBackground(new Color(0, 100, 255));
        text.setBackground(new Color(0, 200, 255));

        frame.add(panel);

        frame.setVisible(true);

        try {
            server = new ServerSocket(3345);
            while (true) {
                Socket socket = server.accept();
                info("accept");
                BufferedReader input;
                PrintWriter output;
                input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                output = new PrintWriter(socket.getOutputStream(), true);
                String request = input.readLine();
                info("request: \"" + request + "\"");
                String response = bot.check(request);
                info("response: \"" + response + "\"");
                output.write(response + "\n");
                output.flush();
                //input.close();
                //output.close();
            }
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

    private void info(String message) {
        log.info(message);
        text.setText(text.getText() + message + "\n");
    }

    public void shutdown() {
        if (server != null) {
            try {
                server.close();
            } catch (IOException e) {
                info(e.getMessage());
            }
        }
    }
    public void initialize() {

    }
    public void firstStart() {
        bot.initialize();
    }
}
