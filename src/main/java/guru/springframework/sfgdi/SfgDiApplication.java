package guru.springframework.sfgdi;

import guru.springframework.sfgdi.controllers.ConstructorInjectedController;
import guru.springframework.sfgdi.controllers.I18nController;
import guru.springframework.sfgdi.controllers.MyController;
import guru.springframework.sfgdi.controllers.PetController;
import guru.springframework.sfgdi.controllers.PropertyInjectedController;
import guru.springframework.sfgdi.controllers.SetterInjectedController;
import guru.springframework.sfgdi.datasource.FakeDataSource;
import guru.springframework.sfgdi.services.PrototypeBean;
import guru.springframework.sfgdi.services.SingletonBean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
public class SfgDiApplication {

    public static void main(String[] args) {

        ApplicationContext ctx = SpringApplication.run(SfgDiApplication.class, args);

        PetController petController = (PetController) ctx.getBean("petController", PetController.class);
        System.out.println("---- The best pet is ----");
        System.out.println(petController.whichPetIsTheBest());

        I18nController i18nController = (I18nController) ctx.getBean("i18nController");

        System.out.println(i18nController.sayHello());

        MyController myController = (MyController) ctx.getBean("myController");

        System.out.println("-------Primary bean");
        System.out.println(myController.sayHello());

        System.out.println("--------Property");

        PropertyInjectedController propertyInjectedController = (PropertyInjectedController) ctx.getBean("propertyInjectedController");
        System.out.println(propertyInjectedController.getGreeting());

        System.out.println("--------Setter");

        SetterInjectedController setterInjectedController = (SetterInjectedController) ctx.getBean("setterInjectedController");
        System.out.println(setterInjectedController.getGreeting());

        System.out.println("--------Constructor");

        ConstructorInjectedController constructorInjectedController = (ConstructorInjectedController) ctx.getBean("constructorInjectedController");
        System.out.println(constructorInjectedController.getGreeting());

        System.out.println("--------- Bean scopes ----------");

        SingletonBean singletonBean = ctx.getBean(SingletonBean.class);
        System.out.println(singletonBean.getMyScope());
        SingletonBean singletonBean1 = ctx.getBean(SingletonBean.class);
        System.out.println(singletonBean1.getMyScope());

        PrototypeBean prototypeBean = ctx.getBean(PrototypeBean.class);
        System.out.println(prototypeBean.getMyScope());
        PrototypeBean prototypeBean1 = ctx.getBean(PrototypeBean.class);
        System.out.println(prototypeBean1.getMyScope());

        FakeDataSource fakeDataSource = ctx.getBean(FakeDataSource.class);
        System.out.println(fakeDataSource.getUserName());
        System.out.println(fakeDataSource.getPassword());
        System.out.println(fakeDataSource.getJdbcUrl());
    }

}
