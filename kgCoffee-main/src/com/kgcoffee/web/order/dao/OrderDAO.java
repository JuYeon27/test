package com.kgcoffee.web.order.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.kgcoffee.web.common.DBConn;
import com.kgcoffee.web.common.LoggableStatement;
import com.kgcoffee.web.common.Paging;
import com.kgcoffee.web.order.domain.OrderVO;
import com.kgcoffee.web.order.domain.PaymentsVO;

public class OrderDAO {

	private Connection con;
	private PreparedStatement pst = null;
	private ResultSet rs = null;
	String sql = "";

	public OrderDAO() {
		// TODO Auto-generated constructor stub

		this.con = DBConn.getInstance().getCon();

	}

	public boolean insertOrder(OrderVO order) {

		boolean result = false;

		sql = "insert into order_table(order_id, user_id, map_id, order_price, order_date, imp_uid) values(order_seq.nextval,?,?,?,?,?)";
		try {

			long ms = order.getOrderDate().getTime();
			java.sql.Date sqlDate = new java.sql.Date(ms);

			pst = new LoggableStatement(con, sql);

			pst.setString(1, order.getUserId());
			pst.setInt(2, order.getMapId());
			pst.setInt(3, order.getTotalPrice());

			pst.setDate(4, sqlDate);
			pst.setString(5,order.getImpUid());
			
			
			System.out.println(((LoggableStatement) pst).getQueryString());
			if (pst.executeUpdate() >= 1) {
				result = true;
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return result;

	}

	public int getOrderSeq() {

		int result = 0;

	
		sql = "select order_seq.currval cnt from dual";

		try {

			pst = con.prepareStatement(sql);


			rs = pst.executeQuery();

			if (rs.next()) {

				result = rs.getInt("cnt");

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return result;

	}

	public boolean insertPayments(PaymentsVO vo) {

		boolean result = false;

		sql = "insert into payments_table (payments_id, user_id, order_id, menu_id, menu_amount) values(payments_seq.nextval,?,?,?,?)";
		try {

			String userId = vo.getUserId();
			int menuId = vo.getMenuId();
			int orderId = vo.getOrderId();
			int menuAmount = vo.getMenuAmount();

			pst = new LoggableStatement(con, sql);

			pst.setString(1, userId);
			pst.setInt(2, orderId);
			pst.setInt(3, menuId);
			pst.setInt(4, menuAmount);

			System.out.println(((LoggableStatement) pst).getQueryString());
			if (pst.executeUpdate() >= 1) {
				result = true;
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return result;

	}
	
	
	public List<PaymentsVO> findPayments(Map<String, Object> keyMap) {

		List<PaymentsVO> paymentsList = new ArrayList<PaymentsVO>();

		String type = (String) keyMap.get("type");
		String value = "%" + keyMap.get("value") + "%";



		String sql = "select * from payments_table"
				+ " inner join MENU ON   payments_table.menu_id = menu.menuId "
				+ " where payments_table." + type + " like ? ";

		
		

		try {

			pst = new LoggableStatement(con, sql);

			pst.setString(1, value);


			System.out.println(((LoggableStatement) pst).getQueryString());
			rs = pst.executeQuery();

			while (rs.next()) {

				PaymentsVO payments = new PaymentsVO();
				payments.setUserId(rs.getString("user_id"));
				payments.setOrderId(rs.getInt("order_id"));
				payments.setpPaymentsId(rs.getInt("payments_id"));
				payments.setMenuAmount(rs.getInt("menu_amount"));
                payments.setMenuId(rs.getInt("menu_id"));
                payments.setFileName(rs.getString("imgurl"));
                payments.setCaffeineType(rs.getString("caffeineType"));
                payments.setMenuName(rs.getString("menuName"));
                payments.setMenuPrice(rs.getInt("menuPrice"));
                payments.setMenuType(rs.getString("menuExplain"));
                payments.setMenuExplain(rs.getString("menuType"));
                paymentsList.add(payments);
                
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return paymentsList;

	}

	public List<OrderVO> findOrder(Map<String, Object> keyMap, Paging paging) {

		List<OrderVO> orderList = new ArrayList<OrderVO>();

		String type = (String) keyMap.get("type");
		String value = "%" + keyMap.get("value") + "%";

		String type2 = "";

		if (value == "") {
			type2 = type + " ASC";
		} else {
			type2 = "order_id DESC";
		}

		String sql = "select * from "
				+ "(select * from "
				+ "(select A. *, ROW_NUMBER() over(order by " + type2 + ") as num "
				+ "    from (select * from order_table where " + type + " like ?) A) " 
				+ "    where num between ? and ?) B "
						+ " inner join map_table C on B.map_id = C.map_id ";

		int page = paging.getPage();
		int amount = paging.getDisplayRow();

		try {

			pst = new LoggableStatement(con, sql);

			pst.setString(1, value);

			pst.setInt(2, (page * amount) - amount + 1);
			pst.setInt(3, page * amount);
			System.out.println(((LoggableStatement) pst).getQueryString());
			rs = pst.executeQuery();

			while (rs.next()) {

				OrderVO order = new OrderVO();

				order.setOrderId(rs.getInt("order_id"));
				order.setUserId(rs.getString("user_id"));
				order.setMapId(rs.getInt("map_id"));
				order.setTotalPrice(rs.getInt("order_price"));
				order.setOrderDate(rs.getDate("order_date"));
				order.setPlaceName(rs.getString("place_name"));
				order.setImpUid(rs.getString("imp_uid"));
				System.out.println(order); 
				orderList.add(order);

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return orderList;

	}

	public int findTotalCnt(Map<String, Object> keyMap) {

		int result = 0;

		String type = (String) keyMap.get("type");
		String value = "%" + keyMap.get("value") + "%";

		sql = "select count(*) cnt from order_table where " + type + " like ?";

		try {

			pst = con.prepareStatement(sql);

			pst.setString(1, value);

			rs = pst.executeQuery();

			if (rs.next()) {

				result = rs.getInt("cnt");

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return result;

	}
	
	
	public boolean deleteOrder(String impUid) {

		boolean result = false;

		sql = "delete order_table where imp_uid = ?";
		try {

	
			pst = new LoggableStatement(con, sql);

			pst.setString(1, impUid);
		

			System.out.println(((LoggableStatement) pst).getQueryString());
			if (pst.executeUpdate() >= 1) {
				result = true;
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return result;

	}
	
	

}
