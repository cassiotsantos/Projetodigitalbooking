package br.com.digitalbooking.digitalbooking.domain.service;

import br.com.digitalbooking.digitalbooking.domain.entity.SingIn;
import br.com.digitalbooking.digitalbooking.domain.entity.SingUp;

public interface AuthenticationUserService {

    String signIn(SingIn request);

    String signUp(SingUp request);

}
