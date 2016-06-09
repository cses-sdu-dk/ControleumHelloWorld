package dk.sdu.mmmi.controleum.output;

import dk.sdu.mmmi.controleum.api.core.ControlDomain;
import dk.sdu.mmmi.controleum.control.commons.AbstractOutput;
import static dk.sdu.mmmi.controleum.control.commons.AbstractOutput.R;
import java.math.BigDecimal;
import static java.math.BigDecimal.*;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.BitSet;
import java.util.Date;

/**
 * 
 * @author jcs
 */
public class DoubleOutput extends AbstractOutput<Double> {
    
    private final static BigDecimal DELTA = valueOf(1.0);
    private static final BigDecimal MAX_VALUE = valueOf(10);
    private static final BigDecimal MIN_VALUE = valueOf(-10);

    
    public DoubleOutput(String name, ControlDomain g) {
        super(name, g);
    }

    @Override
    public Double getRandomValue(Date t) {
        double r = R.nextDouble() * (R.nextBoolean() ? 10.0 : -10.0);
        BigDecimal bd = new BigDecimal(r);

        return bd.setScale(1, RoundingMode.HALF_UP).doubleValue(); // [-10;10]
    }

    @Override
    public Double getMutatedValue(Double v) {
        BigDecimal m = new BigDecimal(BigInteger.ZERO);
        BigDecimal val = new BigDecimal(v);

        boolean positive = R.nextBoolean();
        // [-1;1]
        if (positive) {
            m = val.add(DELTA);
        } else {
            m = val.subtract(DELTA);
        }

        // Border cases
        if (m.doubleValue() > MAX_VALUE.doubleValue()) {
            m = MAX_VALUE;
        } else if (m.doubleValue() < MIN_VALUE.doubleValue()) {
            m = MIN_VALUE;
        }

        return m.setScale(1, RoundingMode.HALF_UP).doubleValue();
    }

    @Override
    public void doCommitValue(Date t, Double result) {
        
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
