package de.pius.cookshare.user;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Permission {

    /*
     * .ADMIN_READ.name() => ADMIN_READ
     * ADMIN_READ.getPermission() => admin:read
     */
    ADMIN_READ("admin:read"), // singemäß: new Permission("admin:read");
    ADMIN_UPDATE("admin:update"),
    ADMIN_CREATE("admin:create"),
    ADMIN_DELETE("admin:delete");

    @Getter
    private final String permission;
}