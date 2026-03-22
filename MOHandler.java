import java.util.Scanner;

/**
 * MO模块处理类
 * 实现Sprint1 P0级功能：发布招聘岗位、查看已发布岗位（简化版）
 */
public class MOHandler {
    // 岗位ID自增（简化版，Sprint2优化为从文件读取最大ID）
    private static int jobId = 1001;

    /**
     * MO模块主界面
     */
    public static void moModule(Scanner scanner) {
        while (true) {
            System.out.println("\n===== MO模块 =====");
            System.out.println("请选择操作：");
            System.out.println("1. 发布招聘岗位");
            System.out.println("2. 筛选岗位申请者 - 暂未实现");
            System.out.println("3. 查看已发布岗位 - 简化版");
            System.out.println("4. 返回主界面");
            System.out.print("请输入数字选择：");
            String choice = scanner.next();
            switch (choice) {
                case "1":
                    publishJob(scanner);
                    break;
                case "2":
                    System.out.println("【提示】该功能暂未实现，将在后续迭代开发！");
                    Utils.backToMenu(scanner);
                    break;
                case "3":
                    viewJobs(scanner);
                    break;
                case "4":
                    return; // 返回主界面
                default:
                    System.out.println("【错误】输入无效，请输入1-4的数字！");
            }
        }
    }

    /**
     * 发布招聘岗位
     */
    private static void publishJob(Scanner scanner) {
        System.out.println("\n===== 发布招聘岗位 =====");
        // 生成岗位ID
        String jobIdStr = "J" + (jobId++);
        // 输入岗位名称
        System.out.print("请输入岗位名称：");
        String jobName = scanner.next();
        // 输入岗位要求
        System.out.print("请输入岗位要求：");
        String jobReq = scanner.next();
        // 输入招聘人数
        int num = 0;
        while (true) {
            System.out.print("请输入招聘人数：");
            try {
                num = scanner.nextInt();
                if (num <= 0) {
                    System.out.println("【错误】招聘人数必须为正整数，请重新输入！");
                } else {
                    break;
                }
            } catch (Exception e) {
                System.out.println("【错误】招聘人数必须为数字，请重新输入！");
                scanner.next(); // 吸收无效输入
            }
        }
        // 输入截止日期
        System.out.print("请输入截止日期（格式：2026-04-10）：");
        String deadLine = scanner.next();
        // 输入发布人
        System.out.print("请输入发布人（姓名）：");
        String publisher = scanner.next();
        // 拼接CSV格式的岗位信息
        String jobInfo = String.join(",", jobIdStr, jobName, jobReq, String.valueOf(num), deadLine, publisher);
        // 二次确认
        System.out.print("确认发布该岗位？Y/N：");
        String confirm = scanner.next();
        if (confirm.equalsIgnoreCase("Y")) {
            DataHandler.publishJob(jobInfo);
        } else {
            System.out.println("【提示】岗位发布已取消！");
        }
        Utils.backToMenu(scanner);
    }

    /**
     * 查看已发布岗位（简化版，Sprint2实现文件读取并展示）
     */
    private static void viewJobs(Scanner scanner) {
        System.out.println("\n===== 已发布岗位（简化版） =====");
        System.out.println("【提示】Sprint2将实现从job_list.csv读取并展示所有岗位信息！");
        System.out.println("当前已发布岗位ID示例：J1001、J1002...");
        Utils.backToMenu(scanner);
    }
}