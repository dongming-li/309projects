import {get} from 'axios';

import {
    GET_RECIPES_REQUEST,
    GET_RECIPES_SUCCESS,
    GET_RECIPES_FAILURE
} from '../../actionTypes';

/**
 * Indicates a request to get recipes
 * @return {object} contains action
 */
function getRecipesRequest(){
    return {
        type: GET_RECIPES_REQUEST
    }
}

/**
 * Indicates get of recipe succeeded
 * @param {recipe} recipes
 * @return {object} contains action and recipes
 */
function getRecipesSuccess(recipes){
    return {
        type: GET_RECIPES_SUCCESS,
        recipes
    }
}

/**
 * Indicates get of recipes failed
 * @param {error} error
 * @returns {object} contains action and error
 */
function getRecipesFailure(error){
    return {
        type: GET_RECIPES_FAILURE,
        error
    }
}

/**
 * Function to get all recipes
 * @returns {Promise}
 */
export function getRecipes(){
    return(dispatch) => {
        dispatch(getRecipesRequest());
        return get('/api/recipe')
            .then((response) => {
                dispatch(getRecipesSuccess(response.data));
            })
            .catch((error) => {
                dispatch(getRecipesFailure(error));
                console.log(error);
            })
    }
}
