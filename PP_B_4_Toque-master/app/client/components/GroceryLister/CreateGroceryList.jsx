import React, {Component} from 'react';
import { Header, Button, Form, Dropdown, Container } from 'semantic-ui-react';
import DateTime from 'react-datetime';

import 'react-datetime/css/react-datetime.css'

/**
 * Component for the create grocery list page
 * @extends Component
 */
class CreateGroceryList extends Component {
    /**
     * Create a 'CreateGroceryList' object
     * @param {object} props
     */
    constructor(props) {
        super(props);

        this.state = {
            date: new Date(),
            ingredient: undefined,
            ingredients: this.props.ingredients,
            mealplans: this.props.mealPlans
        }
    }

    /**
     * Sets the state's ingredient to the one stored
     * in data.value
     * @param {error} e
     * @param {ingredient} data
     */
    handleIngredient(e, data) {
        const val = data.value;
        this.setState((prevState) =>
            ({...prevState,
                ingredient: val}))
    }

    /**
     * Calls {@link onSearchIngredients} stored in the props
     * @param {ingredient} searchQuery
     */
    handleIngredientSearch(searchQuery) {
        this.props.onSearchIngredients(searchQuery);
    }

    /**
     * Renders the createGroceryList component
     * @returns the create view
     */
    render() {
        return(
            <div>
                <Container>
                    <Header>Create a Grocery List</Header>
                    <Form>
                        <Form.Field>
                            <label>Name</label>
                            <input placeholder='name' />
                        </Form.Field>
                        <Form.Field>
                            <label>Date</label>
                            <DateTime

                            />
                        </Form.Field>
                        <Form.Field>
                            <label>Ingredients</label>
                            <Dropdown
                                fluid
                                search
                                selection
                                value={this.state.ingredient}
                                onSearchChange={this.handleIngredientSearch.bind(this)}
                                onChange={this.handleIngredient.bind(this)}
                                placeholder='Select Ingredients'
                                options={this.props.ingredients}
                                loading={this.props.isIngredientsLoading}
                            />
                        </Form.Field>
                        <Button
                            color="blue"
                            type="submit"
                            // onClick={this.handleCreate.bind(this)}
                        >
                            Create
                        </Button>
                        {/* <Form.Field>
                            <label>MealPlans</label>
                            <Dropdown
                                fluid
                                search
                                selection
                                value={this.state.mealplans}
                                placeholder='Select Meal Plans'
                            />
                        </Form.Field> */}
                    </Form>
                </Container>
            </div>
        );
    }
}

export default CreateGroceryList;
