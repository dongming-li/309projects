const MealPlan = require('../../db/models').MealPlan;
const Recipe = require('../../db/models').Recipe;
const Ingredient = require('../../db/models').Ingredient;
const Op = require('sequelize').Op;

/**
 * Gets meal plans within a specified date range
 * @param {int} userID
 * @param {Date} startDate
 * @param {Date} endDate
 * @param {bool} isEager
 * @returns {Promise.<MealPlan>}
 */
module.exports = function (userID, startDate, endDate, isEager) {

    let query = {
        where: {
            user_id: userID,
            date: {
                [Op.between]: [startDate, endDate]
            }
        },
    };

    if (isEager)
        query.include = [
            {
                model: Recipe,
                as: 'recipe'
            }
        ];

    return MealPlan
        .findAll(query);
};
