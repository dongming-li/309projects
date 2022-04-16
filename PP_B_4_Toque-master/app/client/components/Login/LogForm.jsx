import React, {Component} from 'react';
import { Input, Grid, Form, Message, Segment, Sidebar, Button, Menu, Icon, Image, Header } from 'semantic-ui-react';
import { connect } from 'react-redux';
/**
 * Component used to log users in
 * @extends Component
 */
class LogForm extends Component {
    constructor(props) {
        super(props);
        this.state = {
            email: '',
            password: '',
        };
    }

    handleChange(e, { name, value } ) {
        this.setState({
            [name]: value
        });
    }

    handleSubmit(e) {
        const {email, password} = this.state;
        console.log(email + ' ' + password);

        this.props.login(email, password).then((response) => {
            if(this.props.isLoggedIn == true){
                window.location.href = '#/home';
            }
            else{
              console.log('LogForm: Login Failure');
            }
        });

        return({email, password});
    }

    render() {
      return(
        <div>
        <Segment basic>
            <Grid textAlign='center' verticalAlign='middle'>
                <Grid.Column style={{ height:'100vh', maxWidth: 450 }}>
                    <Header as='h1' color='blue' textAlign='center'>
                        {' '}Login
                    </Header>
                        <Form size='large' onSubmit={this.handleSubmit.bind(this)}>
                            <Segment stacked color='blue'>
                                <Form.Input fluid icon='user'
                                            iconPosition='left'
                                            placeholder='E-mail'
                                            name='email'
                                            value={this.state.email}
                                            onChange={(e,{name, value}) => this.handleChange(e,{name, value})}/>

                                <Form.Input fluid icon='lock'
                                            iconPosition='left'
                                            placeholder='Password'
                                            type='password' name='password'
                                            value={this.state.password}
                                            onChange={(e,{name, value}) => this.handleChange(e,{name, value})}/>

                                <Button type='submit' color='blue' fluid size='large'>Login</Button>
                            </Segment>
                        </Form>
                        <Message>
                            New User? <a href='#/register'>Sign Up</a>
                        </Message>
                    </Grid.Column>
                </Grid>
            </Segment>
            </div>
        );
    }
}

export default LogForm;
