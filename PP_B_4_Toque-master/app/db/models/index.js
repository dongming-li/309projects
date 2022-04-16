/* @flow */
const fs        = require('fs');
const path      = require('path');
const Sequelize = require('sequelize');
const basename  = path.basename(module.filename);
let db        = {};

//This is subject to change as we learn more about env varables
let sequelize;
if(process.env.NODE_ENV === 'production') {
    if (!process.env.DATABASE_URL) throw new Error('DATABASE_URL environment variable must be set in producton environment!');
    sequelize = new Sequelize(process.env.DATABASE_URL);
}
else {
    let config = require('../../config').db.development;
    if (!config) throw new Error('Local sequlize configuration not found. Consult README.md.');
    sequelize = new Sequelize(config.database, config.username, config.password, config);
}

fs
  .readdirSync(__dirname)
  .filter(file =>
      (file.indexOf('.') !== 0) &&
      (file !== basename) &&
      (file.slice(-3) === '.js'))
  .forEach(file => {
      const model = sequelize.import(path.join(__dirname, file));
      db[model.name] = model;
  });

Object.keys(db).forEach(modelName => {
  if (db[modelName].associate) {
    db[modelName].associate(db);
  }
});

db.sequelize = sequelize;
db.Sequelize = Sequelize;
// sequelize.sync();

/*
Add new models here for IDE autocomplete when importing.
It goes without saying that you never need to use this function.
 */
function enableAutocomplete() {
    /**  @type {Model|User|*} */
    db.User = require('./user')();
    /**  @type {Model|Recipe|*} */
    db.Recipe = require('./recipe')();
    /**  @type {Model|Recipe|*} */
    db.Status = require('./status')();
    /**  @type {Model|Recipe|*} */
    db.MealPlan = require('./status')();
    /**  @type {Model|Recipe|*} */
    db.Ingredient = require('./ingredient')();
    /**  @type {Model|Recipe|*} */
    db.GroceryList = require('./grocerylist')();

    throw new Error('this function for IDE autocomplete');
}

module.exports = db;
