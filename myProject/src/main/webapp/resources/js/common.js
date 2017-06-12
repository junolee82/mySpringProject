
// input 태그들을 받아서 값이 비어있으면 false, 문제가 없으면 true를 반환한다.

// 해당 input태그가 비었다면 형제 .error를 화면에 나타나도록 처리한다.

function checkInputEmpty($obj){
	var count = 0;
	
	$obj.each(function(i, obj) {
		if($(obj).val() == ""){
			var $next = $(obj).next(".error");
			$next.css("display","block");
			count++;
		}		
	});
	
	if(count > 0)
		return false;
	
	return true;
}