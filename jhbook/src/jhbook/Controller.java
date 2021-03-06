package jhbook;

import java.io.IOException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

/**
 * Servlet implementation class Controller
 */
@WebServlet(urlPatterns="/news.nhn")
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
	StudentDAO dao;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Controller() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		dao=new StudentDAO();
	}

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action=request.getParameter("action");
		String view="";
		switch(action) {
		case "insert": view=insert(request,response); break;
		}

	}

	private String insert(HttpServletRequest request, HttpServletResponse response) {
		Student s=new Student();
		try {
			BeanUtils.populate(s, request.getParameterMap());
		} catch (Exception e) {
			// TODO: handle exception
		}
		dao.insert(s);
		return "";
	}

}

