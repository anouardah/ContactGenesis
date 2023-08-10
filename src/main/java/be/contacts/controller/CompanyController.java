package be.contacts.controller;

import be.contacts.domain.Company;
import be.contacts.dto.CompanyDto;
import be.contacts.service.CompanyService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Tag(name = "Company", description = "The company controller")
@RestController
@RequestMapping("/company")
public class CompanyController {

    @Autowired
    private CompanyService companyService;

    @Operation(
            summary = "Get all companies",
            description = "Endpoint used to fetch all companies.",
            tags = "GET"
    )
    @GetMapping
    public List<Company> getAll(){
        return companyService.getAll();
    }

    @Operation(
            summary = "Get company by vat number",
            description = "Endpoint used to find a single company by their vat number.",
            tags = "GET"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = Company.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "404", description = "Entity not found exception is thrown if company does not exist.", content = { @Content(schema = @Schema()) })})
    @GetMapping("/{vatNr}")
    public Company getByVatNr(@PathVariable String vatNr){
        return companyService.getByVatNr(vatNr);
    }


    @Operation(
            summary = "Create company",
            description = "Endpoint used to create a single company.",
            tags = "POST"
    )
    @PostMapping
    public Company create(@RequestBody CompanyDto companyDto){
        return companyService.create(companyDto);
    }


    @Operation(
            summary = "Update company",
            description = "Endpoint used to update a single company. Only fields which are not null are updated.",
            tags = "PUT"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = Company.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "404", description = "Entity not found exception is thrown if company does not exist.", content = { @Content(schema = @Schema()) })})
    @PutMapping("/{id}")
    public Company update(@PathVariable long id, @RequestBody CompanyDto companyDto){
        return companyService.update(id, companyDto);
    }

    @Operation(
            summary = "Add contact to company",
            description = "Endpoint used to add a single contact to a company.",
            tags = "PUT"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = Company.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "404", description = "Entity not found exception is thrown if company does not exist.", content = { @Content(schema = @Schema()) })})
    @PostMapping("/{companyId}/add-contact/{contactId}")
    public Company addContact(@PathVariable long companyId, @PathVariable long contactId){
        return companyService.addContact(companyId, contactId);
    }
}
