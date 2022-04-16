import {connect} from 'react-redux';
import MealPlannerComponent from '../../components/MealPlanner/index.jsx';
import {getMealPlans, createMealPlan, deleteMealPlan, editMealPlan, searchRecipes} from "../../actions/meal-plan";

const mapStateToProps = state => {
    const mealPlans = state.mealPlanner.mealPlans.map((mealPlan) => {
        const start = new Date(mealPlan.date);
        const end = new Date(start.getTime());
        end.setUTCMinutes(end.getUTCMinutes() + 30);//TODO make this be the recipe length
        return {
            id: mealPlan.id,
            recipe: mealPlan.recipe,
            title: mealPlan.recipe.title,
            start,
            end
        }
    });
    const recipes = state.mealPlanner.recipes.map((recipe) => {
        return {
            key: recipe.id,
            value: recipe.id,
            text: recipe.title
        }
    });

    return {
        mealPlans,
        recipes,
        isLoading: state.mealPlanner.isLoading,
        isRecipesLoading: state.mealPlanner.isRecipesLoading
    }
};

const mapDispatchToProps = dispatch => {
    return {
        getMealPlans: () => dispatch(getMealPlans()),
        onSearchRecipes: (searchQuery) => (dispatch(searchRecipes(searchQuery))),
        onCreateMealPlan: (recipe, date) =>  (dispatch(createMealPlan({recipe_id: recipe, date}))),
        onEditMealPlan: ({id, recipe, date}) =>  (dispatch(editMealPlan({id, recipe_id: recipe, date}))),
        onDeleteMealPlan: (mealPlan) =>  (dispatch(deleteMealPlan(mealPlan))),
    }
};

const MealPlanner = connect(
    mapStateToProps,
    mapDispatchToProps
)(MealPlannerComponent);

/**
 * Container that injects props into the MealPlanner Component
 */
export default MealPlanner;