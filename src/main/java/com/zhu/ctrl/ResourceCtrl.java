package com.zhu.ctrl;

import com.swetake.util.Qrcode;
import com.zhu.constant.AppLicationConstant;
import com.zhu.constant.EmailConstant;
import com.zhu.context.RequestContext;
import com.zhu.util.JsonUtil;
import com.zhu.util.QrcodeUtil;
import com.zhu.util.email.SimpleMailSender;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URLEncoder;
import java.util.Collection;
import java.util.UUID;

/**
 * Created by zhu on 2016/11/25.
 *
 *@MultipartConfig
 */
@Controller
public class ResourceCtrl {
    private final Logger log = LoggerFactory.getLogger(this.getClass());
    /**
     * 文件下载跳转
     */
    @RequestMapping(value = {"/toDownFilePage"})
    public void toDownFilePage(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
        if (QrcodeUtil.users.containsKey(request.getSession().getId()) && QrcodeUtil.users.get(request.getSession().getId())){

            request.getRequestDispatcher("/downFile").forward(request,response);
        }else {
            response.sendRedirect("index");
        }
    }
    /**
     * 文件下载
     */
    @RequestMapping(value = {"/downFile"})
    public void downFile(HttpServletRequest request,HttpServletResponse response) throws Exception {
        //String filename = request.getParameter("filename");

        //源文件名
        String srcFile  = "srcFile.docx";
        //获取目标文件的绝对路径   读取目标文件，通过response将目标文件写到客户端
        String fullFileName = "C:\\Users\\Administrator\\Desktop\\" + srcFile;
        log.debug("文件全路径="+fullFileName);
        //闯将文件对象
        File targetFile = new File(fullFileName);

        //客户端下载的文件名
        String outputfilename = "outputfilename.docx";
        //设置文件MIME类型
        if (request.getHeader("User-Agent").toUpperCase().indexOf("MSIE") > 0) {
            outputfilename = URLEncoder.encode(outputfilename, "UTF-8");
        } else {
            outputfilename = new String(outputfilename.getBytes("UTF-8"), "ISO8859-1");
        }
        //设置Content-Disposition
        response.setHeader("Content-Disposition", "attachment;filename="+outputfilename);
        //写明要下载的文件的大小
        response.setContentLength((int)targetFile.length());
        //设置附加文件名
        //response.setHeader("Content-Disposition","attachment;filename="+filename);
        //读取文件
        InputStream in = new FileInputStream(fullFileName);
        OutputStream out = response.getOutputStream();
        //写文件
        int b;
        while((b=in.read())!= -1)
        {
            out.write(b);
        }
        in.close();
        out.close();

        final String downIP = "下载IP "+RequestContext.getClientIp();
        log.info(downIP);
       /* new Thread(new Runnable() {
            @Override
            public void run() {
                SimpleMailSender sms = new SimpleMailSender();
                sms.sendTextMail(EmailConstant.ToMail,"简历下载",downIP);//发送文体格式
            }
        }).start();*/
    }
    /**
     * 文件接收
     */
    @ResponseBody
    @RequestMapping(value = {"/uploadFile"},method = RequestMethod.POST)
    public String uploadFile(HttpServletRequest request,HttpServletResponse response){
        //String savePath = request.getServletContext().getRealPath("/files");
        String savePath = "C:\\Users\\Administrator\\Desktop\\target\\";
        File f = new File(savePath);
        if (!f.mkdir()){
            //不存在就新建
            f.mkdir();
        }
        try {
            Collection<Part> parts = request.getParts();
            for (Part file : parts){
                log.info("保存路径="+savePath+"/"+file.getName());
                file.write(savePath+file.getName());
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ServletException e) {
            e.printStackTrace();
        }
        return "success";
    }
    /**
     * 文件上传跳转
     */
    @RequestMapping(value = {"/toUploadFilePage"})
    public String toUploadFilePage(HttpServletRequest request){
        if (QrcodeUtil.users.containsKey(request.getSession().getId()) && QrcodeUtil.users.get(request.getSession().getId())){
            return "uploadFile";
        }
        return "index";
    }
    /**
     * 二维码登陆校验
     */
    @ResponseBody
    @RequestMapping(value = {"/validQrcodeLogin"})
    public Boolean validQrcodeLogin(HttpServletRequest request){
        if (QrcodeUtil.users.containsKey(request.getSession().getId()) && QrcodeUtil.users.get(request.getSession().getId())){
            return true;
        }
        return false;
    }
    /**
     * 生成登陆二维码
     * @param request
     * @param response
     */
    @RequestMapping(value = {"/loginQrcode"})
    public void loginQrcode(HttpServletRequest request,HttpServletResponse response){

        String sessionId = request.getSession().getId();
        log.info("sessionId  ======== "+sessionId);
        //初始化未登录
        QrcodeUtil.users.put(sessionId,false);

        String url = AppLicationConstant.QRCODELOGINURL+"?sessionId="+sessionId;
        BufferedImage image = QrcodeUtil.getQrcode(url);
        response.setContentType("image/jpeg");
        try {
            OutputStream fileOut = response.getOutputStream();
            ImageIO.write(image, "JPEG", fileOut);
            fileOut.flush();
            fileOut.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }





}
