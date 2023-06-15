package Tests;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Test1 extends JFrame {
    private int rows;
    private int cols;
    private JPanel gridPanel;

    public Test1 (int initialRows, int initialCols) {
        this.rows = initialRows;
        this.cols = initialCols;

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Resizable Grid Example");

        // Create a panel to hold the grid
        gridPanel = new JPanel();
        gridPanel.setLayout(new GridLayout(rows, cols));

        // Create the grid cells (JButtons) and add them to the panel
        for (int i = 0; i < rows * cols; i++) {
            JButton button = new JButton();
            button.addActionListener(new CellClickListener());
            gridPanel.add(button);
        }

        // Add the grid panel to the frame
        add(gridPanel, BorderLayout.CENTER);

        // Add a button to resize the grid
        JButton resizeButton = new JButton("Resize Grid");
        resizeButton.addActionListener(new ResizeClickListener());
        add(resizeButton, BorderLayout.SOUTH);

        pack();
        setVisible(true);
    }

    private class CellClickListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JButton button = (JButton) e.getSource();
            button.setBackground(Color.RED);
        }
    }

    private class ResizeClickListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String input = JOptionPane.showInputDialog("Enter new number of rows and columns (e.g., 3x3):");
            if (input != null) {
                String[] dimensions = input.split("x");
                if (dimensions.length == 2) {
                    int newRows = Integer.parseInt(dimensions[0]);
                    int newCols = Integer.parseInt(dimensions[1]);

                    // Remove existing grid cells
                    gridPanel.removeAll();

                    // Set the new number of rows and columns
                    rows = newRows;
                    cols = newCols;

                    // Update the grid layout
                    gridPanel.setLayout(new GridLayout(rows, cols));

                    // Create the new grid cells and add them to the panel
                    for (int i = 0; i < rows * cols; i++) {
                        JButton button = new JButton();
                        button.addActionListener(new CellClickListener());
                        gridPanel.add(button);
                    }

                    // Refresh the frame to reflect the changes
                    pack();
                }
            }
        }
    }
}
