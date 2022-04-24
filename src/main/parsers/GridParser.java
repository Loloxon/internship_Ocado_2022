package main.parsers;

import main.structures.Module;
import main.structures.Product;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class GridParser {
    private int X;
    private int Y;
    private int N;
    private Module[][] grid;
    private final ArrayList<Product> products = new ArrayList<>();

    public GridParser(String filename){
        try {
            File file = new File("src\\resources\\" + filename);
            Scanner sc = new Scanner(file);
            int lineCounter = 0;
            while (sc.hasNextLine()) {
                String input = sc.nextLine();
                if(lineCounter == 0) {
                    String[] numbers = input.split(" ");
                    X = Integer.parseInt(numbers[0]);
                    Y = Integer.parseInt(numbers[1]);
                    N = Integer.parseInt(numbers[2]);
                    grid = new Module[Y][X];
                }
                else if(lineCounter <= Y){
                    for(int i=0;i<X;i++){
                        grid[lineCounter-1][i] = new Module(input.charAt(i));
                    }
                }
                else{
                    String[] product = input.split(" ");

                    if(     Integer.parseInt(product[1])<0 || Integer.parseInt(product[1])>=X ||
                            Integer.parseInt(product[2])<0 || Integer.parseInt(product[2])>=Y ||
                            Integer.parseInt(product[3])<0 || Integer.parseInt(product[3])>=N){
                        System.err.println("Product is outside of the grid");
                        System.exit(5);
                    }
                    products.add(new Product(product[0], Integer.parseInt(product[1]),
                            Integer.parseInt(product[2]), Integer.parseInt(product[3])));
                }
                lineCounter++;
            }
        }
        catch (FileNotFoundException e) {
            System.err.println("'Grid' file does not exist");
            System.exit(3);
        }
    }
    public int getX() {return X;}
    public int getY() {
        return Y;
    }
    public int getN() {
        return N;
    }
    public Module[][] getGrid() {return grid;}
    public ArrayList<Product> getProducts() {
        return products;
    }
}
