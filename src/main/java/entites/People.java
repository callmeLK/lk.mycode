package entites;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class People implements Externalizable {
    private static final long serialVersionUID = -666325465629169812L;
    private User user;
    private String userName;
    private int age;
    private String a;

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeObject(userName);
        out.writeInt(age);
        out.writeObject(user);
        out.writeObject(a);
    }

    //顺序与上面方法必须相同，否则报错
    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        userName = (String) in.readObject();
        age = in.readInt();
        user = (User) in.readObject();
        a = (String) in.readObject();

    }
}
