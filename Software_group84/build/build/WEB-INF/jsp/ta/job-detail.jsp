<%@ page import="cn.bupt.tarecruitment.model.*" %>
<%@ include file="../common/header.jspf" %>
<% JobPost job = (JobPost) request.getAttribute("job"); SkillMatch m = (SkillMatch) request.getAttribute("match"); boolean already = Boolean.TRUE.equals(request.getAttribute("alreadyApplied")); %>
<h1 class="page-title"><%=WebUtils.html(job.getTitle())%></h1>
<p class="page-subtitle"><%=WebUtils.html(job.getModuleCode())%> · <%=WebUtils.html(job.getSemester())%> · <%=job.getHoursPerWeek()%> hours per week</p>
<div class="grid two">
    <div class="card">
        <span class="eyebrow">Job information</span>
        <h2><%=WebUtils.html(job.label())%></h2>
        <p><strong>Description</strong><br><%=WebUtils.nl2br(job.getDescription())%></p>
        <p><strong>Required skills</strong><br><%=WebUtils.html(job.getRequiredSkills())%></p>
        <p><span class="badge <%=WebUtils.statusClass(job.getStatus())%>"><%=WebUtils.html(job.getStatus())%></span></p>
    </div>
    <div class="card">
        <span class="eyebrow">AI-assisted fit</span>
        <h2>Skill Match</h2>
        <p><span class="badge <%=m.badgeClass()%>"><%=m.getScore()%>% <%=m.getLevel()%></span></p>
        <div class="progress"><span style="width:<%=m.getScore()%>%"></span></div>
        <p><%=WebUtils.html(m.getExplanation())%></p>
        <p><strong>Matched skills:</strong><br><% if (m.getMatchedSkills().isEmpty()) { %><span class="muted">None yet.</span><% } for (String s : m.getMatchedSkills()) { %><span class="skill-chip matched"><%=WebUtils.html(s)%></span><% } %></p>
        <p><strong>Missing skills:</strong><br><% if (m.getMissingSkills().isEmpty()) { %><span class="muted">No missing skills identified.</span><% } for (String s : m.getMissingSkills()) { %><span class="skill-chip missing"><%=WebUtils.html(s)%></span><% } %></p>
    </div>
</div>
<% if (already) { %>
<div class="card"><strong>You already have an active application for this job.</strong><p class="muted">Track the decision status on the Applications page.</p><a class="btn" href="<%=ctx%>/ta/applications">View Applications</a></div>
<% } else { %>
<form class="card" method="post" action="<%=ctx%>/ta/apply">
    <span class="eyebrow">Apply now</span>
    <h2>Application statement</h2>
    <input type="hidden" name="jobId" value="<%=WebUtils.html(job.getId())%>">
    <div class="form-row"><label>Why are you suitable for this job?</label><textarea name="statement" required minlength="10" placeholder="Mention relevant skills, availability, and previous experience."></textarea></div>
    <div class="actions"><button class="btn" type="submit">Submit Application</button><a class="btn secondary" href="<%=ctx%>/ta/jobs">Back to Jobs</a></div>
</form>
<% } %>
<%@ include file="../common/footer.jspf" %>
