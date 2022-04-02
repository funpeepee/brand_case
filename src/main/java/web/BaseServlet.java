package web;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class BaseServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            //1获取请求路径
            String uri = req.getRequestURI();
            //2获取最后一段路径
            int index=uri.lastIndexOf("/");
            String methodName=uri.substring(index+1);
            //2利用反射执行方法
            //2.1获取Class对象
            Class<? extends BaseServlet> aClass = this.getClass();
            //2.2获取method对象
            Method method=aClass.getMethod(methodName,HttpServletRequest.class,HttpServletResponse.class);
            //3执行方法
            method.invoke(this,req,resp);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
