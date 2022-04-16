'use strict';

const tableName = 'Ingredients';

module.exports = {
    up: (queryInterface, Sequelize) =>
        queryInterface.bulkInsert(tableName, [{
            name: 'American Cheese',
            fat: 10,
            sugar: 10,
            sodium: 20,
            protein: 30,
            carbs: 300,
            cholesterol: 12,
            calcium: 0,
            iron: 9,
            createdAt: new Date(),
            updatedAt: new Date()
        }], {}),

    down: (queryInterface, Sequelize) =>
        queryInterface.bulkDelete(tableName, null, {})
};
