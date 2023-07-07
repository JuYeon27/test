<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

		<!DOCTYPE html>
		<html>

		<head>
			<meta charset="UTF-8">
			<meta name="viewport" content="width=device-width, initial-scale=1.0">
			<title>관리자 페이지</title>

			<link rel="stylesheet" href="/kgCoffee/css/adminMain.css?after">
			<link rel="stylesheet" href="/kgCoffee/css/adminNav.css?after">
			<link rel="stylesheet" href="/kgCoffee/css/adminOrderData.css?after">


			<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
			<!-- chart js CDN -->
			<script src="https://cdn.jsdelivr.net/npm/chart.js"></script>

		</head>

		<body>

			<div class="wrap">

				<!-- nav-start ------------------------------------------------------- -->
				<%@ include file="adminSide.jsp" %>
					<!-- nav-end -------------------------------------------------------- -->


					<section class="content_section">

						<div class="content_hedear_wrap">
							<div class="content_header">
								<div class="header_title">주문 데이터 분석 페이지</div>
							</div>
						</div>

						<div class="content_wrap">
							<div class="content">

								<div class="base_content_box">

									<form action="#" method="post">
										<div class="content_container">


											<div class="content_container_sub">

												<div class="con-date">
													<label class="con-label">날짜 선택</label> <input type="date" id="date"
														min="1800-01-01"> <select id="dateType">
														<option name="dateType" value="" selected>전체</option>
														<option name="dateType" value="yyyy">년</option>
														<option name="dateType" value="yyyy mm">월</option>
														<option name="dateType" value="yyyy mm dd">일</option>
													</select>
												</div>

												<div class="con-store">
													<label class="con-label">매장검색</label> <input type="text"
														name="store" class="store">
												</div>

											</div>


											<div class="content_container_sub">

												<div class="con-menu">
													<label class="con-label">메뉴검색</label> <input type="text" name="menu"
														class="menu">
												</div>

											</div>


										</div>

										<div class="content_container">
											<button type="button" class="btn_design" onclick="chartSend()">검색</button>
										</div>

									</form>

								</div>

								<div class="chart_content_bax">

									<div class="chart_container">

										<!--차트가 그려질 부분-->
										<%-- <canvas id="myChart"></canvas> --%>

											<div class="chart_ex">
												<h1>[ 데이터 분석 검색방법 ]</h1>
												<b class="chart_ex_b">
													< 나이대 베스트 메뉴>
												</b><br>
												1) 연령별 분석(부메뉴) 선택 <br>
												2) 검색 버튼 클릭 <br><br>
												<b class="chart_ex_b">
													< 메뉴별 총 주문량>
												</b><br>
												1) 메뉴별 분석(부메뉴) 선택 <br>
												2) 날짜 (전체, 년, 월, 일)을 선택 <br>
												3) 메뉴 이름 입력 <br>
												4) 검색 버튼 클릭 <br><br>
												<b class="chart_ex_b">
													< 매장별 총 주문량>
												</b><br>
												1) 매장 분석(부메뉴) 선택 <br>
												2) 날짜 (전체, 년, 월, 일)을 선택<br>
												3) 매장 이름 입력 <br>
												4) 검색 버튼 클릭 <br>
											</div>


									</div>

								</div>

							</div>
						</div>
					</section>
			</div>


			<script>


				$(".select-chart").toggleClass("show");



				$("#order_report").toggleClass("active");

				$(".select-chart li").click(function () {
					event.stopPropagation();

					$(".active").removeClass("active");

					$(this).toggleClass("active");



				});



				//버튼을 클릭하면 ajax가 실행되면서 데이터를 가져오고 -> 설명글을 지우고, 차트 태그를 보이게 한 후 차트를 보여준다.
				var select = null;

				$(".select-chart li").click(function () {
					select = $(this).data("type");
					console.log(select);
				})


				function chartSend() {

					var ageType = $("#ageType").val()
					var dateType = $("#dateType").val()
					var dateValue = $("#date").val()
					var keywords = $(".store").val()
					var menuName = $(".menu").val()

					$.ajax({
						url: "/kgCoffee/admin/api/getChart.do",	// 데이터를 가져올 경로 설정
						type: "get",	// 데이터를 가져오는 방식
						dataType: "JSON",
						data: {
							ageType: ageType,
							dateType: dateType,
							dateValue: dateValue,
							keywords: keywords,
							menuName: menuName,
							select: select,
							rank: 100,
							page: 1,
							amount: 500
						},
						success: function (data) {	// 데이터를 가져왔을때 동작. 매개변수로 data 입력

							/* alert("연결성공"); */

							console.log(data); //data 전체 리스트


							if (data === null) {
								alert("부메뉴 선택 후 다시 시도해주세요.");
							} else if (data) {

								$('.chart_ex').remove(); //설명글 입력된 태그 삭제
								$('canvas').remove();

								var keyNames = data.keyNames;

								for (i = 0; i < keyNames.length; i++) {

									const key = keyNames[i];
									console.log(key);
									var dtoList = data[key];

									chartShow(dtoList, i);

								}


							}

						}
					})//ajax-end

				} //chartSend()-end



				function chartShow(dtoList, i) {

					var chartDatas = [];
					var chartLabels = [];
					var chartTitle;


					const $chart_container = document.querySelector(".chart_container");

					var newEl = document.createElement('canvas');

					newEl.id = select + i;


					$chart_container.append(newEl);

					console.log('태그생성');
					console.log(newEl);


					var context = document.getElementById(select + i).getContext('2d');


					if (select === 'chart-age') {

						console.log(dtoList);

						for (dto of dtoList) {
							chartTitle = dto.ageGroup + '대';
							chartLabels.push(dto.menuName);
							chartDatas.push(dto.orderAmount);
						}

						if (chartTitle==="0대"){
							chartTitle = '10세 미만'
						}


					} else if (select === 'chart-menu') {

						console.log(dtoList);

						for (dto of dtoList) {
							chartTitle = dto.orderDate
							chartLabels.push(dto.menuName);
							chartDatas.push(dto.orderAmount);
						}



					} else if (select === 'chart-store') {


						console.log(dtoList);

						const LabelsUpdate = []; /* 지점이름 수정 */

						for (dto of dtoList) {
							chartTitle = dto.orderDate
							LabelsUpdate.push(dto.placeName);
							chartDatas.push(dto.orderAmount);
						}

						chartLabels = LabelsUpdate.map(item => item.substring(8));

					}

					dateForm = ["년", "월", "일"];
					if (chartTitle === undefined) {

						chartTitle = "전체기간";

					} else if (chartTitle.length > 3) {

						tempTitle = chartTitle.split(" ");
						chartTitle = ""
						for (i = 0; i < tempTitle.length; i++) {

							chartTitle += tempTitle[i] + dateForm[i] + " ";

						}

					}


					var myChart = new Chart(context, {
						type: 'bar', // 차트의 형태
						data: { // 차트에 들어갈 데이터

							labels: chartLabels, //x 축

							datasets: [  //데이터
								{
									label: chartTitle, //차트 제목
									fill: false, // line 형태일 때, 선 안쪽을 채우는지 안채우는지
									data: chartDatas,
									backgroundColor: [	//색상
										'rgba(255, 99, 132, 0.2)',
										'rgba(54, 162, 235, 0.2)',
										'rgba(255, 206, 86, 0.2)',
										'rgba(75, 192, 192, 0.2)',
										'rgba(153, 102, 255, 0.2)',
										'rgba(255, 159, 64, 0.2)'
									],
									borderColor: [	//경계선 색상
										'rgba(255, 99, 132, 1)',
										'rgba(54, 162, 235, 1)',
										'rgba(255, 206, 86, 1)',
										'rgba(75, 192, 192, 1)',
										'rgba(153, 102, 255, 1)',
										'rgba(255, 159, 64, 1)'
									],
									borderWidth: 1 //경계선 굵기
								}
							]
						},
						options: {
							scales: {
								y: {
									beginAtZero: true
								}
							}
						}
					});

				} //chartShow()-end

			</script>


		</body>

		</html>