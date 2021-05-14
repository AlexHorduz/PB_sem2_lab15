package lab15;

import lab15.interfaces.IStringProcessor;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Service {
    public Set<String> testForOneThread() {
        ArrayList<String> text = new ArrayList<>();
        FileReaderRunnable r1 = new FileReaderRunnable("ukr_text1.txt", text);
        FileReaderRunnable r2 = new FileReaderRunnable("ukr_text2.txt", text);
        FileReaderRunnable r3 = new FileReaderRunnable("ukr_text3.txt", text);
        Thread t1 = new Thread(r1);
        Thread t2 = new Thread(r2);
        Thread t3 = new Thread(r3);

        long start = System.nanoTime();
        try {
            t1.start();
            t1.join();
            t2.start();
            t2.join();
            t3.start();
            t3.join();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        long end = System.nanoTime();

        System.out.println("Files read in single thread in " + (end - start) / 1_000_000 + " ms");
        IStringProcessor processor = new MyStringProcessor();
        HashSet<String> setOfRarestWords = new HashSet<>();
        try {
            setOfRarestWords = new HashSet<>(processor.rarestWords(text));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return  setOfRarestWords;
    }

    public Set<String> testForMultipleThreads() {
        ArrayList<String> text = new ArrayList<>();
        FileReaderRunnable r1 = new FileReaderRunnable("ukr_text1.txt", text);
        FileReaderRunnable r2 = new FileReaderRunnable("ukr_text2.txt", text);
        FileReaderRunnable r3 = new FileReaderRunnable("ukr_text3.txt", text);
        Thread t1 = new Thread(r1);
        Thread t2 = new Thread(r2);
        Thread t3 = new Thread(r3);

        long start = System.nanoTime();
        try {
            t1.start();
            t2.start();
            t3.start();
            t1.join();
            t2.join();
            t3.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        long end = System.nanoTime();

        System.out.println("Files read in multiple threads in " + (end - start) / 1_000_000 + " ms");
        IStringProcessor processor = new MyStringProcessor();
        HashSet<String> setOfRarestWords = new HashSet<>();
        try {
            setOfRarestWords = new HashSet<>(processor.rarestWords(text));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return  setOfRarestWords;
    }
}
