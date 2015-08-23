package ilentt.ilenlab.com.rcp.editor;

import ilentt.ilenlab.com.rcp.constant.EditorConstants;
import ilentt.ilenlab.com.rcp.other.DirtyListener;
import ilentt.ilenlab.com.rcp.other.DirtyUtils;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.part.EditorPart;

public abstract class AbstractBaseEditor extends EditorPart {
	private boolean dirty;
	
	// Được gọi trước create part control
	public void init(IEditorSite site, IEditorInput input) throws PartInitException {
		// very important!!!
		setSite(site);
		setInput(input);
	}
	
	/*
	 *  Khi thuộc tính PROP_DIRTY thay đổi, WorkBench sẽ gọi method này
	 *  WorkBench kiểm tra editor có thay đổi hay không
	 *  nếu mothod trả về true, nút Save sáng, ngược lại tối.
	 */
	public boolean isDirty() {
		return dirty;
	}
	
	protected void setDirty(boolean dirty) {
		if(this.dirty != dirty)
			this.dirty = dirty;
		// thông báo thuộc tính PROP_DIRTY thay đổi cho WorkBench
		this.firePropertyChange(IEditorPart.PROP_DIRTY);
	}
	
	public void doSaveAs() {}
	
	public boolean isSaveAsAllowed() { return false; }
	
	public void setFocus() {}
	
	// write code in createPartControl2(Composite)
	public final void createPartControl(Composite parent) {
		this.createPartControl2(parent);
		this.showData();
		this.setDirty(false);
		this.firePropertyChange(EditorConstants.IDITOR_DATA_CHANGED);
		
		Control[] controls = this.registryDirtyControls();
		DirtyListener listener = new DirtyListenerImpl();
		DirtyUtils.registryDirty(listener, controls);
	}
	
	public abstract void showData();
	
	// create control in this method
	protected abstract void createPartControl2(Composite parent);
	
	// đăng ký các control này, nếu thay đổi nó sẽ thông báo lên workbench
	protected abstract Control[] registryDirtyControls();
	
	class DirtyListenerImpl implements DirtyListener {
		public void fireDirty() {
			// if have any change, fire to Editor
			AbstractBaseEditor.this.setDirty(true);
		}
	}
}
