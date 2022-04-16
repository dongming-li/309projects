const Sequelize = require('sequelize');

// this configures the connection
const db = new Sequelize('postgres', 'postgres', 'Snailmulator309', {
  host: 'localhost',
  port: '5432',
  dialect: 'postgres'
});

// This is the relative location of the models to this file.
const relativeModelPath = __dirname + "/../models/";

function BindModel(database, tableName) {
  tableName = tableName.toLowerCase() + '.js';
  var location = `${__dirname}/../models/${tableName}`;
  return database.import(location);
}

// These bind Sequelize to models.
const User = BindModel(db, 'user');
const Project = BindModel(db, 'project');
const Rendering = BindModel(db, 'rendering');
Rendering.belongsTo(Project);
Project.belongsTo(User);

// add to database object
db.user = User;
db.project = Project;
db.rendering = Rendering;

// START LEGACY CODE ===========

// insertion
db.insertUser = (user) => db.sync().then(() => User.create(user));
db.insertProject = (project) => db.sync().then(() => Project.create(project));

// getters
db.getUser = (user) => () => User.findOne({
  where: {
    email: user.email,
    password: user.password
  }
});
db.getProject = (project) => Project.findOne({
  where: {
    id: project.id
  }
});

db.listProject = () => Project.findAll();


// END LEGACY CODE =============

module.exports = serverState => db;
