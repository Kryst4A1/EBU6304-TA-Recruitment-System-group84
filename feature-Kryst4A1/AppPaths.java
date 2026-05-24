package cn.bupt.tarecruitment.util;

import java.nio.file.Path;
import java.nio.file.Paths;

public final class AppPaths {
    private AppPaths() {}

    public static Path dataRoot() {
        String override = System.getProperty("ta.data.dir");
        if (override != null && !override.trim().isEmpty()) {
            return Paths.get(override).toAbsolutePath().normalize();
        }
        return Paths.get(System.getProperty("user.dir"), "data").toAbsolutePath().normalize();
    }

    public static Path uploadsDir() {
        return dataRoot().resolve("uploads");
    }
}
