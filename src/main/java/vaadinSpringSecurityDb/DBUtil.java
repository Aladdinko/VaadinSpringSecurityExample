//package vaadinSpringSecurityDb;
//
//import javax.sql.DataSource;
//import java.sql.Connection;
//import java.sql.SQLException;
//import java.sql.Statement;
//
///**
//* Created by Maggouh on 20/01/17.
//*/
//public class DBUtil {
//
//    private DataSource dataSource;
//
//    public DataSource getDataSource() {
//        return dataSource;
//    }
//
//    public void setDataSource(DataSource dataSource) {
//        this.dataSource = dataSource;
//    }
//
//    public void initializea() {
//        DataSource dataSource = getDataSource();
//        try {
//            Connection connection = dataSource.getConnection();
//
//            Statement statement = connection.createStatement();
//            statement.executeUpdate("CREATE TABLE USER_AUTHENTICATION(USER_ID INTEGER , USERNAME VARCHAR(50), PASSWORD VARCHAR(50));");
//            statement.executeUpdate("INSERT INTO USER_AUTHENTICATION VALUES (1, 'admin@admin.com', 'password1');");
//            statement.executeUpdate("INSERT INTO USER_AUTHENTICATION VALUES (2, 'user@user.com', 'password2');");
//            statement.executeUpdate("INSERT INTO USER_AUTHENTICATION VALUES (3, 'trainee@trainee.com', 'password3');");
//            statement.executeUpdate("CREATE TABLE USER_AUTORIZATION(USER_ROLE_ID INTEGER, USER_ID INTEGER, ROLE VARCHAR(50));");
//            statement.executeUpdate("INSERT INTO USER_AUTORIZATION VALUES (1,1, 'ROLE_ADMIN');");
//            statement.executeUpdate("INSERT INTO USER_AUTORIZATION VALUES (2,2, 'ROLE_USER' );");
//            statement.executeUpdate("INSERT INTO USER_AUTORIZATION VALUES (3,3, 'ROLE_TRAINEE');");
//
//            statement.close();
//            connection.close();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//}
