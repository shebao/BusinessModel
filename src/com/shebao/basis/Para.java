package com.shebao.basis;


/**
 * 此枚举保存程序初始化的参数值,
 * @author yimin
 *
 */
public enum Para implements BaseObject{
	LogLevel("logLevel","0"),
	RealPath("realPath",System.getProperty("user.home") + "/shebao"),
	LogFileName("LogFilePath","log%g.log"),
	DBConnectionDriver("dbConnectionDriver","localhost"),
	DBConnectionPort("dbConnectionPort","5432"),
	DBConnectionName("dbConnectionName","postgres"),
	DBDataBaseName("dbDatabaseName","shebaofq"),
	DBConnetionPassword("dbConnectionPassword","postgres"),
	DBPoolConnNumber("dbPoolConnNumber","20"),
	IsTestEnvironment("testEnvironment","N");
	
	private final String paraName;
	private String paraVal;
	private boolean oneSetVal = true;
	
	private Para(String name,String defaultVal){
		this.paraName = name;
		this.paraVal = defaultVal;
	}

	public final String getVal() {
		return paraVal;
	}
	
	public final String getParaName(){
		return paraName;
	}
	
	public final void setParaVal(String val){
		if (oneSetVal){
			this.paraVal = val;
			oneSetVal = false;
			
			switch (this) {
			case RealPath:
				LogFileName.paraVal = this.paraVal + "log/log%g.log";
				break;
			default:
				break;
			}
		}
	}
	
	@Override
	public final String toString() {
		return paraName + "=" + paraVal;
	}
}
