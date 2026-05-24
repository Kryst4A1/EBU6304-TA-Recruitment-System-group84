<%@ page import="cn.bupt.tarecruitment.util.WebUtils" %>
<!doctype html>
<html lang="en">
<head><meta charset="UTF-8"><meta name="viewport" content="width=device-width, initial-scale=1.0"><title>Register · TA Recruitment</title><link rel="stylesheet" href="<%=request.getContextPath()%>/assets/app.css"></head>
<body class="auth-body">
<div class="auth-card card">
    <section class="auth-panel">
        <a class="brand" href="<%=request.getContextPath()%>/login"><span class="brand-mark">TA</span><span><strong>Recruitment</strong><small>BUPT International School</small></span></a>
        <h1>Create a role-based account</h1>
        <p>Registration supports all stakeholders required by the coursework: TA applicants, Module Organisers, and Administrators.</p>
        <p class="ai-note">TA accounts automatically receive an editable applicant profile after registration.</p>
    </section>
    <section class="auth-form">
        <span class="eyebrow">New account</span>
        <h1>Register</h1>
        <% if (request.getAttribute("error") != null) { %><div class="error"><%=WebUtils.html(request.getAttribute("error").toString())%></div><% } %>
        <% String selectedRole = (String) request.getAttribute("role"); if (selectedRole == null) selectedRole = "TA"; %>
        <form method="post" action="<%=request.getContextPath()%>/register">
            <div class="form-row"><label>Display name</label><input type="text" name="displayName" value="<%=WebUtils.html((String)request.getAttribute("displayName"))%>" placeholder="e.g. Alice Chen"></div>
            <div class="form-row"><label>Username</label><input type="text" name="username" required value="<%=WebUtils.html((String)request.getAttribute("username"))%>" placeholder="3-30 letters, numbers, dot, underscore, hyphen"></div>
            <div class="form-row"><label>Role</label>
                <select name="role">
                    <option value="TA" <%= "TA".equals(selectedRole) ? "selected" : "" %>>Teaching Assistant Applicant</option>
                    <option value="MO" <%= "MO".equals(selectedRole) ? "selected" : "" %>>Module Organiser</option>
                    <option value="ADMIN" <%= "ADMIN".equals(selectedRole) ? "selected" : "" %>>Administrator</option>
                </select>
            </div>
            <div class="grid two">
                <div class="form-row"><label>Password</label><input type="password" name="password" required></div>
                <div class="form-row"><label>Confirm password</label><input type="password" name="confirmPassword" required></div>
            </div>
            <div class="actions">
                <button class="btn" type="submit">Create account</button>
                <a class="btn secondary" href="<%=request.getContextPath()%>/login">Back to login</a>
            </div>
        </form>
    </section>
</div>
</body></html>
