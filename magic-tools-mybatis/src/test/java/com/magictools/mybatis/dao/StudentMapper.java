package com.magictools.mybatis.dao;

import com.magictools.mybatis.bean.Student;
import jdk.nashorn.internal.ir.LiteralNode;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface StudentMapper {

	void createTable();

	Student getStuById(Integer id);

	List<Student> getList(@Param("map") Map<String, Object> map);
}
