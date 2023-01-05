package entites;

import java.io.Serializable;

public class Singleton implements Serializable {
    private static final long serialVersionUID = -8065425516468104408L;
    private static volatile Singleton singleton;
    private Singleton(){
        //防止反射破坏单例模式
        if(singleton != null){
            throw new RuntimeException("单例对象只能创建一次");
        }
    }
    public static Singleton getSingleton(){
        if(singleton == null){
            synchronized (Singleton.class){
                if(singleton == null){
                    singleton = new Singleton();
                }
            }
        }
        return singleton;
    }
    //防止反序列化破坏单例模式
    //反序列化的时候会判断被反序列化的类是否有该方法，如果有，在反序列化时会通过反射调用该方法，得到该对象。
    private Object readResolve(){
        return singleton;
    }
}
