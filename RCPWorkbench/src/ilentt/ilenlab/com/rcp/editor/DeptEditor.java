package ilentt.ilenlab.com.rcp.editor;

import ilentt.ilenlab.com.rcp.constant.EditorConstants;
import ilentt.ilenlab.com.rcp.model.Department;
import ilentt.ilenlab.com.rcp.model.DepartmentDAO;
import ilentt.ilenlab.com.rcp.other.DirtyUtils;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IReusableEditor;
import org.eclipse.ui.IWorkbenchPartConstants;

public class DeptEditor extends AbstractBaseEditor implements IReusableEditor {
	
	public static final String ID = "deptEditor";
	private Integer deptId;
	private Text text_deptNo;
	private Text text_deptName;
	private Text text_deptLocation;
	
	public DeptEditor() {
		// constructor
	}
	
	
	@Override
	public void showData() {
		// TODO Auto-generated method stub
		DeptEditorInput deptEditorInput = (DeptEditorInput)this.getEditorInput();
		Department dept = deptEditorInput.getDept();
		this.deptId = dept.getDeptId();
		this.text_deptNo.setText(dept.getDeptNo() == null ? "" : dept.getDeptNo());
		this.text_deptName.setText(dept.getDeptName() == null ? "" : dept.getDeptName());
		this.text_deptLocation.setText(dept.getDeptName() == null ? "" : dept.getDeptLocation());
		
		// clear dirty
		this.setDirty(false);
	}

	@Override
	protected void createPartControl2(Composite parent) {
		// TODO Auto-generated method stub
		Composite container = new Composite(parent, SWT.NONE);
		container.setLayout(new GridLayout(2, false));
		
		Label lblDeptNo = new Label(container, SWT.NONE);
		lblDeptNo.setLayoutData(new GridData(SWT.RIGHT,SWT.CENTER,false,false,1,1));
		lblDeptNo.setText("Department No");
		
		text_deptNo = new Text(container, SWT.BORDER);
		text_deptNo.setLayoutData(new GridData(SWT.FILL, SWT.CENTER,true,false,1,1));
		
		Label lblDeptName = new Label(container, SWT.NONE);
		lblDeptName.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER,false,false,1,1));
		lblDeptName.setText("Department Name");
		
		text_deptName =  new Text(container, SWT.BORDER);
		text_deptName.setLayoutData(new GridData(SWT.FILL, SWT.CENTER,true,false,1,1));
		
		Label lblDeptLocation = new Label(container, SWT.NONE);
		lblDeptLocation.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER,false,false,1,1));
		lblDeptLocation.setText("Department Location");
		
		text_deptLocation =  new Text(container, SWT.BORDER);
		text_deptLocation.setLayoutData(new GridData(SWT.FILL, SWT.CENTER,true,false,1,1));
		
		new Label(container, SWT.NONE);
		
		DirtyListenerImpl dirtyListener = new DirtyListenerImpl();
		DirtyUtils.registryDirty(dirtyListener, this.text_deptName, this.text_deptNo, this.text_deptLocation);
	}

	@Override
	protected Control[] registryDirtyControls() {
		// TODO Auto-generated method stub
		return new Control[] {this.text_deptName, this.text_deptNo, this.text_deptLocation};
	}

	@Override
	public void doSave(IProgressMonitor monitor) {
		// TODO Auto-generated method stub
		try {
			String deptNo = this.text_deptNo.getText();
			String deptName = this.text_deptName.getText();
			String deptLocation = this.text_deptLocation.getText();
			if(deptId != null) {
				Department dept = DepartmentDAO.updateDepartment(deptId, deptNo, 
						deptName, deptLocation);
				this.setInput(new DeptEditorInput(dept));
				this.setDirty(false);
				this.firePropertyChange(EditorConstants.IDITOR_DATA_CHANGED);
			}else{
				Department dept = DepartmentDAO.insertDepartment(deptNo, deptName, deptLocation);
				this.setInput(new DeptEditorInput(dept));
				this.setDirty(false);
				this.firePropertyChange(EditorConstants.IDITOR_DATA_CHANGED);
			}
			
		}catch(Exception e) {
			MessageDialog.openError(this.getSite().getShell(), "Error", e.getMessage());
			e.printStackTrace();
		}
	}
	
	// chuyển setInput sang public
	@Override
	public void setInput(IEditorInput input) {
		super.setInput(input);
		firePropertyChange(IWorkbenchPartConstants.PROP_INPUT);
	}
	
	public String getDeptInfo() {
		DeptEditorInput input = (DeptEditorInput) this.getEditorInput();
		Department dept = input.getDept();
		if(dept == null)
			return "";
		String info = dept.getDeptNo() + " - " + dept.getDeptName() + " - " + dept.getDeptLocation();
		return info;
	}

}
