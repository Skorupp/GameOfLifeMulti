import java.io.IOException;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) throws IOException {
        String filename = "example.txt";
        MapCreator mp = new MapCreator(filename);
        int simLength = mp.getLength();
        int[][] map = mp.getMap();
        CellManager cm = new CellManager(map, simLength);
        cm.showMap(map);
        cm.runProgram(map, simLength);
    }
}
