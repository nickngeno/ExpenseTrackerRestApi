package com.kimmy.ExpenseTrackerREStAPI;

import com.kimmy.ExpenseTrackerREStAPI.filters.AuthFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ExpenseTrackerReStApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(ExpenseTrackerReStApiApplication.class, args);
    }

    @Bean
    public FilterRegistrationBean<AuthFilter> filterFilterRegistrationBean() {

        FilterRegistrationBean<AuthFilter> filterRegistrationBean = new FilterRegistrationBean();
        AuthFilter authFilter = new AuthFilter();
        filterRegistrationBean.setFilter(authFilter);
        filterRegistrationBean.addUrlPatterns("/api/categories/*");
        return filterRegistrationBean;
    }
}
