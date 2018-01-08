import Phrases.DataBaseProcessing.XMLProcessing.XmlAdapter;
import Phrases.DataBaseProcessing.XMLProcessing.XmlDomParser;

public class Main {
    public static void main(String[] args) {
        ChatCot chat = new ChatCot(new XmlAdapter("phrases.xml", new XmlDomParser()));

        // delete after first start;
        chat.firstStart();
        chat.start();
    }
}
