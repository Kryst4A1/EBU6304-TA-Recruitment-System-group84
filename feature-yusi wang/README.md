# TA Recruitment Web Final

Final lightweight Java Servlet/JSP implementation for the **International School Teaching Assistant Recruitment System** coursework.

This build follows the mandatory restrictions in the handout:

- Java Servlet/JSP web application, compatible with Tomcat 9.
- CSV/text-file persistence under `data/`.
- No database.
- No Spring Boot or heavyweight framework.
- Role-based functionality for TA, Module Organiser, and Admin.

## Final Feature Set

### TA Applicant

- Register and log in as a TA.
- Maintain applicant profile: name, email, major, year, availability, skills, experience, self-introduction.
- Upload and preview CV files: PDF, DOC, DOCX, TXT.
- Search and browse open jobs.
- View explainable skill-match score, matched skills, and missing skills.
- Submit applications.
- Track application status: Submitted, Shortlisted, Selected, Rejected.

### Module Organiser

- Register and log in as an MO.
- Create, edit, close, and reopen job posts.
- Define module/activity code, title, semester, description, required skills, and weekly hours.
- View applicants for each job.
- Review applicant profile, statement, CV evidence, and skill-match explanation.
- Update decisions: Submitted, Shortlisted, Selected, Rejected.
- Selecting an applicant automatically creates a TA assignment for workload tracking.

### Admin

- Register and log in as an Admin.
- View all selected TA assignments and total weekly workload.
- Highlight overloaded TAs using a threshold of 12 hours/week.
- View explainable workload rebalancing recommendations.
- Audit all applications across applicants, modules, MOs, and statuses.
- Filter application audit records by status and keyword.

## AI-Assisted Features

No external AI API is used. The project implements deterministic, explainable decision-support logic:

- Skill matching compares job-required skills with TA profile skills.
- Missing skills are listed explicitly.
- Workload balancing recommends moving the largest assignment from an overloaded TA to the least-loaded safe receiver.

These features are advisory only. Final decisions remain with human users.

## Technology Choices

- Java 17
- Java Servlet API 4.0.1
- JSP
- Tomcat 9
- CSV storage
- Plain Java tests using `assert`

## Project Structure

```text
TARecruitmentWebFinal/
  data/                         Seed CSV data
  scripts/                      Build and test scripts
  src/main/java/                Java source code
  src/main/webapp/              JSP views and CSS
  src/test/java/                Plain Java logic tests
  pom.xml                       Maven WAR project file
  README.md                     Setup and feature guide
  USER_MANUAL.md                User-facing manual
  DESIGN_AND_TESTING.md         Architecture and testing notes
  FINAL_CHECKLIST.md            Final function verification checklist
```

## Demo Accounts

| Role | Username | Password |
| --- | --- | --- |
| TA | `ta01` | `ta01` |
| TA | `ta02` | `ta02` |
| TA | `ta03` | `ta03` |
| MO | `mo01` | `mo01` |
| Admin | `admin01` | `admin01` |

You can also register new TA, MO, and Admin users from the registration page.

## Run in IntelliJ IDEA Ultimate

1. Open the folder containing `pom.xml`.
2. Set Project SDK to JDK 17.
3. Reload Maven.
4. Add a Tomcat Server → Local configuration.
5. Select a Tomcat 9 installation directory.
6. Add artifact: `ta-recruitment-web-final:war exploded`.
7. Set Application context to:

```text
/ta-recruitment-web-final
```

8. Add VM option so CSV data is read from the project folder:

```text
-Dta.data.dir=ABSOLUTE_PATH_TO_PROJECT/data
```

Example on Windows:

```text
-Dta.data.dir=D:\JavaProjects\TARecruitmentWebFinal\data
```

9. Run Tomcat and open:

```text
http://localhost:8080/ta-recruitment-web-final/
```

## Build with Maven

```bash
mvn clean package
```

Deploy this WAR to Tomcat 9:

```text
target/ta-recruitment-web-final.war
```

## Build without Maven

If a servlet API JAR is available on the machine:

```bash
./scripts/build_war.sh
```

Output:

```text
build/ta-recruitment-web-final.war
```

If the script cannot locate the servlet API JAR, run:

```bash
SERVLET_API_JAR=/path/to/servlet-api.jar ./scripts/build_war.sh
```

## Run Tests

```bash
./scripts/run_tests.sh
```

The tests cover:

- CSV parsing and multiline field handling.
- HTML escaping and UI helper methods.
- Skill matching, matched/missing skill explanation.
- Multi-role registration.
- Job close/reopen.
- Application decision updates.
- Automatic assignment synchronisation.
- Workload overload and rebalancing recommendation logic.

## Recommended Demo Flow

1. Register a new TA, MO, and Admin account to show multi-role registration.
2. Sign in as `ta01`, update profile, and upload a CV.
3. Browse open jobs and inspect skill-match explanation.
4. Submit an application.
5. Sign in as `mo01`, create a job, then review applicants.
6. Shortlist or select an applicant.
7. Sign in as `admin01`, inspect workload board and application audit.

## Notes for Submission

- Rename the ZIP file to match the group number if required, for example `Software_groupXXX.zip`.
- Keep individual GitHub branches and commits visible for contribution evidence.
- Do not replace CSV files with a database, because the coursework restriction requires simple text-file storage.
