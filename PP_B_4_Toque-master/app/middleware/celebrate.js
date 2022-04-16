const {celebrate, errors} = require('celebrate');

module.exports = (app, middleware) => {
    middleware.celebrate = celebrate;
    app.use(errors());
};
