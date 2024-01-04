import java.io.IOException;

public class Main{

    public static void main(String[] args) throws IOException, InterruptedException {
        String filename = args[0];
        int NumThreads = Integer.parseInt(args[1]);
        MapCreator mp = new MapCreator(filename);
        int SimLength = mp.getLength();
        System.out.println("#Column-based partitioning");
        System.out.println("Dlugosc symulacji: " + SimLength);
        int[][] map = mp.getMap();
        CellManager cm = new CellManager(map, NumThreads, SimLength);
        cm.ShowThreadBorders();
        System.out.println("Stan poczatkowy");
        cm.runProgram();
        }
}
