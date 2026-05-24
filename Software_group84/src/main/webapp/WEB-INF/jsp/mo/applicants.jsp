<%@ page import="java.util.*" %>
<%@ page import="cn.bupt.tarecruitment.model.*" %>
<%@ include file="../common/header.jspf" %>
<% JobPost job = (JobPost) request.getAttribute("job"); List<Application> apps = (List<Application>) request.getAttribute("applications"); Map<String, Profile> profileMap = (Map<String, Profile>) request.getAttribute("profileMap"); Map<String, SkillMatch> matches = (Map<String, SkillMatch>) request.getAttribute("matches"); %>
<h1 class="page-title">Applicants</h1>
<p class="page-subtitle"><%=WebUtils.html(job.label())%> · applicants are sorted by skill-match score to speed up review.</p>
<div class="card compact"><span class="badge <%=WebUtils.statusClass(job.getStatus())%>"><%=WebUtils.html(job.getStatus())%></span> <strong><%=job.getHoursPerWeek()%>h/week</strong> · <span class="muted"><%=WebUtils.html(job.getRequiredSkills())%></span></div>
<div class="card">
<% if (apps == null || apps.isEmpty()) { %>
    <div class="empty-state"><strong>No applicants yet.</strong><span>Keep this job open so TA applicants can apply.</span></div>
<% } else { %>
<div class="table-wrap"><table class="table">
<tr><th>Applicant</th><th>Status</th><th>Match</th><th>Statement</th><th></th></tr>
<% for (Application app : apps) { Profile p = profileMap.get(app.getTaUserId()); SkillMatch m = matches.get(app.getId()); %>
<tr>
    <td><strong><%=p == null ? WebUtils.html(app.getTaUserId()) : WebUtils.html(p.getFullName())%></strong><br><span class="muted"><%=p == null ? "" : WebUtils.html(p.getMajor())%></span></td>
    <td><span class="badge <%=WebUtils.statusClass(app.getStatus())%>"><%=WebUtils.html(app.getStatus())%></span></td>
    <td><span class="badge <%=m.badgeClass()%>"><%=m.getScore()%>% <%=m.getLevel()%></span><div class="progress"><span style="width:<%=m.getScore()%>%"></span></div><span class="muted"><%=WebUtils.html(m.getExplanation())%></span></td>
    <td><%=WebUtils.html(WebUtils.shortText(app.getStatement(), 120))%></td>
    <td><a class="btn small" href="<%=ctx%>/mo/applicant-review?appId=<%=WebUtils.html(app.getId())%>">Review</a></td>
</tr>
<% } %>
</table></div>
<% } %>
<p class="ai-note">The match result is advisory. The final decision must consider availability, CV, statement, and module needs.</p>
</div>
<%@ include file="../common/footer.jspf" %>
