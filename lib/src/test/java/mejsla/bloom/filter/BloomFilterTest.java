package mejsla.bloom.filter;

import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.assertTrue;

class BloomFilterTest {

    @Test
    void oneElement() {
        var filter = new BloomFilter();
        var word = "myWord";
        filter.add(word);
        assertTrue(filter.query(word));
    }

    @Test
    void bible() throws Exception {
        var filter = new BloomFilter();

        var reader
                = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream("/bible.txt"), StandardCharsets.UTF_8));
        String line;
        while ((line = reader.readLine()) != null)  {
            for (var word : line.split(" ")) {
                filter.add(word);
            }
        }

        assertTrue(filter.query("watered"));
        assertTrue(filter.query("slaughter"));
        assertTrue(filter.query("Chedorlaomer"));
        assertTrue(filter.query("heaven"));
        assertTrue(filter.query("breastplates"));
    }


}
