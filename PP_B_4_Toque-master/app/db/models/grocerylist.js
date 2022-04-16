'use strict';
module.exports = (sequelize, DataTypes) => {
    var GroceryList = sequelize.define('GroceryList', {
        title: {
            type: DataTypes.STRING,
            allowNull: false
        },
        date: {
            type: DataTypes.DATE,
            allowNull: false
        }
    });
    GroceryList.associate = (models) => {
        GroceryList.belongsTo(models.User, {
            foreignKey: 'user_id',
            onDelete: 'CASCADE',
            allowNull: false
        });
    };
    GroceryList.associate = (models) => {
        GroceryList.belongsToMany(models.Ingredient, { as: 'ingredients', through: 'GroceryListItems', onDelete: 'CASCADE' });
    };
    return GroceryList;
};
