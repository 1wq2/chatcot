import Phrases.DataBaseProcessing.XMLProcessing.XmlAdapter;
import Phrases.DataBaseProcessing.XMLProcessing.XmlDomParser;
import Server.MultiThreadServer;

public class ServerStart {
    public static void main(String[] args) {
        MultiThreadServer server = new MultiThreadServer(new XmlAdapter("phrases.xml", new XmlDomParser()));
        //server.firstStart();
        server.start();
    }
}