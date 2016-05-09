package com.shebao.basis.database;

import java.sql.DriverManager;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.shebao.basis.Const;
import com.shebao.basis.Para;

public class ConnectionPool{
	private final static ConnectionPool cp = new ConnectionPool();
	private final Logger log = Logger.getLogger(Const.LoggerName.getStrval());
	// 数据库连接池数量
	private final int poolnum = Integer.parseInt(Para.DBPoolConnNumber.getVal());
	// 为了将原数据连接对象与现连接对象对应上,做此对应关系.
	private final Map<java.sql.Connection, Connection> c2c = new HashMap<>();

	// 指向当前可用的连接对象.
	private Connection curr = null;
	private Connection temp = null;

	private ConnectionPool() {
		Connection last = null;
		try {
			Class.forName("org.postgresql.Driver");

			for (int i = 0; i < poolnum; i++) {
				java.sql.Connection conn = DriverManager.getConnection(
						String.format("jdbc:postgresql://%1$s:%2$s/%3$s", Para.DBConnectionDriver.getVal(),
								Para.DBConnectionPort.getVal(), Para.DBDataBaseName.getVal()),
						Para.DBConnectionName.getVal(), Para.DBConnetionPassword.getVal());
				temp = new Connection(conn);

				temp.index = i;

				c2c.put(conn, temp);

				if (curr == null) {
					curr = temp;
					last = temp;
				} else {
					temp.next = curr;
					curr.per = temp;
					curr = temp;
				}
			}

			// 首尾相连
			curr.per = last;
			last.next = curr;
		} catch (ClassNotFoundException e) {
			log.log(Level.SEVERE, "读取数据库连接对象异常.", e);
		} catch (Exception e) {
			log.log(Level.SEVERE, "数据连接对象出现异常.", e);
		}

		log.finer("数据连接池建立!");
	}

	public java.sql.Connection getConnection() {
		synchronized (this) {
			try {
				while (!curr.canUse) {
					log.fine("当前没有可用连接对象!线程等待.");
					this.wait();
				}
			} catch (InterruptedException e) {
				log.log(Level.SEVERE, "线程等待异常:", e);
			}

			curr.canUse = false;

			// 首尾相连,给出去的时候按顺序给出.直接移动指针位置.
			curr = curr.next;

			return curr.per;
		}
	}

	public static ConnectionPool getIns() {
		return cp;
	}

	public void close(java.sql.Connection conn) {
		// 将关闭动作调用自身写的连接对象.
		close(c2c.get(conn));
	}

	void close(Connection conn) {
		synchronized (this) {
			conn.canUse = true;

			if (conn != curr) {
				// 将conn从原来位置取出
				conn.per.next = conn.next;
				conn.next.per = conn.per;

				curr.per.next = conn;
				conn.per = curr.per;
				curr.per = conn;
				conn.next = curr;
				curr = conn;
			}

			this.notify();
		}
	}

}
