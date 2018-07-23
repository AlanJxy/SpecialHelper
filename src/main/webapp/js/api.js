window.version = "v1";
//window.server = "http://47.92.135.231:12580/";
//window.server = "http://47.92.79.186:12580/";
//window.server = "http://192.168.58.81:12580/";
window.server = "http://192.168.58.177:12588/";
window.api = {
	
	addList: server + "tjDateQueryXinZeng", 
  	activeList: server + "tjDateQueryRiHuo", 
  	retainedList: server + "tjDateQueryLiuCun", 
  	topUpList: server + "tjDateQueryChongZhi"
	
};
// https://leancloud.cn/docs/rest_api.html#数据查询_API
//window.leadcloud = "https://api.leancloud.cn/1.1/";
//window.lc = {
//	headers: {
//		"X-Avoscloud-Application-Id": "nsPyHrF6uI8NyVRVtBd9SOiM-gzGzoHsz",
//		"X-Avoscloud-Application-Key": "aES2KiLlKzBINuOk0aNYBtdm,master"
//	},
//	getInfo: leadcloud + "stats/appinfo",
//	inTimeData: leadcloud + "stats/rtmetrics",
//	getData: leadcloud + "stats/appmetrics",
//}

/**
 * {MATCH_STATUS}
 * 
 * match_status_created: 0, //比赛创建阶段
 * match_status_sign_up: 1, //报名阶段
 * match_status_ready: 3, //准备开赛
 * match_status_started: 4, //PLAYING
 * match_status_end: 5, //比赛结束
 * PLAYING: 
 * match_sub_status_waiting_match: 0, //匹配中
 * match_sub_status_creating_room: 1, //创建比赛房间
 * match_sub_status_matching: 2 //比赛中
 */
//window.MATCH_STATUS = {
//	0: "比赛创建阶段",
//	1: "报名阶段",
//	3: "准备开赛",
//	4: {
//		0: "匹配中",
//		1: "创建比赛房间",
//		2: "比赛中"
//	},
//	5: "比赛结束"
//};