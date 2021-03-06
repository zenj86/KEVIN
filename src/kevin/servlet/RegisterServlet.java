package kevin.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kevin.model.User;
import kevin.service.UserService;

/**
 * Servlet implementation class RegisterServlet
 */
@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String firstName, lastName, telephone, email, password, confirmPassword, postalNumber, street, city, district;
		
		firstName = request.getParameter("firstName");
		lastName = request.getParameter("lastName");
		email = request.getParameter("email");
		telephone = request.getParameter("telephone");
		password = request.getParameter("password");
		confirmPassword = request.getParameter("confirmPassword");
		postalNumber = request.getParameter("postalNumber");
		street = request.getParameter("street");
		city = request.getParameter("city");
		district = request.getParameter("district");
		
		User user = UserService.setUser(firstName, lastName, email, telephone, password, confirmPassword, postalNumber, street, city, district);
		String validationResult = UserService.validateUser(user);
		
		if (validationResult.equals("User Validated")) {
			
			request.setAttribute("result", UserService.saveUser(user));
			request.getRequestDispatcher("account.jsp").forward(request, response);
			
		} else {
			
			request.setAttribute("result", validationResult);
			request.getRequestDispatcher("account.jsp").forward(request, response);
			
		}
		
	}

}
