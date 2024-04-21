package mk.ukim.finki.courses.model.enumerations;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    ROLE_STUDENT,
    ROLE_LECTURER;

    @Override
    public String getAuthority() {
        return name();
    }
}
