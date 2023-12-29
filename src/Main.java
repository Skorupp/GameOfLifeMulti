import java.io.IOException;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) throws IOException {
        String filename = "example.txt";
        MapCreator mp = new MapCreator(filename);
        mp.showMap();
        int simLength = mp.getLength();
    }
}
