package com.petshopadmin.adapter.input.http;

import com.petshopadmin.application.domain.ServiceDomain;
import com.petshopadmin.application.port.input.ServiceUserCase;
import com.petshopadmin.exception.InternalServerErrorException;
import com.petshopadmin.exception.NotFoundException;
import jakarta.websocket.server.PathParam;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping(path = "/service")
public class ServiceController {

    private final ServiceUserCase serviceUserCase;

    public ServiceController (ServiceUserCase serviceUserCase) {
        this.serviceUserCase = serviceUserCase;
    }

    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseHTTP getByID(@PathVariable(name = "id", required = true) Long serviceID,
                                @RequestParam(value = "contract") Long contractID) throws NotFoundException, InternalServerErrorException {

        ServiceDomain serviceDomain = serviceUserCase.getByID(contractID, serviceID);
        return new ResponseHTTP("success to get service by id", new ServiceResponseHTTP(serviceDomain), null, LocalDateTime.now());

    }

    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    @GetMapping(path = {"", "/"}, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseHTTP getActives(@RequestParam(value = "active") boolean active,
                                @RequestParam(value = "contract") Long contractID) throws NotFoundException, InternalServerErrorException {

        List<ServiceDomain> list = serviceUserCase.getByActive(contractID, active);
        List<ServiceResponseHTTP> result = new ArrayList<>();
        list.stream().forEach(serviceDomain -> result.add(new ServiceResponseHTTP(serviceDomain)));

        return new ResponseHTTP("success to get services", null, result, LocalDateTime.now());

    }

}
