package br.com.lambdateam.myrh.controllers;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import br.com.lambdateam.myrh.models.RecruiterModel;
import br.com.lambdateam.myrh.dtos.RecruiterDto;
import br.com.lambdateam.myrh.repositories.RecruiterRepository;
import br.com.lambdateam.myrh.services.RecruiterService;
import br.com.lambdateam.myrh.exceptions.NotFoundException;
import br.com.lambdateam.myrh.exceptions.BadRequestException;

@AllArgsConstructor
@RestController
@RequestMapping(value = "/recruiters")
public class RecruiterController {
	
    private final RecruiterService service;
    
    private final ModelMapper mapper;
    
//    public RecruiterController(RecruiterService service, ModelMapper mapper) {
//        this.service = service;
//        this.mapper = mapper;
//    }
    
    private static final Logger LOGGER = LoggerFactory.getLogger(RecruiterController.class);
    
    private RecruiterDto convertToDto(RecruiterModel model) {
        return mapper.map(model, RecruiterDto.class);
    }
    
    private RecruiterModel convertToModel(RecruiterDto dto) {
        return mapper.map(dto, RecruiterModel.class);
    }
    
    @GetMapping
    public List<RecruiterDto> getRecruiter(@PageableDefault(size = Integer.MAX_VALUE) Pageable pageable) throws NotFoundException {
        try{
            int toSkip = pageable.getPageSize() *
                    pageable.getPageNumber();

            LOGGER.info("SL4J: Getting recruiters list - /recruiters");

            var recruitersList = StreamSupport
                    .stream(service.findAllRecruiters().spliterator(), false)
                    .skip(toSkip).limit(pageable.getPageSize())
                    .collect(Collectors.toList());

            return recruitersList
                    .stream()
                    .map(this::convertToDto)
                    .collect(Collectors.toList());
        } catch (NotFoundException ex) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "No recruiters found."
            );
        }
    }

    @GetMapping(value = "/{id}")
    public RecruiterDto getRecruiterById(@PathVariable("id") Long id) throws NotFoundException {
        try{
            LOGGER.info("SL4J: Getting recruiter by id - /recruiters/{id}");

            return convertToDto(service.findRecruiterById(id));
        } catch (NotFoundException ex) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Component not found."
            );
        }
    }

    @GetMapping(value = "/dynamicSearchByCompany")
    public ResponseEntity<List<RecruiterDto>> dynamicSearchByCompany(@RequestParam String company) throws NotFoundException {
        try{
            var recruitersList = service.dynamycSearchByCompany(company);

            LOGGER.info("SL4J: Dynamic search by company - /recruiters/dynamicSearchByCompany");

            return new ResponseEntity(recruitersList
                    .stream()
                    .map(this::convertToDto)
                    .collect(Collectors.toList()), HttpStatus.OK);
        } catch (NotFoundException ex) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "No components found."
            );
        }
    }

    @PostMapping
    public ResponseEntity<RecruiterDto> postRecruiter(@Valid @RequestBody RecruiterDto recruiterDto) {
        try{
            var model = convertToModel(recruiterDto);
            var recruiter = service.createRecruiter(model);

            LOGGER.info("SL4J: Post recruiter - /recruiters");

            return new ResponseEntity(convertToDto(recruiter), HttpStatus.CREATED);
        } catch (Exception ex) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Error in conversion to DTO."
            );
        }
    }

    @PutMapping(value = "/{id}")
    public void putRecruiter(@PathVariable("id") Long id, @Valid @RequestBody RecruiterDto recruiterDto) throws NotFoundException {
        try{
            if(!id.equals(recruiterDto.getId())) throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "id does not match."
            );

            LOGGER.info("SL4J: Updating recruiter - /recruiters/{id}");

            var recruiterModel = convertToModel(recruiterDto);
            service.updateRecruiter(id, recruiterModel);
        } catch (NotFoundException e) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Component not found."
            );
        }
    }

    @DeleteMapping(value = "/{id}")
    public HttpStatus deleteRecruiterById(@PathVariable("id") Long id) throws NotFoundException {
        try{
            LOGGER.info("SL4J: Deleting recruiter - /recruiter/{id}");

            service.deleteRecruiterById(id);
            return HttpStatus.NO_CONTENT;
        } catch (NotFoundException e) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Component not found."
            );
        }
    }
}