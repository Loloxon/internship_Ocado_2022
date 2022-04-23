package test;

import main.parsers.JobParser;
import org.testng.annotations.Test;

import static org.testng.AssertJUnit.assertEquals;

public class JobParserTest {
    @Test
    public void GridParserTest(){
        JobParser jobParser = new JobParser("job-1.txt");

        assertEquals(1, jobParser.getStart().x);
        assertEquals(1, jobParser.getStart().y);

        assertEquals(0, jobParser.getEnd().x);
        assertEquals(0, jobParser.getEnd().y);

        assertEquals("P1", jobParser.getName());
    }
}
