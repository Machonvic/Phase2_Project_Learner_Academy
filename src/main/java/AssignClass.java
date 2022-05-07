

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.learneracademy.ClassSubject;
import com.learneracademy.Classes;
import com.learneracademy.HibernateUtil;
import com.learneracademy.Subjects;
import com.learneracademy.Teachers;

/**
 * Servlet implementation class AssignClass
 */
public class AssignClass extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AssignClass() {
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
			 
			 		try {
			             SessionFactory factory = HibernateUtil.getSessionFactory();
			             
			             Session opensession = factory.openSession();
			             
			                         // using HQL
			             
			             if (opensession != null) {
			            	 
						     			  out.println("<form action='AssignClass' method='post'>");
						     			  out.println("Select a Class:&nbsp;");
						     			  out.println("<select name='classes' >");
						     			    List<Classes> list = opensession.createQuery("from Classes").list();
							     			 for (Classes classes : list) {
							     				//out.print("<br>"+classes.getID() +"\t"+ classes.getClassname() +"\t" +"<br>");
							     				out.println("<option value="+ classes.getID()+">"+ classes.getClassname()+"</option>");
							     			}
						     			  out.println("</select>");
						     			  out.println("<br><br>");
						     			  out.println("Select a Teacher:&nbsp;");
						     			 out.println("<select name='teachers'>");
						     			   List<Teachers> list3 = opensession.createQuery("from Teachers").list();
							     			for (Teachers teachers : list3) {
							     				//out.print("<br>"+teachers.getID() +"\t"+ teachers.getLastname() +"\t" +"<br>");
							     				out.println("<option value="+ teachers.getID()+">"+ teachers.getLastname()+"</option>");
							     			}
						     			  out.println("</select>");
						     			  out.println("<br><br>");
						     			  out.println("Select a Subject:&nbsp;");
						     			  out.println("<select name='subjects'>");
						     			 List<Subjects> list2 = opensession.createQuery("from Subjects").list();
							     			for (Subjects subjects : list2) {
							     				out.print("<br>"+subjects.getID() +"\t"+ subjects.getSubjectname() +"\t" +"<br>");
							     				out.println("<option value="+ subjects.getID()+">"+ subjects.getSubjectname() +"</option>");
							     			}
						     			  out.println("</select>");
						     			  out.println("<br><br>");						     			
						     			  out.println("<input type='submit' name='submit' value='Submit' />");
						     			  out.println("</form>");
						     			  
						     			   if(request.getParameter("submit")!=null) {
						     				   
						     				   String sclassId=request.getParameter("classes");
							     			  
							     			   String steacherId=request.getParameter("teachers");
							     			  
							     			   String ssubjectId=request.getParameter("subjects");
							     			   
							     			   int classId=Integer.parseInt(sclassId);
							     			   int teacherId=Integer.parseInt (steacherId);
							     			   int subjectId=Integer.parseInt (ssubjectId);
							     			   
							     			  //out.println("ClassId "+ classId + "teacherId" + teacherId +"subjectId " + subjectId);
							     			  //Integer.parseInt 
				     			
								     		    ClassSubject cs =new ClassSubject();
								     			cs.setCLASSID(classId);
								     			cs.setSUBJECTID(teacherId);
								     			cs.setTEACHERID(subjectId);
								     			
								     				            	
								            	opensession.beginTransaction();
								            	opensession.save(cs); //Saving the first BackupDatabase object
								            	opensession.getTransaction().commit();
								            	
								            	out.println("Successfully Added ");
						     			   };
						     			 
						     			  opensession.close();  
					     				   
			     			//out.print("Hibernate Session is closed");
			     		}
			     		
			     		
			          
			          
			      } catch (Exception ex) {
			    	  out.print(ex.getMessage());
			    	  out.print("<br>Note that you can not add the same teacher teaching same subject on the same class");
			    	         throw ex;
			              
			      }
			         
			 	out.print("</body> </html>");
					
			
				
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
