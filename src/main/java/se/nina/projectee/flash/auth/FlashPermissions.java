package se.nina.projectee.flash.auth;

public enum FlashPermissions {
    FLASH_READ("flash:read" ),
    ADMIN_READ("admin:read" ),
    ADMIN_WRITE ("admin:write" );
    private final String permission;
    FlashPermissions (String permission) {
        this.permission = permission;
    }
    public String getUserPermission () {
        return permission;
    }

}
