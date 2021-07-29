package com.flipkart.restController;
import java.util.ArrayList;
import java.util.List;
import javax.validation.Valid;
import javax.validation.ValidationException;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.flipkart.bean.Admin;
import com.flipkart.business.StudentInterface;
import com.flipkart.business.StudentOperation;
import org.hibernate.validator.constraints.Email;
import com.flipkart.bean.Course;
import com.flipkart.bean.Professor;
import com.flipkart.bean.Student;
import com.flipkart.exception.*;
import com.flipkart.business.AdminInterface;
import com.flipkart.business.AdminOperation;
/**
 *
 * @author JEDI-Group05
 * Admin Rest API Controller
 *
 */
@Path("/admin")
public class AdminRESTAPI {
    AdminInterface adminOperation = AdminOperation.getInstance();
    StudentInterface studentOperation = StudentOperation.getInstance();
    /**
     * /admin/createProfessor
     * REST-service for adding a new professor
     * @param professor
     * @return userId
     */
    @POST
    @Path("/createProfessor")
    @Consumes(MediaType.APPLICATION_JSON)
//********CHECK
    //@Consumes("application/json")
    //@Produces(MediaType.APPLICATION_JSON)
    public Response createProfessor(Professor professor){
        int professorId = 0;
        try {
            professorId = adminOperation.createProfessor(professor);
            System.out.println("Professor added");
            return Response.status(201).entity("Professor with userId: " + professorId + " added").build();
        } catch (Exception ex) {
            System.out.println(professor.toString());
            return Response.status(500).entity("Something went wrong! Please try again.").build();
        }
    }
    /**
     * /admin/createAdmin
     * REST-service for adding a new admin
     * @param admin
     * @return userId
     */
    @POST
    @Path("/createAdmin")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createAdmin(Admin admin){
        int adminId = 0;
        try {
            adminId = adminOperation.createAdmin(admin);
            System.out.println("Admin added");
            return Response.status(201).entity("Admin with userId: " + adminId + " added").build();
        } catch (Exception ex) {
            System.out.println(admin.toString());
            return Response.status(500).entity("Something went wrong! Please try again.").build();
        }
    }
    /**
     * /admin/viewPendingAdmissions
     * REST-service for getting un approved Students
     * @return
     */
    @GET
    @Path("/showunapprovedStudents")
    @Produces(MediaType.APPLICATION_JSON)
    public void showunapprovedStudents() {
        adminOperation.showunapprovedStudents();
    }

    @GET
    @Path("/displayAdmins")
    @Produces(MediaType.APPLICATION_JSON)
    public void displayAdmins() {
        adminOperation.displayAdmins();

    }

    /**
     * Method to handle API request for approving student
     * @param studentId
     * @return
     */
    @PUT
    @Path("/approveStudent/{studentId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response approveStudent(
            @NotNull
            @PathParam("studentId") int studentId
    ) {
//        Student student = studentOperation.fetchStudent(studentId);
        try {
            List<Integer> students = new ArrayList<Integer>();
            students.add(studentId);
            adminOperation.approveStudent(students);
            return Response.status(200).entity("Successfully approved student").build();
        }
        catch (Exception e) {
            return Response.status(500).entity(e.getMessage()).build();
        }

    }
}