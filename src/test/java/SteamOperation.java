import entites.User;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.PlatformTransactionManager;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SteamOperation {

    /**
     * 3种创建Stream对象
     * 1、通过集合
     * 2、通过数组
     * 3、通过Stream.of()
     * 4、创建无限流
     */
    @Test
    public void test1() {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        Stream<Integer> stream = list.stream();
        //2
        Object[] arr = new Object[]{1, 2};
        Stream<Object> stream1 = Arrays.stream(arr);
        //3
        Stream<Object> objectStream = Stream.of(1, 2, 3);
        //4
//        Stream.iterate(0, t -> t + 2).limit(10).forEach(System.out::println);    //迭代
        Stream.generate(Math::random).limit(10).forEach(System.out::println);   //生成
    }

    /**
     * 筛选与切片
     * filter(Predicate p) -- 接收Lambda，从流中排除某些元素
     * limit(n) -- 截断流，使其元素不超过给定数量
     * skip(n) -- 跳过元素，返回一个扔掉了前n个元素的流，若流中不足n个，则返回一个空流，与limit互补
     * distinct() -- 筛选，通过流所生成元素的hashCode()和equals()去除重复元素
     */
    @Test
    public void test2() {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        list.stream().filter(o -> o > 1 ).forEach(System.out::println); //filter
        System.out.println();
        list.stream().limit(3).forEach(System.out::println);    //limit
        System.out.println();
        list.stream().skip(7).forEach(System.out::println); //skip
        System.out.println();
        list.stream().distinct().forEach(System.out::println);  //distinct
    }

    /**
     * 映射
     * map(Function f) -- 接收一个函数作为参数，将元素转化成其他形式或提取信息，该函数会被应用到每个元素上，并将其映射成一个新的元素
     * flatMap(Function f) -- 接收一个函数作为参数，将流中的每个值都换成另一个流，然后把所有的流都连成一个流
     */
    @Test
    public void test3() {
        List<String> strings = Arrays.asList("aa", "bb", "cc", "dd");
        strings.stream().map(str -> str.toUpperCase()).forEach(System.out::println);
        List<User> users = new ArrayList();
        users.add(new User("张三", 14));
        users.add(new User("李四", 18));
        users.add(new User("王麻子", 11));
        //获取名字大于2的用户的姓名
        users.stream().map(User::getUserName).filter(userName -> userName.length() > 2).forEach(System.out::println);
        System.out.println();
        //练习2
        Stream<Stream<Character>> streamStream = strings.stream().map(SteamOperation::fromStringToChracter);
        streamStream.forEach(s -> {
            s.forEach(s1 -> System.out.println(s1));
        });

        Stream<Character> characterStream = strings.stream().flatMap(SteamOperation::fromStringToChracter);
        characterStream.forEach(System.out::println);

    }

    /**
     * 排序
     * sorted() -- 自然排序
     * sorted(Comparator com) -- 定制排序
     */
    @Test
    public void test4() {
        List<Integer> integers = Arrays.asList(12, 43, 123, 44, 33);
        integers.stream().sorted().forEach(System.out::println);
        List<User> users1 = new ArrayList();
        users1.add(new User("张三", 14));
        users1.add(new User("李四", 18));
        users1.add(new User("王麻子", 11));
        users1.stream().sorted((u1, u2) -> Integer.compare(u1.getAge(), u2.getAge())).forEach(System.out::println);
        System.out.println();
    }


    /**
     * 终止操作 第一部分
     * 匹配与查找
     * allMatch(Predicate p) -- 检查是否匹配所有元素。
     * anyMatch(Predicate p) -- 检查是否至少匹配一个元素。
     * noneMatch(Predicate p) -- 检查是否没有匹配的元素
     * findFirst() -- 返回第一个元素
     * findAny() -- 返回当前流中任意一个元素
     * count() -- 返回元素总个数
     * max(Comparator com) -- 返回流中最大值
     * min(Comparator com) -- 返回流中最小值
     * forEach() -- 内部遍历
     */
    @Test
    public void test5() {
        List<User> users2 = new ArrayList();
        users2.add(new User("张三", 14));
        users2.add(new User("李四", 18));
        users2.add(new User("王麻子", 11));
        boolean b = users2.stream().allMatch(u -> u.getAge() > 15);
        System.out.println(b);
        boolean b1 = users2.stream().anyMatch(u -> u.getAge() > 10);
        System.out.println(b1);
        boolean b2 = users2.stream().noneMatch(u -> u.getAge() == 0);
        System.out.println(b2);
        Optional<User> first = users2.stream().findFirst();
        System.out.println(first);
        Optional<User> any = users2.stream().findAny();
        System.out.println(any);
        long count = users2.stream().count();
        System.out.println(count);
        Optional<User> max = users2.stream().max((u1, u2) -> Integer.compare(u1.getAge(), u2.getAge()));
        System.out.println(max);
        System.out.println();
    }

    /**
     * 终止操作第二部分 归约
     * reduce(T iden,BinaryOperator b) -- T是初始值，可以将流中元素反复结合起来，得到一个值。返回T
     * reduce(BinaryOperator b ) -- 可以将流中元素反复结合起来，得到一个值，返回Optional<T>
     * 备注：map与reduce的连接通常成为map-reduce模式。
     */
    @Test
    public void test6() {
        //练习1：返回1-10的和
        List<Integer> integers1 = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        Integer reduce = integers1.stream().reduce(0, (i1, i2) -> i1 + i2);
        System.out.println(reduce);
        //练习2： 计算所有用户的年龄总和
        List<User> users3 = new ArrayList();
        users3.add(new User("张三", 14));
        users3.add(new User("李四", 18));
        users3.add(new User("王麻子", 11));
        Optional<Integer> reduce1 = users3.stream().map(u -> u.getAge()).reduce(Integer::sum);
        System.out.println(reduce1);
        System.out.println();
    }
    /**
     * 收集
     *  collect(Collect c) 将流转换为其他形式。接收一个Collector接口的实现，用于给Stream中元素做汇总的方法
     */
    @Test
    public void test7(){
        List<User> users4 = new ArrayList();
        users4.add(new User("张三",14));
        users4.add(new User("李四",18));
        users4.add(new User("王麻子",11));
        //查找年龄大于11的用户，结果返回为一个List或Set
        List<User> collect = users4.stream().filter(u -> u.getAge() > 11).collect(Collectors.toList());
        System.out.println(collect);
        Set<User> collect2 = users4.stream().filter(u -> u.getAge() > 11).collect(Collectors.toSet());
        System.out.println(collect2);
    }
    /**
     * Optional类 为了在程序中避免空指针异常而创建的
     * Optional.of(T t) -- 创建一个Optional实例，t必须非空
     * Optional.empty() -- 创建一个空的Optional
     * Optional.ofNullable(T t) -- t可以为null
     *
     * T orElse(T t) -- 如果当前的Optional内部封装的t是非空的，则返回内部的t，否则返回orElse参数中的t
     * boolean isPresent():判断是否包含对象
     * void ifPresent(Consumer<? super T> consumer):如果有值，就执行Consumer接口的实现代码，并且该值会作为参数传给他
     *
     * T get():如果包含值就返回，否则抛异常
     * T orElse(T t):如果有值就返回，否则返回参数中的值
     * T orElseGet(Supplier<? extend T>Other):如果有值则返回，否则返回由Supplier接口实现的对象
     * T orElseThrow（Supplier<? extend T> ExceptionSupplier）有值则返回，没有则返回接口实现的异常
     */
    @Test
    public void test8(){
        User u = new User("张三", 11);
        System.out.println(getNamePlus(u));
        System.out.println(getNamePlus(null));
    }


    public static Stream<Character> fromStringToChracter(String str){
        ArrayList<Character> list = new ArrayList<>();
        for(Character c : str.toCharArray()){
            list.add(c);
        }
        return list.stream();
    }
    //没有Optional类之前
    public String getName(User user){
        return user.getUserName();
    }
    //有Optional类之后
    public static String getNamePlus(User user) {
        Optional<User> user1 = Optional.ofNullable(user);
        User user2 = user1.orElse(new User("没有指定的name", 0)); //如果不存在就用自定义的
        return user2.getUserName();
    }

}
