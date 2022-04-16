import { connect } from 'react-redux';
import GroceryListComponent from '../../components/GroceryLister/index.jsx';
import IngredientListComponent from '../../components/GroceryLister/IngredientList.jsx';
import { getGroceryLists, createGroceryList, searchIngredients } from '../../actions/grocery-list/index';
import { withRouter } from 'react-router-dom';

/**
 * Maps grocery list fields to a grocery list object
 * @param {object} state
 * @return {object} containing id, title, start, end, ingredients, and mealPlans
 */
const mapStateToProps = state => {
    const groceryLists = state.groceryLister.groceryLists.map((groceryList) => {
        const id = groceryList.id;
        const title = groceryList.title;
        const start = new Date(groceryList.date);
        const end = new Date(start.toJSON());
        const ingredients = groceryList.ingredients;
        const mealPlans = groceryList.mealPlans;

        return {
            id,
            title,
            start,
            end,
            ingredients,
            mealPlans
        }
    });

    const ingredients = state.groceryLister.ingredients.map((ingredient) => {
        return {
            key: ingredient.id,
            value: ingredient.id,
            text: ingredient.name
        }
    });

    const mealPlans = state.groceryLister.mealPlans.map((mealPlan) => {
        return {
            key: mealPlan.id,
            value: mealPlan.id,
            text: mealPlan.recipe.title
        }
    })

    return {
        groceryLists,
        ingredients,
        mealPlans,
        isLoading: state.groceryLister.isCreating,
        isIngredientsLoading: state.groceryLister.isIngredientsLoading,
        isMealPlansLoading: state.groceryLister.isMealPlansLoading
    }
};

/**
 * Sends dispatch function in the props
 * @param {function}  dispatch
 * @return {object}  containing getGroceryLists, onSearchIngredients, onSubmitGroceryList
 */
const mapDispatchToProps = dispatch => {
    return {
        getGroceryLists: (id, name, start, end) => dispatch(getGroceryLists(id, name, start, end)),
        onSearchIngredients: (searchQuery) => dispatch(searchIngredients(searchQuery)),
        onSubmitGroceryList: (title, date, ingredients, mealPlans) => (
            dispatch(createGroceryList({ title, date, ingredients, mealPlans }))
        )
    }
};

const GroceryLister = connect(
    mapStateToProps,
    mapDispatchToProps
) (GroceryListComponent);

/**
 * Container that injects props into the GroceryLister component
 */
export default withRouter(GroceryLister);
