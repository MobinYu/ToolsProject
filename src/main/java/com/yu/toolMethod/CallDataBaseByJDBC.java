package com.yu.toolMethod;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.yu.jdbc.JdbcUtils;
import com.yu.model.User;

public class CallDataBaseByJDBC {
private static List<User> userList = new ArrayList<User>();

    public static void accessDB() {
        getUserList();
        
        insertEntLeagals();
    }

    private static void getUserList() {
        StringBuffer sql = new StringBuffer();
        sql.append(" SELECT u.id,u.age,u.name ");
        sql.append(" FROM t_sys_user u ");
        
        ResultSet rs = JdbcUtils.query(sql.toString(), null);
        try {
            while (rs.next()) {
                User enterprise = new User();
                enterprise.setId(rs.getString(1));
                if (null != rs.getString(2)){
                	enterprise.setAge(Integer.valueOf(rs.getString(2)));
                }
                enterprise.setName(rs.getString(3));
                
                userList.add(enterprise);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void insertEntLeagals() {
        if (userList.size() == 0){
            return;
        }
        
        for (User user : userList) {
            insertEntLeagal(user);
        }
        
    }
    
    private static void insertEntLeagal(User user) {
        StringBuffer sql = new StringBuffer();
        sql.append(" insert into t_sys_access_admin (dataID,adminName,userID,statusCode,updatetime) ");
        sql.append(" values ( ");
        sql.append("UUID_SHORT(),");
        sql.append("'").append(user.getName()).append("',");
        sql.append("'").append(user.getId()).append("',");
        sql.append("0").append(",");
        sql.append("now()");
        sql.append(" ); ");
        
        JdbcUtils.update(sql.toString(), null);
    }
}
