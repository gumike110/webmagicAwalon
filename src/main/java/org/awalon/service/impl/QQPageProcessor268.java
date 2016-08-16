package org.awalon.service.impl;

import com.alibaba.fastjson.JSONObject;
import org.awalon.model.DataList;
import org.awalon.model.PhishResult;
import org.awalon.model.QQ268Model;
import org.awalon.model.QQ269Model;
import org.awalon.utils.JinshanUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by guming on 2016-08-15.
 */
@Service
public class QQPageProcessor268 implements PageProcessor {




    private Site site = Site.me();

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public void process(Page page) {

        Map<Integer,String> map = new HashMap<Integer, String>(){{
            put(268,"recommend_duzaikan");
            put(165,"recommend_jingpin");
            put(166,"icon");
            put(214,"rank_fast");
            put(215,"rank_top");
            put(178,"rank_new");
            put(206,"cate_txzq");
            put(167,"cate_lifehand");
            put(176,"cate_tools");
            put(169,"cate_funny");
            put(228,"cate_pic");
            put(170,"cate_movie");
            put(173,"cate_game");
            put(211,"cate_magazine");
            put(168,"cate_news");
            put(171,"cate_novel");
            put(172,"cate_tweet");
            put(174,"cate_shopping");
        }};

        List<String> list = new ArrayList<>();
        /*//推荐-大家都在看
        list.add("http://app.html5.qq.com/x5/ajax?action=getData&columnId=35&groupId=268&pos=0&size=1000");
        //推荐-精品推荐
        list.add("http://app.html5.qq.com/x5/ajax?action=getData&columnId=35&groupId=165&pos=0&size=1000");
        //名站
        list.add("http://app.html5.qq.com/x5/ajax?action=getData&columnId=37&groupId=166&pos=0&size=1000");
        //排行 上升最快
        list.add("http://app.html5.qq.com/x5/ajax?action=getData&columnId=38&groupId=214&pos=0&size=1000");
        //排行 人气最高
        list.add("http://app.html5.qq.com/x5/ajax?action=getData&columnId=38&groupId=215&pos=0&size=1000");
        //排行 最新上架
        list.add("http://app.html5.qq.com/x5/ajax?action=getData&columnId=38&groupId=178&pos=0&size=1000");
        //分类 腾讯专区 人气
        list.add("http://app.html5.qq.com/x5/ajax?action=getSortGroupDetails&ColumnId=36&GroupId=206&sortkey=1&pos=0");
        //最新
        list.add("http://app.html5.qq.com/x5/ajax?action=getSortGroupDetails&ColumnId=36&GroupId=206&sortkey=2&pos=0");

        //生活助手
        //人气
        list.add("http://app.html5.qq.com/x5/ajax?action=getSortGroupDetails&ColumnId=36&GroupId=167&sortkey=1&pos=0");
        //最新
        list.add("http://app.html5.qq.com/x5/ajax?action=getSortGroupDetails&ColumnId=36&GroupId=167&sortkey=2&pos=0");
        //实用工具
        //人气
        list.add("http://app.html5.qq.com/x5/ajax?action=getSortGroupDetails&ColumnId=36&GroupId=176&sortkey=1&pos=0");
        //最新
        list.add("http://app.html5.qq.com/x5/ajax?action=getSortGroupDetails&ColumnId=36&GroupId=176&sortkey=2&pos=0");
        //搞笑娱乐
        list.add("http://app.html5.qq.com/x5/ajax?action=getSortGroupDetails&ColumnId=36&GroupId=169&sortkey=1&pos=0");
        list.add("http://app.html5.qq.com/x5/ajax?action=getSortGroupDetails&ColumnId=36&GroupId=169&sortkey=2&pos=0");
        //高清美图
        list.add("http://app.html5.qq.com/x5/ajax?action=getSortGroupDetails&ColumnId=36&GroupId=228&sortkey=1&pos=0");
        list.add("http://app.html5.qq.com/x5/ajax?action=getSortGroupDetails&ColumnId=36&GroupId=228&sortkey=2&pos=0");
        //休闲影音
        list.add("http://app.html5.qq.com/x5/ajax?action=getSortGroupDetails&ColumnId=36&GroupId=170&sortkey=1&pos=0");
        list.add("http://app.html5.qq.com/x5/ajax?action=getSortGroupDetails&ColumnId=36&GroupId=170&sortkey=2&pos=0");
        //游戏地带
        list.add("http://app.html5.qq.com/x5/ajax?action=getSortGroupDetails&ColumnId=36&GroupId=173&sortkey=1&pos=0");
        list.add("http://app.html5.qq.com/x5/ajax?action=getSortGroupDetails&ColumnId=36&GroupId=173&sortkey=2&pos=0");
        //杂志专栏
        list.add("http://app.html5.qq.com/x5/ajax?action=getSortGroupDetails&ColumnId=36&GroupId=211&sortkey=1&pos=0");
        list.add("http://app.html5.qq.com/x5/ajax?action=getSortGroupDetails&ColumnId=36&GroupId=211&sortkey=2&pos=0");
        //新闻资讯
        list.add("http://app.html5.qq.com/x5/ajax?action=getSortGroupDetails&ColumnId=36&GroupId=168&sortkey=1&pos=0");
        list.add("http://app.html5.qq.com/x5/ajax?action=getSortGroupDetails&ColumnId=36&GroupId=168&sortkey=2&pos=0");
        //小说阅读
        list.add("http://app.html5.qq.com/x5/ajax?action=getSortGroupDetails&ColumnId=36&GroupId=171&sortkey=1&pos=0");
        list.add("http://app.html5.qq.com/x5/ajax?action=getSortGroupDetails&ColumnId=36&GroupId=171&sortkey=2&pos=0");
        //微博社交
        list.add("http://app.html5.qq.com/x5/ajax?action=getSortGroupDetails&ColumnId=36&GroupId=172&sortkey=1&pos=0");
        list.add("http://app.html5.qq.com/x5/ajax?action=getSortGroupDetails&ColumnId=36&GroupId=172&sortkey=2&pos=0");
        //超值购物
        list.add("http://app.html5.qq.com/x5/ajax?action=getSortGroupDetails&ColumnId=36&GroupId=174&sortkey=1&pos=0");
        list.add("http://app.html5.qq.com/x5/ajax?action=getSortGroupDetails&ColumnId=36&GroupId=174&sortkey=2&pos=0");*/

        //分类 腾讯专区 人气
        list.add("http://app.html5.qq.com/x5/ajax?action=getSortGroupDetails&ColumnId=36&GroupId=206&sortkey=1&pos=10");
        //最新
        list.add("http://app.html5.qq.com/x5/ajax?action=getSortGroupDetails&ColumnId=36&GroupId=206&sortkey=2&pos=10");

        //生活助手
        //人气
        list.add("http://app.html5.qq.com/x5/ajax?action=getSortGroupDetails&ColumnId=36&GroupId=167&sortkey=1&pos=10");
        //最新
        list.add("http://app.html5.qq.com/x5/ajax?action=getSortGroupDetails&ColumnId=36&GroupId=167&sortkey=2&pos=10");
        //实用工具
        //人气
        list.add("http://app.html5.qq.com/x5/ajax?action=getSortGroupDetails&ColumnId=36&GroupId=176&sortkey=1&pos=10");
        //最新
        list.add("http://app.html5.qq.com/x5/ajax?action=getSortGroupDetails&ColumnId=36&GroupId=176&sortkey=2&pos=10");
        //搞笑娱乐
        list.add("http://app.html5.qq.com/x5/ajax?action=getSortGroupDetails&ColumnId=36&GroupId=169&sortkey=1&pos=10");
        list.add("http://app.html5.qq.com/x5/ajax?action=getSortGroupDetails&ColumnId=36&GroupId=169&sortkey=2&pos=10");
        //高清美图
        list.add("http://app.html5.qq.com/x5/ajax?action=getSortGroupDetails&ColumnId=36&GroupId=228&sortkey=1&pos=10");
        list.add("http://app.html5.qq.com/x5/ajax?action=getSortGroupDetails&ColumnId=36&GroupId=228&sortkey=2&pos=10");
        //休闲影音
        list.add("http://app.html5.qq.com/x5/ajax?action=getSortGroupDetails&ColumnId=36&GroupId=170&sortkey=1&pos=10");
        list.add("http://app.html5.qq.com/x5/ajax?action=getSortGroupDetails&ColumnId=36&GroupId=170&sortkey=2&pos=10");
        //游戏地带
        list.add("http://app.html5.qq.com/x5/ajax?action=getSortGroupDetails&ColumnId=36&GroupId=173&sortkey=1&pos=10");
        list.add("http://app.html5.qq.com/x5/ajax?action=getSortGroupDetails&ColumnId=36&GroupId=173&sortkey=2&pos=10");
        //杂志专栏
        list.add("http://app.html5.qq.com/x5/ajax?action=getSortGroupDetails&ColumnId=36&GroupId=211&sortkey=1&pos=10");
        list.add("http://app.html5.qq.com/x5/ajax?action=getSortGroupDetails&ColumnId=36&GroupId=211&sortkey=2&pos=10");
        //新闻资讯
        list.add("http://app.html5.qq.com/x5/ajax?action=getSortGroupDetails&ColumnId=36&GroupId=168&sortkey=1&pos=10");
        list.add("http://app.html5.qq.com/x5/ajax?action=getSortGroupDetails&ColumnId=36&GroupId=168&sortkey=2&pos=10");
        //小说阅读
        list.add("http://app.html5.qq.com/x5/ajax?action=getSortGroupDetails&ColumnId=36&GroupId=171&sortkey=1&pos=10");
        list.add("http://app.html5.qq.com/x5/ajax?action=getSortGroupDetails&ColumnId=36&GroupId=171&sortkey=2&pos=10");
        //微博社交
        list.add("http://app.html5.qq.com/x5/ajax?action=getSortGroupDetails&ColumnId=36&GroupId=172&sortkey=1&pos=10");
        list.add("http://app.html5.qq.com/x5/ajax?action=getSortGroupDetails&ColumnId=36&GroupId=172&sortkey=2&pos=10");
        //超值购物
        list.add("http://app.html5.qq.com/x5/ajax?action=getSortGroupDetails&ColumnId=36&GroupId=174&sortkey=1&pos=10");
        list.add("http://app.html5.qq.com/x5/ajax?action=getSortGroupDetails&ColumnId=36&GroupId=174&sortkey=2&pos=10");

        //分类 腾讯专区 人气
        list.add("http://app.html5.qq.com/x5/ajax?action=getSortGroupDetails&ColumnId=36&GroupId=206&sortkey=1&pos=20");
        //最新
        list.add("http://app.html5.qq.com/x5/ajax?action=getSortGroupDetails&ColumnId=36&GroupId=206&sortkey=2&pos=20");

        //生活助手
        //人气
        list.add("http://app.html5.qq.com/x5/ajax?action=getSortGroupDetails&ColumnId=36&GroupId=167&sortkey=1&pos=20");
        //最新
        list.add("http://app.html5.qq.com/x5/ajax?action=getSortGroupDetails&ColumnId=36&GroupId=167&sortkey=2&pos=20");
        //实用工具
        //人气
        list.add("http://app.html5.qq.com/x5/ajax?action=getSortGroupDetails&ColumnId=36&GroupId=176&sortkey=1&pos=20");
        //最新
        list.add("http://app.html5.qq.com/x5/ajax?action=getSortGroupDetails&ColumnId=36&GroupId=176&sortkey=2&pos=20");
        //搞笑娱乐
        list.add("http://app.html5.qq.com/x5/ajax?action=getSortGroupDetails&ColumnId=36&GroupId=169&sortkey=1&pos=20");
        list.add("http://app.html5.qq.com/x5/ajax?action=getSortGroupDetails&ColumnId=36&GroupId=169&sortkey=2&pos=20");
        //高清美图
        list.add("http://app.html5.qq.com/x5/ajax?action=getSortGroupDetails&ColumnId=36&GroupId=228&sortkey=1&pos=20");
        list.add("http://app.html5.qq.com/x5/ajax?action=getSortGroupDetails&ColumnId=36&GroupId=228&sortkey=2&pos=20");
        //休闲影音
        list.add("http://app.html5.qq.com/x5/ajax?action=getSortGroupDetails&ColumnId=36&GroupId=170&sortkey=1&pos=20");
        list.add("http://app.html5.qq.com/x5/ajax?action=getSortGroupDetails&ColumnId=36&GroupId=170&sortkey=2&pos=20");
        //游戏地带
        list.add("http://app.html5.qq.com/x5/ajax?action=getSortGroupDetails&ColumnId=36&GroupId=173&sortkey=1&pos=20");
        list.add("http://app.html5.qq.com/x5/ajax?action=getSortGroupDetails&ColumnId=36&GroupId=173&sortkey=2&pos=20");
        //杂志专栏
        list.add("http://app.html5.qq.com/x5/ajax?action=getSortGroupDetails&ColumnId=36&GroupId=211&sortkey=1&pos=20");
        list.add("http://app.html5.qq.com/x5/ajax?action=getSortGroupDetails&ColumnId=36&GroupId=211&sortkey=2&pos=20");
        //新闻资讯
        list.add("http://app.html5.qq.com/x5/ajax?action=getSortGroupDetails&ColumnId=36&GroupId=168&sortkey=1&pos=20");
        list.add("http://app.html5.qq.com/x5/ajax?action=getSortGroupDetails&ColumnId=36&GroupId=168&sortkey=2&pos=20");
        //小说阅读
        list.add("http://app.html5.qq.com/x5/ajax?action=getSortGroupDetails&ColumnId=36&GroupId=171&sortkey=1&pos=20");
        list.add("http://app.html5.qq.com/x5/ajax?action=getSortGroupDetails&ColumnId=36&GroupId=171&sortkey=2&pos=20");
        //微博社交
        list.add("http://app.html5.qq.com/x5/ajax?action=getSortGroupDetails&ColumnId=36&GroupId=172&sortkey=1&pos=20");
        list.add("http://app.html5.qq.com/x5/ajax?action=getSortGroupDetails&ColumnId=36&GroupId=172&sortkey=2&pos=20");
        //超值购物
        list.add("http://app.html5.qq.com/x5/ajax?action=getSortGroupDetails&ColumnId=36&GroupId=174&sortkey=1&pos=20");
        list.add("http://app.html5.qq.com/x5/ajax?action=getSortGroupDetails&ColumnId=36&GroupId=174&sortkey=2&pos=20");

        // i = 0-5 用qq268, 其余的用qq269

        page.addTargetRequests(list);

        try{
            QQ268Model qq268Model = JSONObject.parseObject(page.getRawText(), QQ268Model.class);
            for(int k = 0 ; k < qq268Model.getDataList().size(); k ++){
                /*DataList dt = qq268Model.getDataList().get(k);
                String json = JinshanUtil.execute(dt.getTo_href());
                PhishResult result = JSONObject.parseObject(json,PhishResult.class);
                if(result.getSuccess().intValue() == 0){
                    dt.setCode(0);
                }else{
                    dt.setCode(1);
                    dt.setPhish(result.getPhish().intValue());
                }
                mongoTemplate.save(dt, "qq_" + map.get(Integer.valueOf(qq268Model.getGroupId())));*/
            }
        }catch (Exception e){
            QQ269Model qq269Model = JSONObject.parseObject(page.getRawText(), QQ269Model.class);
            List<DataList> lists = qq269Model.getData();
            if (page.getUrl().toString().indexOf("sortkey=2") > -1) {
                for(int k = 0 ; k < lists.size(); k ++){
                    DataList dt = lists.get(k);
                    String json = JinshanUtil.execute(dt.getTo_href());
                    PhishResult result = JSONObject.parseObject(json,PhishResult.class);
                    if(result.getSuccess().intValue() == 0){
                        dt.setCode(0);
                    }else{
                        dt.setCode(1);
                        dt.setPhish(result.getPhish().intValue());
                    }
                    mongoTemplate.save(dt, "qq_" + map.get(Integer.valueOf(lists.get(0).getGroupID())) + "_1");
                }
            } else {
                for(int k = 0 ; k < qq269Model.getData().size(); k ++){
                    DataList dt = lists.get(k);
                    String json = JinshanUtil.execute(dt.getTo_href());
                    PhishResult result = JSONObject.parseObject(json,PhishResult.class);
                    if(result.getSuccess().intValue() == 0){
                        dt.setCode(0);
                    }else{
                        dt.setCode(1);
                        dt.setPhish(result.getPhish().intValue());
                    }
                    mongoTemplate.save(dt, "qq_" + map.get(Integer.valueOf(lists.get(0).getGroupID())) + "_0");
                }
            }
        }
    }

    @Override
    public Site getSite() {
        return site;
    }

    public static void main(String[] args) {

        /*ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:/META-INF/spring/applicationContext.xml");
        final QQPageProcessor268 process = applicationContext.getBean(QQPageProcessor268.class);
        Spider.
                create(process).
                addUrl("http://app.html5.qq.com/x5/ajax?action=getData&columnId=35&groupId=268&pos=0&size=1000")
                .addPipeline(new JsonFilePipeline("D:\\webmagic\\")).run();*/
        String json = JinshanUtil.execute("baidu.com");
        System.out.println(json);
    }
}
