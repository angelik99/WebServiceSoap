package com.cidenet.endpoint;

import com.cidenet.entities.User;
import com.cidenet.model.*;
import com.cidenet.service.impl.UsersServiceImpl;

import ch.qos.logback.core.util.SystemInfo;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
public class UserEndPoint {
    @Autowired
    private UsersServiceImpl usersServiceImpl;

    @PayloadRoot(namespace = "http://Angelica/webservice/apiusers", localPart = "CreateUserRequest")
    @ResponsePayload
    public UserCreateResponse processCourseDetailsRequest(@RequestPayload CreateUserRequest request) {
        UserCreateResponse response = new UserCreateResponse();
        User user = new User();
        String name= request.getName();
        String typedoc =request.getTypeDocument().toUpperCase();
        int documentNum = request.getNumDocument();
        int doc =  Integer.toString(documentNum).length();

        try {

            if(name.isEmpty() || typedoc.isEmpty() || doc==0){
                response.setCode(-400);
                response.setMessage("Error, ninguno de los datos puede ser null");
            } else if (doc !=10) {
                response.setCode(-401);
                response.setMessage("Error, el documento debe tener 10 caracteres y ser númerico");
            } else if (!typedoc.equals("CC") && !typedoc.equals("TI") && !typedoc.equals("RC")) {
                response.setCode(-402);
                response.setMessage("Error, el tipo documneto debe ser CC, TI o RC");
        }else {
                user.setName(request.getName());
                user.setTypeDocument(request.getTypeDocument());
                user.setNumDocument(request.getNumDocument());
                usersServiceImpl.AddUser(user);
                response.setCode(00);
                response.setMessage("Transaccion Exitosa");
            }
        }catch (Exception e){
            response.setCode(-500);
            response.setMessage("Se genero un error y no se pudo crear el usuario");
        }

        return response;
    }

    @PayloadRoot(namespace = "http://Angelica/webservice/apiusers", localPart = "ConsultUserRequest")
    @ResponsePayload
    public UserConsultResponse processCourseDetailsRequest(@RequestPayload ConsultUserRequest request) {
        UserConsultResponse response = new UserConsultResponse();
        User user = new User();
        try {
            user = usersServiceImpl.getUserByTypeDocumentAndNumDocument(request.getTypeDocument(), request.getNumDocument());
            if(user == null){
                response.setCode(-400);
                response.setMessage("User Not Found");
                response.setName("");
            }else{
                response.setCode(00);
                response.setMessage("User Found");
                response.setName(user.getName());
            }
        }catch (Exception e){
            response.setCode(-500);
            response.setMessage("Se genero un error");
        }
        return response;
    }

    @PayloadRoot(namespace = "http://Angelica/webservice/apiusers", localPart = "deleteUserRequest")
    @ResponsePayload
    public DeleteUserResponse processCourseDetailsRequest(@RequestPayload DeleteUserRequest request) {
        DeleteUserResponse response = new DeleteUserResponse();
        User user = new User();
        usersServiceImpl.deleteUser(request.getNumDocument());
            response.setCode(00);
            response.setMessage("User Delete");

        return response;
    }

}
