package numberrangesummarizer;

import java.util.Arrays;
import java.util.Collection;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class NumberRangeSummarizerImpl implements NumberRangeSummarizer{

    @Override
    public Collection<Integer> collect(String input) {
        String[] inputArray = input.split(",");

        for(String item : inputArray){
            if(!item.matches("-?\\d+")){
                throw new IllegalArgumentException("List must contain only numerical values");
            }
        }

        try{
            return Arrays.stream(inputArray)
                    .map(Integer::parseInt)
                    .collect(Collectors.toCollection(ArrayList::new));
        } catch (NumberFormatException e){
            throw new IllegalArgumentException("A number in the string is too large to be an integer");
        }
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

            boolean isSequential = currentNumber+1==nextNumber;
            boolean isRangeEmpty = range.isEmpty();

            if(isSequential && isRangeEmpty){
                range = currentNumber + "-" + nextNumber;
                counter++;
                if(counter==size-1){
                    result.append(range);
                    break;
                }
            }
            else if(isSequential && !isRangeEmpty){
                range = range.split("-")[0] + "-" + nextNumber;
                counter++;
                if(counter==size-1){
                    result.append(range);
                    break;
                }
            }
            else if(!isSequential && isRangeEmpty){
                counter++;
                if(counter==size-1){
                    result.append(currentNumber).append(", ").append(nextNumber);
                    break;
                }
                result.append(currentNumber).append(", ");
            }
            else if(!isSequential && !isRangeEmpty){
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
}