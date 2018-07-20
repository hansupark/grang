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
			String dbID = "cs1234";
			String dbPassword = "1234";
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
		ArrayList<Chat> chatList = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String SQL = "SELECT * FROM CHAT WHERE chatTime > ? ORDER BY chatTime";
		try
		{
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1,nowTime);
			rs=pstmt.executeQuery();
			while(rs.next())
			{
				Chat chat = new Chat();
				chat.setChatName(rs.getString("chatName"));
				chat.setChatContent(rs.getString("chatContent"));
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
		String SQL = "INSERT INTO CHAT VALUES(?, ?, now())";
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
