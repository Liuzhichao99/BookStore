package com.liu.controller;

import com.liu.constant.BookStoreConstant;
import com.liu.controller.base.ModelBaseServlet;
import com.liu.pojo.User;
import com.liu.service.impl.UserServiceImpl;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

@WebServlet("/user")
public class UserController extends ModelBaseServlet {
    /**
     * 转发到login.html
     *
     * @param request
     * @param response
     */
    public void toLoginPage(HttpServletRequest request, HttpServletResponse response) throws IOException {
        processTemplate("user/login", request, response);
    }

    /**
     * 转发到login_success.html
     *
     * @param request
     * @param response
     */
    public void toLoginSuccessPage(HttpServletRequest request, HttpServletResponse response) throws IOException {
        processTemplate("user/login_success", request, response);
    }

    /**
     * 登录功能
     *
     * @param request
     * @param response
     */
    public void login(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // 1.controller——处理请求
        // 获取页面输入参数
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        // 2.service——业务处理  ——》3.dao——操作数据库
        // 通过方法传参将username,password传递到业务层
        UserServiceImpl userService = new UserServiceImpl();
        User dbUser = userService.login(username, password);

        // 4.controller——页面跳转
        // 5.controller——视图渲染
        if (null != dbUser) {
            // 登录成功 ，session存储登录的用户信息, 重定向到toLoginSuccessPage方法
            request.getSession().setAttribute(BookStoreConstant.SESSION_KEY_USER,dbUser);
            response.sendRedirect(request.getContextPath() + "/user?method=toLoginSuccessPage");
        } else {
            //登录失败 , 转发到login.html , 展示错误提示信息
            String errorMsg = "fuck!你错啦！";
            request.setAttribute("errorMsg", errorMsg);
            toLoginPage(request, response);
        }
    }

    /**
     * 转发到regist.html
     *
     * @param request
     * @param response
     * @throws IOException
     */
    public void toRegistPage(HttpServletRequest request, HttpServletResponse response) throws IOException {
        processTemplate("user/regist", request, response);
    }

    public void toRegistSuccessPage(HttpServletRequest request, HttpServletResponse response) throws IOException {
        processTemplate("user/regist_success", request, response);
    }

    /**
     * 注册功能
     *
     * @param request
     * @param response
     */
    public void regist(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            // 获取输入的验证码
            String inputCode = request.getParameter("code");
            // 获取产生的验证码
            String existCode = (String) request.getSession().getAttribute(BookStoreConstant.SESSION_KEY_CODE);
            if (null == existCode) {
                // 验证码失效
                String codeErrorMsg = "验证码失效";
                request.setAttribute("codeErrorMsg", codeErrorMsg);
                // 转发regist.html
                toRegistPage(request, response);

                return;
            }
            // 校验验证码
            if (!inputCode.equals(existCode)) {
                //验证码错误 ,设置错误提示信息, 转发regist.html, 后续代码不执行

                //手动将之前的验证码移除
                request.getSession().removeAttribute(BookStoreConstant.SESSION_KEY_CODE);

                String codeErrorMsg = "验证码错误!";
                request.setAttribute("codeErrorMsg",codeErrorMsg);
                // 转发regist.html
                toRegistPage(request, response);

                return;
            }

            // 手动将之前的验证码移除
            request.getSession().removeAttribute(BookStoreConstant.SESSION_KEY_CODE);

            // 获取页面输入参数并使用BeanUtils封装到User对象
            User user = new User();
            BeanUtils.populate(user, request.getParameterMap());
            UserServiceImpl userService = new UserServiceImpl();

            // 判断用户是否存在
            User dbUser = userService.getByUsername(user.getUserName());
            if (null != dbUser) {
                // 用户名已经存在 ， 在regist.html页面给出错误提示 "用户名已经存在！"
                String errorMsg = "用户名已经存在!";
                request.setAttribute("usernameErrorMsg", errorMsg);
                toRegistPage(request, response);
                return;
            }
            // 执行注册
            userService.regist(user);
            // 注册成功 ， 重定向到regist_success.html
            Cookie registName = new Cookie("registName", user.getUserName());
            registName.setMaxAge(60);

            response.addCookie(registName);

            response.sendRedirect(request.getContextPath() + "/user?method=toRegistSuccessPage");
        } catch (Exception e) {
            e.printStackTrace();
            // 注册失败，转发到regist.html
            toRegistPage(request, response);
        }
    }

    public void logout(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.getSession().invalidate(); // 将session中所有数据(用户信息, 购物车信息 ...)都销毁
        //req.getSession().removeAttribute("existUser"); // 将session中的用户信息移除
        //重定向到首页
        response.sendRedirect(request.getContextPath() + "/index.html");
    }

}