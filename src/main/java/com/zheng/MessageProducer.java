package com.zheng;

import com.alibaba.rocketmq.client.producer.DefaultMQProducer;
import com.alibaba.rocketmq.client.producer.SendResult;
import com.alibaba.rocketmq.common.message.Message;

/**
 * 消息生产者
 * Created by zhenglian on 2016/12/7.
 */
public class MessageProducer {

   public static void main(String[] args) {
       DefaultMQProducer producer = new DefaultMQProducer("Producer");
       producer.setNamesrvAddr("192.168.1.200:9876");


       try {
           producer.start();

           Message msg = new Message("PushTopic", "push", "1", "hello world".getBytes("UTF-8"));
           SendResult result = producer.send(msg);
           System.out.println("id: " + result.getMsgId() +
                "result: " + result.getSendStatus());
           msg = new Message("PushTopic",
                   "push",
                   "2",
                   "Just for test.".getBytes());
           result = producer.send(msg);
           System.out.println("id:" + result.getMsgId() +
                   " result:" + result.getSendStatus());

           msg = new Message("PullTopic",
                   "pull",
                   "1",
                   "Just for test2.".getBytes());

           result = producer.send(msg);
           System.out.println("id:" + result.getMsgId() +
                   " result:" + result.getSendStatus());
            System.out.println("消息发送成功...");
       } catch (Exception e) {
           e.printStackTrace();
       } finally {
           producer.shutdown();
       }
   }


}
