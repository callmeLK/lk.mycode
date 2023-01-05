import cn.hutool.http.HttpException;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import com.alibaba.fastjson.JSONObject;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

/**
 * 编程式事务Demo
 */
public class Transaction {
    @Autowired
    PlatformTransactionManager transactionManager;

    @Test
    public void test(){
        DefaultTransactionDefinition def = new DefaultTransactionDefinition();
//        def.setPropagationBehavior();
//        def.setIsolationLevel();
        TransactionStatus transaction = transactionManager.getTransaction(def);
        try {
            //事务操作
            //事务提交
            transactionManager.commit(transaction);
        }catch (Exception e){
            //事务回滚
            transactionManager.rollback(transaction);
        }
    }



    @Test
    public void test1(){
        HttpResponse execute = null;
        try {
            execute = HttpRequest.get("http://150.158.176.211:8080/ip/info?ip=222.179.155.130").timeout(1000).execute();
        }catch (HttpException e){
            e.printStackTrace();
            System.out.println(111);
        }
        if(execute != null){
            JSONObject ipInfo = JSONObject.parseObject(execute.body());
            System.out.println(ipInfo);
            Integer code = (Integer) ipInfo.get("code");
            System.out.println(code);
        }else{
            System.out.println(123);

        }

    }
}
