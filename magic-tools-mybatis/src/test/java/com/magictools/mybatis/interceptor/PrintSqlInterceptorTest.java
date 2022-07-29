package com.magictools.mybatis.interceptor;

import com.magictools.mybatis.bean.Student;
import com.magictools.mybatis.dao.StudentMapper;
import junit.framework.TestCase;
import org.apache.ibatis.builder.xml.XMLConfigBuilder;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PrintSqlInterceptorTest extends TestCase {

	public void testIntercept() {
	}

	public void test01() throws IOException {
		SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try (Connection connection = sqlSession.getConnection()){
			connection.createStatement().execute("\t\tCREATE TABLE student\n" +
					"\t\t(\n" +
					"\t\t\tid        int(11) NOT NULL AUTO_INCREMENT COMMENT '自增主键(学号)',\n" +
					"\t\t\tlast_name varchar(255) NOT NULL COMMENT '名字',\n" +
					"\t\t\temail     varchar(255) NULL DEFAULT NULL COMMENT '电子邮箱',\n" +
					"\t\t\tgender    varchar(255) NOT NULL COMMENT '性别',\n" +
					"\t\t\tm_id      int(11) NULL DEFAULT NULL COMMENT '专业id',\n" +
					"\t\t\tPRIMARY KEY (id)\n" +
					"\t\t)");
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		SqlSession openSession = sqlSessionFactory.openSession();
		try {


			// 获取接口的实现类对象
			//会为接口自动的创建一个代理对象，代理对象去执行增删改查方法
			StudentMapper mapper = openSession.getMapper(StudentMapper.class);
//			mapper.createTable();
//			Student student = mapper.getStuById(1);
//			System.out.println(student);
			Map<String, Object> map = new HashMap<>();
			map.put("id", 1);
			List<Student> list = mapper.getList(map);


		} finally {
			openSession.close();
		}
	}

	public SqlSessionFactory getSqlSessionFactory() throws IOException {
		String resource = "mybatis-config.xml";
		InputStream inputStream = Resources.getResourceAsStream(resource);
		XMLConfigBuilder parser = new XMLConfigBuilder(inputStream);
		Configuration configuration = parser.parse();
		configuration.addInterceptor(new PrintSqlInterceptor());
//		configuration.addInterceptor(new SqlExecuteTimeCountInterceptor());
		return new SqlSessionFactoryBuilder().build(configuration);
	}
}
