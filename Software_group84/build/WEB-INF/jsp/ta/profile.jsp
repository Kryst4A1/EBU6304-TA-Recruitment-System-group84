<%@ page import="cn.bupt.tarecruitment.model.Profile" %>
<%@ include file="../common/header.jspf" %>
<% Profile p = (Profile) request.getAttribute("profile"); %>
<h1 class="page-title">Applicant Profile</h1>
<p class="page-subtitle">This profile is used by Module Organisers during applicant review and by the matching feature when comparing your skills with job requirements.</p>
<form class="card" method="post" enctype="multipart/form-data" action="<%=ctx%>/ta/profile">
    <div class="grid two">
        <div class="form-row"><label>Full name</label><input type="text" name="fullName" required value="<%=WebUtils.html(p.getFullName())%>"></div>
        <div class="form-row"><label>Email</label><input type="email" name="email" value="<%=WebUtils.html(p.getEmail())%>"></div>
        <div class="form-row"><label>Major</label><input type="text" name="major" value="<%=WebUtils.html(p.getMajor())%>"></div>
        <div class="form-row"><label>Year</label><input type="text" name="year" value="<%=WebUtils.html(p.getYear())%>" placeholder="e.g. Year 3"></div>
    </div>
    <div class="form-row"><label>Availability</label><textarea name="availability" placeholder="Available days, time windows, exam-week restrictions..."><%=WebUtils.html(p.getAvailability())%></textarea></div>
    <div class="form-row"><label>Skills</label><textarea name="skills" required placeholder="Java; tutoring; software engineering; invigilation; Python"><%=WebUtils.html(p.getSkills())%></textarea><p class="muted">Separate skills with semicolons, commas, slashes, or line breaks.</p></div>
    <div class="form-row"><label>Experience</label><textarea name="experience" placeholder="Previous TA, lab support, marking, or project experience"><%=WebUtils.html(p.getExperience())%></textarea></div>
    <div class="form-row"><label>Self introduction</label><textarea name="introduction" placeholder="Briefly explain why you are suitable for TA work"><%=WebUtils.html(p.getIntroduction())%></textarea></div>
    <div class="form-row"><label>CV file</label><input type="file" name="cv" accept=".pdf,.doc,.docx,.txt"> <% if (p.getCvFileName() != null && !p.getCvFileName().isBlank()) { %><a class="btn secondary small" href="<%=ctx%>/cv/preview?file=<%=WebUtils.html(p.getCvFileName())%>" target="_blank">Preview current CV</a><% } %><p class="muted">Accepted: PDF, DOC, DOCX, TXT. Max size: 5 MB.</p></div>
    <div class="actions"><button class="btn" type="submit">Save Profile</button><a class="btn secondary" href="<%=ctx%>/ta/jobs">View Open Jobs</a></div>
</form>
<%@ include file="../common/footer.jspf" %>
