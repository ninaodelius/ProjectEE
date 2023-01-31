package se.nina.projectee.flash.auth;

import java.util.ArrayList;
import java.util.List;

import static se.nina.projectee.flash.auth.FlashPermissions.*;

public enum FlashRoles {
    FLASH(List.of(FLASH_READ)),
    ADMIN(List.of(ADMIN_READ, ADMIN_WRITE));
    private final List<FlashPermissions> permissions;
    FlashRoles(List<FlashPermissions> permissions) {
        this.permissions = permissions;
    }
    public List<FlashPermissions> getPermissions() {
        return permissions;
    }

    //create one role with both role and permissions
    public List<String> getGrantedAuthorities() {

        //for loop with streams
        List<String> permissionsList = new ArrayList<>(getPermissions().stream().map(
                FlashPermissions::getUserPermission
        ).toList());

        //when finished: add role (ex. ROLE_ADMIN)
        permissionsList.add(("ROLE_" + this.name()));
        return permissionsList;
    }
}
