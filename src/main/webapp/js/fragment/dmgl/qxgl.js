$(function() {
	//全选
	$(".chk-all").click(function() {
		if(this.checked == true) {
			$(".chk-item").each(function() {
				$(this).prop("checked", true);
			})
		} else {
			$(".chk-item").each(function() {
				$(this).prop("checked", false);
			})
		}
	})
})