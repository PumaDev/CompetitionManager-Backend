package com.deathstar.competitionmanager.service.user

import com.deathstar.competitionmanager.domain.user.ActivateStatus
import com.deathstar.competitionmanager.domain.user.User
import com.deathstar.competitionmanager.domain.user.UserRole
import com.deathstar.competitionmanager.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Isolation
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(isolation = Isolation.READ_COMMITTED)
class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository

    @Override
    User create(User user) {
        user.id = null
        validateUniqueFields(user)
        setDefaultValues(user)
        return userRepository.save(user)
    }

    @Override
    User update(User newUserEntity) {
        User existingUserEntity = getNotNullUser(newUserEntity.id)
        validateUniqueFields(newUserEntity)
        newUserEntity.password = existingUserEntity.password
        return userRepository.save(newUserEntity)
    }

    @Override
    User updatePassword(Integer userId, String newPassword) {
        User user = getNotNullUser(userId)
        user.password = newPassword
        return userRepository.save(user)
    }

    @Override
    User findByLogin(String login) {
        return userRepository.findByLogin(login)
    }

    @Override
    User findById(Integer id) {
        return userRepository.findOne(id)
    }

    @Override
    List<User> findUsersByActivateStatus(ActivateStatus activateStatus) {
        return userRepository.findByActivateStatus(activateStatus)
    }

    @Override
    List<User> getAllUsers() {
        return userRepository.findAll()
    }

    @Override
    User getNotNullUser(Integer id) {
        User existingUserEntity = userRepository.findOne(id)
        if (!existingUserEntity) {
            throw new Exception("User with id ${id} doesn't exist")
        }
        return existingUserEntity
    }

    private validateUniqueFields(User user) {
        User existingUserByLogin = userRepository.findByLogin(user.login)
        if (existingUserByLogin && existingUserByLogin.id != user.id) {
            throw new Exception("User with login ${user.login} exists")
        }
    }

    private setDefaultValues(User user) {
        user.userRole = UserRole.COACH
        user.activateStatus = ActivateStatus.WAITING_APPROVE
    }
}
