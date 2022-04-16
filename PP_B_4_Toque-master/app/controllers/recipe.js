const Recipe = require('../db/models').Recipe;
const Ingredient = require('../db/models').Ingredient;
/**
 * Gets all recipes
 * @param  req
 * @param  res
 */
module.exports.getRecipes = function(req, res) {
    Recipe
        .findAll({
            include: [
                {
                    model: Ingredient,
                    as: 'ingredients'
                }
            ]
        })
        .then(recipes => {
            res
                .status(200)
                .json(recipes);
        })
        .catch(reason => {
            console.log(reason);
            res
                .status(500)
                .json(reason);
        });
};
/**
 * Creates a recipe
 * Name and description are required req.body fields
 * @param  req
 * @param {string} req.body.name
 * @param {string} req.body.description
 * @param {array, number} req.body.ingredients
 * @param  res
 */
module.exports.createRecipe = function(req, res) {
    // TODO: Remove once celebrate is added
    if(!req.body.description || !req.body.name) {
        //Extra response content
        res
            .status(400)
            .json({
            message: "Name and description are required!"
        });
        return;
    }

    let idList = [];

    if(req.body.ingredients) {
        req.body.ingredients.forEach(ingredient => {
            idList.push(ingredient.id);
        });
    }

    Recipe
        .create({
            title: req.body.name,
            content: req.body.description,
            user_id: req.user.id
        }, {
            include: [{
                model: Ingredient,
                as: 'ingredients'
            }]
        })
        .then(recipe => {
            return recipe
                .setIngredients(idList)
                .then(() => {
                    res
                        .status(200)
                        .json({
                            message: "Recipe Created: " + recipe.id
                        });
                })
        })
        .catch(reason => {
            console.log(reason);
            res
                .status(500)
                .json({
                    message: "Internal Server Error"
                });
        });
};
