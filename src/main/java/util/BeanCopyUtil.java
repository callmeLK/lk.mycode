package util;

import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

/**
 * <p>
 * 类属性复制工具  支持单一对象，集合复制
 * </p>
 */
public class BeanCopyUtil extends BeanUtils {

    //单一对象复制
    public static <S, T> T beanCopy(S resource, Supplier<T> t) {
        T t1 = t.get();
        copyProperties(resource, t1);
        return t1;
    }

    //集合复制
    public static <S, T> List<T> copyList(List<S> sList, Supplier<T> t) {
        ArrayList<T> ts = new ArrayList<>();
        sList.forEach(source -> {
            T t1 = t.get();
            copyProperties(source, t1);
            ts.add(t1);
        });
        return ts;
    }
}
