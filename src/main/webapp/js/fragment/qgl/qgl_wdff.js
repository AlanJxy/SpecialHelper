$(function(){
	if($("#txtBeginDate").val() == ""){
		$("#txtBeginDate").val(moment().subtract(1,"days").format("YYYY-MM-DD"));
	}
	if($("#txtEndDate").val() == ""){
		$("#txtEndDate").val(moment().format("YYYY-MM-DD"));
	}
	$(".tabs a").click(function(){
		$(".tabs a").removeClass("active");
		$(this).addClass("active");
		var index = $(this).index();
		$(".panel").addClass("hidden");
		$(".panel").eq(index).removeClass("hidden");
	})
})
