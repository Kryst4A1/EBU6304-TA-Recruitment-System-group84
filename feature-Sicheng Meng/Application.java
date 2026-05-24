package cn.bupt.tarecruitment.model;

public class Application {
    private String id;
    private String jobId;
    private String taUserId;
    private String status;
    private String statement;
    private String createdAt;

    public Application() {}

    public Application(String id, String jobId, String taUserId, String status, String statement, String createdAt) {
        this.id = id;
        this.jobId = jobId;
        this.taUserId = taUserId;
        this.status = status;
        this.statement = statement;
        this.createdAt = createdAt;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getJobId() { return jobId; }
    public void setJobId(String jobId) { this.jobId = jobId; }
    public String getTaUserId() { return taUserId; }
    public void setTaUserId(String taUserId) { this.taUserId = taUserId; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public String getStatement() { return statement; }
    public void setStatement(String statement) { this.statement = statement; }
    public String getCreatedAt() { return createdAt; }
    public void setCreatedAt(String createdAt) { this.createdAt = createdAt; }
}
