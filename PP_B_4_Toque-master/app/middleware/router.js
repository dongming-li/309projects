const router = require('express').Router();

const AuthController = require('./../controllers/auth');
const RecipeController = require('./../controllers/recipe');
const MealPlanController = require('./../controllers/meal-plan');
const GroceryListController = require('./../controllers/grocery-list');
const IngredientController = require('./../controllers/ingredient');

const Validators = require('./validators');

const AUTH = require('./../config').AUTH;

//TODO Parse routes from a config (JSON, yaml, or javacript array)
module.exports = function(app, middleware) {
    if(!middleware.passport) throw new Error("Passport middleware must be added before the router");
    const passport = middleware.passport;
    app.use(router);
    middleware.router = router;

    // authentication routes
    router.post('/api/register', AuthController.register);
    router.post('/api/login', passport.authenticate('local', {session:false}), AuthController.login);
    router.post('/api/authenticate', requireAuth(AUTH.default), AuthController.authenticate);

    // ingredient routes
    router.get('/api/ingredient', IngredientController.getIngredients);
    router.post('/api/ingredient', IngredientController.createIngredient);

    // recipe routes
    router.get('/api/recipe', RecipeController.getRecipes);
    router.post('/api/recipe', requireAuth(AUTH.default), RecipeController.createRecipe);

    // meal plan routes
    router.get('/api/meal-plan', requireAuth(AUTH.default), Validators.mealPlan.validDateRange, MealPlanController.getMealPlans);
    router.post('/api/meal-plan', requireAuth(AUTH.default), Validators.mealPlan.validMealPlan, MealPlanController.createMealPlan);
    router.get('/api/meal-plan/:id', requireAuth(AUTH.default), Validators.shared.paramId, MealPlanController.getMealPlan);
    router.post('/api/meal-plan/:id', requireAuth(AUTH.default), Validators.shared.paramId, Validators.mealPlan.validMealPlan, MealPlanController.editMealPlan);
    router.delete('/api/meal-plan/:id', requireAuth(AUTH.default), Validators.shared.paramId, MealPlanController.deleteMealPlan);

    // grocery list routes
    router.get('/api/grocery-list', requireAuth(AUTH.default), GroceryListController.getGroceryLists);
    router.get('/api/grocery-list/:id', requireAuth(AUTH.default), GroceryListController.getGroceryList);
    router.post('/api/grocery-list', requireAuth(AUTH.default), GroceryListController.createGroceryList);
    router.post('/api/grocery-list/:id', requireAuth(AUTH.default), GroceryListController.editGroceryList);
    router.delete('/api/grocery-list/:id', requireAuth(AUTH.default), GroceryListController.deleteGroceryList);

    //Catch any unhandled routes
    router.get('*', function(req, res) {
        res.redirect('/#/404'); //give a 404 page.
    });


    function requireAuth(level) {
        return [
            passport.authenticate('jwt', {session: false}),
            (req,res,next) => {
                if (AUTH[req.user.userType] >= level)
                    return next();
                res.status(401).json({
                    message: 'Must be signed in!'
                });
            }
        ]
    }


};
