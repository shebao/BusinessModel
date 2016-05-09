package com.shebao.basis.database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.shebao.basis.Const;

/**
 * 数据库操作方法,此类都是类方法,调用此类的方法是线程安全.<br>
 * 目前此对象绑定PG.需要时扩展此对象
 * 
 * @author yimin
 *
 */
public class DBOperation{
	private Logger log = Logger.getLogger(Const.LoggerName.getStrval());
	private ConnectionPool connPool = ConnectionPool.getIns();
	
	public DBOperation() {
	}

	/**
	 * 此方法调用一个sql语句,并且返回一个单一的int值.调用此方法应该仅有一个返回结果类型为int的调用.
	 * 
	 * @param sql
	 *          要执行的sql语句.
	 * @return 返回的int值. 返回-1为执行失败
	 */
	public int runSelReturnOneIntValue(String sql) {
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		int res = -1;

		log.finer("执行sql语句,sql:" + sql);

		try {
			conn = connPool.getConnection();
			st = conn.createStatement();
			rs = st.executeQuery(sql);

			// 如果有一个结果,将结果返回.
			if (rs.next()) {
				res = rs.getInt(1);
				log.finest("执行sql语句成功,sql:" + sql + " 返回值:" + res);
			} else {
				log.warning("执行sql语句出错,返回无结果.sql:" + sql);
			}
		} catch (Exception e) {
			log.log(Level.SEVERE, "执行sql语句出现异常,需要执行的sql:" + sql + "\n", e);
		} finally {
			try {
				rs.close();
				st.close();
				connPool.close((com.shebao.basis.database.Connection)conn);
			} catch (SQLException e) {
				log.log(Level.SEVERE, "执行关闭出现异常,需要执行的sql:" + sql + "\n", e);
			}
		}
		return res;
	}

	/**
	 * 此方法调用一个sql语句,并且返回一个结果集.<br>
	 * 由于结果集可能需要长期使用. 因此调用此方法将由用户自已关闭ResultSet/Statement和Connection.<br>
	 * 可调用closeResource()方法进行关闭操作.
	 * 
	 * @see DBOperation.closeResource
	 * @param sql
	 *          要执行的sql语句.
	 * @return 返回的结果集
	 */
	public ResultSet runSelReturnRS(String sql) {
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;

		log.finer("单行执行sql语句,sql:" + sql);
		try {
			conn = connPool.getConnection();
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			log.finest("单行执行sql语句成功,sql:" + sql);
		} catch (Exception e) {
			log.log(Level.SEVERE, "执行sql语句出现异常,需要执行的sql:" + sql + "\n", e);
		}
		return rs;
	}

	/**
	 * 此方法调用一组sql语句,并且返回一个单一的int值.
	 * 
	 * @param sql
	 *          要执行的sql语句.
	 */
	public void runBatchSql(String[] sqls) {
		Connection conn = null;
		Statement st = null;
		log.finer("批量执行sql语句.");

		try {
			conn = connPool.getConnection();
			st = conn.createStatement();
			conn.setAutoCommit(false);

			for (String sql : sqls) {
				log.finer("要执行的sql语句,sql:" + sql);
				st.addBatch(sql);
			}

			st.executeBatch();
			conn.commit();

			log.finest("批量执行sqls语句成功");
		} catch (Exception e) {
			log.log(Level.SEVERE, "执行批量sql语句出现异常", e);

			try {
				conn.rollback();
			} catch (SQLException e1) {
				log.log(Level.SEVERE, "执行回滚出错.", e1);
			}
		} finally {
			try {
				st.close();
				connPool.close((com.shebao.basis.database.Connection)conn);
			} catch (SQLException e) {
				log.log(Level.SEVERE, "执行关闭出现异常,需要执行的sql:", e);
			}
		}
	}

	/**
	 * 执行一个无返回值的操作,如update,或者delete.
	 * 
	 * @param sql
	 *          要执行的sql语句
	 * @return 执行影响的行数
	 */
	public int runUpdate(String sql) {
		Connection conn = null;
		Statement st = null;
		int res = 0;

		log.finer("执行无返回sql语句,sql:" + sql);
		try {
			conn = connPool.getConnection();
			st = conn.createStatement();
			res = st.executeUpdate(sql);

			if (res > 0) {
				log.finest("执行sql语句成功." + sql);
			} else {
				log.warning("执行sql语句出错,返回无结果.sql:" + sql);
			}
		} catch (Exception e) {
			log.log(Level.SEVERE, "执行sql语句出现异常,需要执行的sql:" + sql, e);
		} finally {
			try {
				st.close();
				connPool.close((com.shebao.basis.database.Connection)conn);
			} catch (SQLException e) {
				log.log(Level.SEVERE, "执行关闭出现异常,需要执行的sql:" + sql, e);
			}
		}

		return res;
	}

	/**
	 * 关闭所有用到的控件.调用此方法需要注意
	 * 
	 * @param rs
	 */
	public void closeResource(ResultSet rs) {
		try {
			log.finer("关闭所用到的JDBC组件.");

			Statement st = rs.getStatement();
			Connection conn = st.getConnection();
			rs.close();
			st.close();
			connPool.close(conn);
			log.finest("成功关闭所有用到JDBC组件.");
		} catch (SQLException e) {
			log.log(Level.SEVERE, "执行关闭出现异常", e);
		}
	}

}
