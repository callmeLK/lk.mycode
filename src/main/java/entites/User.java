package entites;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class User implements Serializable {

    private static final long serialVersionUID = 8435215968653562430L;
    private String userName;
    private int age;

    //如果被序列化的类中定义了writeObject和readObject方法，在序列化的时候会调用writeObject方法，反序列化的时候会调用readObject方法
    private void writeObject(ObjectOutputStream s) throws IOException {
        //不加会报错
        s.defaultWriteObject();
        System.out.println("out");
    }

    private void readObject(ObjectInputStream i) throws IOException, ClassNotFoundException {
        //不加会报错
        i.defaultReadObject();
        System.out.println("in");
    }
}
