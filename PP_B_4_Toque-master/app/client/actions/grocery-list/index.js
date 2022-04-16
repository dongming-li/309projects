import { post, get } from 'axios';
import Cookies from 'universal-cookie';
const cookie = new Cookies();
import {
    GROCERYLIST_REQUEST,
    GROCERYLIST_SUCCESS,
    GROCERYLIST_FAILURE,
    GROCERYLIST_CREATE_REQUEST,
    GROCERYLIST_CREATE_SUCCESS,
    GROCERYLIST_CREATE_FAILURE,
    GROCERYLIST_INGREDIENT_SEARCH_REQUEST,
    GROCERYLIST_INGREDIENT_SEARCH_SUCCESS,
    GROCERYLIST_INGREDIENT_SEARCH_FAILURE,
    GROCERYLIST_MEALPLAN_SEARCH_REQUEST,
    GROCERYLIST_MEALPLAN_SEARCH_SUCCESS,
    GROCERYLIST_MEALPLAN_SEARCH_FAILURE
} from '../../actionTypes';

/**
 * Indicates get of grocery list requested
 * @param  query
 * @returns {object} contains action & ingredients
 */
function groceryListRequest(query) {
    return {
        type: GROCERYLIST_REQUEST,
        query
    };
}

/**
 * Indicates get of grocery list succeeded
 * @param {groceryList} groceryLists
 * @returns {object} contains action & ingredients
 */
function groceryListSuccess(groceryLists) {
    return {
        type: GROCERYLIST_SUCCESS,
        groceryLists
    };
}

/**
 * Indicates get of grocery list failed
 * @param {error} error
 * @returns {object} contains action & error
 */
function groceryListFailure(error) {
    return {
        type: GROCERYLIST_FAILURE,
        error
    };
}

/**
 * Indicates creation of grocery list requested
 * @param {groceryList} groceryLists
 * @returns {object} contains action & ingredients
 */
function groceryListCreateRequest(groceryLists) {
    return {
        type: GROCERYLIST_CREATE_REQUEST,
        groceryLists
    };
}

/**
 * Indicates creation of grocery list succeeded
 * @param {groceryList} groceryLists
 * @returns {object} contains action & groceryLists
 */
function groceryListCreateSuccess(groceryLists) {
    return {
        type: GROCERYLIST_CREATE_SUCCESS,
        groceryLists
    };
}

/**
 * Indicates creation of grocery list failed
 * @param {error} error
 * @returns {object} contains action & error
 */
function groceryListCreateFailure(error) {
    return {
        type: GROCERYLIST_CREATE_FAILURE,
        error
    };
}

/**
 * Indicates search for ingredients requested
 * @param {ingredient} ingredients
 * @returns {object} contains action & ingredients
 */
function ingredientSearchRequest(ingredients) {
    return {
        type: GROCERYLIST_INGREDIENT_SEARCH_REQUEST,
        ingredients
    };
}

/**
 * Indicates search for ingredients succeeded
 * @param {ingredient} ingredients
 * @returns {object} contains action & ingredients
 */
function ingredientSearchSuccess(ingredients) {
    return {
        type: GROCERYLIST_INGREDIENT_SEARCH_SUCCESS,
        ingredients
    };
}

/**
 * Indicates search for ingredients failed
 * @param {error} error
 * @returns {object} contains action & error
 */
function ingredientSearchFailure(error) {
    return {
        type: GROCERYLIST_INGREDIENT_SEARCH_FAILURE,
        error
    };
}

/**
 * Gets a grocery list with the given id
 * @param {number} id
 * @returns {Promise}
 */
export function getGroceryList(id) {
    let query = null;

    if (id) {
        query.id = id;
    } else {
        return Promise.resolve(dispatch(groceryListFailure({ error: 'Must specify a Grocery List ID' })));
    }

    const token = cookie.get('token');
    return (dispatch) => {
        dispatch(groceryListRequest(query));

        if (!token) {
            return Promise.resolve(dispatch(groceryListFailure({ error: 'User must be signed in!' })));
        }

        let queryString = '/api/grocery-list/' + id;

        return get(queryString,
            {
                headers: {
                    toque_token: token,
                    'Content-Type': 'application/json'
                }
            })
            .then((response) => {
                dispatch(groceryListSuccess(response.data));
            })
            .catch((error) => {
                dispatch(groceryListError(error));
            });
    }
}

/**
 * Gets a grocery list by the given parameters
 * @param {number} id
 * @param {string} name
 * @param {date} startDate
 * @param {date} endDate
 * @returns {Promise}
 */
export function getGroceryLists(id, name, startDate, endDate) {
    let query = null;

    if(name) {
        query.name = name;
    }

    if(startDate && endDate) {
        query.endDate = startDate;
        query.startDate = endDate;
    }

    const token = cookie.get('token');
    return (dispatch) => {
        dispatch(groceryListRequest(query));

        if(!token) {
            return Promise.resolve(dispatch(groceryListFailure({ error: 'User must be signed in!'})));
        }

        let queryString = '/api/grocery-list';

        if(id) {
            queryString += '/' + id;
        } else if(query) {
            queryString += '?';

            if(query.name) {
                queryString += 'name=' + query.name;
            }

            if(query.startDate && query.endDate) {
                queryString += 'startDate=' + query.startDate + '&endDate=' + query.endDate;
            }
        }

        return get(queryString,
            {
                headers: {
                    toque_token: token,
                    'Content-Type': 'application/json'
                }
            })
            .then((response) => {
                dispatch(groceryListSuccess(response.data));
            })
            .catch((error) => {
                dispatch(groceryListError(error));
            });
    }
}

/**
 * Creates a grocery list from the given grocerylist object
 * @param {groceryList} groceryList
 * @returns {Promise}
 */
export function createGroceryList(groceryList) {
    const token = cookie.get('token');
    return (dispatch) => {
        dispatch(groceryListCreateRequest(groceryList));
        if(!token) {
            return Promise.resolve(dispatch(groceryListFailure({ error: 'User must be signed in!' })));
        }
        return post('api/grocery-list',
            groceryList,
            {
                headers: {
                    toque_token: token,
                    'Content-Type': 'application/json'
                }
            })
            .then((response) => {
                dispatch(groceryListCreateSuccess(response.data));
            })
            .catch((error) => {
                dispatch(groceryListCreateFailure(error));
            });
    }
}

/**
 * Searches ingredients that match searchQuery
 * @param {ingredient} searchQuery
 * @returns {Promise}
 */
export function searchIngredients(searchQuery) {
    const token = cookie.get('token');
    return (dispatch) => {
        dispatch(ingredientSearchRequest(searchQuery));
        if (!token) {
            return Promise.resolve(dispatch(recipeSearchFailure({ error: "User must be signed in!" })));
        }
        return get('/api/ingredient',
            {
                headers: {
                    toque_token: token,
                    'Content-Type': 'application/json'
                }
            })
            .then((response) => {
                console.log(response.data);
                dispatch(ingredientSearchSuccess(response.data));
            })
            .catch((error) => {
                dispatch(ingredientSearchFailure(error));
            })
    }
}
