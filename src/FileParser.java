import java.io.*;
import java.util.*;

public class FileParser {

    public static void parseFile(String filePath, DirectedGraph graph) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] words = line.split("\\W+");
                for (int i = 0; i < words.length - 1; i++) {
                    String word1 = words[i].toLowerCase();
                    String word2 = words[i + 1].toLowerCase();
                    graph.addEdge(word1, word2);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
