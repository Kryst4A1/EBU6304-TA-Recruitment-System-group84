<%@ page import="cn.bupt.tarecruitment.model.*" %>
<%@ include file="../common/header.jspf" %>
<% Application app = (Application) request.getAttribute("application"); JobPost job = (JobPost) request.getAttribute("job"); Profile p = (Profile) request.getAttribute("profile"); SkillMatch m = (SkillMatch) request.getAttribute("match"); %>
<h1 class="page-title">Applicant Review</h1>
<p class="page-subtitle">Review the applicant profile, CV evidence, statement, and explainable skill-match result before updating the decision.</p>
<div class="grid two">
    <div class="card">
        <span class="eyebrow">Applicant evidence</span>
        <h2><%=WebUtils.html(p.getFullName())%></h2>
        <p><strong>Email:</strong> <%=WebUtils.html(p.getEmail())%></p>
        <p><strong>Major / year:</strong> <%=WebUtils.html(p.getMajor())%> · <%=WebUtils.html(p.getYear())%></p>
        <p><strong>Availability</strong><br><%=WebUtils.nl2br(p.getAvailability())%></p>
        <p><strong>Skills</strong><br><%=WebUtils.html(p.getSkills())%></p>
        <p><strong>Experience</strong><br><%=WebUtils.nl2br(p.getExperience())%></p>
        <p><strong>Introduction</strong><br><%=WebUtils.nl2br(p.getIntroduction())%></p>
        <% if (p.getCvFileName() != null && !p.getCvFileName().isBlank()) { %><p><a class="btn secondary" target="_blank" href="<%=ctx%>/cv/preview?file=<%=WebUtils.html(p.getCvFileName())%>">Preview CV</a></p><% } else { %><p class="muted">No CV uploaded.</p><% } %>
    </div>
    <div class="card">
        <span class="eyebrow">Decision support</span>
        <h2><%=WebUtils.html(job.label())%></h2>
        <p><strong>Application status:</strong> <span class="badge <%=WebUtils.statusClass(app.getStatus())%>"><%=WebUtils.html(app.getStatus())%></span></p>
        <p><strong>Statement</strong><br><%=WebUtils.nl2br(app.getStatement())%></p>
        <h3>Skill Match</h3>
        <p><span class="badge <%=m.badgeClass()%>"><%=m.getScore()%>% <%=m.getLevel()%></span></p>
        <div class="progress"><span style="width:<%=m.getScore()%>%"></span></div>
        <p><%=WebUtils.html(m.getExplanation())%></p>
        <p><strong>Matched:</strong><br><% if (m.getMatchedSkills().isEmpty()) { %><span class="muted">None yet.</span><% } for (String s : m.getMatchedSkills()) { %><span class="skill-chip matched"><%=WebUtils.html(s)%></span><% } %></p>
        <p><strong>Missing:</strong><br><% if (m.getMissingSkills().isEmpty()) { %><span class="muted">No missing skills identified.</span><% } for (String s : m.getMissingSkills()) { %><span class="skill-chip missing"><%=WebUtils.html(s)%></span><% } %></p>
        <form method="post" action="<%=ctx%>/mo/applicant-review">
            <input type="hidden" name="appId" value="<%=WebUtils.html(app.getId())%>">
            <div class="form-row"><label>Decision</label><select name="status">
                <option value="SUBMITTED" <%=app.getStatus().equals("SUBMITTED") ? "selected" : ""%>>Submitted</option>
                <option value="SHORTLISTED" <%=app.getStatus().equals("SHORTLISTED") ? "selected" : ""%>>Shortlisted</option>
                <option value="SELECTED" <%=app.getStatus().equals("SELECTED") ? "selected" : ""%>>Selected</option>
                <option value="REJECTED" <%=app.getStatus().equals("REJECTED") ? "selected" : ""%>>Rejected</option>
            </select></div>
            <div class="actions"><button class="btn" type="submit">Update Decision</button><a class="btn secondary" href="<%=ctx%>/mo/applicants?jobId=<%=WebUtils.html(job.getId())%>">Back to Applicants</a></div>
        </form>
        <p class="ai-note">Selecting an applicant automatically adds that TA's weekly hours to the Admin workload board. Changing away from Selected removes that assignment.</p>
    </div>
</div>
<%@ include file="../common/footer.jspf" %>
