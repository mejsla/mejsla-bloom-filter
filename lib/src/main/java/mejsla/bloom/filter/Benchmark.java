package mejsla.bloom.filter;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.HashSet;
import java.util.Set;

/**
 * A simple benchmark of {@link BloomFilter}.
 * <p>
 * It reads in around 100k words, then validates that all these words exist in the filter.
 * <p>
 * Then, it reads in around 7k words not existing in the previous set, and records and prints the
 * false positive hit ratio - the ratio between the occurrences where the bloom filter responded
 * "possibly in set", and all words queried for (which are known to not be in the set).
 * <p>
 * It also records and prints the elapsed time, as a simple benchmark. Note that only runtime is
 * benchmark here and not memory consumption.
 */
public class Benchmark {

    public static void main(String[] args) throws Exception {
        var bibleWords = readWords("bible.txt");
        var nonBibleWords = readWords("words-not-in-bible.txt").stream().toArray(String[]::new);

        var filter = new BloomFilter();

        for (var word : bibleWords) {
            filter.add(word);
        }

        for (var word : bibleWords) {
            if (!filter.query(word)) {
                System.err.println("Word should exist in filter: " + word);
                System.exit(1);
            }
        }

        var wordCount = 0;
        var incorrectCount = 0;
        for (var word : nonBibleWords) {
            wordCount++;
            if (filter.query(word)) {
                incorrectCount++;
            }
        }
        var falsePositiveRatio = incorrectCount / (double) wordCount;

        var start = System.nanoTime();
        for (var run = 0; run < 1000; run++) {
            for (var word : nonBibleWords) {
                filter.query(word);
            }
        }
        var elapsedMs = (System.nanoTime() - start) * 0.000001;

        System.out.println("False positive ratio: " + falsePositiveRatio);
        System.out.println("Runtime: " + elapsedMs + " ms");
    }

    private static Set<String> readWords(String resource) throws Exception {
        var words = new HashSet<String>();
        var reader
                = new BufferedReader(new InputStreamReader(Benchmark.class.getResourceAsStream("/" + resource), StandardCharsets.UTF_8));
        String line;
        while ((line = reader.readLine()) != null) {
            for (var word : line.split(" ")) {
                words.add(word);
            }
        }
        return words;
    }

}
