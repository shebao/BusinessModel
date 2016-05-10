package com.shebao.model.Employee;

/**
 * 员工的类型 1：职员 2：兼职 3：体验店管理 4：体验店员工 99：超验管理员
 * 
 * @author yimin
 *
 */
public enum EmpType {
	General(1,"一般员工"), PartTime(2,"兼职人员"), StoreManager(3,"体验店管理者"), Store(4,"体验店员工"), SuperMan(99,"超级管理员");

	private final int typeValue;
	private final String remark;
	
	private EmpType(int value,String remark) {
		this.typeValue = value;
		this.remark = remark;
	}

	public static EmpType valueof(int typeValue) {
		switch (typeValue) {
		case 1:
			return EmpType.General;
		case 2:
			return EmpType.PartTime;
		case 3:
			return StoreManager;
		case 4:
			return EmpType.Store;
		case 99:
			return EmpType.SuperMan;
		default:
			return null;
		}
	}
	
	public int getValue(){
		return typeValue;
	}

	@Override
	public String toString() {
		return remark;
	}
}
