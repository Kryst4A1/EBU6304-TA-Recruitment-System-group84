package cn.bupt.tarecruitment.model;

public class Profile {
    private String userId;
    private String fullName;
    private String email;
    private String major;
    private String year;
    private String availability;
    private String skills;
    private String experience;
    private String introduction;
    private String cvFileName;

    public Profile() {}

    public Profile(String userId, String fullName, String email, String major, String year, String availability,
                   String skills, String experience, String introduction, String cvFileName) {
        this.userId = userId;
        this.fullName = fullName;
        this.email = email;
        this.major = major;
        this.year = year;
        this.availability = availability;
        this.skills = skills;
        this.experience = experience;
        this.introduction = introduction;
        this.cvFileName = cvFileName;
    }

    public String getUserId() { return userId; }
    public void setUserId(String userId) { this.userId = userId; }
    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getMajor() { return major; }
    public void setMajor(String major) { this.major = major; }
    public String getYear() { return year; }
    public void setYear(String year) { this.year = year; }
    public String getAvailability() { return availability; }
    public void setAvailability(String availability) { this.availability = availability; }
    public String getSkills() { return skills; }
    public void setSkills(String skills) { this.skills = skills; }
    public String getExperience() { return experience; }
    public void setExperience(String experience) { this.experience = experience; }
    public String getIntroduction() { return introduction; }
    public void setIntroduction(String introduction) { this.introduction = introduction; }
    public String getCvFileName() { return cvFileName; }
    public void setCvFileName(String cvFileName) { this.cvFileName = cvFileName; }
}
