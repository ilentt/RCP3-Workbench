package ilentt.ilenlab.com.rcp.model;

import java.util.ArrayList;
import java.util.List;

public class DepartmentDAO {
	
	private static final List<Department> list = new ArrayList<Department>();
	
	static {
		list.add(new Department(10, "D10", "Accounting", "Ha Noi"));
		list.add(new Department(20, "D20", "Reserch", "Ho Chi Minh"));
		list.add(new Department(30, "D30", "Sales", "Can Tho"));
		list.add(new Department(40, "D40", "Operators", "Da Nang"));
	}
	
	public static List<Department> listDepartment() {
		return list;
	}
	
	public static int getMaxDeptId() {
		int max = 0;
		for(Department dept : list) {
			if(dept.getDeptId() > max)
				max = dept.getDeptId();
		}
		
		return max;
	}
	
	public static Department findDepartment(int deptId) {
		for(Department dept : list) {
			if(dept.getDeptId() == deptId)
				return dept;
		}
		return null;
	}
	
	public static Department findDepartment(String deptNo) {
		for(Department dept : list) {
			if(dept.getDeptNo() == deptNo)
				return dept;
		}
		return null;
	}
	
	public static void deletDepartment(int deptId) {
		Department dept = findDepartment(deptId);
		if(dept == null)
			return;
		list.remove(dept);
	}
	
	
	public static Department updateDepartment(int deptId, String deptNo, String deptName,
			String deptLocation) throws DataException {
		Department dept = findDepartment(deptId);
		if(dept == null)
			return null;
		Department dept2 = findDepartment(deptNo);
		if(dept2!=null && dept2.getDeptId().intValue() != dept.getDeptId()) {
			throw new DataException("Unique constraints error - deptNo: " + deptNo);
		}
		dept.setDeptNo(deptNo);
		dept.setDeptName(deptName);
		dept.setDeptLocation(deptLocation);
		list.add(dept);
		return dept;
	}
	
	public static Department insertDepartment(String deptNo, String deptName, 
			String deptLocation) throws DataException {
		Department dept = findDepartment(deptNo);
		if(dept != null) {
			throw new DataException("Unique constraints error - deptNo: " + deptNo);
		}
		dept = new Department();
		int deptId = getMaxDeptId() + 1;
		dept.setDeptId(deptId);
		dept.setDeptNo(deptNo);
		dept.setDeptName(deptName);
		dept.setDeptLocation(deptLocation);
		return dept;
	}
	
}
