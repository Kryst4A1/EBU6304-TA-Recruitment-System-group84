import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

/**
 * Utility class for system initialization, time formatting, file reading, and validation
 */
public class Utils {
    public static final String DATA_FOLDER = "data/";
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    // Initialize data directory
    public static void initSystem() {
        File directory = new File(DATA_FOLDER);
        if (!directory.exists()) {
            directory.mkdir();
        }
    }

    // Get formatted current time
    public static String getCurrentTime() {
        return DATE_FORMAT.format(new Date());
    }

    // Read all lines from a text/CSV file
    public static List<String> readFileLines(String filePath) {
        try {
            return Files.readAllLines(new File(filePath).toPath());
        } catch (IOException e) {
            return null;
        }
    }

    // Get maximum job ID for auto-increment
    public static int getMaxJobId() {
        List<String> lines = readFileLines(DATA_FOLDER + "job_list.csv");
        int maxId = 1000;
        if (lines == null || lines.size() <= 1) return maxId;

        for (int i = 1; i < lines.size(); i++) {
            try {
                String idStr = lines.get(i).split(",")[0].replace("J", "");
                int id = Integer.parseInt(idStr);
                if (id > maxId) maxId = id;
            } catch (Exception ignored) {}
        }
        return maxId;
    }

    // Check if string is empty
    public static boolean isEmpty(String text) {
        return text == null || text.trim().isEmpty();
    }

    // Validate CV file format (only .txt and .csv allowed)
    public static boolean validateCVFormat(String path) {
        return path.endsWith(".txt") || path.endsWith(".csv");
    }

    // Validate CV file size (<= 10MB)
    public static boolean validateCVSize(String path) {
        File file = new File(path);
        return file.exists() && file.length() <= 10 * 1024 * 1024;
    }

    // Press Enter to return to menu
    public static void pressEnterToReturn(Scanner scanner) {
        System.out.print("\nPress Enter to go back...");
        scanner.nextLine();
        scanner.nextLine();
    }
}
