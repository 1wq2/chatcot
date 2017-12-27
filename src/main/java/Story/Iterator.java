package Story;

/**
 * class for iterating in User input story
 */
public class Iterator {

    private StoryCollection collection;
    private int index;

    protected Iterator(StoryCollection collection) {
        this.collection = collection;
        this.index = -1;
    }

    /**
     * checking next String in story
     * @return - has next String in story?
     */
    public boolean hasNext() {
        return (index + 1 < collection.size());
    }

    /**
     * checking previous String in story
     * @return - has previous String in story?
     */
    public boolean hasPrevious() {
        return (index > 0);
    }

    /**
     * go to next String in story
     * @return - if has Next - the next String in Story
     *           else - return null
     */
    public String next() {
        if (hasNext()) {
            index++;
            return collection.get(index);
        }
        return null;
    }

    /**
     * go to previous String in story
     * @return - if has Previous - the previous String in Story
     *           else - return null
     */
    public String previous() {
        if (hasPrevious()) {
            index--;
            return collection.get(index);
        }
        return null;
    }

    /**
     * getting current String from iterator
     * @return - current String in story
     * if index isn't in bounds of story - return null
     */
    public String current() {
        if (index >= 0 && index < collection.size()) {
            return collection.get(index);
        }
        return null;
    }

    /**
     * set iterator after Last String in story
     */
    public void setLast() {
        this.index = this.collection.size();
    }

    /**
     * set Iterator before First String in Story
     */
    public void setFirst() {
        this.index = -1;
    }
}
