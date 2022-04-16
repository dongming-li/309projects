import Database from './helpers/database';

module.exports = {
    client: 'mysql',
    connection: {
        host: Database.address,
        user: Database.username,
        password: Database.password,
        database: Database.database
    }
}
