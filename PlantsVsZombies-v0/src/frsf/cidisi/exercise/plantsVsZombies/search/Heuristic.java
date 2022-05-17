package frsf.cidisi.exercise.plantsVsZombies.search;

import frsf.cidisi.faia.solver.search.IEstimatedCostFunction;
import frsf.cidisi.faia.solver.search.NTree;

public class Heuristic implements IEstimatedCostFunction {

    /**
     * It returns the estimated cost to reach the goal from a NTree node.
     */
    public double getEstimatedCost(NTree node) {
        PlantsAgentState plantsState = (PlantsAgentState) node.getAgentState();

        return (plantsState.getUnknownCellsCount() +
                plantsState.getRemainingFoodCount());
    }
}
