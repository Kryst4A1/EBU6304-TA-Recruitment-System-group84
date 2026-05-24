<%@ page import="java.util.*" %>
<%@ page import="cn.bupt.tarecruitment.model.*" %>
<%@ include file="../common/header.jspf" %>
<% List<JobPost> jobs = (List<JobPost>) request.getAttribute("jobs"); int openCount = 0; if (jobs != null) for (JobPost j : jobs) if ("OPEN".equals(j.getStatus())) openCount++; %>
<h1 class="page-title">MO Job Posts</h1>
<p class="page-subtitle">Create module or school-activity jobs, manage job status, and review applicants for each posting.</p>
<div class="stats">
    <div class="stat"><span>Total posts</span><strong><%=jobs == null ? 0 : jobs.size()%></strong></div>
    <div class="stat"><span>Open posts</span><strong><%=openCount%></strong></div>
</div>
<p class="actions"><a class="btn" href="<%=ctx%>/mo/jobs?action=new">Create New Job</a></p>
<div class="card">
<% if (jobs == null || jobs.isEmpty()) { %>
    <div class="empty-state"><strong>No job posts yet.</strong><span>Create your first job to receive TA applications.</span></div>
<% } else { %>
<div class="table-wrap"><table class="table">
<tr><th>Job</th><th>Skills</th><th>Hours</th><th>Status</th><th>Actions</th></tr>
<% for (JobPost job : jobs) { %>
<tr>
    <td><strong><%=WebUtils.html(job.getModuleCode())%></strong><br><%=WebUtils.html(job.getTitle())%><br><span class="muted"><%=WebUtils.html(job.getSemester())%></span></td>
    <td><%=WebUtils.html(job.getRequiredSkills())%></td>
    <td><%=job.getHoursPerWeek()%>h / week</td>
    <td><span class="badge <%=WebUtils.statusClass(job.getStatus())%>"><%=WebUtils.html(job.getStatus())%></span></td>
    <td>
        <div class="actions">
            <a class="btn secondary small" href="<%=ctx%>/mo/jobs?action=edit&id=<%=WebUtils.html(job.getId())%>">Edit</a>
            <a class="btn small" href="<%=ctx%>/mo/applicants?jobId=<%=WebUtils.html(job.getId())%>">Applicants</a>
            <% if ("OPEN".equals(job.getStatus())) { %>
            <form class="inline-form" method="post" action="<%=ctx%>/mo/jobs"><input type="hidden" name="action" value="close"><input type="hidden" name="jobId" value="<%=WebUtils.html(job.getId())%>"><button class="btn danger small" type="submit">Close</button></form>
            <% } else { %>
            <form class="inline-form" method="post" action="<%=ctx%>/mo/jobs"><input type="hidden" name="action" value="reopen"><input type="hidden" name="jobId" value="<%=WebUtils.html(job.getId())%>"><button class="btn secondary small" type="submit">Reopen</button></form>
            <% } %>
        </div>
    </td>
</tr>
<% } %>
</table></div>
<% } %>
</div>
<%@ include file="../common/footer.jspf" %>
