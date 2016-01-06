package dk.sdu.mmmi.controleumtest;

import static com.decouplink.Utilities.context;
import dk.sdu.mmmi.controleum.api.core.ControlDomain;
import dk.sdu.mmmi.controleum.api.core.ControlDomainManager;
import dk.sdu.mmmi.controleum.api.moea.SearchConfiguration;
import dk.sdu.mmmi.controleum.api.moea.SearchStatistics;
import dk.sdu.mmmi.controleum.api.moea.Solver;
import java.util.Date;
import java.util.logging.Level;
import junit.framework.Test;
import org.netbeans.junit.NbModuleSuite;
import org.netbeans.junit.NbTestCase;
import org.openide.util.Lookup;

public class ApplicationTest extends NbTestCase {

    public static Test suite() {
        return NbModuleSuite.createConfiguration(ApplicationTest.class).
                gui(false).
                failOnMessage(Level.WARNING). // works at least in RELEASE71
                failOnException(Level.INFO).
                enableClasspathModules(false).
                clusters(".*").
                suite(); // RELEASE71+, else use NbModuleSuite.create(NbModuleSuite.createConfiguration(...))
    }

    public ApplicationTest(String n) {
        super(n);
    }

    public void testApplication() {
        
        // Get DomainManager
        ControlDomainManager cm = Lookup.getDefault().lookup(ControlDomainManager.class);
        
        // Get context of domain
        ControlDomain domain = cm.getControlDomain("Default Context");
        Solver s = context(domain).one(Solver.class); 
        
        // Configure search mechanism
        SearchConfiguration cfg = new SearchConfiguration.Builder().copy(s.getSearchCfg()).debuggingEnabled(true).build();        
        s.setSearchConfiguration(cfg);
        
        // Solve problem
        SearchStatistics r = s.solve(new Date()); 
        
        // Clean up
        cm.deleteControlDomain(domain);
    }

}
