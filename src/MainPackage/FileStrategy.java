package MainPackage;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * This class is used to provide a new way of controlling the game.
 * By reading a file and get the movements from it.
 */
public class FileStrategy implements Strategy {
    private Scanner reader;

    /**
     * The Constructor Method of FileStrategy, it init the Scanner Object to be ready to read the file
     * @param f the file we want to get the movement from
     */
    public FileStrategy(File f) {
        try {
            reader = new Scanner(f);
        } catch (FileNotFoundException e) {
            System.out.println("Cont Read File");
        }
    }

    /**
     * read the file and get next movement from next line until reaching end of the file.
     * @return movement type Game have to apply as integer value.
     */
    @Override
    public int moveType() {
        if (reader.hasNextLine()) {
            return Integer.parseInt(reader.nextLine());
        }
        return GameModel.DO_NOTHING;
    }
}
