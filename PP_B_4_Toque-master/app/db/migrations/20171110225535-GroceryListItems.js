'use strict';

let tableName = 'GroceryListItems'

module.exports = {
    up: (queryInterface, Sequelize) =>
        queryInterface.createTable(tableName, {
            id: {
                allowNull: false,
                autoIncrement: true,
                primaryKey: true,
                type: Sequelize.INTEGER
            },
            groceryListId: {
                allowNull: false,
                references: {
                    model: 'GroceryLists',
                    key: 'id'
                },
                type: Sequelize.INTEGER
            },
            ingredientId: {
                allowNull: false,
                references: {
                    model: 'Ingredients',
                    key: 'id'
                },
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
