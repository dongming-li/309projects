import { post } from 'axios';
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

const INITIAL_STATE = {
    isFetching: false,
    query: null,
    groceryLists: [],
    isCreating: false,
    creatingGroceryList: null,
    groceryList: null,
    ingredientQuery: '',
    mealPlanQuery: '',
    isIngredientLoading: false,
    isMealPlanLoading: false,
    ingredients: [],
    mealPlans: []
};

export default (state = INITIAL_STATE, action) => {
    switch (action.type) {
        case GROCERYLIST_REQUEST:
            return {
                ...state,
                isFetching: true,
                query: action.query
            };
        case GROCERYLIST_SUCCESS:
            return {
                ...state,
                groceryLists: action.groceryLists,
                isFetching: false
            };
        case GROCERYLIST_FAILURE:
            return {
                ...state,
                query: null,
                isFetching: false
            };
        case GROCERYLIST_CREATE_REQUEST:
            return {
                ...state,
                isCreating: true,
                creatingGroceryList: action.groceryList
            };
        case GROCERYLIST_CREATE_SUCCESS:
            return {
                ...state,
                isCreating: false,
                creatingGroceryList: null
            };
        case GROCERYLIST_CREATE_FAILURE:
            return {
                ...state,
                isCreating: false
            };
        case GROCERYLIST_INGREDIENT_SEARCH_REQUEST:
            return {
                ...state,
                isIngredientsLoading: true
            }
        case GROCERYLIST_INGREDIENT_SEARCH_SUCCESS:
            return {
                ...state,
                isIngredientsLoading: false,
                ingredients: action.ingredients
            }
        case GROCERYLIST_INGREDIENT_SEARCH_FAILURE:
            return {
                ...state,
                isIngredientsLoading: false
            }
        case GROCERYLIST_MEALPLAN_SEARCH_REQUEST:
            return {
                ...state,
                isMealPlanLoading: true
            }
        case GROCERYLIST_MEALPLAN_SEARCH_SUCCESS:
            return {
                ...state,
                isMealPlanLoading: false,
                mealPlans: action.mealPlans
            }
        case GROCERYLIST_MEALPLAN_SEARCH_FAILURE:
            return {
                ...state,
                isMealPlanLoading: false
            }
        default:
            return state;
    }
}
