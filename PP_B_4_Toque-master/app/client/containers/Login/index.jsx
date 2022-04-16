import {connect} from 'react-redux';
import LoginComponent from '../../components/Login/LogForm.jsx';
import {login} from "../../actions/auth/index";

const mapStateToProps = state => {

    return {
        isLoggedIn: state.auth.isLoggedIn,
        isLoggingIn: state.auth.isLoggingIn,
        user: state.auth.user
    }
};

const mapDispatchToProps = dispatch => {
    return {
        login: (username, password) => dispatch(login(username, password)),
    }
};

const Login = connect(
    mapStateToProps,
    mapDispatchToProps
)(LoginComponent);

/**
 * Container that injects props into LoginComponent
 */
export default Login;
