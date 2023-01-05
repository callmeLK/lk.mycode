package util;

import annotation.ExportExcel;
import cn.hutool.core.io.IoUtil;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Excel工具类
 * */
@Component
@Slf4j
public class ExcelUtils {

    /**
     * 导出为Excel
     * @param list 需要导出的数据
     * */
    public static void exportExcel(List<?> list, HttpServletResponse response) throws Exception{
        ExcelWriter writer = ExcelUtil.getWriter(true);
        writer.write(formartData4Map(list), true);

        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=utf-8");
        response.setHeader("Content-Disposition",String.format("attachment;filename=%s.xlsx",System.currentTimeMillis()));
        ServletOutputStream outputStream = response.getOutputStream();
        writer.flush(outputStream, true);
        writer.close();
        IoUtil.close(outputStream);
    }

    /**
     * 处理数据，导出需要导出的字段
     * @param list 原始数据
     * @return 生成的excel数据
     * */
    public static LinkedList<LinkedList<String>> formartData(List<?> list) throws ClassNotFoundException, IllegalAccessException {
        LinkedList<LinkedList<String>> re = new LinkedList<>();
        if (ObjectUtils.isEmpty(list)){
            return re;
        }
        String className =list.get(0).getClass().getName();
        Class<?> clazz = Class.forName(className);
        // 获取类的所有属性
        Field[] fields = clazz.getDeclaredFields();
        // 获取需要导出的属性
        LinkedList<Field> fieldList= new LinkedList<>();
        LinkedList<String> titles= new LinkedList<>();
        for (Field field : fields) {
            ExportExcel myField = field.getAnnotation(ExportExcel.class);
            try {
                if (!ObjectUtils.isEmpty(myField)){
                    fieldList.add(field);
                    titles.add(myField.titleName());
                }
            }catch (Exception ex){
                log.error(ex.toString());
                continue;
            }
        }

        //表头
        re.add(titles);
        //数据
        for (Object o:list){
            LinkedList<String> row = new LinkedList<>();
            for (Field field:fieldList){
                field.setAccessible(true);
                if (ObjectUtils.isEmpty(field.get(o))){
                    row.add("");
                }else {
                    row.add(field.get(o).toString());
                }
                field.setAccessible(false);
            }
            re.add(row);
        }

        return re;
    }

    /**
     * 处理数据，导出需要导出的字段
     * @param list 原始数据
     * @return 生成的excel数据
     * */
    public static LinkedList<Map<String,String>> formartData4Map(List<?> list) throws ClassNotFoundException, IllegalAccessException{
        LinkedList<Map<String,String>> re = new LinkedList<>();
        if (ObjectUtils.isEmpty(list)){
            return re;
        }
        String className =list.get(0).getClass().getName();
        Class<?> clazz = Class.forName(className);
        // 获取类的所有属性
        Field[] fields = clazz.getDeclaredFields();
        // 获取需要导出的属性
        LinkedList<Field> fieldList= new LinkedList<>();
        LinkedList<String> titles= new LinkedList<>();
        for (Field field : fields) {
            ExportExcel myField = field.getAnnotation(ExportExcel.class);
            try {
                if (!ObjectUtils.isEmpty(myField)){
                    fieldList.add(field);
                    titles.add(myField.titleName());
                }
            }catch (Exception ex){
                log.error(ex.toString());
                continue;
            }
        }

        for (int i = 0;i<list.size();i++){
            Map<String,String> map = new HashMap<>();
            for (Field field:fieldList){
                field.setAccessible(true);
                ExportExcel myField = field.getAnnotation(ExportExcel.class);
                if (ObjectUtils.isEmpty(field.get(list.get(i)))){
                    map.put(myField.titleName(),"");
                }else {
                    map.put(myField.titleName(),field.get(list.get(i)).toString());
                }
                field.setAccessible(false);
            }
            re.add(map);
        }

        return re;
    }




}
