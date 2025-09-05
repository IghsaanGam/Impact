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
        int size = input.size();
        int counter=0;
        ArrayList<Integer> inputArray = new ArrayList<>(input);
        String range ="";
        StringBuilder result = new StringBuilder();

        while(counter<size){
            int currentNumber = inputArray.get(counter);
            int nextNumber = inputArray.get(counter+1);

            boolean isSequential = isSequential(currentNumber, nextNumber);

            if(isSequential && range.isEmpty()){
                range = currentNumber + "-" + nextNumber;
                counter++;
                if(counter==size-1){
                    result.append(range);
                    break;
                }
            }
            else if(isSequential && !range.isEmpty()){
                range = range.split("-")[0] + "-" + nextNumber;
                counter++;
                if(counter==size-1){
                    result.append(range);
                    break;
                }
            }
            else if(!isSequential && range.isEmpty()){
                counter++;
                if(counter==size-1){
                    result.append(currentNumber).append(", ").append(nextNumber);
                    break;
                }
                result.append(currentNumber).append(", ");
            }
            else if(!isSequential && !range.isEmpty()){
                counter++;
                if(counter==size-1){
                    result.append(range).append(", ").append(nextNumber);
                    break;
                }
                result.append(range).append(", ");
                range="";
            }
        }
        return result.toString();
    }

    private boolean isSequential(int currentNumber, int nextNumber){
        return currentNumber+1==nextNumber;
    }
}