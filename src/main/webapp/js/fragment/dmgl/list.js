var _page = 1;
$(function(){
    if($("#startTime").val() == ''){
    	$("#startTime").val(moment().subtract(1,'days').format('YYYY-MM-DD'))
    }
    if($("#endTime").val() == ''){
    	$("#endTime").val(moment().format('YYYY-MM-DD'));
    }
    search(1);
	
})
//点击查询符合日期条件的数据
function search(strPageNo){
	var beginDate = $("#startTime").val();
	var endDate = $("#endTime").val();
	var strPageSize = $("#selPageNum").val();
	$.ajax({
		url:"json/dmgl.json",
		type: "GET",
		data : {
//			beginDate : beginDate, endDate : endDate, 
			strPageSize :strPageSize, strPageNo : strPageNo
		},
		async : false,
        dataType: 'json',
		success:function(data){
			bildList(data);
		},
		error:function(){
			alert("网络异常，请重试");
		}
	})
}
//获取数据列表
function bildList(data){
	var arr = data.dtResult1;
	var num = data.totalRecord;
	if(data!=null){
		var html = '';
		for(var i = 0; i < arr.length; i++){
			html += '<tr>'
			html += '	<td>'+ arr[i].rn +'</td>'
			html += '	<td>'
			html += '		<div class="user">'
			html += '            <img src="'+ arr[i].vcHeadImages +'" />'
			html += '			<span>'+ arr[i].vcBase64NickName +'</span>'
			html += '		</div>'
			html += '	</td>'
			html += '	<td>'+ arr[i].nCount +'</td>'
			html += '	<td>'+ arr[i].vcCardNum +'</td>'
			html += '	<td>'+ arr[i].roomNum +'</td>'
			html += '	<td>'
			html += '		<a id="zx" class="zx">注销</a>'
			html += '		<a id="qgl" class="zx" data-toggle="modal" data-target="#myModal1">管理所属群</a>'
			html += '		<a id="jqr" class="zx" data-toggle="modal" data-target="#myModal2">可使用机器人</a>'
			html += '	</td>'
			html += '</tr>'
		}
		$("#tb").append(html);
	}
	$("#spanPage").append(num);
	if(data.totalRecord > 0){
		setPage(data.pageNo,data.totalRecord);
	}
}
function setPage(pageNo,totalRecord){
	var strPageSize = $("#selPageNum").val();
	var pageSize = parseInt(strPageSize);
	var options = {
		bootstrapMajorVersion: 3,
		currentPage : pageNo,
		totalPages : Math.ceil(totalRecord / pageSize),
		alignment: "center",
		itemTexts: function (type, page, current) {
            switch (type) {
                case "first":
                    return "首页";
                case "prev":
                    return "上一页";
                case "next":
                    return "下一页";
                case "last":
                    return "末页";
                case "page":
                    return page;
            }
        },
        onPageClicked: function (event, originalEvent, type, page) {
            $.ajax({
            	url:"json/dmgl.json",
				type: "GET",
				data : {
//					beginDate : beginDate, endDate : endDate, 
					strPageSize :strPageSize
				},
				success : function(data1){
					bildList(data1);
					_page = page;
				},
				error : function(){
					alert("获取分页失败");
				}
            })
        }
		
	}
	$("#pagination_ul").bootstrapPaginator(options);
}

