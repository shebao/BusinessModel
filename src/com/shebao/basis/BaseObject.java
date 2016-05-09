package com.shebao.basis;

import java.util.logging.Logger;

import com.shebao.basis.database.DBOperation;
import com.shebao.basis.database.DBUtility;

/**
 * 基本的一些常量对象,实现此接口的对象将可以直接使用常用的对象<br>
 * 此接口是为了方便而设计
 * @author yimin
 *
 */
public interface BaseObject{
	Base base = Base.getIns();
	Logger log = base.getLogger();
	DBOperation dbOperation = base.getDBOperation();
	DBUtility dbu = base.getdBUtility();
}
