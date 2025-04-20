package com.example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Spliterator;
import java.util.function.Consumer;
import java.util.stream.StreamSupport;

public class StartsWithJIterator implements Spliterator<Integer> {

    private final Spliterator<String> baseSpliterator;
    private final char startChar;

    public StartsWithJIterator(Spliterator<String> baseSpliterator, char startChar) {
        this.baseSpliterator = baseSpliterator;
        this.startChar = startChar;
    }

    @Override
    public boolean tryAdvance(Consumer<? super Integer> action) {
        return baseSpliterator.tryAdvance(s -> {
            if (s.startsWith(String.valueOf(startChar))) {
                action.accept(s.length());
            }
        });
    }

    @Override
    public Spliterator<Integer> trySplit() {
        Spliterator<String> splitBase = this.baseSpliterator.trySplit();
        return (splitBase != null) ? new StartsWithJIterator(splitBase, startChar) : null;
    }

    @Override
    public long estimateSize() {
        return baseSpliterator.estimateSize(); // This is a rough estimate since filtering may remove elements.
    }

    @Override
    public int characteristics() {
        // Since filtering changes the size and we cannot guarantee order, characteristics need to be carefully considered.
        return baseSpliterator.characteristics() & ~(Spliterator.SIZED | Spliterator.SUBSIZED | Spliterator.ORDERED);
    }

    public static void main(String[] args) {
        List<String> list = Arrays.asList("Java", "is ", "advanced", "language");
        StartsWithJIterator spliterator = new StartsWithJIterator(list.spliterator(), 'J');
        StreamSupport.stream(spliterator, false)
                .forEach(System.out::println);
    }
}

