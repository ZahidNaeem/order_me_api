package org.zahid.apps.om.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.zahid.apps.om.service.impl.AuditorAwareImpl;

@Configuration
@EnableJpaAuditing(auditorAwareRef = "auditorAware")
@RequiredArgsConstructor
public class CommonConfig {

    private final ConfigProperties configProperties;

    @Bean
    public WebMvcConfigurer corsConfigurer() {
//        final String origin = "http://" + configProperties.getApp().get("server") + ":" + configProperties.getApp().get("port");
        final String origin = "*";
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry
                        .addMapping("/**").allowedOrigins(origin)
                        .allowedMethods("HEAD", "GET", "POST", "PUT", "DELETE", "PATCH", "OPTIONS");
            }
        };
    }

    @Bean
    public AuditorAware<Long> auditorAware(){
        return new AuditorAwareImpl();
    }

    /*public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**").allowedOrigins("http://localhost:3000");
            }
        };
    }*/

 /*   public CorsConfigurationSource corsConfigurationSource() {
        final CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(ImmutableList.of("*"));
        configuration.setAllowedMethods(ImmutableList.of("HEAD",
            "GET", "POST", "PUT", "DELETE", "PATCH", "OPTIONS"));
        configuration.setAllowCredentials(true);
        configuration.setAllowedHeaders(ImmutableList.of("*"));
        configuration.setExposedHeaders(ImmutableList.of("X-Auth-Token","Authorization","Access-Control-Allow-Origin","Access-Control-Allow-Credentials"));
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }*/
}
