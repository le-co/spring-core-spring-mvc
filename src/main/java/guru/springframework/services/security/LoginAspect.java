package guru.springframework.services.security;

import org.aspectj.lang.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoginAspect {

    private PublishService failurePublishService;

    private PublishService successPublishService;

    @Autowired
    @Qualifier("loginFailureEventPublisher")
    public void setFailurePublishService(PublishService failurePublishService) {
        this.failurePublishService = failurePublishService;
    }

    @Autowired
    @Qualifier("authorizeEventPublisher")
    public void setSuccessPublishService(PublishService successPublishService) {
        this.successPublishService = successPublishService;
    }

    @Pointcut("execution(* org.springframework.security.authentication.AuthenticationProvider.authenticate(..))")
    public void doAuthenticate() {

    }

    @Before("guru.springframework.services.security.LoginAspect.doAuthenticate() && args(authentication)")
    public void logBefore(Authentication authentication) {

        System.out.println(authentication.getClass().toString());
        System.out.println("This is before the Authenticate Method: authentication: " + authentication.isAuthenticated());
    }

    @AfterReturning(value = "guru.springframework.services.security.LoginAspect.doAuthenticate()", returning = "authentication")
    public void logAfterAuthenticate(Authentication authentication) {

        if (authentication.isAuthenticated()) {
            this.successPublishService.publish(new AuthorizeEvent(authentication));
        }
    }

    @AfterThrowing("guru.springframework.services.security.LoginAspect.doAuthenticate() && args(authentication)")
    public void logAuthenticationException(Authentication authentication) {

        this.failurePublishService.publish(new LoginFailureEvent(authentication));

        String userDetails = (String) authentication.getPrincipal();
        System.out.println("Login failed for user: " + userDetails);
    }


}
