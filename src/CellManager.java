import java.util.concurrent.*;

public class CellManager implements Runnable {

    private char[][] map;
    private int rows;
    private int columns;
    private int simLength;

    public CellManager(char[][] map, int simLength) {
        this.map = map;
        this.rows = map.length;
        this.columns = map[0].length;
        this.simLength = simLength;
    }


    @Override
    public void run() {

    }

    public Integer CountNeighbours(char[][] map, int row, int col) {
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
                if (map[x_toCheck][y_toCheck] == '1') count++;
            }
        }
        return count;
    }

    public char[][] newIteration (char[][] map){
        char [][] newMap = map;
        for (int i = 0; i <= map.length - 1; i++){
            for(int j = 0; j <= map[0].length - 1; j++){
                int neighbours = CountNeighbours(map, i , j);

                if (map[i][j] == '_' && neighbours == 3){
                    newMap[i][j] = '1';
                } else if (map[i][j] == '1' && (neighbours == 2 || neighbours == 3)){
                    newMap [i][j] = '1';
                }
                else newMap [i][j] = '_';
            }
        }
    return newMap;
    }


    public void showMap() {
        for (int i = 0; i <map.length; i++) {
            for (int j = 0; j<map[0].length; j++) {
                System.out.print(map[i][j]);
            }
            System.out.println(" ");
        }
        System.out.println(" ");
    }

    public void runProgram(char[][] map, int simLength){
        for (int i = 0; i<simLength; i++){
            char[][] newMap = newIteration(map);
            map = newMap;
            showMap();
        }
    }

    public void setMap(char[][] map) {
        this.map = map;
    }
}
