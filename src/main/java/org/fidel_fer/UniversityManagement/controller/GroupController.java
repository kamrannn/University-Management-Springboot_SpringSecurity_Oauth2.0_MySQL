package org.fidel_fer.UniversityManagement.controller;

import org.fidel_fer.UniversityManagement.model.Group;
import org.fidel_fer.UniversityManagement.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/groups")
public class GroupController {
    private final GroupService groupService;

    @Autowired
    public GroupController(GroupService groupService) {
        this.groupService = groupService;
    }

    @GetMapping("/list")
    public ResponseEntity<Object> listOfGroups(@RequestHeader(name = "Authorization") String token) {
        return groupService.getListOfGroups();
    }

    @PostMapping("/add")
    public ResponseEntity<Object> addGroup(@RequestHeader(name = "Authorization") String token, @RequestBody Group group) {
        return groupService.createGroup(group);
    }

    @PutMapping("/update")
    public ResponseEntity<Object> updateGroup(@RequestHeader(name = "Authorization") String token, @RequestBody Group group) {
        return groupService.updateGroup(group);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Object> deleteGroup(@RequestHeader(name = "Authorization") String token, @RequestParam(name = "id") Long groupId) {
        return groupService.deleteGroupById(groupId);
    }

    @GetMapping("/getById")
    public ResponseEntity<Object> getGroupById(@RequestHeader(name = "Authorization") String token, @RequestParam(name = "id") Long groupId) {
        return groupService.getGroupById(groupId);
    }
}
