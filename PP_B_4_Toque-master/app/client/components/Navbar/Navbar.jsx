import React, {Component} from 'react';
import { Input, Grid, Form, Message, Segment, Sidebar, Button, Menu, Icon, Image, Header, Container } from 'semantic-ui-react';
import {Link} from 'react-router-dom';

import {
    HashRouter as Router,
    Route,
    Switch
} from 'react-router-dom';

import Home from '../../containers/Home/index.js';
import Login from '../../containers/Login/index.jsx';
import Register from '../../containers/Register/index.jsx'

import MealPlanner from '../../containers/MealPlanner/index.jsx';
import CreateRecipe from '../../containers/CreateRecipeForm/index.js';

import GroceryLister from '../../containers/GroceryList/index.jsx';
import CreateGroceryList from '../GroceryLister/CreateGroceryList.jsx';

import Splash from '../Splash/index.jsx';

/**
 * Component use to navigate the site
 * @extends Component
 */
class Navbar extends Component {
    constructor(props) {
        super(props);
        this.state = { sidebarVisible:false };
    }

    toggleVisibility(){
        this.setState({ sidebarVisible: !this.state.sidebarVisible});
    }

    sideClick(name){
        this.toggleVisibility();
    }

    componentDidMount(){
        this.props.authenticate();
    }

    handleLogout() {
        this.toggleVisibility();
        this.props.logout();
    }


    render() {
        return (
            <div>
            <Menu secondary borderless attached="top">
                <Menu.Item onClick={() => this.toggleVisibility()}>
                    <Icon name="sidebar" />
                    Toque
                </Menu.Item>
                <Menu.Item>
                    <Input className='icon' icon='search' iconPosition='left' placeholder='Search' />
                </Menu.Item>
            </Menu>

            <Sidebar.Pushable as={Segment} attached="bottom">
                {
                    this.props.user ?
                        <Sidebar as={Menu} animation='uncover' width='thin' vertical inverted visible={this.state.sidebarVisible} icon='labeled'>
                            <Menu.Item name='home' href='#/home' onClick={() => this.toggleVisibility()}> <Icon name='home' /> Home </Menu.Item>
                            <Menu.Item name='meals' href='#/meal-planner' onClick={() => this.toggleVisibility()}> <Icon name='table' /> Meal Plan </Menu.Item>
                            <Menu.Item name='grocery' href='#/grocery-lists' onClick={() => this.toggleVisibility()}> <Icon name='shop' /> Grocery List </Menu.Item>
                            <Menu.Item name='sign-out' href="#/" onClick={this.handleLogout.bind(this)}> <Icon name='sign out' /> Sign-Out </Menu.Item>
                        </Sidebar>
                        :
                        <Sidebar as={Menu} animation='uncover' width='thin' vertical inverted visible={this.state.sidebarVisible} icon='labeled'>
                            <Menu.Item name='login'  href="#/login" onClick={() => this.toggleVisibility()}> <Icon name='sign in' />Log-in</Menu.Item>
                            <Menu.Item name='register' href="#/register" onClick={() => this.toggleVisibility()}> <Icon name='sign in' />Sign-Up</Menu.Item>
                        </Sidebar>

                }

                <Sidebar.Pusher>
                    <Container fluid style={{
                        height: '95vh'
                    }}>
                        <Router>
                            <Switch>
                                <Route exact path="/home" component={Home} />
                                <Route exact path="/" component={Splash} />
                                <Route path="/login" component={Login} />
                                <Route path="/register" component={Register} />
                                <Route path="/meal-planner" component={MealPlanner}/>
                                <Route path="/createrecipe" component={CreateRecipe} />
                                <Route exact path="/grocery-lists" component={GroceryLister} />
                                <Route exact path="/grocery-lists/:id" component={GroceryLister} />
                                <Route exact path="/create-grocery-list" component={GroceryLister} />
                            </Switch>
                        </Router>
                    </Container>
                </Sidebar.Pusher>
            </Sidebar.Pushable>
            </div>
        );
    }
}

export default Navbar;
