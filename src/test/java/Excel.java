import annotation.ExportExcel;
import annotation.ImportExcel;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.ReUtil;
import cn.hutool.core.util.ReflectUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import com.baomidou.mybatisplus.annotation.TableName;
import entites.DocumentCarInfoTimeFormatVO;
import org.junit.Test;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

public class Excel {

    @Test
    public void readExcel1() throws IllegalAccessException, InstantiationException {
        File file = new File("/Users/xiangyunan/Desktop/1662601635987.xlsx");
        ExcelReader reader = ExcelUtil.getReader(file);
        Class<DocumentCarInfoTimeFormatVO> clazz = DocumentCarInfoTimeFormatVO.class;
        //
        Field[] fields = ReflectUtil.getFields(clazz,field -> {
            ImportExcel importExcel = field.getAnnotation(ImportExcel.class);
            return !ObjectUtils.isEmpty(importExcel);
        });

        TableName tableNameAnnotation = clazz.getAnnotation(TableName.class);
        //表名
        String tableName = tableNameAnnotation.value();
        StringBuilder sb = new StringBuilder();
        List<String> tableField = new ArrayList<>(fields.length);
        sb.append("insert into" + tableName + "(");

        //字段名
        Arrays.stream(fields).forEach(field -> {
            //驼峰Field属性名转数据库字段名
            String name = decamelize(field.getName());
            tableField.add(name);
        });
        //以逗号隔开的字段名字符串 如： car_name,car_type...
        String sqlField = CollUtil.join(tableField, ",");
        sb.append(sqlField);
        sb.append(") values ");
        List<String> valueField = new ArrayList<>();
        int rowCount = reader.getRowCount();
        for(int i=1;i<rowCount;i++){
            List<Object> list = reader.readRow(i);
            if(ObjectUtil.isEmpty(list)){
                break;
            }
            valueField.add("(" + CollUtil.join(list, ",","'","'") + ")");
        }
        sb.append(CollUtil.join(valueField,","));
//        System.out.println(sb.toString());

    }

    @Test
    public void readExcel() throws IllegalAccessException, InstantiationException {
        File file = new File("/Users/xiangyunan/Desktop/1662601635987.xlsx");
        ExcelReader reader = ExcelUtil.getReader(file);
        List<Map<String, Object>> rows = reader.readAll();
        System.out.println(JSONUtil.toJsonStr(rows.get(2)));
        Class<DocumentCarInfoTimeFormatVO> clazz = DocumentCarInfoTimeFormatVO.class;
        Method[] methods = clazz.getMethods();
        Arrays.stream(methods).forEach(o -> System.out.println(o.getName()));
        Field[] fields = clazz.getDeclaredFields();
        AtomicReference<DocumentCarInfoTimeFormatVO> atomicReference = new AtomicReference<>();
        rows.stream().forEach(row -> {
            Arrays.stream(fields).forEach(field -> {
                ExportExcel annotation = field.getAnnotation(ExportExcel.class);
                //列中包含该字段
                if (!ObjectUtils.isEmpty(annotation) && row.containsKey(annotation.titleName())) {
                    try {
                        field.setAccessible(true);
                        Class<?> type = field.getType();
                        field.set(atomicReference.get(), row.get(annotation.titleName()));
                        field.setAccessible(false);
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }

            });

        });

    }

    /**
     * 驼峰式的实体类属性名转换为数据表字段名
     * @param camelCaseStr  驼峰式的实体类属性名
     * @return  转换后的以"_"分隔的数据表字段名
     */
    public static String decamelize(String camelCaseStr){
        return ObjectUtils.isEmpty(camelCaseStr) ? camelCaseStr : camelCaseStr.replaceAll("[A-Z]", "_$0").toLowerCase();
    }

}
