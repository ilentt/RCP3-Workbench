package ilentt.ilenlab.com.rcp.workbench;

import org.eclipse.jface.action.ICoolBarManager;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.ToolBarManager;
import org.eclipse.swt.SWT;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.actions.ActionFactory;
import org.eclipse.ui.actions.ActionFactory.IWorkbenchAction;
import org.eclipse.ui.application.ActionBarAdvisor;
import org.eclipse.ui.application.IActionBarConfigurer;

public class ApplicationActionBarAdvisor extends ActionBarAdvisor {
	
	private IWorkbenchAction saveAction;
	private IWorkbenchAction exitAction;
	private IWorkbenchAction aboutAction;
	
    public ApplicationActionBarAdvisor(IActionBarConfigurer configurer) {
        super(configurer);
    }

    protected void makeActions(IWorkbenchWindow window) {
    	saveAction = ActionFactory.SAVE.create(window);
    	this.register(saveAction);
    	
    	exitAction = ActionFactory.QUIT.create(window);
    	this.register(exitAction);
    	
    	aboutAction = ActionFactory.ABOUT.create(window);
    	this.register(aboutAction);
    	
    	super.makeActions(window);
    }

    protected void fillMenuBar(IMenuManager menuBar) {
    	// File
    	MenuManager filemenu = new MenuManager("&File", "file");
    	menuBar.add(filemenu);
    	filemenu.add(exitAction);
    	
    	// Help
    	MenuManager helpmenu = new MenuManager("&Help","help");
    	menuBar.add(helpmenu);
    	helpmenu.add(aboutAction);
    	
    	super.fillMenuBar(menuBar);
    }
    
    protected void fillCoolBar(ICoolBarManager coolBar) {
    	IToolBarManager toolbar1 = new ToolBarManager(SWT.FLAT | SWT.RIGHT);
    	toolbar1.add(saveAction);
    	coolBar.add(toolbar1);
    	
    	IToolBarManager toolbar2 = new ToolBarManager(SWT.FLAT | SWT.RIGHT);
    	toolbar2.add(exitAction);
    	coolBar.add(toolbar2);
    }
}
