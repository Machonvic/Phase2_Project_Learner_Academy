

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;


import com.learneracademy.HibernateUtil;

/**
 * Servlet implementation class Dashboard
 */
public class Dashboard extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Dashboard() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		      
		  HttpSession session=request.getSession(false);  
	      	      
	      HttpServletResponse httpResponse = (HttpServletResponse)response;

	      httpResponse.setHeader("Cache-Control","no-cache, no-store, must-revalidate"); 
	      response.addHeader("Cache-Control", "post-check=0, pre-check=0");
	      httpResponse.setHeader("Pragma","no-cache"); 
	      httpResponse.setDateHeader ("Expires", 0); 
	      if (session.getAttribute("user") == null || session.getAttribute("isLoggedIn") == null ) {                               
	                       response.sendRedirect("index.jsp");
	                       return;
	       }
	      
		if ( session.getAttribute("user") != null && (boolean)session.getAttribute("isLoggedIn")) {
			 
		 	String username =(String) session.getAttribute("username");
		 	
						 
	         PrintWriter out = response.getWriter();
	         out.println("<html><body>");
	         out.println("<a href='Logout'>Logout of session</a><br><br>");	                 
	         out.println("<b>Wecome "+username.toUpperCase()+" to the Administrator Dashboard</b><br><br><br>");
	         out.println("Use the below adminstrative tools:<br> " );
	         out.println("<a href='AddStudent'>Add Students</a><br>");
	         out.println("<a href='AddTeacher'>Add Teachers</a><br>");
	         out.println("<a href='AddClass'>Add Classes</a><br>");
	         out.println("<a href='AddSubject'>Add Subjects</a><br>");
	         out.println("<a href='AssignClass'>Assign teacher and subjects to classes</a><br>");
	         out.println("<a href='ClassReport'>Class Report</a><br>");
	         //out.println(org.hibernate.Version.getVersionString());
	         
	         out.println("</body></html>");
		}
	
       
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);

		
	}

}
