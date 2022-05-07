

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

/**
 * Servlet implementation class AddClass
 */
public class AddClass extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddClass() {
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
			 
		 		 
			    out.println(" <form action='AddClass' method='post'>");
			    out.println("<label for='classname'>Name of the Class:</label>");
				out.println("<input name='classname' size='30' />");
				out.println("<br><br>");
				out.println("<button type='submit'>Add Class</button>");
				out.println("</form>");			 
	        
				String classname = request.getParameter("classname"); 
				//out.print("Class Name "+ classname);
				
				if(classname == null || classname.equals("") || classname.trim().equals("")) {
					
					out.print("The Field is Blank. Enter the Class Name");
				}else {
					
			 		try {
			             SessionFactory factory = HibernateUtil.getSessionFactory();
			             
			             Session opensession = factory.openSession();
			             
			                         // using HQL
			             
			             if (opensession != null) {
			     			
			     			Classes cls =new Classes();
			     					     			
			            	cls.setClassname(classname.toUpperCase());
			            	
			            	opensession.beginTransaction();
			            	opensession.save(cls); //Saving the first BackupDatabase object
			            	opensession.getTransaction().commit();
			            	
			            	out.println("<b>"+classname.toUpperCase()+"<b>"+"<b> has been successful Added<b>");
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
