package com.icbc;

import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.icbc.restFull.RestResult;
import com.icbc.restFull.RestResultGenerator;
import com.icbc.restFull.ReturnCode;


@Controller
@RequestMapping("${server.error.path:${error.path:/error}}")
public class ICBCErrorController implements ErrorController {

    @Override
    public String getErrorPath() {
        return "/error";
    }

    @SuppressWarnings("rawtypes")
	@RequestMapping
    @ResponseBody
    public RestResult doHandleError() {
        return RestResultGenerator.genResult(false, ReturnCode.SYSTEM_ERROR.getMessage(), null);
    }
}