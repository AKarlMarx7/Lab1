import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GraphGUI {
    private JFrame frame;
    private DirectedGraph graph;
    private JTextArea textArea;

    public GraphGUI() {
        graph = new DirectedGraph();
        frame = new JFrame("图形文本处理");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);

        // 文件选择面板
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        // 文本显示区域
        textArea = new JTextArea();
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);
        panel.add(scrollPane, BorderLayout.CENTER);

        // 按钮和输入框
        JPanel controlPanel = new JPanel();
        controlPanel.setLayout(new GridLayout(2, 2));

        // 加载文件按钮
        JButton loadButton = new JButton("加载文本文件");
        loadButton.addActionListener(e -> loadFile());

        // 查询桥接词按钮
        JButton queryButton = new JButton("查询桥接词");
        queryButton.addActionListener(e -> queryBridgeWords());

        // 生成新文本按钮
        JButton generateButton = new JButton("生成新文本");
        generateButton.addActionListener(e -> generateNewText());

        // 随机游走按钮
        JButton walkButton = new JButton("随机游走");
        walkButton.addActionListener(e -> randomWalk());

        controlPanel.add(loadButton);
        controlPanel.add(queryButton);
        controlPanel.add(generateButton);
        controlPanel.add(walkButton);

        panel.add(controlPanel, BorderLayout.SOUTH);

        frame.add(panel);
        frame.setVisible(true);
    }

    private void loadFile() {
        JFileChooser fileChooser = new JFileChooser();
        int result = fileChooser.showOpenDialog(frame);
        if (result == JFileChooser.APPROVE_OPTION) {
            String filePath = fileChooser.getSelectedFile().getAbsolutePath();
            FileParser.parseFile(filePath, graph);
            textArea.setText("文件加载成功，图结构已生成！\n");
        }
    }

    private void queryBridgeWords() {
        String word1 = JOptionPane.showInputDialog("请输入第一个单词:");
        String word2 = JOptionPane.showInputDialog("请输入第二个单词:");
        String result = graph.queryBridgeWords(word1, word2);
        textArea.setText(result);
    }

    private void generateNewText() {
        String inputText = JOptionPane.showInputDialog("请输入文本:");
        String result = graph.generateNewText(inputText);
        textArea.setText("生成的新文本: \n" + result);
    }

    private void randomWalk() {
        RandomWalk.randomWalk(graph);
    }
}
