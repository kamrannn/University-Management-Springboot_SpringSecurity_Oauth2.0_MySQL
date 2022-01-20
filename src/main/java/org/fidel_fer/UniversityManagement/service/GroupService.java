package org.fidel_fer.UniversityManagement.service;

import org.fidel_fer.UniversityManagement.model.Group;
import org.fidel_fer.UniversityManagement.repository.GroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GroupService {
    private final GroupRepository groupRepository;

    @Autowired
    public GroupService(GroupRepository groupRepository) {
        this.groupRepository = groupRepository;
    }

    public ResponseEntity<Object> createGroup(Group group) {
        try {
            groupRepository.save(group);
            return new ResponseEntity<>("Group: " + group.getName() + " has been added in the system", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Object> getListOfGroups() {
        try {
            List<Group> groupList = groupRepository.findAll();
            if (groupList.isEmpty()) {
                return new ResponseEntity<>("There is no group in the system", HttpStatus.OK);
            } else {
                return new ResponseEntity<>(groupList, HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Object> updateGroup(Group group) {
        try {
            Optional<Group> optionalGroup = groupRepository.findById(group.getId());
            if (optionalGroup.isPresent()) {
                groupRepository.save(group);
                return new ResponseEntity<>("Group: " + group.getName() + " has been updated in the system", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("There is no group against this ID in the system", HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Object> deleteGroupById(Long groupId) {
        try {
            Optional<Group> group = groupRepository.findById(groupId);
            if (group.isPresent()) {
                groupRepository.delete(group.get());
                return new ResponseEntity<>("Group has been deleted from the system", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("There is no group against this ID in the system", HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Object> getGroupById(Long groupId) {
        try {
            Optional<Group> group = groupRepository.findById(groupId);
            if (group.isPresent()) {
                return new ResponseEntity<>(group, HttpStatus.OK);
            } else {
                return new ResponseEntity<>("There is no group against this ID in the system", HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
