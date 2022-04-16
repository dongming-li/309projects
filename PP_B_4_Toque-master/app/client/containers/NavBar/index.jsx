import {connect} from 'react-redux';
import NavBarComponent from '../../components/Navbar/Navbar.jsx';
import {logout, authenticate} from "../../actions/auth";

const mapStateToProps = (state, ownProps) => {
    return {
        user: state.auth.user,
        children: ownProps.children,
    }
};

const mapDispatchToProps = dispatch => {
    return {
        logout: () => (dispatch(logout())),
        authenticate: () => (dispatch(authenticate()))
    }
};

const NavBar = connect(
    mapStateToProps,
    mapDispatchToProps
)(NavBarComponent);
/**
 * Container that injects the props into the NavBar component.
 */
export default NavBar;