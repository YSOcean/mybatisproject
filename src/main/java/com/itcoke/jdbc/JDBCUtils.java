package com.itcoke.jdbc;

import com.itcoke.bean.Person;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JDBCUtils {
    //MySQL数据库驱动,注意多了一个cj，com.mysql.jdbc.Driver和mysql-connector-java 5一起用
    public static String driverClass = "com.mysql.cj.jdbc.Driver";
    //MySQL用户名
    public static String userName = "root";
    //MySQL密码
    public static String passWord = "root1234";
    //MySQL URL
    public static String url = "jdbc:mysql://localhost:3306/mybatis-study";
    //定义数据库连接
    public static Connection conn = null;
    //定义声明数据库语句,使用 预编译声明 PreparedStatement提高数据库执行性能
    public static PreparedStatement ps = null;
    //定义返回结果集
    public static ResultSet rs = null;

    public static List<Person> selectPersonByName(String pname){
        List<Person> personList = new ArrayList<>();

        try {
            // 1、加载数据库驱动
            Class.forName(driverClass);
            // 2、获取数据库连接
            conn = DriverManager.getConnection(url,userName,passWord);
            // 3、定义 sql 语句,?表示占位符
            String sql = "select * from person where pname=?";
            // 4、获取预编译处理的statement
            ps = conn.prepareStatement(sql);
            //设置sql语句中的参数，第一个为sql语句中的参数的?(从1开始)，第二个为设置的参数值
            ps.setString(1, "itcoke");
            // 5、向数据库发出 sql 语句查询，并返回结果集
            rs = ps.executeQuery();
            while(rs.next()){
                // 6、读取ResultSet，转换成我们要的对象
                Person person = new Person();
                person.setPid(rs.getLong("pid"));
                person.setPname(rs.getString("pname"));
                personList.add(person);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            // 7、关闭数据库连接
            if(rs!=null){
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(ps!=null){
                try {
                    ps.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(conn!=null){
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return personList;
    }

    public static void main(String[] args){
        List<Person> personList = JDBCUtils.selectPersonByName("itcoke");
        System.out.println(personList);
    }
}
