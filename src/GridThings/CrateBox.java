package GridThings;

public class CrateBox extends GridThing{
    private boolean marked;
    public CrateBox(int ni, int nj, boolean nmarked){
        super(ni, nj);
        marked = nmarked;
    }

    public boolean isMarked() {
        return marked;
    }

    public void setMarked(boolean marked) {
        this.marked = marked;
    }
}
