package com.shebao.basis.database;

import java.util.Date;

import com.shebao.basis.BaseObject;

/**
 * 数据库操作字段处理类,将输入的值处理成数据库相对应的结果.<br>
 * 或者将数据库输出的值处理成对象需要的样子!<br><br>
 * 由于所有数据库接受的都是字符串,因此将所有的输入都使用字符串返回.
 * <br>
 * 所有数据库传来的都是字符,所以从数据库得到需要转换的都采用String格式<br>
 * 不需要转换的可以不通过此对象..
 * <br>
 * 
 * 所有字符串输入需要通过此对象转换之后进入数据库.一防止注入,二防止出现逃逸字符.
 * @author yimin
 *
 */

public abstract class DBUtility implements BaseObject{

	/**
	 * 将一个字符串处理成数据库程序接受的字符串
	 * @param str
	 * @return 返回一个数据库接受的值
	 */
	public abstract String inputStr(String str);
	
	/**
	 * 将一个字符串处理成数据库程序接受的字符串,直接调用参数的toString方法
	 * @param obj
	 * @return 返回一个数据库接受的值
	 */
	public abstract String inputStr(Object obj);
	
	/**
	 * 将一个日期对象处理成数据库程序接受的字符串
	 * @param date
	 * @return 返回一个数据库接受的值
	 */
	public abstract String inputDate(Date date);
	
	/**
	 * 将一个数据库输出的值转换成Date
	 * @param str
	 * @return
	 */
	public abstract Date outputDate(String str);
	
	/**
	 * 输入一个bool型值,转换成数据库接受的bool型
	 * @param bool
	 * @return
	 */
	public abstract String inputBool(boolean bool);

	
	/**
	 * 将一个string值转换成Bool类型
	 * @param str
	 * @return
	 */
	public abstract boolean outputBool(String str);
	
}
