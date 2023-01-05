import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.date.LocalDateTimeUtil;
import cn.hutool.core.img.ImgUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.file.FileWriter;
import cn.hutool.core.io.resource.ClassPathResource;
import cn.hutool.core.lang.Console;
import cn.hutool.core.lang.Opt;
import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.*;
import cn.hutool.poi.word.Word07Writer;
import entites.HomeAddress;
import entites.LK;
import entites.LKDTO;
import entites.User;
import mapper.LKConverter;
import org.springframework.util.ObjectUtils;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.net.URL;
import java.net.URLConnection;
import java.text.DecimalFormat;
import java.time.Instant;
import java.util.*;
import java.util.List;

public class Test {
    public static void main(String[] args) throws IOException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Map<String,Object> map = new HashMap<>();
        map.put("a","1");
        map.put("b","2");
        map.put("c","3");
        map.put("d","4");


//        List<Object> list1 = new ArrayList<>();
            String[] a = new String[]{""};
        System.out.println(a.length);
        System.out.println(ObjectUtils.isEmpty(a));


//        List<Object> list2 = new ArrayList<>();
//        List<String> list3 = new ArrayList<>();
//        list1 = list2;
////        list1 = list3;  报错
//        list1.addAll(list2);
//        list1.addAll(list3);


//
//        Word07Writer writer = new Word07Writer();
//        // 添加段落（标题）
//        writer.addText(new Font("方正小标宋简体", Font.PLAIN, 22), "我是第一部分", "我是第二部分");
//        // 添加段落（正文）
//        writer.addText(new Font("宋体", Font.PLAIN, 22), "我是正文第一部分", "我是正文第二部分");
//        List<String> listTable = new ArrayList<>();
//        List<Map> listTable1 = new ArrayList<>();
//        listTable.add("a");
//        listTable.add("b");
//        listTable.add("c");
//        HashMap<String, String> objectObjectHashMap = new HashMap<>();
//        objectObjectHashMap.put("a","aaaa");
//        objectObjectHashMap.put("b","bbbb");
//        objectObjectHashMap.put("c","cccc");
//        objectObjectHashMap.put("d","dddd");
//        writer.addTable(listTable);
//        writer.addText(new Font("宋体", Font.PLAIN, 22), "我是正文第一部分", "我是正文第二部分");
//
//        listTable1.add(objectObjectHashMap);
//        HashMap<String, String> objectObjectHashMap1 = new HashMap<>();
//        objectObjectHashMap1.put("a","aaaa2");
//        objectObjectHashMap1.put("b","bbbb2");
//        objectObjectHashMap1.put("c","cccc2");
//        objectObjectHashMap1.put("d","dddd2");
//
//        listTable1.add(objectObjectHashMap1);
//        writer.addTable(listTable1);
//        writer.addPicture(new File("/Users/xiangyunan/Downloads/dfsk_doc_new/img/166192687530240117578782346587083565847.JPEG"),200,200);
//// 写出到文件
//        writer.flush(FileUtil.file("/Users/xiangyunan/Desktop/a.docx"));
//// 关闭
//        writer.close();




//        ImgUtil.scale(
//                FleUtil.file("/Users/xiangyunan/Downloads/dfsk_doc_new/img/166192687530240117578782346587083565847.JPEG"),
//                FileUtil.file("/Users/xiangyunan/Downloads/dfsk_doc_new/img/166192687530240117578782346587083565847.mini.JPEG"),
//                250,
//                250,
//                null
//        );




//        BigDecimal bigDecimal = new BigDecimal(0.533333);
//        System.out.println(new DecimalFormat("#.##%").format(bigDecimal));
//        String s = NumberUtil.decimalFormat("#.##%", bigDecimal);
//        System.out.println(s);
//
//        String[] str = new String[]{"a","v","c"};
//        Integer[] id = new Integer[]{1,3,5};
//        Map<String, Integer> map = ArrayUtil.zip(str, id);
//        System.out.println(map);
//        User user = new User();
//        ReflectUtil.invoke(user,"setAge",11);
//        System.out.println(user.getAge());
//        Constructor<User> constructor = ReflectUtil.getConstructor(User.class, String.class,int.class);
//        System.out.println(constructor.newInstance("13",111));
//        File file = FileUtil.file("/Users/xiangyunan/Desktop/工作记录.docx");
//        URLConnection urlConnection = url.openConnection();
//        urlConnection.connect()
//        System.out.println(3 < 2 ? 'a' : 98);
//        System.out.println(3 < 2 ? 'a' : 65535);
//        System.out.println(3 < 2 ? 'a' : 65536);
//        System.out.println(true ? 2 : 3.0);
//        System.out.println(true ? 2 : 3);
//        ReentrantLock a = new ReentrantLock();
//        new Thread(()->{
//            a.lock();
//        }).run();
//
//        a.unlock();
    }
}
