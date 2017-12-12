package com.websystique.springmvc.caa;

import org.springframework.messaging.simp.SimpMessagingTemplate;

public class MyRunnable implements Runnable {
	private SimpMessagingTemplate template;

	public SimpMessagingTemplate getTemplate() {
		return template;
	}

	public void setTemplate(SimpMessagingTemplate template) {
		this.template = template;
	}

	
	public MyRunnable(SimpMessagingTemplate template) {
		super();
		this.template = template;
	}

	@Override
	public void run() {
		int i = 0;
		while (i < 15) {
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
				System.out.println(e.getMessage());
			}
			if (template != null) {
				template.convertAndSend("/topic/greetings", i);
			} else
			{
				System.out.println("@@@@@nNULL");
			}
			i++;
		}
	}
}
