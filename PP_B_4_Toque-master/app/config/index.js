module.exports = {
    db: require('./db'),
    JWTSecret: process.env.SECRET||"Development",
    port: 80,
    AUTH: {
        default: 0,
        moderator: 1,
        admin: 2
    }
    //TODO implement defining routes in here -TD
};
