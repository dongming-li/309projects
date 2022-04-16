import {
    MEALPLANS_FAILURE,
    MEALPLANS_REQUEST,
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

const INITIAL_STATE = {
    isFetching: false,
    query: null,
    mealPlans: [],
    isLoading: false,
    mealPlan: null, //The meal plan the user is currently creating/editing/deleting, we will store it here while the request is sent,
    recipeQuery: '',
    isRecipesLoading: false,
    recipes: []
};

export default (state = INITIAL_STATE, action) => {
    switch (action.type) {
        case MEALPLANS_REQUEST:
            return {
                ...state,
                isFetching: true,
                query: action.query
            };
        case MEALPLANS_SUCCESS:
            return {
                ...state,
                mealPlans: action.mealPlans,
                isFetching: false
            };
        case MEALPLANS_FAILURE:
            return {
                ...state,
                query: null,
                isFetching: false
            };
        case MEALPLAN_CREATE_REQUEST:
            return  {
                ...state,
                isLoading: true,
                mealPlan: action.mealPlan
            };
        case MEALPLAN_CREATE_SUCCESS:
            return {
                ...state,
                isLoading: false,
                mealPlan: null
            };
        case MEALPLAN_CREATE_FAILURE:
            return {
                ...state,
                isLoading: false,
            };
        case MEALPLAN_EDIT_REQUEST:
            return  {
                ...state,
                isLoading: true,
                mealPlan: action.mealPlan
            };
        case MEALPLAN_EDIT_SUCCESS:
            return {
                ...state,
                isLoading: false,
                mealPlan: null
            };
        case MEALPLAN_EDIT_FAILURE:
            return {
                ...state,
                isLoading: false,
            };
        case MEALPLAN_DELETE_REQUEST:
            return  {
                ...state,
                isLoading: true,
                mealPlan: action.mealPlan
            };
        case MEALPLAN_DELETE_SUCCESS:
            return {
                ...state,
                isLoading: false,
                mealPlan: null
            };
        case MEALPLAN_DELETE_FAILURE:
            return {
                ...state,
                isLoading: false,
            };
        case MEALPLAN_RECIPE_SEARCH_REQUEST:
            return  {
                ...state,
                isRecipesLoading: true,
            };
        case MEALPLAN_RECIPE_SEARCH_FAILURE:
            return {
                ...state,
                isRecipesLoading: false,
            };
        case MEALPLAN_RECIPE_SEARCH_SUCCESS:
            return {
                ...state,
                isRecipesLoading: false,
                recipes: action.recipes
            };
        default:
            return state;

    }
}