package com.jspproject.bbs.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public interface BCommand { // 2021.05.13 조혜지 - 다른 class에서 Interface 하기 위한 command 생성
	
	// 메소드 이름만 정의
	public void execute(HttpServletRequest request, HttpServletResponse response, HttpSession session); 

}
