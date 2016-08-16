package org.awalon.webmagic;

import org.awalon.utils.JsonUtil;
import org.awalon.model.UrlRankModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.PageModelPipeline;


@Service
public class TopChinazPipeline implements PageModelPipeline<UrlRankModel> {

    //@Autowired
    //private UrlRankMapper urlMappper;
    
    @Autowired
    private MongoTemplate mongoTemplate;
    
	private String mongoTable = "url_rank_";

	@Override
	public void process(UrlRankModel t, Task task) {
		
		System.out.println(mongoTable);
		mongoTemplate.insert(t,mongoTable);
		//BeanUtils.copyProperties(t, urlRank);
		//urlMappper.insert(urlRank);
		System.out.println(JsonUtil.toJson(t));
	}
	
	public void initialize(String updateTime){
		this.mongoTable += updateTime;
	}
	

}
