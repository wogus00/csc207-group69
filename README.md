# csc207-group69
## Basic Information
1. Domain: Collaborative Task Management System
2. Software Specification:
    - User
        - An user is created when someone signs up
    - Project
        - A project will be created by an user, this user will be the default leader
        - The leader will be able to invite users to be a member of the project
        - User members of the project will be able to create and remove events, this includes tasks and meetings
        - User members of the project will be able to edit events in the project
    - Event
    - Task(Event)
    - Meeting(Event)
    - Announcement
    - Notification(Announcement)
    - Calender

## Entities
- User: ID, Role, Username, Email, Password, Date of Meeting
- Event: Event Name, Responsibility, Detail, Start & End Time
- Task (Event): Task Name, Supervisor, Detail, Detail, Start & End Time,
  Completion Status
- Meeting (Event): Meeting Name, Member, Subject of Meeting,
  Detail, Start & End Time
- Project: Project Name, Leader, Detail, Members, Events,
  Announcement, Notification, Start & End Time
- Calendar: Date, Meeting, Event, Change Date
- Announcement: ID, Title, Detail, Date
- Notification (Annou): ID, Title, Request, Accept, Denied, Conflict,
  Resolved Conflict
