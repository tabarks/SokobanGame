package MainPackage;

import GridThings.BlankMarkedBox;
import GridThings.CrateBox;
import GridThings.Wall;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;

public class GraphicalFrame extends JFrame implements Observer,Strategy {
    private GameModel model;
    private int lastPressed;
    private final Dimension dimension;
    private final PictureComponent[][] components;
    private static File BLANK_FILE;
    private static File BLANK_MARKED_FILE;
    private static File CRATE_FILE;
    private static File CRATE_MARKED_FILE;
    private static File PLAYER_FILE;
    private static File WALL_FILE;


    public GraphicalFrame(GameModel nmodel){
        setTitle("Sokoban Game");
        loader();
        model = nmodel;
        lastPressed = 0;
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
                model.move();
                lastPressed = GameModel.DO_NOTHING;
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });

        stateChanged();
        setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        pack();
        setVisible(true);
    }

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

    public void setModel(GameModel m){
        model = m;
    }

    public static void loader(){
        BLANK_FILE = new File("Icons/blank.png");
        BLANK_MARKED_FILE = new File("Icons/blankmarked.png");
        CRATE_FILE = new File("Icons/crate.png");
        CRATE_MARKED_FILE = new File("Icons/cratemarked.png");
        PLAYER_FILE = new File("Icons/player.png");
        WALL_FILE = new File("Icons/wall.png");
    }

    @Override
    public int moveType() {
        return lastPressed;
    }
}
