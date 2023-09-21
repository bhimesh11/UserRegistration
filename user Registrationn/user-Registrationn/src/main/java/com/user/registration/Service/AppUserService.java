package com.user.registration.Service;

import com.user.registration.appUser.AppUser;
import com.user.registration.reg.token.confirmationToken;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.user.registration.reg.token.confirmationTokenService;


import java.time.LocalDateTime;
import java.util.UUID;

@Service
@AllArgsConstructor
public class AppUserService implements UserDetailsService
{
    private final  AppUserRepository appUserRepository;
    private final confirmationTokenService confirmationTokenService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;



    private final static String USER_NOT_FOUND_MSG = "User with email %s not found";

    @Override
    public UserDetails loadUserByUsername(String email)
            throws UsernameNotFoundException {
        return appUserRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException(String.format(USER_NOT_FOUND_MSG,email)));
    }

    public String signUpUser(AppUser appUser)
    {
      boolean UserExists =  appUserRepository.findByEmail(appUser.getEmail()).isPresent();

      if(UserExists)
      {
          throw new IllegalStateException("Email already Taken");
      }
      String encodedPassword = bCryptPasswordEncoder
              .encode(appUser.getPassword());

      appUser.setPassword(encodedPassword);
      appUserRepository.save(appUser);

      // TODO: send confirmation token

        String token = UUID.randomUUID().toString();


        confirmationToken confirmationToken = new confirmationToken(
                token,
        LocalDateTime.now(),
        LocalDateTime.now().plusMinutes(20),
        appUser);

        confirmationTokenService.saveConfirmationToken(confirmationToken);

        //TODO SEND EMAIL

        return token;
    }
    public int enableAppUser(String email)
    {
        return appUserRepository.enableAppUser(email);
    }
}
