package ilentt.ilenlab.com.rcp.editor;

import ilentt.ilenlab.com.rcp.model.Department;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IPersistableElement;

public class DeptEditorInput implements IEditorInput {

	protected Department dept;
	
	public DeptEditorInput(Department dept) {
		this.dept = dept;
	}
	
	public Department getDept() {
		return this.dept;
	}
	
	@SuppressWarnings("rawtypes")
	public Object getAdapter(Class adapter) {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean exists() {
		// TODO Auto-generated method stub
		return false;
	}

	public ImageDescriptor getImageDescriptor() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getName() {
		// TODO Auto-generated method stub
		// required!!!
		return "Department";
	}

	public IPersistableElement getPersistable() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getToolTipText() {
		// TODO Auto-generated method stub
		// required!!!
		return "Department";
	}

}
