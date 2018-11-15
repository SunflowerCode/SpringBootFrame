package com.springboot.frame.controller;

import com.github.pagehelper.PageHelper;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.springboot.frame.dao.userMapper;
@RestController
public class testController {

    @Autowired
    userMapper user;

    @ApiOperation(value = "根据id查询user的信息",notes = "查询数据库中某个user的信息")
    @ApiImplicitParam(name ="id",value = "用户id",dataType = "Integer",paramType = "query",required = true,example = "1")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id",value = "用户id",dataType = "Integer",paramType = "query",required = true,example = "1")
    })
    @ApiResponses({
            @ApiResponse(code=400,message = "请求参数没有填好"),
            @ApiResponse(code=404,message="请求路径没有找到")
    })
    @RequestMapping(value="/test",method = RequestMethod.GET)
    public Object test(@RequestParam("id") Integer id){
        return user.selectByPrimaryKey(id);
    }

    @ApiOperation(value = "查询所有user的信息接口",notes = "查询数据库中所有user的信息")
    @ApiImplicitParam(name ="pageNum",value = "第几页",dataType = "Integer",paramType = "query",required = true,example = "3")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageSize",value = "每页显示user信息条数",dataType = "Integer",paramType = "query",required = true,example = "3")
    })
    @ApiResponses({
            @ApiResponse(code=400,message = "请求参数没有填好"),
            @ApiResponse(code=404,message="请求路径没有找到")
    })
    @RequestMapping(value="/all",method = RequestMethod.GET)
    public Object getAllUsers(@RequestParam("pageNum") Integer pageNum,
                              @RequestParam("pageSize") Integer pageSize){
        PageHelper.startPage(pageNum,pageSize);
        return user.selectAllUsers();
    }
}
