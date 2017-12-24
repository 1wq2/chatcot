package Story;

import static org.junit.Assert.*;

public class IteratorTest {
    @org.junit.Before
    public void setUp() throws Exception {

    }

    @org.junit.After
    public void tearDown() throws Exception {
    }

    @org.junit.Test
    public void hasNext() {
        StoryCollection collection = new StoryCollection();
        collection.add("TestString1");
        collection.add("TestString2");
        collection.add("TestString3");
        Iterator iterator = collection.getIterator();
        assertTrue(iterator.hasNext());
        assertEquals(iterator.next(),"TestString1");
        assertTrue(iterator.hasNext());
        assertEquals(iterator.next(),"TestString2");
        assertTrue(iterator.hasNext());
        assertEquals(iterator.next(),"TestString3");
        assertTrue(!iterator.hasNext());
        assertEquals(iterator.next(),null);
        assertTrue(!iterator.hasNext());
        assertEquals(iterator.next(),null);
    }

    @org.junit.Test
    public void hasPrevious() {
        StoryCollection collection = new StoryCollection();
        collection.add("TestString1");
        collection.add("TestString2");
        collection.add("TestString3");
        Iterator iterator = collection.getIterator();
        iterator.setLast();
        assertTrue(iterator.hasPrevious());
        assertEquals(iterator.previous(),"TestString3");
        assertTrue(iterator.hasPrevious());
        assertEquals(iterator.previous(),"TestString2");
        assertTrue(iterator.hasPrevious());
        assertEquals(iterator.previous(),"TestString1");
        assertTrue(!iterator.hasPrevious());
        assertEquals(iterator.previous(),null);
    }


    @org.junit.Test
    public void setLast() {
        StoryCollection collection = new StoryCollection();
        collection.add("TestString1");
        collection.add("TestString2");
        collection.add("TestString3");
        Iterator iterator = collection.getIterator();
        iterator.setLast();
        assertEquals(iterator.current(), null);
        assertTrue(iterator.hasPrevious());
        assertEquals(iterator.previous(),"TestString3");
    }

    @org.junit.Test
    public void setFirst() {
        StoryCollection collection = new StoryCollection();
        collection.add("TestString1");
        collection.add("TestString2");
        collection.add("TestString3");
        Iterator iterator = collection.getIterator();
        iterator.setFirst();
        assertEquals(iterator.current(), null);
        assertTrue(iterator.hasNext());
        assertEquals(iterator.next(),"TestString1");
    }
}