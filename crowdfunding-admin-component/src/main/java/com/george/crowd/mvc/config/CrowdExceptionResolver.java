package com.george.crowd.mvc.config;

import com.george.crowd.constant.CrowdConstant;
import com.george.crowd.exception.AccessForbiddenException;
import com.george.crowd.exception.LoginAcctAlreadyInUseException;
import com.george.crowd.exception.LoginAcctAlreadyInUseForUpdateException;
import com.george.crowd.exception.LoginFailedException;
import com.george.crowd.util.CrowdUtil;
import com.george.crowd.util.ResultEntity;
import com.google.gson.Gson;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author georgeLin
 * @date 2021-04-14-0:06
 */
@ControllerAdvice
public class CrowdExceptionResolver {

    /**
     * 处理LoginAcctAlreadyInUseForUpdateException
     * @param exception
     * @param request
     * @param response
     * @return
     * @throws IOException
     */
    @ExceptionHandler(value = LoginAcctAlreadyInUseForUpdateException.class)
    public ModelAndView resolveLoginAcctAlreadyInUseForUpdateException(
            LoginAcctAlreadyInUseForUpdateException exception,
            HttpServletRequest request,
            HttpServletResponse response
    ) throws IOException {

        String viewName = "system-error";

        return commonResolve(viewName, exception, request, response);
    }

    /**
     * 处理LoginAcctAlreadyInUseException
     * @param exception
     * @param request
     * @param response
     * @return
     * @throws IOException
     */
    @ExceptionHandler(value = LoginAcctAlreadyInUseException.class)
    public ModelAndView resolveLoginAcctAlreadyInUseException(
            LoginAcctAlreadyInUseException exception,
            HttpServletRequest request,
            HttpServletResponse response
    ) throws IOException {

        String viewName = "admin-add";

        return commonResolve(viewName, exception, request, response);
    }

    /**
     * 处理LoginFailedException
     * @param exception
     * @param request
     * @param response
     * @return
     * @throws IOException
     */
    @ExceptionHandler(value = LoginFailedException.class)
    public ModelAndView resolveLoginFailedException(
            LoginFailedException exception,
            HttpServletRequest request,
            HttpServletResponse response
    ) throws IOException {

        String viewName = "admin-login";

        return commonResolve(viewName, exception, request, response);
    }

    /**
     * 处理AccessForbiddenException
     * @param exception
     * @param request
     * @param response
     * @return
     * @throws IOException
     */
    @ExceptionHandler(value = AccessForbiddenException.class)
    public ModelAndView resolveAccessForbiddenException(
            AccessForbiddenException exception,
            HttpServletRequest request,
            HttpServletResponse response
    ) throws IOException {

        String viewName = "admin-login";

        return commonResolve(viewName, exception, request, response);
    }

    @ExceptionHandler(value = Exception.class)
    public ModelAndView resolveException(
            Exception exception,
            HttpServletRequest request,
            HttpServletResponse response
    ) throws IOException {

        String viewName = "system-error";

        return commonResolve(viewName, exception, request, response);
    }

    /**
     * @ExceptionHandler将一个具体的异常类型和一个方法关联起来
     * @param viewName
     * @param exception
     * @param request
     * @param response
     * @return
     * @throws IOException
     */
    private ModelAndView commonResolve(

            // 异常处理完成后要去的页面
            String viewName,

            // 实际捕获到的异常类型
            Exception exception,

            // 当前请求对象
            HttpServletRequest request,

            // 当前响应对象
            HttpServletResponse response) throws IOException {

        // 1.判断当前请求类型
        boolean judgeResult = CrowdUtil.judgeRequestType(request);

        // 2.如果是Ajax请求
        if(judgeResult) {

            // 3.创建ResultEntity对象
            ResultEntity<Object> resultEntity = ResultEntity.failed(exception.getMessage());

            // 4.创建Gson对象
            Gson gson = new Gson();

            // 5.将ResultEntity对象转换为JSON字符串
            String json = gson.toJson(resultEntity);

            // 6.将JSON字符串作为响应体返回给浏览器
            response.getWriter().write(json);

            // 7.由于上面已经通过原生的response对象返回了响应，所以不提供ModelAndView对象
            return null;
        }

        // 8.如果不是Ajax请求则创建ModelAndView对象
        ModelAndView modelAndView = new ModelAndView();

        // 9.将Exception对象存入模型
        modelAndView.addObject(CrowdConstant.ATTR_NAME_EXCEPTION, exception);

        // 10.设置对应的视图名称
        modelAndView.setViewName(viewName);

        // 11.返回modelAndView对象
        return modelAndView;
    }
}
