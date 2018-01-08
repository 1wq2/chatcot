package Phrases.DataBaseProcessing.XMLProcessing;

import Phrases.DataBaseProcessing.Adapter;
import Phrases.DataBaseProcessing.PhraseModel;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class XmlDomParserTest {

    Adapter adapter = new XmlAdapter("test.xml", new XmlDomParser());
    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void parseFromXML() {
        List<PhraseModel> list = adapter.listPhrases();
        for (PhraseModel model : list){
            assertEquals(model.getType(),"default");
            assertTrue(model.getId() == 0);
        }
    }

    @Test
    public void parseToXML() {
        adapter.create();

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


        adapter.addPhrase("default", "can't speak now, sorry :(");
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
}