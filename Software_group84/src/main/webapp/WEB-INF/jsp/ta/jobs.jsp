<%@ page import="java.util.*" %>
<%@ page import="cn.bupt.tarecruitment.model.*" %>
<%@ include file="../common/header.jspf" %>
<% List<JobPost> jobs = (List<JobPost>) request.getAttribute("jobs"); Map<String, SkillMatch> matches = (Map<String, SkillMatch>) request.getAttribute("matches"); String query = (String) request.getAttribute("query"); %>
<h1 class="page-title">Open Jobs</h1>
<p class="page-subtitle">Jobs are shown with explainable skill-match scores based on your profile. Matching supports decisions, but it does not replace MO judgement.</p>
<form class="filters" method="get" action="<%=ctx%>/ta/jobs">
    <div class="form-row"><label>Search by module, title, semester, skill, or description</label><input type="text" name="q" value="<%=WebUtils.html(query)%>" placeholder="e.g. Java, EBU6304, invigilation"></div>
    <button class="btn" type="submit">Search</button>
    <a class="btn secondary" href="<%=ctx%>/ta/jobs">Reset</a>
</form>
<div class="card">
<% if (jobs == null || jobs.isEmpty()) { %>
    <div class="empty-state"><strong>No open jobs found.</strong><span>Try changing the search keyword or check again later.</span></div>
<% } else { %>
<div class="table-wrap">
<table class="table">
<tr><th>Job</th><th>Required skills</th><th>Hours</th><th>Match</th><th></th></tr>
<% for (JobPost job : jobs) { SkillMatch m = matches.get(job.getId()); %>
<tr>
    <td><strong><%=WebUtils.html(job.getModuleCode())%></strong><br><%=WebUtils.html(job.getTitle())%><br><span class="muted"><%=WebUtils.html(job.getSemester())%></span></td>
    <td><%=WebUtils.html(job.getRequiredSkills())%></td>
    <td><%=job.getHoursPerWeek()%>h / week</td>
    <td><span class="badge <%=m.badgeClass()%>"><%=m.getScore()%>% <%=m.getLevel()%></span><div class="progress"><span style="width:<%=m.getScore()%>%"></span></div><span class="muted"><%=WebUtils.html(m.getExplanation())%></span></td>
    <td><a class="btn small" href="<%=ctx%>/ta/job-detail?id=<%=WebUtils.html(job.getId())%>">Details</a></td>
</tr>
<% } %>
</table>
</div>
<% } %>
<p class="ai-note">Explainability: the system compares required skills with profile skills, lists matched and missing skills, then calculates a percentage score.</p>
</div>
<%@ include file="../common/footer.jspf" %>
