package org.project.msgame.domain.service;

import org.project.msgame.domain.states.ObjectiveState;
import org.springframework.stereotype.Service;

@Service
public class ObjectiveDomainService {

    public ObjectiveState handleStateTransition(ObjectiveState oldState, ObjectiveState newState) {
        switch (oldState) {
            case CREATED:
                if (newState == ObjectiveState.ON_COURSE)
                    return newState;
                throw new IllegalArgumentException("CREATED only can change to ON_COURSE");
            case ON_COURSE:
                if (newState == ObjectiveState.END || newState == ObjectiveState.CANCELLED)
                    return newState;
                throw new IllegalArgumentException("ON_COURSE only can change to END or CANCELLED");
            case END:
                throw new IllegalArgumentException("END is final state");
            case CANCELLED:
                throw new IllegalArgumentException("CANCELLED is final state");
            default:
                throw new IllegalArgumentException("Unknown state: " + oldState);
        }
    }
}