var tools = require('../../util/tools');


function formTasks(plugin, req, res) {
  if (tools.hasKeys(req.body, ['name', 'desc', 'code'])) {
    var p = plugin.sequelize.insertProject(tools.grabKeys(req.body, ['name', 'desc', 'code']));
    res.redirect('/');
  }
}

function create(plugin, req, res) {
  formTasks(plugin, req, res);
  return {};
}

function room(plugin, req, res) {
  if (tools.hasKeys(req.query, ['p'])) {
    var projectId = req.query.p;
    return plugin.sequelize.getProject({
      id: projectId
    }).then(project => ({
      project: project.dataValues
    }));
  }
  return {};
}

function edit(plugin, req, res) {
  formTasks(plugin, req, res);

  if (tools.hasKeys(req.query, ['p'])) {
    var projectId = req.query.p;
    return plugin.sequelize.getProject({
      id: projectId
    }).then(project => ({
      project: project.dataValues
    }));
  }
  return {};
}

function render() {
  return {};
}

function view(plugins, req, res) {
  console.log(req.query);
  return {};
}

function editUsers() {
  return {};
}

function index(plugins) {
  var projects = plugins.sequelize.listProjects();
  return projects.then(projects => ({
    projects: projects.map(project => project.dataValues)
  }));
}

module.exports = {
  create: create,
  edit: edit,
  room: room,
  view: view,
  render: render,
  editUsers: editUsers,
  index: index
};
