package com.ccread.order;


import com.ccread.admin.api.UserFeignClient;
import com.ccread.book.api.BookFeign;
import com.ccread.bookeack.api.BookeackFeign;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients(basePackageClasses = {UserFeignClient.class, BookFeign.class, BookeackFeign.class})
@ComponentScan( basePackages = {"com.ccread.order","com.ccread.admin.api"})
@ComponentScan( basePackages = {"com.ccread.order","com.ccread.book.api"})
@ComponentScan( basePackages = {"com.ccread.order","com.ccread.bookeack.api"})
public class OrderApplication {
    public static void main(String[] args) {
        SpringApplication.run(OrderApplication.class,args);
    }
}
