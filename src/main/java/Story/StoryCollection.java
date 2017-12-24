package Story;

import java.util.LinkedList;

/**
 * class for storage user story
 */
public class StoryCollection extends LinkedList<String> {
    private Iterator iterator;

    public StoryCollection(){
        super();
        iterator = new Iterator(this);
    }

    /**
     * getting iterator for this collection
     * @return Iterator
     */
    public Iterator getIterator() {
        return iterator;
    }
}

