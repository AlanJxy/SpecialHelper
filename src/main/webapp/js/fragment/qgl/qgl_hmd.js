$(function(){
	$(".tabs a").click(function(){
		$(".tabs a").removeClass("active");
		$(this).addClass("active");
		var index = $(this).index();
		$(".panel").addClass("hidden");
		$(".panel").eq(index).removeClass("hidden");
	})
//	全选
	$(".chk-all").click(function(){
		if(this.checked == true){
			$(".chk-item").each(function(){
				$(".chk-item").prop("checked",true);
			})
		}else{	
			$(".chk-item").prop("checked",false);
		}
	})
})
