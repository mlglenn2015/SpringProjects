package com.example.project.springmvc;

import java.io.IOException;

/**
 * Example login servlet using Spring MVC.
 *
 * Created by mlglenn on 12/20/2016.
 */
@WebServlet(urlPatterns = "/login.do")
public class LoginServlet extends HttpServlet {

    private LoginService service = new LoginService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        request.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        String name = request.getParameter("name");
        String password = request.getParameter("password");

        boolean isValidUser = service.validateUser(name, password);

        if (isValidUser) {
            request.setAttribute("name", name);
            request.getRequestDispatcher("/WEB-INF/views/welcome.jsp").forward(request, response);
        } else {
            request.setAttribute("errorMessage", "Invalid Credentials!!");
            request.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(request, response);
        }
    }

}
