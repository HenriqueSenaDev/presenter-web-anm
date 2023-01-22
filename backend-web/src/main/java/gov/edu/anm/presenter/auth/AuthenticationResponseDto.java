package gov.edu.anm.presenter.auth;

import gov.edu.anm.presenter.api.appuser.AppUser;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationResponseDto {

    private String access_token;
    private String refresh_token;
    private AppUser profile;

}
