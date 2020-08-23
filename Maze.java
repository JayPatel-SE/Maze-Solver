
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;

public class Maze {

    private JFrame frame = null;

    public Maze(GridObj[][] grid) {

        int length = grid.length;

        frame = new JFrame();

        JPanel panel = new JPanel(new GridLayout(length, length, 3, 3)) {
            @Override
            public Dimension getPreferredSize() {
                return new Dimension(800, 800);
            }
        };

        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid[row].length; col++) {

                JPanel p2 = new JPanel();

                //check if it's a path, wall, or destination
                if (grid[row][col].getState() == 0) { //path
                    p2.setBackground(Color.gray);
                } else if (grid[row][col].getState() == 1) { //wall
                    p2.setBackground(Color.darkGray);
                } else if (grid[row][col].getState()==2){//destination
                    p2.setBackground(Color.orange);
                } else {
                    p2.setBackground(Color.PINK);
                }//end if

                panel.add(p2);

            }//end col
        }//end row

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Maze Game");
        frame.setContentPane(panel);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setVisible(true);

    }//end constructor()

    public void setPanelColor(int index, Color color) {
        frame.getContentPane().getComponents()[index].setBackground(color);
    }//end setPanelColor()

}//end class()
