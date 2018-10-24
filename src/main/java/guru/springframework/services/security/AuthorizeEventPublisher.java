package guru.springframework.services.security;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component("authorizeEventPublisher")
public class AuthorizeEventPublisher implements PublishService {

    private ApplicationEventPublisher applicationEventPublisher;

    @Override
    public void publish(ApplicationEvent event) {
        this.applicationEventPublisher.publishEvent(event);
    }

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }
}
