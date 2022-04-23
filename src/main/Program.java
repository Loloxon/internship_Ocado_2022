package main;

import main.objects.Product;
import main.parsers.GridParser;
import main.parsers.JobParser;

import java.util.ArrayList;

public class Program {
    public static void main(String[] args) {
        GridParser gridParser = new GridParser(args[0]);
        Grid grid = new Grid(gridParser.getX(), gridParser.getY(), gridParser.getGrid());

        ArrayList<Product> products = gridParser.getProducts();
        for (Product product : products) {
            grid.addProduct(product);
        }

        JobParser jobParser = new JobParser(args[1]);
        grid.calculateTime(jobParser.getStart(), jobParser.getEnd(), jobParser.getName());
    }
}
