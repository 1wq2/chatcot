package Phrases.PhrasesAdapter;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class MySqlAdapterTest {

    private MySqlAdapter adapter;

    @Before
    public void setUp() throws Exception {
        adapter = new MySqlAdapter();
    }

    @After
    public void tearDown() throws Exception {
        adapter.shutdown();
    }

    @Test
    public void addPhrase() {
        adapter.addPhrase("COMMON", "hi cat!");

        List<PhraseModel> list = adapter.listPhrases();
        assertEquals(list.get(list.size() - 1).getPhrase(), "hi cat!");
    }

    @Test
    public void listPhrases() {

    }

    @Test
    public void deletePhrases() {
        adapter.deletePhrase(new PhraseModel("COMMON","hi cat!"));
        List<PhraseModel> list = adapter.listPhrases();
        assertNotEquals(list.get(list.size() - 1).getPhrase(), "hi cat!");
    }

    @Test
    public void deleteEntity() {
    }
}