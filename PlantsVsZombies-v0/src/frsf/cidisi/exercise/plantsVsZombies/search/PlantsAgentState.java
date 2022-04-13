package frsf.cidisi.exercise.plantsVsZombies.search;

import java.util.Random;

import frsf.cidisi.faia.agent.Perception;
import frsf.cidisi.faia.agent.search.SearchBasedAgentState;

public class PlantsAgentState extends SearchBasedAgentState {

    private int[][] matriz;
    private int[] posicionPlants;
    private int[] posicionZombie;
    private int[] posicionInicial;
    private int cantidadSol;
    private int cantidadSolZombie;
    private int celdasVisitadas;

    public PlantsAgentState(int[][] m, int row, int col, int s, int z) {
        matriz = m;
        posicionPlants = new int[] {row,col};
        posicionInicial = new int[2];
        posicionInicial[0] = row;
        posicionInicial[1] = col;
        cantidadSol = s;
        cantidadSolZombie = z;
        celdasVisitadas = 0;
    }

    public PlantsAgentState() {
        matriz = new int[5][9];
        posicionPlants = new int[2];
        cantidadSol = 1;
        cantidadSolZombie = 0;
        this.initState();
    }

    @Override
    public SearchBasedAgentState clone() {
        int[][] newmatriz = new int[5][9];

        for (int row = 0; row < matriz.length; row++) {
            for (int col = 0; col < matriz.length; col++) {
                newmatriz[row][col] = matriz[row][col];
            }
        }

        int[] newPosition = new int[2];
        newPosition[0] = posicionPlants[0];
        newPosition[1] = posicionPlants[1];

        PlantsAgentState newState = new PlantsAgentState(newmatriz,
                this.getRowPosition(), this.getColumnPosition(), this.cantidadSol, this.cantidadSolZombie);

        return newState;
    }

    @Override
    public void updateState(Perception p) {
        PlantsPerception plantsPerception = (PlantsPerception) p;

        int row = this.getRowPosition();
        int col = this.getColumnPosition();

        if (col == 0) {
            col = 3;
        } else {
            col = col - 1;
        }
        matriz[row][col] = plantsPerception.getSensorIzquierdo();

        row = this.getRowPosition();
        col = this.getColumnPosition();

        if (col == 8) {
            col = 0;
        } else {
            col = col + 1;
        }
        matriz[row][col] = plantsPerception.getSensorDerecho();

        row = this.getRowPosition();
        col = this.getColumnPosition();

        if (row == 0) {
            row = 8;
        } else {
            row = row - 1;
        }
        matriz[row][col] = plantsPerception.getSensorArriba();


        row = this.getRowPosition();
        col = this.getColumnPosition();

        if (row == 4) {
            row = 0;
        } else {
            row = row + 1;
        }
        matriz[row][col] = plantsPerception.getSensorAbajo();

        cantidadSol = plantsPerception.getSolPlants();
    }

    @Override
    public void initState() {
    	
    	Random random = new Random();
    	
        for (int row = 0; row < matriz.length; row++) {
            for (int col = 0; col < matriz.length; col++) {
                matriz[row][col] = PlantsPerception.UNKNOWN_PERCEPTION;
            }
        }
        
        this.setRowPosition(2);
        this.setColumnPosition(0);

        this.setcantidadSol(random.nextInt(25)+5);
        this.setCantidadSolZombie(random.nextInt(6)+1);
    }

    /**
     * This method returns the String representation of the agent state.
     */
    @Override
    public String toString() {
        String str = "";

        str = str + " position=\"(" + getRowPosition() + "," + "" + getColumnPosition() + ")\"";
        str = str + " cantidadSol=\"" + cantidadSol + "\"\n";

        str = str + "matriz=\"[ \n";
        for (int row = 0; row < matriz.length; row++) {
            str = str + "[ ";
            for (int col = 0; col < matriz.length; col++) {
                if (matriz[row][col] == -1) {
                    str = str + "* ";
                } else {
                    str = str + matriz[row][col] + " ";
                }
            }
            str = str + " ]\n";
        }
        str = str + " ]\"";

        return str;
    }

    /**
     * This method is used in the search process to verify if the node already
     * exists in the actual search.
     */
    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof PlantsAgentState))
            return false;

        int[][] matrizObj = ((PlantsAgentState) obj).getmatriz();
        int[] positionObj = ((PlantsAgentState) obj).getPosition();

        for (int row = 0; row < matriz.length; row++) {
            for (int col = 0; col < matriz.length; col++) {
                if (matriz[row][col] != matrizObj[row][col]) {
                    return false;
                }
            }
        }

        if (posicionPlants[0] != positionObj[0] || posicionPlants[1] != positionObj[1]) {
            return false;
        }
        
        return true;
    }

	public int[][] getmatriz() {
        return matriz;
    }

    public int getmatrizPosition(int row, int col) {
        return matriz[row][col];
    }

    public void setmatrizPosition(int row, int col, int value) {
        this.matriz[row][col] = value;
    }

    public int[] getPosition() {
        return posicionPlants;
    }

    public void setRowPosition(int value) {
        this.posicionPlants[0] = value;
    }

    public void setColumnPosition(int value) {
        this.posicionPlants[1] = value;
    }

    public int getRowPosition() {
        return posicionPlants[0];
    }

    public int getColumnPosition() {
        return posicionPlants[1];
    }

    public int getcantidadSol() {
        return cantidadSol;
    }

    private void setcantidadSol(int cantidadSol) {
        this.cantidadSol = cantidadSol;
    }
    
    public int getCantidadSolZombie() {
		return cantidadSolZombie;
	}

	public void setCantidadSolZombie(int cantidadSolZombie) {
		this.cantidadSolZombie = cantidadSolZombie;
	}

    public boolean isAllmatrizKnown() {
        for (int row = 0; row < matriz.length; row++) {
            for (int col = 0; col < matriz.length; col++) {
                if (matriz[row][col] == PlantsPerception.UNKNOWN_PERCEPTION) {
                    return false;
                }
            }
        }
        
        return true;
    }

    public int getUnknownCellsCount() {
        int result = 0;

        for (int row = 0; row < matriz.length; row++) {
            for (int col = 0; col < matriz.length; col++) {
                if (matriz[row][col] == PlantsPerception.UNKNOWN_PERCEPTION) {
                    result++;
                }
            }
        }

        return result;
    }

    public int getRemainingFoodCount() {
        int result = 0;

        for (int row = 0; row < matriz.length; row++) {
            for (int col = 0; col < matriz.length; col++) {
                if (matriz[row][col] == PlantsPerception.FOOD_PERCEPTION) {
                    result++;
                }
            }
        }
        
        return result;
    }

    public boolean isNoMoreFood() {
        for (int row = 0; row < matriz.length; row++) {
            for (int col = 0; col < matriz.length; col++) {
                if (matriz[row][col] == PlantsPerception.FOOD_PERCEPTION) {
                    return false;
                }
            }
        }
        return true;
    }

    public int getVisitedCellsCount() {
        return celdasVisitadas;
    }
}
