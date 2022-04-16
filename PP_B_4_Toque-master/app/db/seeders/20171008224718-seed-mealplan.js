'use strict';

const tableName = 'MealPlans';

module.exports = {
    up: (queryInterface, Sequelize) =>
        queryInterface.bulkInsert(tableName, [{
            date: new Date(),
            createdAt: new Date(),
            updatedAt: new Date(),
            recipe_id: 1,
            user_id: 1
        }, {
            date: new Date(),
            createdAt: new Date(),
            updatedAt: new Date(),
            recipe_id: 2,
            user_id: 2
        }, {
            date: new Date(),
            createdAt: new Date(),
            updatedAt: new Date(),
            recipe_id: 3,
            user_id: 3
        }], {}),

    down: (queryInterface, Sequelize) =>
        queryInterface.bulkDelete(tableName, null, {})
};
