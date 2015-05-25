import java.io.File;
import java.io.IOException;

/**
 * Created by us on 23/05/15.
 */
public class FileSize {

    public static void main (String[] args) throws IOException {
        File file = new File(args[0]);
        System.out.println(file.length());
    }
}
