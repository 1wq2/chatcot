import Phrases.Bot;
import Story.Iterator;
import Story.StoryCollection;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.*;

public class ChatCot extends JFrame implements KeyListener {

	private static final long serialVersionUID = 1L;
	JPanel p =new JPanel();
	JTextArea dialog=new JTextArea(20,50);
	JTextArea input=new JTextArea(1,50);
	JScrollPane scroll=new JScrollPane(
			dialog,
			JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
			JScrollPane.HORIZONTAL_SCROLLBAR_NEVER
	);

	// SOMEHOW I NEED TO ADD MYSQL TO THIS

	Connection conn = null;
	StoryCollection story = new StoryCollection();

	Bot chat;

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

	public ChatCot() {
        super("Chat Cot");
        chat = new Bot();
    }

    public void start() {
		setSize(600,400);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		dialog.setEditable(false);
		input.addKeyListener(this);

		p.add(scroll);
		p.add(input);
		p.setBackground(new Color(255,200,0));
		add(p);

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

        addText(chat.check(quote) + "\n");
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

	public void addText(String str){
		dialog.setText(dialog.getText() + str);
	}
}