$(function() {
	window.onresize = function() {
		resize();
	}

	function resize() {
		//html:20px: 16rem 320px
		//html:40px: 16rem 640px
		var base = 16; //rem

		var wid = $(window).width();
		var hei = $(window).height();
		if(hei < 460) { //适应个别高度较矮的设备
			$('html').css('font-size', '18px');
			return;
		}
		if(wid < 320) {
			$('html').css('font-size', '10px');
			return;
		}
		if(wid > 640) {
			$('html').css('font-size', '20px');
			return;
		}
		$('html').css('font-size', wid / 16 + 'px'); //在320和640之间，除以16的基数，来算font-size
	}
	resize();
	if($("#beginDate").val() == '') {
		$("#beginDate").val(moment().subtract(7, 'days').format('YYYY/MM/DD'));
	}
	if($("#endDate").val() == '') {
		$("#endDate").val(moment().subtract(1, 'days').format('YYYY/MM/DD'));
	}
	if($("#startTime").val() == '') {
		$("#startTime").val(moment().subtract(7, 'days').format('YYYY/MM/DD'));
	}
	if($("#endTime").val() == '') {
		$("#endTime").val(moment().subtract(1, 'days').format('YYYY/MM/DD'));
	}
	//  最近7天按钮
	$(".dropdown-menu a").click('click', function() {
		$(this).parents(".btn-group").find(".date1").text($(this).text());
	})

	var d1 = new Date($("#beginDate").val()).getTime();
	var d2 = new Date($("#endDate").val()).getTime();
	var dd1 = new Date($("#startTime").val()).getTime();
	var dd2 = new Date($("#endTime").val()).getTime();
	paintChart(d1, d2);
	setTable(dd1, dd2);

	var date1 = (new Date().valueOf() - 1000 * 60 * 60 * 24 * 7);
	var date2 = (new Date().valueOf() - 1000 * 60 * 60 * 24);
	var date3 = (new Date().valueOf() - 1000 * 60 * 60 * 24 * 30);
	var date4 = (new Date().valueOf() - 1000 * 60 * 60 * 24 * 15);
	var localStr1 = new Date(date1).toLocaleDateString();
	var localStr2 = new Date(date2).toLocaleDateString();
	var localStr3 = new Date(date3).toLocaleDateString();
	var localStr4 = new Date(date4).toLocaleDateString();

	$("#beginDate,#endDate").change(function() {
		if($("#beginDate").val() != '' && $("#endDate").val() != '') {
			var d1 = new Date($("#beginDate").val()).getTime();
			var d2 = new Date($("#endDate").val()).getTime();
			paintChart(d1, d2);
		}
	});
	$("#a7,#a30").on('click', function() {
		if($(this).attr("id") == 'a7') {
			$("#beginDate").val(localStr1);
			$("#endDate").val(localStr2);
			var d1 = new Date($("#beginDate").val()).getTime();
			var d2 = new Date($("#endDate").val()).getTime();
			paintChart(d1, d2);
		}
		if($(this).attr("id") == 'a30') {
			$("#beginDate").val(localStr3);
			$("#endDate").val(localStr2);
			var d1 = new Date($("#beginDate").val()).getTime();
			var d2 = new Date($("#endDate").val()).getTime();
			paintChart(d1, d2);
		}

	});

	$("#startTime,#endTime").change(function() {
		if($("#startTime").val() != '' && $("#endTime").val() != '') {
			var d1 = new Date($("#startTime").val()).getTime();
			var d2 = new Date($("#endTime").val()).getTime();
			setTable(d1, d2);
		}
	});
	$("#a7z,#a15z").on('click', function() {
		if($(this).attr("id") == "a7z") {
			$("#startTime").val(localStr1);
			$("#endTime").val(localStr2);
			var d1 = new Date($("#startTime").val()).getTime();
			var d2 = new Date($("#endTime").val()).getTime();
			setTable(d1, d2);
		}
		if($(this).attr("id") == "a15z") {
			$("#startTime").val(localStr4);
			$("#endTime").val(localStr2);
			var d1 = new Date($("#startTime").val()).getTime();
			var d2 = new Date($("#endTime").val()).getTime();
			setTable(d1, d2);
		}
	});
});

//折线图
function paintChart(d1, d2) {
	var ds = (new Date(d2) - new Date(d1)) / (1000 * 60 * 60 * 24);
	var arrDays = [];
	for(var i = 0; i <= ds; i++) {
		var d = new Date(d1).valueOf() + 1000 * 60 * 60 * 24 * i;
		var str = new Date(d).toLocaleDateString();
		arrDays.push(str);
	}
	$.ajax({
		url: api.retainedList + "?appid=51888",
		type: "GET",
		cache: "false",
		data: {
			starttime: d1,
			endtime: d2
		},
		dataType: "json",
		success: function(dt) {
			console.log(dt);
			var arrDate1 = [];
			var arrDate2 = [];
			var arrDate3 = [];
			if(dt != null && dt.length > 0) {
				for(var i = 0; i < dt.length; i++) {
					arrDate1.push(dt[i].CiRiLiuCunCount);
					arrDate2.push(dt[i].QiRiLiuCunCount);
					arrDate3.push(dt[i].ShiSiRiLiuCunCount);
				}
				chart(arrDays, arrDate1, arrDate2, arrDate3);
			}
		}
	})
}
//获取表格数据
function setTable(d1, d2) {
	$("#tb").empty();
	$.ajax({
		url: api.retainedList + "?appid=51888",
		type: "GET",
		cache: "false",
		data: {
			starttime: d1,
			endtime: d2
		},
		dataType: "json",
		success: function(data) {
			var html = "";
			for(var i = 0; i < data.length; i++) {
				html += "<tr>"
				html += "	<td>" + data[i].TjDate + "</td>"
				html += "	<td>" + data[i].CiRiLiuCunCount + "</td>"
				html += "	<td>" + data[i].QiRiLiuCunCount + "</td>"
				html += "</tr>"
			}
			$("#tb").append(html);
		}
	})
}

function chart(xdate, ydate1, ydate2, ydate3) {
	var myChart = echarts.init(document.getElementById("mychart"));
	var colors = ['#675bba', '#d14a61', '#2ea7e0'];

	var option = {
		color: colors,

		tooltip: {
			trigger: 'axis',
			axisPointer: {
				type: 'cross'
			}
		},
		grid: {
			left: '2%',
			right: '13%',
			bottom: '2%',
			containLabel: true
		},

		legend: {
			top: '3.5%',
			left: 'center',
			data: ['游戏局数', '房卡消耗', '群总数']
		},
		xAxis: [{
			type: 'category',
			axisTick: {
				alignWithLabel: true
			},
			splitLine: {
				show: true,
				lineStyle: {
					color: '#f1f1f1'
				}
			},
			data: xdate
		}],
		yAxis: [{
				type: 'value',
				name: '房卡消耗',
				position: 'right',
				axisLine: {
					lineStyle: {
						color: colors[0]
					}
				},
				splitLine: false,
			},
			{
				type: 'value',
				name: '群总数',
				position: 'right',
				offset: 80,
				axisLine: {
					lineStyle: {
						color: colors[1]
					}
				},
				splitLine: false
			},
			{
				type: 'value',
				name: '游戏局数',
				position: 'left',
				axisLine: {
					lineStyle: {
						color: colors[2]
					}
				},
				splitLine: {
					lineStyle: {
						color: '#f1f1f1'
					}
				}

			}
		],
		series: [{
				name: '房卡消耗',
				type: 'line',
				data: ydate1,
				label: {
					normal: {
						show: true,
						position: 'top'
					}
				}
			},
			{
				name: '群总数',
				type: 'line',
				yAxisIndex: 1,
				data: ydate2,
				label: {
					normal: {
						show: true,
						position: 'top'
					}
				}
			},
			{
				name: '游戏局数',
				type: 'line',
				yAxisIndex: 2,
				data: ydate3,
				label: {
					normal: {
						show: true,
						position: 'top'
					}
				}
			}
		]
	};
	window.onresize = function() {
		myChart.resize();
	}
	myChart.setOption(option);
}