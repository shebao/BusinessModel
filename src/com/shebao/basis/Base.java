package com.shebao.basis;

import java.io.File;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.EnumSet;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.shebao.basis.database.DBOperation;
import com.shebao.basis.database.DBUtility;
import com.shebao.basis.database.PGDBUtility;
import sun.misc.BASE64Encoder;
import java.util.Map;
import java.util.Map.Entry;

/**
 * 基础服务类,此类为单例,使用此类的对象通过getIns()获得此类的实例.不能使用new操作符获得实例.<br>
 * 在最初调用此对象后,必须同时调用init方法以进行整个程序的初始化.<br>
 * 提供日志对象,通过getLog()方法获得日志对象实例 提供配置对象,通过getConfiguration()方法
 * 因为此对象提供所有其他对象基础数据,因此此对象应该在所有其他对象实例化之前被调用. <br/>
 * 当需要读写文件时,第一次调用时必须给出对应的根目录所在位置.调用重载的方法:getIns(String)设置根目录所在位置.
 * 如未设置,则默认值为当前目录.除第一次调用外,再次给出目录值将无效 <br/>
 * <br/>
 * 数据连接对象，使用getDBConnection()方法获得一个数据库的连接对象，此对象每请求一次都会提供一个新的数据库连接对象。
 * 在需要开启事务的时候务必不能请求多个数据连接对象。 另：此对象需要获得者手工释放此连接. <br>
 * 
 * @author yimin
 * 
 *
 */
public class Base{
	private static Base ins;
	private final Logger log;

	private final DBOperation dbo;
	private final DBUtility dBUtility;
	private boolean isFirstCallInit = true;

	/**
	 * 私有的实例化方法,不允许创建多个实例
	 * 
	 * @param initPara
	 *          初始化的参数.以MAP形式传入.
	 */
	private Base(Map<String, String> initPara) {
		if (initPara != null) {
			EnumSet<Para> paras = EnumSet.allOf(Para.class);
			for (final Para p : paras) {
				for (final Entry<String, String> en : initPara.entrySet()) {
					if (en.getKey().equals(p.getParaName())) {
						p.setParaVal(en.getValue());
						break;
					}
				}
			}
		} else {
			System.err.println("传入的参数对象为空.无法继续执行.");
		}

		log = Logger.getLogger(Const.LoggerName.getStrval());
		createLogger(log);

		dbo = new DBOperation();
		dBUtility = new PGDBUtility();
	}

	/**
	 * 初始化方法,此过程应该尽早调用,完成整个程序需要初始化的对象初始化动作.
	 */
	public synchronized boolean init() {
		// 此方法仅能调用一次.
		if (!isFirstCallInit) {
			log.warning("不允许多次调用初始化方法!!");
			return false;
		}

		isFirstCallInit = false;

		// TODO 初始化所有MODEL对象
		// PermissionGroup.init();
		// Employee.init();
		// ProductCategory.init();
		// Specification.init();//规格
		// Product.init(); //商品必须在规格后初始化
		// ProductGroup.init();//商品组引用商品,需要在商品后初始化
		// SaleActivity.init();//促销活动,需要商品

		return true;
	}

	/**
	 * 返回数据库输入输出参数处理对象
	 * 
	 * @return
	 */
	public DBUtility getdBUtility() {
		return dBUtility;
	}

	/**
	 * 返回用户与Session对应的对象
	 * 
	 * @return
	 */
	// public SessionHelper getSessionHelper() {
	// if (cs == null) {
	// cs = SessionHelper.getIns();
	// }
	//
	// return cs;
	// }

	/**
	 * 创建日志对象
	 */
	private void createLogger(Logger log) {
		// 创建日志目录
		String logpath = Para.RealPath.getVal() + "/log";
		File dir = new File(logpath);
		if (!dir.exists()) {
			dir.mkdirs();
		}
		System.out.println("日志输出目录:" + logpath);

		FileHandler fh;
		try {
			if (Para.IsTestEnvironment.getVal().toUpperCase().equals("Y")) {
				// 控制台日志输出
				ConsoleHandler ch = new ConsoleHandler();
				ch.setLevel(Level.ALL);
				ch.setFormatter(new LogFormatter());
				log.addHandler(ch);
			}

			// 创建文件日志输出
			fh = new FileHandler(Para.RealPath.getVal() + Const.LoggerName.getStrval(), 5000000, 10, true);
			fh.setLevel(getLogLevel());
			fh.setFormatter(new LogFormatter());

			log.addHandler(fh);

			log.setLevel(getLogLevel());

			log.info("程序启动");
			log.finer("日志对象启用.");
		} catch (SecurityException | IOException e) {
			// 这时候日志对象有可能未创建完成,此部分不能用日志
			e.printStackTrace();
		}
	}

	/**
	 * 通过此方法获得对象的实例,单例
	 * 
	 * @return
	 */
	public static Base getIns() {
		return ins;
	}

	/**
	 * 通过此方法获得对象的实例,单例
	 * 
	 * @return
	 */
	public synchronized static Base getIns(Map<String, String> config) {
		if (ins != null)
			return ins;
		else {
			ins = new Base(config);
		}

		return ins;
	}

	/**
	 * 返回一个日志对象,此对象可以记录日志.
	 * 
	 * @return
	 */
	public Logger getLogger() {
		return this.log;
	}

	private Level getLogLevel() {
		switch (Integer.parseInt(Para.LogLevel.getVal())) {
		case -1:
			return Level.OFF;
		case 0:
			return Level.ALL;
		case 1:
			return Level.SEVERE;
		case 2:
			return Level.WARNING;
		case 3:
			return Level.INFO;
		case 4:
			return Level.CONFIG;
		case 5:
			return Level.FINE;
		case 6:
			return Level.FINER;
		case 7:
			return Level.FINEST;
		default:
			return Level.ALL;
		}
	}

	/**
	 * 加密码一个明文,此过程返回对应的密码,如出现错误,返回NULL
	 * 
	 * @param plaintext
	 *          需要加密的文本
	 * @return
	 */
	public String encrypt(String plaintext) {
		MessageDigest en;
		try {
			en = MessageDigest.getInstance(Const.KEY.getStrval());
			en.update(plaintext.getBytes());
			String pass = (new BASE64Encoder()).encodeBuffer(en.digest());
			return pass.trim();
		} catch (NoSuchAlgorithmException e) {
			log.log(Level.SEVERE, "加密出错.", e);
		}

		return null;
	}

	/**
	 * 判断明文加密之后与密码是否匹配,
	 * 
	 * @param plaintext
	 *          明文
	 * @param passwd
	 *          密码
	 * @return 匹配返回true,不匹配返回false
	 */
	public boolean matchPasswd(String plaintext, String passwd) {
		return encrypt(plaintext).equals(passwd);
	}

	/**
	 * 返回一个数据库操作对象,此对象可对数据库进行读/写等操作.
	 * 
	 * @return
	 */
	public DBOperation getDBOperation() {
		return dbo;
	}
}
