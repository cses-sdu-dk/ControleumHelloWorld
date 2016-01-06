package dk.sdu.mmmi.controleum;

import com.decouplink.Disposable;
import com.decouplink.DisposableList;
import static com.decouplink.Utilities.context;
import dk.sdu.mmmi.controleum.api.core.ControlDomain;
import dk.sdu.mmmi.controleum.api.core.ControleumPlugin;
import dk.sdu.mmmi.controleum.api.moea.Concern;
import dk.sdu.mmmi.controleum.api.moea.Output;
import dk.sdu.mmmi.controleum.concerns.ConcernA;
import dk.sdu.mmmi.controleum.concerns.ConcernB;
import dk.sdu.mmmi.controleum.output.DoubleOutput;
import org.openide.util.lookup.ServiceProvider;

/**
 *
 * @author jcs
 */
@ServiceProvider(service = ControleumPlugin.class)
public class Plugin implements ControleumPlugin {

    private final DisposableList disposables = new DisposableList();

    @Override
    public Disposable create(ControlDomain domain) {

        // Problem
        ConcernA a = new ConcernA("A", domain, Concern.HIGH_PRIORITY);
        ConcernB b = new ConcernB("B", domain, Concern.HIGH_PRIORITY);
        DoubleOutput x = new DoubleOutput("x", domain);

        a.setEnabled(true);
        b.setEnabled(true);

        // Add to context
        disposables.add(context(domain).add(Concern.class, a));
        disposables.add(context(domain).add(Concern.class, b));
        disposables.add(context(domain).add(Output.class, x));

        return disposables;
    }
}
