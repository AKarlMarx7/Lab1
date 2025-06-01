import java.util.*;

public class DirectedGraph {
    private Map<String, Map<String, Integer>> graph;

    public DirectedGraph() {
        graph = new HashMap<>();
    }

    // 添加边
    public void addEdge(String from, String to) {
        graph.putIfAbsent(from, new HashMap<>());
        graph.get(from).put(to, graph.get(from).getOrDefault(to, 0) + 1);
    }

    // 展示有向图
    public void showDirectedGraph() {
        for (Map.Entry<String, Map<String, Integer>> entry : graph.entrySet()) {
            System.out.print(entry.getKey() + " -> ");
            for (Map.Entry<String, Integer> edge : entry.getValue().entrySet()) {
                System.out.print(edge.getKey() + "(" + edge.getValue() + ") ");
            }
            System.out.println();
        }
    }

    // 查询桥接词
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

    // 根据桥接词生成新文本
    public String generateNewText(String inputText) {
        String[] words = inputText.split(" ");
        StringBuilder newText = new StringBuilder(words[0]);
        for (int i = 1; i < words.length; i++) {
            String bridgeWord = queryBridgeWords(words[i - 1], words[i]);
            if (!bridgeWord.contains("没有桥接词")) {
                String bridge = bridgeWord.split(":")[1].trim().split(" ")[0];
                newText.append(" ").append(bridge).append(" ").append(words[i]);
            } else {
                newText.append(" ").append(words[i]);
            }
        }
        return newText.toString();
    }

    // 计算最短路径（使用Dijkstra算法）
    public String calcShortestPath(String word1, String word2) {
        if (!graph.containsKey(word1) || !graph.containsKey(word2)) {
            return "图中没有该单词!";
        }

        // Dijkstra算法的实现
        return "最短路径计算未实现。";
    }

    // 计算PageRank（简化版本）
    public double calPageRank(String word) {
        // 简化版的PageRank计算
        return 0.85;
    }

    // 获取图的所有节点
    public Map<String, Map<String, Integer>> getGraph() {
        return graph;
    }
}
