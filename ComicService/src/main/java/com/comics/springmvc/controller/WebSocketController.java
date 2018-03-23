package com.comics.springmvc.controller;

import org.springframework.messaging.handler.annotation.MessageExceptionHandler;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.messaging.simp.annotation.SubscribeMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.comics.springmvc.caa.CalcInput;
import com.comics.springmvc.caa.Result;

@Controller
public class WebSocketController {
	//full url = wsrq/sum
	@MessageMapping("/sum" )
    @SendTo("/aaa/showResult")
	public Result addNum(CalcInput input) throws Exception {
        Thread.sleep(2000);
        Result result = new Result(input.getNum1()+"+"+input.getNum2()+"="+(input.getNum1()+input.getNum2())); 
        return result;
    }
    @RequestMapping("/start")
    public String start() {
        return "start";
    }
    
    @MessageExceptionHandler
    @SendToUser("/queue/errors")
    public String handleException(Throwable exception) {
        return exception.getMessage();
    }
    
    @SubscribeMapping("/notify")
    public String notifyA(){
    	return "Notify: Hello";
    }
}
