import React, {Component, contextTypes} from 'react';
import { Input, Grid, Form, Message, Segment, Sidebar, Button, Menu, Icon, Image, Header } from 'semantic-ui-react';
import { connect } from 'react-redux';
import { authenticate, register } from '../../actions/auth/index'
/**
 * Component used to handle registration
 * @extends Component
 */
class RegForm extends Component {
    constructor(props) {
        super(props);

        this.handleChange = this.handleChange.bind(this);
        this.handleSubmit = this.handleSubmit.bind(this);

        this.state = {
            username: this.props.username,
            email: this.props.email,
            password: this.props.password,
            confirmPassword: this.props.confirmPassword,
        };


    }

    handleChange(e, { name, value } ) {
        this.setState({
            [name]: value
        });
    }

    handleSubmit(e) {
        const {username, email, password, confirmPassword} = this.state;

        console.log(username + ' ' + email + ' ' + password + ' ' + confirmPassword);

        if(password === confirmPassword) {
            this.props.register(username, email, password).then((response) =>{

      console.log('test' + 'RegForm this.props.isRegistered:' + this.props.isRegistered);
              if(this.props.isRegistered == true){

                  window.location.href = '#/Login';
              }
              else{
                console.log('RegForm: register failed');
                  console.log('RegForm this.props.isRegistered:' + this.props.isRegistered);

              }
           });
        }
        else {
            alert('passwords do not match');
        }


        //Redux call
        return({username, email, password, confirmPassword});
    }

    render() {
      return(
          <div style={{height:'100vh'}}>
            <Segment basic>
              <Grid textAlign='center' verticalAlign='middle'>
                <Grid.Column style={{ height: '100vh', maxWidth: 450 }}>
                  <Header as='h1' color='blue' textAlign='center'>
                        {' '}Register
                     </Header>

                        <Form size='large' onSubmit={this.handleSubmit}>
                            <Segment stacked color='blue'>
                                <Form.Input fluid icon='user'
                                            iconPosition='left'
                                            placeholder='User Name'
                                            name='username'
                                            value={this.state.username}
                                            onChange={(e,{name, value}) => this.handleChange(e,{name, value})}/>
                                <Form.Input fluid icon='user'
                                            iconPosition='left'
                                            placeholder='E-mail'
                                            name='email'
                                            value={this.state.email}
                                            onChange={(e,{name, value}) => this.handleChange(e,{name, value})}/>

                                <Form.Input fluid icon='lock'
                                            iconPosition='left'
                                            placeholder='Password'
                                            type='password'
                                            name='password'
                                            value={this.state.password}
                                            onChange={(e,{name, value}) => this.handleChange(e,{name, value})}/>
                                  <Form.Input fluid icon='lock'
                                              iconPosition='left'
                                              placeholder='Confirm Password'
                                              type='password'
                                              name='confirmPassword'
                                              value={this.state.confirmPassword}
                                              onChange={(e,{name, value}) => this.handleChange(e,{name, value})}/>
                                <Button type='submit' color='blue' fluid size='large'>Register</Button>
                            </Segment>
                        </Form>

                        <Message>
                            Already Registered? <a href='#/Login'>Login Here</a>
                        </Message>
                  </Grid.Column>
              </Grid>
          </Segment>
          </div>
        );

    }
}

export default RegForm;
