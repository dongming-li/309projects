# Contribution Standards

## Purpose
- Provide current standards for contributing.
> If functionality is needed that isn't specified in this document, then add a manager to the appropriate point in the `managerPipeline` and document the new standard of contribution here.

## First-Time Development Environment Setup
### Requirements
- NodeJS >7.x | ``node --version``
  - This includes Node Package Manager | ``npm -v``
- [Google Cloud SDK](https://cloud.google.com/sdk/docs/) | ``gcloud --version``
- [Cloud SQL Proxy](https://cloud.google.com/sql/docs/mysql/sql-proxy)

### Database Access:
1. Get the [Google Cloud SDK](https://cloud.google.com/sdk/docs/).
    - Run ``gcloud init`` to set up your account. Ensure that it is the one that has access to our project.
      - Select ``Y``es for configuring Google App Engine, and set region to ``24``.
    - Run ``gcloud auth application-default`` to set up the default settings. Ensure that you use the account that has access to our project.
2. Get the [Cloud SQL Proxy](https://cloud.google.com/sql/docs/mysql/sql-proxy).
    - Run with the command ``./cloud_sql_proxy -instances="physimulator:us-central1:physimulator"=tcp:5432``.
    - This must be running any time that you are running the app locally. You can either run it in the background, or use two terminals.

### Starting the Application
- Run ``npm start`` or ``node app.js``.
- View in-browser at [``localhost:8080``](http://localhost:8080).

## How To: Add a web page or pass data to the client

- All new web page additions can be done following the specification in `framework.md`
- The only code that needs be mutated is the `config.json` and the controller (if it already exists).
  - Other contribution can be made by adding new files instead of mutating existing ones.
- The return value of any controller action will be passed to the view using that action.

## How To: Share functionality between controllers

- Make a controller plugin. The intended purpose of a controller plugin is to provide a service many controllers may potentially use.
- If you ever think you need to change part of the server core (`manager/`, and `app.js`), **first** try to create a plugin that can accomplish the same task.
- Plugins optionally take in a single parameter (where the serverState will be passed) so they have *nearly* global application access.

## How To: Create a global variable for views

- All global variables for view should be added to the `serverState.globals.globals` variable **after** the global manager has executed in the pipeline.

## How To: Add an asset available to client

- The public folder is statically linked to clients so they have access to all of it's contents. If a file path for what you need doesn't exist, attempt to mimic the pattern already set in the public folder.

## Experimentation with new technology

- If you are not sure the best way to integrate a new technology into the framework, but want to share code with others, create a mock view and controller for it. Controllers are isolated from the serverState and need the help of a controller Plugin to access the server internals. You have the full power of Node.JS in an environment that isn't harmful to the rest of the codebase.
- Basically, sandbox it!
