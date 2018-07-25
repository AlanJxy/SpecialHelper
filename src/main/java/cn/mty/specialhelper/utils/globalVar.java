package cn.mty.specialhelper.utils;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import org.apache.ibatis.builder.StaticSqlSource;
import org.aspectj.weaver.patterns.ThisOrTargetAnnotationPointcut;

public class globalVar {
	//这个不行啊，  多开后就不行了
	public static String mUin;

	public static Gson gson = new Gson();


	private static String _houTaiAddress ="http://192.168.58.221:8080/SpecialHelper/";
	private static List<Flock> listFlock=new ArrayList<Flock>();

	public static String getHouTaiAddress() {
		return globalVar._houTaiAddress;
	}

	public static List<Flock> getListFlock() {
		return globalVar.listFlock;
	}
	public static void addQunToList(Flock flock) {
		globalVar.listFlock.add(flock);
	}
}
