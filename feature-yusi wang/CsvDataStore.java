package cn.bupt.tarecruitment.repository;

import cn.bupt.tarecruitment.model.*;
import cn.bupt.tarecruitment.util.CsvUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CsvDataStore {
    private final Path root;

    public CsvDataStore(Path root) {
        this.root = root;
    }

    public synchronized List<User> readUsers() {
        List<User> out = new ArrayList<>();
        for (List<String> r : readRecords("users.csv")) {
            if (r.size() >= 5) {
                out.add(new User(r.get(0), r.get(1), r.get(2), Role.valueOf(r.get(3)), r.get(4)));
            }
        }
        return out;
    }

    public synchronized void writeUsers(List<User> users) {
        List<List<String>> rows = new ArrayList<>();
        for (User u : users) {
            rows.add(Arrays.asList(u.getId(), u.getUsername(), u.getPassword(), u.getRole().name(), u.getDisplayName()));
        }
        writeRecords("users.csv", "id,username,password,role,displayName", rows);
    }

    public synchronized List<Profile> readProfiles() {
        List<Profile> out = new ArrayList<>();
        for (List<String> r : readRecords("profiles.csv")) {
            if (r.size() >= 10) {
                out.add(new Profile(r.get(0), r.get(1), r.get(2), r.get(3), r.get(4), r.get(5), r.get(6), r.get(7), r.get(8), r.get(9)));
            }
        }
        return out;
    }

    public synchronized void writeProfiles(List<Profile> profiles) {
        List<List<String>> rows = new ArrayList<>();
        for (Profile p : profiles) {
            rows.add(Arrays.asList(p.getUserId(), p.getFullName(), p.getEmail(), p.getMajor(), p.getYear(),
                    p.getAvailability(), p.getSkills(), p.getExperience(), p.getIntroduction(), p.getCvFileName()));
        }
        writeRecords("profiles.csv", "userId,fullName,email,major,year,availability,skills,experience,introduction,cvFileName", rows);
    }

    public synchronized List<JobPost> readJobs() {
        List<JobPost> out = new ArrayList<>();
        for (List<String> r : readRecords("jobs.csv")) {
            if (r.size() >= 10) {
                out.add(new JobPost(r.get(0), r.get(1), r.get(2), r.get(3), r.get(4), r.get(5), r.get(6), parseInt(r.get(7)), r.get(8), r.get(9)));
            }
        }
        return out;
    }

    public synchronized void writeJobs(List<JobPost> jobs) {
        List<List<String>> rows = new ArrayList<>();
        for (JobPost j : jobs) {
            rows.add(Arrays.asList(j.getId(), j.getOwnerId(), j.getModuleCode(), j.getTitle(), j.getSemester(),
                    j.getDescription(), j.getRequiredSkills(), String.valueOf(j.getHoursPerWeek()), j.getStatus(), j.getCreatedAt()));
        }
        writeRecords("jobs.csv", "id,ownerId,moduleCode,title,semester,description,requiredSkills,hoursPerWeek,status,createdAt", rows);
    }

    public synchronized List<Application> readApplications() {
        List<Application> out = new ArrayList<>();
        for (List<String> r : readRecords("applications.csv")) {
            if (r.size() >= 6) {
                out.add(new Application(r.get(0), r.get(1), r.get(2), r.get(3), r.get(4), r.get(5)));
            }
        }
        return out;
    }

    public synchronized void writeApplications(List<Application> applications) {
        List<List<String>> rows = new ArrayList<>();
        for (Application a : applications) {
            rows.add(Arrays.asList(a.getId(), a.getJobId(), a.getTaUserId(), a.getStatus(), a.getStatement(), a.getCreatedAt()));
        }
        writeRecords("applications.csv", "id,jobId,taUserId,status,statement,createdAt", rows);
    }

    public synchronized List<Assignment> readAssignments() {
        List<Assignment> out = new ArrayList<>();
        for (List<String> r : readRecords("assignments.csv")) {
            if (r.size() >= 4) {
                out.add(new Assignment(r.get(0), r.get(1), r.get(2), parseInt(r.get(3))));
            }
        }
        return out;
    }

    public synchronized void writeAssignments(List<Assignment> assignments) {
        List<List<String>> rows = new ArrayList<>();
        for (Assignment a : assignments) {
            rows.add(Arrays.asList(a.getId(), a.getJobId(), a.getTaUserId(), String.valueOf(a.getHoursPerWeek())));
        }
        writeRecords("assignments.csv", "id,jobId,taUserId,hoursPerWeek", rows);
    }

    private List<List<String>> readRecords(String fileName) {
        try {
            Path file = root.resolve(fileName);
            if (!Files.exists(file)) return new ArrayList<>();
            String content = Files.readString(file, StandardCharsets.UTF_8);
            List<List<String>> allRows = CsvUtils.parseRecords(content);
            if (allRows.isEmpty()) return new ArrayList<>();
            return new ArrayList<>(allRows.subList(1, allRows.size()));
        } catch (IOException ex) {
            throw new IllegalStateException("Cannot read " + fileName, ex);
        }
    }

    private void writeRecords(String fileName, String header, List<List<String>> rows) {
        try {
            Files.createDirectories(root);
            Path file = root.resolve(fileName);
            List<String> lines = new ArrayList<>();
            lines.add(header);
            for (List<String> row : rows) lines.add(CsvUtils.toLine(row));
            Path temp = root.resolve(fileName + ".tmp");
            Files.write(temp, lines, StandardCharsets.UTF_8);
            try {
                Files.move(temp, file, java.nio.file.StandardCopyOption.REPLACE_EXISTING, java.nio.file.StandardCopyOption.ATOMIC_MOVE);
            } catch (java.nio.file.AtomicMoveNotSupportedException ex) {
                Files.move(temp, file, java.nio.file.StandardCopyOption.REPLACE_EXISTING);
            }
        } catch (IOException ex) {
            throw new IllegalStateException("Cannot write " + fileName, ex);
        }
    }

    private int parseInt(String value) {
        try { return Integer.parseInt(value); } catch (Exception ex) { return 0; }
    }
}
