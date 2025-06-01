import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.*;

class BridgeWordTest {

    // 被测函数所在类（嵌入式实现）
    static class DirectedGraph {
        Map<String, Map<String, Integer>> graph = new HashMap<>();

        public void addEdge(String from, String to) {
            graph.putIfAbsent(from, new HashMap<>());
            graph.get(from).put(to, 1);
        }

        public String queryBridgeWords(String word1, String word2) {
            Set<String> bridgeWords = new HashSet<>();
            for (String intermediate : graph.keySet()) {
                if (graph.get(word1) != null && graph.get(word1).containsKey(intermediate) &&
                    graph.get(intermediate) != null && graph.get(intermediate).containsKey(word2)) {
                    bridgeWords.add(intermediate);
                }
            }
            if (bridgeWords.isEmpty()) {
                return "从 \"" + word1 + "\" 到 \"" + word2 + "\" 没有桥接词!";
            } else {
                return "从 \"" + word1 + "\" 到 \"" + word2 + "\" 的桥接词是: " + String.join(", ", bridgeWords) + "。";
            }
        }
    }

    @Test
    void testTC1() {
        DirectedGraph graph = new DirectedGraph();
        graph.addEdge("wordA", "wordB");
        graph.addEdge("wordB", "wordC");
        assertEquals("从 \"wordA\" 到 \"wordC\" 的桥接词是: wordB。", graph.queryBridgeWords("wordA", "wordC"));
    }

    @Test
    void testTC2() {
        DirectedGraph graph = new DirectedGraph();
        graph.addEdge("wordA", "wordB");
        assertEquals("从 \"wordA\" 到 \"wordZ\" 没有桥接词!", graph.queryBridgeWords("wordA", "wordZ"));
    }

    @Test
    void testTC3() {
        DirectedGraph graph = new DirectedGraph();
        graph.addEdge("wordA", "wordB");
        assertEquals("从 \"wordA\" 到 \"wordX\" 没有桥接词!", graph.queryBridgeWords("wordA", "wordX"));
    }

    @Test
    void testTC4() {
        DirectedGraph graph = new DirectedGraph();
        graph.addEdge("wordA", "wordB");
        assertEquals("从 \"wordX\" 到 \"wordA\" 没有桥接词!", graph.queryBridgeWords("wordX", "wordA"));
    }

    @Test
    void testTC5() {
        DirectedGraph graph = new DirectedGraph();
        assertEquals("从 \"wordX\" 到 \"wordY\" 没有桥接词!", graph.queryBridgeWords("wordX", "wordY"));
    }

    @Test
    void testTC6() {
        DirectedGraph graph = new DirectedGraph();
        graph.addEdge("wordA", "wordB");
        assertEquals("从 \"wordA\" 到 \"wordA\" 没有桥接词!", graph.queryBridgeWords("wordA", "wordA"));
    }

    @Test
    void testTC7() {
        DirectedGraph graph = new DirectedGraph();
        graph.addEdge("wordA", "wordB");
        assertEquals("从 \"\" 到 \"wordA\" 没有桥接词!", graph.queryBridgeWords("", "wordA"));
    }

    @Test
    void testTC8() {
        DirectedGraph graph = new DirectedGraph();
        graph.addEdge("wordA", "wordB");
        assertEquals("从 \"wordA\" 到 \"\" 没有桥接词!", graph.queryBridgeWords("wordA", ""));
    }

    @Test
    void testTC9() {
        DirectedGraph graph = new DirectedGraph();
        assertEquals("从 \"wordA\" 到 \"wordB\" 没有桥接词!", graph.queryBridgeWords("wordA", "wordB"));
    }
}