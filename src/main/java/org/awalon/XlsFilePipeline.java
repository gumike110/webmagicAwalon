package org.awalon;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;
import us.codecraft.webmagic.utils.FilePersistentBase;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * Created by guming on 2016-08-23.
 */
public class XlsFilePipeline<T> extends FilePersistentBase implements Pipeline {

    private String fileName = "";

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    public XlsFilePipeline() {
        this.setPath("/data/webmagic");
        this.setFileName(UUID.randomUUID().toString());
    }

    public XlsFilePipeline(String path,String fileName) {
        this.setPath(path);
        this.setFileName(fileName);
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public Logger getLogger() {
        return logger;
    }

    public void setLogger(Logger logger) {
        this.logger = logger;
    }

    @Override
    public void process(ResultItems resultItems, Task task) {
        String path = this.path + PATH_SEPERATOR + task.getUUID() + PATH_SEPERATOR;
        try {
            HSSFWorkbook hssfWorkbook = new HSSFWorkbook();
            Map<String, Object> map = resultItems.getAll();
            Iterator it = map.keySet().iterator();
            while (it.hasNext()){
                String key = it.next().toString();
                HSSFSheet sheet = hssfWorkbook.createSheet(key);
                HSSFRow row = null;
                int index = 1;
                List<T> list = (List<T>)map.get(key);
                for(int i = 0 ; i < list.size(); i ++){
                    T t = list.get(i);
                    Class c = t.getClass();
                    Field[] fields = c.getDeclaredFields();
                    //先创建headers
                    if(i == 0){
                       row = sheet.createRow(0);
                       for(int k = 0 ; k < fields.length; k ++){
                           HSSFCell cell = row.createCell(k);
                           cell.setCellValue(fields[k].getName());
                       }
                    }
                    row = sheet.createRow(index);
                    index ++;
                    for(int k = 0 ; k < fields.length; k ++){
                        HSSFCell cell = row.createCell(k);
                        Method method = c.getMethod(getter(fields[k].getName(),"s"),new Class[]{});
                        Object value = method.invoke(t,new Object[]{});
                        //TODO 先全部按照string插入，后期可以优化成 根据类型反射插入不同值
                        cell.setCellValue(value == null ? "" : value.toString());
                    }
                }
            }
            OutputStream outputStream = new FileOutputStream(this.getFile(path + fileName + ".xls"));
            hssfWorkbook.write(outputStream);
        }catch (Exception e){
            logger.error(e.getMessage());
        }

    }

    /**
     * 有些get方法并没有首字母大写，所以需自己在pattern中过滤
     * @param field
     * @param pattern
     * @return
     */
    private static String getter(String field,String pattern){
        if(field.startsWith(pattern)){
            return "get" + field;
        }else{
            return "get" + field.substring(0,1).toUpperCase() + field.substring(1);
        }


    }
}
