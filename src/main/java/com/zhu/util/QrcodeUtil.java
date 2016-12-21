package com.zhu.util;

import com.swetake.util.Qrcode;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.stereotype.*;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Map;

/**
 * Created by zhu on 2016/11/24.
 */
@org.springframework.stereotype.Component
public class QrcodeUtil {
    //验证码登陆的用户组
    public static Map<String,Boolean> users = new HashedMap();

    /**
     * 获取二维码流
     * @param content
     * @return
     */
    public static BufferedImage getQrcode(String content){
        //设置长高 二维码默认正方形
        int width = 235,height = 235;
        //内存中创建一个缓存图片对象   TYPE_3BYTE_BGR 三原色
        BufferedImage bufferedImage = new BufferedImage(width,height,BufferedImage.TYPE_3BYTE_BGR);
        //创建画图工具  默认创建背景色黑色
        Graphics2D grap = bufferedImage.createGraphics();
        //设置背景色 白色
        grap.setBackground(Color.white);
        //清空矩形区域
        grap.clearRect(0,0,width,height);
        //设置画笔颜色 黑色
        grap.setColor(Color.black);
        //grap.fillRect(50,50,3,3);
        Qrcode q = new Qrcode();
        //二维码的类型  B  byte类型
        q.setQrcodeEncodeMode('B');
        //容错级别  M 中级
        q.setQrcodeErrorCorrect('M');
        //版本号 和内容有关  最大 40
        q.setQrcodeVersion(15);
        //设置二维码内容
        boolean[][] results = q.calQrcode(content.getBytes());
            for (int y = 0 ; y < results.length ; y++){
                for (int x = 0 ; x < results.length ; x++){
                    if (results[x][y]){
                        //开始填充  *3 偏移量
                        grap.fillRect(x*3,y*3,3,3);
                    }
                }
            }
            //图片写入磁盘
            //ImageIO.write(bufferedImage, "JPEG", new File("C:\\Users\\Administrator\\Desktop\\my.jpg"));



        return bufferedImage;
    }

}
