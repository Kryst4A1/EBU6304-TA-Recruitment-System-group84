<%@ page import="java.util.*" %>
<%@ page import="cn.bupt.tarecruitment.model.*" %>
<%@ include file="../common/header.jspf" %>
<% List<Application> apps = (List<Application>) request.getAttribute("applications"); Map<String, JobPost> jobMap = (Map<String, JobPost>) request.getAttribute("jobMap"); Map<String, Profile> profileMap = (Map<String, Profile>) request.getAttribute("profileMap"); Map<String, User> userMap = (Map<String, User>) request.getAttribute("userMap"); String status = (String) request.getAttribute("status"); String query = (String) request.getAttribute("query"); %>
<h1 class="page-title">Application Audit</h1>
<p class="page-subtitle">Read-only overview of all TA recruitment applications across applicants, modules, MOs, and decision statuses.</p>
<form class="filters" method="get" action="<%=ctx%>/admin/applications">
    <div class="form-row"><label>Search</label><input type="text" name="q" value="<%=WebUtils.html(query)%>" placeholder="Applicant, job, module, MO, statement"></div>
    <div class="form-row"><label>Status</label><select name="status">
        <option value="" <%=status.isBlank() ? "selected" : ""%>>All statuses</option>
        <option value="SUBMITTED" <%="SUBMITTED".equals(status) ? "selected" : ""%>>Submitted</option>
        <option value="SHORTLISTED" <%="SHORTLISTED".equals(status) ? "selected" : ""%>>Shortlisted</option>
        <option value="SELECTED" <%="SELECTED".equals(status) ? "selected" : ""%>>Selected</option>
        <option value="REJECTED" <%="REJECTED".equals(status) ? "selected" : ""%>>Rejected</option>
    </select></div>
    <button class="btn" type="submit">Filter</button>
    <a class="btn secondary" href="<%=ctx%>/admin/applications">Reset</a>
</form>
<div class="card">
<% if (apps == null || apps.isEmpty()) { %>
    <div class="empty-state"><strong>No applications match the current filter.</strong><span>Reset filters or wait for TAs to submit applications.</span></div>
<% } else { %>
<div class="table-wrap"><table class="table">
<tr><th>Applicant</th><th>Job</th><th>Module</th><th>MO</th><th>Status</th><th>Submitted</th></tr>
<% for (Application app : apps) { JobPost job = jobMap.get(app.getJobId()); Profile p = profileMap.get(app.getTaUserId()); User mo = job == null ? null : userMap.get(job.getOwnerId()); %>
<tr>
    <td><strong><%=WebUtils.html(p == null ? app.getTaUserId() : p.getFullName())%></strong></td>
    <td><%=WebUtils.html(job == null ? app.getJobId() : job.getTitle())%></td>
    <td><%=WebUtils.html(job == null ? "-" : job.getModuleCode())%></td>
    <td><%=WebUtils.html(mo == null ? "-" : mo.getDisplayName())%></td>
    <td><span class="badge <%=WebUtils.statusClass(app.getStatus())%>"><%=WebUtils.html(app.getStatus())%></span></td>
    <td><%=WebUtils.html(app.getCreatedAt())%></td>
</tr>
<% } %>
</table></div>
<% } %>
</div>
<%@ include file="../common/footer.jspf" %>
