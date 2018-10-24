package guru.springframework.services.security;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component("loginFailureEventPublisher")
public class LoginFailureEventPublisher implements PublishService {

    private ApplicationEventPublisher publisher;

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.publisher = applicationEventPublisher;
    }

    @Override
    public void publish(ApplicationEvent event) {
        this.publisher.publishEvent(event);
    }
}
