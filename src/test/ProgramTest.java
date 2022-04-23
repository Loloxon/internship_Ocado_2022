package test;

import main.assistants.Solver;
import main.objects.Grid;
import main.parsers.GridParser;
import main.parsers.JobParser;
import org.testng.annotations.Test;

import java.io.IOException;

import static org.testng.AssertJUnit.assertEquals;

public class ProgramTest {
    @Test
    public void ProgramInput1Test() throws IOException {
        GridParser gridParser = new GridParser("grid-1.txt");
        JobParser jobParser = new JobParser("job-1.txt");

        Grid grid = new Grid(gridParser.getGrid(), gridParser.getProducts());

        Solver solver = new Solver(grid);
        solver.setData(jobParser.getStart(), jobParser.getEnd(), jobParser.getName());

        solver.calculateTime();
//        String expectedOutput = new String(Files.readAllBytes(Paths.get("src\\resources\\results-1.txt")));
//        assertEquals(expectedOutput, solver.getOutput());     not working because there are many optimal paths
        assertEquals(8, solver.getPath().size()-1);
        assertEquals(10.5f, solver.getBestTime());
    }
    @Test
    public void ProgramInput2Test() throws IOException {
        GridParser gridParser = new GridParser("grid-2.txt");
        JobParser jobParser = new JobParser("job-2.txt");

        Grid grid = new Grid(gridParser.getGrid(), gridParser.getProducts());

        Solver solver = new Solver(grid);
        solver.setData(jobParser.getStart(), jobParser.getEnd(), jobParser.getName());

        solver.calculateTime();
//        String expectedOutput = new String(Files.readAllBytes(Paths.get("src\\resources\\results-2.txt")));
//        assertEquals(expectedOutput, solver.getOutput());     not working because there are many optimal paths
        assertEquals(81, solver.getPath().size()-1);
        assertEquals(82.0f, solver.getBestTime());
    }
}
