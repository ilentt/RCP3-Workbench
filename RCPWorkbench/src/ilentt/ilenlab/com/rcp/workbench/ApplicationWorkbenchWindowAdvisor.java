package ilentt.ilenlab.com.rcp.workbench;

import ilentt.ilenlab.com.rcp.editor.DeptEditor;
import ilentt.ilenlab.com.rcp.view.DeptListView;
import ilentt.ilenlab.com.rcp.view.DeptView;

import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IPartListener2;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchPartReference;
import org.eclipse.ui.PlatformUI;
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
        configurer.setShowMenuBar(true);
        
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
    
    public void postWindowOpen() {
    	Shell shell = getWindowConfigurer().getWindow().getShell();
    	shell.setMaximized(true);
    	
    	// đăng ký khi có sự kiện liên quan đến các thành phần của trang
    	// view, editor,...
    	PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage()
    		.addPartListener(new IPartListener2() {
			
			public void partVisible(IWorkbenchPartReference partRef) {
				// TODO Auto-generated method stub
				
			}
			
			// khi một view hay một editor được mở
			public void partOpened(IWorkbenchPartReference partRef) {
				// TODO Auto-generated method stub
				System.out.println("Part OPENED: " + partRef.getPartName());
				IWorkbenchPart part = partRef.getPart(false);
				if(part instanceof DeptListView) {
					DeptListView deptListView = (DeptListView) part;
					DeptEditor deptEditor = (DeptEditor) partRef.getPage()
							.findView(DeptEditor.ID);
				
					// DeptListView lắng nghe các thay đổi thuộc tính từ DeptEditor
					if(deptEditor != null) 
						deptEditor.addPropertyListener(deptListView);
				} else if(part instanceof DeptView) {
					DeptView deptView = (DeptView) part;
					DeptEditor deptEditor = (DeptEditor) partRef.getPage().
							findView(DeptEditor.ID);
					// DeptView lắng nghe các thay đổi thuộc tính từ DeptEditor
					if(deptView != null)
						deptEditor.addPropertyListener(deptView);
				} else if (part instanceof DeptEditor) {	
					DeptEditor deptEditor = (DeptEditor) part;
					DeptView deptView = (DeptView) partRef.getPage()
							.findView(DeptView.ID);
					
					// DeptView lắng nghe các thay đổi thuộc tính từ DeptEditor
					if(deptView != null)
						deptEditor.addPropertyListener(deptView);
					
					DeptListView deptListView = (DeptListView) partRef.getPage()
							.findView(DeptListView.ID);
					
					// DeptListView lắng nghe các thay đổi từ DeptEditor
					if(deptListView != null)
						deptEditor.addPropertyListener(deptListView);
				}
				
			}
			
			public void partInputChanged(IWorkbenchPartReference partRef) {
				// TODO Auto-generated method stub
				
			}
			
			public void partHidden(IWorkbenchPartReference partRef) {
				// TODO Auto-generated method stub
				
			}
			
			public void partDeactivated(IWorkbenchPartReference partRef) {
				// TODO Auto-generated method stub
				
			}
			
			public void partClosed(IWorkbenchPartReference partRef) {
				// TODO Auto-generated method stub
				
			}
			
			public void partBroughtToTop(IWorkbenchPartReference partRef) {
				// TODO Auto-generated method stub
				
			}
			
			public void partActivated(IWorkbenchPartReference partRef) {
				// TODO Auto-generated method stub
				
			}
		});
    	
    }
}
