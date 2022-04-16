import {post, get, delete as del} from 'axios';
import Cookies from 'universal-cookie';
const cookie = new Cookies();
import {
    MEALPLANS_REQUEST,
    MEALPLANS_FAILURE,
    MEALPLANS_SUCCESS,
    MEALPLAN_CREATE_FAILURE,
    MEALPLAN_CREATE_SUCCESS,
    MEALPLAN_CREATE_REQUEST,
    MEALPLAN_EDIT_REQUEST,
    MEALPLAN_EDIT_SUCCESS,
    MEALPLAN_EDIT_FAILURE,
    MEALPLAN_DELETE_REQUEST,
    MEALPLAN_DELETE_SUCCESS,
    MEALPLAN_DELETE_FAILURE,
    MEALPLAN_RECIPE_SEARCH_REQUEST,
    MEALPLAN_RECIPE_SEARCH_SUCCESS,
    MEALPLAN_RECIPE_SEARCH_FAILURE
} from '../../actionTypes';

/**
 * Indicates MealPlan request was sent to server.
 * @param query
 * @returns {{type, query: *}}
 */
function mealPlansRequest(query) {
    return {
        type: MEALPLANS_REQUEST,
        query
    }
}

/**
 * Indicates MealPlan request was successful.
 * @param mealPlans
 * @returns {{type, mealPlans}}
 */
function mealPlansSuccess(mealPlans) {
    return {
        type: MEALPLANS_SUCCESS,
        mealPlans
    }
}
/**
 * Indicates MealPlan request was unsuccessful.
 * @param error
 * @returns {{type, error: *}}
 */
function mealPlansFailure(error) {
    return {
        type: MEALPLANS_FAILURE,
        error
    }
}
/**
 * Indicates MealPlan create request was sent to the server.
 * @param mealPlan
 * @returns {{type, mealPlan: *}}
 */
function mealPlanCreateRequest(mealPlan) {
    return {
        type: MEALPLAN_CREATE_REQUEST,
        mealPlan
    }
}
/**
 * Indicates MealPlan create request was successful.
 * @param mealPlan
 * @returns {{type, mealPlan: *}}
 */
function mealPlanCreateSuccess(mealPlan) {
    return {
        type: MEALPLAN_CREATE_SUCCESS,
        mealPlan
    }
}
/**
 * Indicates the MealPlan create request was unsuccessful.
 * @param error
 * @returns {{type, error: *}}
 */
function mealPlanCreateFailure(error) {
    return {
        type: MEALPLAN_CREATE_FAILURE,
        error
    }
}

/**
 * Indicates the MealPlan edit request was sent to the server.
 * @param mealPlan
 * @returns {{type, mealPlan: *}}
 */
function mealPlanEditRequest(mealPlan) {
    return {
        type: MEALPLAN_EDIT_REQUEST,
        mealPlan
    }
}
/**
 * Indicates the MealPlan edit request was successful.
 * @param mealPlan
 * @returns {{type, mealPlan: *}}
 */
function mealPlanEditSuccess(mealPlan) {
    return {
        type: MEALPLAN_EDIT_SUCCESS,
        mealPlan
    }
}
/**
 * Indicates the MealPlan edit request was unsuccessful.
 * @param error
 * @returns {{type, error: *}}
 */
function mealPlanEditFailure(error) {
    return {
        type: MEALPLAN_EDIT_FAILURE,
        error
    }
}
/**
 * Indicates the MealPlan delete request was sent to the server.
 * @param mealPlan
 * @returns {{type, mealPlan: *}}
 */
function mealPlanDeleteRequest(mealPlan) {
    return {
        type: MEALPLAN_DELETE_REQUEST,
        mealPlan
    }
}
/**
 * Indicates the MealPlan delete request was successful.
 * @param mealPlan
 * @returns {{type, mealPlan: *}}
 */
function mealPlanDeleteSuccess(mealPlan) {
    return {
        type: MEALPLAN_DELETE_SUCCESS,
        mealPlan
    }
}
/**
 * Indicates the MealPlan delete request was unsuccessful.
 * @param error
 * @returns {{type, error: *}}
 */
function mealPlanDeleteFailure(error) {
    return {
        type: MEALPLAN_DELETE_FAILURE,
        error
    }
}
/**
 * Indicates the MealPlan search request was sent to the server.
 * @param searchQuery
 * @returns {{type, searchQuery: *}}
 */
function recipeSearchRequest(searchQuery) {
    return {
        type: MEALPLAN_RECIPE_SEARCH_REQUEST,
        searchQuery
    }
}
/**
 * Indicates the MealPlan search was successful.
 * @param recipes
 * @returns {{type, recipes: *}}
 */
function recipeSearchSuccess(recipes) {
    return {
        type: MEALPLAN_RECIPE_SEARCH_SUCCESS,
        recipes
    }
}
/**
 * Indicates the MealPlan search was unsuccessful.
 * @param error
 * @returns {{type, error: *}}
 */
function recipeSearchFailure(error) {
    return {
        type: MEALPLAN_RECIPE_SEARCH_FAILURE,
        error
    }
}

/**
 * Gets all the MealPlans between the start date and end date
 * @param startDate Date
 * @param endDate Date
 * @returns {Promise}
 */
export function getMealPlans(startDate, endDate) {
    let query = null;

    if (startDate && endDate) {
        query = {startDate, endDate};
    }
    const token = cookie.get('token');
    return (dispatch) => {
        dispatch(mealPlansRequest(query));
        if(!token){
            return Promise.resolve(dispatch(mealPlansFailure({error: "User must be signed in!"})));
        }

        return get(query ? '/api/meal-plan?startDate=' + query.startDate + '&endDate=' + query.endDate : '/api/meal-plan',
            {
                headers: {
                    toque_token: token,
                    'Content-Type': 'application/json'
                }
            })
            .then((response) => {
                dispatch(mealPlansSuccess(response.data));
            })
            .catch((error) => {
                dispatch(mealPlansFailure(error));
            })
    }
}
/**
 * Creates a MealPlan based upon the supplied data.
 * @param mealPlan
 * @returns {Promise}
 */
export function createMealPlan(mealPlan) {
    const token = cookie.get('token');
    return (dispatch) => {
        dispatch(mealPlanCreateRequest(mealPlan));
        if(!token){
            return Promise.resolve(dispatch(mealPlanCreateFailure({error: "User must be signed in!"})));
        }
        return post('/api/meal-plan',
            mealPlan,
            {
                headers: {
                    toque_token: token,
                    'Content-Type': 'application/json'
                }
            })
            .then((response) => {
                dispatch(mealPlanCreateSuccess(response.data));
            })
            .catch((error) => {
                dispatch(mealPlanCreateFailure(error));
            })
    }
}

/**
 * Edits a MealPlan based upon the supplied data.
 * @param mealPlan
 * @returns {Promise}
 */
export function editMealPlan(mealPlan) {
    const token = cookie.get('token');
    return (dispatch) => {
        dispatch(mealPlanEditRequest(mealPlan));
        if(!token){
            return Promise.resolve(dispatch(mealPlanEditFailure({error: "User must be signed in!"})));
        }
        const id = mealPlan.id;
        delete mealPlan.id;
        return post('/api/meal-plan/' + id,
            mealPlan,
            {
                headers: {
                    toque_token: token,
                    'Content-Type': 'application/json'
                }
            })
            .then((response) => {
                dispatch(mealPlanEditSuccess(response.data));
            })
            .catch((error) => {
                dispatch(mealPlanEditFailure(error));
            })
    }
}

/**
 * Deletes the supplied MealPlan from the server.
 * @param mealPlan
 * @returns {Promise}
 */
export function deleteMealPlan(mealPlan) {
    const token = cookie.get('token');
    return (dispatch) => {
        dispatch(mealPlanDeleteRequest(mealPlan));
        if(!token){
            return Promise.resolve(dispatch(mealPlanDeleteFailure({error: "User must be signed in!"})));
        }
        return del('/api/meal-plan/' + mealPlan.id,
            {
                headers: {
                    toque_token: token,
                    'Content-Type': 'application/json'
                }
            })
            .then((response) => {
                dispatch(mealPlanDeleteSuccess(response.data));
            })
            .catch((error) => {
                dispatch(mealPlanDeleteFailure(error));
            })
    }
}

/**
 * Searches for Recipes with the given query.
 * @param searchQuery
 * @returns {Promise}
 */
export function searchRecipes(searchQuery) {
    const token = cookie.get('token');
    return (dispatch) => {
        dispatch(recipeSearchRequest(searchQuery));
        if(!token){
            return Promise.resolve(dispatch(recipeSearchFailure({error: "User must be signed in!"})));
        }
        return get('/api/recipe',
            {
                headers: {
                    toque_token: token,
                    'Content-Type': 'application/json'
                }
            })
            .then((response) => {
                console.log(response.data);
                dispatch(recipeSearchSuccess(response.data));
            })
            .catch((error) => {
                dispatch(recipeSearchFailure(error));
            })
    }
}