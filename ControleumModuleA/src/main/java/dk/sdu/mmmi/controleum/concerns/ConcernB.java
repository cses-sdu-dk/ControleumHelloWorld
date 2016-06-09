package dk.sdu.mmmi.controleum.concerns;

import dk.sdu.mmmi.controleum.api.core.ControlDomain;
import dk.sdu.mmmi.controleum.api.moea.Solution;
import dk.sdu.mmmi.controleum.control.commons.AbstractConcern;
import dk.sdu.mmmi.controleum.output.DoubleOutput;
import static java.lang.Math.pow;
import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * f2 = (x-2)^2 + 20;
 *
 * @author jcs
 */
public class ConcernB extends AbstractConcern {

    public ConcernB(String name, ControlDomain controlDomain, int priority) {
        super(name, controlDomain, priority);
    }

    @Override
    public double evaluate(Solution option) {
        double x = option.getValue(DoubleOutput.class);

        BigDecimal d = new BigDecimal(pow(x - 2, 2) + 20);

        return d.setScale(1, RoundingMode.HALF_UP).doubleValue();
    }

}
