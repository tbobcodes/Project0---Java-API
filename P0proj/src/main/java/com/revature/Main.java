package com.revature;

import com.revature.controllers.AuthController;
import com.revature.controllers.EmployeeController;
import com.revature.controllers.RoleController;
import com.revature.utils.ConnectionUtil;
import io.javalin.Javalin;

import java.sql.Connection;
import java.sql.SQLException;

public class Launcher {

    public static void main(String[] args) throws InterruptedException {

        /* This is a "try with resources" block
        A resource is opened up within the parentheses of the try block
        If the resource is successfully created, the rest of the try block runs
        A big benefit of this is that the resource will close after the try block finishes
        This is helpful for code cleanup and preventing memory leaks */
        try(Connection conn = ConnectionUtil.getConnection()){
            System.out.println("CONNECTION SUCCESSFUL :)");
        } catch(SQLException e){
            e.printStackTrace(); //tell us what went wrong
            System.out.println("connection failed :(");
        }

        //Typical Javalin setup syntax
        var app = Javalin.create(/*any extra configs would go here*/)
                .start(3000)
                .get("/", ctx -> ctx.result("Hello Postman!")); //callable resource just for fun

        /*We need .start() to start our Javalin server on port 3000
         You can choose any port, I chose 3000 because probably nothing is running on it
         a port is like a parking space for an application, where messages, etc. can find it*/


        //ENDPOINT HANDLERS----------------------

        /*Below, we'll expose different paths to different functionalities...
        ...by using the app.get(), app.post(), app.put(), etc. methods (not shown: app.patch, app.delete)
         When requests come in, they must match one these paths in order to execute some specific behavior.
         they'll call the Handlers we write in our Controllers- they "handle" http requests */

        //instantiate Controllers so we can access their Handlers
        EmployeeController ec = new EmployeeController();
        RoleController rc = new RoleController();
        AuthController ac = new AuthController();

        /*app.get() is the Javalin method that takes GET requests
         In this case, it's calling to the getEmployeesHandler in the EmployeeController
         SO, when we send a GET request to localhost:3000/employees, it goes here.*/
        app.get("/employees", ec.getEmployeesHandler);

        //app.post() is the Javalin method that takes in POST requests
        //Why are we allowed to have two handlers that end in /employees? It's a different resource since it's a POST, not a GET
        app.post("/employees", ec.insertEmployeeHandler);

        //this endpoint lets us get a Role by ID
        //what is {id}? this is a PATH PARAMETER. The id we search for is variable.
        app.get("/roles/{id}", rc.getRoleByIdHandler);

        //this endpoint lets us update a role salary
        app.patch("/roles/{id}", rc.updateSalaryHandler);

        //this endpoint will accept POST requests to /login to login the user
        app.post("/login", ac.loginHandler);

    }

}
