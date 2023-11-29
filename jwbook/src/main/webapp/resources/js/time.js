// time.jsp 시계구현 


setInterval(myWatch, 1000);

function myWatch(){
   let date = new Date();
   let time = date.toLocaleTimeString(); //시간만 출력
   document.getElementById("display").innerHTML = time;
}