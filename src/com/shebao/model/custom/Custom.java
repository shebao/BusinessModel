package com.shebao.model.custom;

import java.sql.ResultSet;
import java.util.Date;
import java.util.Hashtable;
import java.util.Map;
import java.util.logging.Level;
import com.shebao.basis.BaseObject;
import com.shebao.basis.database.tables.TabField;

/**
 * 客户类
 * 
 * @author yimin<br>
 * 
 *         字段:<br>
 *         Name Code Data Type Length Precision Primary Foreign Key Mandatory
 *         <br>
 *         id id integer TRUE FALSE TRUE<br>
 *         登录名称 login_name varchar(30) 30 FALSE FALSE TRUE<br>
 *         手机号码 mobile varchar(20) 20 FALSE FALSE TRUE<br>
 *         登录密码 password varchar(100) 100 FALSE FALSE TRUE<br>
 *         支付密码 trade_password varchar(100) 100 FALSE FALSE FALSE<br>
 *         创建时间 create_time timestamp FALSE FALSE TRUE<br>
 *         最后登录时间 last_login_time timestamp FALSE FALSE FALSE<br>
 *         是否可用 enable boolean FALSE FALSE TRUE<br>
 *         真实名称 real_name varchar(20) 20 FALSE FALSE FALSE<br>
 *         性别 sex integer FALSE FALSE TRUE<br>
 *         身份证号 idcard varchar(20) 20 FALSE FALSE FALSE<br>
 *         所在地 seat TEXT FALSE FALSE FALSE<br>
 *         邮件地址 email TEXT FALSE FALSE FALSE<br>
 *         头像URL head_picture TEXT FALSE FALSE FALSE<br>
 *         推荐人 reg_recommended integer FALSE TRUE FALSE<br>
 */
public class Custom implements BaseObject{
	// 保存已经登录客户列表
	private static Map<String, Custom> customs = new Hashtable<String, Custom>();

	private int id;
	private String loginName;
	private String mobile;
	private String passwd;
	private String tradePasswd;
	private Date createTime;
	private Date lastLoginTime;
	private boolean enable;
	private String realName;
	private int sex;
	/** 身份证号码 */
	private String iDCard;
	private String seat;
	private String email;
	private String headPictureURL;
	/** 推荐人 */
	private int recommendedId; // 这个部分不能用推荐有,员工有可能离职

	// private List<CustomAddress> address;
	// private ShoppingCart shoppingCart;
	// private List<SaleOrder> saleOrders;

	/**
	 * 用户登录方法,用户的实例只能从登录方法,或者新增方法得到. 登录成功,返回客户的实例,登录失败,返回null
	 * 
	 * @param session
	 *          请求对应的页面的唯一标识
	 * @param userName
	 *          用户登录名
	 * @param passwd
	 *          用户密码
	 * @return
	 */
	public static Custom login(String userName, String passwd) {
		log.finest(String.format("用户登录方法调用:,name:%s,passwd:%s", userName, passwd));

		Custom ins = getCustom(userName);
		if (ins == null) {
			return null;
		}

		if (ins.verificationPassWD(passwd)) {
			return ins;
		} else {
			log.info("用户名或密码错误:用户名:" + userName + ",密码:" + passwd);
			return null;
		}
	}

	// TODO 使用守护线程,将不常用的客户对象清除.每天做一次,将所有用户清除出内存.或者每天重启一次程序.
	/**
	 * 客户退出登录操作
	 * 
	 * @return
	 */
	public boolean quit() {
		flash();

		// 维护list列表
		customs.remove(this);
		return true;
	}

	/**
	 * 
	 * 校验密码是否正确.
	 * 
	 * @param passwd
	 * @return
	 */
	public boolean verificationPassWD(String passwd) {
		return base.matchPasswd(passwd, this.passwd);
	}

	private Custom() {
	}

	/**
	 * 客户注册操作
	 * 
	 * @param map
	 *          参数集
	 * @return
	 */
	public Custom(String loginName, String passwd) {
		this();

		this.loginName = loginName;
		this.passwd = base.encrypt(passwd);

		// 创建一个新的客户的操作.执行sql语句写入数据当中.
		String sql = String.format("select addCustom(%s,%s);", loginName, this.passwd);

		this.id = dbOperation.runSelReturnOneIntValue(sql);

		log.finer("创建一个新的客户.id:" + id);
	}

	/**
	 * 通过登录名返回一个客户的对象,如果未找到,返回值为NULL
	 * 
	 * @param loginName
	 *          登录名
	 * @return
	 */
	public static Custom getCustom(String loginName) {
		// 找到已经存在对象,直接返回
		Custom ins = customs.get(loginName);
		if (ins != null) {
			return ins;
		}

		ins = new Custom();
		String sql = "SELECT * from custom  where enable = true and login_name = " + dbu.inputStr(loginName);

		try {
			ResultSet rs = dbOperation.runSelReturnRS(sql);
			// 如果没有结果,直接返回
			if (!rs.next()) {
				log.finest("未找到对应的登录名称.返回NULL");
				return null;
			}

			ins.id = rs.getInt(TabField.ID);
			ins.loginName = rs.getString(TabField.LonginName);
			ins.mobile = rs.getString(TabField.Mobile);
			ins.passwd = rs.getString(TabField.PassWD);
			ins.tradePasswd = rs.getString(TabField.TradePassWD);
			ins.createTime = rs.getDate(TabField.CreateTime);
			ins.lastLoginTime = rs.getDate(TabField.LastLoginTime);
			ins.enable = rs.getBoolean(TabField.Enable);
			ins.realName = rs.getString(TabField.RealName);
			ins.sex = rs.getInt(TabField.Sex);
			ins.iDCard = rs.getString(TabField.IDCard);
			ins.seat = rs.getString(TabField.Seat);
			ins.email = rs.getString(TabField.EMail);
			ins.headPictureURL = rs.getString(TabField.HeadPicture);
			ins.recommendedId = rs.getInt(TabField.RegRecommended);

			// 将用户对象放入到用户对象表当中
			customs.put(loginName, ins);

			dbOperation.closeResource(rs);

			ins.initCustom();
			return ins;
		} catch (Exception e) {
			log.log(Level.SEVERE, "赋值出现异常,需要执行的sql:" + sql + "\n", e);
		}

		return null;
	}

	/**
	 * 客户的初始化操作,完成一个客户从数据库当中读出后的初始化. 如第一次创建,不需要调用此方法.
	 */
	private void initCustom() {
		// shoppingCart = new ShoppingCart(this);
		// address = CustomAddress.getAddress(id);
		// saleOrders = SaleOrder.getOrders(this);
	}

	/**
	 * 注册一个用户,如果失败返回NULL
	 * 
	 * @param loginName
	 *          用户名
	 * @param passwd
	 *          用户密码
	 * @return 用户名已经存在 返回NULL,其他返回对象.
	 */
	public static Custom register(String loginName, String passwd) {
		if (exists(loginName)) {
			return null;
		}

		Custom c = new Custom(loginName, passwd);

		customs.put(loginName, c);
		log.finest("成功加载客户对象:" + c);
		return c;
	}

	/**
	 * 判断用户登录名是否已经存在
	 * 
	 * @return true 用户名已经存在,false:用户名不存在
	 */
	public static boolean exists(String loginName) {
		return dbOperation.runSelReturnOneIntValue("select existsCusLoginName('" + loginName + "'')") == 1;
	}

	/**
	 * 将数据写入数据库持久层.
	 */
	public void flash() {
		String sql = String.format(
				"UPDATE custom SET email = %s,enable = %s,head_picture = %s,idcard = %s,last_login_time = %s,mobile = %s,password = %s,"
						+ "real_name = %s,seat = %s,sex = %d,trade_password = %s,reg_recommended = %d where id = %d",
				email, enable ? "true" : "false", headPictureURL, iDCard, dbu.inputDate(lastLoginTime), mobile, passwd,
				realName, seat, sex, tradePasswd, recommendedId, id);

		// for (CustomAddress add : address) {
		// add.flash();
		// }
		//
		// shoppingCart.flash();
		//
		// for (SaleOrder order : saleOrders) {
		// order.flash();
		// }

		dbOperation.runUpdate(sql);
		log.finer("修改用户入数据库完成:用户:" + this);
	}

	public int getId() {
		return id;
	}

	@Override
	public String toString() {
		return String.format("ID:%d,登录名:%s,手机号码:%s,真实姓名:%s", id, loginName, mobile, realName);
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getPasswd() {
		return passwd;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}

	public String getTradePasswd() {
		return tradePasswd;
	}

	public void setTradePasswd(String tradePasswd) {
		this.tradePasswd = tradePasswd;
	}

	public boolean isEnable() {
		return enable;
	}

	public void setEnable(boolean enable) {
		this.enable = enable;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public int getSex() {
		return sex;
	}

	public void setSex(int sex) {
		this.sex = sex;
	}

	public String getiDCard() {
		return iDCard;
	}

	public void setiDCard(String iDCard) {
		this.iDCard = iDCard;
	}

	public String getSeat() {
		return seat;
	}

	public void setSeat(String seat) {
		this.seat = seat;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getHeadPictureURL() {
		return headPictureURL;
	}

	public void setHeadPictureURL(String headPictureURL) {
		this.headPictureURL = headPictureURL;
	}

	public String getLoginName() {
		return loginName;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public Date getLastLoginTime() {
		return lastLoginTime;
	}

	public int getRecommendedId() {
		return recommendedId;
	}

}
