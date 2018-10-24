package guru.springframework.services.security;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationEventPublisherAware;

public interface PublishService extends ApplicationEventPublisherAware {

    void publish(ApplicationEvent event);
}
