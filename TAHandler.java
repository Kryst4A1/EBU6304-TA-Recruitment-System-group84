import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * Handler for TA functions: view jobs, apply, check application status
 */
public class TAHandler {

    public static void showTAMenu(Scanner scanner) {
        while (true) {
            System.out.println("\n===== TA Main Menu =====");
            System.out.println("1. Create/Update My Profile");
            System.out.println("2. Upload CV");
            System.out.println("3. View Available Jobs");
            System.out.println("4. Apply for a Job");
            System.out.println("5. Check My Application Status");
            System.out.println("0. Logout");
            System.out.print("Please enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1: createTAProfile(scanner); break;
                case 2: uploadCV(scanner); break;
                case 3: viewAvailableJobs(scanner); break;
                case 4: applyForJob(scanner); break;
                case 5: checkApplicationStatus(scanner); break;
                case 0: System.out.println("Logging out..."); return;
                default: System.out.println("Invalid choice!");
            }
        }
    }

    private static void createTAProfile(Scanner scanner) {
        scanner.nextLine();
        System.out.print("Enter your student ID: ");
        String studentId = scanner.nextLine();
        System.out.print("Enter your full name: ");
        String name = scanner.nextLine();
        System.out.print("Enter your major: ");
        String major = scanner.nextLine();
        System.out.print("Enter your phone number: ");
        String phone = scanner.nextLine();
        System.out.print("Enter your CV file path: ");
        String cvPath = scanner.nextLine();

        String profile = "{\n\"studentId\": \"" + studentId + "\",\n\"name\": \"" + name + "\",\n\"major\": \"" + major + "\",\n\"phone\": \"" + phone + "\",\n\"cvPath\": \"" + cvPath + "\"\n}";
        DataHandler.saveTAProfile(studentId, profile);
        Utils.pressEnterToReturn(scanner);
    }

    private static void uploadCV(Scanner scanner) {
        scanner.nextLine();
        System.out.print("Enter CV file path: ");
        String path = scanner.nextLine();
        if (!Utils.validateCVFormat(path)) {
            System.out.println("[ERROR] Only .txt and .csv files are allowed!");
        } else if (!Utils.validateCVSize(path)) {
            System.out.println("[ERROR] File size exceeds 10MB!");
        } else {
            System.out.println("[SUCCESS] CV uploaded and validated!");
        }
        Utils.pressEnterToReturn(scanner);
    }

    // View all available jobs
    private static void viewAvailableJobs(Scanner scanner) {
        System.out.println("\n===== Available Jobs =====");
        List<String> jobs = DataHandler.loadAllJobs();
        if (jobs == null || jobs.size() <= 1) {
            System.out.println("No jobs available currently.");
        } else {
            for (int i = 1; i < jobs.size(); i++) {
                System.out.println(jobs.get(i));
            }
        }
        Utils.pressEnterToReturn(scanner);
    }

    // Apply for a job
    private static void applyForJob(Scanner scanner) {
        System.out.print("Enter your student ID: ");
        String studentId = scanner.next();
        Map<String, String> profile = DataHandler.loadTAProfile(studentId);

        if (profile == null) {
            System.out.println("[ERROR] Profile not found! Please create profile first.");
            Utils.pressEnterToReturn(scanner);
            return;
        }

        System.out.print("Enter target Job ID (e.g., J1001): ");
        String jobId = scanner.next();

        String application = String.join(",",
                jobId,
                studentId,
                profile.get("name"),
                profile.get("major"),
                profile.get("phone"),
                profile.get("cvPath"),
                Utils.getCurrentTime(),
                "Pending"
        );
        DataHandler.submitApplication(application);
        Utils.pressEnterToReturn(scanner);
    }
//
//    // Check application status
//    private static void checkApplicationStatus(Scanner scanner) {
//        System.out.print("Enter your student ID: ");
//        String studentId = scanner.next();
//        List<String> applications = DataHandler.loadAllApplications();
//
//        System.out.println("\n===== My Applications =====");
//        boolean found = false;
//        if (applications != null && applications.size() > 1) {
//            for (int i = 1; i < applications.size(); i++) {
//                String[] data = applications.get(i).split(",");
//                if (data[1].equals(studentId)) {
//                    System.out.println(applications.get(i));
//                    found = true;
//                }
//            }
//        }
//        if (!found) System.out.println("No application records found.");
//        Utils.pressEnterToReturn(scanner);
//    }
}
