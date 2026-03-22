import java.io.File;
import java.util.Scanner;
/**
 * 通用工具类
 * 提供系统初始化、输入校验、界面提示等功能
 */
public class Utils {
    // 数据存储根目录
    public static final String DATA_DIR = "data/";

    /**
     * 初始化系统：创建data目录
     */
    public static void initSystem() {
        File dir = new File(DATA_DIR);
        if (!dir.exists()) {
            boolean createSuccess = dir.mkdir();
            if (createSuccess) {
                System.out.println("【系统】数据目录创建成功：" + DATA_DIR);
            } else {
                System.out.println("【错误】数据目录创建失败，请检查权限！");
            }
        }
    }

    /**
     * 返回到上一级菜单的提示
     */
    public static void backToMenu(Scanner scanner) {
        System.out.print("按回车键返回上一级菜单...");
        scanner.nextLine();
        scanner.nextLine(); // 吸收换行符
        System.out.println("======================================");
    }

    /**
     * 非空字符串校验
     */
    public static boolean isEmpty(String str) {
        return str == null || str.trim().length() == 0;
    }

    /**
     * 文件格式校验（仅支持TXT/CSV）
     */
    public static boolean checkFileFormat(String filePath) {
        return filePath.endsWith(".txt") || filePath.endsWith(".csv");
    }

    /**
     * 文件大小校验（≤10MB）
     */
    public static boolean checkFileSize(String filePath) {
        File file = new File(filePath);
        if (!file.exists()) {
            return false;
        }
        // 10MB = 10 * 1024 * 1024 字节
        long maxSize = 10 * 1024 * 1024;
        return file.length() <= maxSize;
    }
}