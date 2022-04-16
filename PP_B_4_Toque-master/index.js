// modules =================================================
const express           = require('express');
const app               = express();
const port              = require('./app/config').port;

// configuration ===========================================

// set the static files location /public/assets/img will be assets/img for users
app.use(express.static(__dirname + '/app/public'));
//Apply our middleware
require('./app/middleware')(app);
// startup our app
app.listen(port);
// shoutout to the user
console.log('Magic happens on port ' + port);
// expose app
module.exports = app;
