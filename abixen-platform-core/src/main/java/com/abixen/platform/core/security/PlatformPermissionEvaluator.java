/**
 * Copyright (c) 2010-present Abixen Systems. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.abixen.platform.core.security;

import com.abixen.platform.core.model.SecurableModel;
import com.abixen.platform.core.model.enumtype.PermissionName;
import com.abixen.platform.core.model.impl.User;
import com.abixen.platform.core.service.SecurityService;
import com.abixen.platform.core.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.io.Serializable;

import static org.springframework.context.annotation.ScopedProxyMode.TARGET_CLASS;

@Slf4j
@Component
@Lazy
@Scope(proxyMode = TARGET_CLASS)
public class PlatformPermissionEvaluator implements PermissionEvaluator {

    private SecurityService securityService;
    private UserService userService;

    @Autowired
    public PlatformPermissionEvaluator(SecurityService securityService, UserService userService) {
        this.securityService = securityService;
        this.userService = userService;
    }

    @Override
    public boolean hasPermission(Authentication authentication, Object targetDomainObject, Object permission) {
        log.debug("hasPermission() - authentication: " + authentication + ", targetDomainObject: " + targetDomainObject + ", permission: " + permission);
        log.debug("targetDomainObject class: " + targetDomainObject.getClass());
        //TODO
        User user = userService.findUser(authentication.getName());
        return securityService.hasUserPermissionToObject(user, PermissionName.valueOf((String) permission), (SecurableModel) targetDomainObject);
    }

    @Override
    public boolean hasPermission(Authentication authentication, Serializable targetId,
                                 String targetType, Object permission) {
        log.debug("hasPermission() - authentication: " + authentication + ", targetId: " + targetId + ", targetType: " + targetType + ", permission: " + permission);

        User user = userService.findUser(authentication.getName());

        if (targetId == null) {
            return securityService.hasUserPermissionToClass(user, PermissionName.valueOf((String) permission), targetType);
        }
        return securityService.hasUserPermissionToObject(user, PermissionName.valueOf((String) permission), (Long) targetId, targetType);
    }

}