const methodOverride = require('method-override');

module.exports = (app, middleware) => {
    // override with the X-HTTP-Method-Override header in the request. simulate DELETE/PUT
    app.use(methodOverride('X-HTTP-Method-Override'));
    middleware.methodOverride = methodOverride;
};