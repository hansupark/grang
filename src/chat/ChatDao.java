package chat;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class ChatDao {

	private Connection conn;
	public ChatDao()
	{
		try 
		{
			String dbURL = "jdbc:mysql://localhost:3306/annoymous";
			String dbID = "root";
			String dbPassword = "cs1234";
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(dbURL,dbID,dbPassword);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public ArrayList<Chat> getChatList(String nowTime)
	{
		ArrayList<Chat> chatList = new ArrayList<Chat>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String SQL = "SELECT * FROM CHAT WHERE chatTime > ? ORDER BY chatTime";
		System.out.println("now time : " + nowTime);
		try
		{
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1,nowTime);
			rs=pstmt.executeQuery();
			while(rs.next())
			{
				Chat chat = new Chat();
				chat.setChatName(rs.getString("chatName"));
				chat.setChatID(rs.getInt("chatID"));
				chat.setChatContent((rs.getString("chatContent")).trim());
				/*int chatTime = Integer.parseInt(rs.getString("chatTime").substring(11,13));
				String timeType = "오전";
				if(Integer.parseInt(rs.getString("chatTime").substring(11,13))>=12)
				{
					timeType = "오후";
					chatTime -= 12;
				}*/
				chat.setChatTime(rs.getString("chatTime"));
				chatList.add(chat);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			try
			{
				if(rs!=null) rs.close();
				if(pstmt !=null) pstmt.close();
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		return chatList;
	}
	
	public ArrayList<Chat> getChatListByRecent(int num)
	{
		ArrayList<Chat> chatList = new ArrayList<Chat>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		System.out.println("num : " + num);
		String SQL = "SELECT * FROM CHAT WHERE chatID > (SELECT MAX(chatID) - ? FROM CHAT) ORDER BY chatTime";
		try
		{
			pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1,num);
			rs=pstmt.executeQuery();
			while(rs.next())
			{
				Chat chat = new Chat();
				chat.setChatName(rs.getString("chatName"));
				chat.setChatID(rs.getInt("chatID"));
				System.out.println("dao in content : " + (rs.getString("chatContent")).trim());
				chat.setChatContent((rs.getString("chatContent")).trim());
				/*int chatTime = Integer.parseInt(rs.getString("chatTime").substring(11,13));
				String timeType = "오전";
				if(Integer.parseInt(rs.getString("chatTime").substring(11,13))>=12)
				{
					timeType = "오후";
					chatTime -= 12;
				}*/
				chat.setChatTime(rs.getString("chatTime"));
				System.out.println("in dao // chatName : " + chat.getChatName());
				chatList.add(chat);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			try
			{
				if(rs!=null) rs.close();
				if(pstmt !=null) pstmt.close();
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		return chatList;
	}
	
	public ArrayList<Chat> getChatListByRecent(String chatID)
	{
		ArrayList<Chat> chatList = new ArrayList<Chat>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String SQL = "SELECT * FROM CHAT WHERE chatID > ? ORDER BY chatTime";
		try
		{
			pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1,Integer.parseInt(chatID));
			rs=pstmt.executeQuery();
			while(rs.next())
			{
				Chat chat = new Chat();
				chat.setChatName(rs.getString("chatName"));
				chat.setChatID(rs.getInt("chatID"));
				chat.setChatContent((rs.getString("chatContent")).trim());
				/*int chatTime = Integer.parseInt(rs.getString("chatTime").substring(11,13));
				String timeType = "오전";
				if(Integer.parseInt(rs.getString("chatTime").substring(11,13))>=12)
				{
					timeType = "오후";
					chatTime -= 12;
				}*/
				chat.setChatTime(rs.getString("chatTime"));
				chatList.add(chat);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			try
			{
				if(rs!=null) rs.close();
				if(pstmt !=null) pstmt.close();
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		return chatList;
	}
	
	public int submit(String chatName,String chatContent)
	{
		ArrayList<Chat> chatList = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String SQL = "INSERT INTO CHAT VALUES(?, ?, now(),NULL)";
		try
		{
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1,chatName);
			pstmt.setString(2, chatContent);
			return pstmt.executeUpdate();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			try
			{
				if(rs!=null) rs.close();
				if(pstmt !=null) pstmt.close();
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		return -1;
	}
}
