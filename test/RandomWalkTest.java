import org.junit.jupiter.api.Test;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class DirectedGraph {
    private final Map<String, Map<String, Integer>> graph = new HashMap<>();

    public void addEdge(String from, String to) {
        graph.computeIfAbsent(from, k -> new HashMap<>()).put(to, 1);
        graph.computeIfAbsent(to, k -> new HashMap<>());
    }

    public Map<String, Map<String, Integer>> getGraph() {
        return graph;
    }
}

public class RandomWalkTest {

    @Test
    public void testEmptyGraph() {
        DirectedGraph g = new DirectedGraph();
        RandomWalk.randomWalk(g);
        // 目视验证输出：“图中没有节点，无法进行随机游走！”
    }

    @Test
    public void testNoOutEdges() {
        DirectedGraph g = new DirectedGraph();
        g.addEdge("A", "A"); // 加一条环，避免空节点访问失败
        g.getGraph().put("B", new HashMap<>()); // B 节点无出边
        RandomWalk.randomWalk(g);
        // 输出中应出现：“没有出边，停止游走。”
    }

    @Test
    public void testCircularGraph() {
        DirectedGraph g = new DirectedGraph();
        g.addEdge("A", "B");
        g.addEdge("B", "A");
        RandomWalk.randomWalk(g);
        // 输出中应出现：“节点已被访问，停止游走。”
    }

    @Test
    public void testLinearGraph() {
        DirectedGraph g = new DirectedGraph();
        g.addEdge("A", "B");
        g.addEdge("B", "C");
        RandomWalk.randomWalk(g);
        // 输出应依次访问 A→B→C，最后停止
    }
}
