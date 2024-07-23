package com.petshopadmin.adapter.input.http;

import com.petshopadmin.application.domain.ContractDomain;
import com.petshopadmin.application.domain.ServiceDomain;
import com.petshopadmin.application.port.input.ContractUserCase;
import com.petshopadmin.application.port.input.ServiceUserCase;
import com.petshopadmin.exception.InternalServerErrorException;
import com.petshopadmin.exception.NotFoundException;
import com.petshopadmin.exception.ValidationException;
import com.petshopadmin.utils.converter.ContractConverterMapper;
import com.petshopadmin.utils.converter.ServiceConverterMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping(path = "/service")
public class ServiceController {

    private final ServiceUserCase serviceUserCase;
    private final ServiceConverterMapper serviceConverterMapper;
    private final ContractUserCase contractUserCase;

    public ServiceController (ServiceUserCase serviceUserCase, ServiceConverterMapper serviceConverterMapper,
                              ContractUserCase contractUserCase) {
        this.serviceUserCase = serviceUserCase;
        this.serviceConverterMapper = serviceConverterMapper;
        this.contractUserCase = contractUserCase;
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

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseHTTP create(@RequestBody ServiceRequestHTTP serviceRequestHTTP) throws NotFoundException, InternalServerErrorException {
        try {
            ServiceDomain serviceDomain = serviceConverterMapper.toServiceDomain(serviceRequestHTTP);

            ContractDomain contractDomain = contractUserCase.getById(serviceRequestHTTP.contractid());
            serviceDomain.setContract(contractDomain);

            ServiceDomain created = serviceUserCase.create(serviceDomain);
            return new ResponseHTTP("sucess to create a new services", new ServiceResponseHTTP(created), null, LocalDateTime.now());
        } catch (ValidationException e) {
            return new ResponseHTTP("Errors found", null, Arrays.asList(e.getMessages().toArray()), LocalDateTime.now());
        }
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping(path = "/validate-create", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity validate(@RequestBody ServiceRequestHTTP serviceRequestHTTP) throws NotFoundException, InternalServerErrorException {
        try {
            ServiceDomain serviceDomain = serviceConverterMapper.toServiceDomain(serviceRequestHTTP);

            ContractDomain contractDomain = contractUserCase.getById(serviceRequestHTTP.contractid());
            serviceDomain.setContract(contractDomain);
            serviceUserCase.validate(serviceDomain);

            ResponseHTTP responseHTTP = new ResponseHTTP("Validation successful", null, null, LocalDateTime.now());
            return new ResponseEntity(responseHTTP, HttpStatus.OK);
        } catch (ValidationException e) {
            ResponseHTTP responseHTTP = new ResponseHTTP("Validation Errors", null, Arrays.asList(e.getMessages().toArray()), LocalDateTime.now());
            return new ResponseEntity(responseHTTP, HttpStatus.BAD_REQUEST);
        }

    }

}
