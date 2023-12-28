package com.empsys.controller;
import java.util.List;

import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import com.empsys.entity.Employee;
import com.empsys.service.EmpService;
@Controller
public class EmpController {
	@Autowired
	private EmpService service;
	
	@GetMapping("/home")
	public String home(Model m){
		List<Employee> emp=service.getAllEmp();
		m.addAttribute("emp", emp);
		return "index";         
	}
	
	@GetMapping("/addemp")
	public String addEmpForm(){
		return "addEmp";       
	}
	
	@PostMapping("/register")
    public String empRegister(@ModelAttribute Employee e,HttpSession session,Model model){
		System.out.println(e);
		service.saveEmp(e);	
		session.setAttribute("msg", "data inserted successfully....");
		return "redirect:/home";                                                    
	}
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable int id, Model m){
		Employee e=service.getEmpById(id);
		m.addAttribute("emp", e);
		return "edit";	                             }
	@PostMapping("/update")
	public String updateEmp(@ModelAttribute Employee e, HttpSession session){
		service.saveEmp(e);
		session.setAttribute("msg", "data updated successfully....");
		return "redirect:/home";                                            }
	@GetMapping("/delete/{id}")
	public String deleteEmp(@PathVariable int id,HttpSession session){
	service.deleteEmp(id);
	session.setAttribute("msg", "Emp data deleted successfully");
	return "redirect:/home";                                         }
}