package web;

import com.alibaba.fastjson.JSON;
import pojo.Brand;
import pojo.pageBean;
import service.BrandService;
import service.impl.BrandServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

@WebServlet("/brand/*")
public class BrandServlet extends BaseServlet{
    private BrandService brandService=new BrandServiceImpl();
    /**
     * 查询所有
     */
    public void selectAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Brand> brands= brandService.selectAll();
        String jsonString = JSON.toJSONString(brands);
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(jsonString);
    }
    /**
     * 增加数据
     */
    public void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1接收数据
        BufferedReader br= request.getReader();;
        String params=br.readLine();
        //2将json对象转换成java对象
        Brand brand= JSON.parseObject(params,Brand.class);
        System.out.println(brand);
        //3调用service
        int result= brandService.add(brand);
        //4响应数据
        if(result!=0){
            response.getWriter().write("success");
            System.out.println("success");
        }


    }
    /**
     * 根据ID删除数据
     */
    public void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1获取请求体
        BufferedReader br = request.getReader();
        String s = br.readLine();
        //2将json对象转为java对象
        System.out.println(s);
        Integer id = JSON.parseObject(s, Integer.class);
        //3调用service方法
        int result=brandService.deleteById(id);
        if(result!=0){
            //4响应数据
            response.getWriter().write("success");
        }

    }
    /**
     * 根据ID批量删除数据
     */
    public void deleteByIds(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1获取请求体
        BufferedReader br = request.getReader();
        String params=br.readLine();
        //2将json对象转为java对象
        int[] ids=JSON.parseObject(params,int[].class);
        //调用service方法
        int result=brandService.deleteByIds(ids);
        if(result!=0){
            //4响应数据
            response.getWriter().write("success");
        }


    }
    /**
     * 根据ID更新数据
     */
    public void updateById(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1获取请求体
        BufferedReader br = request.getReader();
        String params=br.readLine();
        //将json对象转为java对象
        Brand brand=JSON.parseObject(params,Brand.class);
        //调用service方法
        int result= brandService.updateById(brand);
        if(result!=0){
            response.getWriter().write("success");
        }
    }
    /**
     * 根据ID查找数据
     */
    public void selectById(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException{
        //1获取请求体
        BufferedReader br= request.getReader();
        String params=br.readLine();
        //将json对象转为java对象
        Integer id=JSON.parseObject(params,Integer.class);
        System.out.println("kd"+id);
        //调用service方法
        Brand brand=brandService.selectById(id);
        //将java对象转为Json对象
        String jsonString = JSON.toJSONString(brand);
        //响应数据
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(jsonString);
    }
    /**
     * 分页查询
     */
    public void selectByPage(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException{
        int currentPage= Integer.parseInt(request.getParameter("currentPage"));
        int pageSize= Integer.parseInt(request.getParameter("pageSize"));
        pageBean<Brand> pageBean= brandService.selectByPage(currentPage,pageSize);
        String jsonString = JSON.toJSONString(pageBean);
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(jsonString);

    }
    /**
     * 分页条件查询
     */
    public void selectByCondition(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException{
        int currentPage=Integer.parseInt(request.getParameter("currentPage"));
        int pageSize=Integer.parseInt(request.getParameter("pageSize"));
        BufferedReader br= request.getReader();
        String params= br.readLine();
        Brand brand=JSON.parseObject(params,Brand.class);
        pageBean<Brand> pageBean= brandService.selectByPageAndCondition(currentPage,pageSize,brand);
        String jsonString = JSON.toJSONString(pageBean);
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(jsonString);

    }
}
