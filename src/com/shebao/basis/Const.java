package com.shebao.basis;

/**
 * 存放枚举常量
 * @author yimin
 *
 */
public enum Const {
	/**
	 * 成功状态
	 */
	Success(1,"Success"),
	Fali(0,"Fali"),
	ExistNull(-1,"ExistNull"),
	NotExistsUser(2,"NotExistsUser"),
	NotPermission(3,"NotPermission"),
	CustomName(0,"CustomName"),
	ActionId(-1,"ActionId"),
	/**
	 * 默认的Logger对象使用的名称
	 */
	LoggerName(-1,"/log/shebao_log%g.log"),
	/**
	 * 输出日志的格式字串
	 */
	RecodeFormat(-1,"%1$tm-%1$td %1$tT.%1$tL Thread:%7$s %2$s\n%4$s: %5$s%6$s\n"),
	/**
	 * 加密使用的密文格式
	 */
	KEY(-1,"SHA"),
	/**
	 * 空值时显示值
	 */
	NULL(-1,"NULL");
	
	private final int intval;
	private final String strval;
	
	private Const(int intval,String strval){
		this.intval = intval;
		this.strval = strval;
	}

	public String getStrval() {
		return strval;
	}
	
	public int getIntval() {
		return intval;
	}
	
	@Override
	public String toString() {
		return strval;
	}
}
