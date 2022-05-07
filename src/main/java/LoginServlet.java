

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import com.learneracademy.User;
import com.learneracademy.*;

import java.io.IOException;
import java.sql.SQLException;

/**
 * Servlet implementation class LoginServlet
 */
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		 
		 	String username = request.getParameter("username");
			String password = request.getParameter("password");
			
			UserDAO userDao = new UserDAO();
			
			try {
				User user = userDao.checkLogin(username, password);
				String destPage = "index.jsp";
				
				if (user != null) {
					HttpSession session = request.getSession();
					session.setAttribute("user", user);
					session.setAttribute("isLoggedIn", true);
					
					destPage = "dashboard";
					session.setAttribute("username", username);
					
				} else {
					String message = "Invalid username/password";
					request.setAttribute("message", message);
					
				}
				
								
				RequestDispatcher dispatcher = request.getRequestDispatcher(destPage);
				dispatcher.forward(request, response);
				
				
			} catch (SQLException | ClassNotFoundException ex) {
				throw new ServletException(ex);
			}
			
			
	}

}
