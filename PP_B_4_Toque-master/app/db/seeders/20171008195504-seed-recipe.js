'use strict';

const tableName = "Recipes";

module.exports = {
    up: (queryInterface, Sequelize) =>
        queryInterface.bulkInsert(tableName, [{
            title: 'Grilled Cheese',
            content: 'Use bread and cheese',
            createdAt: new Date(),
            updatedAt: new Date(),
            user_id: 3
        }, {
            title: 'Yogurt',
            content: 'Milk and old age',
            createdAt: new Date(),
            updatedAt: new Date(),
            user_id: 3
        }, {
            title: 'Dirt Pie',
            content: 'Put dirt in a pie crust',
            createdAt: new Date(),
            updatedAt: new Date(),
            user_id: 3
        }], {}),
    down: (queryInterface, Sequelize) =>
        queryInterface.bulkDelete(tableName, null, {})
};
