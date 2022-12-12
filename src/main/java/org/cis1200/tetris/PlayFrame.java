package org.cis1200.tetris;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//JFrame is class inside swing package (javax.swing.JFrame)
//a class extends another class
//game form is a more advanced JFrame and inherits from JFrame
//GameForm is subclass of JFrame (superclass)
//ex: GameForm inherits setVisible from JFrame.
//subclasses inherit public and protected members and override inherited methods
//overriding is giving a method a different body

public class PlayFrame extends JFrame {
    private boolean loadedgame=false;
    private GameBoard ga;
    private QueueArea qa;
    private PlayThread gt;
    private PlayFrame gf;
    static JLabel scoreLabel;
    static JLabel levelLabel;
    JButton mainMenuButton = new JButton("main menu");
    JButton pauseButton = new JButton("Pause");
    JButton resumeButton = new JButton("Resume");
    JButton saveButton = new JButton("Save");
    JButton loadButton = new JButton("Load");
    JButton newBlockButton = new JButton("Queue");
    public PlayFrame() {
        ga = new GameBoard(this, 10);
        qa = new QueueArea(ga);
        gf=this;
        //Thread t = new Thread(qa);
        //t.run();
    }
    public void start() {

        this.add(ga);
        this.add(qa);
        this.setTitle("Game Form");
        this.setSize(610,850);

        scoreLabel = new JLabel("score: 0");
        levelLabel = new JLabel("level: 1");
        JPanel p = new JPanel();
        p.add(scoreLabel);
        p.add(levelLabel);
        p.add(mainMenuButton);
        //p.add(pauseButton);
        //p.add(resumeButton);
        p.add(saveButton);
        p.add(loadButton);
        p.add(newBlockButton);

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
                if(gt!=null)
                    gt.interrupt();

                Tetris.showStart();
            }
        });
        mainMenuButton.setFocusable(false);

        pauseButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //this.setVisible(false);
                gt.interrupt();

            }
        });
        pauseButton.setFocusable(false);
        resumeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(gt.isInterrupted()) {
                    gt.interrupt();
                }
                gt.start();
            }
        });
        resumeButton.setFocusable(false);
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ga.saveFallenBlocks();
            }
        });
        saveButton.setFocusable(false);

        loadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ga.loadFallenblocks();
                loadedgame=true;

            }
        });
        loadButton.setFocusable(false);

        newBlockButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ga.addBlockToQueue();
                qa.retrieveQueue();
                gf.repaint();
            }
        });
        newBlockButton.setFocusable(false);

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
        im.put(KeyStroke.getKeyStroke("RIGHT"),"right");
        im.put(KeyStroke.getKeyStroke("LEFT"),"left");
        im.put(KeyStroke.getKeyStroke("UP"),"up");
        im.put(KeyStroke.getKeyStroke("DOWN"),"down");
        im.put(KeyStroke.getKeyStroke("SPACE"),"space");

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
    public void startGame(){
        if(!loadedgame) {
            ga.resetBlocks(); //reset if game not loaded from saved file
        } else {
            loadedgame=false; //if loaded from file, continue and reset flag
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
}