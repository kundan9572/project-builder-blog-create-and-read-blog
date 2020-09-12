package dao;

import java.io.IOException;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import model.Blog;
import utility.ConnectionManager;

public class BlogDaolmpl implements BlogDaoInterface{
	
	public void insertBlog(Blog blog) throws SQLException, ClassNotFoundException, IOException {
		
		int blogId= blog.getBlogId();
		String blogTitle=blog.getBlogTitle();
		String blogDescription =blog.getBlogDescription();
		LocalDate postedOn = blog.getPostedOn();
		
		ConnectionManager con = new ConnectionManager();
		
		String sql = "INSERT INTO BLOG (BLOGID,BLOGTITLE,BLOGDESCRIPTION,POSTEDON) VALUES(?,?,?,?)";
		
		PreparedStatement st = con.getConnection().prepareStatement(sql);
		
		st.setInt(1, blogId);
		st.setString(2, blogTitle);
		st.setString(3, blogDescription);
		st.setDate(4, Date.valueOf(postedOn));
		
		st.executeUpdate();
		con.getConnection().close();
	}
	
	
	public List  selectAllBlogs() throws ClassNotFoundException, SQLException, IOException {
		
		ConnectionManager con = new ConnectionManager();
		
		Statement st = con.getConnection().createStatement();
		
		String sql = "SELECT * FROM BLOGS";
		
		ResultSet rs = st.executeQuery(sql);
		
		List<Blog> blogList = new ArrayList<Blog>();
		
		Blog blog= new Blog();
		
		while(rs.next()) {
			
			int blogid = rs.getInt("ID");
			String blogTitle = rs.getString("TITLE");
			String blogDesc = rs.getString("DESCRIPTION");
			LocalDate date = rs.getDate("POSTON").toLocalDate();
			blog.setBlogId(blogid);
			blog.setBlogTitle(blogTitle);
			blog.setBlogDescription(blogDesc);
			blog.setPostedOn(date);
			blogList.add(blog);
		}
		
		
		return blogList;
	}
	
}