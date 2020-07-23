package app.platform.userservice.events.consumers;

import app.platform.userservice.events.SuccessfulRegistrationEvent;
import app.platform.userservice.models.User;
import app.platform.userservice.models.VerificationToken;
import app.platform.userservice.repostiories.VerificationTokenRepository;
import lombok.AllArgsConstructor;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class SuccessfulRegistrationEventConsumer implements ApplicationListener <SuccessfulRegistrationEvent>{
    private VerificationTokenRepository verificationTokenRepository;
    @Override
    public void onApplicationEvent(SuccessfulRegistrationEvent successfulRegistrationEvent) {
        User registeredUser=successfulRegistrationEvent.getRegisteredUser();
        VerificationToken token=new VerificationToken(registeredUser);
        verificationTokenRepository.save(token);
        //TODO: Send Email for verification
        //TODO:Allocate resources
    }
}
