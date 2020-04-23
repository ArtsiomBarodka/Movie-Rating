package epam.my.project.main;


import epam.my.project.configuration.Constants;
import epam.my.project.configuration.SortMode;
import epam.my.project.dao.jdbc.pool.impl.DataSource;
import epam.my.project.exception.InternalServerErrorException;
import epam.my.project.exception.ObjectNotFoundException;
import epam.my.project.model.domain.Page;
import epam.my.project.model.entity.Movie;
import epam.my.project.model.form.SearchMovieForm;
import epam.my.project.service.ViewMovieService;
import epam.my.project.service.impl.ViewMovieServiceImpl;
import epam.my.project.util.DataUtil;

import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.sql.*;
import java.util.List;

public class MainTest {
    public static void main(String[] args) throws SQLException, InterruptedException, NoSuchProviderException, NoSuchAlgorithmException, InternalServerErrorException, ObjectNotFoundException {

//
//        for (int i = 0; i < 100; i++) {
//            A a = new A(i);
//            a.start();
//        }
//        Thread.sleep(7000);
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
//            Thread.sleep(1000);
//        } catch (InterruptedException e){
//            System.out.printf("%d has been interrupted%n", index);
//        } finally {
//            DataSource.CONNECTION_POOL_INSTANCE.returnConnection(connection);
//            System.out.printf("%d Release connection%n", index);
//        }
//        System.out.printf("Finish %d%n", index);
//    }
//}