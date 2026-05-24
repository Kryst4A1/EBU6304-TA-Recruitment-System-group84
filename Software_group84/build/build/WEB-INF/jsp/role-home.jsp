<%@ page import="java.util.*" %>
<%@ include file="common/header.jspf" %>
<h1 class="page-title">Dashboard</h1>
<p class="page-subtitle">Role-based workspace for the complete TA recruitment lifecycle: profile, job posting, application review, selection, and workload monitoring.</p>
<div class="card hero-card">
    <span class="eyebrow">Current role</span>
    <h2><%=currentUser.getRole()%> workspace</h2>
    <p class="muted">Welcome, <%=WebUtils.html(currentUser.getDisplayName())%>. Use the cards below to start the core workflow.</p>
</div>
<div class="grid">
<% if (currentUser.getRole() == Role.TA) { %>
    <div class="card"><h2>Applicant Profile</h2><p>Maintain your skills, availability, experience, and CV so MOs can assess your application.</p><a class="btn" href="<%=ctx%>/ta/profile">Update Profile</a></div>
    <div class="card"><h2>Open Jobs</h2><p>Browse TA jobs. The system sorts jobs by explainable skill-match score.</p><a class="btn" href="<%=ctx%>/ta/jobs">Find Jobs</a></div>
    <div class="card"><h2>Application Status</h2><p>Track Submitted, Shortlisted, Selected, and Rejected decisions.</p><a class="btn" href="<%=ctx%>/ta/applications">View Applications</a></div>
<% } else if (currentUser.getRole() == Role.MO) { %>
    <div class="card"><h2>Post Jobs</h2><p>Create and edit module or activity TA opportunities with required skills and weekly hours.</p><a class="btn" href="<%=ctx%>/mo/jobs?action=new">Create Job</a></div>
    <div class="card"><h2>Review Applicants</h2><p>Open your jobs to shortlist, select, or reject applicants using profile and matching evidence.</p><a class="btn" href="<%=ctx%>/mo/jobs">Manage Jobs</a></div>
<% } else { %>
    <div class="card"><h2>Workload Board</h2><p>Monitor selected TA assignments and detect overloaded applicants.</p><a class="btn" href="<%=ctx%>/admin/workload">Open Board</a></div>
    <div class="card"><h2>Application Audit</h2><p>Review all recruitment applications across MOs, modules, statuses, and applicants.</p><a class="btn" href="<%=ctx%>/admin/applications">Open Audit</a></div>
<% } %>
</div>
<%@ include file="common/footer.jspf" %>
