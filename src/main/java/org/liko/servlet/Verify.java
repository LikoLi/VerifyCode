package org.liko.servlet;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/verify")
public class Verify extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Verify() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/json");
		String inputCode = request.getParameter("verifyCode");
		HttpSession session = request.getSession(true);
		String verifyCode = (String) session.getAttribute("verifyCode");
		OutputStream os = response.getOutputStream();
		PrintWriter pw = new PrintWriter(os);
		if(inputCode.equalsIgnoreCase(verifyCode)) {
			pw.println("{\"result\":\"success\"}");
		} else {
			pw.println("{\"result\":\"error\"}");
		}
		pw.flush();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
