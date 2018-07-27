package chat;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ChatSubmitServlet
 */
@WebServlet("/ChatListServlet")
public class ChatListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset = UTF-8");
		String chatName = request.getParameter("chatName");
		String listType = request.getParameter("listType");
		if(listType == null || listType.equals("")) response.getWriter().write("");
		else if(listType.equals("today")) response.getWriter().write(getToday());			
	}
	
	public String getToday() //데이터베이스에서 가져온 내용을 출력
	{
		StringBuffer result = new StringBuffer("");
		result.append("{\"result\":[");
		ChatDao chatDao = new ChatDao();
		ArrayList<Chat> chatList = chatDao.getChatList(new SimpleDateFormat("yyyy-MM-DD").format(new Date()));
		for(int i = 0 ; i < chatList.size() ; i++)
		{
			result.append("[{\"value\" : \"" + chatList.get(i).getChatName() + "\"},");
			result.append("{\"value\" : \"" + chatList.get(i).getChatContent() + "\"},");
			result.append("{\"value\" : \"" + chatList.get(i).getChatTime() + "\"}]");
			if(i != chatList.size() - 1) result.append(",");
		}
		result.append("]}");
		System.out.println(result.toString());
		return result.toString(); //{result: [value : name],[value : content], [value : time] }
	}

}
