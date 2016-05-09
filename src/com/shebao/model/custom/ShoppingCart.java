package com.shebao.model.custom;

import com.shebao.basis.BaseObject;

/**
 * 购物车,购物车每一个用户对象对应一个购物车对象.因此此对象直接在用户对象当中引用<br>
 * 应用增加/修改/删除方法为购物车内的商品进行调整.<br>
 * 每一次购物车商品进行变化后,将对购物车进行重新计价.根据当前活动会重新计算购物车金额<br>
 * 有商品可能对应多个活动,因此需要将活动的id带入,如是无活动商品.可带入0.<br>
 * 赠品同样在购物车内显示,只是赠品的flag标志将是Constant.IsGift.
 * 
 * @author yimin
 *
 */
public class ShoppingCart implements BaseObject{
	/**
	 * 购物车内商品记录数对象.
	 * 
	 * @author yimin
	 *
	 */
//	public class ShoppingCartItem{
//		public Product product;
//		public int number;
//		public double price;
//		public double totalPrice;
//		public int flag;
//		public int saleActivityId;
//		// 在内存当中存在的返还金额和返还月份,因每一次读出来都需要单独去计算.所以不用写入数据库
//		public double RebateMoney = 0;
//		public int RebateMonth = 0;
//		public boolean selected = true;
//
//		private ShoppingCartItem(Product product, int number, double price, double totalPrice, int saleActivityId,
//				int flag) {
//			this.product = product;
//			this.number = number;
//			this.price = price;
//			this.totalPrice = totalPrice;
//			this.saleActivityId = saleActivityId;
//			this.flag = flag;
//
//			log.finest(String.format("%s,购物车新增加一个商品,商品:%s,数量:%s,单价:%.2f,总金额:%.2f", custom,
//					product.getProperty(FieldName.ProductName), number, price, totalPrice));
//		}
//	}
//
//	// 存在购物车内商品和数量的列表
//	private List<ShoppingCartItem> items = new LinkedList<ShoppingCartItem>();
//	private Custom custom;
//	private double sumPrice = 0;
//	private String viewStr = null;
//
//	/**
//	 * 立即购买时产生的临时购物车,此购物车仅使用一次.并且不写入数据持久层中.
//	 * 
//	 * @param custom
//	 * @param product
//	 * @param number
//	 */
//	public ShoppingCart(Custom custom, Product product, int number) {
//		this.custom = custom;
//		double price = (double) product.getProperty(FieldName.Price);
//		
//		items.add(new ShoppingCartItem(product, number, price, number * price, 0, Constant.IsNormal));
//	}
//
//	/**
//	 * 将客户的购物车对象读出,并构造购物车对象
//	 * 
//	 * @param custom
//	 */
//	public ShoppingCart(Custom custom) {
//		this.custom = custom;
//
//		// 读数据库,将购物车内相应记录读出
//		String sql = "SELECT * from shopping_cart  where flag > -1 and custom_id = " + custom.getId();
//
//		log.fine(custom + "购物车读出.");
//		try {
//			ResultSet rs = DBOperation.runSelReturnRS(sql);
//
//			ShoppingCartItem si;
//			while (rs.next()) {
//				si = new ShoppingCartItem(Product.getProduct(rs.getInt(FieldName.ReferenceProductId)),
//						rs.getInt(FieldName.Number), rs.getDouble(FieldName.Price), rs.getDouble(FieldName.TotalPrice),
//						rs.getInt(FieldName.SaleActivityId), rs.getInt(FieldName.Flag));
//
//				items.add(si);
//			}
//
//			DBOperation.closeResource(rs);
//		} catch (Exception e) {
//			log.log(Level.SEVERE, custom + "购物车读出现异常,需要执行的sql:" + sql + "\n\n", e);
//		}
//
//		sumPriceAgain();
//	}
//
//	public ShoppingCartItem get(int index) {
//		return items.get(index);
//	}
//
//	@Override
//	public void flash() {
//		String[] sqls = new String[items.size() + 1];
//		int i = 0;
//		sqls[i] = "delete from shopping_cart where custom_id = " + custom.getId() + ";";
//		i++;
//
//		for (ShoppingCartItem p : items) {
//			sqls[i] = String.format(
//					"INSERT INTO shopping_cart(custom_id, price, number, total_price,product_id,flag,sale_activity_id) values(%d,%.2f,%d,%.2f,%d,%d,%d)",
//					custom.getId(), p.price, p.number, p.totalPrice, p.product.getId(), p.flag, p.saleActivityId);
//			i++;
//		}
//
//		DBOperation.runBatchSql(sqls);
//
//		log.finer("修改购物车内商品,写入持久层完成:" + custom.getId());
//	}
//
//	/**
//	 * 此对象内有多个记录集,因此不适用此方法
//	 * 
//	 * @param PropertyName
//	 * @return
//	 */
//	@Override
//	public Object getProperty(String PropertyName) {
//		log.severe("此对象不适用此请求");
//		return null;
//	}
//
//	/**
//	 * 得到购物车内商品的记录数.即购物车内存在商品数量
//	 * 
//	 * @return
//	 */
//	public int size() {
//		return items.size();
//	}
//
//	@Override
//	public void setProperty(String PropertyName, Object PropertyValue) {
//		log.severe("此对象不适用此请求");
//	}
//
//	/**
//	 * 为购物车新增加一个商品,每一个商品为唯一一条记录,如商品已经存在,将追加数量.<br>
//	 * 此处的单价和总金额都将是参考值,最终写入购物车的单价和总金额将根据活动情况等进行调整后写入.
//	 * 
//	 * @param product
//	 *          商品id
//	 * @param number
//	 *          需要增加的数量
//	 * @param price
//	 *          商品单价
//	 * @param totalPrice
//	 *          商品总价
//	 * @param flag
//	 *          标志
//	 * @return 成功:true,失败:false 如果数量小于等于0,将返回失败.或者找不到此商品id对应的商品.
//	 */
//	public boolean addProduct(Product product, int number, double price, double totalPrice, int saleActivity, int flag) {
//		if (number <= 0) {
//			log.warning("商品的数量不能小于等于0.");
//			return false;
//		}
//
//		if (product == null) {
//			log.warning("要增加的商品对象未找到.id:" + product);
//			return false;
//		}
//
//		for (ShoppingCartItem item : items) {
//			if (item.product == product) {
//				item.number += number;
//				log.fine(String.format("%s,购物车存在相同商品,商品:%d,增加数量:%d", custom, product, number));
//				return true;
//			}
//		}
//
//		items.add(new ShoppingCartItem(product, number, price, totalPrice, saleActivity, flag));
//		log.fine(String.format("%s,购物车新增加商品,商品:%d,增加数量:%d", custom, product, number));
//
//		viewStr = null;
//		custom.calculateShoppingCartMoney();
//		return true;
//	}
//
//	/**
//	 * 为购物车内商品修改数量,不能修改为0.<br>
//	 * 修改后将有可能调整购物车内其他商品的金额和数量,因此需要重新显示整个购物车.<br>
//	 * 
//	 * @param product
//	 *          商品id
//	 * @param step
//	 *          需要步进的数量,增加或者减少,增加用正数,减少用负数
//	 * @return true:成功,
//	 */
//	public boolean decProduct(int product, int step) {
//		for (ShoppingCartItem item : items) {
//			if (item.product.getId() == product) {
//				if ((item.number += step) <= 0) {
//					log.warning(String.format("%s,购物车内商品数量小于要修改的数据,商品:%d,修改数量:%d,现商品数量", custom, product, step, item.number));
//					return false;
//				}
//
//				item.number += step;
//				log.fine(String.format("%s,购物车内商品数量修改,商品:%d,修改数量:%d,修改后数量:%d", custom, product, step, item.number));
//
//				viewStr = null;
//				custom.calculateShoppingCartMoney();
//				return true;
//			}
//		}
//
//		log.warning("要修改的商品对象未在购物车内,id:" + product);
//		return false;
//	}
//
//	/**
//	 * 将一个商品从购物车内移除.
//	 * 
//	 * @param productId
//	 *          要移除的商品id
//	 * @return true:成功,false:失败.未找到对应的商品
//	 */
//	public boolean removeProduct(int productId) {
//		ShoppingCartItem it = null;
//		for (ShoppingCartItem item : items) {
//			if (item.product.getId() == productId) {
//				it = item;
//				break;
//			}
//		}
//
//		if (it != null) {
//			items.remove(it);
//			log.fine(String.format("%s,购物车内商品移除,商品:%d", custom, productId));
//
//			viewStr = null;
//			custom.calculateShoppingCartMoney();
//			return true;
//		}
//
//		log.warning("要移除的商品对象未在购物车内,id:" + productId);
//		return false;
//	}
//
//	/**
//	 * 获得购物车内所有商品的总金额
//	 * 
//	 * @return
//	 */
//	public double getSumPrice() {
//		return sumPrice;
//	}
//
//	/**
//	 * 重新计算购物车内所有商品总金额
//	 */
//	public void sumPriceAgain() {
//		sumPrice = 0;
//		for (int i = 0; i < items.size(); i++) {
//			sumPrice += items.get(i).totalPrice;
//		}
//		viewStr = null;
//	}
//
//	/**
//	 * 清除所有的赠品
//	 */
//	public void removeGift() {
//		List<ShoppingCartItem> its = new ArrayList<>();
//
//		for (ShoppingCartItem item : items) {
//			if (item.flag == Constant.IsGift) {
//				its.add(item);
//				break;
//			}
//		}
//
//		if (!its.isEmpty()) {
//			for (ShoppingCartItem it : its) {
//				items.remove(it);
//				log.fine(String.format("%s,购物车内移除赠品,商品:%d,赠品: %d", custom, it.product.getId()));
//			}
//
//		}
//
//		viewStr = null;
//	}
//
//	/**
//	 * 判断购物车内是否为空.
//	 * 
//	 * @return
//	 */
//	public boolean isEmpty() {
//		return items.isEmpty();
//	}
//
//	/**
//	 * 返回以json格式的显示字符串.用来在前台显示
//	 * 
//	 * @return
//	 */
//	public String view() {
//		if (viewStr == null) {
//			// TODO 返回以json格式的显示字符串.用来在前台显示
//		}
//
//		return viewStr;
//	}
//
//	/**
//	 * 清除购物车内所有数据
//	 */
//	public void clear() {
//		items = new LinkedList<>();
//		viewStr = null;
//		sumPrice = 0;
//	}
//
//	/**
//	 * 当已经将购物车商品转成销售订单时调用此方法,将移除购物车内所有选中的商品.
//	 */
//	public void removeComplete() {
//		List<ShoppingCartItem> temp = new LinkedList<>();
//
//		for (ShoppingCartItem item : items) {
//			if (!item.selected) {
//				temp.add(item);
//			}
//		}
//
//		items = temp;
//	}
//	
//	public Custom getCustom() {
//		return custom;
//	}
}
