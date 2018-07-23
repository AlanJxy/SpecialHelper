$(function(){
	if($("#txtBeginDate").val() == ""){
		$("#txtBeginDate").val(moment().subtract(1,"days").format("YYYY-MM-DD"));
	}
	if($("#txtEndDate").val() == ""){
		$("#txtEndDate").val(moment().format("YYYY-MM-DD"));
	}
})
