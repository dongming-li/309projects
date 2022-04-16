import React, {Component, contextTypes} from 'react';
import { Input, Grid, Form, Message, Segment, Sidebar, Button, Menu, Icon, Image, Header, Dropdown } from 'semantic-ui-react';
import { connect } from 'react-redux';
/**
 * Component that is used for the create recipe screen
 */
class CreateRecipeForm extends React.Component {

    constructor(props) {
        super(props);

        this.handleChange = this.handleChange.bind(this);
        this.handleSubmit = this.handleSubmit.bind(this);

        this.state = {
            title: this.props.title,
            description: this.props.description,
            currIngredients: [],
        };
    }


    /**
     * gets ingredients from the server on pageload, and initializes the dropdown list
     */
    componentDidMount() {
        this.props.getIngredients();
    }



    /**
     * adds new ingredient to the state and updates the dropdown form
     * @param {*} e 
     * @param {*} param1 
     */
    addIngredient(e, { value }) {
        this.props.createIngredient(value)
            .then(this.props.getIngredients);
    }


    /**
     * handles the change of Semantic UI elements
     * @param {*} e 
     * @param {*} param1 
     */
    handleChange(e, {name, value}){
        console.log(name, value);
        this.setState({
            [name]: value
        });
    }


    /**
     * handles the submission of the CreateRecipe Form, creating a new recipe
     * @param {*} e 
     */
    handleSubmit(e) {
        const {title, description, currIngredients} = this.state;
        console.log(title + " " + description + " " + currIngredients);
        
        const recipeIngredients = currIngredients
            .filter((id) => (!isNaN(id)))
            .map((id) => ({id}));

        this.props.createRecipe({name: title, description: description, ingredients: recipeIngredients})
            .then(() => this.setState((preState) => ({...preState, currIngredients: []})))
            .then(() => window.location = '#/home');
    }

    render() {
        const dropdownIngredients = this.props.ingredients.map( (ingredient) => {
            return {
                key: ingredient.id,
                text: ingredient.name,
                value: ingredient.id
            }
        });

        return(
            
            <Segment basic>
                <Grid verticalAlign='middle'>
                    <Grid.Column style={{ height: '100vh', maxWidth: 450 }}>

                        <Header>Create a Recipe</Header>

                        <div style={{height:'100vh'}}>
                        <Form onSubmit={this.handleSubmit}>
                            <Form.Input label='Recipe Title'
                                placeholder='Recipe Title'
                                name='title'
                                value={this.state.title}
                                onChange={ (e, {name, value}) => this.handleChange(e, {name,value})}
                            />

                            <Form.TextArea label='Description'
                                placeholder='Tell us about this recipe...'
                                name='description'
                                value={this.state.description}
                                onChange={ (e, {name, value}) => this.handleChange(e, {name,value})}
                            />

                            <Dropdown
                                multiple 
                                options={dropdownIngredients}
                                placeholder='Add Ingredients'
                                search
                                selection
                                fluid
                                allowAdditions
                                additionLabel='Add: '
                                name='currIngredients'
                                onAddItem={this.addIngredient.bind(this)}
                                onChange={ (e, {name,value}) => this.handleChange(e, {name,value})}
                            />
                            <br/><br/>
                            <Button type='submit' color='blue'>Create</Button>

                        </Form>
                        </div>

                    </Grid.Column>
                </Grid>   
            </Segment> 
           
        );
    }
}

export default CreateRecipeForm;