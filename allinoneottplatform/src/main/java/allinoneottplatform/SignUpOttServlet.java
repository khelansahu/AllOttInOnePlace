package allinoneottplatform;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SignUpOttServlet extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String name = req.getParameter("nm");
		String email = req.getParameter("em");
		String pass = req.getParameter("pwd");
		int age = Integer.parseInt(req.getParameter("ag"));

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/practice?user=root&&password=root");
			PreparedStatement ps = con.prepareStatement("insert into ott values(?,?,?,?)");
			
			ps.setString(1, email);
			ps.setString(2, pass);
			ps.setString(3, name);
			ps.setInt(4, age);
			
			ps.executeUpdate();

			RequestDispatcher rd=req.getRequestDispatcher("login.html");
			rd.forward(req, resp);
			
			
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}

	}
}
