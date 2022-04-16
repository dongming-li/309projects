import {
    GET_RECIPES_REQUEST,
    GET_RECIPES_SUCCESS,
    GET_RECIPES_FAILURE
} from '../../actionTypes';

const INITIAL_STATE = {
    recipes: [],
    receivedRecipes: false
};

export default (state = INITIAL_STATE, action) => {
    switch(action.type) {
        case GET_RECIPES_REQUEST:
            return {
                ...state,
                receivedRecipes: false
            };

        case GET_RECIPES_SUCCESS:
            return {
                ...state,
                recipes: action.recipes,
                receivedRecipes: true
            };
        case GET_RECIPES_FAILURE:
            return {
                ...state,
                receivedRecipes: false
            };

        default:
            return state;
    }
}
