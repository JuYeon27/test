package test.com.kgcoffee.web.menu;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.jupiter.api.Test;

class TestCartInsert {

	@Test
	void test() {
		
//		CartInsertController insert = new CartInsertController();
//		
//		
//		
//		Map<String, String> paramMap =  new HashMap<String, String>();
//		
//	
//		Map<String, Object> model = new ConcurrentHashMap<>();
//				
//		for(int i=0; i<10; i++) {
//		paramMap.put("userId", "lee02");
//		paramMap.put("menuId", ""+i);
//		paramMap.put("menuAmount",""+i);
//		
//		
//		insert.process(paramMap, model);
//		}
		
//		HashMap<String, Object> resMap = new HashMap<String, Object>();
//		ArrayList<ReportMenuDTO> menuDTOList = new ArrayList<ReportMenuDTO>();
//
//		resMap.put("test", menuDTOList);
//		ReportMenuDTO dto = new ReportMenuDTO();
//		
//		dto.setAgeGroup("블라블라");
//		
//		menuDTOList.add(dto);
//		
//		System.out.println(resMap.size());
//		
//		System.out.println(((ArrayList<ReportMenuDTO>)resMap.get("test")).get(0));
//		System.out.println(resMap.size());
//		
		

		
		SimpleDateFormat format = new SimpleDateFormat("yy-mm-dd");
		
		try {
			
			Date now = format.parse("22-11-26");
			System.out.println(now);
			format.applyPattern("y m d");
			
			System.out.println(format.format(now));
			
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
