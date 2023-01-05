package com.example.demo.annotation;

import com.example.demo.entites.OpType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface OpLog {
    /**
     * 业务类型，如查询，新增，删除
     * @return
     */
    public OpType opType();

    /**
     * 业务对象名称，如订单，库存，价格
     */
    public String opItem();

    /**
     * 业务对象编号表达式，描述了如何获取订单号
     */
    public String opItemIdExpression();
}
