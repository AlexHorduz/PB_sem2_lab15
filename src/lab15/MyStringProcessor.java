package lab15;



import lab15.interfaces.IStringProcessor;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class MyStringProcessor implements IStringProcessor {
    public List<String> rarestWords(List<String> text) {
        text = text.stream()
                .flatMap(line -> Stream.of(line.split("[^\\wa-яА-ЯіІєЄґҐїЇгГ']+")))
                .collect(Collectors.toList());

        HashMap<String, Integer> counter = new HashMap<>();
        for (String s : text) {
            counter.put(s, counter.getOrDefault(s, 0) + 1);
        }
        int minValue = -1;
        List<String> minCountStrings = new ArrayList<>();
        for (HashMap.Entry<String, Integer> entry : counter.entrySet()) {
            if (entry.getValue() < minValue || minValue == -1) {
                minValue = entry.getValue();
                minCountStrings = new ArrayList<>();
                minCountStrings.add(entry.getKey());
            } else if (entry.getValue() == minValue) {
                minCountStrings.add(entry.getKey());
            }
        }
        return minCountStrings;
    }
}
