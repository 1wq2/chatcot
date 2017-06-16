package divmod;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;

import java.awt.Color;

import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

import java.lang.Math;

public class chatCot extends JFrame implements KeyListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JPanel p=new JPanel();
	JTextArea dialog=new JTextArea(20,50);
	JTextArea input=new JTextArea(1,50);
	JScrollPane scroll=new JScrollPane(
		dialog,
		JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
		JScrollPane.HORIZONTAL_SCROLLBAR_NEVER
	);
	
	
	
	String[][] chatBot={
		//standard greetings
		{"hi","hello","hola","hey","hey man","how's it going",
			"nice to meet you","you look great today",
			"look at you, you're so smart"
			
			},
		{"hi","hello","hey","meow", "greetings",
				"i'm glad we can talk a bit", 
				"hi, nice to meet you", "Meow!!!", "good afternoon", 
				"hi! let's talk a bit", "hello, we can talk about stuff"},
		//question greetings
		{"how are you","how r you","how r u","how are u",
			"what's new", "what's going on", "how's life", 
			"what's up"},
		{"good","doing well", "fine", "great", "not good",
				"I dunno :D ", "you tell me",
				"still excellent",
				"fine, trying to find another job",
				"not god. Have you seen those milk prices? Cats will be angry",
				"not bad, just live in my pretty cat's life",
				},
		//yes
		{"yes","speak", "talk",  "i wanna speak, now", "talk to me, please",
					"please", "you", "go away", "stupid", 
					},
		{"no","NO","NO!!!!", "Please", "c'mon",
			"stop it", "i don't wanna be chatcot anymore",
			"can i talk to you later", "i really tired",
			"give me a brake, man", "we don't speak anymore",
			"chatCot Inc is closed today, bye", 
			"i don't speak English"
			},
		//default
		{"can't speak now, sorry :<","no, i don't think so","please, repeat",
				"I don't get it", "I'm really trying, but I can understand a word!",
				"Not this again", "what? I don't think so", "let's not start this topic again",
				"I think, I'm not a real cat", "I can't imagene you said this again",
				"you're funny :)", 
				"i don't think so"," я теб€ не понимаю",
				"look! just behind you!","2X2=5", "Ich verstehe dich nicht",
				"can't wark in this conditions", 
				"please, shut up","you're bad","Tu enim operor non intellego",
				"oh my god", "i believe i can fly","Nie rozumiem cie","я не розум≥ю тебе",
				"____ i believe i can touch the sky", "я не разумею ц€бе", 
				"stop talking and go to work", "010001 010101010101 0101010101010 0110 101010100 0101010 010101",
		"(i'm unavailable right now)"}
	};
	
	public static void main(String[] args){
		new chatCot();
	}
	
	public chatCot(){
		super("Chat Cot");
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
		if(e.getKeyCode()==KeyEvent.VK_ENTER){
			input.setEditable(false);
			//-----grab quote-----------
			String quote=input.getText();
			input.setText("");
			addText("-->You:\t"+quote);
			quote.trim();
			while(
				quote.charAt(quote.length()-1)=='!' || // returns the last element
				quote.charAt(quote.length()-1)=='.' ||
				quote.charAt(quote.length()-1)=='?'
			){
				quote=quote.substring(0,quote.length()-1);
			}
			quote.trim();
			byte response=0;
			/*
			0:we're searching through chatBot[][] for matches
			1:we didn't find anything
			2:we did find something
			*/
			//-----check for matches----
			int j=0;//which group we're checking
			while(response==0){
				if(inArray(quote.toLowerCase(),chatBot[j*2])){
					response=2;
					int r=(int)Math.floor(Math.random()*chatBot[(j*2)+1].length);
					addText("\n-->ChatCot \t"+chatBot[(j*2)+1][r]);
				}
				j++;
				if(j*2==chatBot.length-1 && response==0){
					response=1;
				}
			}
			
			//-----default--------------
			if(response==1){
				int r=(int)Math.floor(Math.random()*chatBot[chatBot.length-1].length);
				addText("\n-->ChatCot\t"+chatBot[chatBot.length-1][r]);
			}
			addText("\n");
		}
	}
	
	public void keyReleased(KeyEvent e){
		if(e.getKeyCode()==KeyEvent.VK_ENTER){
			input.setEditable(true);
		}
	}
	
	public void keyTyped(KeyEvent e){}
	
	public void addText(String str){
		dialog.setText(dialog.getText()+str);
	}
	
	public boolean inArray(String in,String[] str){
		boolean match=false;
		for(int i=0;i<str.length;i++){
			if(str[i].equals(in)){
				match=true;
			}
		}
		return match;
	}
}