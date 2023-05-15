import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;
import java.util.stream.IntStream;

public class LevelGen {
    private final ArrayList<ArrayList<ArrayList<Integer>>> levelMaps;

    public LevelGen() {
        levelMaps = new ArrayList<>(1);
        readLevelMap("src/resources/levelMap.txt");
    }

    public ArrayList<ArrayList<ArrayList<Integer>>> getLevelMap() {
        return levelMaps;
    }

    public void readLevelMap(String fileName) {
        levelMaps.add(new ArrayList<>());
        int i = 0;

        try {
            FileInputStream fStream = new FileInputStream(fileName);
            DataInputStream dStream = new DataInputStream(fStream);
            BufferedReader bRead = new BufferedReader(new InputStreamReader(dStream));
            String line;

            while ((line = bRead.readLine()) != null) {
                if (!Objects.equals(line, "+")) {
                    String[] temp = line.split("\\.");
                    try {
                        int[] data = Arrays.stream(temp).mapToInt(Integer::parseInt).toArray();
                        Integer[] iLine = IntStream.of(data).boxed().toArray(Integer[]::new);
                        levelMaps.get(i).add(new ArrayList<>(Arrays.asList(iLine)));
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid string. Cannot parse to int.");
                    }
                } else {
                    levelMaps.add(new ArrayList<>());
                    i++;
                }
            }
            dStream.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
        } catch (IOException e) {
            System.out.println("Cannot read line.");
        }
    }
}
