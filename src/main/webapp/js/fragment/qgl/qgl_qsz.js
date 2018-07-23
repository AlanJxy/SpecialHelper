//-------------------------------------------------
//欢迎语设置
//添加文字
$(".js-txt-add").click(function() {
	$(".source-box textarea").focus();
})
$(".source-box textarea").blur(function() {
	var val;
	if($(this).val() != '') {
		val = $(this).val();
		$(".source").append('<div class="row2"><div class="left">' + val + '</div><div class="right"><a class="del" href="javascript:void(0)">x</a></div></div>');
	}
	$(this).val('');
})
//添加图片
$("#aImg").change(function() {
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
			var str = '<div class="row2"><div class="left"><img src="' + this.result + '" /></div><div class="right"><a class="del" href="javascript:void(0)">x</a></div></div>';
			$(".source").append(str);
		}
	}
})
//删除
$(".source").on("click", ".del", function() {
	$(this).parents(".row2").remove();
})
//添加录音
$(".js-voice-add").click(function() {
	$(".voice-rec").show();
})
$(".rec-btn").click(function() {
	if($(this).attr("data-mode") == 0) {
		$(this).attr("data-mode", 1).text("停止录音");
		$(".voice-rec").addClass("active");

	} else {
		$(".voice-rec").hide().removeClass("active");
		$(this).attr("data-mode", 0).text("开始录音");
		var str = '<div class="row2">' +
			'<div class="left"><div onclick="javascript:play(this)" class="voice-box"><audio id="audio" src="js/fragment/qgl/test.wav" controls loop></audio>" </div></div>' +
			'<div class="right"><a href="javascript:void(0)" class="del">×</a></div>' +
			'</div>';
		$(".source").append(str);
	}
})

function startRecord() {
	wx.startRecord({
		success: function() {
			START = new Date().getTime();
			wx.onVoiceRecordEnd({
				// 录音时间超过一分钟没有停止的时候会执行 complete 回调
				complete: function(res) {
					alert('最多只能录制一分钟');
					var localId = res.localId;
					uploadluyin(localId, 60000);
				}
			});
		},
		cancel: function() {
			alert('用户拒绝授权录音');
			return false;
		}
	});
}

function stopRecord() {
	END = new Date().getTime();
	//录音时间
	luyintime = END - START;
	if(luyintime < 2000) {
		END = 0;
		START = 0;
		wx.stopRecord({});
		alert('录音时间不能少于2秒');
		return false;
		//小于300ms，不录音
	} else {
		wx.stopRecord({
			success: function(res) {
				localId = res.localId;
				uploadluyin(localId, luyintime);

			}
		});
	}
}
function uploadluyin(localId, luyintime) {
	wx.uploadVoice({
		localId: localId, // 需要上传的音频的本地ID，由stopRecord接口获得
		isShowProgressTips: 1, // 默认为1，显示进度提示
		success: function(res) {
			var serverId = res.serverId; // 返回音频的服务器端ID
			console.log(serverId);

			$.post("/home/xishanluyin/scyuyin", {
					"serverId": serverId,
					"luyintime": luyintime
				},
				function(data) {
					if(data.success == 1) {
						alert('录音成功');
					} else {
						alert(data.msg);
					}
				}, "json");
		}
	})
}
//播放录音
function play(obj) {
	var audio = document.getElementById('audio');
	console.log(audio);
	if(audio.paused) {
		audio.play();
	} else {
		audio.pause();
	}
}

//-------------------------------------------------
//游戏设置
$("#cbnIsAA1").click(function(){
	if(this.checked == true){
		$(".chk-07171156-box").addClass("hidden");
	}
})
function showulNew(){
	$("#ulNew").removeClass("hidden");
}

//空间设置
$("#aChatRoomOver").click(function(){
	$(".destroy").show();
})
$("#aShowSelectUser").click(function(){
	$(".alert-seluser").show();
})
