package com.user.registration.reg;


import com.user.registration.model.RegistrationRequest;
import com.user.registration.Service.registrationService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "api/v1/registration")
@AllArgsConstructor
public class registrationController {

    private registrationService registerService;

    @PostMapping
    public String register(@RequestBody RegistrationRequest request)
    {
        return registerService.register(request);
    }

    @GetMapping(path = "confirm")
    public String confirm(@RequestParam("token") String token)
    {
        return registerService.confirmToken(token);
    }
}
