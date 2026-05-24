package cn.bupt.tarecruitment.model;

public class WorkloadRecommendation {
    private String fromTaName;
    private String toTaName;
    private String jobLabel;
    private int hours;
    private String reason;

    public WorkloadRecommendation() {}

    public WorkloadRecommendation(String fromTaName, String toTaName, String jobLabel, int hours, String reason) {
        this.fromTaName = fromTaName;
        this.toTaName = toTaName;
        this.jobLabel = jobLabel;
        this.hours = hours;
        this.reason = reason;
    }

    public String getFromTaName() { return fromTaName; }
    public void setFromTaName(String fromTaName) { this.fromTaName = fromTaName; }
    public String getToTaName() { return toTaName; }
    public void setToTaName(String toTaName) { this.toTaName = toTaName; }
    public String getJobLabel() { return jobLabel; }
    public void setJobLabel(String jobLabel) { this.jobLabel = jobLabel; }
    public int getHours() { return hours; }
    public void setHours(int hours) { this.hours = hours; }
    public String getReason() { return reason; }
    public void setReason(String reason) { this.reason = reason; }
}
