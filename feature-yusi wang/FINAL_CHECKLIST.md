# Final Function Verification Checklist

## Coursework Restrictions

- [x] Java Servlet/JSP web application.
- [x] Runs on Tomcat 9 with `javax.servlet`.
- [x] Data stored in CSV files under `data/`.
- [x] No database.
- [x] No Spring Boot.
- [x] Core product prototype implemented as working software.

## TA Applicant Functions

- [x] TA can register.
- [x] TA can log in and log out.
- [x] TA can create and update profile.
- [x] TA can upload and preview CV.
- [x] TA can browse open jobs.
- [x] TA can search jobs.
- [x] TA can view match score, matched skills, and missing skills.
- [x] TA can apply for jobs.
- [x] TA can check application status.

## Module Organiser Functions

- [x] MO can register.
- [x] MO can log in and log out.
- [x] MO can post jobs.
- [x] MO can edit jobs.
- [x] MO can close and reopen jobs.
- [x] MO can view applicants for own jobs.
- [x] MO can inspect applicant profile and CV.
- [x] MO can view AI-assisted skill-match explanation.
- [x] MO can shortlist, select, or reject applicants.
- [x] Selection creates TA assignment records for workload monitoring.

## Admin Functions

- [x] Admin can register.
- [x] Admin can log in and log out.
- [x] Admin can view TA workload.
- [x] Admin can see overloaded TA highlighting.
- [x] Admin can view workload-balancing recommendations.
- [x] Admin can audit all applications.
- [x] Admin can filter applications by keyword and status.

## Code Quality Improvements in Final Version

- [x] Improved responsive UI with sidebar, topbar, cards, stats, empty states, filters, and clearer badges.
- [x] Added validation for registration, TA profile, CV type, job posting, and application statement.
- [x] Added robust CSV parsing for fields containing commas, quotes, and line breaks.
- [x] Added safer CSV writes with temporary file replacement.
- [x] Added job reopen function.
- [x] Added search/filter functions for TA job browsing and admin audits.
- [x] Added additional tests for core workflow logic.

## Tests Run

Command:

```bash
./scripts/run_tests.sh
```

Result:

```text
CoreLogicTest passed.
All tests passed.
```

Command:

```bash
./scripts/build_war.sh
```

Result:

```text
WAR created: build/ta-recruitment-web-final.war
```
