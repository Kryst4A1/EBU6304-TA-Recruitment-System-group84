package cn.bupt.tarecruitment.model;

import java.util.ArrayList;
import java.util.List;

public class WorkloadRow {
    private String userId;
    private String taName;
    private int totalHours;
    private List<String> assignments = new ArrayList<>();
    private boolean overloaded;

    public WorkloadRow() {}

    public WorkloadRow(String userId, String taName, int totalHours, List<String> assignments, boolean overloaded) {
        this.userId = userId;
        this.taName = taName;
        this.totalHours = totalHours;
        this.assignments = assignments == null ? new ArrayList<>() : new ArrayList<>(assignments);
        this.overloaded = overloaded;
    }

    public String getUserId() { return userId; }
    public void setUserId(String userId) { this.userId = userId; }
    public String getTaName() { return taName; }
    public void setTaName(String taName) { this.taName = taName; }
    public int getTotalHours() { return totalHours; }
    public void setTotalHours(int totalHours) { this.totalHours = totalHours; }
    public List<String> getAssignments() { return assignments; }
    public void setAssignments(List<String> assignments) { this.assignments = assignments; }
    public boolean isOverloaded() { return overloaded; }
    public void setOverloaded(boolean overloaded) { this.overloaded = overloaded; }
}
