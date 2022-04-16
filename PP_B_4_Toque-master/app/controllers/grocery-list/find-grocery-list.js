const GroceryList = require('../../db/models').GroceryList;
const Ingredient = require('../../db/models').Ingredient;
const Sequelize = require('sequelize');
const Op = Sequelize.Op;
const Errors = require('../meal-plan/errors');
/**
 * Finds a grocery list based on given parameters
 * @param  {number} userID
 * @param  {string} title
 * @param  {date} startDate
 * @param  {date} endDate
 */
module.exports = function(userID, title, startDate, endDate) {
    let query = {
        where: {
            user_id: userID,
        },
        include: [
            {
                model: Ingredient,
                as: 'ingredients'
            }
        ]
    };

    if(title) {
        query.where.title = { [Op.like]: '%' + title + '%' }
    }

    if(startDate && endDate) {
        query.where.date = { [Op.between]: [startDate, endDate] }
    }

    return GroceryList
        .findAll(query);
}
