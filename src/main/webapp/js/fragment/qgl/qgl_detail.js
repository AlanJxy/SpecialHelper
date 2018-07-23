$(".chk-all").click(function() {
	if(this.checked == true) {
		$(".chk-item").each(function() {
			$(this).prop("checked", true);
		})
	}
	else{
		$(".chk-item").each(function() {
			$(this).prop("checked", false);
		})
	}
})
$(".chk-item").click(function() {
	if(this.checked == false) {
		$(".chk-all").prop("checked", false);
	}
})