<%@ page import="java.util.*" %>
<%@ page import="cn.bupt.tarecruitment.model.*" %>
<%@ include file="../common/header.jspf" %>
<% List<Application> apps = (List<Application>) request.getAttribute("applications"); Map<String, JobPost> jobMap = (Map<String, JobPost>) request.getAttribute("jobMap"); %>
<h1 class="page-title">My Applications</h1>
<p class="page-subtitle">Track decisions made by Module Organisers. Selected applications are automatically counted in the admin workload board.</p>
<div class="card">
<% if (apps == null || apps.isEmpty()) { %>
    <div class="empty-state"><strong>No applications yet.</strong><span>Find an open TA job and submit your first application.</span><br><br><a class="btn" href="<%=ctx%>/ta/jobs">Browse Jobs</a></div>
<% } else { %>
<div class="table-wrap"><table class="table">
<tr><th>Job</th><th>Status</th><th>Statement</th><th>Submitted</th></tr>
<% for (Application app : apps) { JobPost job = jobMap.get(app.getJobId()); %>
<tr>
    <td><strong><%=job == null ? WebUtils.html(app.getJobId()) : WebUtils.html(job.label())%></strong><br><span class="muted"><%=job == null ? "" : WebUtils.html(job.getSemester())%></span></td>
    <td><span class="badge <%=WebUtils.statusClass(app.getStatus())%>"><%=WebUtils.html(app.getStatus())%></span></td>
    <td><%=WebUtils.html(WebUtils.shortText(app.getStatement(), 120))%></td>
    <td><%=WebUtils.html(app.getCreatedAt())%></td>
</tr>
<% } %>
</table></div>
<% } %>
</div>
<%@ include file="../common/footer.jspf" %>
