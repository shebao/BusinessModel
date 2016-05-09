package com.shebao.model.custom;

import java.util.List;
import com.shebao.basis.BaseObject;

/**
 * 用户收货地址类
 * 
 * @author Administrator
 *
 */
public class CustomAddress implements BaseObject{
	private int id;
	
	
	public void flash() {
		// TODO Auto-generated method stub
		log.fine("abcdefg");
	}

	public static void addAddress() {
		// TODO Auto-generated method stub
		
	}

	public static void editAddress() {
		// TODO Auto-generated method stub
		
	}

	public static void setDefaultAddress() {
		// TODO Auto-generated method stub
		
	}

	public static void setSortNumUp() {
		// TODO Auto-generated method stub
		
	}

	public static void setSortNumDown() {
		// TODO Auto-generated method stub
		
	}

	public static void deleteAddress() {
		// TODO Auto-generated method stub
		
	}


	/**
	 * 返回客户的所有的地址信息
	 * @param id
	 * @return
	 */
	public static List<CustomAddress> getAddress(int customId) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public int getId() {
		return id;
	}

}
