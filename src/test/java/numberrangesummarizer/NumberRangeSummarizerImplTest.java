package numberrangesummarizer;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Collection;

/**
 * @author Mogamad Ighsaan Gamieldien
 *
 * Unit tests for the methods implemented in the NumberRangeSummarizerImpl class.
 */

public class NumberRangeSummarizerImplTest {

    NumberRangeSummarizer numberRangeSummarizer;

    @BeforeEach
    public void setup(){
        numberRangeSummarizer = new NumberRangeSummarizerImpl();
    }

    @Test
    @DisplayName("Given a string of comma delimited integers, returns a collection of those integers")
    public void givenCommaDelimitedStringOfIntegers_whenCollect_thenReturnCollectionOfIntegers(){
        String input = "1,3,6,7,8,12,13,14,15,21,22,23,24,31";
        Collection<Integer> integerCollection = numberRangeSummarizer.collect(input);
        Assertions.assertEquals(14, numberRangeSummarizer.collect(input).size());
        Assertions.assertEquals("[1, 3, 6, 7, 8, 12, 13, 14, 15, 21, 22, 23, 24, 31]", integerCollection.toString());
        Assertions.assertFalse(integerCollection.isEmpty());
    }

    @Test
    @DisplayName("Throws an IllegalArgumentException if input is null or empty")
    public void givenNullOrEmptyString_whenCollect_thenThrowIllegalArgumentException(){
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            numberRangeSummarizer.collect(null);
        });
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            numberRangeSummarizer.collect("");
        });
    }

    @ParameterizedTest
    @ValueSource(strings = {"1,3,6,7,a,b", "a,ab"})
    @DisplayName("Throws an IllegalArgumentException if comma delimited list contains any non numerical characters")
    public void givenCommaDelimitedListContainingNonNumericalValues_whenCollect_thenThrowIllegalArgumentException(String input){
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            numberRangeSummarizer.collect(input);
        });
    }

    @Test
    @DisplayName("Throws an IllegalArgumentException if comma delimited list contains any number larger than an integer")
    public void givenCommaDelimitedListContainingNonIntegerNumber_whenCollect_thenThrowIllegalArgumentException(){
        String input = "11111111111111111111111111,1";
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            numberRangeSummarizer.collect(input);
        });
    }

    @Test
    @DisplayName("Given a collection of integers, returns a summarized comma delimited list of numbers as a string with sequential numbers being grouped in ranges")
    public void givenCollectionOfIntegers_whenSummarizeCollection_thenReturnSummarizedCommaDelimitedListAsString(){
        String input = "1,3,6,7,8,12,13,14,15,21,22,23,24,31";
        Collection<Integer> integerCollection = numberRangeSummarizer.collect(input);
        Assertions.assertEquals("1, 3, 6-8, 12-15, 21-24, 31", numberRangeSummarizer.summarizeCollection(integerCollection));
    }

    @Test
    @DisplayName("Throws an IllegalArgumentException if input is null")
    public void givenNullCollection_whenSummarizeCollection_thenThrowIllegalArgumentException(){
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            numberRangeSummarizer.summarizeCollection(null);
        });
    }

}
