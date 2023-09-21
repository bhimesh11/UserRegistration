package com.user.registration.reg.token;


import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
@AllArgsConstructor
public class confirmationTokenService
{
private final confirmationTokenRepository confirmationTokenRepository;
public void saveConfirmationToken(confirmationToken token)
{
    confirmationTokenRepository.save(token);
}
public Optional<confirmationToken> getToken(String token)
{
    return confirmationTokenRepository.findByToken(token);
}
public int setConfirmedAt(String token)
{
    return confirmationTokenRepository.updateConfirmedAt(token, LocalDateTime.now());

}
}
