package com.newsportal.services.implementation;

import com.newsportal.models.GroupInvitation;
import com.newsportal.models.GroupUser;
import com.newsportal.models.User;
import com.newsportal.models.enums.Gender;
import com.newsportal.models.enums.InvitationState;
import com.newsportal.models.enums.Role;
import com.newsportal.repositories.GroupInvitationRepository;
import com.newsportal.repositories.UserRepository;
import com.newsportal.services.GroupService;
import com.newsportal.services.GroupUserService;
import com.newsportal.services.UserService;
import com.newsportal.viewmodels.UsersSearchItem;
import com.newsportal.viewmodels.enums.UsersSearchItemState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private GroupInvitationRepository groupInvitationRepository;

    @Autowired
    private GroupUserService groupUserService;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public User findById(long id) {
        return userRepository.findById(id).get();
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public List<User> findByUsernameContaining(String searchTerm) {
        return userRepository.findByUsernameContaining(searchTerm);
    }

    @Override
    public List<UsersSearchItem> searchUsers(String searchTerm, long groupId, String currentUserUsername) {
        List<User> users = findByUsernameContaining(searchTerm);
        List<GroupUser> groupUsers = groupUserService.findByGroupId(groupId);
        List<GroupInvitation> groupInvitations =
                groupInvitationRepository
                        .findAll()
                        .stream()
                        .filter(invitation -> invitation.getGroup().getId().equals(groupId)).collect(Collectors.toList());

        List<UsersSearchItem> result = new ArrayList<>();

        for (User user : users) {
            UsersSearchItem item = new UsersSearchItem();
            item.setUser(user);
            if (user.getUsername().equals(currentUserUsername)) {
                item.setState(UsersSearchItemState.YOU);
            } else if (groupUsers.stream().anyMatch(groupUser -> groupUser.getUser().getUsername().equals(user.getUsername()))) {
                item.setState(UsersSearchItemState.MEMBER);
            } else {
                Optional<GroupInvitation> invitationOptional =
                        groupInvitations
                                .stream()
                                .filter(inv -> inv.getState() == InvitationState.NEW)
                                .filter(inv -> inv.getUser().getUsername().equals(user.getUsername()))
                                .findFirst();
                if (invitationOptional.isPresent()) {
                    item.setState(UsersSearchItemState.INVITED);
                } else {
                    item.setState(UsersSearchItemState.NOTINVITED);
                }
            }
            result.add(item);
        }

        return result;
    }

    @Override
    public User registerNewUserAccount(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        //By default a new user has regular user's rights
        user.setRole(Role.REGULAR);
        return userRepository.save(user);
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }

    @Override
    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }


}
