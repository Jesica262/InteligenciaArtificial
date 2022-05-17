package frsf.cidisi.exercise.plantsVsZombies.search;

import domain.Grilla;
import frsf.cidisi.faia.agent.Agent;
import frsf.cidisi.faia.agent.Perception;
import frsf.cidisi.faia.environment.Environment;

public class PlansVsZombiesPerception extends Perception {

    public static int UNKNOWN_PERCEPTION = -1;   
	private int suciedad;

    public  PlansVsZombiesPerception() {
    	//Aquí crearimos los objetos, listas, arrays que componen la percepción
    	suciedad = UNKNOWN_PERCEPTION;
    }

    public PlansVsZombiesPerception(Agent agent, Environment environment) {
        super(agent, environment);
    }

    /**
     * This method initializa a perception of the agent.
     */
    @Override
    public void initPerception(Agent agentIn, Environment environmentIn) {
    	
        PlansVsZombies agent = (PlansVsZombies) agentIn;
        AmbientePlansVsZombies environment = (AmbientePlansVsZombies) environmentIn;
        EstadoAmbiente environmentState = environment.getEnvironmentState();
       
        //Aquí creamos la percepción inicial del agente
        //Pimero chequeamos que la habitación en la que está el agente está sucia
        Grilla h = environmentState.getposicionAspiradora();
        boolean estaSucia = environmentState.gethabitacionesSucias().contains(h);
        
        //Si lo está, el valor de la percepción será 1; en caso contrario será 0
        if(estaSucia)
        	suciedad = 1;
        else
        	suciedad = 0;
        
    }
    
    @Override
    public String toString() {
        String str = "";
        
        str += "La habitación está ";
        str += (suciedad == 1)?"sucia.":"limpia.";

        return str.toString();
    }

    // The following methods are agent-specific:	
     public int getsuciedad(){
        return suciedad;
     }
     
     public void setsuciedad(int arg){
        this.suciedad = arg;
     }
	
   
}
