$(function(){
	$(".tabs a").click(function(){
		$(".tabs a").removeClass("active");
		$(this).addClass("active");
		
		var index = $(this).index();
		$(".tabs-main .panel").addClass("hidden");
		$(".tabs-main .panel").eq(index).removeClass("hidden");
	})
})
