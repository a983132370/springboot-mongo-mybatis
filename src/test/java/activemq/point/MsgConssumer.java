/*
package activemq.point;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.junit.Test;

import javax.jms.*;

*/
/**
 * Created by zhu on 2016/12/4.
 * 消息接受者
 *//*

public class MsgConssumer {

    private static final  String USERNAME = ActiveMQConnection.DEFAULT_USER;
    private static final  String PASSWORD = ActiveMQConnection.DEFAULT_PASSWORD;
    private static final  String BROKEURL = ActiveMQConnection.DEFAULT_BROKER_URL;

    //用main没有问题   如用Test 发生接收不到或者乱码,但可用Thread.sleep 能收    TODO main 与 @Test 的区别
    public static void main(String[] args){
        ConnectionFactory factory = null;//连接工厂
        Connection conn = null;//连接
        Session session = null;//会话线程
        Destination destination = null;//目的地
        MessageConsumer consumer;//消费者
        factory = new ActiveMQConnectionFactory(MsgConssumer.USERNAME, MsgConssumer.PASSWORD, MsgConssumer.BROKEURL);
        try {
            conn = factory.createConnection();
            conn.start();//启动连接
            session = conn.createSession(Boolean.FALSE, Session.AUTO_ACKNOWLEDGE);//非事务false
            destination = session.createQueue("MyQueue");//创建消息队列
            consumer =session.createConsumer(destination);//创建消息消费者
            */
/*循环模式
            while (true){
                TextMessage message = (TextMessage)consumer.receive(1000);
                if (message!=null){
                    System.out.println("接收到消息："+message.getText());
                }else {
                    break;
                }
            }*//*

            //监听模式
            consumer.setMessageListener(new MsgListener());//注册消息监听

        } catch (JMSException e) {
            e.printStackTrace();
        }
    }



}
*/
