package com.ldu.blog.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ldu.blog.model.UserEntity;
import com.ldu.blog.repository.UserRepository;

@Controller
public class MainController {
	
	
	//�Զ�װ�����ݿ�ӿڣ�����Ҫ��дԭʼ��Connection���������ݿ�
	@Autowired
	UserRepository userRepository;
	
	
		@RequestMapping(value="/",method=RequestMethod.GET)
		public String index(){
			return "index";
		}
		
		@RequestMapping(value="/admin/users",method=RequestMethod.GET)
		public String getUsers(ModelMap modelMap){
			//��ѯuser�������м�¼
			List<UserEntity> userList=userRepository.findAll();
			modelMap.addAttribute("userList", userList);
			return "admin/users";
		}
		
		//get���󣬷�������û�ҳ��
		@RequestMapping(value="/admin/users/add")
		public String addUser(){
			return "admin/addUser";
		}
		
		
		//post���󣬴�������û����󣬲��ض����û�����ҳ��
		@RequestMapping(value="/admin/users/addP",method=RequestMethod.POST)
		
		public String addUserPost(@ModelAttribute("user")UserEntity userEntity){
			
			//post���󴫵ݹ�������һ��UserEntity�Ķ�����������˸��û�����Ϣ
			//ͨ��ModelAttribute()ע����Ի�ȡ���ݹ�����user,�������������
			
			//�����ݿ������һ���û���������ˢ�»���
			userRepository.saveAndFlush(userEntity);
			
			//�ض����û�����ҳ�棬��redirect:urlʵ��
			return "redirect:/admin/users";
		}
		
		//�鿴�û�����
		
		@RequestMapping(value="/admin/users/show/{id}",method=RequestMethod.GET)
		public String showUser(@PathVariable("id")Integer userId,ModelMap modelMap){
			
			
			//ͨ��userid�ҵ���Ӧ�û�
			UserEntity userEntity=userRepository.findOne(userId);
			
			//���ݸ�����ҳ��
			modelMap.addAttribute("user",userEntity);
			return "admin/userDetail";
		}
		
		//�����û���Ϣ
		@RequestMapping(value="/admin/users/update/{id}",method=RequestMethod.GET)
		public String update(@PathVariable("id")Integer userId,ModelMap modelMap){
			
			
			//ͨ��userid���Ҷ�Ӧ���û�
			UserEntity userEntity=userRepository.findOne(userId);
			
			//���ݸ�����ҳ��
			modelMap.addAttribute("user",userEntity);
			
			return "admin/updateUser";
		}
		
		//�����û���Ϣ����
		@RequestMapping(value="/admin/users/updateP",method=RequestMethod.GET)
		public String updateUserPost(@ModelAttribute("userP")UserEntity user){
			
			
			//ʹ��JpaRepository�з��������û���Ϣ
			userRepository.updateUser(user.getNickname(), user.getFirstName(), user.getLastName(), user.getPassword(), user.getId());
			
			//ˢ�»�����
			userRepository.flush();
			return "redirect:/admin/users";
		}
		
		//ɾ���û�
		@RequestMapping(value="/admin/users/delete/{id}",method=RequestMethod.GET)
		public String deleteUser(@PathVariable("id")Integer userId){
			
			//ɾ��idΪuserId���û�
			userRepository.delete(userId);
			//ˢ�»���
			userRepository.flush();
			
			return "redirect:/admin/users";
		}
		
}
