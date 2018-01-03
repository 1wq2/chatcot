import Phrases.DataBaseProcessing.SQLite.SQLiteAdapter;

public class Main {
    public static void main(String[] args) {
        ChatCot chat = new ChatCot(new SQLiteAdapter());

        // delete after first start;
        //chat.firstStart();
        chat.start();
    }
}
