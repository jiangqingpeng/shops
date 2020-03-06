package com.example.gateway.filter;

import com.example.gateway.redis.RedisUtil;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 自定义zuul的过滤器，继承ZuulFilter
 */
@Component
public class AccessFilter extends ZuulFilter {
    private static Logger logger = LoggerFactory.getLogger(AccessFilter.class);

    @Autowired
    private RedisUtil redisUtil;

    //过滤器的类别，这里决定过滤器在请求的哪个生命周期执行，这里定义为pre，代表请求在路由之前执行
    @Override
    public String filterType() {
        return "pre";
    }

    //过滤器的执行顺序，当请求在一个阶段中存在多个过滤器时，需要根据该方法的返回值来确定执行顺序。
    @Override
    public int filterOrder() {
        return 0;
    }

    //判断该过滤器是否需要执行，这里直接返回true，因此该过滤去对所有的请求都会生效，可以利用该方法来指定过滤器的有效范围。
    @Override
    public boolean shouldFilter() {
        return true;
    }

    /**
     * 过滤器的具体逻辑
     * TODO 登录拦截请求在这里实现，没有登录，不可以操作
     **/
    @Override
    public Object run() {
        RequestContext context = RequestContext.getCurrentContext();
        HttpServletRequest request = context.getRequest();
        HttpServletResponse response = context.getResponse();
        logger.info("send {} request to ",  request.getMethod(),
                request.getRequestURI().toString());
        Object token = request.getParameter("token");
        if( token == null ){
            logger.info(" token is empty ");
            context.setSendZuulResponse(false);
            context.setResponseStatusCode(404);
            return null;
        }
        /*Boolean resultBoolean = redisUtil.exist( String.valueOf( token ) );
        if( resultBoolean == false ){
            try {
                response.sendRedirect("login");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }*/
        logger.info("access token ok");
        return null;
    }
}
