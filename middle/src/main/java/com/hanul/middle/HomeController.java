package com.hanul.middle;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.text.DateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;

import com.google.gson.Gson;

import common.Common;
import customer.CustomerVO;
import member.MemberVO;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	@Autowired @Qualifier("hanul") private SqlSession sql;
	Gson gson = new Gson();

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
	
		return "home";
	}
	@ResponseBody
	@RequestMapping("/test")
	public void test(HttpServletRequest req, HttpServletResponse res) throws IOException {
		System.out.println("Test");

		req.setCharacterEncoding("UTF-8");
		System.out.println(req.getParameter("data"));
		List<CustomerVO> list = sql.selectList("customer.mapper.list");
		res.setCharacterEncoding("UTF-8");
		res.setContentType("text/html");
		req.setCharacterEncoding("UTF-8");
		PrintWriter out = res.getWriter();
		out.println(gson.toJson(list));
	}
	@ResponseBody
	@RequestMapping("/list.cu")
	public void list(HttpServletRequest req, HttpServletResponse res , String search) throws IOException {
		System.out.println(search);
		search = req.getParameter("search");
		System.out.println(search);
		List<CustomerVO> list = sql.selectList("customer.mapper.list",search);
		res.setCharacterEncoding("UTF-8");
		res.setContentType("text/html");
		req.setCharacterEncoding("UTF-8");
		PrintWriter out = res.getWriter();
		out.println( gson.toJson(list) );
	}
	@ResponseBody
	@RequestMapping("/file.f")
	public void filef(HttpServletRequest req, HttpServletResponse res , HttpSession session) {
		System.out.println(req.getParameter("idx"));
		//String tempVo = req.getParameter("vo");
		//MemberVO vo = gson.fromJson(tempVo, MemberVO.class);
		
		
		MultipartRequest reqs = (MultipartRequest) req;
	
		MultipartFile f = reqs.getFileMap().get("file") ;
		if(f != null) {
		
			System.out.println("파일 들어옴");
			Common common = new Common();
			String imgpath = common.fileUpload(session, f, "test");
			String server_path = "http://" + req.getLocalAddr() + ":" + req.getLocalPort()+req.getContextPath() +  "/resources/";
			System.out.println(server_path + imgpath);
			//vo.setFile_path(server_path + imgpath);
			//int ok = sql.insert("member.mapper.insert" , vo);
			
		}else {
			System.out.println("파일 안들어옷");
		}
		
	}
	
	@ResponseBody
	@RequestMapping("/login")
	public void login (HttpServletRequest req, HttpServletResponse res ) throws IOException {
		res.setCharacterEncoding("UTF-8");
		res.setContentType("text/html");
		req.setCharacterEncoding("UTF-8");
		MemberVO vo = new MemberVO();
		vo.setId(req.getParameter("id")+"");
		vo.setPw(req.getParameter("pw")+"");
		vo = sql.selectOne("member.mapper.login" , vo);
		PrintWriter out = res.getWriter();
		out.println( gson.toJson(vo) );
		
	}
	@ResponseBody
	@RequestMapping("/testt")
	public void testt (HttpServletRequest req, HttpServletResponse res ) throws IOException {
		res.setCharacterEncoding("UTF-8");
		res.setContentType("text/html");
		req.setCharacterEncoding("UTF-8");
		PrintWriter out = res.getWriter();
		System.out.println(1);
		out.println( gson.toJson(1) );
	}
}
