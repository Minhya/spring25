package org.example.spring25.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.*;

import java.util.Locale;

@Configuration
public class MvcConfigurer implements WebMvcConfigurer {

    @Bean
    public LocaleResolver localeResolver() {
        // return new AcceptHeaderLocaleResolver(); Som första hand
        return new CookieLocaleResolver();

        /*
        För att spara språk inställning.
        För att spara icookies som sparats i webbläsare som sparats tills nästa gång man är inne igen
        */
        //return new SessionLocaleResolver();
        /*Sparar vilket språk i användarens sektion
        * Ligger i minnet i servern. Begränsad livstid, ish 30 min om man inte trycker på ngt.
        * */

        return new FixedLocaleResolver(Locale.ENGLISH); //För att få en fixed lokalisering.
    }

    /*Skicka med en parameter som heter lang för att byta språk*/
    @Bean
    public LocaleChangeInterceptor localeChangeInterceptor() {
        LocaleChangeInterceptor localeChangeInterceptor = new LocaleChangeInterceptor();
        localeChangeInterceptor.setParamName("lang");
        return localeChangeInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry){
        registry.addInterceptor(localeChangeInterceptor());
    }
}
