package cn.bupt.tarecruitment.service.impl;

import cn.bupt.tarecruitment.model.JobPost;
import cn.bupt.tarecruitment.model.Profile;
import cn.bupt.tarecruitment.model.SkillMatch;
import cn.bupt.tarecruitment.service.MatchService;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;

public class DefaultMatchService implements MatchService {
    @Override
    public SkillMatch match(Profile profile, JobPost job) {
        List<String> required = splitSkills(job == null ? "" : job.getRequiredSkills());
        List<String> owned = splitSkills(profile == null ? "" : profile.getSkills());
        if (job == null) return new SkillMatch("", 0, "WEAK", List.of(), List.of(), "Job is missing.");
        if (required.isEmpty()) {
            return new SkillMatch(job.getId(), 100, "STRONG", List.of(), List.of(), "No required skills were listed, so this is treated as a full match.");
        }
        List<String> matched = new ArrayList<>();
        List<String> missing = new ArrayList<>();
        for (String req : required) {
            if (coversAny(owned, req)) matched.add(req); else missing.add(req);
        }
        int score = (int) Math.round((matched.size() * 100.0) / required.size());
        String level = score >= 75 ? "STRONG" : (score >= 40 ? "MODERATE" : "WEAK");
        String explanation = "Covered " + matched.size() + " of " + required.size() + " required skills.";
        if (!missing.isEmpty()) explanation += " Missing: " + String.join(", ", missing) + ".";
        return new SkillMatch(job.getId(), score, level, matched, missing, explanation);
    }

    public static List<String> splitSkills(String text) {
        Set<String> out = new LinkedHashSet<>();
        if (text == null || text.trim().isEmpty()) return new ArrayList<>();
        String[] parts = text.split("[;,/\\n]");
        for (String part : parts) {
            String clean = part.trim();
            if (!clean.isEmpty()) out.add(clean);
        }
        return new ArrayList<>(out);
    }

    private boolean coversAny(List<String> owned, String required) {
        for (String skill : owned) {
            if (covers(skill, required)) return true;
        }
        return false;
    }

    private boolean covers(String owned, String required) {
        String a = normalise(owned);
        String b = normalise(required);
        if (a.equals(b)) return true;
        return containsWholeWord(a, b) || containsWholeWord(b, a);
    }

    private String normalise(String value) {
        return value == null ? "" : value.trim().toLowerCase(Locale.ROOT).replaceAll("\\s+", " ");
    }

    private boolean containsWholeWord(String text, String phrase) {
        if (text.isEmpty() || phrase.isEmpty()) return false;
        return (" " + text + " ").contains(" " + phrase + " ");
    }
}
