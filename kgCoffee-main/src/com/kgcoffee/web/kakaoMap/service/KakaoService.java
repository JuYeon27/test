package com.kgcoffee.web.kakaoMap.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.kgcoffee.web.kakaoMap.dao.KakaoMapDAO;
import com.kgcoffee.web.kakaoMap.vo.KakaoMapVO;

public class KakaoService {

	private final String uri = "https://dapi.kakao.com/v2/local/search/keyword.json?size=15";
	private final String apiKey = "KakaoAK 4811118d83cc4f11769af7407cf75b6e";
	
	KakaoMapDAO dao = KakaoMapDAO.getInstance();
	

	//얻어온 정보 db에 저장
	public void saveMap() {

		JsonArray jsonMapList = new JsonArray();
		double jumpX = 0.5;
		double jumpY = 0.5;

		double startX = 126;

		List<KakaoMapVO> resultList = new ArrayList<KakaoMapVO>();
		for (int i = 0; i < 10; i++) {

			double endX = startX + jumpX;
			double startY = 33;

			for (int j = 0; j < 13; j++) {
				double endY = startY + jumpY;

				jsonMapList.addAll(getStoreList(startX, startY, endX, endY));
				startY = endY;

			}
			startX = endX;

		}

		if (jsonMapList != null) {

			for (int i = 0; i < jsonMapList.size(); i++) {
				
				JsonObject map = (JsonObject) jsonMapList.get(i);
				

				String addressName = map.get("address_name").getAsString();
				String categoryGroupCode = map.get("category_group_code").getAsString();
				String categoryGroupName = map.get("category_group_name").getAsString();
				String categoryName = map.get("category_name").getAsString();
				int mapId = map.get("id").getAsInt();
				String placeName = map.get("place_name").getAsString();
				String placeUrl = map.get("place_url").getAsString();
				String phone = map.get("phone").getAsString();
				String roadAddressName = map.get("road_address_name").getAsString();
				Double x = map.get("x").getAsDouble();
				Double y = map.get("y").getAsDouble();
				
				KakaoMapVO vo = new KakaoMapVO(addressName, categoryGroupCode, categoryGroupName, 
						categoryName, mapId, placeName, placeUrl, phone, roadAddressName, x, y);
				
				resultList.add(vo);

			}
			
			System.out.println("result List size - " + resultList.size());
			
			//중복제거
			Map<Integer,KakaoMapVO> distinctMap= new HashMap<Integer,KakaoMapVO>();
			for(int i=0; i<resultList.size();i++) {
				
				KakaoMapVO vo =resultList.get(i);
				distinctMap.put(vo.getMapId(), vo);
					
			}

			resultList = new ArrayList<KakaoMapVO>(distinctMap.values());
			System.out.println("distinct result - "+resultList.size());
			
			
			for(KakaoMapVO vo : resultList){
				
				if(!(dao.insertMapList(vo))) {
					System.out.println("Insert Map Fail - map id "+vo.getMapId());
					
				}
				
			}
			
			
			
			
			
		}

	}
	
//	public void test() {
//		
//		KakaoMapVO vo = new KakaoMapVO("서울 마포구 동교동 164-22","CE7","카페","음식점 > 카페 > 커피전문점 > 메가MGC커피",
//				1969925488,"메가MGC커피 홍대입구역점","http://place.map.kakao.com/1969925488"
//				,"02-323-0650","서울 마포구 홍익로6길 48",126.9242387367584,37.55619709509767);
//	
//		dao.insertMapList(vo);
//		
//		
//		
//	}
	
	//api를 통해 매장 검색
	public JsonArray getStoreList(double startX, double startY, double endX, double endY) {
		
		
		int page = 1;

		double offset = 0.01;

		JsonArray mapList = new JsonArray();

		while (true) {
			try {
				String query = URLEncoder.encode("메가커피","UTF-8");

				StringBuilder reqUri = new StringBuilder(uri);
				reqUri.append("&query="+query).append("&page=" + page).append("&rect=" + (startX - offset)).append(",").append(startY - offset)
						.append(",").append(endX + offset).append(",").append(endY + offset);

//				System.out.println("URL : " + reqUri.toString());
				URL url = new URL(reqUri.toString());

				
				HttpURLConnection conn = (HttpURLConnection) url.openConnection();

				conn.setRequestMethod("GET");
				conn.setConnectTimeout(5000);
				conn.setReadTimeout(5000);
				conn.setRequestProperty("Authorization", apiKey);
				conn.setRequestProperty("Content-type", "application/json; charset=utf-8");
			
//				System.out.println("응답코드 : " + conn.getResponseCode() + conn.getResponseMessage());
//
//				System.out.println("ContentType : " + conn.getContentType());

				try (BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()))) {

					String responseData = br.readLine();

					JsonElement element = JsonParser.parseString(responseData);


					JsonObject object = element.getAsJsonObject();
					JsonObject meta = object.get("meta").getAsJsonObject();
					JsonArray documents = object.get("documents").getAsJsonArray();

//					System.out.println(meta);
					int searchCnt = meta.get("total_count").getAsInt();
					System.out.println("검색결과"+searchCnt);

					if (searchCnt > 45) {

						System.out.println("지도분할");
						double halfX = (startX + endX) / 2;
						double halfY = (startY + endY) / 2;

						mapList.addAll(getStoreList(startX, startY, halfX, halfY));
						mapList.addAll(getStoreList(halfX, startY, endX, halfY));
						mapList.addAll(getStoreList(startX, halfY, halfX, endY));
						mapList.addAll(getStoreList(halfX, halfY, endX, endY));

						return mapList;

					} else if (meta.get("is_end").getAsBoolean()) {
						System.out.println("페이징종료");
						mapList.addAll(documents);
						return mapList;
					} else {
						System.out.println("페이징");
						page += 1;
						mapList.addAll(documents);

					}

				} catch (Exception e) {
					e.printStackTrace();
				}

			} catch (Exception e) {
				e.printStackTrace();
			}

		}


	}

	
	public ArrayList<KakaoMapVO> findMap(HashMap<String, Object> keyMap){
		
		ArrayList<KakaoMapVO> result = null;
		
		
		result= dao.findMap(keyMap);
		
		
		return result;
		
		
		
	}
	

}
