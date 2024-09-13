package pr.iceworld.fernando.java21.java21_basic;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.SequencedCollection;

class OO11_SequencedCollectionTest {

    @Test
    void ordering() throws Exception {
        var list = LinkedHashSet.<String>newLinkedHashSet(100);
        if (list instanceof SequencedCollection<String> sequencedCollection) {
            sequencedCollection.add("ciao");
            sequencedCollection.add("hola");
            sequencedCollection.add("ni hao");
            sequencedCollection.add("salut");
            sequencedCollection.add("hello");
            sequencedCollection.addFirst("ola"); //<1>
            Assertions.assertEquals(sequencedCollection.getFirst(), "ola"); // ②
        }
    }

    @Test
    void getFirstOrLast() {
        List<Integer> list = new ArrayList<Integer>();
        list.add(0);
        list.add(1);
        list.add(2);
        // Fetch the first element (element at index 0)
        int firstElement = list.get(0);
        // Fetch the last element
        int lastElement = list.get(list.size() - 1);
        Assertions.assertEquals(0, firstElement);
        Assertions.assertEquals(2, lastElement);
    }

    @Test
    void getFirstOrLast21() {
        List<Integer> list = new ArrayList<Integer>();
        list.add(0);
        list.add(1);
        list.add(2);
        // Fetch the first element (element at index 0)
        int firstElement = list.getFirst();
        // Fetch the last element
        int lastElement = list.getLast();
        Assertions.assertEquals(0, firstElement);
        Assertions.assertEquals(2, lastElement);
    }
}