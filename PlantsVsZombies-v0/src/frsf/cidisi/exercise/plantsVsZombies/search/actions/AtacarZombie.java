package frsf.cidisi.exercise.plantsVsZombies.search.actions;

import frsf.cidisi.exercise.plants.search.PlantsAgentState;
import frsf.cidisi.exercise.plants.search.PlantsEnvironmentState;
import frsf.cidisi.exercise.plants.search.PlantsPerception;
import frsf.cidisi.faia.agent.search.SearchAction;
import frsf.cidisi.faia.agent.search.SearchBasedAgentState;
import frsf.cidisi.faia.state.AgentState;
import frsf.cidisi.faia.state.EnvironmentState;

public class AtacarZombie extends SearchAction {

    @Override
    public SearchBasedAgentState execute(SearchBasedAgentState s) {
        PlantsAgentState plantsState = (PlantsAgentState) s;

        int row = plantsState.getRowPosition();
        int col = plantsState.getColumnPosition();

        if ((plantsState.getmatriz()[row][col] == 1) &&
                (plantsState.getcantidadSol() > 30)) {

            // There is no more enemy if we fight against it
            plantsState.setmatrizPosition(row, col, PlantsPerception.EMPTY_PERCEPTION);
            
            return plantsState;
        }

        return null;
    }

    /**
     * See comments in the Eat class.
     */
    @Override
    public EnvironmentState execute(AgentState ast, EnvironmentState est) {

        PlantsEnvironmentState environmentState = (PlantsEnvironmentState) est;
        PlantsAgentState plantsState = ((PlantsAgentState) ast);

        int row = environmentState.getPosicionAgente()[0];
        int col = environmentState.getPosicionAgente()[1];

        if ((environmentState.getMatriz()[row][col] == 1) &&
                (environmentState.getAgentSoles() > 30)) {
            // Update the real world state
            environmentState.setMatriz(row, col, PlantsPerception.EMPTY_PERCEPTION);

            // Update the agent state
            plantsState.setmatrizPosition(row, col, PlantsPerception.EMPTY_PERCEPTION);
            
            return environmentState;
        }

        return null;
    }

    /**
     * See comments in the Eat class.
     */
    @Override
    public Double getCost() {
        return new Double(0);
    }

    /**
     * See comments in the Eat class.
     */
    @Override
    public String toString() {
        return "Fight";
    }
}
