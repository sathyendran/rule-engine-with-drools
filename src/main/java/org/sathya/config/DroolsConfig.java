package org.sathya.config;

import org.kie.api.KieBase;
import org.kie.api.KieServices;
import org.kie.api.builder.KieBuilder;
import org.kie.api.builder.KieFileSystem;
import org.kie.api.builder.KieModule;
import org.kie.api.runtime.KieContainer;
import org.kie.internal.io.ResourceFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

@Configuration
public class DroolsConfig {

    private static final String RULES_FOLDER_LOCATION = "rules/";

    @Bean
    public KieBase kieBase() throws IOException {
        return kieContainer().getKieBase();
    }

    @Bean
    public KieContainer kieContainer() throws IOException {
        KieServices kieServices = kieServices();

        KieBuilder kieBuilder = kieServices.newKieBuilder(kieFileSystem());
        kieBuilder.buildAll();
        KieModule kieModule = kieBuilder.getKieModule();

        return kieServices().newKieContainer(kieModule.getReleaseId());
    }


    @Bean
    public KieFileSystem kieFileSystem() throws IOException {
        KieFileSystem kieFileSystem = kieServices().newKieFileSystem();
        /*for (Resource resource : resources) {
            kieFileSystem.write(ResourceFactory.newFileResource(resource.getFile()));
        }*/
        kieFileSystem.write(ResourceFactory.newClassPathResource("rules/Regulatory-Rule.xls"));
        /*for (Resource resource : getRuleFiles()) {
            kieFileSystem.write(ResourceFactory.newFileResource(resource.getFile()));
        }*/
        return kieFileSystem;
    }

    private KieServices kieServices() {
        return KieServices.Factory.get();
    }
}
