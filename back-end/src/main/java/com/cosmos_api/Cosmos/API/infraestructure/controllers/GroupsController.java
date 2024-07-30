package com.cosmos_api.Cosmos.API.infraestructure.controllers;

import com.cosmos_api.Cosmos.API.aplication.dto.dtoGroups;
import com.cosmos_api.Cosmos.API.domain.entities.Groups;
import com.cosmos_api.Cosmos.API.domain.services.interfaces.IGroupsService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/groups")
public class GroupsController {

    @Autowired
    private final IGroupsService groupsService;

    @GetMapping
    public ResponseEntity<List<Groups>> listGroups() {
        List<Groups> list = groupsService.listAlllGroups();
        if (!list.isEmpty()) {
            return new ResponseEntity<>(list, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Groups> getByIdGroups(@PathVariable("id") long id) {
        Groups groups = groupsService.getOneGroups(id);
        if (groups != null) {
            return new ResponseEntity<>(groups, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteGroups(@PathVariable("id") long id) {
        boolean deleted = groupsService.deleteGroups(id);
        if (deleted) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/save")
    public ResponseEntity<Groups> createGroups(@RequestBody dtoGroups dtoGroups) {
        Groups groups = groupsService.saveGroups(dtoGroups);
        return new ResponseEntity<>(groups, HttpStatus.CREATED);
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<Groups> updateGroups(@PathVariable("id") long id, @RequestBody dtoGroups dtoGroups) {
        Groups groups = groupsService.editGroups(id, dtoGroups);
        if (groups != null) {
            return new ResponseEntity<>(groups, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
