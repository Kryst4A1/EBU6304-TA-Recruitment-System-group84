package cn.bupt.tarecruitment;

import cn.bupt.tarecruitment.model.*;
import cn.bupt.tarecruitment.repository.CsvDataStore;
import cn.bupt.tarecruitment.service.impl.*;
import cn.bupt.tarecruitment.util.CsvUtils;
import cn.bupt.tarecruitment.util.WebUtils;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class CoreLogicTest {
    public static void main(String[] args) throws Exception {
        csvRoundTrip();
        csvMultilineRoundTrip();
        htmlEscaping();
        statusCssMapping();
        skillMatchStrong();
        skillMatchMissing();
        multiRoleRegistration();
        applicationDecisionCreatesAssignments();
        jobCloseAndReopen();
        workloadRows();
        System.out.println("CoreLogicTest passed.");
    }

    static void csvRoundTrip() {
        String line = CsvUtils.toLine(List.of("alpha", "two, words", "quote \" mark"));
        List<String> parsed = CsvUtils.parseLine(line);
        assert parsed.size() == 3;
        assert parsed.get(1).equals("two, words");
        assert parsed.get(2).equals("quote \" mark");
    }

    static void csvMultilineRoundTrip() {
        String content = "h1,h2\n" + CsvUtils.toLine(List.of("line1\nline2", "simple")) + "\n";
        List<List<String>> rows = CsvUtils.parseRecords(content);
        assert rows.size() == 2 : rows.size();
        assert rows.get(1).get(0).equals("line1\nline2");
    }

    static void htmlEscaping() {
        assert WebUtils.html("<x>").equals("&lt;x&gt;");
        assert WebUtils.html("A&B").equals("A&amp;B");
    }

    static void statusCssMapping() {
        assert WebUtils.statusClass("SELECTED").equals("selected");
        assert WebUtils.statusClass("CLOSED").equals("closed");
        assert WebUtils.shortText("abcdef", 4).equals("abc…");
    }

    static void skillMatchStrong() {
        Profile p = new Profile("u1", "A", "", "", "", "", "Java; software engineering; tutoring", "", "", "");
        JobPost j = new JobPost("j1", "mo", "M", "Job", "S", "", "Java; software; tutoring", 5, "OPEN", "");
        SkillMatch m = new DefaultMatchService().match(p, j);
        assert m.getScore() == 100 : m.getScore();
        assert m.getLevel().equals("STRONG");
        assert m.getMissingSkills().isEmpty();
    }

    static void skillMatchMissing() {
        Profile p = new Profile("u1", "A", "", "", "", "", "Java", "", "", "");
        JobPost j = new JobPost("j1", "mo", "M", "Job", "S", "", "Java; statistics; tutoring", 5, "OPEN", "");
        SkillMatch m = new DefaultMatchService().match(p, j);
        assert m.getScore() == 33 : m.getScore();
        assert m.getMissingSkills().contains("statistics");
    }

    static void multiRoleRegistration() throws Exception {
        Path dir = seedEmptyStore("auth");
        DefaultAuthService auth = new DefaultAuthService(new CsvDataStore(dir));
        User ta = auth.registerUser("newta", "pass", "New TA", Role.TA);
        User mo = auth.registerUser("newmo", "pass", "New MO", Role.MO);
        User admin = auth.registerUser("newadmin", "pass", "New Admin", Role.ADMIN);
        assert ta.getRole() == Role.TA;
        assert mo.getRole() == Role.MO;
        assert admin.getRole() == Role.ADMIN;
        assert auth.login("newmo", "pass").getRole() == Role.MO;
        assert new CsvDataStore(dir).readProfiles().size() == 1;
    }

    static void applicationDecisionCreatesAssignments() throws Exception {
        Path dir = seedWorkflowStore("workflow");
        CsvDataStore store = new CsvDataStore(dir);
        DefaultJobService jobs = new DefaultJobService(store);
        DefaultApplicationService apps = new DefaultApplicationService(store, jobs);
        Application app = apps.apply("j1", "u1", "I can support the Java labs.");
        assert app.getStatus().equals("SUBMITTED");
        apps.updateStatus(app.getId(), "SELECTED");
        assert store.readAssignments().size() == 1;
        assert store.readAssignments().get(0).getHoursPerWeek() == 8;
        apps.updateStatus(app.getId(), "REJECTED");
        assert store.readAssignments().isEmpty();
    }

    static void jobCloseAndReopen() throws Exception {
        Path dir = seedWorkflowStore("jobs");
        DefaultJobService jobs = new DefaultJobService(new CsvDataStore(dir));
        jobs.close("j1", "mo");
        assert jobs.findById("j1").getStatus().equals("CLOSED");
        assert jobs.listOpenJobs().isEmpty();
        jobs.reopen("j1", "mo");
        assert jobs.findById("j1").getStatus().equals("OPEN");
        assert jobs.listOpenJobs().size() == 1;
    }

    static void workloadRows() throws Exception {
        Path dir = Files.createTempDirectory("ta-web-final-workload-test");
        Files.writeString(dir.resolve("users.csv"), "id,username,password,role,displayName\n" +
                "u1,ta1,p,TA,TA One\n" +
                "u2,ta2,p,TA,TA Two\n");
        Files.writeString(dir.resolve("jobs.csv"), "id,ownerId,moduleCode,title,semester,description,requiredSkills,hoursPerWeek,status,createdAt\n" +
                "j1,mo,M1,Job 1,S,D,Java,8,OPEN,now\n" +
                "j2,mo,M2,Job 2,S,D,Java,6,OPEN,now\n");
        Files.writeString(dir.resolve("assignments.csv"), "id,jobId,taUserId,hoursPerWeek\n" +
                "a1,j1,u1,8\n" +
                "a2,j2,u1,6\n");
        Files.writeString(dir.resolve("profiles.csv"), "userId,fullName,email,major,year,availability,skills,experience,introduction,cvFileName\n");
        Files.writeString(dir.resolve("applications.csv"), "id,jobId,taUserId,status,statement,createdAt\n");
        DefaultWorkloadService svc = new DefaultWorkloadService(new CsvDataStore(dir));
        List<WorkloadRow> rows = svc.buildRows();
        assert rows.get(0).getTotalHours() == 14;
        assert rows.get(0).isOverloaded();
        assert !svc.recommendRebalancing().isEmpty();
    }

    static Path seedEmptyStore(String name) throws Exception {
        Path dir = Files.createTempDirectory("ta-web-final-" + name + "-test");
        Files.writeString(dir.resolve("users.csv"), "id,username,password,role,displayName\n");
        Files.writeString(dir.resolve("profiles.csv"), "userId,fullName,email,major,year,availability,skills,experience,introduction,cvFileName\n");
        Files.writeString(dir.resolve("jobs.csv"), "id,ownerId,moduleCode,title,semester,description,requiredSkills,hoursPerWeek,status,createdAt\n");
        Files.writeString(dir.resolve("applications.csv"), "id,jobId,taUserId,status,statement,createdAt\n");
        Files.writeString(dir.resolve("assignments.csv"), "id,jobId,taUserId,hoursPerWeek\n");
        return dir;
    }

    static Path seedWorkflowStore(String name) throws Exception {
        Path dir = seedEmptyStore(name);
        Files.writeString(dir.resolve("users.csv"), "id,username,password,role,displayName\n" +
                "u1,ta1,p,TA,TA One\n" +
                "mo,mo,p,MO,MO One\n");
        Files.writeString(dir.resolve("profiles.csv"), "userId,fullName,email,major,year,availability,skills,experience,introduction,cvFileName\n" +
                "u1,TA One,ta@example.com,SE,Year 3,Monday,Java; tutoring,Lab support,Intro,\n");
        Files.writeString(dir.resolve("jobs.csv"), "id,ownerId,moduleCode,title,semester,description,requiredSkills,hoursPerWeek,status,createdAt\n" +
                "j1,mo,EBU6304,Software Engineering TA,Semester 2,Lab support,Java; tutoring,8,OPEN,2026-05-20T10:00\n");
        return dir;
    }
}
