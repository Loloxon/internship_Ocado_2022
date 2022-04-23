package main.assistants;

public class Cords {
    public final int x;
    public final int y;
    public Cords(int x, int y){
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return x + " " + y;
    }
}
