/*
package activemq.ReleaseSubscription;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.junit.Test;

import javax.jms.*;

*/
/**
 * Created by zhu on 2016/12/4.
 * 消息发布者
 *//*

public class MsgCreator {

    private static final  String USERNAME = ActiveMQConnection.DEFAULT_USER;
    private static final  String PASSWORD = ActiveMQConnection.DEFAULT_PASSWORD;
    private static final  String BROKEURL = ActiveMQConnection.DEFAULT_BROKER_URL;
    private static final int SENDNUM = 10000;
    @Test
    public void producer(){
        ConnectionFactory factory = null;//连接工厂
        Connection conn = null;//连接
        Session session = null;//会话线程
        Destination destination = null;
        MessageProducer producer = null;

        factory = new ActiveMQConnectionFactory(MsgCreator.USERNAME, MsgCreator.PASSWORD,MsgCreator.BROKEURL);

        try {
            conn = factory.createConnection();
            conn.start();//启动连接
            session = conn.createSession(Boolean.TRUE,Session.AUTO_ACKNOWLEDGE);//使用事务
            //destination =  session.createQueue("MyQueue");//创建消息队列
            destination = session.createTopic("Topic");//创建话题
            producer =session.createProducer(destination);//创建消息生产者
            sendMsg(session,producer);//发送消息
            session.commit();//提交
        } catch (JMSException e) {
            e.printStackTrace();
        }finally {
            if (conn != null){
                try {
                    conn.close();
                } catch (JMSException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    */
/**
     * 发布消息
     * @param session
     * @param producer
     *//*

    public static void sendMsg(Session session,MessageProducer producer){
        for (int i = 0 ; i< MsgCreator.SENDNUM ; i ++){
            try {
                String m = "activemq消息"+i;
                TextMessage message = session.createTextMessage(m);//创建文本消息
                System.out.println("发布的消息："+message.getText());
                producer.send(message);//执行发送
            } catch (JMSException e) {
                e.printStackTrace();
            }
        }

    }

}
*/
