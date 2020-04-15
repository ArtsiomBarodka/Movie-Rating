package epam.my.project.main;

import epam.my.project.configuration.SecurityConfiguration;
import epam.my.project.service.impl.AuthenticateAndAuthorizationServiceImpl;
import epam.my.project.util.DataUtil;
import epam.my.project.validation.ServiceValidatorFactory;


import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.sql.*;

public class MainTest {
    public static void main(String[] args) throws SQLException, InterruptedException, NoSuchProviderException, NoSuchAlgorithmException {

//        for (int i = 0; i < 100; i++) {
//            A a = new A(i);
//            a.start();
//        }
//        Thread.sleep(1000);
//        DataSource.CONNECTION_POOL_INSTANCE.shutdown();


    }

}

//class A extends Thread {
//    private int index;
//
//    A(int index){
//        this.index = index;
//    }
//    public void run(){
//        System.out.printf("Start %d%n", index);
//        Connection connection = DataSource.CONNECTION_POOL_INSTANCE.getConnection();
//        System.out.printf("%d Get connection%n", index);
//        System.out.println(connection);
//        try {
//            Thread.sleep(500);
//        } catch (InterruptedException e){
//            System.out.printf("%d has been interrupted%n", index);
//        } finally {
//            DataSource.CONNECTION_POOL_INSTANCE.returnConnection(connection);
//            System.out.printf("%d Release connection%n", index);
//        }
//        System.out.printf("Finish %d%n", index);
//    }
//}