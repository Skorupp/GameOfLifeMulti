import java.util.concurrent.*;

public class CellManager implements Runnable{

    private char[][] map;
    private char[][] newMap;
    private int rows;
    private int columns;
    private int simLength;

    public CellManager (char [][] map, int simLength){
        this.map = map;
        this.rows = map.length;
        this.columns = map[0].length;
        this.newMap = map;
        this.simLength = simLength;
    }


    @Override
    public void run() {

    }

}
