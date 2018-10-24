package com.yu;

import java.util.ArrayList;
import java.util.List;

import com.yu.model.User;
import com.yu.toolMethod.CallDataBaseByJDBC;
import com.yu.toolMethod.CoordinateConvert;
import com.yu.toolMethod.ObjectListDuplicateRemove;

/**
 * Hello world!
 *
 */
public class App
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        
        //1、火星坐标系 (GCJ-02)转为百度坐标系 (BD-09)
        CoordinateConvert.gcj02_To_Bd09(60486.107832982100, 49554.379575900450);
        
        //2、集合对象去重（根据名称）
        List<User> userlist = new ArrayList<User>();
        userlist.add(new User("01","18","AAA"));
        userlist.add(new User("02","20","AAA"));
        
        ObjectListDuplicateRemove.removeDuplicateObject1(userlist); //ObjectListDuplicateRem.removeDuplicateObject2(userlist);
        
      //3、JDBC操作数据库
        CallDataBaseByJDBC.accessDB();
        
    }
}
