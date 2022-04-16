const Recipe = require('../../db/models').Recipe;
const MealPlan = require('../../db/models').MealPlan;

const Errors = require('./errors');
/**
 * Creates a meal plan or throws an error if the given meal plan cannot be created.
 * @param userID
 * @param recipeID
 * @param dateTime
 * @returns {Promise.<MealPlan>}
 */
module.exports = function (userID, recipeID, dateTime) {
    return Recipe
        .findById(recipeID)
        .then(recipe => {
            if(!recipe){
                let e = new Error('Recipe with ID: ' + recipeID + ' not found.');
                e.name = Errors.LOOKUP_ERROR;
                throw e;
            }
            return MealPlan
                .create({
                    date: new Date(dateTime),
                    recipe_id: recipe.id,
                    user_id: userID
                });
        })
};