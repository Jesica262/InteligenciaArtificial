package frsf.cidisi.exercise.plantsVsZombies.search;

import frsf.cidisi.faia.agent.search.GoalTest;
import frsf.cidisi.faia.state.AgentState;

public class PlantsGoal extends GoalTest {

    @Override
    public boolean isGoalState(AgentState agentState) {
        if (((PlantsAgentState) agentState).isNoMoreFood() &&
                ((PlantsAgentState) agentState).isAllmatrizKnown()) {
            return true;
        }
        return false;
    }
}
