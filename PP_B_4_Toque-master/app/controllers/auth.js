const User = require('../db/models').User;
const JWTSecret = require('../config').JWTSecret;
const JWT = require('jsonwebtoken');
/**
 * Will create a user with a given email, username, and password and return a success response.
 * @param req
 * @param {string} req.body.name
 * @param {string} req.body.username
 * @param {string} req.body.password
 * @param res
 */
module.exports.register = function(req, res) {
    if(!req.body.email || !req.body.username || !req.body.password)
        res.status(400).json({message: "Username, email, and password are required!"});
    else
        User

        .findOne({

                where: {
                    email: req.body.email,

                },
            })
            .then(user => {
                if (user !== null){
                    throw Error('Email is already in Use');
                }

                return User
                    .create({
                        username: req.body.username,
                        email: req.body.email,
                        password: User.generatePasswordHash(req.body.password),
                        userType: 'default',
                    })
            })
            .then(user => {
                res.status(200);
                res.json({
                    message: "User registered",
                });
            })
            .catch(error => {
              return User
                  .create({
                      username: req.body.username,
                      email: req.body.email,
                      password: User.generatePasswordHash(req.body.password),
                      userType: 'default',
                  });
            });
};

/**
 * Authenticates a user and give a success response containing their auth token.
 * @param req
 * @param res
 */
module.exports.login = function(req, res) {
    let user = req.user.toJSON();
    delete user.password;

    res.status(200).json({
        message : "User logged-in: " + user.username,
        user: user,
        token: JWT.sign({id:user.id}, JWTSecret)
    });
};

/**
 * Authenticates a user and give a success response containing their auth token.
 * @param req
 * @param res
 */
module.exports.authenticate = function (req,res) {
    let user = req.user.toJSON();
    delete user.password;

    res.status(200).json({
        message : "User authenticated: " + user.username,
        user: user
    });
};
/**
 * Logs a user out if they are authenticated.
 * @param req
 * @param res
 */
module.exports.logout = function(req, res) {
    res.status(200).json({
        "message" : "You have been logged out!"
    });
};
