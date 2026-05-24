<%@ page import="cn.bupt.tarecruitment.util.WebUtils" %>
<!doctype html>
<html lang="en">
<head><meta charset="UTF-8"><meta name="viewport" content="width=device-width, initial-scale=1.0"><title>Login · TA Recruitment</title><link rel="stylesheet" href="<%=request.getContextPath()%>/assets/app.css"></head>
<body class="auth-body">
<div class="auth-card card">
    <section class="auth-panel">
        <a class="brand" href="<%=request.getContextPath()%>/login"><span class="brand-mark">TA</span><span><strong>Recruitment</strong><small>BUPT International School</small></span></a>
        <h1>Teaching Assistant Recruitment System</h1>
        <p>Use the role-based workspace to apply for TA jobs, manage module recruitment, and monitor workload balance.</p>
        <div class="stats">
            <div class="stat"><span>Storage</span><strong>CSV</strong></div>
            <div class="stat"><span>Platform</span><strong>JSP</strong></div>
        </div>
    </section>
    <section class="auth-form">
        <span class="eyebrow">Secure demo sign-in</span>
        <h1>Sign in</h1>
        <p class="muted">TA, Module Organiser, and Admin accounts are supported.</p>
        <% if (request.getAttribute("flash") != null) { %><div class="flash"><%=WebUtils.html(request.getAttribute("flash").toString())%></div><% } %>
        <% if (request.getAttribute("error") != null) { %><div class="error"><%=WebUtils.html(request.getAttribute("error").toString())%></div><% } %>
        <form method="post" action="<%=request.getContextPath()%>/login">
            <div class="form-row"><label>Username</label><input type="text" name="username" required value="<%=WebUtils.html((String)request.getAttribute("username"))%>"></div>
            <div class="form-row"><label>Password</label><input type="password" name="password" required></div>
            <div class="actions">
                <button class="btn" type="submit">Login</button>
                <a class="btn secondary" href="<%=request.getContextPath()%>/register">Create account</a>
            </div>
        </form>
        <p class="muted">Demo accounts: ta01/ta01, mo01/mo01, admin01/admin01.</p>
    </section>
</div>
</body></html>
