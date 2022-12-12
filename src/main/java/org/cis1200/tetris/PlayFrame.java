package org.cis1200.tetris;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//JFrame is class inside swing package (javax.swing.JFrame)
//a class extends another class
//game form is a more advanced JFrame and inherits from JFrame
//GameForm is subclass of JFrame (superclass)
//ex: GameForm inherits setVisible
// from JFrame.
//subclasses inherit public and protected members and override inherited methods
//overriding is giving a method a different body

public class PlayFrame extends JFrame {
    private boolean loadGame = false;
    private final GameBoard ga;
    private final QueueArea qa;
    private PlayThread gt;
    private PlayFrame gf;
    static JLabel scoreLabel;
    static JLabel levelLabel;
    JButton mainMenuButton = new JButton("main");
    JButton saveButton = new JButton("save");
    JButton loadButton = new JButton("load");
    JButton addToQueueButton = new JButton("queue");

    public PlayFrame() {
        ga = new GameBoard(gf, 10);
        qa = new QueueArea(ga);
        gf = this;
    }

        scoreLabel = new JLabel("score: 0");
        levelLabel = new JLabel("level: 1");
        JPanel p = new JPanel();
        p.add(scoreLabel);
        p.add(levelLabel);
        p.add(mainMenuButton);
        p.add(saveButton);
        p.add(loadButton);
        p.add(addToQueueButton);

        new Timer(100, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                gf.repaint();
            }
        }).start();

        mainMenuButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //this.setVisible(false);
                gt.interrupt();
                Tetris.showStart();
            }
        });
        mainMenuButton.setFocusable(false);

        saveButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //this.setVisible(false);
                ga.saveFallenBlocks();
                gt.saveScore();
                gt.saveLevel();
            }
        });
        saveButton.setFocusable(false);

        loadButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ga.loadFallenBlocks();
                gt.loadLevel();
                gt.loadScore();
                loadGame = true;
            }
        });
        loadButton.setFocusable(false);

        addToQueueButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ga.addQueue();
                qa.retrieveQueue();
                gf.repaint();
            }
        });
        addToQueueButton.setFocusable(false);

        ga.add(p);
        controls();
        //startGame();
    }

    private void controls() {
        //add keystrokes; keyboard action ex: key press
        InputMap im = this.getRootPane().getInputMap();

        //add action to action map with name. then the names of keystroke from input map
        //matches the action name from the action map
        ActionMap am = this.getRootPane().getActionMap();

        //pass any data (object) as the second parameter. it doesn't have to be "right"
        //it can be anything.
        im.put(KeyStroke.getKeyStroke("RIGHT"), "right");
        im.put(KeyStroke.getKeyStroke("LEFT"), "left");
        im.put(KeyStroke.getKeyStroke("UP"), "up");
        im.put(KeyStroke.getKeyStroke("DOWN"), "down");
        im.put(KeyStroke.getKeyStroke("SPACE"), "space");

        //abstract cannot be instantiated (aka new AbstractAction()),
        // but they can be extended
        // create a different class and extend the abstract class
        //ex: class myClass extends AbstractAction{}

        //using anonymous subclass
        am.put("right", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ga.moveRight();
            }
        });
        am.put("left", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ga.moveLeft();
            }
        });
        am.put("up", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ga.rotate();
            }
        });
        am.put("down", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ga.moveDown();
            }
        });
        am.put("space", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ga.drop();
            }
        });

    }

    public void startGame() {
        if (!loadGame) {
            ga.resetBlocks();
        }
        gt = new PlayThread(ga, this);
        gt.start();
    }

    public void updateScore(int score) {
        scoreLabel.setText("score: " + score);
    }

    public void updateLevel(int level) {
        levelLabel.setText("level: " + level);
    }

    public static void main(String[] args) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PlayFrame().setVisible(true);
            }
        });

    }
}
