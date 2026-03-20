# EBU6304-TA-Recruitment-System-group84
## BUPT International School Teaching Assistant Recruitment System
*Queen Mary University of London | School of Electronic Engineering and Computer Science*  
*EBU6304 Software Engineering Group Project | Coursework Weight: 35% of Total Module Grade*  
*Agile Software Development Project | Group 84*


## Group Name-list
*Software Engineering group project for EBU6304*

-Kryst4A1:231224468 (Lead)
-ZJ-Tang:231224181 (Member)
-Sshuu1:231224963 (Member)
-chengdatian:231224424 (Member)
-Meng-1211:23122 (Member)
-wallacewang88:231224158 (Member)
---

## 1. Project Overview
This project is built for **BUPT International School** to deliver a dedicated Teaching Assistant (TA) recruitment management system. It aims to replace the current manual form and Excel-based workflow, streamlining the end-to-end TA recruitment process for Module Organisers (MO), TA applicants, and school administrators.

This project strictly follows **Agile Scrum methodology** throughout the full software development lifecycle, including requirements elicitation, analysis & design, implementation, testing, and delivery. All development activities fully comply with the mandatory technical constraints defined in the course handout, to focus on core software engineering principles.

### Core Compliance Statement
- ✅ Developed as a stand-alone Java desktop application (Java Swing GUI), fully meeting course requirements
- ✅ All input/output data stored in plain text CSV files (**NO database, NO external frameworks like Spring Boot**)
- ✅ Full traceable Agile development process with documented iterations, feedback and improvement
- ✅ 100% compliant with all course assessment requirements and academic integrity rules

---

## 2. Tech Stack
| Component | Specification | Compliance Note |
|-----------|---------------|-----------------|
| Core Language | Java 8+ | Native Java, no advanced language features, fully course-compliant |
| GUI Framework | Java Swing | Lightweight desktop application, no web server dependencies |
| Data Storage | CSV plain text files | No database, no ORM frameworks, strictly follows course restrictions |
| Version Control | GitHub | Full traceability of individual contributions via branches and commits |
| Agile Methodology | Scrum | Daily standups, sprint planning, incremental delivery, retrospective improvement |
| Prototyping Tool | Figma | Low/medium-fidelity prototype for first assessment |
| Testing Framework | JUnit 5 | Unit and integration testing for core business logic |

---

## 3. Team Members & Responsibilities
*All team members participate in the full project lifecycle (requirements, design, development, testing, delivery) to obtain comprehensive software engineering skills, as required by the course.*

| Role | Team Member | Core Responsibilities |
|------|-------------|-----------------------|
| Scrum Master | Yan Shidi | - Agile process management and daily standup organization<br>- Sprint planning, risk control and overall progress tracking<br>- GitHub repository administration and access management<br>- Primary contact for the assigned course TA<br>- Team coordination and conflict resolution |
| Product Owner | Tang Zijun | - Requirements elicitation and user story writing & refinement<br>- Product backlog maintenance and feature prioritization<br>- Prototype design and acceptance criteria definition<br>- Stakeholder communication and requirement validation<br>- Sprint scope definition and delivery validation |
| Requirements & Documentation Team | Cheng Yutian, Meng Sicheng | - Requirement fact-finding activities and analysis execution<br>- User story refinement and acceptance criteria standardization<br>- Course assessment report writing and supporting material organization<br>- Meeting minutes and Agile process documentation<br>- User manual and code documentation maintenance |
| Technical Research & Development Team | Shu Lei, Wang Yusi | - System architecture design and modular development<br>- Core Java code implementation and unit testing<br>- CSV data access layer encapsulation and business logic development<br>- Java Swing GUI development and integration testing<br>- Bug fixing and version iteration optimization |

---

## 4. Project Timeline & Sprint Planning
Fully aligned with the course assessment schedule and official key dates:

| Sprint | Time Range | Core Objective | Assessment Point | Weight |
|--------|------------|----------------|------------------|--------|
| Sprint 0 | 6th Mar - 21st Mar | Project initialization, requirements elicitation, backlog building, prototype design | - | - |
| Sprint 1 | 22nd Mar | First assessment deliverable submission | First Assessment | 30% |
| Sprint 2 | 23rd Mar - 12th Apr | Core function development, 2 incremental working versions | Intermediate Assessment | 20% |
| Sprint 3 | 13th Apr - 3rd May | Full function implementation, AI-powered feature development, full testing | - | - |
| Sprint 4 | 4th May - 24th May | Final optimization, delivery material preparation, final assessment | Final Assessment | 50% |

---

## 5. Core Feature Roadmap
Prioritized with **MoSCoW Method**, fully aligned with the course project specification:

### 🎯 Must Have (Core Features, Sprint 2 Delivery)
- TA applicant functions: account registration/login, personal profile creation & editing, CV upload, available job browsing, job application, application status checking
- Module Organiser (MO) functions: account registration/login, job posting, job management, applicant browsing & review, application status update
- Admin functions: system login, user management, job management, TA overall workload monitoring
- Full data persistence via CSV files, with stable read/write operations and exception handling

### 📌 Should Have (Secondary Features, Sprint 3 Delivery)
- In-system notification for application status updates
- Job edit/close function for MO
- Basic role-based access control
- Input validation and exception handling for all user operations
- MO job review function for Admin

### ✨ Could Have (AI-Powered Bonus Features, Sprint 4 Delivery)
- Skill matching between job requirements and applicant profiles (with explainable matching score)
- Missing skill identification for TA applicants
- Intelligent workload balancing recommendation for Admin/MO

### ❌ Won't Have (Out of Project Scope)
- Online CV editor (only support local file upload)
- Email/SMS notification (only in-system notification)
- Online interview & scoring function
- Complex data analysis dashboard
- Multi-language support

---

## 6. Repository Structure
*Fully aligned with course assessment requirements, clear classification of all deliverables and code


---

## 7. Environment & Run Requirements
- **Minimum JDK Version**: JDK 8 or higher
- **No Additional Dependencies**: No external frameworks, libraries or database required
- **OS Compatibility**: Windows, macOS, Linux (all systems with Java Runtime Environment installed)

---

## 8. Agile Collaboration & GitHub Rules
*100% compliant with course requirements for individual contribution tracking and evidence*

### 8.1 Branch Management Rules
- `main`: Protected main branch, only accepts merged code via Pull Request (PR), always keeps runnable stable version
- Personal development branch: Naming standard `feature/[name-pinyin]/[module-name]`, e.g. `feature/yanshidi/dao-util`
- Bug fix branch: Naming standard `bugfix/[name-pinyin]/[bug-description]`

### 8.2 Commit Message Specification
Standard format: `[Module] Clear and specific description of the change`
- Valid example 1: `[UserDAO] Complete CSV read/write encapsulation for user data`
- Valid example 2: `[TA UI] Implement job list page for TA applicant`
- **Mandatory Requirement**: Each team member must have at least 1 valid commit per day to ensure traceable individual contribution

### 8.3 Pull Request & Code Review Rules
- PR can only be created after personal development and unit testing are completed
- At least 1 team member must complete code review before merging to `main` branch
- No buggy or untested code is allowed to be merged into the `main` branch
- All branch contributions must be merged to `main` before each assessment deadline

### 8.4 Scrum Ceremonies
- **Daily Standup**: 15-minute daily meeting, each member shares: what was completed yesterday, what will be done today, any blockers
- **Sprint Planning**: At the start of each sprint, confirm sprint scope, task assignment and delivery goal
- **Sprint Retrospective**: At the end of each sprint, review progress, summarize problems and optimize the development process

---

## 9. Official Marking Criteria Alignment
*Fully aligned with the course handout. For each assessment stage, Final Mark = Group Marks × Individual Factor*

### 9.1 First Assessment (30% of Total Coursework)
#### Group Marks (Max 30 Points)
| Component | Detail | Max Marks |
|-----------|--------|-----------|
| Product Backlog | Correctness of defining users and project scope | 2 |
| | Correctness of standard user story writing | 5 |
| | Completeness of the full product backlog | 3 |
| Prototype | Functionality coverage of core user journey | 5 |
| | Usability and user experience design | 5 |
| Brief Report | Clear description of fact-finding techniques, iteration plan, prioritisation and estimation methods | 5 |
| Supporting Evidence | Sufficient evidence to support all statements in the report | 5 |
| **Total** | | **30** |

#### Individual Factor (Max 1.0)
| Component | Weight | Max Factor |
|-----------|--------|------------|
| Individual Contribution | Regular and valid commits on GitHub | 1/2 |
| | Observations and feedback from the assigned teaching assistant | 1/2 |
| **Total** | | **1.0** |

### 9.2 Intermediate Assessment (20% of Total Coursework)
#### Group Marks (Max 20 Points)
| Component | Detail | Max Marks |
|-----------|--------|-----------|
| Software Version 1 & 2 (GitHub) | Decent progress made for each version, with clear improvements addressing existing problems | 5 |
| Project Management (GitHub) | GitHub network shows active engagement from all members, with respective branches contributing and merged for new version releases | 5 |
| Demo & Viva | Excellent teamwork in presenting the current software version, with all questions answered clearly and accurately | 10 |
| **Total** | | **20** |

#### Individual Factor (Max 1.0)
| Component | Weight | Max Factor |
|-----------|--------|------------|
| Demo & Viva | Live presentation performance | 1/2 |
| | Q&A session performance | 1/2 |
| **Total** | | **1.0** |

### 9.3 Final Assessment (50% of Total Coursework)
#### Group Marks (Max 50 Points)
| Component | Detail | Max Marks |
|-----------|--------|-----------|
| Demonstration Video & Software | System design rationality and modularity | 10 |
| | Completeness and quality of function implementation | 10 |
| | Testing coverage and quality | 5 |
| Short Report | Overall content quality and completeness | 10 |
| Project Management (GitHub) | GitHub network shows active engagement from all members, with respective branches contributing and merged for final release | 5 |
| Demo & Viva | Pass all acceptance testing tasks, all questions answered clearly and accurately | 10 |
| **Total** | | **50** |

*Note: Students who demonstrate significant contribution and leadership in the group may receive up to 10% additional marks on the final assessment group mark.*

#### Individual Factor (Max 1.0)
| Component | Weight | Max Factor |
|-----------|--------|------------|
| Individual Contribution | Valid contribution records on GitHub | 1/4 |
| | Personal reflection statement in the final report | 1/4 |
| Demo & Viva | Acceptance testing task completion performance | 1/4 |
| | Q&A session performance | 1/4 |
| **Total** | | **1.0** |

---

## 10. Assessment Delivery Checklist
### First Assessment (22nd Mar, 30%)
- [ ] `ProductBacklog_group84.xlsx` (complete user stories with acceptance criteria, priority, estimation and sprint planning)
- [ ] `Prototype_group84.pdf` (low/medium-fidelity prototype, fully covers core user journey)
- [ ] `Report_group84.pdf` (max 5 pages, with supporting materials including interview records, meeting minutes etc.)
- [ ] GitHub repository fully set up, with clear commit records from all members
- [ ] Assigned course TA has full read access to the repository

### Intermediate Assessment (12th Apr, 20%)
- [ ] 2 incremental working software versions merged to `main` branch
- [ ] Full GitHub contribution records from all team members
- [ ] Demo presentation and viva preparation (2 mins per member)

### Final Assessment (24th May, 50%)
- [ ] Final runnable software package `Software_group84.zip` (source code, test programs, JavaDocs, user manual, README)
- [ ] Final report `Report_group84.pdf` (max 10 pages group report + 300 words individual statement per member)
- [ ] 10-minute max demonstration video (unedited, English narration, full function demonstration)
- [ ] Final demo & viva preparation
- [ ] All code merged to `main` branch, complete GitHub contribution records

---

## 11. Academic Integrity & AI Usage Statement
In full compliance with the AI-Assisted Development requirements defined in the course handout:
- AI tools are only used to assist brainstorming, user story writing, prototyping, design, coding, debugging and testing
- All AI-generated content has been critically reviewed, modified and validated by the team; no blind acceptance of AI outputs
- The team can fully explain all AI recommendations, modification logic and implementation details during live demonstration
- All AI-powered features in the system provide fully explainable results, combining structured logic with AI-based interpretation
- All content in this project is the original work of Group 84, with full traceability of all development activities
