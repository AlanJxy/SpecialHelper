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

$(".js-href-btn").click(function() {
	$("#myModal1").modal('hide');
	$("#myModal2").modal('show');
})
//查看通知
$(".btn-look").click(function() {
	$("#myModal3").modal('show');
})

$(document).on("keyup", ".js-link-tit", function() {
	var val;
	if($(this).val() != "") {
		val = $(this).val()
	} else {
		val = $(this).attr("placeholder")
	}
	$(".js-link-show-tit").text(val);
})
$(document).on("keyup", ".js-link-txt", function() {
	var val;
	if($(this).val() != "") {
		val = $(this).val()
	} else {
		val = $(this).attr("placeholder")
	}
	$(".js-link-show-txt").text(val);
})
$(document).on("keyup", ".js-link-tit", function() {
	var val;
	if($(this).val() != "") {
		val = $(this).val()
	} else {
		val = $(this).attr("placeholder")
	}
	$(".js-link-show-tit").text(val);
})
//链接地址
$(document).on("keyup", ".js-link-url", function() {
	$(".js-show-box").attr("data-url", $(this).val());
})
$(document).on("keyup", ".inp", function(e) {
	var ev = document.all ? window.event : e;
	if($(this).val() != '' && ev.keyCode == 13) {
		$("#ulAddNote").append('<li class="list-group-item"><div class="txt">' + $(this).val() + '</div><span class="badge del">删除</span></li>');
		$(this).val("");
	}
})
$(document).on("click", ".del", function() {
	$(this).parent().remove();
})
//读取本地图片
$("#addFile").change(function() {
	var file = $(this)[0].files[0];
	if(!/image\/\w+/.test(file.type)) {
		alert("请确保文件为图像类型");
		return false;
	}
	if(typeof FileReader === 'undefined') {
		result.innerHTML = "抱歉，你的浏览器不支持 FileReader";
		input.setAttribute('disabled', 'disabled');
	} else {
		var reader = new FileReader();
		reader.readAsDataURL(file);
		reader.onload = function(e) {
			var str = '<li class="list-group-item"><img class="pic" src="' + this.result + '" /><span class="badge del">删除</span></li>';
			$("#ulAddNote").append(str);
		}
	}
})
//读取本地图片
$("#addLinkImg").change(function() {
	var file = $(this)[0].files[0];
	if(!/image\/\w+/.test(file.type)) {
		alert("请确保文件为图像类型");
		return false;
	}
	if(typeof FileReader === 'undefined') {
		result.innerHTML = "抱歉，你的浏览器不支持 FileReader";
		input.setAttribute('disabled', 'disabled');
	} else {
		var reader = new FileReader();
		reader.readAsDataURL(file);
		console.log(file);
		reader.onload = function(e) {
			var str = '<img class="pic" src="' + this.result + '" />';
			$(".js-link-show-img").append(str);
		}
	}
})
//保存连接按钮
$("#btnOkOfAddLink").on("click", function() {
	var str = '<li class="list-group-item"><div class="txt">' + $(".js-show-box").html() + '</div><span class="badge del">删除</span></li>'
	$("#ulAddNote").append(str);
	$("#myModal1").modal("show");
	$("#myModal2").modal("hide");
	$(".js-link-tit").val("");
	$(".js-link-txt").val("");
	//				$(".js-link-show-img").empty();
})

//下拉菜单 按群选择/按标签选择
$("#selType").on("change", function() {
	if($("#selType").val() == '0') {
		$("#divselChatRoom").show();
		$("#divselTags").hide();
		$("#divselHelper").hide();
	} 
	else if($("#selType").val() == '1'){
		$("#divselTags").show();
		$("#divselChatRoom").hide();
		$("#divselHelper").hide();
	}
	else if($("#selType").val() == '2'){
		$("#divselHelper").show();
		$("#divselChatRoom").hide();
		$("#divselTags").hide();
	}
})
//选中群主后,@所有人按钮为禁止状态
$(".enable").on("change",function(){
	if($(this).val() == '1'){
		$(".radioEnable input").removeAttr("checked");
		$(".radioEnable input").attr("disabled","disabled");
	}else{
		$(".radioEnable input").removeAttr("disabled","disabled");
		$("#optionsRadios1").attr("checked","checked");
	}
})
//定时发送,选择时间
$(".radioTime input").click(function(){
	if($(this).val() == "option2"){
		$(".time").css("display","block");
	}
})
