# csc207-group69
## Contributors:
- Jaehyeon Park @wogus00
- Tatsuya Shiokawa @ShioTatsu-Japan
- Wendy Sun @sunshuh2
- Makoto Takahara @makototakahara
- Kosei Uemura @Kosei1227
## General Information
- This software is the project for course CSC207H1 at the University of Toronto.
- Distribution without permission is prohibited
## Final Presentation
- Link to be updated after final presentation
## Functionalities and Specifications
- Our software utilizes a fully implemented Swing GUI, where users can log in to the application and record project related data into the Google Firestore database.
- There are five main use cases that each contributor was mainly part of:
  - `Log in` and `Create Project` features by @sunshuh2
    - The leader user is able to create the project and send email notifications to the invited members.
    - Invited members are able to log in to the project by entering their email and project name.
  - `Create, Complete, Modify Tasks` features by @wogus_00
    - Users are able to create the task, assign it to other users, and send emails.
    - Assigned users can complete the task.
    - Supervising user can modify the task.
  - `Create, Delete, Modify Meetings` features by @makototakahara
    - Users are able to create the meeting, assign it to relevant users, and send emails.
    - Assigned users can delete the meeting.
    - Supervising user can modify the task.
  - `Create, Delete Announcement` features by @Kosei1227
    - Users are able to create announcement that sends an email to all users.
    - The responsible user is able to delete announcement.
  - `Add and Delete Email` and `Set Leader` features by @ShioTatsu-Japan
    - The leader user is able to add or remove members of the project
    - The leader user is able to set the leader of the project to other user.
## Code Organization
- Our code was mainly organized by layers according to the Clean Architecture (CA).
- Package `data_access`: contains classes to interact with the database and Gmail API. This is an example of layers on Frameworks & Drivers from CA.
- Package `entity`: contains all classes that are following the Enterprise Business Rules according to CA.
- Package `interface_adapter`: contains all classes that are for Interface Adapters according to CA.
- Package `use_case`: contains all classes that are following the Application Business Rules according to CA.
- Package `view`: contains classes for presenting the program to the user. This is an example of the outermost layer of CA.
- `Main` class is the starting point of the program. It initializes all the detailed classes (e.g., use case classes) for the program and connect them.
## Code Style and Documentation
- We used lowerCamelCase for variable and method names
  - example: method `memberExists(...)` and variable `createProjectViewModel`
- We used CamelCase for class names
  - example: `CreateProjectViewModel`
- We used snail_case for package names
  - example: `use_case`
- We also added javadocs for most of the public methods and classes of the project.
  - For all parameters of a method, we clearly indicated the object type that the method required
  - For each public classes, we added clear explanations of the purpose of the class and related classes to check.
## Clean Architecture
As mentioned above, we incorporated Clean Architecture for the design of the project.
### Business Enterprise Rules: Entities
- **Task**: projectName, taskName, supervisor, workingMembersList, deadline, comments, status
- **Meeting**: meetingName, participantEmail, meetingDate, startTime, endTime, projectName
- **Project**: projectName, leaderEmail, memberEmails
- **Announcement**: announcementTitle, message, creationTime, author, id
### Application Enterprise Rules and Interface Adapters: Use Cases
- As mentioned above, each member was responsible for one of the use cases below
  - `Log in` and `Create Project`
  - `Create, Complete, Modify Tasks`
  - `Create, Delete, Modify Meetings`
  - `Create, Delete Announcement`
  - `Add and Delete Email` and `Set Leader`
## SOLID Design Principles
The following are key examples of adhering the SOLID design principles.
- Single Responsibility Principle
- Open/Closed Principle
## Design Patterns