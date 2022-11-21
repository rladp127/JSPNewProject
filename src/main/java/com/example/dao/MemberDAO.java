package com.example.dao;

import com.example.vo.MemberVO;
import com.example.util.JDBCUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class MemberDAO {
	
	Connection conn = null;
	PreparedStatement stmt = null;
	ResultSet rs = null;

	private final String MEMBER_INSERT = "insert into member(photo, userid, username, email, age, detail) values (?,?,?,?,?,?)";
	private final String MEMBER_UPDATE = "update member set photo=?, userid=?, username=?, email=?, age=?, detail=? where sid=?";
	private final String MEMBER_DELETE = "delete from member where sid=?";
	private final String MEMBER_GET = "select * from member where sid=?";
	private final String MEMBER_LIST = "select * from member order by sid desc";

	public int insertMember(MemberVO data) {
		int result = 0;
		conn = JDBCUtil.getConnection();
		System.out.println("===> JDBC로 insertMember() 기능 처리");
		try {
			stmt = conn.prepareStatement(MEMBER_INSERT);
			stmt.setString(1, data.getPhoto());
			stmt.setString(2, data.getUserid());
			stmt.setString(3, data.getUsername());
			stmt.setString(4, data.getEmail());
			stmt.setString(5, data.getAge());
			stmt.setString(6, data.getDetail());
			result=stmt.executeUpdate();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return result;
	}

	// 글 삭제
	public void deleteMember(MemberVO vo) {
		System.out.println("===> JDBC로 deleteBoard() 기능 처리");
		try {
			conn = JDBCUtil.getConnection();
			stmt = conn.prepareStatement(MEMBER_DELETE);
			stmt.setInt(1, vo.getSid());
			stmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public int updateMember(MemberVO vo) {
		System.out.println("===> JDBC로 updateBoard() 기능 처리");
		try {
			conn = JDBCUtil.getConnection();
			stmt = conn.prepareStatement(MEMBER_UPDATE);
			stmt.setString(1, vo.getPhoto());
			stmt.setString(2, vo.getUserid());
			stmt.setString(3, vo.getUsername());
			stmt.setString(4, vo.getEmail());
			stmt.setString(5, vo.getAge());
			stmt.setString(6, vo.getDetail());
			stmt.setInt(7, vo.getSid());
			
			System.out.println(vo.getPhoto() + "-" + vo.getUserid() + "-" + vo.getUsername() + "-" + vo.getEmail() + "-" + vo.getAge() + "-" + vo.getDetail() + "-" + vo.getSid());
			stmt.executeUpdate();
			return 1;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}	
	
	public MemberVO getBoard(int sid) {
		MemberVO one = new MemberVO();
		System.out.println("===> JDBC로 getBoard() 기능 처리");
		try {
			conn = JDBCUtil.getConnection();
			stmt = conn.prepareStatement(MEMBER_GET);
			stmt.setInt(1, sid);
			rs = stmt.executeQuery();

			if(rs.next()) {
				one.setSid(rs.getInt("sid"));
				one.setPhoto(rs.getString("photo"));
				one.setUserid(rs.getString("userid"));
				one.setUsername(rs.getString("username"));
				one.setEmail(rs.getString("email"));
				one.setAge(rs.getString("age"));
				one.setDetail(rs.getString("detail"));
				one.setRegdate(rs.getDate("regdate"));
			}
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return one;
	}
	
	public List<MemberVO> getBoardList(){
		List<MemberVO> list = new ArrayList<MemberVO>();
		System.out.println("===> JDBC로 getBoardList() 기능 처리");
		try {
			conn = JDBCUtil.getConnection();
			stmt = conn.prepareStatement(MEMBER_LIST);
			rs = stmt.executeQuery();
			while(rs.next()) {
				MemberVO one = new MemberVO();
				one.setSid(rs.getInt("sid"));
				one.setPhoto(rs.getString("photo"));
				one.setUserid(rs.getString("userid"));
				one.setUsername(rs.getString("username"));
				one.setEmail(rs.getString("email"));
				one.setAge(rs.getString("age"));
				one.setDetail(rs.getString("detail"));
				one.setRegdate(rs.getDate("regdate"));
				list.add(one);
			}
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return list;
	}

	public String getPhotoFilename(int seq){
		String filename = null;
		try {
			conn = JDBCUtil.getConnection();
			stmt = conn.prepareStatement(MEMBER_GET);
			stmt.setInt(1, seq);
			rs = stmt.executeQuery();
			if(rs.next()){
				filename = rs.getString("photo");
			}
			rs.close();
		} catch(Exception e){
			e.printStackTrace();
		}
		System.out.println("===> JDBC로 getPhotoFilename() 기능 처리");
		return filename;
	}

}
