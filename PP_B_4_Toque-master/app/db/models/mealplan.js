'use strict';
module.exports = (sequelize, DataTypes) => {
    let MealPlan = sequelize.define('MealPlan', {
        date: {
            type: DataTypes.DATE,
            allowNull: false
        }
    });

    MealPlan.associate = (models) => {
        MealPlan.belongsTo(models.User, {
            as: 'user',
            foreignKey: 'user_id',
            allowNull: false
        });

        MealPlan.belongsTo(models.Recipe, {
            as: 'recipe',
            foreignKey: 'recipe_id',
            allowNull: false
        })
    };

    return MealPlan;
};