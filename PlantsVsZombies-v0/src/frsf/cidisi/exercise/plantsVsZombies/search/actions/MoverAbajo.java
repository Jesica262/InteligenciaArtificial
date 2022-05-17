package frsf.cidisi.exercise.plantsVsZombies.search.actions;

import frsf.cidisi.exercise.plants.search.PlantsAgentState;
import frsf.cidisi.exercise.plants.search.PlantsEnvironmentState;
import frsf.cidisi.exercise.plants.search.PlantsPerception;
import frsf.cidisi.faia.agent.search.SearchAction;
import frsf.cidisi.faia.agent.search.SearchBasedAgentState;
import frsf.cidisi.faia.state.AgentState;
import frsf.cidisi.faia.state.EnvironmentState;

public class MoverAbajo extends SearchAction {

    @Override
    public SearchBasedAgentState execute(SearchBasedAgentState s) {

        PlantsAgentState plantsState = (PlantsAgentState) s;

       // plantsState.increaseVisitedCellsCount(); incrementar para abajo

        int row = plantsState.getRowPosition();
        int col = plantsState.getColumnPosition();

        // Check the limits of the world
        if (col == 0) {
            col = 4;
        } else {
            col = col - 1;
        }

        plantsState.setColumnPosition(col);

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

//        plantsState.increaseVisitedCellsCount();

        int row = environmentState.getPosicionAgente()[0];
        int col = environmentState.getPosicionAgente()[1];

        if (col == 0) {
        	col = 4;
        } else {
        	col = col - 1;
        }

        plantsState.setColumnPosition(col);

        environmentState.setPosicionAgente(new int[] {row, col});
        
        return environmentState;
    }

	@Override
	public Double getCost() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return null;
	}
}
