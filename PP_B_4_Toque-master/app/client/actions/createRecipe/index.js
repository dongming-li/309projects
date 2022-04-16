import {post, get} from 'axios';
import Cookie from 'universal-cookie';
const cookie = new Cookie();

import {CREATE_RECIPE_REQUEST,
        CREATE_RECIPE_SUCCESS,
        CREATE_RECIPE_FAILURE,
        GET_INGREDIENTS_SUCCESS,
        CREATE_INGREDIENT_REQUEST,
        CREATE_INGREDIENT_SUCCESS,
        CREATE_INGREDIENT_FAILURE
} from '../../actionTypes';

/**
 * Indicates create recipe request
 * @param {string} name
 * @return {object} contains action and name
 */
function createRecipeRequest(name){
    return {
        type: CREATE_RECIPE_REQUEST,
        name
    }
}

/**
 * Indicates create recipe succeeded
 * @param {string} name
 * @param {string} description
 * @param {ingredient} ingredients
 * @return {object} contains action, name, description, ingredients
 */
function createRecipeSuccess(name, description, ingredients){
    return {
        type: CREATE_RECIPE_SUCCESS,
        name,
        description,
        ingredients
    }
}

/**
 * Indicates create recipe failed
 * @param {error} error
 * @return {object} contains action and error
 */
function createRecipeFailure(error){
    return {
        type: CREATE_RECIPE_FAILURE,
        error
    }
}

/**
 * Indicates ingredient create succeeded
 * @param {ingredient} ingredients
 * @return {object} contains action and ingredients
 */
function getIngredientsSuccess(ingredients){
    return {
        type: GET_INGREDIENTS_SUCCESS,
        ingredients
    }
}

/**
 * Indicates create ingredient request
 * @param {string} name
 * @return {object} contains action and name
 */
function createIngredientRequest(name){
    return {
        type: CREATE_INGREDIENT_REQUEST,
        name
    }
}

/**
 * Indicates create ingredient succeeded
 * @param {ingredient} ingredients
 * @return {object} contains action and ingredients
 */
function createIngredientSuccess(ingredients){
    return {
        type: CREATE_INGREDIENT_SUCCESS,
        ingredients
    }
}

/**
 * Indicates create ingredient failure
 * @return {object} contains action
 */
function createIngredientFailure(){
    return {
        type: CREATE_INGREDIENT_FAILURE,

    }
}

/**
 * Create a new recipe
 * @param {recipe} recipe
 * @returns {Promise}
 */
export function createRecipe(recipe){
    const token = cookie.get('token');
    return(dispatch) => {

        dispatch(createRecipeRequest(name));

        if(!token){
            return Promise.resolve(dispatch(createRecipeFailure({error: "User must be signed in!"})));
        }
        console.log("token: " + token);
        return post('/api/recipe',
            recipe,
            {
                headers: {
                    toque_token: token,
                    'Content-Type': 'application/json'
                }
            })
            .then((response) => {
                console.log(response);
                dispatch(createRecipeSuccess(response.data));
                console.log(response);
            })
            .catch((error) => {
                dispatch(createRecipeFailure(error));
                console.log(error);
            })
    }
}

/**
 * Get ingredients
 * @returns {Promise}
 */
export function getIngredients(){
    return(dispatch) => {
        //dispatch request?
        return get('/api/ingredient')
            .then((response) => {
                dispatch(getIngredientsSuccess(response.data));
            })
            .catch((error) => {
                console.log(error);
            })
    }
}

/**
 * Create a new ingredient
 * @param {string} name
 * @returns {Promise}
 */
export function createIngredient(name){
    return(dispatch) => {
        dispatch(createIngredientRequest(name));
        return post('/api/ingredient', {
                name
            })
            .then((response) => {
                dispatch(createIngredientSuccess(response.data));
            })
            .catch((error) => {
                dispatch(createIngredientFailure(error));
            })
    }
}
