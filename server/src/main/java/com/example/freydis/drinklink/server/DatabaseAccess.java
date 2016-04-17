package com.example.freydis.drinklink.server;

import java.io.IOException;
import java.nio.charset.Charset;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Enumeration;


import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.*;

import com.google.appengine.api.utils.SystemProperty;



public class DatabaseAccess extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/plain");
        String db_url;
        Connection connection;
        ////response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        try {
            if (SystemProperty.environment.value() == SystemProperty.Environment.Value.Production) {
                Class.forName("com.mysql.jdbc.GoogleDriver");
                //jdbc:google:mysql://<project id>:<instance name>/<database name>
                db_url = "jdbc:google:mysql://spheric-alcove-124715:drinklink01/drinklinkdb";
            } else {
                Class.forName("com.mysql.jdbc.Driver");
                db_url = "jdbc:mysql://173.194.242.21:3306/drinklinkdb";
            }
        } catch (Exception e) {
            response.getWriter().println("exception1: "+e.getMessage());
            e.printStackTrace();
            return;
        }

        try {
            Class.forName("com.mysql.jdbc.GoogleDriver");

            //jdbc:google:mysql://<project id>:<instance name>
            String url = "jdbc:google:mysql://spheric-alcove-124715:drinklink01/drinklinkdb";
            //response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            //open connection
            connection = DriverManager.getConnection(db_url,"root","root");
            //Connection connection = DriverManager.getConnection(url);

            //sql query
            Statement statement = connection.createStatement();
            //String sql = "SELECT user_id FROM Users";
            String sql = "SELECT * FROM users";
            ResultSet resultSet = statement.executeQuery(sql);

            //ServletOutputStream out = response.getOutputStream();
            response.setContentType("text/plain");
            String responseBody = "";
            while(resultSet.next()) {
                responseBody += resultSet.getString("firstname") + "\n";
            }
            String[] paramNames = getParamNames(request);
            for(int i = 0; i < paramNames.length; i++ ) {
                String[] paramValues = request.getParameterValues(paramNames[i]);
                responseBody += paramNames[i] + " ";
                for(int j = 0; j < paramValues.length; j++) {
                    responseBody += paramValues[j] + " ";
                }
                responseBody += "\n";
            }

            response.getWriter().println(responseBody);
            response.getWriter().flush();
            //response.getWriter().close();

            response.getWriter().println("something");



        } catch (SQLException e) {
            response.getWriter().println("exception: "+e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            response.getWriter().println("exception: "+e.getMessage());
            e.printStackTrace();
        }

    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String user_id = request.getParameter("user_id");
        String firstname = request.getParameter("firstname");
        String lastname = request.getParameter("lastname");


        response.setContentType("text/plain");
        Connection connection;
        String db_url;

        try {
            if (SystemProperty.environment.value() == SystemProperty.Environment.Value.Production) {
                Class.forName("com.mysql.jdbc.GoogleDriver");
                //jdbc:google:mysql://<project id>:<instance name>/<database name>
                db_url = "jdbc:google:mysql://spheric-alcove-124715:drinklink01/drinklinkdb";
            } else {
                Class.forName("com.mysql.jdbc.Driver");
                db_url = "jdbc:mysql://173.194.242.21:3306/drinklinkdb";
            }
        } catch (Exception e) {
            response.getWriter().println("exception1: "+e.getMessage());
            e.printStackTrace();
            return;
        }

        try {
            //open connection
            connection = DriverManager.getConnection(db_url, "root", "root");
            try {
                // use prepared statement to avoid sql injections etc.. :3
                String statement = "insert into users (user_id, firstname, lastname) values(?, ?, ?)";
                PreparedStatement preparedStatement = connection.prepareStatement(statement);

                preparedStatement.setString(1, user_id);
                preparedStatement.setString(2, firstname);
                preparedStatement.setString(3, lastname);
                int success;
                success = preparedStatement.executeUpdate();
                if (success == 1) {
                    response.getWriter().println("success");
                } else if (success == 0) {
                    response.getWriter().println("unsuccess");
                }
            } finally {
                connection.close();
            }
        } catch (SQLException e) {
            response.getWriter().println("doot doot exception2: "+e.getMessage());
            e.printStackTrace();
        }
    }


    public String[] getParamNames(HttpServletRequest req) {
        ArrayList<String> names = new ArrayList<String>();
        Enumeration<String> parameterNames = req.getParameterNames();
        while (parameterNames.hasMoreElements()) {
            names.add(parameterNames.nextElement());
        }
        String[] result = new String[names.size()];
        result = names.toArray(result);
        return result;
    }
    public String[] getParamValues(String param, HttpServletRequest req) {
        String[] parameterValues = req.getParameterValues(param);
        return (String[]) parameterValues;
    }
}
