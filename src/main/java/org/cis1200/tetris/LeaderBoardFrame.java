package org.cis1200.tetris;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import static org.cis1200.tetris.Tetris.LEADERBOARD_PATH;

public class LeaderBoardFrame extends JFrame {
    String[] cols = new String[]{"name", "score"};
    DefaultTableModel defaultModel = new DefaultTableModel(cols, 0);
    private TableRowSorter<TableModel> sorter;
    JButton mainMenuButton = new JButton("main menu");
    JPanel myPanel = new JPanel();

    JTable leaderBoard = new JTable(defaultModel);

    public LeaderBoardFrame() {

        myPanel.setVisible(true);
        this.setLayout(new FlowLayout());
        this.setSize(500, 250);
        this.add(mainMenuButton);
        this.add(myPanel);
        myPanel.add(new JScrollPane(leaderBoard));
        mainMenuButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //this.setVisible(false);
                Tetris.showStart();
            }
        });
        tableData();
        tableSorter();
    }

    private void saveLeaderboard() {
        FileOutputStream fs = null;
        ObjectOutputStream os = null;
        try {
            fs = new FileOutputStream(LEADERBOARD_PATH);
            os = new ObjectOutputStream(fs);
            os.writeObject(defaultModel.getDataVector());
            os.close();
            fs.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
            System.out.println("\nCouldn't save to " + LEADERBOARD_PATH + " \nMake sure it is a valid path");
        }

    }

    private void tableData() {
        Vector colId = new Vector();
        colId.add("name");
        colId.add("score");

        FileInputStream fs = null;
        try {
            fs = new FileInputStream(LEADERBOARD_PATH);
            ObjectInputStream os = new ObjectInputStream(fs);
            defaultModel.setDataVector((Vector<Vector>) os.readObject(), colId);
            os.close();
            fs.close();
        } catch (Exception e) {
            System.out.println(e.getMessage()+"\nNo saved leaderboard yet\n");
        }
    }

    private void tableSorter() {
        sorter = new TableRowSorter<>(defaultModel);
        leaderBoard.setRowSorter(sorter);

        ArrayList<RowSorter.SortKey> keys = new ArrayList<>();
        keys.add(new RowSorter.SortKey(1, SortOrder.DESCENDING));

        sorter.setSortKeys(keys);
    }

    public void addPlayer(String userName, int score) {
        defaultModel.addRow(new Object[]{userName, score});
        saveLeaderboard();
        sorter.sort();
        this.setVisible(true);
    }
}
