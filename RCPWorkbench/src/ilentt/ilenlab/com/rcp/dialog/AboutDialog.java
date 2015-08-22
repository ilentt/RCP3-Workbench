package ilentt.ilenlab.com.rcp.dialog;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;

public class AboutDialog extends Dialog{
	protected Object result;
	protected Shell shellAbout;
	
	/*
	 * Create dialog
	 * @param: parent
	 * @param: style
	 */
	
	public AboutDialog(Shell parent, int style) {
		super(parent, style);
		setText("SWT Dialog");
	}
	
	/*
	 * open dialog
	 * @return the result
	 */
	public Object open() {
		createContents();
		shellAbout.open();
		shellAbout.layout();
		Display display = getParent().getDisplay();
		while(!shellAbout.isDisposed()) {
			if(!display.readAndDispatch()) {
				display.sleep();
			}
		}
		return result;
	}
	
	/*
	 * Create content of the dialog 
	 */
	public void createContents() {
		shellAbout = new Shell(getParent(), getStyle());
		shellAbout.setSize(418,145);
		shellAbout.setText("About");
		shellAbout.setLayout(new GridLayout(1, false));
		
		Label lblNewLabel = new Label(shellAbout, SWT.NONE);
		lblNewLabel.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER,true,true,1,1));
		lblNewLabel.setText("RCP Application");
	}
}
