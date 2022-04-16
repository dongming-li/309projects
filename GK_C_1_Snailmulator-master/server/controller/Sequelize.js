var Sequelize = require('sequelize');

function initDB() {
  // TODO: Hide this password.
  const DATABASE_URI = "mysql://dbu309gkc1:CzsewGAw@10.25.71.66:3306/db309gkc1";
  const db = new Sequelize(DATABASE_URI);

  // this checks to make sure the database connected properly
  var result;
  db.authenticate()
    .then(() => {
      console.log("Connection successful.");
      result = "Success.";
    })
    .catch(err => {
      console.error("Connection unsuccessful: ", err);
      result = err;
    });

  return result;
}

const user = sequelize.define('user', {
  id: {
    type: Sequelize.INTEGER,
    autoIncrement: true,
    primaryKey: true
  },
  email: Sequelize.STRING,
  password: Sequelize.STRING
});

var insertUser = db => user => sequelize.sync.then(() => user.create(user));

module.exports = ({
  insertUser: insertUser(db)
})(initDB());
