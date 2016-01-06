package dk.sdu.mmmi.controleum.output;

import dk.sdu.mmmi.controleum.api.core.ControlDomain;
import static dk.sdu.mmmi.controleum.api.moea.utils.BitSetUtil.bitSetToDouble;
import static dk.sdu.mmmi.controleum.api.moea.utils.BitSetUtil.doubleToBitSet;
import dk.sdu.mmmi.controleum.control.commons.AbstractOutput;
import static dk.sdu.mmmi.controleum.control.commons.AbstractOutput.R;
import java.util.BitSet;
import java.util.Date;

/**
 * 
 * @author jcs
 */
public class DoubleOutput extends AbstractOutput<Integer>{
    
    private final static int DELTA = 1;
    private static final int MAX_VALUE = 10;
    private static final int MIN_VALUE = -10;
    private final int bitSize = 32;

    
    public DoubleOutput(String name, ControlDomain g) {
        super(name, g);
    }

    @Override
    public Integer getRandomValue(Date t) {        
        return R.nextInt(11); // [-10;10]
    }

    @Override
    public Integer getMutatedValue(Integer v) {
        Integer m;

        boolean positive = R.nextBoolean();
        // [-1;1]
        if (positive) {
            m = v + DELTA;
        } else {
            m = v - DELTA;
        }

        // Border cases
        if (m > MAX_VALUE) {
            m = MAX_VALUE;
        } else if (m < MIN_VALUE) {
            m = MIN_VALUE;
        }

        return m;      
    }

    @Override
    public void doCommitValue(Date t, Integer result) { 
        
        // TODO: Extension point for writing out the selected solution output value after each epoch.
    }

    @Override
    public int bitSize() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public BitSet asBitSet() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setBitSet(BitSet bitset) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
