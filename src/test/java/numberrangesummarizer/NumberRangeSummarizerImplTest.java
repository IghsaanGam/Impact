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
    public void stringOfOnlyCommaDelimitedNumbersShouldReturnCollectionOfThoseNumbersAsIntegers(){
        String list = "1,3,6,7,8,12,13,14,15,21,22,23,24,31";
        Collection<Integer> integerCollection = numberRangeSummarizer.collect(list);
        Assertions.assertEquals(14, numberRangeSummarizer.collect(list).size());
        Assertions.assertEquals("[1, 3, 6, 7, 8, 12, 13, 14, 15, 21, 22, 23, 24, 31]", integerCollection.toString());
        Assertions.assertFalse(integerCollection.isEmpty());
    }

    @Test
    @DisplayName("Throws an IllegalArgumentException if list contains any non numerical characters")
    public void stringOfListContainingCommaDelimitedCharactersAndStringsShouldThrowIllegalArgumentException(){
        String list = "1,3,6,7,8,12,13,14,15,21,22,23,24,31,a,b,qweruiwqeyr1312";
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            numberRangeSummarizer.collect(list);
        });
    }

    @Test
    public void numberFromStringIsTooLarge(){
        String list = "11111111111111111111111111";
        Assertions.assertThrows(NumberFormatException.class, () -> {
            numberRangeSummarizer.collect(list);
        });
    }

}
