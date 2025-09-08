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

}
