package se.nina.projectee.flash.auth;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

import static se.nina.projectee.flash.auth.FlashPermissions.*;

public enum FlashRoles {
    FLASH(Set.of(FLASH_READ)),
    ADMIN(Set.of(ADMIN_READ, ADMIN_WRITE));

    private final Set<FlashPermissions> permissions;

    FlashRoles(Set<FlashPermissions> permissions) {
        this.permissions = permissions;
    }

    public Set<FlashPermissions> getPermissions() {
        return permissions;
    }

    //create one role with both role and permissions
    public Set<SimpleGrantedAuthority> getGrantedAuthorities() {

        //for loop with streams
        Set<SimpleGrantedAuthority> permissionsList = getPermissions().stream().map(
                index -> new SimpleGrantedAuthority(index.getUserPermission())
        ).collect(Collectors.toSet());

        //when finished: add role (ex. ROLE_ADMIN)
        permissionsList.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
        return permissionsList;
    }
}
