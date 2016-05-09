package com.shebao.basis.database;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import static com.shebao.basis.Const.NULL;

/**
 * 数据库字符转换安全对象.此对象针对PGsql数据库<br>
 * 此类线程安全
 * 
 * @author yimin
 *
 */
public class PGDBUtility extends DBUtility{
	private final String addition =  "$";
	private final SimpleDateFormat sm = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	public PGDBUtility() {
	}

	@Override
	public String inputStr(String str) {
		String both = "$$";
		
		if(str == null) {
			return NULL.getStrval();
		}
		
		// 找到一个在字符串当中没有出现过的附加字串.
		for (int i = 97; i < 123; i++) {
			if (str.indexOf(both) == -1) {
				break;
			}
			char c = (char) i;

			both = addition + c + addition;
		}

		return both + str + both;
	}
	
	
	@Override
	public String inputStr(Object obj) {
		if(obj == null){
			return NULL.getStrval();
		}
		
		return inputStr(obj.toString());
	}

	@Override
	public synchronized String inputDate(Date date) {
		if (date == null){
			return NULL.getStrval();
		}
		
		return inputStr(sm.format(date));
	}


	@Override
	public synchronized Date outputDate(String str) {
		if (str == null || str.equals("")){
			return null;
		}

		try {
			return sm.parse(str);
		} catch (Exception e) {
			log.log(Level.SEVERE, "从数据库解析一个日期出现异常,要解析的字串:" + str, e);
		}

		return null;
	}
}
