import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.junit.Test;
import org.springframework.util.StringUtils;
import redis.clients.jedis.*;

import java.sql.Date;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

public class Redis {


    @Test
    public void test2(){
        Jedis jedis=null;
        JedisPool pool=null;
        // 连接池方式
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxIdle(8);
        config.setMaxTotal(18);
        pool = new JedisPool(config, "114.132.125.96", 6379, 2000, null);
        jedis = pool.getResource();
        jedis.select(0);// 切换数据库
        jedis.set("aaa", JSON.toJSONString("bbb"));
        jedis.close();
        pool.close();



    }

    @Test
    public void tesr3(){
        DateUtil.parse("");
    }
}
