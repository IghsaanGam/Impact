package numberrangesummarizer;

import java.util.Arrays;
import java.util.Collection;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class NumberRangeSummarizerImpl implements NumberRangeSummarizer{

    @Override
    public Collection<Integer> collect(String input) {
        String[] inputArray = input.split(",");

        Collection<Integer> collection = Arrays.stream(inputArray)
                .map(Integer::parseInt)
                .collect(Collectors.toCollection(ArrayList::new));

        return collection;
    }

    @Override
    public String summarizeCollection(Collection<Integer> input) {
        return "";
    }
}