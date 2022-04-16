import React, {Component} from 'react'
import { Button, Header, Image, Modal, Form, Icon, Dropdown} from 'semantic-ui-react'
import DateTime from 'react-datetime';

import 'react-datetime/css/react-datetime.css'
/**
 * Component used to create/edit a meal plan.
 * @extends Component
 */
class CreateMealPlanModal extends Component{
    constructor(props){
        super(props);

        this.state = {
            date: new Date(),
            recipe: undefined,
            recipes: this.props.recipes
        }

    }

    handleRecipe(e, data) {
        const val= data.value;
        this.setState((prevState) =>
            ({...prevState,
                recipe: val}))
    }

    handleDate(momentDate) {
        this.setState((prevState) =>
            ({...prevState,
                date: momentDate.format()}))
    }

    handleCreate(){
        this.props.onCreate(this.state.recipe, this.state.date)
            .then(this.props.closeModal)
            .then(this.props.onSubmit)
    }

    handleEdit(){
        this.props.onEdit({id: this.props.mealPlan.id, recipe: this.state.recipe, date: this.state.date})
            .then(this.props.closeModal)
            .then(this.props.onSubmit)
    }

    handleDelete(){
        this.props.onDelete(this.props.mealPlan)
            .then(this.props.closeModal)
            .then(this.props.onSubmit)
    }

    handleRecipeSearch(searchQuery) {
        this.props.onSearchRecipes(searchQuery);
    }

    componentWillReceiveProps(nextProps) {
        const {recipes, mealPlan} = nextProps;

        this.setState((prevState) => ({
            recipes: recipes,
            date: mealPlan.date
        }));

        if(mealPlan.recipe)
        {
            if(!recipes.some((recipe) => recipe.key == mealPlan.recipe.id))
                recipes.push({
                    key: mealPlan.recipe.id,
                    value: mealPlan.recipe.id,
                    text: mealPlan.recipe.title
                });
            this.setState((prevState) => ({
                ...prevState,
                recipe: mealPlan.recipe.id,
                recipes: recipes
            }))
        }
    }

    render() {
        const type = this.props.type;

        return(
            <Modal open={this.props.isOpen} closeIcon={<Icon name="close" onClick={this.props.closeModal}/>}>
                <Modal.Header>{type} Meal Plan</Modal.Header>
                <Modal.Content>
                    <Form loading={this.props.isLoading}>
                        <Form.Field>
                            <label>Date</label>
                            <DateTime
                                defaultValue={this.props.mealPlan.date}
                                onChange={this.handleDate.bind(this)}
                            />
                        </Form.Field>
                        <Form.Field>
                            <label>Recipe</label>
                            <Dropdown
                                fluid
                                search
                                selection
                                value={this.state.recipe}
                                onSearchChange={this.handleRecipeSearch.bind(this)}
                                onChange={this.handleRecipe.bind(this)}
                                placeholder="Select Recipe"
                                options={this.state.recipes}
                                loading={this.props.isRecipesLoading}
                            />
                        </Form.Field>
                        {
                            type == "Edit" ? <Button color="red" type='submit' onClick={this.handleEdit.bind(this)}>Edit</Button> : null
                        }
                        {
                            type == "Edit" ? <Button color='yellow' type='submit' onClick={this.handleDelete.bind(this)}>Delete</Button> : null
                        }
                        {
                            type == "Create" ? <Button color='green' type='submit'  onClick={this.handleCreate.bind(this)}>Create</Button> : null
                        }


                    </Form>
                </Modal.Content>
            </Modal>)
    }
}

export default CreateMealPlanModal