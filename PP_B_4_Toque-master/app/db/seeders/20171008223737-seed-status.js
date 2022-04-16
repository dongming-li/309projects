'use strict';

const tableName = 'Statuses';

module.exports = {
    up: (queryInterface, Sequelize) =>
        queryInterface.bulkInsert(tableName, [{
            title: 'I am a terrible chef',
            description: 'Check out all my terrible recipes',
            createdAt: new Date(),
            updatedAt: new Date()
        }], {}),
    down: (queryInterface, Sequelize) =>
        queryInterface.bulkDelete(tableName, null, {})
};
