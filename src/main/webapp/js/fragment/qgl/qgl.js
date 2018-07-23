$(function(){
	if($("#startTime").val() == ""){
		$("#startTime").val(moment().subtract(1,'days').format('YYYY-MM-DD'));
	}
	if($("#endTime").val() == ""){
		$("#endTime").val(moment().format('YYYY-MM-DD'))
	}
})
