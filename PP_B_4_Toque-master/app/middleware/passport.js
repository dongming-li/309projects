const passport = require('passport');
const LocalStrategy = require('passport-local').Strategy;
const JWTStrategy = require('passport-jwt').Strategy;
const ExtractJWT = require('passport-jwt').ExtractJwt;

const JWTSecret = require('../config').JWTSecret;
const User = require('../db/models').User;


module.exports = (app, middleware) => {
    middleware.passport = passport;

    passport.use('local', new LocalStrategy({
        // by default, local strategy uses username and password, we will override with email
        usernameField : 'identifier',
        passwordField : 'password',
        session: false
    },
    (email, password, done) => {
        if(!email || !password)
            return done(null, false, {message: "Email and Password are required!"});
        User
            .findOne({
                where: {
                    email: email,
                }
            })
            .then(user => {
                if(!user)
                    return done(null, false, {message: "User with email doesn't exist"});
                if(!user.validPassword(password))
                    return done(null, false, {message: 'Password is incorrect.'});
                return done(null, user);

            })
            .catch(reason => {
                return done(reason);
            });
    }));

    var opts = {};
    opts.secretOrKey = JWTSecret;
    opts.jwtFromRequest = ExtractJWT.fromHeader('toque_token');
    opts.session = false;

    passport.use(new JWTStrategy(opts, (jwt_payload, done) => {
        User
            .findOne({
                where: {
                    id: jwt_payload.id
                }
            })
            .then(user => {
                if(!user) return done(null, false, {message: "User not found"});
                return done(null, user);
            })
            .catch(reason => {
                return done(reason);
            });
    }));

    app.use(passport.initialize());

};
