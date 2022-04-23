package main;

import main.assistants.Solver;
import main.objects.Grid;
import main.parsers.GridParser;
import main.parsers.JobParser;

public class Program {
    public static void main(String[] args) {
        GridParser gridParser = new GridParser(args[0]);
        JobParser jobParser = new JobParser(args[1]);

        Grid grid = new Grid(gridParser.getGrid(), gridParser.getProducts());

        Solver solver = new Solver(grid);
        solver.setData(jobParser.getStart(), jobParser.getEnd(), jobParser.getName());

        solver.calculateTime();
    }
}
