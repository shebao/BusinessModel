package com.shebao.basis.database;

import static com.shebao.basis.Const.NULL;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;

/**
 * 数据库字符转换安全对象.此对象针对PGsql数据库<br>
 * 此类线程安全
 *
 * @author yimin
 *
 */
public final class PGDBUtility extends DBUtility{
	private final String addition = "$";
	private final SimpleDateFormat sm = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	public PGDBUtility() {
	}

	@Override
	public String inputBool(final boolean bool) {
		return bool?"true":"false";
	}

	@Override
	public synchronized String inputDate(final Date date) {
		if (date == null) {
			return NULL.getStrval();
		}

		return this.inputStr(this.sm.format(date));
	}

	@Override
	public String inputStr(final Object obj) {
		if (obj == null) {
			return NULL.getStrval();
		}

		return this.inputStr(obj.toString());
	}

	@Override
	public String inputStr(final String str) {
		String both = "$$";

		if (str == null) {
			return NULL.getStrval();
		}

		// 找到一个在字符串当中没有出现过的附加字串.
		for (int i = 97; i < 123; i++) {
			if (str.indexOf(both) == -1) {
				break;
			}
			final char c = (char) i;

			both = this.addition + c + this.addition;
		}

		return both + str + both;
	}

	@Override
	public synchronized Date outputDate(final String str) {
		if ((str == null) || str.equals("")) {
			return null;
		}

		try {
			return this.sm.parse(str);
		} catch (final Exception e) {
			log.log(Level.SEVERE, "从数据库解析一个日期出现异常,要解析的字串:" + str, e);
		}

		return null;
	}

	@Override
	public boolean outputBool(String str) {
		return str.toLowerCase().equals("true") || str.equals("1");
	}
}
