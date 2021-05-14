package lab15;

import lab15.interfaces.IStringProcessor;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;
import java.util.function.Function;

public class Main {
    public static void main(String[] args) {
        Service service = new Service();
        HashSet<String> setMultipleThreads = new HashSet<>(service.testForMultipleThreads());
        service.testForMultipleThreads();
        service.testForMultipleThreads();
        HashSet<String> setOneThread = new HashSet<>(service.testForOneThread());
        service.testForOneThread();
        service.testForOneThread();

        if (setOneThread.containsAll(setMultipleThreads)) {
            System.out.println("Results match!");
        } else {
            System.out.println("Results do not match(((");
        }

    }
}
