package mejsla.bloom.filter;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class BloomFilterTest {

    @Test
    void oneElementAdditionAndQuery() {
        var filter = new BloomFilter();

        var word = "myWord";

        assertFalse(filter.query(word));

        filter.add(word);

        assertTrue(filter.query(word));
    }


}
