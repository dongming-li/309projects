# Walk Me Home
This is a mobile app that lets users navigate from their current location to whatever destination they choose. The goal of this app is to have someone watching over the user. The user has to walk through a set of waypoints along the way to their destination in a certain amount of time. If they don't get to their waypoint in time, their friends will get alerted so that they can look into what happened.

## Development Setup
To get set up, clone the repo into whatever directory you want the project to live in.

Next, install [node](https://nodejs.org/en/).

### App Setup (React Native)
The React Native app uses Expo to run locally. For this, Expo must be installed on the device being used to run the app.

Once Expo has been installed to the device, from the command line, go to `<project directory>/app` and run `npm install`. This will install all of the dependencies for the app as specified in the package.json.

### Backend Setup
Move into the backend directory and run `npm install`. This will install all of the dependencies for the Node Express backend.

### Database Setup
To setup the local database, you will install the MySQL server. First, download it from (here)[https://dev.mysql.com/downloads/mysql/].

Once you have downloaded the installer, run it. Make sure to document what you use for the user, password, and database. Also, **make sure to set up the database as a process.** This will prevent a lot of headaches later.

After you have run the installer, you should be able to connect to it. Now you need to update `<project directory>/backend/knexfile` with the connection information for the database.

Once the knexfile has been updated, you need to install knex using `npm install -g knex`. Knex is the library that we use on the backend to interface with the database. Once knex is installed, you need to run our migrations on it. To do this, run `knex migrate:latest` while inside of the `<project directory/backend>` directory. This will run any migrations on the database that have yet to be run.

## Running Code
### Running the App Locally (React Native)
These steps will depend on if you are using a physical device (recommended) or an emulator.

If you are using an emulator, [click here to see how to set it up](https://facebook.github.io/react-native/docs/getting-started.html).

If you are using a physical device, make sure to install Expo.

Next, go to `<project directory>/app`, then run `npm start`. This will start the packager. After a bit of time, a QR code will be printed in the terminal. If you are using a physical device, scan the QR code with the Expo app. Then Expo will compile and run the app on the device. If you are using an Android emulator, after the QR code has printed in the terminal, press `a`. This will run the app in your emulator.

### Running the Backend Locally
In order for the React Native app to connect to the backend correctly, the IP the app tries to connect to must be updated. To do this, get your local IP address. Then take this and replace the IP address in the root URL in `<project directory>/app/helpers/routes.js`.

Next, you will have to configure the database connection information so that the backend can communicate with the database. Open `<project directory>/knexfile.js`. Here, make sure that the connection information matches what is needed to connect to your local database (most likely, the only things that will need to be changed are the user, password, and database values).

To run the backend, move into `<project directory>/backend` and run `npm run start`. This will start a node webserver on your computer that you can hit at [localhost:8090/<route>](localhost:8090/).

To test that the backend is up and running, you can go to [localhost:8090/](localhost:8090/), and you should see 'Hello World!' in the browser.

## Database Migrations
In order to make changes to the database, we use migrations. These make changes to the database programatically, and can be ran from the command line. As well as making changes to the database, they also serve as records of what has been changed on it. Because of this, they allow us to also undo these changes.

Also, **never delete a migration in master**. This can and will result in really bad things.

### Creating a Migration
To create a new migration, run `npm run create-migration -- [name]` from `<project directory>/backend`. This will create the boilerplate for a new migration. Inside of the migration, make whatever modifications you need to make to the database. Note, **make sure to include both the up and down function**.

### Running Migrations
To run migrations, go to `<project directory>/backend` in the command line. Then run `npm run migrate`. This will run any migrations that have not been run on the database.

### Rolling Back Migrations
If you ever need to roll back migrations, you can do so by running `npm run rollback` from `<project directory>/backend`. This will reverse all of the changes the migrations have made. Note, this can fail if there have been modifications to the database outside of those by the migrations. In that case, you will have to manually revert everything. The easiest way to do this is to manually delete all tables in the database.

## Seeding Database
There are two seeders in the backend:  one to add users, and one to create the friend relationship between different users. In order to use these, go to `<project directory>/backend`, then run `npm run seed`. This will delete any entries inside of the `user` or `friends` table, then add its own entries.

## Backend Endpoints
Below is a list of the backend endpoints.

### `/`:
- GET
- Used to see if the backend is up
- Response: 'Walk Me Home backend is running!'

### `/login`:
- POST
- Used to authenticate a user
- Request Body:
    - `email`: user email
    - `password`: user password
- Response Body:
    - Success:
        - `email`: user email
    - Failure:
        - No body
        - 401 Status

### `/getFriends`:
- POST
- Gets a list of the emails of friends related to the given user
- Request Body:
    - `email`: user email
- Response Body:
    - Array of friends related to the given user

### `/getFriendInfo`:
- POST
- Gets detailed user info
- Request Body:
    - `email`: user email
- Response Body:
    - `email`: user email
    - `password`: user password
    - `firstName`: user's first name
    - `lastName`: user's last name
    - `status`: user status

### `/register`:
- POST
- Registers a new user
- Request Body:
    - `email`: user email
    - `firstName`: user's first name
    - `lastName`: user's last name
    - `password`: user password
- Response Body:
    - `email`: user email
        - Only present if registering the user was successful
    - `error`: boolean value representing whether there was an error or not

## Running Tests
### App Tests
The testing framework that we are using is called Jest (you can look at the Jest documentation [here](https://facebook.github.io/jest/)).

To run the test suite, run `npm test` while inside of the app project directory in the terminal.

## Accessing AWS Infrastructure Locally
### Accessing the Backend Server
In order to connect to the backend, you will have to ssh into it. To do this, enter the following command:
`ssh -i "<project path>\aws\wmh-backend.pem" ubuntu@ec2-18-216-1-189.us-east-2.compute.amazonaws.com`. Note, this will only work if nobody else is connected to the backend and you have the project cloned locally.

This will connect you to the backend Ubuntu box as user 'ubuntu'. From there you will have access to the local filesystem.

### Connecting to the Database
To connect to the database, have a database program or MySQL installed. Then when making a new connection, enter `wmh-db.cthihu5gp8ac.us-east-2.rds.amazonaws.com` for the endpoint, `3306` for the port, `admin` for the username, and `walkmehome309` for the password.

## Documentation
The frontend is documented using [jsdoc](http://usejsdoc.org/). The backend is documented with a custom-built static website.

### Generating Frontend Documentation
If you want to generate new frontend documentation, you must first have jsdoc installed. To do this, run `npm install -g jsdoc` to install it globally. Once jsdoc has been installed, run `npm run document` from `<project directory>/app`. This will generate new documentation inside of the `<project directory>/app/documentation` directory. Note, if you generate new documentation, git will see these changes, so make sure to commit these.