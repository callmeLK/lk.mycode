import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import entites.People;
import entites.User;
import org.apache.commons.lang3.SerializationUtils;
import org.junit.Test;
import sun.nio.ch.IOUtil;

import java.io.*;

public class Serializable {
    /**
     * Serializable接口 ：序列化与反序列化
     * @throws IOException
     */
    @Test
    public void SerialDemo1() throws IOException {
        User user = new User();
        user.setAge(11);
        user.setUserName("张三");
        System.out.println(user);
        ObjectOutputStream oos = null;
        //序列化people
        try {
            oos = new ObjectOutputStream(new FileOutputStream("people"));
            oos.writeObject(user);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            oos.close();
        }
        ObjectInputStream ois = null;
        //反序列化people
        File file = new File("people");
        ois = new ObjectInputStream(new FileInputStream(file));
        try {
            User o = (User) ois.readObject();
            System.out.println(o);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }finally {
            ois.close();
            if(file.exists()){
                file.delete();
            }
        }

    }
    /**
     * Externalizable接口 ：序列化与反序列化
     * @throws IOException
     */

    @Test
    public void ExternalDemo() throws IOException {
        User user = new User();
        user.setAge(11);
        user.setUserName("张三");
        People people = new People();
        people.setUser(user);
        people.setAge(22);
        people.setUserName("李四");
        people.setA("aaa");
        System.out.println(people);
        ObjectOutputStream oos = null;
        //序列化people
        try {
            oos = new ObjectOutputStream(new FileOutputStream("people"));
            oos.writeObject(people);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            oos.close();
        }
        ObjectInputStream ois = null;
        //反序列化people
        File file = new File("people");
        ois = new ObjectInputStream(new FileInputStream(file));
        try {
            People o = (People) ois.readObject();
            System.out.println(o);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }finally {
            ois.close();
            if(file.exists()){
                file.delete();
            }
        }

    }

    /**
     * 实用序列化实现深拷贝
     */
    @Test
    public void DeepClone(){
        User user = new User();
        user.setAge(1);
        user.setUserName("张三");
        //使用fastJson深拷贝
        User newUser = JSON.parseObject(JSON.toJSONString(user),User.class);
        System.out.println(user == newUser);
        System.out.println(newUser);
        System.out.println();
        //使用hutool工具包实现深拷贝
        User user1 = JSONUtil.toBean(JSON.toJSONString(user), User.class);
        System.out.println(user1);
        System.out.println();
        //使用SerializationUtils实现
        User newUser1 = SerializationUtils.clone(user);
        System.out.println(newUser1);
        System.out.println(newUser1 == user);

    }
}
