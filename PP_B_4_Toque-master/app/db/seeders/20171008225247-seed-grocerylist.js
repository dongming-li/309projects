'use strict';

const tableName = 'GroceryLists';

module.exports = {
    up: (queryInterface, Sequelize) =>
        queryInterface.bulkInsert(tableName, [{
            title: 'First list',
            date: new Date(),
            createdAt: new Date(),
            updatedAt: new Date(),
            user_id: 3
        },
        {
            title: 'Second list',
            date: new Date(),
            createdAt: new Date(),
            updatedAt: new Date(),
            user_id: 3
        },
        {
            title: 'Third list',
            date: new Date(),
            createdAt: new Date(),
            updatedAt: new Date(),
            user_id: 3
        }], {}),

    down: (queryInterface, Sequelize) =>
        queryInterface.bulkDelete(tableName, null, {})
};
