//全选
$("#selAll").click(function() {
	if(this.checked == true) {
		$(".tdId").each(function() {
			$(this).prop("checked", true);
		})
	} else {
		$(".tdId").each(function() {
			$(this).prop("checked", false);
		})
	}
})
$(".tdId").click(function() {
	$(this).each(function() {
		if(this.checked == false) {
			$("#selAll").prop("checked", false);
		}
	})
})

