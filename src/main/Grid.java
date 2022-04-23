package main;

import main.objects.Cords;
import main.objects.Module;
import main.objects.Product;
import org.testng.internal.collections.Pair;

import java.util.*;

public class Grid {
//    private final ArrayList<Module> grid = new ArrayList<>();
    private final Module[][] grid;
    private final int X;
    private final int Y;
    private final ArrayList<Product> products = new ArrayList<>();

    private float[][] distancesFromStart; //without picking
    private float[][] distancesFromEnd; //without picking

    public Grid(int X, int Y, Module[][] grid){
        this.grid = grid;
        this.X = X;
        this.Y = Y;
        int maxDist = X * Y * 2 + 5;
        distancesFromStart = new float[Y][X];
        distancesFromEnd = new float[Y][X];
        for(int i=0;i<X;i++){
            for(int j=0;j<Y;j++){
                distancesFromStart[j][i] = maxDist;
                distancesFromEnd[j][i] = maxDist;
            }
        }

    }
    public void addProduct(Product p){
        products.add(p);
    }

    public ArrayList<Cords> getNeighbours(int x, int y){
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
    public ArrayList<Cords> generatePath(Cords[][] prevFromStart, Cords product, Cords[][] prevFromEnd){
        ArrayList<Cords> tmp = new ArrayList<>();
//        path.add(start);
        int x=product.x, y=product.y;
        while(prevFromStart[y][x].x!=-1){
            tmp.add(prevFromStart[y][x]);
            int tmpx = prevFromStart[y][x].x;
            int tmpy = prevFromStart[y][x].y;
            x=tmpx;
            y=tmpy;
        }
        Collections.reverse(tmp);
        ArrayList<Cords> path = new ArrayList<>(tmp);

        path.add(product);

        tmp.clear();
        x=product.x;
        y=product.y;
        while(prevFromEnd[y][x].x!=-1){
            tmp.add(prevFromEnd[y][x]);
            int tmpx = prevFromEnd[y][x].x;
            int tmpy = prevFromEnd[y][x].y;
            x=tmpx;
            y=tmpy;
        }
        path.addAll(tmp);

        return path;
    }
    public void calculateTime(Cords start, Cords end, String productName) {
        Cords[][] prevFromStart;
        Cords[][] prevFromEnd;
        Pair<float[][],Cords[][]> tmp;
        tmp = dijkstra(distancesFromStart, start);
        distancesFromStart = tmp.first();
        prevFromStart = tmp.second();
        tmp = dijkstra(distancesFromEnd, end);
        distancesFromEnd = tmp.first();
        prevFromEnd = tmp.second();
        float bestTime = -1;
        int bestProduct = -1;
        for(int i=0;i<products.size();i++){
            if(Objects.equals(productName, products.get(i).getName())) {
                Cords p = products.get(i).getCords();
//            int idx = p.x+p.y*X;
                float currBestTime = distancesFromStart[p.y][p.x] + distancesFromEnd[p.y][p.x] +
                        grid[p.y][p.x].timeOfPick(products.get(i).getLayer());
                if (bestTime == -1 || currBestTime < bestTime) {
                    bestTime = currBestTime;
                    bestProduct = i;
                }
            }
        }
        ArrayList<Cords> path = generatePath(prevFromStart, products.get(bestProduct).getCords(), prevFromEnd);

        System.out.println(path.size()-1);
        System.out.println(bestTime);
        for (Cords cords : path) {
            System.out.println(cords.toString());
        }
    }
    public Pair<float[][],Cords[][]> dijkstra(float[][] distances, Cords source) {
        Cords[][] prev = new Cords[Y][X];
        PriorityQueue<Pair<Float, Cords>> Q = new PriorityQueue<>((a,b) -> (int)(a.first() - b.first()));
        prev[source.y][source.x] = new Cords(-1,-1);
        distances[source.y][source.x] = 0;
        Q.add(new Pair(distances[source.y][source.x], new Cords(source.x, source.y)));

        while(!Q.isEmpty()){
            Pair<Float, Cords> tmpP = Q.poll();
            Cords cordsCurr = tmpP.second();
            ArrayList<Cords> cordsNeighbours = this.getNeighbours(cordsCurr.x,cordsCurr.y);

            for (Cords cordsNeighbour : cordsNeighbours) {
                Module neighbour = grid[cordsNeighbour.y][cordsNeighbour.x];
                if (neighbour.canMoveTo()) {
                    float newDist = distances[cordsCurr.y][cordsCurr.x] +
                            grid[cordsCurr.y][cordsCurr.x].timeOfTravelTo(neighbour);
//                    System.out.println(newDist);

                    if (newDist < distances[cordsNeighbour.y][cordsNeighbour.x]) {
                        distances[cordsNeighbour.y][cordsNeighbour.x] = newDist;
                        Q.add(new Pair(distances[cordsNeighbour.y][cordsNeighbour.x], cordsNeighbour));
                        prev[cordsNeighbour.y][cordsNeighbour.x] = cordsCurr;
                    }
                }
            }
        }
        return new Pair(distances,prev);
    }
}
