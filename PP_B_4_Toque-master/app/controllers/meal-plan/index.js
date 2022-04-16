const Errors = require('./errors');

const findMealPlan = require('./find-meal-plan');
const createMealPlan = require('./create-meal-plan');
const getMealPlansInDateRange = require('./get-meal-plans-in-date-range');


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
    return mealPlan => {
        if (mealPlan.user_id !== userID){
            let e = new Error('You do not have permission for this Meal Plan');
            e.name = Errors.PERMISSIONS_ERROR;
            throw e;
        }
        return mealPlan;
    }
};

/**
 * Gets the users meals within a time period
 * If no time period is specified it will select the meals within this month +/- 1 week
 * @param req
 * @param res
 */
module.exports.getMealPlans = function(req, res) {

    getMealPlansInDateRange(req.user.id, req.query.startDate, req.query.endDate, true)
        .then(meals => {
            let mealArr = meals.map(meal => {
                return meal.toJSON();
            });

            res
                .status(200)
                .json(mealArr);
        })
        .catch(handleError(req,res));
};

/**
 * Creates the meal plan
 * @param req
 * @param res
 */
module.exports.createMealPlan = function(req, res) {
    createMealPlan(req.user.id, req.body.recipe_id, req.body.date)
        .then(mealPlan => {
            res
                .status(200)
                .json({
                    message: "Meal Plan Added: " + mealPlan.id
                });
        })
        .catch(handleError(req,res));
};

/**
 * Gets a specific meal plan
 * @param req
 * @param res
 */
module.exports.getMealPlan = function (req, res) {

    findMealPlan(req.params.id, true)
        .then(handlePermissions(req.user.id))
        .then(mealPlan => {
            res
                .status(200)
                .json(mealPlan.toJSON());
        })
        .catch(handleError(req,res));
};

/**
 * Deletes a meal plan
 * @param req
 * @param res
 */
module.exports.deleteMealPlan = function (req, res) {

    findMealPlan(req.params.id)
        .then(handlePermissions(req.user.id))
        .then(mealPlan => {
            return mealPlan.destroy();
        })
        .then(() => {
            res
                .status(200)
                .json({
                    message: "Meal Plan Deleted Successfully!"
                });
        })
        .catch(handleError(req,res));
};
/**
 * Edits a meal plan
 * @param req
 * @param res
 */
module.exports.editMealPlan = function (req, res) {

    findMealPlan(req.params.id)
        .then(handlePermissions(req.user.id))
        .then(mealPlan => {
            const reqMealPlan = req.body;
            return mealPlan.update(reqMealPlan);
        })
        .then((mealPlan) => {
            res
                .status(200)
                .json({
                    message: "Meal Plan edited Successfully!"
                });
        })
        .catch(handleError(req,res));
};
