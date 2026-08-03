package javacollections.list;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ListExample {

    public static void main(String[] args) {
        // List — ordered, allows duplicates, index-based access

        List<String> arrayList = new ArrayList<>(); // resizable array, fast random access
        List<String> linkedList = new LinkedList<>(); // doubly-linked list, fast insert/delete at ends

        arrayList.add("Titan");
        arrayList.add("Fasttrack");
        arrayList.add(0, "Casio");
        System.out.println(arrayList);
        String myName = arrayList.get(1);
        System.out.println(myName);
        arrayList.set(2, "Shiv");
        System.out.println(arrayList);
    }
}
