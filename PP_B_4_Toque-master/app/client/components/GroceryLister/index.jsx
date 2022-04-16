import React, { Component } from 'react';
import { Container } from 'semantic-ui-react';

import IngredientList from './IngredientList.jsx';
import MultiGroceryList from './MultiGroceryList.jsx';
import CreateGroceryList from './CreateGroceryList.jsx';

/**
 * Component for the Grocery List function
 * @extends Component
 */
class GroceryLister extends Component {
    constructor(props) {
        super(props);
    }

    /**
     * Checks wether the component has been mounted
     */
    componentDidMount() {
        this.props.getGroceryLists();
    }

    /**
     * Finds a grocery list that matches the given id from
     * the grocery lists stored in props
     * @param {number} id
     * @returns {groceryList} list
     */
    findListFromId(id) {
        let list = null;
        this.props.groceryLists.forEach((gl) => {
            if(gl.id == id) {
                list = gl;
            }
        });
        return list;
    }

    /**
     * Determines which component to render
     * Does route matching to determine this
     * If the route contains a grocery list id,
     * will render the ingredient list for the
     * grocery list with the given id
     * @returns {component} the component to render
     */
    conditionalRender() {
        let listID = parseInt(this.props.match.params.id);
        let path = this.props.match.path;
        let list = null;
        if(listID && typeof(listID) === 'number') {
            list = this.findListFromId(listID);
            return(
                <IngredientList
                    id={listID}
                    list={list}
                />
            );
        } else if(path === '/create-grocery-list') {
            return(
                <CreateGroceryList
                    ingredients={this.props.ingredients}
                    onSearchIngredients={this.props.onSearchIngredients}
                    isIngredientsLoading={this.props.isIngredientsLoading}
                />
            );
        } else {
            return(
                <MultiGroceryList
                    groceryLists={this.props.groceryLists}
                />
            );
        }
    }

    /**
     * Renders the component returned from conditionalRender
     * @returns the view
     */
    render() {
        return(
            <Container>
                {this.conditionalRender()}
            </Container>
        );
    }
}

export default GroceryLister;
