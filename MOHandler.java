import java.util.List;
import java.util.Scanner;

/**
 * Handler for Module Organizer functions: publish jobs, view applicants
 */
public class MOHandler {

    public static void showMOMenu(Scanner scanner) {
        while (true) {
            System.out.println("\n===== MO Main Menu =====");
            System.out.println("1. Publish New Job");
            System.out.println("2. View All Published Jobs");
            System.out.println("3. Filter Applicants by Job ID");
            System.out.println("0. Logout");
            System.out.print("Please enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1: publishNewJob(scanner); break;
                case 2: viewAllJobs(scanner); break;
                case 3: filterApplicantsByJob(scanner); break;
                case 0: System.out.println("Logging out..."); return;
                default: System.out.println("Invalid choice!");
            }
        }
    }

    private static void publishNewJob(Scanner scanner) {
        int newId = Utils.getMaxJobId() + 1;
        String jobId = "J" + newId;
        scanner.nextLine();

        System.out.print("Enter course code (e.g., EBU6304): ");
        String course = scanner.nextLine();
        System.out.print("Enter position title: ");
        String position = scanner.nextLine();
        System.out.print("Enter working hours per week: ");
        String hours = scanner.nextLine();
        System.out.print("Enter job requirements: ");
        String requirements = scanner.nextLine();

        String jobData = String.join(",", jobId, course, position, hours, requirements);
        DataHandler.publishJob(jobData);
        Utils.pressEnterToReturn(scanner);
    }

    // View all jobs published by MO
    private static void viewAllJobs(Scanner scanner) {
        System.out.println("\n===== All Published Jobs =====");
        List<String> jobs = DataHandler.loadAllJobs();
        if (jobs == null || jobs.size() <= 1) {
            System.out.println("No jobs published yet.");
        } else {
            for (int i = 1; i < jobs.size(); i++) {
                System.out.println(jobs.get(i));
            }
        }
        Utils.pressEnterToReturn(scanner);
    }

    // Filter applicants by Job ID
    private static void filterApplicantsByJob(Scanner scanner) {
        System.out.print("Enter Job ID to filter applicants: ");
        String jobId = scanner.next();
        List<String> applications = DataHandler.loadAllApplications();

        System.out.println("\n===== Applicants for Job: " + jobId + " =====");
        boolean found = false;
        if (applications != null && applications.size() > 1) {
            for (int i = 1; i < applications.size(); i++) {
                String[] data = applications.get(i).split(",");
                if (data[0].equals(jobId)) {
                    System.out.println(applications.get(i));
                    found = true;
                }
            }
        }
        if (!found) System.out.println("No applicants for this job.");
        Utils.pressEnterToReturn(scanner);
    }
}
