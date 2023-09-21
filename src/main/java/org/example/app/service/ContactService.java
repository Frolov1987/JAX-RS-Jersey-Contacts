package org.example.app.service;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.example.app.entity.Contact;

import java.net.URI;
import java.util.*;

@Path("/api/v1.0/contacts")
@Produces({MediaType.APPLICATION_JSON})
public class ContactService {

    private static final List<Contact> contactss;

    static {
        contactss = new ArrayList<>();
        contactss.add(new Contact(1L, "Alice", "555 555-5555"));
        contactss.add(new Contact(2L, "Bob", "555 555-5555"));
        contactss.add(new Contact(3L, "Lucy", "555 555-5555"));
        contactss.add(new Contact(4L, "Tom", "555 555-5555"));
    }

    @GET
    public List<Contact> getContacts() {
        return contactss;
    }

    @GET
    @Path("{id: [0-9]+}")
    public Contact getContact(@PathParam("id") Long id) {
        Contact contact = new Contact(id, null, null);

        int index = Collections.binarySearch(contactss, contact, Comparator.comparing(Contact::getId));

        if (index >= 0)
            return contactss.get(index);
        else
            throw new WebApplicationException(Response.Status.NOT_FOUND);
    }

    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    public Response createContact(Contact contact) {
        if (Objects.isNull(contact.getId()))
            throw new WebApplicationException(Response.Status.BAD_REQUEST);

        int index = Collections.binarySearch(contactss, contact, Comparator.comparing(Contact::getId));

        if (index < 0) {
            contactss.add(contact);
            return Response
                    .status(Response.Status.CREATED)
                    .location(URI.create(String.format("/api/v1.0/contacts/%s", contact.getId())))
                    .build();
        } else
            throw new WebApplicationException(Response.Status.CONFLICT);
    }


    @PUT
    @Path("{id: [0-9]+}")
    @Consumes({MediaType.APPLICATION_JSON})
    public Response updateContact(@PathParam("id") Long id, Contact contact) {
        contact.setId(id);
        int index = Collections.binarySearch(contactss, contact, Comparator.comparing(Contact::getId));

        if (index >= 0) {
            Contact updatedContact = contactss.get(index);
//            updatedUser.setName(user.getName());
            updatedContact.setPhone(contact.getPhone());
            contactss.set(index, updatedContact);
            return Response
                    .status(Response.Status.NO_CONTENT)
                    .build();
        } else
            throw new WebApplicationException(Response.Status.NOT_FOUND);
    }

    @DELETE
    @Path("{id: [0-9]+}")
    public Response deleteContact(@PathParam("id") Long id) {
        Contact contact = new Contact(id, null, null);
        int index = Collections.binarySearch(contactss, contact, Comparator.comparing(Contact::getId));

        if (index >= 0) {
            contactss.remove(index);
            return Response
                    .status(Response.Status.NO_CONTENT)
                    .build();
        } else
            throw new WebApplicationException(Response.Status.NOT_FOUND);
    }
}
