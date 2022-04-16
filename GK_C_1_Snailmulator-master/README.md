# Distributed Physics Simulator

## Project Description
We want to design a general multipurpose collaborative physics simulator capable of simulating complicated physics simulations doing distributed computing. The goal of this application is a place for an organization to get together over the internet to render simulations that their computers are too weak to do by themselves. It will have Admin users that are able to create arbitrary roles based off of a configuration UI. Roles can have multiple abilities spanning from choosing what goes into the simulation, what types of physical attributes are available, how many particles can go in, and who is allowed to partake in rendering. (Optionally we can restrict it to just three types if a specific solution is preferred to a generic composable configuration) Black has the competency to be a math major, to assist in the formulas and mathematics behind the project.

We intend to do this using a Node.js Backend for the server, Pixi.js for flexible WebGL rendering, Bootstrap, jQuery, and SQL. We plan on creating our own physics library in JavaScript. Luke is familiar with SQL and Bootstrap a good deal, although both Luke and Michael aren't exceptionally experienced in web development so that will be a part of the learning curve. Sam and Ryan know a good deal about web development and Ryan has done a peer to peer python app. Between developing the physics engine, developing the peer to peer communication scheme, setting up how roles work, and building the website, this should be enough for one semester.

## The Team
![Group Image](./img/group-photo.jpg)

Ryan Radomski [Tech Lead] - Sophomore Classification | 3rd Semester | Java, Clojure, Common Lisp, Emacs Lisp, C, Python, PHP, MySQL. I also work for ITS as a web developer.

Michael Black [Test Lead] - Junior | C, Java

Sam Westerlund [Web Master] - Junior | C#, Java, C, Sql, Javascript, Some PHP.

Luke Gaynor [Schedule/Repository Lead] - Sophomore Classification | 3rd Semester | C#, Java, Some C. T-SQL. I also work for the Environmental Health & Safety Department as a Developer.

## Roles
- **Web Administrator**: Access to all site functionality.
- **Global Administrator**: Access to all site functionality and backend.
- **Project Creator**: Access to all functionality within the domain of the project they've created.
- **Project Moderator**: Access to any designated functionality within the project that they've been assigned moderator to.
- **Rendering Assistant**: Standard user within a project.
- **Registered User**: A user that has created an account, but is not in a project yet.
- **Unregistered User**: Everyone else.

## How to add dependencies to package.json
``npm install --save [dependency]``

Example: *Adding sockets.io to the dependencies*

``npm install --save socket.io``
