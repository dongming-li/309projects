import React, {Component} from 'react';
import { Button } from 'semantic-ui-react'
import './splash.css';

/**
 * Static index page Component used for display purposes.
 * @extends Component
 */
class Splash extends Component{
    constructor(props){
        super(props);

    }

    render() {
        return(
            <div className="splash">
                <div className="splash-section">
                    <img className="splash-image" src="assets/home.jpg"/>
                    <div className="image-text">
                        <div className="text-title">
                            Toque
                        </div>
                        <div className="text-subtitle">
                            Your Meals
                        </div>
                        <div className="text-subtitle">
                            Your Way
                        </div>
                    </div>

                </div>
                <div className="splash-section flex">
                    <div style={{'fontSize': '75px'}} >Infinite Possibilities</div>
                    <hr/>
                    <img style={{'marginTop': '50px'}} width='50%' height='50%' src="assets/recipe-collage.jpg"/>
                    <div style={{'fontSize': '30px', 'marginTop': '50px'}} >
                        Choose from our catalog of over 1 Billion recipes.
                    </div>
                </div>
                <div className="splash-section">
                    <img className="splash-image" src="assets/meal-prep.jpg"/>
                    <div className="image-text">
                        <div className="text-subtitle">
                            Join for free!
                        </div>
                        <div className="text-subtitle">
                            <Button.Group size="massive">
                                <Button primary href="#/register">Sign-Up</Button>
                                <Button.Or />
                                <Button positive href="#/login">Log-in</Button>
                            </Button.Group>
                        </div>
                    </div>
                </div>
            </div>
        )
    }
}


export default Splash;