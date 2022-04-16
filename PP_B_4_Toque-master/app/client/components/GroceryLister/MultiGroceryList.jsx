import React, { Component } from 'react';
import { List, Button } from 'semantic-ui-react';
import { Link } from 'react-router-dom';

/**
 * Grocery List View component
 * Shows a list of all a users grocery lists
 * @extends Component
 */
class MultiGroceryList extends Component {
    constructor(props) {
        super(props);
    }

    /**
     * Creates a list of grocery list titles and their creation dates
     * @return {html} list of title and dates
     */
    createList() {
        return this.props.groceryLists.map((gl, i) => {
            return(
                <List.Item key={i}>
                    <List.Content>
                        <List.Header>
                            <Link to={'/grocery-lists/' + gl.id}>
                                {gl.title}
                            </Link>
                        </List.Header>
                        Created on {gl.start.toDateString()}
                    </List.Content>
                </List.Item>
            );
        });
    }

    /**
     * Renders the MultiGroceryList component
     * @return {html} the MultiGroceryList component
     */
    render() {
        return (
            <div>
                <h1>Your Grocery Lists</h1>
                <List celled verticalAlign='middle'>
                    { this.createList() }
                </List>
                <Link to='/create-grocery-list'>
                    <Button color="blue">
                        Create Grocery List
                    </Button>
                </Link>
            </div>
        );
    }
}

export default MultiGroceryList;

