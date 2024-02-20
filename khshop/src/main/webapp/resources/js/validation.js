function joinCheck(){
		/* 계정 유효성 검사 */
		let form = document.member;
		let id = form.uid.value;			 
		let pw1 = form.passwd.value;
		let pw2 = form.passwd2.value;
		let name = form.name.value;
		
		let regPw1 = /[0-9]+/ 				//숫자
		let regPw2 = /[a-zA-Z]+/ 			//영문자
		let regPw3 = /[~!@#$%^&*()_+|]+/ 	//특수문자
		let regexName = /^[가-힣]+$/  		//한글만
		
		if(id.length < 5 || id.length > 12){
			alert("아이디는 5~12자리까지 입력 가능합니다.");
			id.select();
			return false;
		}else if(pw1.length < 7 || !regPw1.test(pw1) ||
				 !regPw2.test(pw1) || !regPw3.test(pw1)){	
			alert("비밀번호는 영문자, 숫자, 특수문자 포함 7자 이상 입력 가능합니다.");
			pw1.select();
			return false;
		}else if(pw1 != pw2){ 
			alert("비밀번호를 동일하게 입력해주세요");
			pw2.select();
			return false;
		}else if(!regexName.test(name)){
			alert("이름은 한글로 입력해 주세요");
			name.select();
			return false;
		}
	}