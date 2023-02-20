package guru.springframework.sfgdi;

import com.springframework.pets.controllers.PetController;
import guru.springframework.sfgdi.controllers.*;
import guru.springframework.sfgdi.services.PrototypeBean;
import guru.springframework.sfgdi.services.SingletonBean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"guru.springframework.sfgdi", "com.springframework.pets"})
public class SfgDiApplication {

	public static void main(String[] args) {
		ApplicationContext ctx =  SpringApplication.run(SfgDiApplication.class, args);

		// Another way to get correct class from Spring Context is
		// to pass Class object to getBean method instead of downcasting result
		PetController petController = ctx.getBean("petController", PetController.class);
		System.out.println("----- The Best Pet is -----");
		System.out.println(petController.whichPetIsTheBest());

		System.out.println("------ I18n ---------------");
		I18nController i18nController = (I18nController) ctx.getBean("i18nController");
		System.out.println(i18nController.sayHello());

		System.out.println("------ Primary ------------");
		MyController myController = (MyController) ctx.getBean("myController");
		System.out.println(myController.sayHello());

		System.out.println("------ Property -----------");
		PropertyInjectedController propertyInjectedController = (PropertyInjectedController) ctx.getBean("propertyInjectedController");
		System.out.println(propertyInjectedController.getGreeting());

		System.out.println("------ Setter -------------");
		SetterInjectedController setterInjectedController = (SetterInjectedController) ctx.getBean("setterInjectedController");
		System.out.println(setterInjectedController.getGreeting());

		System.out.println("------ Constructor --------");
		ConstructorInjectedController constructorInjectedController = (ConstructorInjectedController) ctx.getBean("constructorInjectedController");
		System.out.println(constructorInjectedController.getGreeting());

		System.out.println("------ Bean Scopes --------");
		SingletonBean singletonBean1 = ctx.getBean(SingletonBean.class);
		System.out.println(singletonBean1.getScope());
		SingletonBean singletonBean2 = ctx.getBean(SingletonBean.class);
		System.out.println(singletonBean2.getScope());

		PrototypeBean prototypeBean1 = ctx.getBean(PrototypeBean.class);
		System.out.println(prototypeBean1.getScope());
		PrototypeBean prototypeBean2 = ctx.getBean(PrototypeBean.class);
		System.out.println(prototypeBean2.getScope());
	}

}
