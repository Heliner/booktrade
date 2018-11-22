package com.cqut.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.cqut.WEntity.UserRegiser;
import com.cqut.WEntity.UserVali;
import com.cqut.entity.User;
import com.cqut.exception.CodeMsg;
import com.cqut.exception.GlobalException;
import com.cqut.exception.IllegalOperationException;
import com.cqut.result.AjaxResult;
import com.cqut.service.BookService;
import com.cqut.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.imageio.ImageIO;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Properties;
import java.util.Random;

import static com.cqut.util.ModelAndViewGenerator.GENERATE_FAIL_VIEW;
import static com.cqut.util.ResultGenerator.GENERATE_DEFAULT_FAILED_MESSAGE;
import static com.cqut.util.ResultGenerator.GENERATE_FAILED_MESSAGE;
import static com.cqut.util.ResultGenerator.GENERATE_SUCCESS_RESULT;
import static com.cqut.util.SessionUtil.HttpUtil.getCurUser;
import static com.cqut.util.SessionUtil.HttpUtil.getSession;

@Controller
@RequestMapping(value = "/user")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private BookService bookServiceImpl;
    //TODO 确认用JQuery.form 提交的方式与通过普通表单提交是否有不同，主要是确定能否用自动注入的方式
    /*直接使用Result进行包装，需要指定返回的路径*/
    /*使用ModelAndView，可以指定路径（指定路径后，并不支持异步返回),，可以直接返回视图，
     * 如果需要添加返回的地址的话，就不支持Ajax，  指定返回路径：1.不支持异步方式
     * 2.支持异步方式：需要对返回的对象进行包装，然后转化为Json对象。
     * 对网页部分使用异步方式（），部分不使用异步方式（可以使用Jsp进行指定内容）,是页面混杂，
     * 全部使用Jsp赋值的方式会增加Tomcat负载
     * 全异步方式，会增加JavaScript代码，对于取代Jsp内容比较困难。
     *  :使用jsp和javascrip结合的方式
     */


    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public AjaxResult login(HttpServletRequest request, @RequestBody UserVali userVali) throws Exception {
        User user = userVali.getUser();
        String valCode = userVali.getValCode();
        System.out.println((String) getSession(request).getAttribute("imgVal"));
        System.out.println("valCode :" + valCode);
        userService.checkPassword(request, valCode, user);
        user = userService.selectByUsername(user.getUsername());
        getSession(request).setAttribute("user", user);
        return GENERATE_SUCCESS_RESULT("登录成功", "/home.do");
    }

    /*登录转跳 无入参*/
    @RequestMapping("/login.do")
    public String loginDo() {
        return "/Login";
    }

    /*注册转跳 无入参*/
    @RequestMapping(value = "/register.do")
    public String registerDo() {
        return "/Register";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @ResponseBody
    public AjaxResult register(HttpServletRequest request, @RequestBody(required = true) UserRegiser userRegiser /*@RequestParam("valCode") String valCode, @RequestParam("cPasswrod") String cPasswrod, @RequestParam(required = true) User user*/) {
        String cPasswrod = userRegiser.getcPassword();
        String valCode = userRegiser.getValCode();
        User user = userRegiser.getUser();
        AjaxResult ajaxResult = null;
        HttpSession session = getSession(request);
        String rValCode = (String) session.getAttribute("_mailValidateCode");
        if (rValCode == null)
            return GENERATE_DEFAULT_FAILED_MESSAGE();
        /*验证验证码和邮箱*/
        if (!rValCode.equals(valCode)) {
            return GENERATE_FAILED_MESSAGE("邮箱验证码有误");
        } else if (user.getPassword() == null || !user.getPassword().equals(cPasswrod)) {
            ajaxResult = GENERATE_FAILED_MESSAGE("两次密码不相等");
        } else {
            try {
                if (user == null || user.getUsername() == null || userService.selectByUsername(user.getUsername()) != null)
                    return GENERATE_FAILED_MESSAGE("用户名存在");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return ajaxResult;
    }

    @RequestMapping(value = "/getValidateCode")
    @ResponseBody
    public AjaxResult getValidateCode(HttpServletRequest request) {
        HttpSession session = getSession(request);
        String code = generateValidateCode();
        session.setAttribute("valCode", code);
        return GENERATE_SUCCESS_RESULT((Object) code);
    }

    // TODO
    private String generateValidateCode() {
        return "code";
    }

    @RequestMapping(value = "/logout")
    public ModelAndView logout(HttpServletRequest request, ModelAndView modelAndView) {
        HttpSession session = getSession(request);
        session.invalidate();
        modelAndView = new ModelAndView("redirect:/home.do");
        return modelAndView;
    }


    /*更新用户信息*/
    @RequestMapping(value = "/", method = RequestMethod.PUT)
    public ModelAndView update(HttpServletRequest request, User user) {
        ModelAndView modelAndView = new ModelAndView();
        User curUser = getCurUser(request);
        try {
            userService.update(user, curUser);
        } catch (IllegalOperationException ille) {
            ille.printStackTrace();
            return GENERATE_FAIL_VIEW(ille.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            return GENERATE_FAIL_VIEW(e.getMessage());
        }
        /*该视图直接使用session中的内容*/
        modelAndView.setViewName("forward:/user/MyInfoView.do");
        return modelAndView;
        /*正常，返回*/
        /*错误，返回异常值*/
    }

    /**
     * 转向MyInfoView.jsp
     *
     * @param request
     * @param modelAndView
     * @return
     */
    /*直接从session中获取有关用户的信息*/
    @RequestMapping(value = "/MyInfoView")
    public String myInfo(HttpServletRequest request, ModelAndView modelAndView) {
        return "/MyInfoView";
    }

    /*查询我的书摊 无入参*/
    @RequestMapping(value = "/mystall.do")
    public ModelAndView mystall(HttpServletRequest request) {
        User user = getCurUser(request);
        ModelAndView modelAndView = new ModelAndView();
        try {
            modelAndView.addObject("bookList", bookServiceImpl.selectBooksByUser(user));
        } catch (Exception e) {
            e.printStackTrace();
        }
        /*返回到jsp*/
        modelAndView.setViewName("/MyBookStall");
        return modelAndView;
    }


    @RequestMapping("/ImgVal")
    public void imgValidate(HttpServletResponse response, HttpServletRequest request) throws IOException {
        int width = 80;
        int height = 40;
        Random random = new Random();
        //设置response头信息
        //禁止缓存
        response.setHeader("Pragma", "No-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);

        //生成缓冲区image类
        BufferedImage image = new BufferedImage(width, height, 1);
        //产生image类的Graphics用于绘制操作
        Graphics g = image.getGraphics();
        //Graphics类的样式
        g.setColor(this.getRandColor(200, 250));
        g.setFont(new Font("Times New Roman", 0, 28));
        g.fillRect(0, 0, width, height);
        //绘制干扰线
        for (int i = 0; i < 40; i++) {
            g.setColor(this.getRandColor(130, 200));
            int x = random.nextInt(width);
            int y = random.nextInt(height);
            int x1 = random.nextInt(12);
            int y1 = random.nextInt(12);
            g.drawLine(x, y, x + x1, y + y1);
        }

        //绘制字符
        StringBuilder strCode = new StringBuilder();
        for (int i = 0; i < 4; i++) {
            String rand = String.valueOf(random.nextInt(10));
            strCode.append(rand);
            g.setColor(new Color(20 + random.nextInt(110), 20 + random.nextInt(110), 20 + random.nextInt(110)));
            g.drawString(rand, 13 * i + 6, 28);
        }
        //将字符保存到session中用于前端的验证
        request.getSession().setAttribute("imgVal", strCode.toString());
        g.dispose();
        ImageIO.write(image, "JPEG", response.getOutputStream());
        response.getOutputStream().flush();
    }
    //创建颜色

    private Color getRandColor(int fc, int bc) {
        Random random = new Random();
        if (fc > 255)
            fc = 255;
        if (bc > 255)
            bc = 255;
        int r = fc + random.nextInt(bc - fc);
        int g = fc + random.nextInt(bc - fc);
        int b = fc + random.nextInt(bc - fc);
        return new Color(r, g, b);
    }

    @RequestMapping("/mailValidate")
    @ResponseBody
    public AjaxResult sendMailToValidate(HttpServletRequest request) {
        Properties properties = new Properties();
        properties.put("mail.transport.protocol", "smtp");// 连接协议
        properties.put("mail.smtp.host", "smtp.qq.com");// 主机名
        properties.put("mail.smtp.port", 465);// 端口号
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.ssl.enable", "true");// 设置是否使用ssl安全连接 ---一般都使用
        properties.put("mail.debug", "true");// 设置是否显示debug信息 true 会在控制台显示相关信息
        // 得到回话对象
        Session session = Session.getInstance(properties);
        HttpSession httpSession = getSession(request);

        // 获取邮件对象
        Message message = new MimeMessage(session);
        /*生成六位验证码*/
        String _mailValidateCode = "";
        Random random = new Random();
        for (int i = 0; i < 6; i++) {
            _mailValidateCode += random.nextInt(10);
        }

        httpSession.setAttribute("mailVal", _mailValidateCode);
        // 设置发件人邮箱地址
        try {
            message.setFrom(new InternetAddress("303567469@qq.com"));
            // 设置收件人邮箱地址
//            message.setRecipients(Message.RecipientType.TO, new InternetAddress[]{new InternetAddress("303567469@qq.com")});
            message.setRecipient(Message.RecipientType.TO, new InternetAddress("h303567649@gmail.com"));//一个收件人
            // 设置邮件标题
            message.setSubject("邮箱验证");
            // 设置邮件内容
            message.setText("你的验证码");
            // 得到邮差对象
            Transport transport = session.getTransport();
            // 连接自己的邮箱账户
            transport.connect("303567469@qq.com", "oqhfarpooqfobjfc");// 密码为QQ邮箱开通的stmp服务后得到的客户端授权码
            // 发送邮件
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        return GENERATE_SUCCESS_RESULT("发送成功");
    }

}