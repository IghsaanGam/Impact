package numberrangesummarizer;

import org.junit.jupiter.api.*;

import java.util.Collection;

public class NumberRangeSummarizerImplTest {

    NumberRangeSummarizer numberRangeSummarizer;

    @BeforeEach
    public void setup(){
        numberRangeSummarizer = new NumberRangeSummarizerImpl();
    }

    @Test
    @DisplayName("Returns a collection of integers, given a string of comma delimited integers")
    public void stringOfOnlyCommaDelimitedNumbersShouldReturnCollectionOfThoseNumbersAsIntegers(){
        String list = "1,3,6,7,8,12,13,14,15,21,22,23,24,31";
        Collection<Integer> integerCollection = numberRangeSummarizer.collect(list);
        Assertions.assertEquals(14, numberRangeSummarizer.collect(list).size());
        Assertions.assertEquals("[1, 3, 6, 7, 8, 12, 13, 14, 15, 21, 22, 23, 24, 31]", integerCollection.toString());
        Assertions.assertFalse(integerCollection.isEmpty());
    }

    @Test
    @DisplayName("Throws an IllegalArgumentException if list contains any non numerical characters")
    public void stringOfListContainingCommaDelimitedCharactersOrStringsShouldThrowIllegalArgumentException(){
        String list1 = "1,3,6,7,8,12,13,14,15,21,22,23,24,31,a,b";
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            numberRangeSummarizer.collect(list1);
        });
        String list2 = "1,3,6,7,8,12,13,14,15,21,22,23,24,31,ab";
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            numberRangeSummarizer.collect(list2);
        });
    }

    @Test
    @DisplayName("Throws a NumberFormatException if list contains any number larger than an integer")
    public void numberFromStringIsTooLarge(){
        String list = "11111111111111111111111111";
        Assertions.assertThrows(NumberFormatException.class, () -> {
            numberRangeSummarizer.collect(list);
        });
    }

    @Test
    @DisplayName("Returns a summarized comma delimited list of numbers as a string with sequential numbers being grouped in ranges")
    public void givenACollectionOfIntegersCreateACommaDelimitedListOfNumbersAsAStringWithConsecutiveNumbersBeingGroupedInARange(){
        String list = "1,3,6,7,8,12,13,14,15,21,22,23,24,31";
        Collection<Integer> integerCollection = numberRangeSummarizer.collect(list);
        Assertions.assertEquals("1, 3, 6-8, 12-15, 21-24, 31", numberRangeSummarizer.summarizeCollection(integerCollection));
    }

}
