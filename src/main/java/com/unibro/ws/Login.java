package com.unibro.ws;


/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
import com.google.gson.JsonObject;
import com.unibro.service.UserSessionBean;
import com.unibro.utils.Global;
import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import org.apache.log4j.Logger;

/**
 * REST Web Service
 *
 * @author Nguyen Duc Tho
 */
@Path("login")
public class Login {

    private static final String VALID_REQUEST = "Valid request";
    @Context
    private UriInfo context;
    private final Logger logger = Logger.getLogger(this.getClass().getName());

    /**
     * Creates a new instance of AuthenResource
     */
    public Login() {
    }

    /**
     * @param request
     * @param email
     * @param password
     * @param servletResponse
     * @return an instance of com.zsso.model.SysUsers
     */
    @POST
    @Produces(MediaType.TEXT_PLAIN + ";charset=utf-8")
    public Response getString(@Context HttpServletRequest request,
            @FormParam("email") String email,
            @FormParam("password") String password,
            @Context HttpServletResponse servletResponse) {
        //TODO return proper representation object
        UserSessionBean session = UserSessionBean.getUserSession();
        session.setEmail(email);
        session.setPassword(password);
        if (session.login()) {
            try {
                servletResponse.sendRedirect(Global.getConfigValue("app.homepage"));
                JsonObject obj = new JsonObject();
                obj.addProperty("code", -1);
                obj.addProperty("message", "Invalid user");
                return Response.status(200).entity(obj.toString()).build();
            } catch (IOException ex) {
                
            }
        } else {
            JsonObject obj = new JsonObject();
            obj.addProperty("code", -1);
            obj.addProperty("message", "Invalid user");
            return Response.status(200).entity(obj.toString()).build();
        }
        JsonObject obj = new JsonObject();
        obj.addProperty("code", -1);
        obj.addProperty("message", "Invalid user");
        return Response.status(200).entity(obj.toString()).build();
    }

    /**
     * PUT method for updating or creating an instance of AuthenResource
     *
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.TEXT_PLAIN)
    public void putString(String content) {
    }

    

}
