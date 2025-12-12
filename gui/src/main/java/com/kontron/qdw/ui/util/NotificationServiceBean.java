package com.kontron.qdw.ui.util;

import java.util.List;
import java.util.function.Predicate;

import com.kontron.qdw.boundary.base.NotificationBoundaryService;
import com.kontron.qdw.dto.base.NotificationUserRelDTO;
import com.kontron.qdw.ui.UserSession;

import jakarta.ejb.EJB;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

/**
 * Gibt die aktuellen {@link com.kontron.qdw.domain.base.Notification Notification}s als Feed zurück.
 * <p/>
 * 
 * 2025 — © Kontron AG
 * @author Raymund Achner, achner.com
 */
@Named("notificationServiceBean")
@RequestScoped
public class NotificationServiceBean {

    @EJB
    private NotificationBoundaryService service;
    @Inject
    private UserSession userSession;

    public boolean hasNotifications() {
        return !getNotifications().isEmpty();
    }

    public boolean hasNewNotifications() {
        return getNotifications().stream()
                .anyMatch(Predicate.not(NotificationUserRelDTO::isRead));
    }

    private List<NotificationUserRelDTO> getNotifications() {
        return service.getActiveNotifications(userSession.getPrincipal().getId(), false);
    }

}
