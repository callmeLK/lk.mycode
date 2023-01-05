package mapper;

import cn.hutool.core.date.LocalDateTimeUtil;
import entites.HomeAddress;
import entites.LK;
import entites.LKDTO;
import org.apache.poi.ss.formula.functions.T;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.lang.reflect.Field;
import java.time.LocalDateTime;

@Mapper
public interface BeanConverter {
    BeanConverter INSTANCE = Mappers.getMapper(BeanConverter.class);


    @Mapping(source = "name",target = "userName") //不同属性名的转换
    @Mapping(target = "address",expression = "java(homeAddressToString(lk.getAddress()))") //不同属性类型的转换
//    @Mapping(target = "hobby",constant = "玩游戏") // 不管有没有值，都赋constant的值
    @Mapping(target = "hobby",defaultValue = "玩游戏") //默认值,如果属性为null，则赋defaultValue的值
    @Mapping(target = "createTime",expression = "java(createTimeConverter(lk.getCreateTime()))")
    LKDTO toDto(LK lk);

    default String homeAddressToString(HomeAddress address){
        return address.getAddress();
    }
    default String createTimeConverter(LocalDateTime createTime){
        return LocalDateTimeUtil.format(createTime,"yyyy-MM-dd");
    }
}
