package frsf.cidisi.exercise.plantsVsZombies.search;

import frsf.cidisi.faia.solver.search.IStepCostFunction;
import frsf.cidisi.faia.solver.search.NTree;

public class CostFunction implements IStepCostFunction {

    /**
     * This method calculates the cost of the given NTree node.
     */
    public double calculateCost(NTree node) {
        return ((PlantsAgentState) node.getAgentState()).getVisitedCellsCount();
    }
}
