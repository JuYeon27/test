package com.kgcoffee.web.admin.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kgcoffee.web.admin.dao.AdminDAO;
import com.kgcoffee.web.admin.dao.MemberDAO;
import com.kgcoffee.web.common.Paging;
import com.kgcoffee.web.users.vo.UsersVO;

public class AdminService {

	private AdminDAO dao = AdminDAO.getInstance();
	Map<String, Object> resMap = null;
	MemberDAO mdao = new MemberDAO();

	
	
	
	
	
	
	
	
	public Map<String, Object> getAllMember(Map<String, String> keyMap) {
		// TODO Auto-generated method stub

		resMap = new HashMap<String, Object>();

		int page = 1;
		int count = mdao.getAllMemeberCount();

		if(keyMap.containsKey("page")) {
			page = Integer.parseInt(keyMap.get("page"));
		}

		Paging paging = new Paging();
		paging.setPage(page);
		paging.setTotalCount(count);

		ArrayList<UsersVO> list = mdao.getAllMember(page);

		resMap.put("list", list);
		resMap.put("paging", paging);

		return resMap;

	}

	public Map<String, Object> getOneMember(Map<String, String> keyMap) {
		// TODO Auto-generated method stub
		resMap = new HashMap<String, Object>();

		UsersVO uvo = null;

		String user_id = keyMap.get("user_id");

		uvo = mdao.getOneMember(user_id);

		resMap.put("uvo", uvo);

		return resMap;

	}

	public Map<String, Object> memberDelete(Map<String, String> keyMap) {
		// TODO Auto-generated method stub
		resMap = new HashMap<String, Object>();

		// 선택한 회원들의 아이디를 전달받아 저장
		// checkId라는 파라미터를 갖고 있는 값들이 많으므로
		// getParameterValues()를 사용해서 전달받아 저장
		String[] deleteId = keyMap.get("checkId").split(",");


		boolean checkDelete = true;

		// 선택한 회원의 아이디를 이용해서 Users테이블에서 회원정보를 삭제
		// ==> MemberDAO 클래스의 삭제메소드를 호출

		for (String id : deleteId) {

			if (!(mdao.delete(id))) {

				checkDelete = false;

			}

		}

		if (checkDelete) {

			resMap.put("msg", "delete-success");
		} else {
			resMap.put("msg", "delete-failed");
		}

		return resMap;

	}

	public Map<String, Object> memberSearch(Map<String, String> keyMap) {
		// TODO Auto-generated method stub
		resMap = new HashMap<String, Object>();

		String searchKeywordType = "";
		String searchKeyword = "";
		
		int page = 1;
		if(keyMap.containsKey("page")) {
			page = Integer.parseInt(keyMap.get("page"));
		}
		

	

		if(keyMap.containsKey("searchKeywordType")) {
			
			searchKeywordType = keyMap.get("searchKeywordType");
			searchKeyword = keyMap.get("searchKeyword");
		}
		int count = mdao.searchCount(searchKeywordType,searchKeyword);
		
		

		ArrayList<UsersVO> list = mdao.memberSearch(searchKeywordType,searchKeyword,page);
		
		Paging paging = new Paging(); 
		paging.setPage(page);
		paging.setTotalCount(count);
		
		resMap.put("list", list);
		resMap.put("paging", paging);
		resMap.put("searchKeywordType", searchKeywordType);
		resMap.put("searchKeyword", searchKeyword);
		
		return resMap;
		
	}

	public Map<String, Object> memberUpdate(Map<String, String> keyMap) {
		// TODO Auto-generated method stub
		resMap = new HashMap<String, Object>();

	
		
		String user_id = keyMap.get("user_id");
		String user_pw = keyMap.get("user_pw");
		String user_name = keyMap.get("user_name");
		String birthday = keyMap.get("birthday");
		String tel = keyMap.get("tel");
		
		
		
		mdao.update(user_id, user_pw, user_name, birthday, tel);
		
		

		return resMap;

	}

	
	
	
	
	
	
	
	public Map<String, Object> reportOrderByMap(Map<String, String> keyMap) {

		System.out.println("reportOrderByMap");
		System.out.println(keyMap.size());

		if (!keyMap.containsKey("keywords")) {

			keyMap.put("keywords", "");

		}

		formatDate(keyMap);

		Paging paging = makePageOrder(keyMap);

		resMap = dao.findOrderByMap(keyMap);
		resMap.put("paging", paging);

		return resMap;
	}

	public Map<String, Object> reportOrderByMenu(Map<String, String> keyMap) {

		System.out.println("reportOrderByMenu");
		System.out.println(keyMap.size());

		if (!keyMap.containsKey("manu_name")) {

			keyMap.put("menu_name", "");

		}

		formatDate(keyMap);

		Paging paging = makePageOrder(keyMap);

		resMap = dao.findOrderByMenu(keyMap);

		resMap.put("paging", paging);

		return resMap;
	}

	public Map<String, Object> reportOrderByAgeGroup(Map<String, String> keyMap) {
		// TODO Auto-generated method stub

		System.out.println("reportOrderByAgeGroup");
		System.out.println(keyMap.size());

		formatDate(keyMap);

		Paging paging = makePageOrder(keyMap);

		resMap = dao.findOrderByAgeGroup(keyMap);
		resMap.put("paging", paging);

		return resMap;
	}

	public Paging makePageOrder(Map<String, String> keyMap) {

		int page = 1;
		int amount = 10;

		if (keyMap.containsKey("page")) {

			page = Integer.parseInt(keyMap.get("page"));

		}
		if (keyMap.containsKey("amount")) {

			amount = Integer.parseInt(keyMap.get("amount"));

		}
		;
		keyMap.put("page", "" + page);
		keyMap.put("amount", "" + amount);

		int totalCnt = dao.findOrderByAgeGroupTotalCnt(keyMap);

		System.out.println("totalCnt : " + totalCnt);

		Paging paging = new Paging();

		paging.setPage(page);
		paging.setDisplayRow(amount);
		paging.setTotalCount(totalCnt);

		return paging;

	}

	public void formatDate(Map<String, String> keyMap) {

		String dateType = keyMap.get("dateType");

		String newDateValue = "";

		String dateValue = keyMap.get("dateValue");
		System.out.println(dateValue);

		if (dateType == null || dateType == "") {

			dateType = "";
			newDateValue = "";
		} else {

			Date date = null;
			try {
				date = new SimpleDateFormat("yy-mm-dd").parse(dateValue);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			SimpleDateFormat form = new SimpleDateFormat(dateType);
			System.out.println(form.toString());
			System.out.println(dateType);
			System.out.println(date);
			newDateValue = form.format(date);

		}

		keyMap.put("dateType", dateType);
		keyMap.put("dateValue", newDateValue);
		System.out.println("newDateValue" + newDateValue);

	}

}
