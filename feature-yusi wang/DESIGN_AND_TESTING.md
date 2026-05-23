# Design and Testing Notes

## 1. Architecture

The final system uses a lightweight MVC-style Java web architecture.

```text
Browser
  -> Servlet Filter
  -> Servlet Controller
  -> Service Layer
  -> CsvDataStore
  -> CSV files
  -> JSP view
```

### Main Packages

```text
cn.bupt.tarecruitment.model        Domain models
cn.bupt.tarecruitment.repository   CSV persistence
cn.bupt.tarecruitment.service      Service interfaces
cn.bupt.tarecruitment.service.impl Service implementations
cn.bupt.tarecruitment.servlet      Controllers
cn.bupt.tarecruitment.filter       Authentication filter
cn.bupt.tarecruitment.util         Utility functions
```

## 2. Design Decisions

### 2.1 Servlet/JSP Instead of Frameworks

The coursework restricts the implementation to a standalone Java application or a lightweight Servlet/JSP application. This project uses Servlet/JSP and avoids Spring Boot to keep the focus on software engineering logic, modularity, and testable services.

### 2.2 CSV Storage

All data is persisted in CSV files. The repository layer centralises reading and writing logic. This avoids duplication and makes it easier to replace storage later if the coursework scope changes.

The final version improves CSV reliability by supporting:

- Commas in text fields.
- Quotes in text fields.
- Newlines in text fields.
- Temporary-file replacement during writes.

### 2.3 Role-Based Access

A global authentication filter protects application pages. Role-specific checks in controllers ensure that:

- TA users cannot access MO/Admin pages.
- MO users can only manage their own jobs.
- Admin pages require the Admin role.

### 2.4 Explainable AI-Assisted Logic

The project does not call an external AI service. Instead, it implements transparent rule-based advisory functions:

- Skill matching splits skills and compares TA profile skills against job-required skills.
- The result includes score, level, matched skills, missing skills, and explanation.
- Workload balancing identifies overloaded TAs and suggests a safe reassignment where possible.

This satisfies the spirit of AI-assisted decision support without hiding logic from users or markers.

## 3. Core Workflows

### 3.1 TA Workflow

```text
Register/Login -> Profile/CV -> Browse Jobs -> View Match -> Apply -> Track Status
```

### 3.2 MO Workflow

```text
Register/Login -> Post Job -> Review Applicants -> Shortlist/Select/Reject -> Assignment Sync
```

### 3.3 Admin Workflow

```text
Register/Login -> Workload Board -> Rebalancing Advice -> Application Audit
```

## 4. Testing Strategy

The project uses plain Java tests because the coursework focuses on fundamental SE practices and the project avoids additional testing frameworks.

Run tests:

```bash
./scripts/run_tests.sh
```

The test entry point is:

```text
src/test/java/cn/bupt/tarecruitment/CoreLogicTest.java
```

## 5. Test Coverage

The current tests cover:

- CSV escaping and parsing.
- Multiline CSV fields.
- HTML escaping.
- Status-to-CSS mapping.
- Skill matching strong case.
- Skill matching missing-skill case.
- Multi-role registration.
- TA profile auto-creation on TA registration.
- Job close/reopen logic.
- Application submission.
- MO decision update.
- Assignment creation when status becomes Selected.
- Assignment removal when status changes away from Selected.
- Workload row construction.
- Workload recommendation generation.

## 6. Manual Acceptance Test Script

### TA Acceptance Test

1. Register a new TA account.
2. Login as the new TA.
3. Update profile with at least one skill.
4. Upload a small PDF/TXT CV.
5. Open jobs and inspect match score.
6. Apply for a job.
7. Confirm the application appears in My Applications.

Expected result: TA can complete the application workflow and view status.

### MO Acceptance Test

1. Register a new MO account.
2. Login as MO.
3. Create a new job.
4. Login as TA and apply for that job.
5. Login back as MO.
6. Open Applicants for that job.
7. Review the applicant.
8. Select the applicant.

Expected result: MO can post jobs and make decisions.

### Admin Acceptance Test

1. Login as Admin.
2. Open Workload Board.
3. Confirm selected applications appear as assignments.
4. Check overload status.
5. Open Application Audit.
6. Filter by `SELECTED`.

Expected result: Admin can monitor workload and audit applications.

## 7. Known Scope Boundaries

The final version intentionally avoids:

- Database storage.
- Spring Boot.
- External AI APIs.
- Email notification integration.
- Complex password hashing infrastructure.

These are outside the intended scope of the assignment and would add framework complexity not required by the handout.
