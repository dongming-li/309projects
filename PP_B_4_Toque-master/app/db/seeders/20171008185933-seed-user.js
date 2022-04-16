'use strict';

const tableName = 'Users';

module.exports = {
    up: (queryInterface, Sequelize) =>
        queryInterface.bulkInsert(tableName, [{
            username: 'johndoe',
            email: 'johndoe@iastate.edu',
            password: '1234',
            userType: 'admin',
            createdAt: new Date(),
            updatedAt: new Date()
        },
        {
            username: 'janedoe',
            email: 'janedoe@iastate.edu',
            password: '1234',
            userType: 'moderator',
            createdAt: new Date(),
            updatedAt: new Date()
        }, {
            username: 'jordandoe',
            email: 'jordandoe@iastate.edu',
            password: '1234',
            userType: 'default',
            createdAt: new Date(),
            updatedAt: new Date()
        }], {}),
    down: (queryInterface, Sequelize) =>
        queryInterface.bulkDelete(tableName, null, {})
};
