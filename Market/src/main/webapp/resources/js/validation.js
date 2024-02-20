/**
 *  상품 등록 유효성 검사
 * 1. 상품 코드는 P와 숫자를 조합하여 5~12자로 입력
 * 2. 상풍명은 4~20자
 * 3. 가격 및 재고 수는 숫자만 입력
 */

 let checkProduct = function(){
	 //alert("테스트...");
	 let pid = document.getElementById("pid");
	 let pname = document.getElementById("pname");
	 let price = document.getElementById("price");
	 let pstock = document.getElementById("pstock");
	 
	 //정규표현식
	 let regexPid = /P[0-9]{4,11}/
	 
	 if(!regexPid.test(pid.value)){ //정규 표현식이 상품코드와 일치하지 않으면
		 alert("상품 코드는 P와 숫자를 조합하여 5~12자로 입력")
		 pid.select();
		 return false;
	 }else if(pname.value.length < 4 || pname.value.length > 20){
		 alert("상풍명은 4~20자로 입력")
		 pname.select();
		 return false;
	 }else if(price.value.length == 0 || isNaN(price.value)
	 		  || price.value < 0){
		 alert("가격은 숫자만 입력 가능 / 0보다 큰 숫자 입력")
		 price.select();
		 return false;
	 }else if(pstock.value.length=0 || isNaN(pstock.value)
	 		  || pstock.value < 0){
		 alert("재고수는 숫자만 입력 / 0보다 큰 숫자 입력")
		 pstock.select();
		 return false;
	 }else {
		 document.newProduct.submit(); 
	 }
 }