package filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;

public class AccessFilter extends ZuulFilter {
    private static Logger logger = LoggerFactory.getLogger(AccessFilter.class);

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

    //过滤器的具体逻辑
    @Override
    public Object run() {
        RequestContext context = RequestContext.getCurrentContext();
        HttpServletRequest request = context.getRequest();
        logger.info("send {} request to ",  request.getMethod(),
                request.getRequestURI().toString());
        Object accessToken = request.getParameter("accessToken");
        if( accessToken == null ){
            logger.info(" accessToken is empty ");
            context.setSendZuulResponse(false);
            context.setResponseStatusCode(404);
        }
        logger.info("access token ok");
        return null;
    }
}
