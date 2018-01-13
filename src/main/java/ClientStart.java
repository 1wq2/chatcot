import Client.ChatCot;
import Phrases.DataBaseProcessing.XMLProcessing.XmlAdapter;
import Phrases.DataBaseProcessing.XMLProcessing.XmlDomParser;

public class ClientStart {
    public static void main(String[] args) {
        ChatCot chat = new ChatCot(new XmlAdapter("phrases.xml", new XmlDomParser()));
        chat.start();
    }
}
