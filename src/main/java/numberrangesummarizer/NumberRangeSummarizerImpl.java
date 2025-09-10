package numberrangesummarizer;

import java.util.Arrays;
import java.util.Collection;
import java.util.ArrayList;
import java.util.stream.Collectors;

/**
 * @author Mogamad Ighsaan Gamieldien
 *
 * Class implementation of the NumberRangeSummarizer interface.
 *
 * This class provides methods for taking a comma delimited list of numbers and
 * grouping the numbers into a range when they are sequential.
 */

public class NumberRangeSummarizerImpl implements NumberRangeSummarizer{

    /**
     * Returns a collection of integers given a comma delimited list of integers as a string.
     *
     * @param input - comma delimited list of strings.
     * @throws IllegalArgumentException if input is null or empty, or if a number in the input is too large to be an integer.
     * @return collection - Collection of integers from input.
     */
    @Override
    public Collection<Integer> collect(String input) {

        if(input==null || input.isEmpty()){
            throw new IllegalArgumentException("Input string cannot be null nor empty");
        }

        String[] inputArray = input.split(",");

        for(String item : inputArray){
            if(!item.matches("-?\\d+")){
                throw new IllegalArgumentException("List must contain only numerical values");
            }
        }

        try{
            Collection<Integer> collection = Arrays.stream(inputArray)
                    .map(Integer::parseInt)
                    .collect(Collectors.toCollection(ArrayList::new));

            return collection;
        } catch (NumberFormatException e){
            throw new IllegalArgumentException("A number in the string is too large to be an integer");
        }
    }

    /**
     * Groups sequential numbers, from a collection of integers, in a range.
     *
     * @param input - Collection of integers.
     * @throws IllegalArgumentException if input is null.
     * @return result - string of the input summarized where sequential numbers are grouped in a range.
     */
    @Override
    public String summarizeCollection(Collection<Integer> input) {

        if(input==null){
            throw new IllegalArgumentException("Input cannot be null");
        }

        int size = input.size();
        int counter=0;
        ArrayList<Integer> inputArray = new ArrayList<>(input);
        String range ="";
        StringBuilder result = new StringBuilder();

        //Traverses and summarizes the collection while checking if the current number and next number are sequential
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