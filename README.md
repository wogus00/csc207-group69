# csc207-group69

### Members
- Jaehyeon Park
- Makoto Takahara
- Tatsuya Shiokawa
- Kosei Uemura
- Shuhan Sun
# Project Description
# Entities
    - User: ID, Role, Email, Password, DateOfMeeting
    - Event: EventName, Responsibility, Detail, StartEndTime
    - Task (Event): TaskName, Supervisor, Detail, Detail, StartEndTime, Status
    - Meeting (Event): MeetingName, Member, SubjectOfMeeting, Detail, StartEndTime
    - Project: ProjectName, Leader, Detail, Members, Events, Announcement, Notification, StartEndTime
    - Calendar: Date, Meeting, Event, ChangeDate
    - Announcement: ID, Title, Detail, Date
    - Notification (Announcement): ID, Title, Request, Accept, Denied, Conflict, ResolvedConflict