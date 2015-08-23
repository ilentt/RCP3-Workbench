package ilentt.ilenlab.com.rcp.model;

public class Department {
	private Integer deptId;
	private String deptNo;
	private String deptName;
	private String deptLocation;
	
	public Department() {
		// constructor
	}
	
	public Department(Integer deptId, String deptNo, String deptName, String deptLocation) {
		this.deptId = deptId;
		this.deptNo = deptNo;
		this.deptName = deptName;
		this.deptLocation = deptLocation;
	}
	
	public Integer getDeptId() {
		return this.deptId;
	}
	
	public void setDeptId(int deptId) {
		this.deptId = deptId;
	}
	
	public String getDeptNo() {
		return this.deptNo;
	}
	
	public void setDeptNo(String deptNo) {
		this.deptNo = deptNo;
	}
	
	public String getDeptName() {
		return this.deptName;
	}
	
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	
	public String getDeptLocation() {
		return this.deptLocation;
	}
	
	public void setDeptLocation(String deptLocation) {
		this.deptLocation = deptLocation;
	}
}
