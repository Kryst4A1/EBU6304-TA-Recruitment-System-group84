# EBU6304-TA-Recruitment-System-group84
(Meng-1211:231224240(member))
## BUPT International School Teaching Assistant Recruitment System
*Queen Mary University of London | School of Electronic Engineering and Computer Science*  
*EBU6304 Software Engineering Group Project | Coursework Weight: 35% of Total Module Grade*  
*Agile Software Development Project | Group 84*


## Group Name-list
*Software Engineering group project for EBU6304*

- xuanxuanzhu77733-dotcom: 2025010108(Support TA)
- Kryst4A1:231224468 (Lead)
- ZJ-Tang:231224181 (Member)
- Sshuu1:231224963 (Member)
- chengdatian:231224424 (Member)
- Meng-1211:231224240  (Member)
- wallacewang88:231224158 (Member)

---

## 1. Project Overview

This project is a lightweight web-based Teaching Assistant Recruitment System for BUPT International School. It is designed to replace the original recruitment process based on forms and Excel files with a clearer online workflow.

The system supports three roles:

- **TA Applicant**
- **Module Organiser**
- **Administrator**

Main recruitment workflow:

```text
TA Profile → Job Browsing → Skill Matching → Application → MO Review → Assignment → Admin Workload Check
```

---

## 2. Project Requirements

This project follows the coursework requirements:

- Develop a software system for TA recruitment.
- Support TA applicants, Module Organisers, and Administrators.
- Allow TA applicants to create profiles, upload CVs, find jobs, apply for jobs, and check application status.
- Allow Module Organisers to post jobs and select applicants.
- Allow Administrators to check TA workload.
- Use a lightweight Java Servlet/JSP web application.
- Store all data in simple text files.
- Do **not** use a database.
- Do **not** use Spring Boot or other advanced frameworks.
- Use GitHub branches, commits, and pull requests to show individual contribution.

---

## 3. Technology Stack

| Area | Technology |
|---|---|
| Language | Java |
| Web Framework | Servlet, JSP |
| Server | Apache Tomcat 9 |
| Build Tool | Maven |
| Frontend | HTML, JSP, CSS |
| Storage | CSV files |
| Testing | Plain Java tests |
| Version Control | GitHub |

The project uses **CSV files** for persistence and does not use any database.

---

## 4. System Architecture

The project uses a simple layered structure:

```text
JSP Pages
   ↓
Servlet Controllers
   ↓
Service Layer
   ↓
CSV Repository
   ↓
CSV/Text Files
```

Main package structure:

```text
src/main/java/cn/bupt/tarecruitment/
├── context/
├── filter/
├── model/
├── repository/
├── service/
├── service/impl/
├── servlet/
└── util/
```

Frontend pages are stored in:

```text
src/main/webapp/WEB-INF/jsp/
```

---

## 5. Main Features

### 5.1 Authentication and Role Control

The system supports registration and login for TA, MO, and Admin users.

Implemented functions:

- User registration
- User login and logout
- Session management
- Role-based dashboard redirection
- Access control through `AuthenticationFilter`
- Prevention of unauthorised role access

For example, TA users cannot access MO or Admin pages.

---

### 5.2 TA Applicant Functions

TA applicants can:

- Create and edit applicant profile
- Upload and preview CV
- View open jobs
- Search jobs by keyword
- View job details
- Check skill matching results
- Submit job applications
- View application status

The TA profile includes:

- Full name
- Email
- Major
- Year of study
- Availability
- Skills
- Experience
- Self-introduction
- CV file

---

### 5.3 Skill Matching

The system compares TA skills with job required skills.

It displays:

- Match score
- Matched skills
- Missing skills
- Matching explanation

This function is rule-based and explainable. It helps TA applicants choose suitable jobs and helps Module Organisers review applicants.

---

### 5.4 Module Organiser Functions

Module Organisers can:

- Create job posts
- Edit job posts
- Close jobs
- Reopen jobs
- View applicants for their own jobs
- Review applicant profiles and CVs
- Shortlist applicants
- Select applicants
- Reject applicants

When an applicant is selected, the system automatically creates an assignment record for workload calculation.

---

### 5.5 Administrator Functions

Administrators can:

- View all TA workload records
- Check total weekly hours for each TA
- Identify overloaded TAs
- View workload balancing advice
- View all application records
- Filter applications by status
- Search applications by keyword

The workload module uses selected application records and assignment data.

---

## 6. Data Storage

All data is stored in CSV files under the `data/` directory.

| File | Description |
|---|---|
| `users.csv` | User accounts and roles |
| `profiles.csv` | TA applicant profiles |
| `jobs.csv` | Job posts created by MOs |
| `applications.csv` | TA job applications |
| `assignments.csv` | Selected TA workload assignments |
| `uploads/` | Uploaded CV files |

CSV parsing supports fields with commas, quotation marks, and line breaks.

---

## 7. Team Division

### 7.1 Shidi Yan

```text
GitHub: @Kryst4A1
Student ID: 231224468
Role: Lead
```

Responsible for:

- User registration and login
- Session management
- Role-based access control
- Dashboard navigation
- Authentication filter

Main collaboration:

- Provides login and role information for all other modules.

---

### 7.2 Zijun Tang

```text
GitHub: @ZJ-Tang
Student ID: 231224181
Role: Member
```

Responsible for:

- TA profile creation and editing
- CV upload
- CV preview
- Profile validation
- Profile data support for matching and review

Main collaboration:

- Provides TA skills for Lei Shu's matching module.
- Provides profile and CV data for Sicheng Meng's review module.

---

### 7.3 Lei Shu

```text
GitHub: @Sshuu1
Student ID: 231224963
Role: Member
```

Responsible for:

- TA job browsing
- Job keyword search
- Job detail page
- Skill matching display
- Job application submission
- TA application status tracking

Main collaboration:

- Reads TA profile data from Zijun Tang.
- Reads open job data from Yutian Cheng.
- Sends application data to Sicheng Meng's review module.

---

### 7.4 Yutian Cheng

```text
GitHub: @chengdatian
Student ID: 231224424
Role: Member
```

Responsible for:

- MO job creation
- Job editing
- Job closing
- Job reopening
- Job form validation
- MO job ownership control

Main collaboration:

- Provides open jobs for Lei Shu's TA application flow.
- Provides job data for Sicheng Meng's applicant review.
- Provides job information for Yusi Wang's Admin audit.

---

### 7.5 Sicheng Meng

```text
GitHub: @Meng-1211
Student ID: 231224240
Role: Member
```

Responsible for:

- MO applicant list
- Applicant detail review
- Shortlist operation
- Select operation
- Reject operation
- Assignment generation after selection
- Application status synchronisation

Main collaboration:

- Reads TA profile data from Zijun Tang.
- Reads application data from Lei Shu.
- Reads job data from Yutian Cheng.
- Provides assignment data for Yusi Wang's workload module.

---

### 7.6 Yusi Wang

```text
GitHub: @Wallacewang88
Student ID: 231224158
Role: Member
```

Responsible for:

- Admin workload board
- Overload warning
- Workload balancing advice
- Application audit
- Status filtering and keyword search
- Global UI style
- Testing and documentation

Main collaboration:

- Reads applications from Lei Shu.
- Reads job data from Yutian Cheng.
- Reads assignments from Sicheng Meng.
- Tests the integrated workflow across all modules.

---

## 8. How to Run

### 8.1 Requirements

```text
JDK 17
Apache Tomcat 9
Maven
IntelliJ IDEA Ultimate recommended
```

Tomcat 9 is recommended because this project uses `javax.servlet`.

### 8.2 Build with Maven

From the project root:

```bash
mvn clean package
```

Then deploy the generated WAR file to Tomcat 9.

### 8.3 Run in IntelliJ IDEA

1. Open the project folder.
2. Set Project SDK to JDK 17.
3. Configure a local Tomcat 9 server.
4. Add the artifact:

```text
ta-recruitment-web-final:war exploded
```

5. Set application context:

```text
/ta-recruitment-web-final
```

6. Start Tomcat and open:

```text
http://localhost:8080/ta-recruitment-web-final/
```

### 8.4 Recommended VM Option

To ensure the system reads the correct CSV files:

```text
-Dta.data.dir=absolute_path_to_project/data
```

Example:

```text
-Dta.data.dir=D:\Projects\TARecruitmentWebFinal\data
```

---

## 9. Demo Accounts

| Role | Username | Password |
|---|---|---|
| TA | `ta01` | `ta01` |
| TA | `ta02` | `ta02` |
| MO | `mo01` | `mo01` |
| Admin | `admin01` | `admin01` |

---

## 10. Testing

Run:

```bash
./scripts/run_tests.sh
```

The tests cover:

- CSV parsing
- Registration
- Profile creation
- Job management
- Skill matching
- Application submission
- Application status update
- Assignment synchronisation
- Workload calculation
- Application audit logic

Expected output:

```text
CoreLogicTest passed.
All tests passed.
```

---
