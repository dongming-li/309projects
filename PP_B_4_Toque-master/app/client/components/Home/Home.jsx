import React, {Component} from 'react';
import List from './RecipeList.jsx';


import { Grid, Segment, Button, Item, Icon } from 'semantic-ui-react';

/**
 * Home component used for the main view screen.
 * @extends Component
 */
class Home extends React.Component {
    
    constructor(props){
        super(props);
    }

    componentDidMount(){
        this.props.getRecipes();
    }


    render() {
        return (
            <div >  
            <Segment basic>
                <Grid textAlign='center' verticalAlign='middle'>
                    <Grid.Column style={{ height:'100vh', maxWidth: 550 }}>
                        <Item.Group>
                            <Item href='#/createrecipe'>Create Recipe</Item>
                        </Item.Group>

                        <List allRecipes={this.props.recipes}/>
                            <Button circular color='blue' onClick={this.props.getRecipes}>
                                <Button.Content visible> <Icon  name='refresh' /> </Button.Content>
                            </Button>

                    </Grid.Column>
                </Grid>    
            </Segment> 
            </div>
        );
        
    }
}

export default Home;

