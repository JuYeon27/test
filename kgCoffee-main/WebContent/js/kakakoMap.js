

function centerSerach() {


    // latlng.getLat() latlng.getLng()

    // 10m / 1:475
    // 20m / 1:950
    // 30m / 1:1,900
    // 50m / 1:3,800
    // 100m / 1:7,600
    // 250m / 1:15,200
    // 500m / 1:30,400

    // 						1km / 1:60,800
    // 2km / 1:121,600
    // 4km / 1:243,200
    // 8km / 1:486,400
    // 16km / 1:972,800
    // 32km / 1:1,945,600
    // 64km / 1:3,891,200
    // 128km / 1:7,782,400


    // 지도의  레벨을 얻어옵니다
    var newLevel = map.getLevel();

    // var newRd;
    // switch (newLevel) {

    // 	case 1:
    // 		newRd = 10;
    // 		break;


    // 	case 2:
    // 		newRd = 20;
    // 		break;


    // 	case 3:
    // 		newRd = 30;
    // 		break;


    // 	case 4:
    // 		newRd = 50;

    // 		break;


    // 	case 5:
    // 		newRd = 100;
    // 		break;

    // 	case 6:
    // 		newRd = 250;
    // 		break;

    // 	case 7:
    // 		newRd = 500;
    // 		break;

    // 	case 8:
    // 		newRd = 1000;
    // 		break;

    // 	case 9:
    // 		newRd = 2000;
    // 		break;

    // 	case 10:
    // 		newRd = 4000;
    // 		break;

    // 	case 11:
    // 		newRd = 8000;
    // 		break;

    // 	default:
    // 		newRd= 10000;
    // 		break;

    // }


    var options = {
        location: map.getCenter(),
        radius: 10000,
        sort: kakao.maps.services.SortBy.DISTANCE,
        
    };
    

    var newkey = "메가커피"
    moveIndex = true;
    ps.keywordSearch(newkey, placesSearchCB, options);



};


// 키워드 검색을 요청하는 함수입니다
function searchPlaces(location) {

  

    keyword = document.getElementById('keyword').value;



    if (!keyword) {

        keyword = "홍대입구 ";
    }

    if (location) {
        keyword = location;
    }



    if (!keyword.replace(/^\s+|\s+$/g, '')) {
        alert('매장명 또는 주소');
        return false;
    }

    keyword += "메가커피";



    // 장소검색 객체를 통해 키워드로 장소검색을 요청합니다
    ps.keywordSearch(keyword, placesSearchCB);
}

// 장소검색이 완료됐을 때 호출되는 콜백함수 입니다
function placesSearchCB(data, status, pagination) {
    if (status === kakao.maps.services.Status.OK) {


       
        // 정상적으로 검색이 완료됐으면
        // 검색 목록과 마커를 표출합니다
        displayPlaces(data);


        // 페이지 번호를 표출합니다
        displayPagination(pagination);

    } else if (status === kakao.maps.services.Status.ZERO_RESULT) {

        alert('검색 결과가 존재하지 않습니다.');
        return;

    } else if (status === kakao.maps.services.Status.ERROR) {

        alert('검색 중 오류가 발생했습니다.');
        return;

    }
}

// 검색 결과 목록과 마커를 표출하는 함수입니다
function displayPlaces(places) {



    var listEl = document.getElementById('placesList'),
        menuEl = document.getElementById('menu_wrap'),
        fragment = document.createDocumentFragment(),
        bounds = new kakao.maps.LatLngBounds(),
        listStr = '';

    // 검색 결과 목록에 추가된 항목들을 제거합니다
    removeAllChildNods(listEl);

    // 지도에 표시되고 있는 마커를 제거합니다
    removeMarker();

    for (var i = 0; i < places.length; i++) {


        // 마커를 생성하고 지도에 표시합니다
        var placePosition = new kakao.maps.LatLng(places[i].y, places[i].x),
            marker = addMarker(placePosition, i),
            itemEl = getListItem(i, places[i]); // 검색 결과 항목 Element를 생성합니다

        // 검색된 장소 위치를 기준으로 지도 범위를 재설정하기위해
        // LatLngBounds 객체에 좌표를 추가합니다

        bounds.extend(placePosition);

        //마커 인포 윈도우 클릭이벤트
        (function (marker, places) {
            kakao.maps.event.addListener(marker, 'click', function () {
                displayInfowindow(marker, places);

            });

            // kakao.maps.event.addListener(marker, 'mouseout', function () {
            //     infowindow.close();
            // });

            itemEl.onclick = function () {

                //지도 범위 재설정
                panTo(marker.getPosition());
                //정보창 호출
                displayInfowindow(marker, places);
            };

            // itemEl.onmouseout = function () {
            //     infowindow.close();
            // };
        })(marker, places[i]);


        fragment.appendChild(itemEl);
    }

    // 검색결과 항목들을 검색결과 목록 Element에 추가합니다
    listEl.appendChild(fragment);
    menuEl.scrollTop = 0;

    // 검색된 장소 위치를 기준으로 지도 범위를 재설정합니다
    if (!moveIndex) {
        map.setBounds(bounds);
    }
    moveIndex = false;

}

// 검색결과 항목을 Element로 반환하는 함수입니다
function getListItem(index, places) {

    var el = document.createElement('li'),
        itemStr = 
        // '<span class="markerbg marker"></span>' +
            '<div class="info">' +
            '   <h5> ' + places.place_name.substr(8) + '</h5>';

    if (places.road_address_name) {
        itemStr += '    <p class ="road gray">' + places.road_address_name + '</p>'
        // +'   <span class="jibun gray">' + places.address_name + '</span>';
    } else {
        itemStr += '    <p>' + places.address_name + '</p>';
    }

    itemStr += '  <p class="tel">' + places.phone + '</p>' +
        '</div>';

    el.innerHTML = itemStr;
    el.className = 'item';

    return el;
}

//지도이동
function panTo(moveLatLon) {
    // 이동할 위도 경도 위치를 생성합니다 

    // 지도 중심을 부드럽게 이동시킵니다
    // 만약 이동할 거리가 지도 화면보다 크면 부드러운 효과 없이 이동합니다
    map.panTo(moveLatLon);
}

// 마커를 생성하고 지도 위에 마커를 표시하는 함수입니다
function addMarker(position, idx, title) {
    var imageSrc = '/kgCoffee/img/icon/marker.png', // 마커 이미지 url, 스프라이트 이미지를 씁니다
        imageSize = new kakao.maps.Size(40, 50),  // 마커 이미지의 크기
        markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize),
        marker = new kakao.maps.Marker({
            position: position, // 마커의 위치
            image: markerImage
        });

    marker.setMap(map); // 지도 위에 마커를 표출합니다
    markers.push(marker);  // 배열에 생성된 마커를 추가합니다

    return marker;
}

// 지도 위에 표시되고 있는 마커를 모두 제거합니다
function removeMarker() {
    for (var i = 0; i < markers.length; i++) {
        markers[i].setMap(null);
    }
    markers = [];
}

// 검색결과 목록 하단에 페이지번호를 표시는 함수입니다
function displayPagination(pagination) {


    document.querySelector(".search_count").innerHTML = "(검색 결과 " + pagination.totalCount + "개 (max:45))";


    var paginationEl = document.getElementById('pagination'),
        fragment = document.createDocumentFragment(),
        i;

    // 기존에 추가된 페이지번호를 삭제합니다
    while (paginationEl.hasChildNodes()) {
        paginationEl.removeChild(paginationEl.lastChild);
    }

    for (i = 1; i <= pagination.last; i++) {
        var el = document.createElement('a');
        el.href = "#";
        el.innerHTML = i;

        if (i === pagination.current) {
            el.className = 'on';
        } else {
            el.onclick = (function (i) {
                return function () {
                    pagination.gotoPage(i);
                }
            })(i);
        }

        fragment.appendChild(el);
    }
    paginationEl.appendChild(fragment);
}

// 검색결과 목록 또는 마커를 클릭했을 때 호출되는 함수입니다
// 인포윈도우에 장소명을 표시합니다
function displayInfowindow(marker, places) {
  
  
   

    var content = '<div class="map_marker_pop_wrap">' +
        '<div class="map_marker_pop">' +
        '<div class="map_marker_pop_header">' + places.place_name.substr(7) + '</div>' +
        ' <div class="close" onclick="infowindow.close()" title="닫기"></div>' +
        '<article>' +
        '<div class="map_marker_pop_inner">' +
        '<div class="cont_wrap">';

    if (places.road_address_name) {
        content += '<p class ="road_addr">' + places.road_address_name + '</p>'

    } else {
        content += '<pclass ="jibun_addr">' + places.address_name + '</pclass>';
    }

    content +=
        '<p class="tel">' + places.phone + '</p>' +
        '<a class="btn_marker_detail" href="https://map.kakao.com/link/to/' + places.place_name + ',' + places.y + ',' + places.x + '" target="_blank">길찾기</a>' +
        '</div>' +
        '</div>' +
        '</article>' +
        '</div>' +
        '</div>';

        infowindow.setContent(content);
        infowindow.open(map, marker);
        
        $(document).animate({scrollTop: ($('.window').height() / 2)}, 500);
}





// 검색결과 목록의 자식 Element를 제거하는 함수입니다
function removeAllChildNods(el) {
    while (el.hasChildNodes()) {
        el.removeChild(el.lastChild);
    }

}

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////








var locationList = {
    서울: ["종로구", "중구", "용산구", "성동구", "광진구", "동대문구", "중랑구", "성북구", "강북구", "도봉구", "노원구", "은평구", "서대문구", "마포구", "양천구", "강서구", "구로구", "금천구", "영등포구", "동작구", "관악구", "서초구", "강남구", "송파구", "강동구"]
    , 부산: ["중구", "서구", "동구", "영도구", "부산진구", "동래구", "남구", "북구", "해운대구", "사하구", "금정구", "강서구", "연제구", "수영구", "사상구", "기장군"]
    , 대구: ["중구", "동구", "서구", "남구", "북구", "수성구", "달서구", "달성군"]
    , 인천: ["중구", "동구", "남구", "미추홀구", "연수구", "남동구", "부평구", "계양구", "서구", "강화군", "옹진군"]
    , 광주: ["동구", "서구", "남구", "북구", "광산구"]
    , 대전: ["동구", "중구", "서구", "유성구", "대덕구"]
    , 울산: ["중구", "남구", "동구", "북구", "울주군"]
    , 세종: ["가름로", "나성북1로", "남세종로", "다정중앙로", "도움1로", "보듬3로", "보듬4로", "새롬중앙로", "소담1로", "여주시", "조치원읍", "한누리대로", "해밀3로"]
    , 경기: ["수원시", "성남시", "고양시", "용인시", "부천시", "안산시", "안양시", "남양주시", "화성시", "평택시", "의정부시", "시흥시", "파주시", "광명시", "김포시", "군포시", "광주시", "이천시", "양주시", "오산시", "구리시", "안성시", "포천시", "의왕시", "하남시", "여주시", "여주군", "양평군", "동두천시", "과천시", "가평군", "연천군"]
    , 강원: ["춘천시", "원주시", "강릉시", "동해시", "태백시", "속초시", "삼척시", "홍천군", "횡성군", "영월군", "평창군", "정선군", "철원군", "화천군", "양구군", "인제군", "고성군", "양양군"]
    , 충북: ["청주시", "충주시", "제천시", "청원군", "보은군", "옥천군", "영동군", "진천군", "괴산군", "음성군", "단양군", "증평군"]
    , 충남: ["천안시", "공주시", "보령시", "아산시", "서산시", "논산시", "계룡시", "당진시", "당진군", "금산군", "연기군", "부여군", "서천군", "청양군", "홍성군", "예산군", "태안군"]
    , 전북: ["전주시", "군산시", "익산시", "정읍시", "남원시", "김제시", "완주군", "진안군", "무주군", "장수군", "임실군", "순창군", "고창군", "부안군"]
    , 전남: ["목포시", "여수시", "순천시", "나주시", "광양시", "담양군", "곡성군", "구례군", "고흥군", "보성군", "화순군", "장흥군", "강진군", "해남군", "영암군", "무안군", "함평군", "영광군", "장성군", "완도군", "진도군", "신안군"]
    , 경남: ["포항시", "경주시", "김천시", "안동시", "구미시", "영주시", "영천시", "상주시", "문경시", "경산시", "군위군", "의성군", "청송군", "영양군", "영덕군", "청도군", "고령군", "성주군", "칠곡군", "예천군", "봉화군", "울진군", "울릉군"]
    , 경북: ["창원시", "마산시", "진주시", "진해시", "통영시", "사천시", "김해시", "밀양시", "거제시", "양산시", "의령군", "함안군", "창녕군", "고성군", "남해군", "하동군", "산청군", "함양군", "거창군", "합천군"]
    , 제주: ["제주시", "서귀포시", "북제주군", "남제주군"]
}

var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
    mapOption = {
        center: new kakao.maps.LatLng(37.566826, 126.9786567), // 지도의 중심좌표
        level: 2 // 지도의 확대 레벨
    };


// 검색 결과 목록이나 마커를 클릭했을 때 장소명을 표출할 인포윈도우를 생성합니다
var infowindow = new kakao.maps.InfoWindow({ zIndex: 1 });



// 마커를 담을 배열입니다
var markers = [];




// 지도를 표시할 div와  지도 옵션으로  지도를 생성합니다
var map = new kakao.maps.Map(mapContainer, mapOption);

// 장소 검색 객체를 생성합니다
var ps = new kakao.maps.services.Places();


var moveIndex = false;

//검색어
var keyword;

// 좌표 이동 이벤트
kakao.maps.event.addListener(map, 'dragend', centerSerach);


//위치정보 있으면 내위치를 중심으로 표시
if (navigator.geolocation) {
    
    // GeoLocation을 이용해서 접속 위치를 얻어옵니다
    navigator.geolocation.getCurrentPosition(function(position) {
        
        var lat = position.coords.latitude, // 위도
            lon = position.coords.longitude; // 경도
        
        var locPosition = new kakao.maps.LatLng(lat, lon); // 마커가 표시될 위치를 geolocation으로 얻어온 좌표로 생성합니다
           
        
        var imageSrc = '/kgCoffee/img/icon/marker_my.png', // 마커 이미지 url, 스프라이트 이미지를 씁니다
        imageSize = new kakao.maps.Size(40, 50),  // 마커 이미지의 크기
        markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize),
        marker = new kakao.maps.Marker({
            position: locPosition, // 마커의 위치
            image: markerImage
        });  

        marker.setMap(map); // 지도 위에 마커를 표출합니다
        
        // 지도 중심좌표를 접속위치로 변경합니다
        map.setCenter(locPosition);      
        
        centerSerach();
                
        });
    
}else{

// 없으면 홍대기본값
searchPlaces();
}






// // 검색버튼 이벤트
$(".quickSearchBtn").click(function () {
    searchPlaces();
}
);



$("#keyword").keyup(function (key) {
    if (key.keyCode === 13) {
        searchPlaces();
    }
});


$(".find_store_header li").click(function () {

    $(".menu_toggle").toggleClass("active");

});



//지역찾기 li 추가
$(".store_area_search_list li").click(function () {

    const $result = $(".store_area_search_list_result");


    var area = $(this).data("store-area-name")
    areas = locationList[area];
    $(".area_list").toggleClass("off");

    areas.forEach(address => {

        var li = document.createElement('li');

        li.className = "store_area"
        li.setAttribute("data-address", area + " " + address);
        li.innerText = address;


        li.onclick = function () {

            key = li.getAttribute("data-address")

            searchPlaces(key);



        }
        $result.append(li);





    });

});


//지역 li 지우기
$(".clear_btn").click(function () {


    $("li").remove(".store_area");

    $(".area_list").toggleClass("off");

})



