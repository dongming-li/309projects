import React, {Component} from 'react';
import { Input, Grid, Form, Message, Segment, Sidebar, Button, Menu, Icon, Image, Item, List, Header, Description } from 'semantic-ui-react';
/**
 * Component used to display the recipes list.
 * @extends Component
 */
class RecipeList extends React.Component {
    constructor(props) {
        super(props);
    
        this.getIngredientNames = this.getIngredientNames.bind(this);
    }

    getIngredientNames(ingredients){

        if(ingredients){
            const ingredientNames = ingredients.map( (ingredient) => ingredient.name);
    
            var stringNames = "";
            for(let i = 0; i<ingredientNames.length; i++){
               
                if(i ==0)
                    stringNames += ingredientNames[i];
                else 
                    stringNames += ", " + ingredientNames[i];
            }

            return {
               stringNames
            }
        }
        else {
            return {stringNames: "No Instructions"};
        }
    }

    render() {
        //recipes passed from Home
        const allRecipes = this.props.allRecipes;
        console.log(allRecipes);
        
        //create recipe list
        const recipes = allRecipes.map((recipe) => 
            <Item key={recipe.id}>
                <Item.Content verticalAlign='middle' >

                    <Item.Header>{recipe.name}</Item.Header>
                    <Item.Description>{recipe.description}</Item.Description>
                    <Item.Description>Ingredients: {this.getIngredientNames(recipe.ingredients).stringNames}</Item.Description>
                    
                </Item.Content>
            </Item>   
        );
        
        return (
            <div >
            <Item.Group divided style={{overflowY:'scroll', height: '80vh'}}>
                {recipes}
            </Item.Group>
            </div> 
        );

    }
}


export default RecipeList;