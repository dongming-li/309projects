const GroceryList = require('../../db/models').GroceryList;
const Ingredient = require('../../db/models').Ingredient;
const Errors = require('../meal-plan/errors');

/**
 * Finds a grocery list by id
 * @param  {number} id
 * @param  {boolean} isEager
 */
module.exports = function(id, isEager) {
    let query;

    if(isEager) {
        query = {
                include: [{
                    model: Ingredient,
                    as: 'ingredients'
                }]
        };
    }

    return GroceryList
        .findById(id, query)
        .then(groceryList => {
            if(!groceryList) {
                let e = new Error('Grocery List with id ' + id + ' not found.');
                e.name = Errors.LOOKUP_ERROR;
                throw e;
            }
            return groceryList;
        });
}
