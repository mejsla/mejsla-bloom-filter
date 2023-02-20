package mejsla.bloom.filter;

/**
 * Implementation of a Bloom filter: https://en.wikipedia.org/wiki/Bloom_filter
 * <p>
 * For simplicity this implements only two methods, and is constrained to act on word strings.
 */
public class BloomFilter {

    /**
     * Add a word element to the filter.
     * <p>
     * After this method has been called, {@link #query(String)} must return true for the added word.
     */
    public void add(String word) {
        // TODO: Implement.
    }

    /**
     * Query the filter if it contains a word.
     * <p>
     * If the element has been added to the filter, this method _must_ return true.
     * If the element has not been added to the filter, it might return either true or (probabilistically preferable) false.
     * <p>
     * From the other direction, a query returns either "possibly in set" or "definitely not in set".
     */
    public boolean query(String word) {
        // TODO: Implement.
        return true;
    }

}
