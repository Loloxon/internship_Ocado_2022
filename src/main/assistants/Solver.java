package main.assistants;

import main.objects.Grid;
import main.objects.Module;
import main.objects.Product;
import org.testng.internal.collections.Pair;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;
import java.util.PriorityQueue;

public class Solver {
    private Cords start;
    private Cords end;
    private String productName;
    private final Grid grid;
    private final int X;
    private final int Y;

    private float[][] distancesFromStart; //without picking
    private float[][] distancesFromEnd; //without picking

    private ArrayList<Cords> path;
    private float bestTime;

    public Solver(Grid grid){
        this.grid = grid;
        this.X = grid.getX();
        this.Y = grid.getY();
        distancesFromStart = new float[Y][X];
        distancesFromEnd = new float[Y][X];
    }
    public Cords getStart() {return start;}
    public void setStart(Cords start) {this.start = start;}
    public Cords getEnd() {return end;}
    public void setEnd(Cords end) {this.end = end;}
    public String getProductName() {return productName;}
    public void setProductName(String productName) {this.productName = productName;}
    public void setData(Cords start, Cords end, String productName){
        setStart(start);
        setEnd(end);
        setProductName(productName);
    }
    public ArrayList<Cords> getPath() {return path;}
    public float getBestTime() {return bestTime;}

    private void generatePath(Cords[][] prevFromStart, Cords product, Cords[][] prevFromEnd){
        ArrayList<Cords> tmp = new ArrayList<>();
        int x=product.x, y=product.y;
        while(prevFromStart[y][x].x!=-1){
            tmp.add(prevFromStart[y][x]);
            int tmpX = prevFromStart[y][x].x;
            y = prevFromStart[y][x].y;
            x=tmpX;
        }
        Collections.reverse(tmp);
        path = new ArrayList<>(tmp);

        path.add(product);

        tmp.clear();
        x=product.x;
        y=product.y;
        while(prevFromEnd[y][x].x!=-1){
            tmp.add(prevFromEnd[y][x]);
            int tmpX = prevFromEnd[y][x].x;
            y = prevFromEnd[y][x].y;
            x=tmpX;
        }
        path.addAll(tmp);
    }
    public void printOutput(){
        System.out.println(path.size()-1);
        System.out.println(bestTime);
        for (Cords cords : path) {
            System.out.println(cords.toString());
        }
    }
    public String getOutput(){
        StringBuilder S = new StringBuilder();
        S.append(path.size() - 1);
        S.append("\n");
        S.append(bestTime);
        S.append("\n");
        for (Cords cords : path) {
            S.append(cords.toString());
            S.append("\n");
        }
        return S.toString();
    }
    public void calculateTime() {
        Cords[][] prevFromStart;
        Cords[][] prevFromEnd;
        Pair<float[][],Cords[][]> tmp;
        tmp = dijkstra(distancesFromStart, start);
        distancesFromStart = tmp.first();
        prevFromStart = tmp.second();
        tmp = dijkstra(distancesFromEnd, end);
        distancesFromEnd = tmp.first();
        prevFromEnd = tmp.second();
        bestTime = -1;
        int bestProduct = -1;
        ArrayList<Product> products = grid.getProducts();
        for(int i=0;i<products.size();i++){
            if(Objects.equals(productName, products.get(i).getName())) {
                Cords p = products.get(i).getCords();
                float currBestTime = distancesFromStart[p.y][p.x] + distancesFromEnd[p.y][p.x] +
                        grid.at(p).timeOfPick(products.get(i).getLayer());
                if (bestTime == -1 || currBestTime < bestTime) {
                    bestTime = currBestTime;
                    bestProduct = i;
                }
            }
        }
        generatePath(prevFromStart, products.get(bestProduct).getCords(), prevFromEnd);
    }
    private Pair<float[][],Cords[][]> dijkstra(float[][] distances, Cords source) {
        Cords[][] prev = new Cords[Y][X];
        PriorityQueue<Pair<Float, Cords>> Q = new PriorityQueue<>((a, b) -> (int)(a.first() - b.first()));
        int maxDist = X * Y * 2 + 5;
        for(int i=0;i<X;i++){
            for(int j=0;j<Y;j++){
                distances[j][i] = maxDist;
            }
        }
        prev[source.y][source.x] = new Cords(-1,-1);
        distances[source.y][source.x] = 0;
        Q.add(new Pair<>(distances[source.y][source.x], new Cords(source.x, source.y)));

        while(!Q.isEmpty()){
            Pair<Float, Cords> tmpP = Q.poll();
            Cords currentCords = tmpP.second();
            ArrayList<Cords> neighboursCords = grid.getNeighbours(currentCords);

            for (Cords neighbourCords : neighboursCords) {
                Module neighbour = grid.at(neighbourCords);
                if (neighbour.canMoveTo()) {
                    float newDistance = distances[currentCords.y][currentCords.x] +
                            grid.at(currentCords).timeOfTravelTo(neighbour);
                    if (newDistance < distances[neighbourCords.y][neighbourCords.x]) {
                        distances[neighbourCords.y][neighbourCords.x] = newDistance;
                        Q.add(new Pair<>(newDistance, neighbourCords));
                        prev[neighbourCords.y][neighbourCords.x] = currentCords;
                    }
                }
            }
        }
        return new Pair<>(distances,prev);
    }
}
