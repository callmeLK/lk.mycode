package kettle;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.Pipeline;

public class kettleTest {

// etl-java-redis

    private Jedis jedis=null;
    private JedisPool pool=null;
    Pipeline pipe = null;
    int cache_size=10000; // 批量提交大小
    int cur_size=0; // 当前数据缓存量

    public boolean processRow(/*StepMetaInterface smi, StepDataInterface sdi*/) {
            String redis_ip = "114.132.125.96";
//            String redis_password = getVariable("redis.password", "");

            // 连接池方式
            JedisPoolConfig config = new JedisPoolConfig();
            config.setMaxIdle(8);
            config.setMaxTotal(18);
            pool = new JedisPool(config, redis_ip, 6379, 2000, "");
            jedis = pool.getResource();
            jedis.select(1);// 切换数据库
        System.out.println(jedis.ping());
//            pipe = jedis.pipelined(); // 创建pipeline 对象


        return true;
    }
}
