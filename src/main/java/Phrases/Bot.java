package Phrases;

import Phrases.PhrasesAdapter.MySqlAdapter;
import Phrases.PhrasesAdapter.PhraseModel;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Bot - class for doing some with phrases of chatBot
 */
public class Bot {

    Adapter adapter;

    ArrayList<String> types;
    LinkedList<LinkedList<String>> chatPhrases;
            //standard greetings
    /*
            {"hi", "hello", "hola", "hey", "hey man", "how's it going",
                    "nice to meet you", "you look great today",
                    "look at you, you're so smart"

            },
            {"hi", "hello", "hey", "meow", "greetings",
                    "i'm glad we can talk a bit",
                    "hi, nice to meet you", "Meow!!!", "good afternoon",
                    "hi! let's talk a bit", "hello, we can talk about stuff"},
            //question greetings
            {"how are you", "how r you", "how r u", "how are u",
                    "what's new", "what's going on", "how's life",
                    "what's up"},
            {"good", "doing well", "fine", "great", "not good",
                    "I dunno :D ", "you tell me",
                    "still excellent",
                    "fine, trying to find another job",
                    "not god. Have you seen those milk prices? Cats will be angry",
                    "not bad, just live in my pretty cat's life",
            },
            //yes
            {"yes", "speak", "talk", "i wanna speak, now", "talk to me, please",
                    "please", "you", "go away", "stupid",
            },
            {"no", "NO", "NO!!!!", "Please", "c'mon",
                    "stop it", "i don't wanna be chatcot anymore",
                    "can i talk to you later", "i really tired",
                    "give me a brake, man", "we don't speak anymore",
                    "chatCot Inc is closed today, bye",
                    "i don't speak English"
            },
            //default
            {"can't speak now, sorry :<", "no, i don't think so", "please, repeat",
                    "I don't get it", "I'm really trying, but I can understand a word!",
                    "Not this again", "what? I don't think so", "let's not start this topic again",
                    "I think, I'm not a real cat", "I can't imagene you said this again",
                    "you're funny :)",
                    "i don't think so", " It's wonderful idea",
                    "look! just behind you!", "2X2=5", "Ich verstehe dich nicht",
                    "can't wark in this conditions",
                    "please, shut up", "you're bad", "Tu enim operor non intellego",
                    "oh my god", "i believe i can fly", "Nie rozumiem cie", "repeat yourself",
                    "____ i believe i can touch the sky", "you could ",
                    "stop talking and go to work", "010001 010101010101 0101010101010 0110 101010100 0101010 010101",
                    "(i'm unavailable right now)"}
    };
    */

    public Bot() {
        chatPhrases = new LinkedList<LinkedList<String>>();
        types = new ArrayList<String>();
        types.add("standard greetings");
        chatPhrases.add(new LinkedList<String>());

        types.add("special greetings");
        chatPhrases.add(new LinkedList<String>());

        types.add("question greetings");
        chatPhrases.add(new LinkedList<String>());

        types.add("greetings answer");
        chatPhrases.add(new LinkedList<String>());

        types.add("yes");
        chatPhrases.add(new LinkedList<String>());

        types.add("no");
        chatPhrases.add(new LinkedList<String>());

        types.add("default");
        chatPhrases.add(new LinkedList<String>());

        adapter = new MySqlAdapter();


        // FOR FIRST TIME START - NEED TO INITIALIZE BD !!!!!!!
        // initialize();




        List<PhraseModel> list = adapter.listPhrases();
        for (PhraseModel entity : list) {
            if (types.contains(entity.getType())) {
                chatPhrases.get(types.indexOf(entity.getType())).add(entity.getPhrase());
            }
        }
    }

    public void initialize() {
        adapter.addPhrase("standard greetings", "hi");
        adapter.addPhrase("standard greetings", "hello");
        adapter.addPhrase("standard greetings", "hola");
        adapter.addPhrase("standard greetings", "hey");
        adapter.addPhrase("standard greetings", "hey man");
        adapter.addPhrase("standard greetings", "how's it going");
        adapter.addPhrase("standard greetings", "nice to meet you");
        adapter.addPhrase("standard greetings", "eou look great today");
        adapter.addPhrase("standard greetings", "look at you, you're so smart");

        adapter.addPhrase("special greetings", "hi");
        adapter.addPhrase("special greetings", "hello");
        adapter.addPhrase("special greetings", "hey");
        adapter.addPhrase("special greetings", "meow");
        adapter.addPhrase("special greetings", "greetings");
        adapter.addPhrase("special greetings", "i'm glad we can talk a bit");
        adapter.addPhrase("special greetings", "hi, nice to meet you");
        adapter.addPhrase("special greetings", "Meow!!!");
        adapter.addPhrase("special greetings", "good afternoon");
        adapter.addPhrase("special greetings", "hi! Let's talk a bit");
        adapter.addPhrase("special greetings", "hello, we can talk about stuff");

        adapter.addPhrase("question greetings", "how are you?");
        adapter.addPhrase("question greetings", "how r you?");
        adapter.addPhrase("question greetings", "how r u?");
        adapter.addPhrase("question greetings", "how are u?");
        adapter.addPhrase("question greetings", "what's new?");
        adapter.addPhrase("question greetings", "what's going on?");
        adapter.addPhrase("question greetings", "how's life?");
        adapter.addPhrase("question greetings", "what's up?");

        adapter.addPhrase("greetings answer", "good");
        adapter.addPhrase("greetings answer", "doing well");
        adapter.addPhrase("greetings answer", "fine");
        adapter.addPhrase("greetings answer", "great");
        adapter.addPhrase("greetings answer", "not good");
        adapter.addPhrase("greetings answer", "I dunno :D");
        adapter.addPhrase("greetings answer", "you tell me");
        adapter.addPhrase("greetings answer", "still excellent");
        adapter.addPhrase("greetings answer", "fine, trying to find another job");
        adapter.addPhrase("greetings answer", "not good. Have you seen those milk prices? Cats will be angry");
        adapter.addPhrase("greetings answer", "not bad, just live in my pretty cat's life");

        adapter.addPhrase("yes", "yes");
        adapter.addPhrase("yes", "speak");
        adapter.addPhrase("yes", "talk");
        adapter.addPhrase("yes", "i wanna speak, now");
        adapter.addPhrase("yes", "talk to me, please");
        adapter.addPhrase("yes", "please");
        adapter.addPhrase("yes", "you");
        adapter.addPhrase("yes", "go away");
        adapter.addPhrase("yes", "stupid");

        adapter.addPhrase("no", "no");
        adapter.addPhrase("no", "NO");
        adapter.addPhrase("no", "NO!!!!");
        adapter.addPhrase("no", "Please");
        adapter.addPhrase("no", "c'mon");
        adapter.addPhrase("no", "stop it");
        adapter.addPhrase("no", "i don't wanna be chatcot anymore");
        adapter.addPhrase("no", "can i talk to you later");
        adapter.addPhrase("no", "i really tired");
        adapter.addPhrase("no", "give me a brake, man");
        adapter.addPhrase("no", "we don't speak anymore");
        adapter.addPhrase("no", "chatCot Inc is closed today, bye");
        adapter.addPhrase("no", "i don't speak English");


        adapter.addPhrase("default", "can't speak now, sorry :<");
        adapter.addPhrase("default", "no, i don't think so");
        adapter.addPhrase("default", "please, repeat");
        adapter.addPhrase("default", "I don't get it");
        adapter.addPhrase("default", "I'm really trying, but I can understand a word!");
        adapter.addPhrase("default", "Not this again");
        adapter.addPhrase("default", "what? I don't think so");
        adapter.addPhrase("default", "let's not start this topic again");
        adapter.addPhrase("default", "I think, I'm not a real cat");
        adapter.addPhrase("default", "I can't imagene you said this again");
        adapter.addPhrase("default", "you're funny :)");
        adapter.addPhrase("default", "i don't think so");
        adapter.addPhrase("default", "It's wonderful idea");
        adapter.addPhrase("default", "look! just behind you!");
        adapter.addPhrase("default", "2X2=5");
        adapter.addPhrase("default", "Ich verstehe dich nicht");
        adapter.addPhrase("default", "can't work in this conditions");
        adapter.addPhrase("default", "please, shut up");
        adapter.addPhrase("default", "you're bad");
        adapter.addPhrase("default", "Tu enim operor non intellego");
        adapter.addPhrase("default", "oh my god");
        adapter.addPhrase("default", "i believe i can fly");
        adapter.addPhrase("default", "Nie rozumiem cie");
        adapter.addPhrase("default", "repeat yourself");
        adapter.addPhrase("default", "____ i believe i can touch the sky");
        adapter.addPhrase("default", "you could ");
        adapter.addPhrase("default", "stop talking and go to work");
        adapter.addPhrase("default", "010001 010101010101 0101010101010 0110 101010100 0101010 010101");
        adapter.addPhrase("default", "(i'm unavailable right now)");
    }

    /**
     * checking contains string into phrases
     * @param in - input string for checking
     * @param str - storage of phrases
     * @return true - if str contains in
     *         false - if str don't contains in
     */
    public boolean contains(String in, String[] str){
        boolean match = false;
        for(int i = 0; i < str.length;i++){
            if(str[i].equals(in)){
                match = true;
            }
        }
        return match;
    }

    /**
     * getting result of checking
     * @param quote - string from chat window
     * @return answer of Bot
     */
    public String check(String quote) {
        byte response = 0;
        StringBuilder result = new StringBuilder();
			/*
			0:we're searching through chatBot[][] for matches
			1:we didn't find anything
			2:we did find something
			*/
        //-----check for matches----
        int j = 0;//which group we're checking
        while(response == 0){
            if(chatPhrases.get(j * 2).contains(quote.toLowerCase())) {
                response = 2;
                int r = (int)Math.floor(Math.random() * chatPhrases.get((j * 2) + 1).size());
                result.append("\n-->ChatCot \t").append(chatPhrases.get((j * 2) + 1).get(r));
            }
            j++;
            if(j * 2 == chatPhrases.size() - 1 && response == 0){
                response = 1;
            }
        }

        //-----default--------------
        if(response == 1){
            int r = (int)Math.floor(Math.random() * chatPhrases.get(chatPhrases.size() - 1).size());
            result.append("\n-->ChatCot\t").append(chatPhrases.get(chatPhrases.size() - 1).get(r));
        }
        return result.toString();
    }

    public void shutdown() {
        this.adapter.shutdown();
    }
}
