'use strict';

const tableName = 'Ingredients';

module.exports = {
    up: (queryInterface, Sequelize) =>
        queryInterface.createTable(tableName, {
            id: {
                allowNull: false,
                autoIncrement: true,
                primaryKey: true,
                type: Sequelize.INTEGER
            },
            name: {
                type: Sequelize.STRING,
                allowNull: false
            },
            calories: {
                type: Sequelize.INTEGER
            },
            fat: {
                type: Sequelize.INTEGER
            },
            sugar: {
                type: Sequelize.INTEGER
            },
            sodium: {
                type: Sequelize.INTEGER
            },
            protein: {
                type: Sequelize.INTEGER
            },
            carbs: {
                type: Sequelize.INTEGER
            },
            cholesterol: {
                type: Sequelize.INTEGER
            },
            calcium: {
                type: Sequelize.INTEGER
            },
            iron: {
                type: Sequelize.INTEGER
            },
            createdAt: {
                allowNull: false,
                type: Sequelize.DATE
            },
            updatedAt: {
                allowNull: false,
                type: Sequelize.DATE
            }
        }),
    down: (queryInterface, Sequelize) =>
        queryInterface.dropTable(tableName)
};
