package ilentt.ilenlab.com.rcp.workbench;

import org.eclipse.swt.graphics.Point;
import org.eclipse.ui.application.ActionBarAdvisor;
import org.eclipse.ui.application.IActionBarConfigurer;
import org.eclipse.ui.application.IWorkbenchWindowConfigurer;
import org.eclipse.ui.application.WorkbenchWindowAdvisor;

public class ApplicationWorkbenchWindowAdvisor extends WorkbenchWindowAdvisor {

    public ApplicationWorkbenchWindowAdvisor(IWorkbenchWindowConfigurer configurer) {
        super(configurer);
    }

    public ActionBarAdvisor createActionBarAdvisor(IActionBarConfigurer configurer) {
        return new ApplicationActionBarAdvisor(configurer);
    }
    
    public void preWindowOpen() {
        IWorkbenchWindowConfigurer configurer = getWindowConfigurer();
        configurer.setInitialSize(new Point(400, 300));
        
        // show menubar 
        configurer.setShowCoolBar(true);
        
        // show coolbar
        configurer.setShowCoolBar(true);
        
        // show status line
        configurer.setShowStatusLine(true);
        
        // show persentive bar
        configurer.setShowPerspectiveBar(true);
        
        // show fast view bar
        configurer.setShowFastViewBars(true);
        
        // show process indicator
        configurer.setShowProgressIndicator(true);
        
        configurer.setTitle("RCP Application"); //$NON-NLS-1$
    }
}
