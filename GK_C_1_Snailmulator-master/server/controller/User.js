var tools = require('../../util/tools.js');

function login(plugin, req, res) {
  if (tools.hasKeys(req.body, ['email', 'password'])) {
    var u = plugin.sequelize.getUser(tools.grabKeys(req.body, ['email', 'password']));
  }
  return {};
}

function signup(plugin, req, res) {
  if (tools.hasKeys(req.body, ['email', 'password'])) {
    plugin.sequelize.insertUser(tools.grabKeys(req.body, ['email', 'password']));
  }

  return {};
}

module.exports = {
  login: login,
  signup: signup
};
