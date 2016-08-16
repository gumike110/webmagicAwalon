package org.spf.heat;

import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.awalon.utils.HttpClientUtil;

public class HeatTest {
	
	private String appKey = "58494877db015175d2beb4f53239b773";
	private String urlGet ="http://v.juhe.cn/alexaApi/historical.php";//请求接口地址

	@Test
	public void testHistory(){
		Map<String,String> params = new HashMap<String,String>();//请求参数
        params.put("site","baidu.com");//域名如:juhe.cn ,1jing.com
        //params.put("dtype","");//返回类型,xml/json可选
        params.put("key",appKey);//您申请的APPKEY
        System.out.println(HttpClientUtil.executePost(params, urlGet));
	}
	
	@Test
	public void testUrlEncoder(){
		String test = "http://m.hao123.com/j.php?tn=ops1015147az=2&ver=2_ios&level=2&pos=aihao_mingzhan_ios&page=m_aihao&qt=tz&url=http%3A%2F%2Fm.3533.com%2Fbizhi%2F0%2F&key=f60ce508033b09b1fe6634d3a35f786d";
		System.out.println(URLDecoder.decode(test));
	}
	
	public void exportURL(){
		String url="http://localhost:9200/nubrowser_suggest_idx/suggest_content/_search?size=10000&from=0";
		HttpClientUtil.executeGet(null, url);
	}
}
