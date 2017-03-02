package com.ldu.blog.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
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
	@RequestMapping(value="/admin/blogs/addP",method=RequestMethod.POST)
	public String addBlogPost(@ModelAttribute("blog")BlogEntity blogEntity){
		
		//System.out.println(blogEntity.getId()+blogEntity.getTitle());
		//�����Ĵ浽���ݿⲢˢ�»���
		blogRepository.saveAndFlush(blogEntity);
		return "redirect:/admin/blogs";
	}
	
	//�鿴��������
	@RequestMapping(value="/admin/blogs/show/{id}")
	public String showBlog(@PathVariable("id") int id,ModelMap modelMap){
		//����blogIdΪid�Ĳ���
		BlogEntity blog=blogRepository.findOne(id);
		//ע�뵽jspҳ��
		modelMap.addAttribute("blog", blog);
		return "admin/blogDetail";
	}
	
	//���²���
	@RequestMapping(value="/admin/blogs/update/{id}")
	public String updateBlog(@PathVariable("id")int id,ModelMap modelMap){
		
		BlogEntity blog=blogRepository.findOne(id);
		modelMap.addAttribute("blog",blog);
		List<UserEntity> userList=userRepository.findAll();
		modelMap.addAttribute("userList",userList);
		return "admin/updateBlog";
	}
	
	//���²��Ͳ���
	@RequestMapping(value="/admin/blogs/updateP",method=RequestMethod.POST)
	public String updateBlogP(@ModelAttribute("blogP")BlogEntity blog){
		blogRepository.updateBlog(blog.getTitle(),blog.getUserByUserId().getId(),blog.getContent(),blog.getPubDate(),blog.getId());
		
		blogRepository.flush();
		return "redirect:/admin/blogs";
	}
	
	//ɾ������
	@RequestMapping(value="/admin/blogs/delete/{id}")
	public String deleteBlog(@PathVariable("id")int id){
		
		blogRepository.delete(id);
		blogRepository.flush();
		return "redirect:/admin/blogs";
	}
}
