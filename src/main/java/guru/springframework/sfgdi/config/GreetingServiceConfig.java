package guru.springframework.sfgdi.config;

import com.springframework.pets.services.PetService;
import com.springframework.pets.services.PetServiceFactory;
import guru.springframework.sfgdi.repositories.EnglishGreetingRepository;
import guru.springframework.sfgdi.repositories.EnglishGreetingRepositoryImpl;
import guru.springframework.sfgdi.services.*;
import org.springframework.context.annotation.*;

@Configuration
@ImportResource("classpath:sfgdi-config.xml")
@ComponentScan(basePackages = {"guru.springframework.sfgdi", "com.springframework.pets"})
public class GreetingServiceConfig {

    @Bean
    PetServiceFactory petServiceFactory() {
        return new PetServiceFactory();
    }

    @Bean
    @Profile("dog")
    PetService dogPetService(PetServiceFactory petServiceFactory) {
        return petServiceFactory.getPetService("dog");
    }

    @Bean
    @Profile({"cat", "default"})
    PetService catPetService(PetServiceFactory petServiceFactory) {
        return petServiceFactory.getPetService("cat");
    }

    @Bean("i18nService")
    @Profile({"ES", "default"})
    I18nSpanishGreetingService i18nSpanishService() {
        return new I18nSpanishGreetingService();
    }

    @Bean("i18nService")
    @Profile("EN")
    I18nEnglishGreetingService i18nEnglishService(EnglishGreetingRepository englishGreetingRepository) {
        return new I18nEnglishGreetingService(englishGreetingRepository);
    }

    @Bean
    EnglishGreetingRepository englishGreetingRepository() {
        return new EnglishGreetingRepositoryImpl();
    }

    @Bean
    @Primary
    PrimaryGreetingService primaryGreetingService() {
        return new PrimaryGreetingService();
    }

    @Bean
    PropertyInjectedGreetingService propertyInjectedGreetingService () {
        return new PropertyInjectedGreetingService();
    }

    @Bean
    SetterInjectedGreetingService setterInjectedGreetingService() {
        return new SetterInjectedGreetingService();
    }

}
