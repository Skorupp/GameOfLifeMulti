import java.io.IOException;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) throws IOException {
        String filename = "dick.txt";
        MapCreator mp = new MapCreator(filename);
        int simLength = mp.getLength();
        char[][] map = mp.getMap();
        CellManager cm = new CellManager(map, simLength);
        cm.showMap();
        System.out.println(cm.CountNeighbours(mp.getMap(), 0, 0));
        cm.runProgram(map, simLength);
    }
}
