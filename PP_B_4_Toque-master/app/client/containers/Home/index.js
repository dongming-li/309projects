import { connect } from 'react-redux';
import HomeComponent from '../../components/Home/Home.jsx';
import { getRecipes } from '../../actions/home/index.js';

const mapStateToProps = state => {

    const recipes = state.home.recipes.map( (recipe) => {
        return {
            id: recipe.id,
            name: recipe.title,
            description: recipe.content,
            ingredients: recipe.ingredients
        }
    });
    console.log(recipes);
    return {
        recipes,
        receivedRecipes: state.home.receivedRecipes
    }
};


const mapDispatchToProps = dispatch => {
    return {
        getRecipes: () => dispatch(getRecipes()),
    }
};


const Home = connect(
    mapStateToProps,
    mapDispatchToProps
)(HomeComponent);

/**
 * Container that injects props into the HomeComponent
 */
export default Home;
