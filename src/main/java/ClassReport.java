

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.learneracademy.ClassSubject;
import com.learneracademy.Classes;
import com.learneracademy.HibernateUtil;
import com.learneracademy.Students;
import com.learneracademy.Subjects;
import com.learneracademy.Teachers;

/**
 * Servlet implementation class ClassReport
 */
public class ClassReport extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ClassReport() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		
		int iselected=0;
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
			            	 
						     			  out.println("<form action='ClassReport' method='post'>");
						     			  out.println("<p>Please select the report you want to generate:</p>");
						     			  out.println("<input type='radio'  name='selected' value='1'>");
						     			  out.println("<label for='Classes'>List of Classes</label><br>");
						     			  out.println("<input type='radio'  name='selected' value='2'>");
						     			  out.println("<label for='Teachers'>List of Teachers</label><br>");
						     			  out.println("<input type='radio'  name='selected' value='3'>");
						     			  out.println("<label for='Students'>List of Students</label><br>");
						     			  out.println("<input type='radio'  name='selected' value='4'>");
						     			  out.println("<label for='Subjects'>List of Subjects</label><br><br>");
						     			  out.println("<input type='submit' name='submit' value='Submit' />");
						     			  out.println("</form>");
						     			  
						     			   if(request.getParameter("submit")!=null) {
						     				   
						     				   String sselected=request.getParameter("selected");
							     			  
							     			   if(sselected !=null)
							     			   {
							     				  iselected=Integer.parseInt(sselected);  
							     			   }
							     			   else
							     			   {
							     				  out.println("<b>Select one of the radio button to generate the report you need.<b/><br>"); 
							     			   }
							     			   
							     			  switch (iselected) {
							     			  case 1:
							     				   List<Classes> list1 = opensession.createQuery("from Classes").list();
							     				   
							     				  Collections.sort(list1, new Comparator<Classes>() {
							     				 	@Override
													public int compare(Classes o1, Classes o2) {
														// TODO Auto-generated method stub
														return o1.getClassname().compareToIgnoreCase(o2.getClassname());
													}
							     				  });
								     			   
								     			    out.println("<b>List of Classes</b><br>");
								     			    
								     			   out.println("<ol>");
								     			  	
									     			for (Classes classes : list1) {
									     				out.println("<li>"+classes.getClassname()+"</li>");
									     											     			
									     			}
									     			 out.println("</ol>"); 
							     			    break;
							     			  case 2:
							     				   List<Teachers> list2 = opensession.createQuery("from Teachers").list();
							     				   
							     				       Collections.sort(list2, new Comparator<Teachers>() {
								     				 	@Override
														public int compare(Teachers o1, Teachers o2) {
															// TODO Auto-generated method stub
															return o1.getFirstname().compareToIgnoreCase(o2.getFirstname());
														}
								     				  });
									     			   
								     			   
									     			  out.println("<b>List of Teachers</b><br>");
									     			  out.println("<ol>");
										     			for (Teachers teachers : list2) {
										     				//out.print(teachers.getFirstname() +"\t"+ teachers.getLastname()  +"<br>");
										     				out.println("<li>"+teachers.getFirstname() +"\t"+ teachers.getLastname() +"</li>");							     			
										     			}
										     			out.println("</ol>");
							     			    break;
							     			  case 3:
							     				 List<Students> list3 = opensession.createQuery("from Students").list();
							     				 
							     				 Collections.sort(list3, new Comparator<Students>() {
								     				 	@Override
														public int compare(Students o1, Students o2) {
															// TODO Auto-generated method stub
															return o1.getFirstname().compareToIgnoreCase(o2.getFirstname());
														}
								     				  });
									     			   
								     			  out.println("<b>List of Students</b><br>");
								     			  out.println("<ol>");
									     			for (Students students : list3) {
									     				//out.print(students.getFirstname() +"\t"+ students.getLastname()  +"<br>");
									     				out.println("<li>"+students.getFirstname() +"\t"+ students.getLastname() +"</li>");
									     												     			
									     			}
									     			out.println("</ol>");
									     			
							     			    break;
							     			  case 4:
							     				 List<Subjects> list4 = opensession.createQuery("from Subjects").list();
							     				 
							     				 Collections.sort(list4, new Comparator<Subjects>() {
							     				 	@Override
													public int compare(Subjects o1, Subjects o2) {
														// TODO Auto-generated method stub
														return o1.getSubjectname().compareToIgnoreCase(o2.getSubjectname());
													}
							     				  });
								     			   
								     			  out.println("<b>List of Students</b><br>");
								     			  out.println("<ol>");
									     			for (Subjects subjects : list4) {
									     				//out.print(subjects.getSubjectname()+"<br>");
									     				out.println("<li>"+subjects.getSubjectname()+"</li>");
									     												     			
									     			}
									     			out.println("</ol>");
									     			
							     			    break;
							     			
							     			}
							     			  	
								            	//out.println("Successfully Added ");
						     			   };
						     			 
						     			  opensession.close();  
					     				   
			     			//out.print("Hibernate Session is closed");
			     		}
			     		
			     		
			          
			          
			      } catch (Exception ex) {
			    	  out.print(ex.getMessage());
			    	  
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
