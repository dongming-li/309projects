const bodyParser = require('body-parser');

module.exports = (app, middleware) => {
    middleware.bodyParser = bodyParser;
    // parse application/json
    app.use(bodyParser.json());
    // parse application/vnd.api+json as json
    app.use(bodyParser.json({ type: 'application/vnd.api+json' }));
    // parse application/x-www-form-urlencoded
    app.use(bodyParser.urlencoded({ extended: true }));
};
