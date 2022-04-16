/* @flow */
module.exports = (sequelize, DataTypes) => {
    const Recipe = sequelize.define('Recipe', {
        title: {
            type: DataTypes.STRING,
            allowNull: false
        },
        content: {
            type: DataTypes.TEXT,
            allowNull: false
        }
    });
    Recipe.associate = (models) => {
        Recipe.belongsTo(models.User, {
            foreignKey: 'user_id',
            //When user is deleted, so are their recipes.
            //We may want to change this in the future.
            onDelete: 'CASCADE',
            allowNull: false
        });
    };
    Recipe.associate = (models) => {
        Recipe.belongsToMany(models.Ingredient, {as: 'ingredients', through: 'RecipeIngredients'});
    };
    return Recipe;
};
