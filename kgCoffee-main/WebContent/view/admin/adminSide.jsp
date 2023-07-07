<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<link rel="stylesheet" href="/kgCoffee/css/adminMain.css?after">
<link rel="stylesheet" href="/kgCoffee/css/adminNav.css?after">
		<!-- nav-start ------------------------------------------------------- -->
			<section class="nav_section">
			<div class="nav_wrap">
				<div class="nav">

					<ul class="nav_menu">

						<a href="/kgCoffee/view/user/index.jsp"> <img
							src="/kgCoffee/img/logo-removebg.png" width="200">
						</a>

						<a class="nav_page">관리자 페이지</a>

						<li><a href="/kgCoffee/admin/adminMenu.do" class="nav_list">메뉴관리</a></li>
						<li id="user_management"><a href="/kgCoffee/admin/getAllMember.do" class="nav_list" >회원관리</a></li>
						<li id="order_report"><a href="/kgCoffee/admin/getOrderReport" class="nav_list" >주문데이터분석</a>
							<ul class="select-chart">
								<li data-type="chart-age" class="chart-age"><a
									class="nav_list_s">연령별 분석</a></li>
								<li data-type="chart-menu" class="chart-menu"><a
									class="nav_list_s">메뉴별 분석</a></li>
								<li data-type="chart-store" class="chart-store"><a
									class="nav_list_s">매장 분석</a></li>
							</ul></li>
					</ul>


				</div>
			</div>
		</section>
		<!-- nav-end -------------------------------------------------------- -->


	


