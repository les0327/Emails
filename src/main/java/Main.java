import java.nio.file.Files;
import java.nio.file.Paths;

public class Main {

    public static void main(String[] args) throws Exception {
        EmailUtilities.sendEmail(args[0], args[1], args[2], args[3], args[4]);
        String html = new String(Files.readAllBytes(Paths.get(args[5])));
        EmailUtilities.sendHTMLEmail(args[0], args[1], args[2], html, args[4]);
    }
}
