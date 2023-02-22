# mejsla-bloom-filter

En liten laboration i att implementera ett [Bloom filter](https://en.wikipedia.org/wiki/Bloom_filter).

# Öppna projektet

Projektet är ett standardprojekt i gradle och kan öppnas i t.ex. IntelliJ som vanligt projekt.

Innehåller tre klasser:

- [BloomFilter](lib/src/main/java/mejsla/bloom/filter/BloomFilter.java): En enkel implementation av ett Bloom filter med
  stub metoder att implementera.
- [BloomFilterTest](lib/src/test/java/mejsla/bloom/filter/BloomFilterTest.java): Enkelt, begränsat test av filtret.
- [Benchmark](lib/src/main/java/mejsla/bloom/filter/Benchmark.java): En körbar `main()` metod som ger feedback på false
  hit ratio samt exekveringstid.

# Tips
Klassen [java.util.BitSet](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/BitSet.html) finns tillgänglig för att representera bitset.
