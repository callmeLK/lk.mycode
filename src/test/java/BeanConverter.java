import cn.hutool.core.date.LocalDateTimeUtil;
import com.baomidou.mybatisplus.core.toolkit.BeanUtils;
import entites.HomeAddress;
import entites.LK;
import entites.LKDTO;
import mapper.LKConverter;
import org.junit.Test;

import java.util.Date;

public class BeanConverter {

    @Test
    public void test(){
        LK lk = new LK();
        lk.setName("张三").setAge(28).setBirthday(new Date()).setId(1).setAddress(new HomeAddress("重庆市")).setGender("MALE").setHobby("123").setCreateTime(LocalDateTimeUtil.now());
        LKDTO lkdto = LKConverter.INSTANCE.toDto(lk);
        lkdto.setAge(33);
        System.out.println(lkdto);
        System.out.println(lk);
    }
}
