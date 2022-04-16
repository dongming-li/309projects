import React, { Component } from 'react';
import { List, Image } from 'semantic-ui-react';

/**
 * Creates the IngredientList view
 * @extends Component
 */
class IngredientList extends Component {
    constructor(props) {
        super(props);
    }

    /**
     * Create the ingredient list html from a list of ingredients
     * @return {html}  a list of ingredients
     */
    createIngredientList() {
        return(
            this.props.list.ingredients.map((ingredient, i) => {
                return(
                    <List.Item key={i}>
                        <List.Content>
                            <List.Header>{ingredient.name}</List.Header>
                                Calories: {ingredient.calories ? ingredient.calories : 'Not Set'} <br/>
                                Fat: {ingredient.fat ? ingredient.fat : 'Not Set'}<br/>
                                Sugar: {ingredient.sugar ? ingredient.sugar : 'Not Set'}<br/>
                                Sodium: {ingredient.sodium ? ingredient.sodium : 'Not Set'}<br/>
                                Protein: {ingredient.protein ? ingredient.protein : 'Not Set'}<br/>
                                Carbs: {ingredient.carbs ? ingredient.carbs : 'Not Set'}<br/>
                                Chrolesterol: {ingredient.cholesterol ? ingredient.chrolesterol : 'Not Set'}<br/>
                                Calcium: {ingredient.calcium ? ingredient.calcium : 'Not Set'}<br/>
                                Iron: {ingredient.iron ? ingredient.iron : 'Not Set'}<br/>
                        </List.Content>
                    </List.Item>
                );
            })
        );
    }

    /**
     * Renders the IngredientList component
     * @return {html} the IngredientList component
     */
    render() {
        return (
            <div>
                <h1>{this.props.list.title}</h1>
                <h5>Created on {this.props.list.start.toDateString()}</h5>
                <List celled>
                    {this.createIngredientList()}
                </List>
            </div>
        );
    }
}

export default IngredientList;

