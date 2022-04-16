'use strict';

let tableName = "RecipeIngredients";

module.exports = {
    up: (queryInterface, Sequelize) =>
        queryInterface.createTable(tableName, {
            id: {
                allowNull: false,
                autoIncrement: true,
                primaryKey: true,
                type: Sequelize.INTEGER
            },
            recipeId: {
                allowNull: false,
                references: {
                    model: 'Recipes',
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
