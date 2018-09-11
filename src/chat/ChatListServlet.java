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
		else if(listType.equals("ten")) response.getWriter().write(getTen());
		else
		{
			try
			{
				System.out.println("getid 실행");
				Integer.parseInt(listType);
				//System.out.println(listType);
				response.getWriter().write(getID(listType));
			}
			catch(Exception e)
			{
				response.getWriter().write("");
			}
		}
	}
	
	public String getToday() //데이터베이스에서 가져온 내용을 출력
	{
		StringBuffer result = new StringBuffer("");
		result.append("{\"result\":[");
		ChatDao chatDao = new ChatDao();
		ArrayList<Chat> chatList = chatDao.getChatList(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
		for(int i = 0 ; i < chatList.size() ; i++)
		{
			result.append("[{\"value\" : \"" + chatList.get(i).getChatName() + "\"},");
			result.append("{\"value\" : \"" + chatList.get(i).getChatContent() + "\"},");
			result.append("{\"value\" : \"" + chatList.get(i).getChatTime() + "\"},");
			result.append("{\"last\" :\"" + chatList.get(chatList.size() - 1).getChatID() +  "\"}]");
			if(i != chatList.size() - 1) result.append(",");
		}
		result.append("]}");
		return result.toString(); //{result: [value : name],[value : content], [value : time] }
	}
	
	public String getTen() //데이터베이스에서 가져온 내용을 출력
	{
		System.out.println("getTen 실행");
		StringBuffer result = new StringBuffer("");
		result.append("{\"result\":[");
		ChatDao chatDao = new ChatDao();
		ArrayList<Chat> chatList = chatDao.getChatListByRecent(10);
		for(int i = 0 ; i < chatList.size() ; i++)
		{
			//System.out.println("name : " + chatList.get(i).getChatName());
			result.append("[{\"value\" : \"" + chatList.get(i).getChatName() + "\"},");
			result.append("{\"value\" : \"" + chatList.get(i).getChatContent() + "\"},");
			result.append("{\"value\" : \"" + chatList.get(i).getChatTime() + "\"},");
			result.append("{\"last\" :\"" + chatList.get(chatList.size() - 1).getChatID() +  "\"}]");
			if(i != chatList.size() - 1) result.append(",");
		}
		result.append("]}");
		System.out.println("chat : " + result.toString());
		return result.toString(); //{result: [value : name],[value : content], [value : time] }
	}
	
	public String getID(String chatID) //데이터베이스에서 가져온 내용을 출력 , chatID 이후의 채팅내용들을 출력함.
	{
		StringBuffer result = new StringBuffer("");
		result.append("{\"result\":[");
		ChatDao chatDao = new ChatDao();
		ArrayList<Chat> chatList = chatDao.getChatListByRecent(chatID);
		for(int i = 0 ; i < chatList.size() ; i++)
		{
			System.out.println("name : " + chatList.get(i).getChatName());
			result.append("[{\"value\" : \"" + chatList.get(i).getChatName() + "\"},");
			result.append("{\"value\" : \"" + chatList.get(i).getChatContent() + "\"},");
			result.append("{\"value\" : \"" + chatList.get(i).getChatTime() + "\"},");
			result.append("{\"last\" :\"" + chatList.get(chatList.size() - 1).getChatID() +  "\"}]");
			if(i != chatList.size() - 1) result.append(",");
		}
		result.append("]}");
		//System.out.println("chat : " + result.toString());
		return result.toString(); //{result: [value : name],[value : content], [value : time] }
	}
}
