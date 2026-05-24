package cn.bupt.tarecruitment.model;

public class Assignment {
    private String id;
    private String jobId;
    private String taUserId;
    private int hoursPerWeek;

    public Assignment() {}

    public Assignment(String id, String jobId, String taUserId, int hoursPerWeek) {
        this.id = id;
        this.jobId = jobId;
        this.taUserId = taUserId;
        this.hoursPerWeek = hoursPerWeek;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getJobId() { return jobId; }
    public void setJobId(String jobId) { this.jobId = jobId; }
    public String getTaUserId() { return taUserId; }
    public void setTaUserId(String taUserId) { this.taUserId = taUserId; }
    public int getHoursPerWeek() { return hoursPerWeek; }
    public void setHoursPerWeek(int hoursPerWeek) { this.hoursPerWeek = hoursPerWeek; }
}
