package com.xavier.fast.controller;

import com.xavier.fast.model.base.RopRequest;
import com.xavier.fast.model.base.RopResponse;
import com.xavier.fast.utils.RopContext;
import com.xavier.fast.utils.ServiceMethodHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
* @Description:    全局路由
* @Author:         Wang
* @CreateDate:     2019/6/25 16:49
* @UpdateUser:
* @UpdateDate:     2019/6/25 16:49
* @UpdateRemark:
* @Version:        1.0
*/
@RestController
public class Router {

    private RopContext ropContext;

    private String method;

    @RequestMapping("/router/rest")
    @ResponseBody
    public Object router() {
        ServiceMethodHandler handler = ropContext.getServiceHandler();
        RopResponse<?> repResponse;
        try {
            repResponse = (RopResponse<?>)handler.getHandlerMethod().invoke(handler.getHandler(), ropContext.getRequestData());
        } catch (Exception e) {
            e.printStackTrace();
            return RopResponse.createFailedRep("-1", e.getMessage(), "1.0.0");
        }
        return repResponse;
    }

}
