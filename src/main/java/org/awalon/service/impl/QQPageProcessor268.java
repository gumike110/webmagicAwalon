package org.awalon.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.mongodb.DBCollection;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.awalon.XlsFilePipeline;
import org.awalon.model.DataList;
import org.awalon.model.PhishResult;
import org.awalon.model.QQ268Model;
import org.awalon.model.QQ269Model;
import org.awalon.utils.JinshanUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.pipeline.JsonFilePipeline;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Json;

import java.io.*;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;

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
        //推荐-大家都在看
        list.add("http://app.html5.qq.com/x5/ajax?action=getData&columnId=35&groupId=268&pos=0&size=1000");
        /*//推荐-精品推荐
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
        list.add("http://app.html5.qq.com/x5/ajax?action=getSortGroupDetails&ColumnId=36&GroupId=174&sortkey=2&pos=0");

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
*/
        // i = 0-5 用qq268, 其余的用qq269

        page.addTargetRequests(list);

        try{
            QQ268Model qq268Model = JSONObject.parseObject(page.getRawText(), QQ268Model.class);
            page.putField("qq_"+map.get(Integer.valueOf(qq268Model.getGroupId())),qq268Model.getDataList());
            /*for(int k = 0 ; k < qq268Model.getDataList().size(); k ++){
                DataList dt = qq268Model.getDataList().get(k);
                String json = JinshanUtil.execute(dt.getTo_href());
                PhishResult result = JSONObject.parseObject(json,PhishResult.class);
                if(result.getSuccess().intValue() == 0){
                    dt.setCode(0);
                }else{
                    dt.setCode(1);
                    dt.setPhish(result.getPhish().intValue());
                }
                //mongoTemplate.save(dt, "qq_" + map.get(Integer.valueOf(qq268Model.getGroupId())));
            }*/
        }catch (Exception e){
            QQ269Model qq269Model = JSONObject.parseObject(page.getRawText(), QQ269Model.class);
            /*List<DataList> lists = qq269Model.getData();
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
                    //mongoTemplate.save(dt, "qq_" + map.get(Integer.valueOf(lists.get(0).getGroupID())) + "_1");
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
                    //mongoTemplate.save(dt, "qq_" + map.get(Integer.valueOf(lists.get(0).getGroupID())) + "_0");
                }
            }*/
        }
    }

    @Override
    public Site getSite() {
        return site;
    }


    private static String getter(String field){
        if(field.startsWith("s")){
            return "get" + field;
        }else{
            return "get" + field.substring(0,1).toUpperCase() + field.substring(1);
        }


    }

    public static void main(String[] args) throws NoSuchMethodException, IOException, InvocationTargetException, IllegalAccessException {

        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:/META-INF/spring/applicationContext.xml");
        final QQPageProcessor268 process = applicationContext.getBean(QQPageProcessor268.class);
        Spider.
                create(process).
                addUrl("http://app.html5.qq.com/x5/ajax?action=getData&columnId=35&groupId=268&pos=0&size=1000")
                .addPipeline(new XlsFilePipeline<DataList>("D:\\webmagic\\","qq_m5")).run();
        /*Set<String> tableNames = process.mongoTemplate.getCollectionNames();
        HSSFWorkbook workbook = new HSSFWorkbook();
        for(String tableName : tableNames){
            HSSFSheet sheet = workbook.createSheet(tableName);
            HSSFRow row = null;
            List<DataList> dataLists = process.mongoTemplate.findAll(DataList.class,tableName);
            int index = 0;
            for(DataList dataList : dataLists){
                row = sheet.createRow(index);
                index ++;
                Field[] fileds = dataList.getClass().getDeclaredFields();
                for(short i = 0 ; i < fileds.length ; i ++){
                    HSSFCell cell = row.createCell(i);
                    Class c = dataList.getClass();
                    Method method = c.getMethod(getter(fileds[i].getName()),new Class[]{});

                    Object value = method.invoke(dataList,new Object[] {});
                    cell.setCellValue(value == null ? "" : value.toString());
                }
            }
        }

        OutputStream out = new FileOutputStream("D:\\111.xls");
        workbook.write(out);*/


        /*try {
            URL url = new URL("http://push.browser.server.nubia.cn/push.action");
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setDoInput(true);
            urlConnection.setDoOutput(true);

            urlConnection.setRequestMethod("POST");
            DataOutputStream out = null;
            OutputStream outputStream = null;
            outputStream = urlConnection.getOutputStream();
            out = new DataOutputStream(new BufferedOutputStream(outputStream,256));
            out.writeBytes("receiver_value_rule=1&package=cn.nubia.browser&msg_digest=&receiver_value_split=%2C&verify_code=9E61B9839DA99133AE33BCDFF781C60C&platform=1&receiver_type=2&receiver_value=NX613J_Z2_CN_ABCDEFGHIJK108%2CNX513J_Z3_CN_ABCDEFGHIJK109%2CNX508J_Z0_CN_LSZ001JF00J139%2CNX508J_Z0_CN_LSZ001JF00J149%2CNX510J_Z0_CN_xxxxxxxxxxx111%2CNX510J_Z0_CN_xxxxxxxxxxx113%2CN958St_Z0_CN_JSXPH1ID00H123%2CNX513J_Z78_CN_CSXPK1ID00J152%2CNX535J_Z0_CN_2SXPO1GF00K126&app_key=Vjp3SUIE7ZIKl%2151vfzsbniD%40dK%21qu3v&time_to_live=86400&msg_content=%7B%22title%22%3A%22%E5%8F%B0%E9%A3%8E%E7%94%B5%E6%AF%8D%E8%A2%AD%E6%B5%B7%E5%8F%A3%22%2C%22type%22%3A2%2C%22content%22%3A%22%22%2C%22digest%22%3A%22%E5%8F%B0%E9%A3%8E%E7%94%B5%E6%AF%8D%E8%A2%AD%E6%B5%B7%E5%8F%A3%22%2C%22link%22%3A%22%22%2C%22icon_url%22%3A%22%22%2C%22img_url%22%3A%22%22%2C%22extention%22%3A%22%22%2C%22priority%22%3A2%2C%22id%22%3A1471584291938%7D&msg_extras=&send_no=1471584291938");
            urlConnection.connect();
            StringBuilder sb = new StringBuilder();
            InputStream in = urlConnection.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(in,"UTF-8"));
            String line = "";
            while( (line = reader.readLine()) != null){
                sb.append(line);
            }
            System.out.println(sb.toString());

        } catch (MalformedURLException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }*/
    }

    private static void exportExcel(OutputStream out){

    }
}
