import { combineReducers } from 'redux';
import auth from './auth';
import mealPlanner from './meal-planner';
import createRecipe from './createRecipe';
import home from './home';
import groceryLister from './grocery-list';

export default combineReducers({
    auth,
    mealPlanner,
    createRecipe,
    home,
    groceryLister
});
