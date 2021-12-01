package ch10;

import java.util.List;
import java.io.IOException;
import java.lang.reflect.Method;

import javax.servlet.RequestDispatcher;
import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.commons.beanutils.BeanUtils;

//controller
@WebServlet(urlPatterns="/news.nhn")
@MultipartConfig(maxFileSize=1024*1024*2,location="c:/Temp/img")
public class NewsController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private NewsDAO dao;
    private ServletContext ctx;
    
    //웹 리소스 기본경로 지정
    private final String START_PAGE="newList.jsp";
   
   
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		dao=new NewsDAO();
		ctx=getServletContext();
		
	}

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String action=request.getParameter("action");
		dao=new NewsDAO();
		//자바 리플랙션 api 를 사용해 자동으로 action에 저장된 이름과, 동일한 메서드를 호출하는 구조 
		//일종의 command 패턴을 적용한것으로 불필요한 if문을 없애준다. 
		Method m;
		String view=null;
		//action 피라미터 없이 접근하는 경우
		if(action==null) {
			action="listNews";
		}
		//자바 리플렉션을  이용하여 action으로 전달된 이르므이 메서드를 자동으로 호출
		try {
			//현 클래스에서 action, httpservletRequest를 피라미터로 하는 메서드를 찾음;
			m=this.getClass().getMethod(action,HttpServletRequest.class);
			//메서드 실행후에 리턴값을 받아옴
			view=(String)m.invoke(this, request);
		} catch (Exception e) {
			e.printStackTrace();
		}
		//컨트롤러는 페이지 이동시에 포워딩과 리디렉션을 모두 지원할 수 있어야한다. 
		//post 요청시에 포워딩을 수행하면, 등록이 한번더 된다 
		//post 할때에는 리디렉션을 방법으로 이동하도록 분기 
		
		if(view.startsWith("redirect:/")) {
			String rview=view.substring("redirect:/".length());
			response.sendRedirect(rview);
		}else {
			RequestDispatcher dispatcher=request.getRequestDispatcher(view);
			dispatcher.forward(request, response);
		}	
	}
	//뉴스를 등록하기 위한 메소드 
	public String addNews(HttpServletRequest request) {
		News n=new News();
		try {
			//이미지 파일을 저장
			//request로부터 part객체를 참고, getFilename()메서드를 호출하여 파일이름을 가져온 다음 서버의 디스크에 저장한다.
			Part part=request.getPart("file");
			String fileName=getFilename(part);
			if(fileName !=null && !fileName.isEmpty()) {
				part.write(fileName);
			}
			//입력값을 news 객체로 매핑
			BeanUtils.populate(n, request.getParameterMap());
			//이미지 파일 이름을 news 객체에도 저장
			n.setImg("/img/"+fileName);
			dao.addNews(n);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/news.nhn?action=listNews";
	}
	//뉴스 출력을 위한 메소드
	public String listNews(HttpServletRequest request) {
		List <News> list;
		try {
			list=dao.getAll();
			request.setAttribute("newslist", list);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "ch10/newsList.jsp";
	}

	//특정 뉴스기사를 호출하기 위한 메소드 
	public String getNews(HttpServletRequest request) {
		int aid=Integer.parseInt(request.getParameter("aid"));
		try {
			News n=dao.getNews(aid);
			request.setAttribute("news", n);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "ch10/newsView.jsp";
	}
	//뉴스를 삭제하기 위한 메소드 
	public String deleteNews(HttpServletRequest request) {
		int aid=Integer.parseInt(request.getParameter("aid"));
		try {
			dao.delNews(aid);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/news.nhn?action=listNews";
	}
	//part 객체로 전달된 이미지 파일로부터 파일이름을 추출하기 위한 메소드 
	private String getFilename(Part part) {
		String fileName=null;
		//파일이름이 들어있는 헤더 영역을 가져온다
		String header=part.getHeader("content-disposition");
		System.out.println("Header=>"+header);
		//파일 이름이 들어있는 속성부분의 시작위치를 가져와 "" 사이의 값만 가지고 온다. 
		int start= header.indexOf("filename=");
		fileName=header.substring(start+10,header.length()-1);
		ctx.log("파일명:"+fileName);
		return fileName;
	}
	
	
}
