package org.awalon.task;

import org.awalon.webmagic.TopChianzSpider;
import org.awalon.webmagic.TopChinazHookSpider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Spider;

@Component
public class SpiderTask {
	
	@Autowired
	private TopChinazHookSpider hookChinazSpider;
	
	@Autowired
	private TopChianzSpider chinazSpide;

	
	//@Scheduled(cron="0/1 * * * * ? ")//每十秒执行一次
	public void run(){	
		//钩子爬虫启用同步抓取并拿到结果
        ResultItems result = Spider.create(hookChinazSpider).thread(1).get("http://top.chinaz.com/all/");
        String updateTime = result.get("updateTime");
        System.out.println(updateTime);
        //如果钩子爬虫发现页面数据有更新就调用真正的爬虫爬取数据
        if(null != updateTime){
        	chinazSpide.crawl(updateTime);
        }
		
	}
	
	public static void main(String[] args) {
		 ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:/META-INF/spring/applicationContext.xml");
		 applicationContext.getBean(SpiderTask.class).run();
	}
	
}

