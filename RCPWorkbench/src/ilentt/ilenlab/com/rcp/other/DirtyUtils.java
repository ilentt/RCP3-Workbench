package ilentt.ilenlab.com.rcp.other;

import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.events.VerifyEvent;
import org.eclipse.swt.events.VerifyListener;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Control;


public class DirtyUtils {
	public static void registryDirty(DirtyListener listener, Control... controls) {
		if(controls == null)
			return;
		for(Control control : controls) {
			if(control instanceof Text) {
				Text text = (Text) control;
				text.addVerifyListener(new VerifyListenerImpl(listener));
			}
			
			// checkbox or radio button
			else if(control instanceof Button) {
				Button button = (Button) control;
				button.addSelectionListener(new SectionListenerImpl(listener));
			}
			
			// not support
			else{
				throw new UnsupportedOperationException("Not support for " +
						control.getClass().getSimpleName());
			}
				
		}
	}
	
	static class VerifyListenerImpl implements VerifyListener {
		private DirtyListener listener;
		
		public VerifyListenerImpl(DirtyListener listener) {
			this.listener = listener;
		}
		
		public void verifyText(VerifyEvent arg0) {
			listener.fireDirty();
		}
	}
	
	// for button radio, check box
	static class SectionListenerImpl implements SelectionListener {
		 protected DirtyListener listener;
		 
		 public SectionListenerImpl(DirtyListener listener) {
			 this.listener = listener;
		 }

		public void widgetSelected(SelectionEvent e) {
			// TODO Auto-generated method stub
			
		}

		public void widgetDefaultSelected(SelectionEvent e) {
			// TODO Auto-generated method stub
			
		}
	}
}
