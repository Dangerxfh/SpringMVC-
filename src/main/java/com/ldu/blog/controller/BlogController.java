package com.ldu.blog.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ldu.blog.model.BlogEntity;
import com.ldu.blog.model.UserEntity;
import com.ldu.blog.repository.BlogRepository;
import com.ldu.blog.repository.UserRepository;


@Controller
public class BlogController {
	
	@Autowired
	BlogRepository blogRepository;
	
	
	@Autowired
	UserRepository userRepository;
	
	//�鿴���в���
	@RequestMapping(value="/admin/blogs",method=RequestMethod.GET)
	public String showBlogs(ModelMap modelMap){
		
		//�������в���
		List<BlogEntity> blogList=blogRepository.findAll();
		
		//�����ͼ��ϴ��ݵ�ҳ��
		modelMap.addAttribute("blogList", blogList);
		return "admin/blogs";
	}
	
	
	//��Ӳ���
	@RequestMapping(value="/admin/blogs/add",method=RequestMethod.GET)
	public String addBlog(ModelMap modelMap){
		
		//���������û�
		List<UserEntity> userList=userRepository.findAll();
		
		//��jspҳ��ע���û��б�
		modelMap.addAttribute("userList",userList);
		return "admin/addBlog";
	}
	
	//��Ӳ��Ĳ���,�ض��򵽲鿴����ҳ��
	@RequestMapping(value="/webapp/admin/blogs/addP",method=RequestMethod.POST)
	public String addBlogsPost(@ModelAttribute("blog")BlogEntity blogEntity){
		
		System.out.println(blogEntity.getId()+blogEntity.getTitle());
		//�����Ĵ浽���ݿⲢˢ�»���
		blogRepository.saveAndFlush(blogEntity);
		return "redirect:/admin/blogs";
	}
	
}
