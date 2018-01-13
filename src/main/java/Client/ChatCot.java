package Client;

import Phrases.Bot;
import Phrases.DataBaseProcessing.Adapter;
import Phrases.DataBaseProcessing.PhraseModel;
import Story.Iterator;
import Story.StoryCollection;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.Socket;
import java.sql.*;

public class ChatCot extends JFrame implements KeyListener {
    private static final Logger log = LogManager.getLogger(ChatCot.class);

	private static final long serialVersionUID = 1L;
	private JPanel p = new JPanel();
	private JTextArea dialog = new JTextArea(20,50);
	private JTextArea input = new JTextArea(1,50);
	private JScrollPane scroll = new JScrollPane (
			dialog,
			JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
			JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

	// SOMEHOW I NEED TO ADD MYSQL TO THIS

	private Connection conn = null;
	private StoryCollection story = new StoryCollection();

	public void initial() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jbdc: mysql://localhost/chatcot_db", "root", "");

			Statement sqlState = conn.createStatement();

			String selectStuff = "Select first_name from customer";

			ResultSet rows = sqlState.executeQuery(selectStuff);

			while (rows.next()) {
				System.out.println(rows.getString("first_name"));

			}
		} catch (SQLException ex) {
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("VendorError: " + ex.getErrorCode());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public ChatCot(Adapter adapter) {
        super("Chat Cot");
    }

    public void start() {
        setSize(600, 400);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        dialog.setEditable(false);
        input.addKeyListener(this);

        p.add(scroll);
        p.add(input);
        p.setBackground(new Color(255, 200, 0));
        add(p);

        super.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {

            }

            @Override
            public void windowClosing(WindowEvent e) {
                closingAction();
            }

            @Override
            public void windowClosed(WindowEvent e) {

            }

            @Override
            public void windowIconified(WindowEvent e) {

            }

            @Override
            public void windowDeiconified(WindowEvent e) {

            }

            @Override
            public void windowActivated(WindowEvent e) {

            }

            @Override
            public void windowDeactivated(WindowEvent e) {

            }
        });
        setVisible(true);
    }

	public void keyPressed(KeyEvent e){
		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            enterAction();
		}
		if (e.getKeyCode() == KeyEvent.VK_UP) {
		    upAction();
        }
        if (e.getKeyCode() == KeyEvent.VK_DOWN) {
		    downAction();
        }
	}

    private void enterAction() {
        input.setEditable(false);
        //-----grab quote-----------
        String quote = input.getText();
        story.add(quote);
        story.getIterator().setLast();
        input.setText("");
        addText("-->You:\t"+quote);
        quote = quote.trim();
        while(quote.endsWith("!") || quote.endsWith(".") || quote.endsWith("?") ) {
            quote = quote.substring(0, quote.length()-1);
        }
        quote = quote.trim();
        try {
            Socket socket = new Socket("localhost",3345);
            sendToServer(quote + "\n", socket);
            addText("\n" + getFromServer(socket) + "\n");
            socket.close();
        } catch (IOException e) {
            log.error(e.getMessage());
        }
    }

    private void upAction() {
	    Iterator iterator = story.getIterator();
	    if (iterator.hasPrevious()) {
	        input.setText(iterator.previous());
        }
    }

    private void downAction() {
	    Iterator iterator = story.getIterator();
        if (iterator.hasNext()) {
            input.setText(iterator.next());
        }
        else {
            input.setText("");
        }
    }

    public void keyReleased(KeyEvent e){
		if(e.getKeyCode() == KeyEvent.VK_ENTER){
			input.setEditable(true);
		}
	}

	public void keyTyped(KeyEvent e){}

	public void closingAction() {

    }

	public void addText(String str){
		dialog.setText(dialog.getText() + str);
	}

    public void sendToServer(String message, Socket socket) throws IOException {
        PrintWriter writer = new PrintWriter(socket.getOutputStream());
        writer.write(message);
        writer.flush();
        //writer.close();
    }

    public String getFromServer(Socket socket) throws IOException {
	    BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        log.info("START GETTING");
        //reader.readLine();
        return reader.readLine();
    }
}