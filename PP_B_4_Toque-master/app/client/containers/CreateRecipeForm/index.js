import { connect } from 'react-redux';
import CreateRecipeComponent from '../../components/CreateRecipe/CreateRecipeForm.jsx';
import { createRecipe, createIngredient, getIngredients } from '../../actions/createRecipe/index.js';

const mapStateToProps = state => {

    const ingredients = state.createRecipe.ingredients.map( (ingredient) => {
        return {
            id: ingredient.id,
            name: ingredient.name,
        }
    });

    return {
        ingredients,
        createdRecipe: state.createRecipe.createdRecipe
    }
};


const mapDispatchToProps = dispatch => {
    return {
        getIngredients: () => dispatch(getIngredients()),
        createIngredient: (name) => dispatch(createIngredient(name)),
        createRecipe: ({name, description, ingredients}) => dispatch(createRecipe({name, description, ingredients}))
    }
};


const CreateRecipeForm = connect(
    mapStateToProps,
    mapDispatchToProps
)(CreateRecipeComponent);

/**
 * Container that injects props into the CreateRecipeComponent
 */
export default CreateRecipeForm;