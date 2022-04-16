const MealPlan = require('../../db/models').MealPlan;
const Recipe = require('../../db/models').Recipe;
const Errors = require('./errors');
/**
 * Finds a meal plan with a given ID or throws an error if one does not exist.
 * @param id
 * @param isEager
 * @returns {Promise.<MealPlan>}
 */
module.exports = function (id, isEager) {
    let query = null;
    if (isEager)
        query = {
            include: [
                {
                    model: Recipe,
                    as: 'recipe'
                }
            ]
        };

    return MealPlan
        .findById(id, query)
        .then(mealPlan => {
            if (!mealPlan){
                let e = new Error('Meal  with ID: ' + recipeID + ' not found.');
                e.name = Errors.LOOKUP_ERROR;
                throw e;
            }
            return mealPlan;
        })
};
