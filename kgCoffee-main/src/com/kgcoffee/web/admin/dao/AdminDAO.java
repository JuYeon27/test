package com.kgcoffee.web.admin.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import com.kgcoffee.web.admin.DTO.ReportMapDTO;
import com.kgcoffee.web.admin.DTO.ReportMenuDTO;
import com.kgcoffee.web.common.DBConn;
import com.kgcoffee.web.common.LoggableStatement;

public class AdminDAO {

	private Connection con = null;
	private String sql = "";
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;

	private static AdminDAO dao = new AdminDAO();

	public static AdminDAO getInstance() {
		return dao;
	}

	private AdminDAO() {
		// TODO Auto-generated constructor stub
		try {
			con = DBConn.getInstance().getCon();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public Map<String, Object> findOrderByMenu(Map<String, String> keyMap) {

		Map<String, Object> resMap = new HashMap<String, Object>();

		
		String menuName = keyMap.get("menuName");
		String dateType = keyMap.get("dateType");
		String dateValue = "%" + keyMap.get("dateValue") + "%";
		int page =  Integer.parseInt(keyMap.get("page"));
		int amount = Integer.parseInt(keyMap.get("amount"));

		String temp = "";

		if (menuName!=""&&menuName!=null) {

			temp = " where menuName like ?";

		}

		sql = "select * from\r\n"
				+ "(select row_number() over(order by 날짜 desc, 주문량 desc) rn, E.* from "
				+ "		(select C.menu_id, C.주문량, C.날짜,  D.* from   "
				+ "			(select B.menu_id, sum(B.menu_amount) 주문량, A.날짜 from       "
				+ "				(select order_id, TO_CHAR(order_date, ?) as 날짜 from order_table)A    	"
				+ "			inner join payments_table B on A.order_id = B.order_id where "
				+ "			 (날짜 like ? or 날짜 IS NULL) group by 날짜, menu_id)C      "
				+ "		inner join menu D on C.menu_id = D.menuId " +temp
				+ "		order by 날짜 desc, 주문량 desc) E) where rn between ? and ?";

		try {

			pstmt = new LoggableStatement(con, sql);


			int i = 3;
			pstmt.setString(1, dateType);
			pstmt.setString(2, dateValue);
			if (menuName!=""&&menuName!=null) {

				pstmt.setString(i, "%"+menuName+"%");
				i++;

			}
			pstmt.setInt(i, (page - 1) * amount + 1);
			pstmt.setInt(i + 1, page * amount);

			System.out.println(((LoggableStatement) pstmt).getQueryString());
			List<ReportMenuDTO> menuDTOList = null;

			Set<String> keySet = new HashSet<String>();
			
			rs=pstmt.executeQuery();
			
			while (rs.next()) {
				ReportMenuDTO dto = new ReportMenuDTO();

				String orderDate = rs.getString("날짜");
			
				
				keySet.add(orderDate);
				dto.setOrderDate(orderDate);
				dto.setOrderAmount(rs.getInt("주문량"));
				dto.setMenuId(rs.getInt("menu_id"));
				dto.setFileName(rs.getString("imgurl"));
				dto.setCaffeineType(rs.getString("caffeineType"));
				dto.setMenuName(rs.getString("menuName"));
				dto.setMenuPrice(rs.getInt("menuPrice"));
				dto.setMenuType(rs.getString("menuExplain"));
				dto.setMenuExplain(rs.getString("menuType"));

				
				if(resMap.containsKey(orderDate)) {
					
					menuDTOList = (List<ReportMenuDTO>) resMap.get(orderDate);
					
					menuDTOList.add(dto);
					
					
					
				}else {
					
					menuDTOList = new ArrayList<ReportMenuDTO>();
					menuDTOList.add(dto);
					resMap.put(orderDate, menuDTOList);
				}
				
				
				
			}
			
			
			List<String> keyNames = new ArrayList<String>(keySet);
			
			resMap.put("keyNames", keyNames);
			

			return resMap;

		} catch (Exception e) {
			e.printStackTrace();
			
		}
		return null;

	}
	
	
	
	public Map<String, Object> findOrderByAgeGroup(Map<String, String> keyMap) {

		Map<String, Object> resMap = new HashMap<String, Object>();

		String rank = keyMap.get("rank");
		String dateType =  keyMap.get("dateType");
		String dateValue = "%" +  keyMap.get("dateValue") + "%";
		int page =  Integer.parseInt(keyMap.get("page"));
		int amount = Integer.parseInt(keyMap.get("amount"));

		
		sql = "select * from"
				+ "(select ROW_NUMBER() over(order by 날짜 desc, age asc, sum(menu_amount) desc) rn, age, menu_id, sum(menu_amount) 주문량, 날짜, "
				+ "   ROW_NUMBER() over(partition by 날짜, age order by 날짜 desc, age asc, sum(menu_amount) desc) rank from"
				+ "       (select A.age, B.menu_id, B.menu_amount, 날짜 from "
				+ "        (select o.order_id, TO_CHAR(order_date, ?) as 날짜, floor((to_char(sysdate, 'YYYY') - extract(year from u.birthday)+1)/10 ) * 10 as age from "
				+ "            order_table o inner join users u on o.user_id = u.user_id)A "
				+ "        inner join payments_table B on A.order_id = B.order_id)C where (날짜 like ? or 날짜 IS NULL)  group by age, menu_id, 날짜) R, "
				+ "        menu m where R.menu_id = m.menuId and rank <= ? and r.rn between ? and ? order by rn";

		try {

			pstmt = new LoggableStatement(con, sql);


			
			pstmt.setString(1, dateType);
			pstmt.setString(2, dateValue);
			pstmt.setString(3, rank);
		
			pstmt.setInt(4, (page - 1) * amount + 1);
			pstmt.setInt(5, page * amount);

			System.out.println(((LoggableStatement) pstmt).getQueryString());
			List<ReportMenuDTO> menuDTOList = null;

			Set<String> keySet = new HashSet<String>();
			
			rs=pstmt.executeQuery();
			
			while (rs.next()) {
				ReportMenuDTO dto = new ReportMenuDTO();
				String ageGroup= rs.getString("AGE");
				
			
				
				String orderDate = rs.getString("날짜");
				
				keySet.add(ageGroup+orderDate);
				
				
				dto.setAgeGroup(ageGroup);
				
				dto.setOrderDate(rs.getString("날짜"));
				dto.setOrderAmount(rs.getInt("주문량"));
				dto.setMenuId(rs.getInt("menu_id"));
				dto.setFileName(rs.getString("imgurl"));
				dto.setCaffeineType(rs.getString("caffeineType"));
				dto.setMenuName(rs.getString("menuName"));
				dto.setMenuPrice(rs.getInt("menuPrice"));
				dto.setMenuType(rs.getString("menuExplain"));
				dto.setMenuExplain(rs.getString("menuType"));
				
			
				
				if(resMap.containsKey(ageGroup+orderDate)) {
					
					menuDTOList = (List<ReportMenuDTO>) resMap.get(ageGroup+orderDate);
					
					menuDTOList.add(dto);
					
					
					
				}else {
					
					menuDTOList = new ArrayList<ReportMenuDTO>();
					menuDTOList.add(dto);
					resMap.put(ageGroup+orderDate, menuDTOList);
				}
				
			}
			
			
			List<String> keyNames = new ArrayList<String>(keySet);
			
			Collections.sort(keyNames);
			resMap.put("keyNames", keyNames);
			

			return resMap;

		} catch (Exception e) {
			e.printStackTrace();
			
		}
		return null;

	}
	
	
	
	public HashMap<String, Object> findOrderByMap(Map<String, String> keyMap) {

		HashMap<String, Object> resMap = new HashMap<String, Object>();

		String keywords = (String) keyMap.get("keywords");
		String dateType = (String) keyMap.get("dateType");
		String dateValue = "%" + (String) keyMap.get("dateValue") + "%";
		int page =  Integer.parseInt(keyMap.get("page"));
		int amount = Integer.parseInt(keyMap.get("amount"));

		
		String temp = "where (b.road_address_name like ? or b.address_name like ? or b.place_name like ?) ";
		
		String[] keyword = keywords.split("\\s+");
		
		if(keyword.length>1) {
			
			for(int i=1; i<keyword.length; i++) {
				
				temp += " and (b.road_address_name like ? or b.address_name like ? b.place_name like ?) ";
					
			}
			
		}
		
		
		sql = "select * from "
				+ "    (select ROW_NUMBER() over(order by 날짜 desc, 주문량 desc) rn, A.주문량, a.map_id, a.날짜, B.place_name, B.road_address_name, b.address_name from"
				+ "        (select count(*) 주문량, map_id, 날짜 from  "
				+ "             (select map_id, TO_CHAR(order_date, ?) as 날짜 from order_table)  "
				+ "                where 날짜 like ? or 날짜 IS NULL group by 날짜, map_id  order by 날짜 desc, 주문량 desc) A "
				+ "    inner join map_table B on A.map_id=B.map_id " + temp + ")  "
				+ "    where rn between ? and ? ";

		try {

			pstmt = new LoggableStatement(con, sql);


			
			pstmt.setString(1, dateType);
			pstmt.setString(2, dateValue);
			
			for(int i=0; i<keyword.length; i++) {
				
				String key = "%"+keyword[i]+"%";
				pstmt.setString((3*i)+3, key);
				pstmt.setString((3*i)+4, key);
				pstmt.setString((3*i)+5, key);
				
			}
			

			pstmt.setInt(3*(keyword.length-1)+6, (page - 1) * amount + 1);
			pstmt.setInt(3*(keyword.length-1)+7, page * amount);

			List<ReportMapDTO> mapDTOList = null;

			System.out.println(((LoggableStatement) pstmt).getQueryString());
			Set<String> keySet = new HashSet<String>();
			
			rs=pstmt.executeQuery();
			
			
			while (rs.next()) {
				ReportMapDTO dto = new ReportMapDTO();

				String orderDate = rs.getString("날짜");
				
				keySet.add(orderDate);
				dto.setOrderDate(orderDate);
				dto.setOrderAmount(rs.getInt("주문량"));
				dto.setAddressName(rs.getString("address_name"));
				dto.setRoadaddressName(rs.getString("road_address_name"));
				dto.setMapId(rs.getInt("map_id"));
				dto.setPlaceName(rs.getString("place_name"));
				

				if(resMap.containsKey(orderDate)) {
					
					mapDTOList = (List<ReportMapDTO>) resMap.get(orderDate);
					
					mapDTOList.add(dto);
					
					
					
				}else {
					
					mapDTOList = new ArrayList<ReportMapDTO>();
					mapDTOList.add(dto);
					resMap.put(orderDate, mapDTOList);
				}
				
			}
			
			
			List<String> keyNames = new ArrayList<String>(keySet);
			
			resMap.put("keyNames", keyNames);
			

			return resMap;

		} catch (Exception e) {
			e.printStackTrace();
			
		}
		return null;

	}
	
	
	
	
	public int findOrderByMenuTotalCnt(Map<String, String> keyMap) {

		int cnt=0;

		String menuName = keyMap.get("menuName");
		String dateType = keyMap.get("dateType");
		String dateValue = "%" + keyMap.get("dateValue") + "%";
	

		String temp = "";

		if (menuName!="") {

			temp = " and menuName like ?";

		}

		sql = "select count(*) cnt from"
				+ " (select ROW_NUMBER() over(order by 날짜 desc, 주문량 desc) rn, C.menu_id, C.주문량, C.날짜,  D.* fromn"
				+ "    (select B.menu_id, sum(B.menu_amount) 주문량, A.날짜 from "
				+ "      (select order_id, TO_CHAR(order_date, ?) as 날짜 from order_table)A "
				+ "   	 inner join payments_table B on A.order_id = B.order_id where (날짜 like ? or 날짜 IS NULL) " + temp
				+ " group by 날짜, menu_id)C "
				+ "        inner join menu D on C.menu_id = D.menuId order by 날짜 desc, 주문량 desc)";

		try {

			pstmt = new LoggableStatement(con, sql);

			
			
			pstmt.setString(1, dateType);
			pstmt.setString(2, dateValue);
			if (menuName!="") {

				pstmt.setString(3, "%"+menuName+"%");
				

			}
			
			System.out.println(((LoggableStatement) pstmt).getQueryString());

			
			
			rs=pstmt.executeQuery();
			
		
			if (rs.next()) {
				
				cnt =rs.getInt("cnt");
				
			}
			
			
		
			

			return cnt;

		} catch (Exception e) {

			e.printStackTrace();
			
		}
		return cnt;

	}
	
	
	
	public int findOrderByAgeGroupTotalCnt(Map<String, String> keyMap) {
		int cnt=0;
	
		String rank = keyMap.get("rank");
		String dateType =  keyMap.get("dateType");
		String dateValue = "%" +  keyMap.get("dateValue") + "%";

		
		sql = "select count(*) cnt from"
				+ "(select ROW_NUMBER() over(order by 날짜 desc, age asc, sum(menu_amount) desc) rn, age, menu_id, sum(menu_amount) 주문량, 날짜, "
				+ "   ROW_NUMBER() over(partition by 날짜, age order by 날짜 desc, age asc, sum(menu_amount) desc) rank from"
				+ "       (select A.age, B.menu_id, B.menu_amount, 날짜 from "
				+ "        (select o.order_id, TO_CHAR(order_date, ?) as 날짜, floor((to_char(sysdate, 'YYYY') - extract(year from u.birthday))/10 +1) * 10 as age from "
				+ "            order_table o inner join users u on o.user_id = u.user_id)A "
				+ "        inner join payments_table B on A.order_id = B.order_id)C where 날짜 like ? or 날짜 IS NULL group by age, menu_id, 날짜) R, "
				+ "        menu m where R.menu_id = m.menuId and rank <= ?";

		try {

			pstmt = new LoggableStatement(con, sql);

			
			
			pstmt.setString(1, dateType);
			pstmt.setString(2, dateValue);
			pstmt.setString(3, rank);
			System.out.println(((LoggableStatement) pstmt).getQueryString());

			rs=pstmt.executeQuery();
			
			if (rs.next()) {
				
				cnt =rs.getInt("cnt");
				
			}
			
	
			

			return cnt;

		} catch (Exception e) {
			e.printStackTrace();
			
		}
		return cnt;

	}
	
	
	
	public int findOrderByMapTotalCnt(Map<String, String> keyMap) {

		int cnt=0;
		String keyword = (String) keyMap.get("keyword");
		String dateType = (String) keyMap.get("dateType");
		String dateValue = "%" + (String) keyMap.get("dateValue") + "%";


		
		sql = "select count(*) cnt from "
				+ "    (select ROW_NUMBER() over(order by 날짜 desc, 주문량 desc) rn, A.주문량, a.map_id, a.날짜, B.place_name, B.road_address_name, b.address_name fromn"
				+ "        (select count(*) 주문량, map_id, 날짜 from  "
				+ "             (select map_id, TO_CHAR(order_date, ?) as 날짜 from order_table)  "
				+ "                where 날짜 like ? or 날짜 IS NULL group by 날짜, map_id  order by 날짜 desc, 주문량 desc) A "
				+ "    inner join map_table B on A.map_id=B.map_id where b.road_address_name like ? or b.address_name like ?)  ";

		try {

			pstmt = new LoggableStatement(con, sql);


			
			pstmt.setString(1, dateType);
			pstmt.setString(2, dateValue);
			pstmt.setString(3, keyword);
			pstmt.setString(4, keyword);
	
			System.out.println(((LoggableStatement) pstmt).getQueryString());

			rs=pstmt.executeQuery();
			
			while (rs.next()) {
			
				cnt= rs.getInt("cnt");
			}
			
			
	

			return cnt;

		} catch (Exception e) {
			e.printStackTrace();
			
		}
		return cnt;

	}

}
