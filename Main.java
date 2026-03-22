import java.util.Scanner;

/**
 * 系统主入口类
 * 北邮国际学院TA招聘系统 - Sprint1 基础版本
 * 纯Java控制台应用，无框架依赖
 */
public class Main {
    public static void main(String[] args) {
        // 初始化系统：创建data目录
        Utils.initSystem();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            // 系统主界面
            System.out.println("===== BUPT国际学院TA招聘系统 [Sprint1] =====");
            System.out.println("请选择用户身份：");
            System.out.println("1. TA（助教申请者）");
            System.out.println("2. MO（模块组织者）");
            System.out.println("3. Admin（管理员）- 暂未实现");
            System.out.println("4. 退出系统");
            System.out.print("请输入数字选择：");
            String choice = scanner.next();
            switch (choice) {
                case "1":
                    // 进入TA模块
                    TAHandler.taModule(scanner);
                    break;
                case "2":
                    // 进入MO模块
                    MOHandler.moModule(scanner);
                    break;
                case "3":
                    System.out.println("【提示】管理员模块暂未实现，将在后续迭代开发！");
                    Utils.backToMenu(scanner);
                    break;
                case "4":
                    System.out.println("【提示】感谢使用本系统，再见！");
                    scanner.close();
                    System.exit(0);
                    break;
                default:
                    System.out.println("【错误】输入无效，请输入1-4的数字！");
            }
        }
    }
}