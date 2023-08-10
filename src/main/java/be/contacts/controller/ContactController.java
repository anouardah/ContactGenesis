package be.contacts.controller;

import be.contacts.domain.Contact;
import be.contacts.dto.ContactDto;
import be.contacts.service.ContactService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Contact", description = "The contact controller")
@RestController
@RequestMapping("/contact")
public class ContactController {

    @Autowired
    private ContactService contactService;

    @Operation(
            summary = "Get all contacts",
            description = "Endpoint used to fetch all the contacts. Both employees and freelancers.",
            tags = "GET"
    )
    @GetMapping
    public List<Contact> getAll(){
        return contactService.getAll();
    }

    @Operation(
            summary = "Create contact",
            description = "Endpoint used to create a single contact. Will create freelancer if vatNr field is provided, otherwise an employee is created.",
            tags = "POST"
    )
    @PostMapping
    public Contact create(@RequestBody ContactDto contactDto){
        return contactService.create(contactDto);
    }

    @Operation(
            summary = "Update contact",
            description = "Endpoint used to update a single contact. Only fields which are not null are updated.",
            tags = "PUT"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = Contact.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "404", description = "Entity not found exception is thrown if contact does not exist.", content = { @Content(schema = @Schema()) })})
    @PutMapping("/{id}")
    public Contact update(@PathVariable long id, @RequestBody ContactDto contactDto){
        return contactService.update(id, contactDto);
    }

    @Operation(
            summary = "Delete contact",
            description = "Endpoint used to delete a single contact.",
            tags = "DELETE"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "returns a success string"),
            @ApiResponse(responseCode = "404", description = "Entity not found exception is thrown if contact does not exist.", content = { @Content(schema = @Schema()) })})
    @DeleteMapping("/{id}")
    public String delete(@PathVariable long id){
        contactService.delete(id);
        return "success";
    }
}
