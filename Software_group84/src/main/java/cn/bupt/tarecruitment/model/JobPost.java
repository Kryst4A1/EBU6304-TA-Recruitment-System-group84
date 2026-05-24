package cn.bupt.tarecruitment.model;

public class JobPost {
    private String id;
    private String ownerId;
    private String moduleCode;
    private String title;
    private String semester;
    private String description;
    private String requiredSkills;
    private int hoursPerWeek;
    private String status;
    private String createdAt;

    public JobPost() {}

    public JobPost(String id, String ownerId, String moduleCode, String title, String semester, String description,
                   String requiredSkills, int hoursPerWeek, String status, String createdAt) {
        this.id = id;
        this.ownerId = ownerId;
        this.moduleCode = moduleCode;
        this.title = title;
        this.semester = semester;
        this.description = description;
        this.requiredSkills = requiredSkills;
        this.hoursPerWeek = hoursPerWeek;
        this.status = status;
        this.createdAt = createdAt;
    }

    public String label() {
        return safe(moduleCode) + " - " + safe(title);
    }

    private static String safe(String v) { return v == null ? "" : v; }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getOwnerId() { return ownerId; }
    public void setOwnerId(String ownerId) { this.ownerId = ownerId; }
    public String getModuleCode() { return moduleCode; }
    public void setModuleCode(String moduleCode) { this.moduleCode = moduleCode; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getSemester() { return semester; }
    public void setSemester(String semester) { this.semester = semester; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public String getRequiredSkills() { return requiredSkills; }
    public void setRequiredSkills(String requiredSkills) { this.requiredSkills = requiredSkills; }
    public int getHoursPerWeek() { return hoursPerWeek; }
    public void setHoursPerWeek(int hoursPerWeek) { this.hoursPerWeek = hoursPerWeek; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public String getCreatedAt() { return createdAt; }
    public void setCreatedAt(String createdAt) { this.createdAt = createdAt; }
}
