package main.structures;

import static java.lang.Math.max;

public class Module {
    private final float moveTime;
    private final int pickTimeFlat;
    private final int pickTimeMultiplayer;
    private final char type;

    public Module(char type){
        this.type = type;
        switch (type) {
            case 'H' -> {
                moveTime = 0.5f;
                pickTimeFlat = 4;
                pickTimeMultiplayer = 3;
            }
            case 'B' -> {
                moveTime = 1f;
                pickTimeFlat = 2;
                pickTimeMultiplayer = 2;
            }
            case 'S' -> {
                moveTime = 2f;
                pickTimeFlat = 1;
                pickTimeMultiplayer = 1;
            }
            case 'O' -> {
                moveTime = -1;
                pickTimeFlat = -1;
                pickTimeMultiplayer = -1;
            }
            default -> {
                moveTime = 0;
                pickTimeFlat = 0;
                pickTimeMultiplayer = 0;
                System.err.println("Invalid module type");
                System.exit(1);
            }
        }
    }

    public float getMoveTime() {return moveTime;}
    public int getPickTimeFlat() {return pickTimeFlat;}
    public int getPickTimeMultiplayer() {return pickTimeMultiplayer;}
    public char getType(){return type;}
    
    public boolean canMoveTo(){
        return moveTime>0;
    }
    public float timeOfTravelTo(Module m) {
        if(!m.canMoveTo()||!this.canMoveTo()){
            System.err.println("Invalid travel connection");
            System.exit(2);
        }
        return max(m.moveTime, this.moveTime);
    }
    public int timeOfPick(int layer){
        return pickTimeFlat+pickTimeMultiplayer*layer;
    }
}
