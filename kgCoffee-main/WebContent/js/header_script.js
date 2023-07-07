/* header */
/* 화면을 작게하면 menu-bar가 생기고 menu-bar를 클릭하면 navbar 펼쳐짐 */
var menu = document.querySelector('#menu-bar');
var navbar = document.querySelector('.navbar');

menu.addEventListener('click', function(event){
	menu.classList.toggle('fa-times');
	navbar.classList.toggle('header_active');
});