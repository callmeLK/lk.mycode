package com.example.demo;

import com.example.demo.entites.Student;
import com.example.demo.mapper.StudentMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.TransactionDefinition;

import javax.annotation.Resource;
import java.util.List;

@SpringBootTest
class DemoApplicationTests {

    @Autowired
    private DataSourceTransactionManager dataSourceTransactionManager;

    @Autowired
    private TransactionDefinition transactionDefinition;

    @Autowired
    private StudentMapper studentMapper;

    @Test
    void updateStudentWithTrans() {
        List<Student> allStudents = studentMapper.getAll();
        TransactionStatus transactionStatus = dataSourceTransactionManager.getTransaction(transactionDefinition);
        try {
            allStudents.forEach(s -> {
                //更新教师信息
                String teacher = s.getTeacher();
                String newTeacher = "TNO_" + new Random().nextInt(100);
                s.setTeacher(newTeacher);
                studentMapper.update(s);
            });
            dataSourceTransactionManager.commit(transactionStatus);
        } catch (Throwable e) {
            dataSourceTransactionManager.rollback(transactionStatus);
            throw e;
        }
    }
}
