<%@ page import="java.util.*" %>
<%@ page import="cn.bupt.tarecruitment.model.*" %>
<%@ include file="../common/header.jspf" %>
<% List<WorkloadRow> rows = (List<WorkloadRow>) request.getAttribute("rows"); List<WorkloadRecommendation> recs = (List<WorkloadRecommendation>) request.getAttribute("recommendations"); Integer threshold = (Integer) request.getAttribute("threshold"); int overloaded = 0; int totalHours = 0; if (rows != null) for (WorkloadRow r : rows) { if (r.isOverloaded()) overloaded++; totalHours += r.getTotalHours(); } %>
<h1 class="page-title">Admin Workload Board</h1>
<p class="page-subtitle">Selected applications are converted into assignments. This board checks weekly TA workload and offers explainable rebalancing advice.</p>
<div class="stats">
    <div class="stat"><span>Total TAs</span><strong><%=rows == null ? 0 : rows.size()%></strong></div>
    <div class="stat"><span>Total hours</span><strong><%=totalHours%></strong></div>
    <div class="stat"><span>Overloaded</span><strong><%=overloaded%></strong></div>
    <div class="stat"><span>Threshold</span><strong><%=threshold%>h</strong></div>
</div>
<div class="card">
<div class="table-wrap"><table class="table">
<tr><th>TA</th><th>Total Hours</th><th>Assignments</th><th>Status</th></tr>
<% for (WorkloadRow row : rows) { %>
<tr class="<%=row.isOverloaded() ? "overloaded" : ""%>">
    <td><strong><%=WebUtils.html(row.getTaName())%></strong></td>
    <td><%=row.getTotalHours()%>h <div class="progress"><span style="width:<%=Math.min(100, row.getTotalHours() * 100 / threshold)%>%"></span></div></td>
    <td><% if (row.getAssignments().isEmpty()) { %><span class="muted">No selected assignments.</span><% } for (String label : row.getAssignments()) { %><div><%=WebUtils.html(label)%></div><% } %></td>
    <td><span class="badge <%=row.isOverloaded() ? "weak" : "strong"%>"><%=row.isOverloaded() ? "OVERLOADED" : "OK"%></span></td>
</tr>
<% } %>
</table></div>
</div>
<div class="card">
<h2>AI-Assisted Workload Recommendations</h2>
<% if (recs.isEmpty()) { %>
    <div class="empty-state"><strong>No safe rebalancing suggestion is needed or available.</strong><span>Current selected assignments are within the threshold, or no receiver can safely take the hours.</span></div>
<% } else { for (WorkloadRecommendation r : recs) { %>
    <div class="card compact">
        <strong>Move <%=r.getHours()%>h: <%=WebUtils.html(r.getJobLabel())%></strong>
        <p>From <%=WebUtils.html(r.getFromTaName())%> to <%=WebUtils.html(r.getToTaName())%>.</p>
        <p class="muted"><%=WebUtils.html(r.getReason())%></p>
    </div>
<% }} %>
<p class="ai-note">Recommendations are not executed automatically. Admins should confirm with the relevant MO and TA before reassignment.</p>
</div>
<%@ include file="../common/footer.jspf" %>
