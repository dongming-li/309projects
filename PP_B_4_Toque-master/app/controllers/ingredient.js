const Ingredient = require('../db/models').Ingredient;
const Sequelize = require('sequelize');
const Op = Sequelize.Op;
/**
 * Gets ingredients
 * Optional query by name
 * @param  req
 * @param {string} req.body.name
 * @param  res
 */
module.exports.getIngredients = function(req, res) {
    let name = req.query.name;
    let query = {
        where: {}
    };

    //Put all queries here in this form
    if (name){
        query.where.name = { [Op.like]: '%' + name + '%' }
    }

    if(!name){//Add a check for each query item, if none are set then dont make a query
        query = undefined;
    }

    Ingredient
        .findAll(query)
        .then(ingredients => {
            res
                .status(200)
                .json(ingredients);
        })
        .catch(reason => {
            console.log('Reason: ', reason);
            res
                .status(500)
                .json(reason);
        });
};
/**
 * Creates an ingredient
 * Name is required
 * @param  req
 * @param {string} req.body.name
 * @param {number} req.body.calories
 * @param {number} req.body.fat
 * @param {number} req.body.sugar
 * @param {number} req.body.sodium
 * @param {number} req.body.protein
 * @param {number} req.body.carbs
 * @param {number} req.body.cholesterol
 * @param {number} req.body.calcium
 * @param {number} req.body.iron
 * @param  res
 */
module.exports.createIngredient = function(req, res) {
    if(!req.body.name) {
        res
            .status(400)
            .json({
                message: "Name is required!"
            });
        return;
    }

    Ingredient
        .create({
            name: req.body.name,
            calories: req.body.calories,
            fat: req.body.fat,
            sugar: req.body.sugar,
            sodium: req.body.sodium,
            protein: req.body.protein,
            carbs: req.body.carbs,
            cholesterol: req.body.cholesterol,
            calcium: req.body.calcium,
            iron: req.body.iron
        })
        .then(ingredient => {
            res.status(201);
            res.json({
                message: "Ingredient created: " + ingredient.id
            });
        })
        .catch(reason => {
            res
                .status(500)
                .json({
                    message: "Internal Server Error"
            });
        });
};
