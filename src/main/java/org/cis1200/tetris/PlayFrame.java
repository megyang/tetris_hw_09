package org.cis1200.tetris;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Properties;

import static org.cis1200.tetris.Tetris.GAME_STATE;

//JFrame is class inside swing package (javax.swing.JFrame)
//a class extends another class
//game form is a more advanced JFrame and inherits from JFrame
//GameForm is subclass of JFrame (superclass)
//ex: GameForm inherits setVisible from JFrame.
//subclasses inherit public and protected members and override inherited methods
//overriding is giving a method a different body

public class PlayFrame extends JFrame {
    private boolean loadedgame = false;
    private final GameBoard ga;
    private final QueueArea qa;
    private PlayThread gt;
    private final PlayFrame gf;
    static JLabel scoreLabel;
    static JLabel levelLabel;
    private static int score;
    private static int level;
    JButton mainMenuButton = new JButton("main");
    JButton saveButton = new JButton("save");
    JButton loadButton = new JButton("load");
    JButton addToQueueButton = new JButton("queue");

    public PlayFrame() {
        ga = new GameBoard(this, 10);
        qa = new QueueArea(ga);
        gt = new PlayThread(ga, this);
        gf = this;
    }

    public void start() {

        this.add(ga);
        this.add(qa);
        this.setTitle("Game Form");
        this.setSize(610, 850);

        scoreLabel = new JLabel("score: 0");
        levelLabel = new JLabel("level: 1");
        JPanel p = new JPanel();
        p.add(scoreLabel);
        p.add(levelLabel);
        p.add(mainMenuButton);
        p.add(saveButton);
        p.add(loadButton);
        p.add(addToQueueButton);

        //Use a timer to repaint GameForm. gf.repaint will repaint all JPanels inside it
        //GameArea and QueueArea
        new Timer(100, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gf.repaint();
            }
        }).start();
        mainMenuButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //this.setVisible(false);
                if (gt != null)
                    gt.interrupt();

                Tetris.showStart();
            }
        });
        mainMenuButton.setFocusable(false);

        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ga.saveFallenBlocks();
                saveStates();
            }
        });
        saveButton.setFocusable(false);

        loadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ga.loadFallenblocks();
                loadStates();
                loadedgame = true;

            }
        });
        loadButton.setFocusable(false);

        addToQueueButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ga.addBlockToQueue();
                qa.retrieveQueue();
                gf.repaint();
            }
        });
        addToQueueButton.setFocusable(false);

        ga.add(p);
        controls();
        //startGame();
    }
    public void saveStates() {
        Properties pp = new Properties();
        pp.setProperty("Score",Integer.toString(score));
        pp.setProperty("Level",Integer.toString(level));
        try {
            RWProperties.WriteFile(GAME_STATE,pp);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
    public void loadStates() {
        Properties pp = new Properties();
        try {
            pp = RWProperties.ReadFile(GAME_STATE);
        } catch (IOException e) {
            System.out.println("No saved game state found\n");
        }
        score = Integer.valueOf(pp.getProperty("Score",Integer.toString(score)));
        level = Integer.valueOf(pp.getProperty("Level",Integer.toString(level)));
        gt.setScore(score);
        gt.setLevel(level);
        updateScore(score);
        updateLevel(level);
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
        if (!loadedgame) {
            ga.resetBlocks(); //reset if game not loaded from saved file
        } else {
            loadedgame = false; //if loaded from file, continue and reset flag
        }
        gt.start();
    }

    public void updateScore(int score) {
        this.score = score;
        if(scoreLabel!=null) {
            scoreLabel.setText("score: " + score);
        }
    }

    public void updateLevel(int level) {
        this.level = level;
        if(levelLabel!=null) {
            levelLabel.setText("level: " + level);
        }
    }
}
