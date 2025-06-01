import java.util.*;

public class RandomWalk {

    public static void randomWalk(DirectedGraph graph) {
        Random rand = new Random();
        List<String> nodes = new ArrayList<>(graph.getGraph().keySet());

        // 检查图中是否有节点
        if (nodes.isEmpty()) {
            System.out.println("图中没有节点，无法进行随机游走！");
            return;
        }

        String currentNode = nodes.get(rand.nextInt(nodes.size()));
        System.out.println("随机游走从节点: " + currentNode + " 开始");
        Set<String> visited = new HashSet<>();
        visited.add(currentNode);

        while (true) {
            Map<String, Integer> neighbors = graph.getGraph().get(currentNode);
            if (neighbors == null || neighbors.isEmpty()) {
                System.out.println("没有出边，停止游走。");
                break;
            }
            List<String> nextNodes = new ArrayList<>(neighbors.keySet());
            currentNode = nextNodes.get(rand.nextInt(nextNodes.size()));
            if (visited.contains(currentNode)) {
                System.out.println("节点已被访问，停止游走。");
                break;
            }
            visited.add(currentNode);
            System.out.println("访问节点: " + currentNode);
        }
    }
}
