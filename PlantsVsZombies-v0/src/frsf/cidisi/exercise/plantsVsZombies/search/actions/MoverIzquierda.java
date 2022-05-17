package frsf.cidisi.exercise.plantsVsZombies.search.actions;

import frsf.cidisi.exercise.plants.search.PlantsAgentState;
import frsf.cidisi.exercise.plants.search.PlantsEnvironmentState;
import frsf.cidisi.exercise.plants.search.PlantsPerception;
import frsf.cidisi.faia.agent.search.SearchAction;
import frsf.cidisi.faia.agent.search.SearchBasedAgentState;
import frsf.cidisi.faia.state.AgentState;
import frsf.cidisi.faia.state.EnvironmentState;

public class MoverIzquierda extends SearchAction {

    @Override
    public SearchBasedAgentState execute(SearchBasedAgentState s) {

        PlantsAgentState plantsState = (PlantsAgentState) s;

       // plantsState.increaseVisitedCellsCount();

        int row = plantsState.getRowPosition();
        int col = plantsState.getColumnPosition();

        if (row == 0) {
        	row = 8;
        } else {
        	row = row - 1;
        }

        plantsState.setRowPosition(row);

        /* The agent can only go left when the cell is not empty */
        if (plantsState.getmatrizPosition(row, col) != PlantsPerception.EMPTY_PERCEPTION) {

            plantsState.setmatrizPosition(row, col, PlantsPerception.EMPTY_PERCEPTION);

            return plantsState;
        }

        return null;
    }

    @Override
    public EnvironmentState execute(AgentState ast, EnvironmentState est) {

        PlantsEnvironmentState environmentState = (PlantsEnvironmentState) est;
        PlantsAgentState plantsState = ((PlantsAgentState) ast);

      //  plantsState.increaseVisitedCellsCount();

        int row = environmentState.getPosicionAgente()[0];
        int col = environmentState.getPosicionAgente()[1];

        if (row == 0) {
        	row = 8;
        } else {
        	row = row - 1;
        }

        plantsState.setRowPosition(row);

        environmentState.setPosicionAgente(new int[] {row, col});
        
        return environmentState;
    }

    @Override
    public Double getCost() {
        return new Double(0);
    }

    @Override
    public String toString() {
        return "GoLeft";
    }
}