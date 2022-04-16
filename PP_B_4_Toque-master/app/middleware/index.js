const bodyParser = require('./body-parser');
const methodOverride = require('./method-override');
const passport = require('./passport');
const router = require('./router');
const celebrate = require('./celebrate');

module.exports = app => {
    var middleware = {};
    //TODO implement some sort of dependency injection so that we can just give it any old array
    //List the middleware in order of dependency
    [
        bodyParser,
        methodOverride,
        passport,
        router,
        celebrate,
    ].reduce((acc, cur) => {cur(app, middleware)},0); //Will apply each middleware sequentially on our app
};

