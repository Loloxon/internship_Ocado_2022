package main.structures;

import main.assistants.Cords;

import java.util.*;

public class Grid {
    private final Module[][] grid;
    private final int X;
    private final int Y;
    private final ArrayList<Product> products = new ArrayList<>();

    public Grid(Module[][] grid, ArrayList<Product> products){
        this.grid = grid;
        this.X = grid[0].length;
        this.Y = grid.length;
        this.products.addAll(products);
    }

    public ArrayList<Cords> getNeighbours(Cords cords){
        int x = cords.x;
        int y = cords.y;
        ArrayList<Cords> tmp = new ArrayList<>();
        if(x>0){
            tmp.add(new Cords(x-1,y));
        }
        if(y>0){
            tmp.add(new Cords(x,y-1));
        }
        if(x<X-1){
            tmp.add(new Cords(x+1,y));
        }
        if(y<Y-1){
            tmp.add(new Cords(x,y+1));
        }
        return tmp;
    }
    public Module[][] getGrid() {return grid;}
    public int getX() {return X;}
    public int getY() {return Y;}
    public ArrayList<Product> getProducts() {return products;}

    public Module at(Cords cords){return grid[cords.y][cords.x];}
}
