import {CREATE_RECIPE_REQUEST,
    CREATE_RECIPE_SUCCESS,
    CREATE_RECIPE_FAILURE,
    GET_INGREDIENTS_SUCCESS,
    CREATE_INGREDIENT_REQUEST,
    CREATE_INGREDIENT_SUCCESS,
    CREATE_INGREDIENT_FAILURE
} from '../../actionTypes';

const INITIAL_STATE = {
    ingredients: [],
    isCreatingRecipe: false,
    createdRecipe: false,
    isCreatingIngredient: false,
    createdIngredient: false
};

export default (state = INITIAL_STATE, action) => {
    switch(action.type) {
        case CREATE_RECIPE_REQUEST:
            return {
                ...state,
                isCreatingRecipe: false
            }

        case CREATE_RECIPE_SUCCESS:
            return {
                ...state,
                createdRecipe: true,
                isCreatingRecipe: false
            }

        case CREATE_RECIPE_FAILURE:
            return {
                ...state,
                createdRecipe: false,
                isCreatingRecipe: false
            }

        case GET_INGREDIENTS_SUCCESS:
            
            return {
                ...state,
                ingredients: action.ingredients,
            }

        case CREATE_INGREDIENT_REQUEST:
            return {
                ...state,
                isCreatingIngredient: true
            }

        case CREATE_INGREDIENT_SUCCESS:
            return {
              ...state,
              createdIngredient: true,
              isCreatingIngredient: false
            }

        case CREATE_INGREDIENT_FAILURE:
            return {
              ...state,
              createdIngredient: false,
              isCreatingIngredient: false
            }
        default:
            return state;
    }
}
