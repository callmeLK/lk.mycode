import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 枚举的常用方法。
 * 可以将它理解成一张表。
 */
@AllArgsConstructor
public enum MyEnum {
    ONE(1,"张三",18),TWO(2,"李四",19),THREE(3,"王五",20);

    @Getter
    private Integer ID;
    @Getter
    private String userName;
    @Getter
    private Integer age;

    public static MyEnum foreach_UserName(int index){
        MyEnum[] values = MyEnum.values();
        for (MyEnum element : values) {
            if(element.getID() == index){
                return element;
            }
        }
        return null;
    }


    public static void main(String[] args) {
        System.out.println(MyEnum.THREE);
        System.out.println(MyEnum.ONE.getUserName());
        MyEnum myEnum = foreach_UserName(2);
        System.out.println(myEnum.getAge());
        System.out.println(myEnum.getUserName());


    }
}
