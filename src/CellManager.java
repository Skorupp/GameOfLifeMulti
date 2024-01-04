
import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.*;

public class CellManager  {

    private int[][] map;
    private int[][] newMap;
    private int rows;
    private int cols;
    private int NumThreads;
    private int SimLength;

    public CellManager(int[][] map, int NumThreads, int SimLength){
        this.map = map;
        this.NumThreads = NumThreads;
        this.rows = map.length;
        this.cols = map[0].length;
        this.SimLength = SimLength;
    }

    public Integer CountNeighbours(int row, int col) {
        int count = 0;
        int x_toCheck;
        int y_toCheck;
        boolean xSet = false;
        boolean ySet = false;
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                x_toCheck = 0;
                y_toCheck = 0;
                if (i == 0 && j == 0) continue;
                if (row + i < 0) {
                    x_toCheck = map.length - 1;
                    xSet = true;
                }
                if (row + i >= map.length) {
                    x_toCheck = 0;
                    xSet = true;
                }
                if (col + j < 0) {
                    y_toCheck = map[0].length - 1;
                    ySet = true;
                }
                if (col + j >= map[0].length) {
                    y_toCheck = 0;
                    ySet = true;
                }
                if (!xSet) {
                    x_toCheck = row + i;
                }
                if (!ySet) {
                    y_toCheck = col + j;
                }
                ySet = false;
                xSet = false;
                if (map[x_toCheck][y_toCheck] == 1) count++;
            }
        }
        return count;
    }

    public void newGen () throws InterruptedException {
        newMap = new int[rows][cols];
        CyclicBarrier barrier = new CyclicBarrier(NumThreads);
        ArrayList<Thread> threadList = new ArrayList<>();
        for (int id = 0; id < NumThreads; id++) {
            int local_col_start = StartingCol(id);
            int local_col_end = EndingCol(id);
            Thread t = new Thread(() -> {
                for (int i = 0; i < rows; i++) {
                    for (int j = local_col_start; j <= local_col_end; j++) {
                        evNewValue(i, j);
                    }
                    try {
                        barrier.await();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    } catch (BrokenBarrierException e) {
                        throw new RuntimeException(e);
                    }}
            });
            t.start();
            threadList.add(t);
        }
        for (Thread t: threadList){
            t.join();
        }
        setMap(newMap);
    }

    public void evNewValue (int row, int col){
        int neighbours = CountNeighbours(row, col);
            if (map[row][col] == 1 && (neighbours == 2 || neighbours == 3)){
                newMap[row][col]=1;
            }
            else if(map[row][col] == 0 && neighbours == 3){
                newMap[row][col] =1;
            }
            else newMap[row][col] =0;
    }

    public void showMap() {
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j<map[0].length; j++) {
                if (map[i][j] == 1){
                    System.out.print("0");
                }
                else System.out.print("_");
            }
            System.out.println(" ");
        }
        System.out.println(" ");
    }

    public int StartingCol(int id){
        int mod = cols % NumThreads;
        if (id == 0){
            return 0;
        }
        else if(mod > id && id > 0){
            return id * (cols / NumThreads) + id;
        }
        else return id * (cols / NumThreads) + mod;
    }

    public int EndingCol(int id){
        int mod = cols % NumThreads;
        if(id == 0 && mod == 0){
            return (cols/NumThreads)-1;
        }
        else if (id == 0 & mod != 0){
            return (cols/NumThreads);
        }
        else if(mod > id && id > 0){
            return (id + 1) * (cols / NumThreads) + id;
        }
        else return (id + 1) * (cols / NumThreads) + mod - 1;
    }

    public void ShowThreadBorders(){
        for (int i = 0; i < NumThreads; i++){
            int MinBorder = StartingCol(i);
            int TopBorder = EndingCol(i);
            System.out.println("tid: " + i + " rows (0:" + (rows-1)+ ") cols: (" + MinBorder +":" + TopBorder + ") (" + (TopBorder-MinBorder+1) + ")");
        }
    }

    public void runProgram() throws InterruptedException {
        showMap();
        for (int i = 0; i<SimLength; i++){
            newGen();
            Thread.sleep(1000);
            showMap();


        }
        System.out.println("Stan koncowy:");
        showMap();
    }

    public void setMap(int[][] map) {
        this.map = map;
    }

    public void setNewMap(int[][] newMap) {
        this.newMap = newMap;
    }
}
