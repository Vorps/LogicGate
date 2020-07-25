package me.logic.info;

/**
 * Liste de l'ensemble des portes logiques disponible
 * Gestion des instances de ces portes
 */
public enum GateEnum {
    NOT(new Gate("NOT",1, 1,1,(Boolean[] x) ->  !x[0], new String[] {"I", "O"})),
    AND(new Gate("AND",1,2,1, (Boolean[] x) ->  x[0] & x[1], new String[] {"I1", "I2", "O"})),
    NAND(new Gate("NAND",1,2,1, (Boolean[] x) ->  !(x[0] & x[1]), new String[] {"I1", "I2", "O"})),
    NOR(new Gate("NOR",1,2,1, (Boolean[] x) ->  !(x[0] | x[1]), new String[] {"I1", "I2", "O"})),
    OR(new Gate("OR",1,2,1, (Boolean[] x) ->  (x[0] | x[1]), new String[] {"I1", "I2", "O"})),
    RS_FLIPFLOP_NAND(new Gate("RS_FLIPFLOP_NAND",2,2,  (wiresInput, wiresOutput) -> {
    	NAND.invoke("NAND_1", new Wire[] {wiresInput[0], wiresOutput[1]}, new Wire[] {wiresOutput[0]});
    	NAND.invoke("NAND_2", new Wire[] {wiresOutput[0], wiresInput[1]}, new Wire[] {wiresOutput[1]});
    }, new String[] {"R", "S", "Q", "/Q"})),
    RS_FLIPFLOP_NOR(new Gate("RS_FLIPFLOP_NOR", 2,2,  (wiresInput, wiresOutput) -> {
    	NOR.invoke("NOR_1", new Wire[] {wiresInput[0], wiresOutput[1]}, new Wire[] {wiresOutput[0]});
    	NOR.invoke("NOR_1", new Wire[] {wiresOutput[0], wiresInput[1]}, new Wire[] {wiresOutput[1]});
    }, new String[] {"R", "S", "Q", "/Q"})),
    DFLIPFLOP(new Gate("DFLIPFLOP", 2,2,  (wiresInput, wiresOutput) -> {
        Gate And = AND.invoke("AND_1");
        Gate RS1 =  RS_FLIPFLOP_NAND.invoke("RS_FLIPFLOP_NAND_1");
        Gate RS2 =  RS_FLIPFLOP_NAND.invoke("RS_FLIPFLOP_NAND_2");
        Gate RS3 =  RS_FLIPFLOP_NAND.invoke("RS_FLIPFLOP_NAND_3");
        And.setWires(new Wire[] {RS1.getSignals().wiresOutput[1], wiresInput[0]}, new Wire[] {});
        RS1.setWires(new Wire[] {RS3.getSignals().wiresOutput[1], wiresInput[0]}, new Wire[] {});
        RS2.setWires(new Wire[] {RS1.getSignals().wiresOutput[1], RS3.getSignals().wiresOutput[0]}, wiresOutput);
        RS3.setWires(new Wire[] {And.getSignals().wiresOutput[0], wiresInput[1]}, new Wire[] {});
    }, new String[] {"H", "D", "Q", "Qb"})),
    COUNTER4(new Gate("COUNTER4", 1, 4,  (wiresInput, wiresOutput) -> {
        Gate DF1 = DFLIPFLOP.invoke("DFLIPFLOP_1");
        Gate DF2 =  DFLIPFLOP.invoke("DFLIPFLOP_2");
        Gate DF3 =  DFLIPFLOP.invoke("DFLIPFLOP_3");
        Gate DF4 =  DFLIPFLOP.invoke("DFLIPFLOP_4");
        DF1.setWires(new Wire[] {wiresInput[0], DF1.getSignals().wiresOutput[1]}, new Wire[] {wiresOutput[0]});
        DF2.setWires(new Wire[] {DF1.getSignals().wiresOutput[1], DF2.getSignals().wiresOutput[1]}, new Wire[] {wiresOutput[1]});
        DF3.setWires(new Wire[] {DF2.getSignals().wiresOutput[1], DF3.getSignals().wiresOutput[1]}, new Wire[] {wiresOutput[2]});
        DF4.setWires(new Wire[] {DF3.getSignals().wiresOutput[1], DF4.getSignals().wiresOutput[1]}, new Wire[] {wiresOutput[3]});

    }, new String[] {"I", "bit 0", "bit 1", "bit 2", "bit 3"}));

    private Gate gate;

    GateEnum(Gate gate){
        this.gate = gate;
    }

    public Gate invoke(String name){
        return this.gate.clone(name);
    }

    public Gate invoke(){
        return this.gate.clone(this.gate.getName());
    }

    public Gate invoke(String name, Wire[] wiresInput, Wire[] wiresOutput){
        return this.gate.clone(name).setWires(new Wires(wiresInput, wiresOutput));
    }
    
    public Gate invoke(String name, Wire[] wiresInput){
        return this.gate.clone(name).setWires(wiresInput, new Wire[] {});
    }


}
