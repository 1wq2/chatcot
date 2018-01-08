import Phrases.DataBaseProcessing.SQLite.SQLiteAdapter;
import Phrases.DataBaseProcessing.XMLProcessing.XMLAdapter;
import Phrases.DataBaseProcessing.XMLProcessing.XmlDomParser;

public class Main {
    public static void main(String[] args) {
        ChatCot chat = new ChatCot(new XMLAdapter("phrases.xml", new XmlDomParser(), ""));

        // delete after first start;
        chat.firstStart();
        chat.start();
    }
}
