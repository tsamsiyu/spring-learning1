package ts.edu.interceptors;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class TimeInterceptor extends HandlerInterceptorAdapter {

    private long startHandleTime;
    private long endHandleTime;
    private static final Logger logger = Logger.getLogger(TimeInterceptor.class.getName());

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        startHandleTime = System.currentTimeMillis();
        return super.preHandle(request, response, handler);
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        endHandleTime = System.currentTimeMillis();
        super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        logger.info("Request was handled in " + (endHandleTime - startHandleTime) + " [REQUEST_TIME]");
        super.afterCompletion(request, response, handler, ex);
    }

    public long getStartHandleTime() {
        return startHandleTime;
    }

    public long getEndHandleTime() {
        return endHandleTime;
    }
}
