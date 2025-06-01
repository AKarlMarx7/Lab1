import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Main {
    public static void main(String[] args) {
        // 初始化图形界面
        SwingUtilities.invokeLater(() -> {
            new GraphGUI();
        });
    }
}
