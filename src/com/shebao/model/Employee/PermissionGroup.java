package com.shebao.model.Employee;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Hashtable;
import java.util.Map;
import java.util.logging.Level;

import com.shebao.basis.BaseObject;
import com.shebao.basis.database.tables.TabField;

/**
 * 权限组对象,此对象保存所有组对应的权限,用户属于此组对象 一个权限组对应可以操作的权限或禁止的权限,未在权限组中明确表示的视为无操作权限<br>
 * 增加一个新权限组操作.需要先增加权限组.再循环为一个权限组赋值.最后调用flash()方法写入数据持久层当中.
 *
 * @author yimin
 *
 */

public final class PermissionGroup implements BaseObject{
	/**
	 * 存在所有权限组对象.
	 */
	private static Map<Integer, PermissionGroup> groups = new Hashtable<Integer, PermissionGroup>();

	/**
	 * 增加一个权限组的操作.允许权限组对象名相同
	 *
	 * @param name
	 *          权限组名称
	 * @param remark
	 *          权限组备注
	 * @return 成功增加权限组的对象.失败返回null
	 */
	public static PermissionGroup addPermissionGroup(final String name, final String remark) {
		final PermissionGroup pg = new PermissionGroup(name, remark);
		groups.put(pg.id, pg);

		log.fine("增加一个权限组,id:" + pg.id);

		return pg;
	}

	/**
	 * 删除一个权限组的操作.
	 *
	 * @param permissionGroupId
	 */
	public static void deletePermissionGroup(final int permissionGroupId) {
		groups.remove(permissionGroupId);

		final String[] sqls = new String[2];

		sqls[0] = "delete from t_group_permission where role_group_id = " + permissionGroupId;
		sqls[1] = "delete from role_group where id = " + permissionGroupId;

		dbOperation.runBatchSql(sqls);

		log.fine("删除一个权限组,id:" + permissionGroupId);
	}

	/**
	 * 返回对应的权限组对象
	 *
	 * @param groupid
	 *          权限组id
	 * @return id对应的对象,未找到对应的返回NULL
	 */
	public static PermissionGroup getIns(final int groupid) {
		return groups.get(groups);
	}

	/**
	 * 对权限组进行初始化,从执久层得到所有权限组相应对象.<br>
	 * 一次性将所有的对象实例化
	 */
	public static void init() {
		final String sql = "SELECT r.id,r.group_name,r.remark,r.create_date,r.delete_date from role_group r";

		try {
			final ResultSet rs = dbOperation.runSelReturnRS(sql);

			while (rs.next()) {
				final PermissionGroup pg = new PermissionGroup();
				pg.id = rs.getInt(1);
				pg.name = rs.getString(2);
				pg.remark = rs.getString(3);

				pg.initPermissionGroup();
				groups.put(pg.id, pg);
			}
			dbOperation.closeResource(rs);
		} catch (final SQLException e) {
			log.log(Level.SEVERE, "执行sql出现异常,需要执行的sql:" + sql + "\n\n", e);
		}
	}

	private int id;

	private String name;

	private String remark;

	/** 组所具有的权限的集合. */
	private final Map<Integer, Boolean> permissions = new Hashtable<Integer, Boolean>();

	private PermissionGroup() {
	}

	/**
	 * 私有的构造方法
	 */
	private PermissionGroup(final String name, final String remark) {
		this();
		// 创建一个新的权限组.执行sql语句写入数据当中.

		this.name = name;
		this.remark = remark;

		final String sql = String.format("select addrolegroup(%s,%s));", dbu.inputStr(name), dbu.inputStr(remark));

		this.id = dbOperation.runSelReturnOneIntValue(sql);

		log.finer("创建一个新的权限组.id:" + this.id);
	}

	/**
	 * 为一个权限组设置权限,此操作仅在内存当中操作,需要写入数据库当中,需要再执行flash()方法. 如已经存在,将失败
	 *
	 * @param funId
	 *          功能id
	 * @param hasPermission
	 *          需要设置是否具有相关权限
	 * @return true:设置成功 false:设置失败
	 */
	public boolean addPermission(final int funId, final boolean hasPermission) {
		if (this.permissions.containsKey(funId)) {
			log.warning("已经存在此功能号,不能新增.funid" + funId + "权限组id:" + this.id);
			return false;
		}

		this.permissions.put(funId, hasPermission);
		log.finest("新增功能成功.funid" + funId + "权限组id:" + this.id + "权限值:" + hasPermission);
		return true;
	}

	/**
	 * 为一个权限组删除一项权限,此操作仅在内存当中操作,需要写入数据库当中,需要再执行flash()方法. 如不存在,将失败
	 *
	 * @param funId
	 *          功能id
	 * @return true:设置成功 false:设置失败
	 */
	public boolean deletePermission(final int funId) {
		if (!this.permissions.containsKey(funId)) {
			log.warning("不存在此功能号,不能删除.funid" + funId + "权限组id:" + this.id);
			return false;
		}

		this.permissions.remove(funId);
		log.finest("删除功能成功.funid" + funId + "权限组id:" + this.id);
		return true;
	}

	/**
	 * 将修改过的数据写入数据库中,同时也会更新权限组自身对象的内容
	 */
	public void flash() {
		final String[] sqls = new String[this.permissions.size() + 2];
		int ind = 0;

		// 更新自身的数据
		sqls[ind] = String.format("update role_group set group_name = %s,remark = %s where id = %d",
				dbu.inputStr(this.name), dbu.inputStr(this.remark), this.id);
		ind++;

		// 先删除数据库当中现有权限组权限
		sqls[ind] = "delete from t_group_permission where role_group_id = " + this.id;
		ind++;

		// 循环加入现在所有的权限对象.
		for (final Map.Entry<Integer, Boolean> ent : this.permissions.entrySet()) {
			sqls[ind] = String.format(
					"insert into t_group_permission(role_group_id,permission_id,enable,flag) VALUES(%d,%d,%d,%d)", this.id,
					ent.getKey(), dbu.inputBool(ent.getValue()), 0);
		}

		dbOperation.runBatchSql(sqls);
		log.finer("修改权限组权限入数据库完成:权限组:" + this.id + this.name);
	}

	public int getId() {
		return this.id;
	}

	public String getName() {
		return this.name;
	}

	public String getRemark() {
		return this.remark;
	}

	/**
	 * 判断一个权限组是否具有操作权限
	 *
	 * @param funId
	 *          操作功能ID
	 * @return true:有操作权限 false:无操作权限
	 */
	public boolean hasPermission(final int funId) {
		if (this.permissions.containsKey(funId)) {
			return this.permissions.get(funId);
		} else {
			return false;
		}
	}

	/**
	 * 初始化一个权限组对象.将所有权限读到对象当中.
	 */
	private void initPermissionGroup() {
		final String sql = "SELECT t.role_group_id,t.enable,t.permission_id from t_group_permission t where t.role_group_id = "
				+ this.id;
		try {
			final ResultSet rs = dbOperation.runSelReturnRS(sql);

			// 为本组所有权限赋值
			while (rs.next()) {
				this.permissions.put(rs.getInt(TabField.GroupPermissionPermissionId), rs.getBoolean(TabField.Enable));
			}

			dbOperation.closeResource(rs);
		} catch (final Exception e) {
			log.log(Level.SEVERE, "赋值出现异常,需要执行的sql:" + sql + "\n\n", e);
		}
	}

	/**
	 * 为一个已经存在的权限组设置权限,此操作仅在内存当中操作,需要写入数据库当中,需要再执行flash()方法. 如不存在,将失败
	 *
	 * @param funId
	 *          功能id
	 * @param hasPermission
	 *          需要设置是否具有相关权限
	 * @return true:设置成功 false:设置失败
	 */
	public boolean modifyPermission(final int funId, final boolean hasPermission) {
		if (!this.permissions.containsKey(funId)) {
			log.warning("不存在此功能号,不能修改.funid" + funId + "权限组id:" + this.id);
			return false;
		}

		this.permissions.put(funId, hasPermission);
		log.finest("修改功能成功.funid" + funId + "权限组id:" + this.id + "权限值:" + hasPermission);
		return false;
	}

	public void setName(final String name) {
		this.name = name;
	}

	public void setRemark(final String remark) {
		this.remark = remark;
	}

}
