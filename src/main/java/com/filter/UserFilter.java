package com.filter;

import com.util.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;

@WebFilter(filterName = "userFilter",urlPatterns = {"/*"})
public class UserFilter implements Filter{

    @Autowired
    private JwtUtils jwtUtils;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        Map<String,String> map = new HashMap<>();
        String url =  ((HttpServletRequest)servletRequest).getRequestURI();
        System.out.println(url);
        //拦截器取到请求先进行判断，如果是OPTIONS请求，则放行
        if("OPTIONS".equals(((HttpServletRequest)servletRequest).getMethod().toUpperCase())) {
            System.out.println("Method:OPTIONS");
            //验证成功，放行
            filterChain.doFilter(servletRequest,servletResponse);
            return;
        }
        if(url != null){
            //登录请求直接放行
            if("/user/login".equals(url)||"/user/register".equals(url)||url.contains("swagger")
                    ||"/favicon.ico".equals(url)||url.contains("api-docs")
                    ||url.contains("csrf")){
                filterChain.doFilter(servletRequest,servletResponse);
                return;
            }else{
                //其他请求验证token
                String token = ((HttpServletRequest)servletRequest).getHeader("token");
                if(StringUtils.isNotBlank(token)){
                    //token验证结果
                    int verify  = jwtUtils.verify(token);
                    if(verify != 1){
                        //验证失败
                        System.out.println("验证失败");
                        if(verify == 2){
                            System.out.println("token已过期");
                            map.put("errorMsg","token已过期");
                        }else if(verify == 0){
                            System.out.println("用户信息验证失败");
                            map.put("errorMsg","用户信息验证失败");
                        }
                    }else if(verify  == 1){
                        System.out.println("验证成功");
                        //验证成功，放行
                        filterChain.doFilter(servletRequest,servletResponse);
                        return;
                    }
                }else{
                    System.out.println("未携带token");
                    map.put("errorMsg","未携带token信息");
                }
            }
            JSONObject jsonObject = new JSONObject(map);
            servletResponse.setContentType("application/json");
            //设置响应的编码
            servletResponse.setCharacterEncoding("utf-8");
            //响应
            PrintWriter pw=servletResponse.getWriter();
            pw.write(jsonObject.toString());
            pw.flush();
            pw.close();
        }
    }

}
