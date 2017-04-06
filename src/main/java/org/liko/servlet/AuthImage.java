package org.liko.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.liko.util.verifycode.VerifyCodeUtil;

@WebServlet("/authImage")
public class AuthImage extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public AuthImage() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
		response.setContentType("image/jpeg");
		
		String verifyCode = VerifyCodeUtil.generateVerifyCode(4);
		HttpSession session = request.getSession(true);
		session.removeAttribute("verifyCode");
		session.setAttribute("verifyCode", verifyCode.toLowerCase());
		
		int w = 100, h = 30;
		VerifyCodeUtil.outputImage(w, h, response.getOutputStream(), verifyCode);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
