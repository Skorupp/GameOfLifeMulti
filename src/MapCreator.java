import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;
public class MapCreator {

    private int[][] map;

    private int length;

        public MapCreator(String filename) throws IOException {
            FileReader fr = new FileReader(filename);
            BufferedReader br = new BufferedReader(fr);
            int x_size;
            int y_size;
            x_size = Integer.parseInt(br.readLine());
            y_size = Integer.parseInt(br.readLine());
            this.length = Integer.parseInt(br.readLine());

            int newMap[][] = new int[x_size][y_size];
            for (int i = 0; i<x_size; i++)
            {
                for (int j = 0; j<y_size; j++)
                    newMap[i][j] = 0;
            }
            int liveCells = Integer.parseInt(br.readLine());
            for (int i = liveCells; i > 0; i--){
                String line = br.readLine();
                String[] res = line.split(" ");
                int y = Integer.parseInt(res[0]);
                int x = Integer.parseInt(res[1]);
                newMap[x][y] = 1;
            }

            this.map = newMap;

        }

    public int[][] getMap() {
        return map;
    }

    public int getLength() {
        return length;
    }



}
