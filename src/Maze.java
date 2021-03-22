import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Maze extends JFrame {
    static volatile boolean x = true;
    JButton[][] jButtons;
    Graph g;

    Maze(int size, Graph g) {
        jButtons = new JButton[size][size];
        setLayout(new GridBagLayout());
        var gbc = new GridBagConstraints();
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.fill = GridBagConstraints.BOTH;
        for (int i = 0; i < size; i++) {
            gbc.gridy = i;
            for (int j = 0; j < size; j++) {
                gbc.gridx = j;
                var cell = new JButton();
                jButtons[i][j] = cell;
                cell.setBackground(Color.RED);
                cell.setBorder(BorderFactory.createLineBorder(Color.black));
                add(cell, gbc);
            }
        }
        this.g = g;

        updateBlockedCells(g);

        setSize(new Dimension(800, 800));
        setLocationRelativeTo(null);
        setVisible(true);
    }

    void setVisited(int i, int j) {
        jButtons[i][j].setBackground(Color.GREEN);
        try{
            Thread.sleep(200);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    void setSeen(int i, int j) {
        jButtons[i][j].setBackground(Color.YELLOW);

    }

    void drawPath(){
        Vertice ver1 = g.graph[g.size-1][g.size-1];
        jButtons[ver1.i][ver1.j].setBackground(Color.BLUE);
        while(ver1.parent!=ver1){
            ver1=ver1.parent;
            jButtons[ver1.i][ver1.j].setBackground(Color.BLUE);
            try {
                Thread.sleep(500);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }


    void updateBlockedCells(Graph g) {
        for (int i = 0; i < g.size; i++) {
            for (int j = 0; j < g.size; j++) {
                if (g.blockedCells[i][j] == true) jButtons[i][j].setBackground(Color.BLACK);
            }
        }
    }


}
