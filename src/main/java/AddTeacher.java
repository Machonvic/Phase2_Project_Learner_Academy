

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.learneracademy.Classes;
import com.learneracademy.HibernateUtil;
import com.learneracademy.Teachers;

/**
 * Servlet implementation class AddTeacher
 */
public class AddTeacher extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddTeacher() {
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
	      
	      PrintWriter out = response.getWriter();
	      out.println("<html><body>");
	      out.println("<a href='Logout'>Logout of session</a><br><br>");
	      out.println("<a href='dashboard'>Back to Dashboard</a><br><br>");
	      
	      if (session.getAttribute("user") == null || session.getAttribute("isLoggedIn") == null ) {                               
	                       response.sendRedirect("index.jsp");
	                       return;
	       }
	      
		  if ( session.getAttribute("user") != null && (boolean)session.getAttribute("isLoggedIn")) {
			 
		 		 
			    out.println(" <form action='AddTeacher' method='post'>");
			    out.println("<label for='firstname'>First Name:</label>");
				out.println("<input name='firstname' size='30' />");
				out.println("<label for='lastname'>Last Name:</label>");
				out.println("<input name='lastname' size='30' />");
				out.println("<br><br>");
				out.println("<button type='submit'>Add Teacher</button>");
				out.println("</form>");			 
	        
				String firstname = request.getParameter("firstname");
				String lastname = request.getParameter("lastname"); 
				//out.print("Class Name "+ classname);
				
				if(firstname == null || firstname.equals("") || firstname.trim().equals("")
						|| lastname == null || lastname.equals("") || lastname.trim().equals("")) {
					
					out.print("The Field is Blank. Enter the Class Name");
				}else {
					
			 		try {
			             SessionFactory factory = HibernateUtil.getSessionFactory();
			             
			             Session opensession = factory.openSession();
			             
			                         // using HQL
			             
			             if (opensession != null) {
			     			
			     			Teachers tch =new Teachers();
			     					     			
			     			tch.setFirstname(firstname.toUpperCase());
			     			tch.setLastname(lastname.toUpperCase());
			            	
			            	opensession.beginTransaction();
			            	opensession.save(tch); //Saving the first BackupDatabase object
			            	opensession.getTransaction().commit();
			            	
			            	out.println("<b>"+firstname.toUpperCase()+" "+lastname.toUpperCase()+"<b> "+"<b>has been successful Added<b>");
			     			opensession.close();
			     			//out.print("Hibernate Session is closed");
			     		}
			     		
			     		
			          
			          
			      } catch (Exception ex) {
			    	  out.print(ex.getMessage());
			    	  out.print("<br>Note: If it a ConstraintViolationException, make sure the name you are adding does not exist.");
			              throw ex;
			      }
			         
			 		out.print("</body> </html>");
					
				}
				
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
