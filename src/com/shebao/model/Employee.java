package com.shebao.model;
import com.shebao.basis.BaseObject;

/**
 * 员工类
 * 
 * @author Administrator
 *
 */
public class Employee implements BaseObject{
//	private static Map<Integer, Employee> emps = new Hashtable<Integer, Employee>();
//
//	private int id;// 标识Id
//	private String loginName;// 登录账号
//	private String password;// 登录密码
//	private String jobNumber;// 员工工号
//	private String realName;// 真实姓名
//	private boolean sex;// 性别
//	private Date birthday;// 年龄
//	private String mobile;// 手机号码
//	private String email;// 邮箱地址
//	private Map<Integer, Boolean> permissions = new Hashtable<Integer, Boolean>();// 权限
//	private Date createDate;// 创建时间
//	private Date updateDate;// 更新时间
//	private Date deleteDate;// 删除时间
//	private Date lastLoginDate;// 最后登录时间
//	private Date lastActionDate;// 最后操作时间
//	private long loginCount;// 登录次数
//	private boolean enable;// 是否启用
//	private PermissionGroup group;// 员工对应的权限组
//	private int department_id;// 部门Id //TODO改成部门对象.
//	private int flag = 0; // 标志
//
//	private HttpSession session;
//	private SessionHelper sh;
//
//	/**
//	 * 私有的构造方法,此方法执行一个新建一个员工对象.并且将其直接写入数据库当中.
//	 */
//	private Employee(String loginName, String password, String jobNumber, String realName, boolean sex, Date birthday,
//			String mobile, String email, boolean enable, PermissionGroup group, int department_id) {
//		this();
//
//		this.loginName = loginName;
//		this.password = base.encrypt(password);
//		this.jobNumber = jobNumber;
//		this.realName = realName;
//		this.sex = sex;
//		this.birthday = birthday;
//		this.mobile = mobile;
//		this.email = email;
//		this.enable = enable;
//		this.group = group;
//		this.department_id = department_id;
//
//		String sql = String.format("select addemployee(ROW(NULL,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,NULL,NULL,NULL,%s,%d));",
//				dbUtility.inputStr(loginName), dbUtility.inputStr(this.password), dbUtility.inputStr(jobNumber),
//				dbUtility.inputStr(realName), dbUtility.inputStr(mobile), dbUtility.inputBoolean(sex),
//				dbUtility.inputDate(birthday), dbUtility.inputStr(email), (group == null) ? 0 : group.getId(), department_id,
//				dbUtility.inputBoolean(enable), 0);
//
//		id = DBOperation.runSelReturnOneIntValue(sql);
//
//		sh.addOperator(session, this);
//
//		log.finer("创建一个新的员工.id:" + this.id);
//	}
//
//	/**
//	 * 员工对象的默认构造函数
//	 */
//	private Employee() {
//	}
//
//	/**
//	 * 返回标识id
//	 * 
//	 * @return
//	 */
//	public int getId() {
//		return id;
//	}
//
//	/**
//	 * 获取登录账号
//	 * 
//	 * @return
//	 */
//	public String getLoginName() {
//		return loginName;
//	}
//
//	/**
//	 * 获取登录密码
//	 * 
//	 * @return
//	 */
//	public String getPassword() {
//		return password;
//	}
//
//	/**
//	 * 设置登录密码
//	 * 
//	 * @param password
//	 *          设置的密码
//	 */
//	public void setPassword(String password) {
//		this.password = base.encrypt(password);
//	}
//
//	/**
//	 * 获取员工工号
//	 * 
//	 * @return
//	 */
//	public String getJobNumber() {
//		return jobNumber;
//	}
//
//	/**
//	 * 设置员工工号
//	 * 
//	 * @param jobNumber
//	 *          工号
//	 */
//	public void setJobNumber(String jobNumber) {
//		this.jobNumber = jobNumber;
//	}
//
//	/**
//	 * 获取员工真实姓名
//	 * 
//	 * @return
//	 */
//	public String getRealName() {
//		return realName;
//	}
//
//	/**
//	 * 设置真实姓名
//	 * 
//	 * @param realName
//	 *          姓名
//	 */
//	public void setRealName(String realName) {
//		this.realName = realName;
//	}
//
//	/**
//	 * 获取性别 true:男 false:女
//	 * 
//	 * @return
//	 */
//	public boolean getSex() {
//		return sex;
//	}
//
//	/**
//	 * 设置性别
//	 * 
//	 * @param sex
//	 *          true:男 false:女
//	 */
//	public void setSex(boolean sex) {
//		this.sex = sex;
//	}
//
//	/**
//	 * 获取年龄
//	 * 
//	 * @return
//	 */
//	public int getAge() {
//		// TODO 得到年龄
//		return 1;
//	}
//
//	/**
//	 * 设置年龄
//	 * 
//	 * @param age
//	 *          年龄
//	 */
//	public void setAge(int age) {
//		// TODO 设置年龄
//	}
//
//	/**
//	 * 获取手机号码
//	 * 
//	 * @return
//	 */
//	public String getMobile() {
//		return mobile;
//	}
//
//	/**
//	 * 设置手机号码
//	 * 
//	 * @param mobile
//	 */
//	public void setMobile(String mobile) {
//		this.mobile = mobile;
//	}
//
//	/**
//	 * 获取邮箱地址
//	 * 
//	 * @return
//	 */
//	public String getEmail() {
//		return email;
//	}
//
//	/**
//	 * 设置邮箱地址
//	 * 
//	 * @param email
//	 *          邮箱地址
//	 * 
//	 */
//	public void setEmail(String email) {
//		this.email = email;
//	}
//
//	/**
//	 * 创建时间
//	 * 
//	 * @return
//	 */
//	public Date getCreateDate() {
//		return createDate;
//	}
//
//	/**
//	 * 设置创建时间
//	 * 
//	 * @param createDate
//	 */
//	public void setCreateDate(Date createDate) {
//		this.createDate = createDate;
//	}
//
//	/**
//	 * 获取更新时间
//	 * 
//	 * @return
//	 */
//	public Date getUpdateDate() {
//		return updateDate;
//	}
//
//	/**
//	 * 设置更新时间
//	 * 
//	 * @param updateDate
//	 *          更新的时间
//	 */
//	public void setUpdateDate(Date updateDate) {
//		this.updateDate = updateDate;
//	}
//
//	/**
//	 * 获取删除时间
//	 * 
//	 * @return
//	 */
//	public Date getDeleteDate() {
//		return deleteDate;
//	}
//
//	/**
//	 * 设置删除时间
//	 * 
//	 * @param deleteDate
//	 *          删除时间
//	 */
//	public void setDeleteDate(Date deleteDate) {
//		this.deleteDate = deleteDate;
//	}
//
//	/**
//	 * 获取最后登录时间
//	 * 
//	 * @return
//	 */
//	public Date getLastLoginDate() {
//		return lastLoginDate;
//	}
//
//	/**
//	 * 设置最后登录时间
//	 * 
//	 * @param lastLoginDate
//	 *          最后登录时间
//	 */
//	public void setLastLoginDate(Date lastLoginDate) {
//		this.lastLoginDate = lastLoginDate;
//	}
//
//	/**
//	 * 获取最后操作时间
//	 * 
//	 * @return
//	 */
//	public Date getLastActionDate() {
//		return lastActionDate;
//	}
//
//	/**
//	 * 设置最后操作时间
//	 * 
//	 * @param lastActionDate
//	 *          最后操作时间
//	 */
//	public void setLastActionDate(Date lastActionDate) {
//		this.lastActionDate = lastActionDate;
//	}
//
//	/**
//	 * 获取登录次数
//	 * 
//	 * @return
//	 */
//	public long getLoginCount() {
//		return loginCount;
//	}
//
//	/**
//	 * 获取员工账号是否启用
//	 * 
//	 * @return true:启用 false:禁用
//	 */
//	public boolean getEnable() {
//		return enable;
//	}
//
//	/**
//	 * 设置员工账号是否启用
//	 * 
//	 * @param enable
//	 *          true:启用 false:禁用
//	 */
//	public void setEnable(Boolean enable) {
//		this.enable = enable;
//	}
//
//	/**
//	 * 为一个员工设置权限,此操作仅在内存当中操作,需要写入数据库当中,需要再执行flash()方法. 如已经存在,将失败
//	 * 
//	 * @param funId
//	 *          功能id
//	 * @param hasPermission
//	 *          需要设置是否具有相关权限
//	 * @return true:设置成功 false:设置失败
//	 */
//	public boolean addPermission(int funId, boolean hasPermission) {
//		if (permissions.containsKey(funId)) {
//			log.warning("已经存在此功能号,不能新增.funid" + funId + "员工id:" + id);
//			return false;
//		}
//
//		permissions.put(funId, hasPermission);
//		log.finest("新增功能成功.funid" + funId + "员工id:" + id + "权限值:" + hasPermission);
//		return true;
//	}
//
//	/**
//	 * 为一个员工删除一项权限,此操作仅在内存当中操作,需要写入数据库当中,需要再执行flash()方法. 如不存在,将失败
//	 * 
//	 * @param funId
//	 *          功能id
//	 * @return true:设置成功 false:设置失败
//	 */
//	public boolean deletePermission(int funId) {
//		if (!permissions.containsKey(funId)) {
//			log.warning("不存在此功能号,不能删除.funid" + funId + "员工id:" + id);
//			return false;
//		}
//
//		permissions.remove(funId);
//		log.finest("删除功能成功.funid" + funId + "员工id:" + id);
//		return true;
//	}
//
//	/**
//	 * 为一个已经存在的员工设置权限,此操作仅在内存当中操作,需要写入数据库当中,需要再执行flash()方法. 如不存在,将失败
//	 * 
//	 * @param funId
//	 *          功能id
//	 * @param hasPermission
//	 *          需要设置是否具有相关权限
//	 * @return true:设置成功 false:设置失败
//	 */
//	public boolean modifyPermission(int funId, boolean hasPermission) {
//		if (!permissions.containsKey(funId)) {
//			log.warning("不存在此功能号,不能修改.funid" + funId + "员工id:" + id);
//			return false;
//		}
//
//		permissions.remove(funId);
//		permissions.put(funId, hasPermission);
//		log.finest("修改功能成功.funid" + funId + "员工id:" + id + "权限值:" + hasPermission);
//		return false;
//	}
//
//	/**
//	 * 将修改过的数据写入数据库中
//	 */
//	public void flash() {
//		String[] sqls = new String[permissions.size() + 2];
//		int ind = 0;
//		// 先删除数据库当中现有权限组权限
//		sqls[ind] = "delete from t_employee_permission where employee_id = " + id;
//		ind++;
//
//		// 更新自身的数据
//		sqls[ind] = String.format(
//				"UPDATE employee  SET birthday = %s,email = %s,enable =%s,flag =%d,department_id=%d,"
//						+ "job_number=%s,login_name=%s,mobile=%s,password=%s,real_name=%s,role_group_id=%d,sex=%d,update_date=now() "
//						+ " WHERE id = %d",
//				dbUtility.inputDate(birthday), dbUtility.inputStr(email), dbUtility.inputBoolean(enable), flag, department_id,
//				dbUtility.inputStr(jobNumber), dbUtility.inputStr(loginName), dbUtility.inputStr(mobile),
//				dbUtility.inputStr(password), dbUtility.inputStr(realName), group.getId(), dbUtility.inputBoolean(sex), id);
//		ind++;
//
//		// 循环加入现在所有的权限对象.
//		for (Map.Entry<Integer, Boolean> ent : permissions.entrySet()) {
//			sqls[ind] = String.format(
//					"insert into t_employee_permission(employee_id,permission_id,enable,flag) VALUES(%d,%d,%d,%d)", id,
//					ent.getKey(), dbUtility.inputBoolean(ent.getValue()), 0);
//			
//			ind++;
//		}
//		
//		DBOperation.runBatchSql(sqls);
//
//		log.finer("修改权限组权限入数据库完成:员工:" + id);
//	}
//
//	/**
//	 * 判断一个员工是否具有操作权限
//	 * 
//	 * @param funId
//	 *          操作功能ID
//	 * @return true:有操作权限 false:无操作权限
//	 */
//	public boolean hasPermission(int funId) {
//		if (permissions.containsKey(funId)) {
//			return permissions.get(funId);
//		} else {
//			return (group == null) ? false : group.hasPermission(funId);
//		}
//	}
//
//	/**
//	 * 通过操作员ID返回操作员对象
//	 * 
//	 * @param empid
//	 *          员工id
//	 * @return 返回员工的实例,找不到对应的实例返回NULL
//	 */
//	public static Employee getIns(int empid) {
//		return emps.get(empid);
//	}
//
//	/**
//	 * 对员工进行初始化,从执久层得到所有权限组相应对象.<br>
//	 * 一次性将所有的对象实例化
//	 */
//	public static void init() {
//		String sql = "SELECT e.id,e.login_name,e.password,e.department_id,e.mobile,e.real_name,e.role_group_id,"
//				+ "e.job_number,e.email,e.enable,e.flag FROM employee e where flag > -1";
//
//		try {
//			ResultSet rs = DBOperation.runSelReturnRS(sql);
//
//			// 将员工自身对象读入,并赋值
//			while (rs.next()) {
//				Employee emp = new Employee();
//				emp.id = rs.getInt(1);
//				emp.loginName = rs.getString(2);
//				emp.password = rs.getString(3);
//				emp.department_id = rs.getInt(4);
//				emp.mobile = rs.getString(5);
//				emp.realName = rs.getString(6);
//				emp.group = PermissionGroup.getIns(rs.getInt(7));
//				emp.jobNumber = rs.getString(8);
//				emp.email = rs.getString(9);
//				emp.enable = dbUtility.outputBoolean(rs.getString(10));
//				emp.flag = rs.getInt(11);
//
//				emp.initInstance();
//				emps.put(emp.id, emp);
//			}
//
//			DBOperation.closeResource(rs);
//		} catch (SQLException e) {
//			log.log(Level.SEVERE, "执行sql出现异常,需要执行的sql:" + sql + "\n\n", e);
//		}
//	}
//
//	/**
//	 * 初始化一个员工对象,目前仅将权限读出
//	 */
//	private void initInstance() {
//
//		// 读数据库,将员工对应的权限读出
//		String sql = "SELECT t.permission_id,t.employee_id,t.enable,t.flag from t_employee_permission t where t.flag > -1 and t.employee_id = "
//				+ id;
//		try {
//			ResultSet rs = DBOperation.runSelReturnRS(sql);
//
//			// 为本组所有权限赋值
//			while (rs.next()) {
//				permissions.put(rs.getInt(1), dbUtility.outputBoolean(rs.getString(3)));
//			}
//
//			DBOperation.closeResource(rs);
//		} catch (Exception e) {
//			log.log(Level.SEVERE, "赋值出现异常,需要执行的sql:" + sql + "\n\n", e);
//		}
//	}
//
//	/**
//	 * 增加一个员工的操作.允许员工名称与其他员工名称相同
//	 * 
//	 * @param name
//	 *          员工名称
//	 * @param remark
//	 *          备注
//	 * @return 成功增加员工的对象.失败返回null
//	 */
//	public static Employee addEmployee(String loginName, String password, String jobNumber, String realName, boolean sex,
//			Date birthday, String mobile, String email, boolean enable, PermissionGroup group, int department_id) {
//
//		Employee emp = new Employee(loginName, password, jobNumber, realName, sex, birthday, mobile, email, enable, group,
//				department_id);
//
//		emps.put(emp.id, emp);
//
//		log.fine("增加一个员工,id:" + emp.id);
//
//		return emp;
//	}
//
//	/**
//	 * 增加一个员工的操作.允许员工名称与其他员工名称相同
//	 * 
//	 * @param name
//	 *          员工名称
//	 * @param remark
//	 *          备注
//	 * @return 成功增加员工的对象.失败返回null
//	 */
//	public static Employee addEmployee(Map<String, String[]> v) {
//		Date bri;
//		PermissionGroup pg;
//		try {
//			bri = dbUtility.outputDate(v.get(FieldName.EmployeeBirthday)[0]);
//		} catch (Exception e) {
//			bri = null;
//		}
//
//		try {
//
//			pg = PermissionGroup.getIns(Integer.parseInt(v.get(FieldName.EmployeeRoleGroup)[0]));
//		} catch (Exception e) {
//			log.log(Level.SEVERE, "新增一个员工数据转换出现异常.", e);
//			return null;
//		}
//
//		Employee emp = new Employee(v.get(FieldName.EmployeeLoginName)[0], v.get(FieldName.EmployeePassword)[0],
//				v.get(FieldName.EmployeeJobNumber)[0], v.get(FieldName.EmployeeRealName)[0],
//				v.get(FieldName.EmployeeSex)[0].equals("1") ? true : false, bri, v.get(FieldName.EmployeeMobile)[0],
//				v.get(FieldName.EMail)[0], v.get(FieldName.Enable)[0].equals("1") ? true : false, pg,
//				Integer.parseInt(v.get(FieldName.EmployeeDepartmentId)[0]));
//
//		emps.put(emp.id, emp);
//
//		log.fine("增加一个员工,id:" + emp.id);
//
//		return emp;
//	}
//
//	/**
//	 * 删除一个员工的操作.
//	 * 
//	 * @param
//	 */
//	public static void deleteEmployee(int employeeId) {
//		emps.remove(employeeId);
//
//		String sql = "update employee set flag = -1 where id = " + employeeId;
//		DBOperation.runUpdate(sql);
//
//		log.fine("删除一个员工,id:" + employeeId);
//	}
//
//	/**
//	 * 返回用户登录名是否已经存在
//	 * 
//	 * @param loginname
//	 *          一个新登录名
//	 * @return 如果已经存在,返回fasle,未存在,返回true
//	 */
//	public static boolean existsEmployeeName(String newLoginName) {
//		Employee ins;
//		for (Map.Entry<Integer, Employee> emp : emps.entrySet()) {
//			ins = emp.getValue();
//			if (ins.loginName.equals(newLoginName)) {
//				return true;
//			}
//		}
//		return false;
//	}
//
//	/**
//	 * 一个用户的登录操作
//	 * 
//	 * @param loginname
//	 *          登录名称
//	 * @param passwd
//	 *          登录密码
//	 * @return
//	 */
//	public static Employee Login(HttpSession session, String loginname, String passwd) {
//		Employee ins;
//
//		for (Map.Entry<Integer, Employee> emp : emps.entrySet()) {
//			ins = emp.getValue();
//			// 判断用户名/密码是否正常,
//			if (ins.loginName.equals(loginname) && ins.password.equals(base.encrypt(passwd))) {
//				// 判断用户是否可以登录
//				if (ins.enable) {
//					ins.sh.addOperator(session, ins);
//					return ins;
//				}
//				log.warning("用户登录.用户已经不可用,用户:" + loginname);
//			}
//		}
//
//		return null;
//	}
//
//	@Override
//	public String toString() {
//		return "LoginName" + loginName + ".id:" + id;
//	}
//
//	@Override
//	public boolean quit() {
//		sh.quitOperator(session, this);
//		return true;
//	}
//
//	@Override
//	public HttpSession getSession() {
//		return session;
//	}
//
//	@Override
//	public Object getProperty(String PropertyName) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public void setProperty(String PropertyName, Object PropertyValue) {
//		// TODO Auto-generated method stub
//		
//	}


}
