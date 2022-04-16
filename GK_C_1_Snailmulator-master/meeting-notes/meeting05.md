# Meeting 05 - Group [2017/09/17]
- Missing Group Members: None

## Trello Tasks
- [x] Use case for @mblack, @lukeg, @samw, and @radomski.
- [x] Screen sketch for @mblack, @lukeg, @samw, and @radomski.
- [x] Screen-flow diagram from @mblack.
- [x] Tables/data list from @radomski.
- [x] Compiled Screen Sketch assignment from @lukeg.

## Notes
- @radomski made a basic API and a MatterJS wrapper.
- @mblack was working
- First page: Title
  - "Screen Sketches"
  - CK_G_1 | Michael Black, Ryan Radomski, Luke Gaynor, Sam Westerlund
  - Snailmulator
- Second page: Actors and Functionality
  - Actors:
    1. Nonregistered User: Ability to view public projects and create an account.
    2. Registered User: Ability to edit account details, join projects, and create projects.
      1. User:  
        1. Rendering Assistant: Ability to quit the rendering.
        2. Project Creator: Ability to edit details of a project, quit the rendering, delete the project, start/stop rendering, and assign moderators.
        3. Project Moderator: Ability to edit details of a project, quit the rendering, and start/stop rendering.
      2. Administrator: Full control over site functionality, including any created project.
  - Non-Functional Requirements:
    1. The connection ping between rendering assistants shall not exceed 50 milliseconds.
    2. The project rendering work should be shared proportionally with the number of rendering assistants.
    3. Any user not in the administrator category should not be able to view details about a project that they do not have access to.
- Third page: List of tables/fields and/or files.
  - @radomski's got this.
- Screen-flow diagram:
  - @mblack will do this.
