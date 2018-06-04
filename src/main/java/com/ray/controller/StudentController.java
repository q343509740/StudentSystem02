package com.ray.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.ray.entity.Student;
import com.ray.service.StudentService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author Ray
 * @date 2018/5/30 0030
 */
@Controller
public class StudentController {

    @Resource
    private StudentService studentService;

    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

    @RequestMapping("search")
    @ResponseBody
    public JSONArray searchStudent(HttpServletRequest request, HttpServletResponse response){
        String name = request.getParameter("name");
        int total = studentService.getTotalByName(name);
        System.out.println("姓名: " + name);

        List<Student> studentList = studentService.selectByName(name);

        // 创建JSONArray对象
        JSONArray jsonArray = new JSONArray();

        for (Student student :
                studentList) {
            // 创建JSONObject对象
            JSONObject jsonObject = new JSONObject();
            // 添加键值对
            jsonObject.put("id",student.getId());
            jsonObject.put("student_id",student.getStudent_id());
            jsonObject.put("name",student.getName());
            jsonObject.put("age",student.getAge());
            jsonObject.put("sex",student.getSex());
            jsonObject.put("birthday",student.getBirthday());
            jsonObject.put("total",total);
            jsonArray.add(jsonObject);
        }

        HttpSession session = request.getSession();
        session.setAttribute("searchResult", jsonArray);
        System.out.println("jsonArray: " + jsonArray.toString());
        return jsonArray;
    }

    @RequestMapping("get")
    @ResponseBody
    public JSONArray getStudent(HttpServletRequest request, HttpServletResponse response){

        int id = Integer.parseInt(request.getParameter("id"));
        System.out.println("edit_id : " + id);
        List<Student> students = studentService.getStudent(id);

        JSONArray jsonArray = new JSONArray();

        for (Student student :
                students) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("id",student.getId());
            jsonObject.put("student_id",student.getStudent_id());
            jsonObject.put("name",student.getName());
            jsonObject.put("age",student.getAge());
            jsonObject.put("sex",student.getSex());
            jsonObject.put("birthday",student.getBirthday());
            jsonArray.add(jsonObject);
        }
        HttpSession session = request.getSession();
        session.setAttribute("searchResult", jsonArray);
        System.out.println("jsonArray: " + jsonArray.toString());
        return jsonArray;
    }

    @RequestMapping("update")
    @ResponseBody
    public boolean updateStudent(HttpServletRequest request, HttpServletResponse response){

        Student student = new Student();

        int id = Integer.parseInt(request.getParameter("id"));
        int student_id = Integer.parseInt(request.getParameter("student_id"));
        String name = request.getParameter("name");
        int age = Integer.parseInt(request.getParameter("age"));
        String sex = request.getParameter("sex");
        Date birthday = null;
        try{
            birthday = simpleDateFormat.parse(request.getParameter("birthday"));
        }catch(Exception e){
            e.printStackTrace();
        }
        student.setId(id);
        student.setStudent_id(student_id);
        student.setName(name);
        student.setAge(age);
        student.setSex(sex);
        student.setBirthday(birthday);

        studentService.updateStudent(student);
        return true;
    }

    @RequestMapping("delete")
    @ResponseBody
    public boolean deleteStudent(HttpServletRequest request, HttpServletResponse response){
        int id = Integer.parseInt(request.getParameter("id"));
        System.out.println("delete id : " + id);
        studentService.deleteStudent(id);
        return true;
    }

    @RequestMapping("add")
    @ResponseBody
    public boolean addStudent(HttpServletRequest request, HttpServletResponse response){

        Student student = new Student();

        int student_id = Integer.parseInt(request.getParameter("student_id"));
        String name = request.getParameter("name");
        int age = Integer.parseInt(request.getParameter("age"));
        String sex = request.getParameter("sex");
        Date birthday = null;
        try{
            birthday = simpleDateFormat.parse(request.getParameter("birthday"));
        }catch (Exception e){
            e.printStackTrace();
        }

        student.setStudent_id(student_id);
        student.setName(name);
        student.setAge(age);
        student.setSex(sex);
        student.setBirthday(birthday);

        studentService.addStudent(student);

        return true;
    }

    @RequestMapping("pageHelper")
    @ResponseBody
    public JSONArray getList(HttpServletRequest request, HttpServletResponse response,
                             @RequestParam(required = false, defaultValue = "1") int page,
                             @RequestParam(required = false, defaultValue = "10") int rows){
        List<Student> students = studentService.findPageStudent(page, rows);
        return jsonArray(students);
    }

    public JSONArray jsonArray(List<Student> students){
        JSONArray jsonArray = new JSONArray();
        PageInfo pageInfo = new PageInfo(students);
        for (Student student :
                students) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("id",student.getId());
            jsonObject.put("student_id",student.getStudent_id());
            jsonObject.put("name",student.getName());
            jsonObject.put("age",student.getAge());
            jsonObject.put("sex",student.getSex());
            jsonObject.put("birthday",student.getBirthday());
            jsonObject.put("hasPreviousPage",pageInfo.isHasPreviousPage());
            jsonObject.put("hasNextPage",pageInfo.isHasNextPage());
            jsonArray.add(jsonObject);
        }
        return jsonArray;
    }
}
