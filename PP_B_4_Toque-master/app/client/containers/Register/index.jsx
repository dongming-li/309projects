import {connect} from 'react-redux';
import RegisterComponent from '../../components/Register/RegForm.jsx';
import {register} from "../../actions/auth/index";

const mapStateToProps = state => {

    return {
        isRegistered: state.auth.isRegistered,
        isRegistering: state.auth.isRegistering,
        user: state.auth.user
    }
};

const mapDispatchToProps = dispatch => {
    return {
        register: (username, email, password) => dispatch(register(username, email, password)),
    }
};

const Register = connect(
    mapStateToProps,
    mapDispatchToProps
)(RegisterComponent);
/**
 * Container that injects props into the Register Component
 */
export default Register;
