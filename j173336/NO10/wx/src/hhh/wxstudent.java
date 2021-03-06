package hhh;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class wxstudent
 */
@WebServlet("/wxstudent")
public class wxstudent extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public wxstudent() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out=response.getWriter();
		Connection conn=null;
		Statement st=null;
		ResultSet rs=null;
		
		String url="jdbc:mysql://127.0.0.1:3306/test";
		String driver="com.mysql.jdbc.Driver";
		String user="root";
		String pass="root";
		
		try {
			Class.forName(driver);
			conn=DriverManager.getConnection(url,user,pass);
			st=conn.createStatement();
			
			String sql="select * from student order by homework desc";
			rs=st.executeQuery(sql);
			
			out.println("[");
			while (rs.next()) {
				out.println("{");
				out.println("\"homework\":\""+rs.getString("homework")+"\",");
				out.println("\"homework name\":\""+rs.getString("homework name")+"\",");
				out.println("\"homework zf\":\""+rs.getInt("homework zf")+"\",");
				out.println("\"homework zy1\":\""+rs.getInt("homework zy1")+"\",");
				out.println("\"homework zy2\":\""+rs.getInt("homework zy2")+"\",");
				out.println("\"homework zy3\":\""+rs.getInt("homework zy3")+"\"");
				out.println("},");
			}
			out.println("]");
			rs.close();
			st.close();
			conn.close();
			
		} catch (Exception ex) {
			ex.printStackTrace();
		}finally {
			out.close();
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
