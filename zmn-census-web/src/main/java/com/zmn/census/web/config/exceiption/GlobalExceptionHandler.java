package com.zmn.census.web.config.exceiption;

import com.zmn.census.common.core.result.CommonResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * 统一异常拦截
 */
@ControllerAdvice
@ResponseBody
public class GlobalExceptionHandler {
	private Logger logger = LoggerFactory.getLogger(this.getClass().getName());

	@ExceptionHandler(value = Exception.class)
	public CommonResult defaultErrorHandler(Exception e) {
		logger.info("================defaultErrorHandler======================{}",e);
		return CommonResult.failed(e.getMessage());
	}


	/**
	 * GET/POST请求方法错误的拦截器
	 * 因为开发时可能比较常见,而且发生在进入controller之前,上面的拦截器拦截不到这个错误
	 * 所以定义了这个拦截器
	 */
	@ExceptionHandler(HttpRequestMethodNotSupportedException.class)
	public CommonResult httpRequestMethodHandler(HttpRequestMethodNotSupportedException e) {
		logger.info("================httpRequestMethodHandler======================{}",e);
		return CommonResult.failed(e.getMessage());
	}
}
