const GroceryList = require('../../db/models').GroceryList;
const Ingredient = require('../../db/models').Ingredient;
const Sequelize = require('sequelize');
const Op = Sequelize.Op;
const Errors = require('../meal-plan/errors');

const findGroceryList = require('./find-grocery-list');
const findGroceryListByID = require('./find-grocery-list-by-id');
const findMealPlan = require('../meal-plan/find-meal-plan');

/**
 * To be used in the end of our promise chains to send data in the case of an error.
 * @param req
 * @param res
 * @returns {function(*)}
 */
const handleError = (req,res) => {
    return error => {
        let result;
        switch(error.name){
            case Errors.LOOKUP_ERROR:
                result = res.status(400);//Handled
                break;
            case Errors.PERMISSIONS_ERROR:
                result = res.status(403);//Handled
                break;
            default:
                result = res.status(500);//Unhandled

        }

        result
            .json({
                message: error.message
            });
    };
};

/**
 * Default permissions only allow the meal plan owner.
 * @param userID
 * @returns {function(*)}
 */
const handlePermissions = userID => {
    return groceryList => {
        if (groceryList.user_id !== userID){
            let e = new Error('You do not have permission for this Grocery List');
            e.name = Errors.PERMISSIONS_ERROR;
            throw e;
        }
        return groceryList;
    }
};
/**
 * Finds a grocery list by id
 * @param  req
 * @param {number}  req.params.id
 * @param  res
 */
module.exports.getGroceryList = function(req, res) {
    let userID = req.user.id;
    let listID = parseInt(req.params.id);

    findGroceryListByID(listID, true)
        .then(handlePermissions(userID))
        .then(groceryList => {
            res
                .status(200)
                .json(groceryList);
        })
        .catch(handleError(req, res));
}

/**
 * Gets all GroceryLists
 * Has the option to specify a name, startDate, and endDate
 * @param  req
 * @param {string} req.query.name
 * @param {date} req.query.startDate
 * @param {date} req.query.endDate
 * @param  res
 */
module.exports.getGroceryLists = function(req, res) {
    let name = req.query.name;
    let startDate = req.query.startDate;
    let endDate = req.query.endDate;
    let userID = req.user.id;

    findGroceryList(userID, name, startDate, endDate)
        .then(groceryList => {
            let groceryListArr = groceryList.map(list => {
                return list.toJSON();
            });

            res
                .status(200)
                .json(groceryListArr);
        })
        .catch(handleError(req, res));
};
/**
 * Creates a grocery list
 * @param  {number} userID
 * @param  {string} title
 * @param  {date} date
 * @param  {object} idList
 * @param  req
 * @param  res
 */
const makeNewGroceryList = function(userID, title, date, idList, req, res) {
    GroceryList
        .create({
            title: title,
            date: date,
            user_id: userID
        }, {
            include: [{
                model: Ingredient,
                as: 'ingredients'
            }]
        })
        .then(grocerylist => {
            return grocerylist
                .setIngredients(idList)
                .then(() => {
                    res
                        .status(200)
                        .json({
                            message: 'GroceryList created: ' + grocerylist.id
                        });
                })
        })
        .catch(handleError(req, res));
}

/**
 * Creates GroceryList
 * Add ingredients with ingredient ids
 * Takes a name, date, ingredients, and meal plans
 * Will parse ingredients from a meal plan's recipe
 * @param req
 * @param {string}  req.body.title
 * @param {date}  req.body.date
 * @param {object}  req.body.ingredients
 * @param {object}  req.body.mealPlans
 * @param res
 */
module.exports.createGroceryList = function(req, res) {
    // TODO: Remove once celebrate is added
    let title = req.body.title;
    let date = req.body.date;
    let userID = req.user.id;

    if (!title) {
        //Extra response content
        res
            .status(400)
            .json({
                message: "Name is required!"
            });
        return;
    }

    if(!date) {
        date = new Date();
    }

    let idList = [];

    if(req.body.ingredients) {
        req.body.ingredients.forEach(ingredient => {
            idList.push(ingredient.id);
        });
    }

    if(req.body.mealPlans) {
        req.body.mealPlans.forEach(mealPlan => {
            findMealPlan(mealPlan.id, true)
                .then(handlePermissions(req.user.id))
                .then(mealPlan => {
                    mealPlan.recipe.getIngredients()
                        .then(ingredients => {
                            ingredients.forEach(ingredient => {
                                idList.push(ingredient.id);
                            });
                            return idList
                        })
                        .then(idList => {
                            makeNewGroceryList(userID, title, date, idList, req, res);
                        });
                })
                .catch(handleError(req, res));
        });
    } else {
        makeNewGroceryList(userID, title, date, idList, req, res);
    }
};
/**
 * Edit a grocery list, specified by an id
 * @param  req
 * @param {number}  req.params.id
 * @param {string}  req.body.title
 * @param {date}  req.body.date
 * @param {object}  req.body.ingredients
 * @param  res
 */
module.exports.editGroceryList = function(req, res) {
    let id = parseInt(req.params.id);
    let idList = [];

    findGroceryListByID(id, true)
        .then(handlePermissions(req.user.id))
        .then(groceryList => {
            /* Some validation would be nice here */
            let listUpdate = req.body;

            if(listUpdate.ingredient) {
                listUpdate.ingredient.forEach(ingredient => {
                    idList.push(ingredient.id);
                });

                delete listUpdate.ingredient;
                groceryList.addIngredients(idList);
            }

            return groceryList.update(listUpdate);
        })
        .then((groceryList) => {
            res
                .status(200)
                .json({
                    message: 'Grocery List edited successfully'
                });
        })
        .catch(handleError(req, res));
}
/**
 * Deletes a grocery list based on a grocery list ID
 * @param  req
 * @param {number} req.params.id
 * @param  res
 */
module.exports.deleteGroceryList = function(req, res) {
    let id = parseInt(req.params.id);
    let userID = req.user.id;

    findGroceryListByID(id)
        .then(handlePermissions(userID))
        .then(groceryList => {
            groceryList.destroy();
        })
        .then(() => {
            res
                .status(200)
                .json({
                    message: 'Grocery List with id ' + id + ' has been successfully deleted.'
                });
        })
        .catch(handleError(req, res));
}
