package mejsla.bloom.filter;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.HashSet;
import java.util.Set;

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
