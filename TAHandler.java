import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * TA模块处理类
 * 实现Sprint1 P0级功能：创建个人档案、上传CV
 */
public class TAHandler {
    /**
     * TA模块主界面
     */
    public static void taModule(Scanner scanner) {
        while (true) {
            System.out.println("\n===== TA模块 =====");
            System.out.println("请选择操作：");
            System.out.println("1. 创建个人档案");
            System.out.println("2. 上传CV");
            System.out.println("3. 查看可用岗位 - 暂未实现");
            System.out.println("4. 申请岗位 - 暂未实现");
            System.out.println("5. 查看申请状态 - 暂未实现");
            System.out.println("6. 返回主界面");
            System.out.print("请输入数字选择：");
            String choice = scanner.next();
            switch (choice) {
                case "1":
                    createTAProfile(scanner);
                    break;
                case "2":
                    uploadCV(scanner);
                    break;
                case "3":
                case "4":
                case "5":
                    System.out.println("【提示】该功能暂未实现，将在后续迭代开发！");
                    Utils.backToMenu(scanner);
                    break;
                case "6":
                    return; // 返回主界面
                default:
                    System.out.println("【错误】输入无效，请输入1-6的数字！");
            }
        }
    }

    /**
     * 创建TA个人档案
     */
    private static void createTAProfile(Scanner scanner) {
        System.out.println("\n===== 创建TA个人档案 =====");
        Map<String, String> profile = new HashMap<>();
        // 输入并校验学号
        while (true) {
            System.out.print("请输入学号：");
            String studentId = scanner.next();
            if (Utils.isEmpty(studentId)) {
                System.out.println("【错误】学号不能为空，请重新输入！");
            } else {
                profile.put("studentId", studentId);
                break;
            }
        }
        // 输入姓名
        while (true) {
            System.out.print("请输入姓名：");
            String name = scanner.next();
            if (Utils.isEmpty(name)) {
                System.out.println("【错误】姓名不能为空，请重新输入！");
            } else {
                profile.put("name", name);
                break;
            }
        }
        // 输入专业
        System.out.print("请输入专业：");
        String major = scanner.next();
        profile.put("major", major);
        // 输入联系方式
        while (true) {
            System.out.print("请输入手机号码：");
            String phone = scanner.next();
            if (Utils.isEmpty(phone) || phone.length() != 11) {
                System.out.println("【错误】手机号码不能为空且为11位，请重新输入！");
            } else {
                profile.put("phone", phone);
                break;
            }
        }
        // 初始化CV路径（未上传）
        profile.put("cvPath", "未上传");
        // 获取当前时间（简化版，Sprint2优化为格式化时间）
        profile.put("createTime", java.util.Calendar.getInstance().getTime().toString());
        // 二次确认
        System.out.print("确认创建档案？Y/N：");
        String confirm = scanner.next();
        if (confirm.equalsIgnoreCase("Y")) {
            DataHandler.saveTAProfile(profile);
        } else {
            System.out.println("【提示】档案创建已取消！");
        }
        Utils.backToMenu(scanner);
    }

    /**
     * 上传CV（仅保存文件路径，Sprint1不做文件实际读取）
     */
    private static void uploadCV(Scanner scanner) {
        System.out.println("\n===== 上传CV =====");
        System.out.print("请输入学号（绑定档案）：");
        String studentId = scanner.next();
        // 简化版：仅校验学号非空，Sprint2实现档案存在性校验
        if (Utils.isEmpty(studentId)) {
            System.out.println("【错误】学号不能为空！");
            Utils.backToMenu(scanner);
            return;
        }
        // 输入CV文件路径
        System.out.print("请输入CV文件路径（仅支持TXT/CSV，≤10MB）：");
        String cvPath = scanner.next();
        // 校验文件格式和大小
        if (!Utils.checkFileFormat(cvPath)) {
            System.out.println("【错误】文件格式不支持，仅支持TXT/CSV！");
            Utils.backToMenu(scanner);
            return;
        }
        if (!Utils.checkFileSize(cvPath)) {
            System.out.println("【错误】文件不存在或大小超过10MB！");
            Utils.backToMenu(scanner);
            return;
        }
        // 模拟更新档案的CV路径（Sprint2实现实际更新）
        System.out.println("【成功】CV路径已绑定至学号：" + studentId);
        System.out.println("【提示】Sprint2将实现档案信息的实际更新功能！");
        Utils.backToMenu(scanner);
    }
}