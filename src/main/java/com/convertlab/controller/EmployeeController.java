package com.convertlab.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.convertlab.entity.Employee;
import com.convertlab.service.IEmployeeService;
import com.convertlab.utils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author limei
 * @since 2021-03-02
 */
@RestController
@RequestMapping("/convertlab/employee")
@Api(tags="操作员工信息")
public class EmployeeController {

    @Autowired
    public IEmployeeService employeeService;


    @ApiOperation("新增用户信息")
    @PostMapping("/insert")
    @ResponseBody
    public Result insert(Employee employee/*, HttpSession session*/){
        /*User user = (User)session.getAttribute("User");
       if(user==null){
            return Result.returnFail("你还没有登录，请先去登录");
        }
        Long userId = user.getId();*/
      if (employeeService.saveOrUpdate(employee)==true){
          return Result.returnSuccess("插入成功！");
      }else{
          return Result.returnFail("插入失败！");
      }

    }

    @ApiOperation("查询用户信息")
    @PostMapping("/select")
    @ResponseBody
    public Result select(){
       List<Employee> al = employeeService.list();
       return Result.returnSuccess(al,"查询成功！");
    }

    @ApiOperation("删除用户信息")
    @PostMapping("/delete")
    @ResponseBody
    public Result delete(String name){
        QueryWrapper<Employee> wrapper = new QueryWrapper<>();
        wrapper.eq("emp_name",name);
        if(employeeService.remove(wrapper)==true){
            return Result.returnSuccess("删除成功！");
        }else{
            return Result.returnFail("删除失败！");
        }

    }

    @ApiOperation("更改用户信息")
    @PostMapping("/update")
    @ResponseBody
    public Result update(Employee employee){
        if(employeeService.updateById(employee)==true){
            return Result.returnSuccess("更新成功！");
        }else{
            return Result.returnFail("更新失败！");
        }



    }


}
