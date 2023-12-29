import java.io.IOException;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) throws IOException {
        String filename = "torus.txt";
        MapCreator mp = new MapCreator(filename);
        int simLength = mp.getLength();
        CellManager cm = new CellManager(mp.getMap(), simLength);
        cm.showMap();
        System.out.println(cm.CountNeighbours(mp.getMap(), 0, 0));

        for(int i = 0; i <= 5; i++) {
            char[][] newMap = cm.newIteration(mp.getMap());
            cm.setMap(newMap);
            cm.showMap();
        }
    }
}
