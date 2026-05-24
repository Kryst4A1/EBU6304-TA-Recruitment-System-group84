<%@ page import="cn.bupt.tarecruitment.model.JobPost" %>
<%@ include file="../common/header.jspf" %>
<% JobPost job = (JobPost) request.getAttribute("job"); boolean edit = job.getId() != null && !job.getId().isBlank(); %>
<h1 class="page-title"><%=edit ? "Edit Job" : "Create Job"%></h1>
<p class="page-subtitle">Define a clear module/activity role, required skills, and weekly workload. These fields drive applicant matching and admin workload monitoring.</p>
<form class="card" method="post" action="<%=ctx%>/mo/jobs">
    <input type="hidden" name="action" value="save">
    <input type="hidden" name="jobId" value="<%=WebUtils.html(job.getId())%>">
    <div class="grid two">
        <div class="form-row"><label>Module code / activity code</label><input type="text" name="moduleCode" required value="<%=WebUtils.html(job.getModuleCode())%>" placeholder="e.g. EBU6304"></div>
        <div class="form-row"><label>Title</label><input type="text" name="title" required value="<%=WebUtils.html(job.getTitle())%>" placeholder="e.g. Software Engineering TA"></div>
        <div class="form-row"><label>Semester</label><input type="text" name="semester" required value="<%=WebUtils.html(job.getSemester())%>" placeholder="e.g. Semester 2, 2026"></div>
        <div class="form-row"><label>Hours per week</label><input type="number" name="hoursPerWeek" min="1" max="20" required value="<%=job.getHoursPerWeek()%>"></div>
    </div>
    <div class="form-row"><label>Description</label><textarea name="description" required placeholder="Responsibilities, schedule expectations, marking/lab/invigilation details"><%=WebUtils.html(job.getDescription())%></textarea></div>
    <div class="form-row"><label>Required skills</label><textarea name="requiredSkills" required placeholder="Java; tutoring; software engineering; communication"><%=WebUtils.html(job.getRequiredSkills())%></textarea><p class="muted">Separate skills with semicolons, commas, slashes, or line breaks.</p></div>
    <div class="actions"><button class="btn" type="submit">Save Job</button><a class="btn secondary" href="<%=ctx%>/mo/jobs">Cancel</a></div>
</form>
<%@ include file="../common/footer.jspf" %>
