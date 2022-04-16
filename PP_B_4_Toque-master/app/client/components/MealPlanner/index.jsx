import React, {Component} from 'react';
import 'react-big-calendar/lib/css/react-big-calendar.css';
import BigCalendar from 'react-big-calendar';
import moment from 'moment';
import MealPlanModal from '../MealPlanModal/index.jsx';

// Setup the localizer by providing the moment (or globalize) Object
// to the correct localizer.
BigCalendar.momentLocalizer(moment);

/**
 * Component used to view all meal plans and to bring up the MealPlanModal
 * @extends Component
 */
class MealPlanner extends Component{
    constructor(props){
        super(props);
        this.state = {
            isModalOpen: false,
            modalType: 'Create',
            mealPlan: {
                date: new Date(),
            },
        }
    }

    /**
     * Loads MealPlans on page load.
     */
    componentDidMount(){
        this.props.getMealPlans();
    }

    /**
     * Opens the MealPlan create Modal.
     * @param slotInfo
     */
    openCreateModal(slotInfo) {
        this.setState(prevState => ({
            isModalOpen: true,
            modalType: 'Create',
            mealPlan: {
                date: slotInfo.start,
            }
        }));
    }

    /**
     * Opens the Meal Plan edit Modal.
     * @param eventInfo
     */
    openEditModal(eventInfo) {
        console.log(eventInfo);
        this.setState(prevState => ({
            ...prevState,
            isModalOpen: true,
            modalType: 'Edit',
            mealPlan : {
                date: eventInfo.start,
                id: eventInfo.id,
                recipe: eventInfo.recipe
            }
        }));
    }

    /**
     * Closes the Meal Plan Modal.
     */
    closeModal() {
        this.setState(prevState => ({
            ...prevState,
            isModalOpen: false
        }));
    }

    onDataChange() {
        this.props.getMealPlans();
    }

    render() {
        return(
        <div style={{height:'100vh'}}>
            <MealPlanModal
                type={this.state.modalType}
                onDelete={this.props.onDeleteMealPlan}
                onCreate={this.props.onCreateMealPlan}
                onEdit={this.props.onEditMealPlan}
                onSubmit={this.onDataChange.bind(this)}
                isLoading={this.props.isLoading}
                isOpen={this.state.isModalOpen}
                closeModal={this.closeModal.bind(this)}
                mealPlan={this.state.mealPlan}
                onSearchRecipes={this.props.onSearchRecipes}
                isRecipesLoading={this.props.isRecipesLoading}
                recipes={this.props.recipes}
            />
            <BigCalendar
                selectable
                views={{month: true}}
                events={this.props.mealPlans}
                onSelectSlot={this.openCreateModal.bind(this)}
                onSelectEvent={this.openEditModal.bind(this)}
            />
        </div>)
    }
}


export default MealPlanner;
