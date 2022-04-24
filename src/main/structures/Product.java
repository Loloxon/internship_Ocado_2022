package main.structures;

import main.assistants.Cords;

public class Product{
    private final String name;
    private final int x;
    private final int y;
    private final int layer;
    public Product(String name, int x, int y, int layer) {
        this.name = name;
        this.x = x;
        this.y = y;
        this.layer = layer;
    }
    public String getName() {
        return name;
    }
    public int getLayer() {
        return layer;
    }
    public Cords getCords(){
        return new Cords(x, y);
    }
}
