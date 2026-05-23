# User Manual: TA Recruitment Web Final

## 1. Starting the System

Deploy the WAR file to Tomcat 9 or run it from IntelliJ IDEA Ultimate using a Tomcat Server configuration.

Recommended URL:

```text
http://localhost:8080/ta-recruitment-web-final/
```

Recommended VM option:

```text
-Dta.data.dir=ABSOLUTE_PATH_TO_PROJECT/data
```

This ensures the system reads and writes the CSV files in the project `data/` folder.

## 2. Demo Login Accounts

| Role | Username | Password |
| --- | --- | --- |
| TA | `ta01` | `ta01` |
| TA | `ta02` | `ta02` |
| TA | `ta03` | `ta03` |
| MO | `mo01` | `mo01` |
| Admin | `admin01` | `admin01` |

## 3. Registering a New Account

1. Open the login page.
2. Click **Create account**.
3. Enter display name, username, role, password, and confirmation password.
4. Choose one role:
   - Teaching Assistant Applicant
   - Module Organiser
   - Administrator
5. Submit the form.
6. Return to login and sign in with the new account.

TA registration automatically creates an applicant profile. MO and Admin registration creates account access for their role-specific dashboards.

## 4. TA Applicant Workflow

### 4.1 Update Profile

1. Sign in as a TA.
2. Open **Applicant Profile**.
3. Enter or update:
   - Full name
   - Email
   - Major
   - Year
   - Availability
   - Skills
   - Experience
   - Self introduction
4. Upload a CV file if available.
5. Click **Save Profile**.

Accepted CV formats: PDF, DOC, DOCX, TXT. Maximum file size: 5 MB.

### 4.2 Find Jobs

1. Open **Open Jobs**.
2. Search by module, title, semester, skill, or description if required.
3. Review each job's:
   - Required skills
   - Weekly hours
   - Match score
   - Match explanation
4. Click **Details** to inspect the job.

### 4.3 Apply for a Job

1. Open a job detail page.
2. Review matched and missing skills.
3. Write an application statement.
4. Click **Submit Application**.
5. Open **My Applications** to track status.

Possible statuses:

- Submitted
- Shortlisted
- Selected
- Rejected

## 5. Module Organiser Workflow

### 5.1 Create a Job

1. Sign in as an MO.
2. Open **Job Posts**.
3. Click **Create New Job**.
4. Fill in:
   - Module/activity code
   - Title
   - Semester
   - Hours per week
   - Description
   - Required skills
5. Click **Save Job**.

The job becomes visible to TA applicants when its status is Open.

### 5.2 Manage Jobs

From the **Job Posts** page, the MO can:

- Edit an existing job.
- Close an open job.
- Reopen a closed job.
- View applicants for a job.

Closed jobs are hidden from the TA open-job list.

### 5.3 Review Applicants

1. Open **Applicants** for a job.
2. Applicants are sorted by match score.
3. Click **Review**.
4. Check:
   - Applicant profile
   - CV preview
   - Application statement
   - Matched skills
   - Missing skills
   - Match score
5. Choose a decision:
   - Submitted
   - Shortlisted
   - Selected
   - Rejected
6. Click **Update Decision**.

Selecting an applicant automatically adds the job's weekly hours to that TA's workload.

## 6. Admin Workflow

### 6.1 Workload Board

1. Sign in as Admin.
2. Open **Workload Board**.
3. Check each TA's weekly total hours.
4. Review assignment labels.
5. Check overloaded status.

The default overload threshold is 12 hours per week.

### 6.2 Workload Recommendations

The system suggests workload changes when a TA is overloaded and another TA can safely receive the assignment. Recommendations are advisory and are not executed automatically.

### 6.3 Application Audit

1. Open **Application Audit**.
2. Search by applicant, job, module, MO, or statement.
3. Filter by status.
4. Review all application records.

This page supports final demonstration and acceptance testing.

## 7. Error Handling

The system validates common mistakes:

- Missing username/password.
- Duplicate username.
- Invalid username format.
- Password confirmation mismatch.
- Missing TA full name or skills.
- Invalid email address.
- Unsupported CV file type.
- Missing job fields.
- Invalid weekly hours.
- Application statement shorter than 10 characters.
- Duplicate active application for the same job.

## 8. Data Storage

CSV files are stored in:

```text
data/
```

Main files:

```text
users.csv
profiles.csv
jobs.csv
applications.csv
assignments.csv
```

CV uploads are stored in:

```text
data/uploads/
```
