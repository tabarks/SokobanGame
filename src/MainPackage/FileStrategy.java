package MainPackage;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FileStrategy implements Strategy {
    private Scanner reader;
    public FileStrategy(File f) {
        try {
            reader = new Scanner(f);
        } catch (FileNotFoundException e) {
            System.out.println("Cont Read File");
        }
    }

    @Override
    public int moveType() {
        if (reader.hasNextLine()) {
            return Integer.parseInt(reader.nextLine());
        }
        return GameModel.DO_NOTHING;
    }
}
