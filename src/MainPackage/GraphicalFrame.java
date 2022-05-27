package MainPackage;

import GridThings.BlankMarkedBox;
import GridThings.CrateBox;
import GridThings.Wall;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;

/**
 * <h1>The class to create a graphical frame to display the game on the screen</h1>
 * this class is extending the JFrame class.
 * implements the Observer interface so it can repaint it self whenever the state of gameModel changed.
 * implements the Strategy interface so it can control the game by keyboard.
 */
public class GraphicalFrame extends JFrame implements Observer,Strategy {
    private GameModel model;
    private int lastPressed;
    private Dimension dimension;
    private PictureComponent[][] components;
    private static File BLANK_FILE;
    private static File BLANK_MARKED_FILE;
    private static File CRATE_FILE;
    private static File CRATE_MARKED_FILE;
    private static File PLAYER_FILE;
    private static File WALL_FILE;

    /**
     * The Constructor of GraphicalFrame class.
     * witch will set and init the settings of the Frame, images and files we have
     * @param nmodel the Game model that Frame will use.
     */
    public GraphicalFrame(GameModel nmodel){
        setTitle("Sokoban Game");
        loader();
        model = nmodel;
        dimension = model.getD();
        components = new PictureComponent[dimension.width][dimension.height];
        setLayout(new GridLayout(dimension.height, dimension.width, 0, 0));
        for (int j = 0; j < dimension.height; j++) {
            for (int i = 0; i < dimension.width; i++) {
                components[i][j] = new PictureComponent(BLANK_FILE);
                add(components[i][j]);
            }
        }

        addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode()==KeyEvent.VK_RIGHT)
                    lastPressed = GameModel.GO_RIGHT;
                else if(e.getKeyCode()==KeyEvent.VK_LEFT)
                    lastPressed = GameModel.GO_LEFT;
                else if(e.getKeyCode()==KeyEvent.VK_UP)
                    lastPressed = GameModel.GO_UP;
                else if(e.getKeyCode()==KeyEvent.VK_DOWN)
                    lastPressed = GameModel.GO_DOWN;
                model.accept(GraphicalFrame.this);
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });

        stateChanged();
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setVisible(true);
    }

    /**
     * this method is used to reset and repaint the frame images when change is happen.
     */
    @Override
    public void stateChanged() {
        for (int j = 0; j < dimension.height; j++) {
            for (int i = 0; i < dimension.width; i++) {
                components[i][j].changeImage(BLANK_FILE);
            }
        }
        for(BlankMarkedBox s : model.getBlankMarkedBoxes()){
            components[s.getI()][s.getJ()].changeImage(BLANK_MARKED_FILE);
        }
        for(CrateBox c : model.getCrateBoxes()){
            if (c.isMarked())
                components[c.getI()][c.getJ()].changeImage(CRATE_MARKED_FILE);
            else
                components[c.getI()][c.getJ()].changeImage(CRATE_FILE);
        }

        for(Wall wall : model.getWalls()){
            components[wall.getI()][wall.getJ()].changeImage(WALL_FILE);
        }
        components[model.getPlayer().getI()][model.getPlayer().getJ()].changeImage(PLAYER_FILE);
    }

    /**
     * When the model is changed, this Method used to set the new Model in the Observer.
     * @param nmodel the new Model
     */
    public void setModel(GameModel nmodel){
        for (int j = 0; j < dimension.height; j++) {
            for (int i = 0; i < dimension.width; i++) {
                remove(components[i][j]);
            }
        }
        model = nmodel;
        dimension = model.getD();
        components = new PictureComponent[dimension.width][dimension.height];
        setLayout(new GridLayout(dimension.height, dimension.width, 0, 0));
        for (int j = 0; j < dimension.height; j++) {
            for (int i = 0; i < dimension.width; i++) {
                components[i][j] = new PictureComponent(BLANK_FILE);
                add(components[i][j]);
            }
        }
        stateChanged();
        pack();
    }

    public static void loader(){
        BLANK_FILE = new File("Icons/blank.png");
        BLANK_MARKED_FILE = new File("Icons/blankmarked.png");
        CRATE_FILE = new File("Icons/crate.png");
        CRATE_MARKED_FILE = new File("Icons/cratemarked.png");
        PLAYER_FILE = new File("Icons/player.png");
        WALL_FILE = new File("Icons/wall.png");
    }

    /**
     * this method will get us the move type we have to apply on the game.
     * @return the move type
     */
    @Override
    public int moveType() {
        int retValue = lastPressed;
        lastPressed = GameModel.DO_NOTHING;
        return retValue;
    }
}
