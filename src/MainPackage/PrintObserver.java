package MainPackage;

import GridThings.BlankMarkedBox;
import GridThings.CrateBox;
import GridThings.Wall;

public class PrintObserver implements Observer {
    private GameModel model;

    public PrintObserver(GameModel m) {
        model = m;
    }

    @Override
    public void stateChanged() {
        StringBuilder[] s = new StringBuilder[model.getD().height + 2];
        for (int j = 0; j < model.getD().height+2; j++) {
            s[j] = new StringBuilder();
        }
        for (int j = 0; j < model.getD().width+2; j++) {
            s[0].append("-");
            s[model.getD().height + 1].append("-");
        }
        for (int i = 1; i < model.getD().height+1; i++) {
            s[i].append("|");
            for (int j = 0; j < model.getD().width; j++) {
                s[i].append(".");
            }
            s[i].append("|");
        }
        for(BlankMarkedBox b : model.getBlankMarkedBoxes()){
            s[b.getJ()+1].setCharAt(b.getI()+1, '@');
        }
        for(CrateBox c : model.getCrateBoxes()){
            if (c.isMarked())
                s[c.getJ()+1].setCharAt(c.getI()+1, 'O');
            else
                s[c.getJ()+1].setCharAt(c.getI()+1, 'X');
        }

        for(Wall wall : model.getWalls()){
            s[wall.getJ()+1].setCharAt(wall.getI()+1, '#');
        }
        s[model.getPlayer().getJ()+1].setCharAt(model.getPlayer().getI()+1, 'P');
        for(StringBuilder s1 : s)
            System.out.println(s1);
    }

    public void setModel(GameModel m){
        model = m;
    }
}
