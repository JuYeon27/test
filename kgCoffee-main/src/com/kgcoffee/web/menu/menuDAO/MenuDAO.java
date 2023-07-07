package com.kgcoffee.web.menu.menuDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.kgcoffee.web.common.DBConn;
import com.kgcoffee.web.common.LoggableStatement;
import com.kgcoffee.web.menu.menuVO.MenuVO;

public class MenuDAO {
	
	private Connection con;
	
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	public MenuDAO() {
	
		try {
		con = DBConn.getInstance().getCon();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	public boolean insertMenu(String fileName, String caffeineType, String menuName, int menuPrice,
			String menuExplain, String menuType) {
		
		String sql = "insert into Menu VALUES(menu_sequencel.nextval,?,?,"
				+ "?, ?, ?, ?)";
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, fileName);
			pstmt.setString(2, caffeineType);
			pstmt.setString(3, menuName);
			pstmt.setInt(4, menuPrice);
			pstmt.setString(5, menuExplain);
			pstmt.setString(6, menuType);
			
			pstmt.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	
	
	
	/*
	 * public ArrayList<MenuVO> getMenuList() {
	 * 
	 * ArrayList<MenuVO> array = new ArrayList<MenuVO>();
	 * 
	 * String sql = "SELECT * FROM Menu ORDER BY menuId";
	 * 
	 * try { pstmt = con.prepareStatement(sql); rs = pstmt.executeQuery();
	 * 
	 * while(rs.next()){ int menuId = rs.getInt("menuId"); String fileName =
	 * rs.getString("imgurl");
	 * 
	 * String caffeineType = rs.getString("caffeineType"); String menuName =
	 * rs.getString("menuName"); int menuPrice = rs.getInt("menuPrice"); String
	 * menuExplain = rs.getString("menuExplain"); String menuType =
	 * rs.getString("menuType");
	 * 
	 * MenuVO mvo = new MenuVO(menuId, fileName, caffeineType, menuName, menuPrice,
	 * menuExplain, menuType);
	 * 
	 * array.add(mvo);
	 * 
	 * 
	 * }
	 * 
	 * return array;
	 * 
	 * }catch (Exception e) { // TODO Auto-generated catch block
	 * e.printStackTrace(); return null; }
	 * 
	 * }
	 */
	
	
	public int totalCnt(String menuAll, String caffeineType, String menuType, String menuName){
		String sql = "SELECT COUNT(*) cnt FROM Menu WHERE "
				+ "caffeineType like ? and menuType like ? "
				+ "and menuName like ? ";
		
		if(menuAll.equals("")&&caffeineType.equals("")&&menuType.equals("")&&menuName.equals("")) {
			return 0;
		}
		
		
		try {
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, "%"+caffeineType+"%");
			pstmt.setString(2,"%"+ menuType+"%");
			pstmt.setString(3,"%"+ menuName+"%");
			rs=pstmt.executeQuery();
			rs.next();
			int result =rs.getInt("cnt");
			
			
			System.out.println(result);
			
			return result;
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return 0;
		
	}
	
	/*
	 * public ArrayList<MenuVO> getMenuListByPage(int page, int amount){
	 * 
	 * String sql =
	 * "select * from (select A. *, ROW_NUMBER() over(order by menuId desc) as num"
	 * + "    from Menu A)" + "    where num between ? and ?"; ArrayList<MenuVO>
	 * array = new ArrayList<MenuVO>();
	 * 
	 * 
	 * 
	 * try { pstmt=con.prepareStatement(sql); pstmt.setInt(1,
	 * (page*amount)-amount+1); pstmt.setInt(2, page*amount);
	 * rs=pstmt.executeQuery();
	 * 
	 * 
	 * 
	 * 
	 * while(rs.next()){ int menuId = rs.getInt("menuId"); String fileName =
	 * rs.getString("imgurl");
	 * 
	 * String caffeineType = rs.getString("caffeineType"); String menuName =
	 * rs.getString("menuName"); int menuPrice = rs.getInt("menuPrice"); String
	 * menuExplain = rs.getString("menuExplain"); String menuType =
	 * rs.getString("menuType");
	 * 
	 * MenuVO mvo = new MenuVO(menuId, fileName, caffeineType, menuName, menuPrice,
	 * menuExplain, menuType);
	 * 
	 * 
	 * array.add(mvo);
	 * 
	 * }
	 * 
	 * 
	 * 
	 * return array;
	 * 
	 * } catch (SQLException e) { // TODO Auto-generated catch block
	 * e.printStackTrace(); }
	 * 
	 * return null;
	 * 
	 * 
	 * }
	 */
	
	
	public MenuVO serchMenu(int menuid) {
		
		MenuVO vo = null;
		String sql = "SELECT * FROM Menu where menuId=?";
		
		try {
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, menuid);
			rs=pstmt.executeQuery();
			
			if(rs.next()) {
				int menuId = rs.getInt("menuId");
				String imgurl = rs.getString("imgurl");
				String CaffeineType = rs.getString("caffeineType");
				String menuName = rs.getString("menuName");
				int menuPrice = rs.getInt("menuPrice");
				String menuExplain = rs.getString("menuExplain");
				String MenuType = rs.getString("menuType");
				
				vo = new MenuVO(menuId, imgurl, CaffeineType, menuName, menuPrice, menuExplain, MenuType);
			}
			
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return vo;
		
	}
	
	
	public boolean deleteMenu(int menuId) {
		
		String sql = "DELETE FROM Menu WHERE menuId=?";
		
		try {
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, menuId);
			pstmt.executeUpdate();
		}catch (Exception e) {
			// TODO: handle exception
			return false;
		}
		return true;
		
	}
	
	
	
	
	public boolean updateMenu(int menuId, String img, String caffeineType, String menuName, int menuPrice, 
			String menuExplain, String menuType ) {
		
		String sql = "UPDATE Menu SET imgurl=?, caffeineType=?, menuName=?, menuPrice=?, menuExplain=?,"
				+ "menuType=? WHERE menuId=?";
		
		try {
			pstmt = new LoggableStatement(con,sql);
			
			pstmt.setInt(7, menuId);
			pstmt.setString(1, img);
			pstmt.setString(2, caffeineType);
			pstmt.setString(3, menuName);
			pstmt.setInt(4, menuPrice);
			pstmt.setString(5, menuExplain);
			pstmt.setString(6, menuType);
			System.out.println("Executing query: "+
			         ((LoggableStatement)pstmt).getQueryString());
			
			pstmt.executeUpdate();
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}
		return true;
		
	}
	
	
	
	
	
	public ArrayList<MenuVO> getInfoMenu(String menuAll, String caffeineType, String menuType, String menuname, int page, int amount ) {
		
		ArrayList<MenuVO> arr = new ArrayList<MenuVO>();
		String sql = "select * from (select A.*, ROW_NUMBER() over(order by menuId desc) as num"
				+ "    from Menu A WHERE caffeineType like ? and menuType like ? and menuName like ?) "
				+ "    where num between ? and ?";
		try {
			
			System.out.println("menuall :" +menuAll);
			System.out.println("menuall :" +caffeineType);
			System.out.println("menuall :" +menuType);
			
			if(menuAll.equals("")&&caffeineType.equals("")&&menuType.equals("")&&menuname.equals("")) {
				page=0;
				amount=0;
			}
			
			pstmt = new LoggableStatement(con, sql);
			
			pstmt.setString(1, "%"+caffeineType+"%");
			pstmt.setString(2,"%"+ menuType+"%");
			pstmt.setString(3, "%"+menuname+"%");
			pstmt.setInt(4, (page*amount)-amount+1);
			pstmt.setInt(5, (page*amount));
			
			System.out.println("Executing query: "+
			         ((LoggableStatement)pstmt).getQueryString());
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				MenuVO mvo= null;
				int menuId = rs.getInt("menuId");
				String imgurl = rs.getString("imgurl");
				String CaffeineType = rs.getString("caffeineType");
				String menuName = rs.getString("menuName");
				int menuPrice = rs.getInt("menuPrice");
				String menuExplain = rs.getString("menuExplain");
				String MenuType = rs.getString("menuType");
				mvo = new MenuVO(menuId, imgurl, CaffeineType, menuName, 
						menuPrice, menuExplain, MenuType);
				arr.add(mvo);
			}
			
			return arr;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
	}
	
	
	

}
