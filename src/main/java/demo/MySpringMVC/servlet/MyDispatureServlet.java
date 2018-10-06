package demo.MySpringMVC.servlet;

import demo.MySpringMVC.annotation.*;
import demo.MySpringMVC.excption.MyException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.net.URL;
import java.util.*;

/**
 * @author: Liu Mingyao
 * @create: 2018-08-19 16:24
 **/
public class MyDispatureServlet extends HttpServlet {
    List classNames = new ArrayList();
    Map beans = new HashMap();
    Map<String, Method> urlMapping = new HashMap();


    @Override
    public void init() throws ServletException {
        System.out.println("*************初始化servlet容器****************");
        //扫描所有包下的class文件==》
        doScanPackae("demo.MySpringMVC");
        //注册所有的bean,并存起来
        getBeans();
        //注入依赖bean
        diBeans();
        //添加映射关系
        addMapping();
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String requestURI = req.getRequestURI();
        String contextPath = req.getContextPath();
        requestURI.replace(contextPath, "");
        Method method = doMapping(requestURI);
        //根据url调用方法
        Object[] args = doParam(req, resp, method);
        char[] chars = method.getClass().getName().toCharArray();
        chars[0] += 32;
        try {
            method.invoke(beans.get(String.valueOf(chars)), args);
        } catch (Exception e) {
            throw new MyException("*********调用controller方法异常*********");
        }
    }

    private Object[] doParam(HttpServletRequest req, HttpServletResponse resp, Method method) {
        Parameter[] parameters = method.getParameters();

        Class<?>[] parameterTypes = method.getParameterTypes();
        Object[] args = new Object[parameterTypes.length];

        return parameters;
    }

    private Method doMapping(String path) {
        return urlMapping.get(path);
    }

    @Override
    public void destroy() {
        System.out.println("*************关闭servlet容器,销毁资源****************");
        super.destroy();
    }


    /**
     * @Description: 扫描包下所有的class文件
     * @Param:
     * @return:
     * @Author: Liu Ming
     */
    private void doScanPackae(String basePackage) {
        URL url = this.getClass().getClassLoader().getResource("");
        File file = new File(url.getFile() + basePackage.replaceAll("\\.", "/"));
        for (String string : file.list()) {
            File filePath = new File(string);
            if (filePath.isDirectory()) doScanPackae(basePackage + "." + string);
            else classNames.add(basePackage + "." + string);
        }
    }

    /**
     * @Description: 加载注册所有的bean
     * @Param:
     * @return:
     * @Author: Liu Ming
     */
    public void getBeans() {
        classNames.forEach(className -> {
            Class<?> clz = className.getClass();
            Object obj;
            try {
                obj = clz.newInstance();
            } catch (Exception e) {
                throw new MyException("*********实例化bean异常*********");
            }
            if (clz.isAnnotationPresent(MyDao.class)) {
                //   char[] chars = clz.getName().toCharArray();
                //   chars[0] += 32;
                String value = clz.getAnnotation(MyDao.class).value();
                beans.put(value, obj);
            } else if (clz.isAnnotationPresent(MyService.class)) {
                String value = clz.getAnnotation(MyService.class).value();
                beans.put(value, obj);
            } else if (clz.isAnnotationPresent(MyController.class)) {
                String value = clz.getAnnotation(MyController.class).value();
                beans.put(value, obj);
            }
        });
    }

    /**
     * @Description: 注入字段所需要的bean
     * @Param:
     * @return:
     * @Author: Liu Ming
     */
    private void diBeans() {
        beans.entrySet().forEach(instance -> {
            Field[] fields = instance.getClass().getDeclaredFields();
            Arrays.asList(fields).forEach(field -> {
                if (field.isAnnotationPresent(MyAutowired.class)) {
                    field.setAccessible(true);
                    try {
                        field.set(instance, beans.get(field.getAnnotation(MyAutowired.class).value()));
                    } catch (IllegalAccessException e) {
                        throw new MyException("*********依赖注入bean设值异常*********");
                    }
                }
            });
        });
    }

    /**
     * @Description: 添加mapping映射关系
     * @Param:
     * @return:
     * @Author: Liu Ming
     */
    private void addMapping() {
        for (Object bean : beans.entrySet()) {
            String url = "";
            Class<?> clz = bean.getClass();
            if (clz.isAnnotationPresent(MyController.class)) {
                if (clz.isAnnotationPresent(MyRequestMapping.class)) {
                    String clzMapping = clz.getAnnotation(MyRequestMapping.class).value();
                    if (String.valueOf(clzMapping.toCharArray()[0]).equals("/")) url += clzMapping;
                    else url += "/" + clzMapping;
                }
                for (Method method : Arrays.asList(clz.getMethods())) {
                    if (method.isAnnotationPresent(MyRequestMapping.class)) {
                        String clzMapping = clz.getAnnotation(MyRequestMapping.class).value();
                        if (String.valueOf(clzMapping.toCharArray()[0]).equals("/")) url += clzMapping;
                        else url += "/" + clzMapping;
                    }
                    urlMapping.put(url, method);
                }
            }
        }
    }
}
