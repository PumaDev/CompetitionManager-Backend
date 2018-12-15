package com.deathstar.competitionmanager.exception

import com.deathstar.competitionmanager.domain.user.ActivateStatus

class UserNotActiveException extends CompetitionManagerException {

    UserNotActiveException(ActivateStatus activateStatus) {
        super(0)
        switch (activateStatus) {
            case ActivateStatus.BANNED:
                errorCode = 21
                break
            case ActivateStatus.WAITING_APPROVE:
                errorCode = 22
                break
            case ActivateStatus.BLOCKED:
                errorCode = 23
                break
        }
    }
}
