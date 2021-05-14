package lab15;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileReaderRunnable implements Runnable{
    private final List<String> textAccumulator;
    private String filename;

    public FileReaderRunnable(String filename) {
        this.filename = filename;
        textAccumulator = new ArrayList<>();
    }

    public FileReaderRunnable(String filename, List<String> textAccumulator) {
        this.filename = filename;
        this.textAccumulator = textAccumulator;
    }
    @Override
    public void run() {
        try(BufferedReader in = new BufferedReader(new FileReader(filename))) {
            String line;
            while((line = in.readLine()) != null) {
                synchronized (textAccumulator) {
                    textAccumulator.add(line);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
