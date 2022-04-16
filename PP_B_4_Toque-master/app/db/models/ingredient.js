'use strict';
module.exports = (sequelize, DataTypes) => {
    var Ingredient = sequelize.define('Ingredient', {
        name: {
            
            type: DataTypes.STRING,
            allowNull: false
        },
        calories: DataTypes.INTEGER,
        fat: DataTypes.INTEGER,
        sugar: DataTypes.INTEGER,
        sodium: DataTypes.INTEGER,
        protein: DataTypes.INTEGER,
        carbs: DataTypes.INTEGER,
        cholesterol: DataTypes.INTEGER,
        calcium: DataTypes.INTEGER,
        iron: DataTypes.INTEGER
    });
    // Ingredient.associate = (models) => {
    //     Ingredient.belongsTo(models.Recipe, {
    //         foreignKey: 'recipe_id',
    //         //When user is deleted, so are their recipes.
    //         //We may want to change this in the future.
    //         onDelete: 'CASCADE',
    //         allowNull: false
    //     });
    // };
    // Ingredient.associate = (models) => {
    //     Ingredient.belongsTo(models.GroceryList, {
    //         foreignKey: 'grocerylist_id',
    //         //When user is deleted, so are their recipes.
    //         //We may want to change this in the future.
    //         onDelete: 'CASCADE',
    //         allowNull: false
    //     });
    // };
    // Ingredient.associate = (models) => {
    //     Ingredient.belongsToMany(models.Recipe, {through: 'RecipeIngredients'});
    // };
  return Ingredient;
};


// db.Recipe.Ingredients = db.Recipe.hasMany(db.Ingredient, {as: 'ingredients'});
