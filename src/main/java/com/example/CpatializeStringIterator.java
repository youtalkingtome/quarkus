package com.example;
import java.util.Arrays;
import java.util.List;
import java.util.Spliterator;
import java.util.function.Consumer;
import java.util.stream.StreamSupport;

public class CpatializeStringIterator implements Spliterator<String>  {


        private final Spliterator<String> baseSpliterator;

        public CpatializeStringIterator(Spliterator<String> baseSpliterator) {
            this.baseSpliterator = baseSpliterator;
        }

        @Override
        public boolean tryAdvance(Consumer<? super String> action) {
            return baseSpliterator.tryAdvance(s -> action.accept(s.toUpperCase()));
        }

        @Override
        public Spliterator<String> trySplit() {
            Spliterator<String> splitBase = this.baseSpliterator.trySplit();
            return (splitBase != null) ? new CpatializeStringIterator(splitBase) : null;
        }

        @Override
        public long estimateSize() {
            return baseSpliterator.estimateSize();
        }

        @Override
        public int characteristics() {
            return baseSpliterator.characteristics();
        }

        public static void main(String[] args) {
            List<String> list = Arrays.asList("my", "name ", "is ", "vikas");
            CpatializeStringIterator spliterator = new CpatializeStringIterator(list.spliterator());
            StreamSupport.stream(spliterator, false)
                    .forEach(System.out::println);
        }
    }

