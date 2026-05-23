package cn.bupt.tarecruitment.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SkillMatch {
    private String jobId;
    private int score;
    private String level;
    private List<String> matchedSkills = new ArrayList<>();
    private List<String> missingSkills = new ArrayList<>();
    private String explanation;

    public SkillMatch() {}

    public SkillMatch(String jobId, int score, String level, List<String> matchedSkills, List<String> missingSkills, String explanation) {
        this.jobId = jobId;
        this.score = score;
        this.level = level;
        this.matchedSkills = new ArrayList<>(matchedSkills == null ? Collections.emptyList() : matchedSkills);
        this.missingSkills = new ArrayList<>(missingSkills == null ? Collections.emptyList() : missingSkills);
        this.explanation = explanation;
    }

    public String badgeClass() {
        if (score >= 75) return "strong";
        if (score >= 40) return "moderate";
        return "weak";
    }

    public String getJobId() { return jobId; }
    public void setJobId(String jobId) { this.jobId = jobId; }
    public int getScore() { return score; }
    public void setScore(int score) { this.score = score; }
    public String getLevel() { return level; }
    public void setLevel(String level) { this.level = level; }
    public List<String> getMatchedSkills() { return matchedSkills; }
    public void setMatchedSkills(List<String> matchedSkills) { this.matchedSkills = matchedSkills; }
    public List<String> getMissingSkills() { return missingSkills; }
    public void setMissingSkills(List<String> missingSkills) { this.missingSkills = missingSkills; }
    public String getExplanation() { return explanation; }
    public void setExplanation(String explanation) { this.explanation = explanation; }
}
