package com.ccread.comment;


import com.ccread.admin.api.UserFeignClient;
import com.ccread.book.api.BookFeign;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients(basePackageClasses = {UserFeignClient.class, BookFeign.class})
//@EnableFeignClients(basePackages = {"com.ccread.admin.api","com.ccread.book.api"})
@ComponentScan( basePackages = {"com.ccread.comment","com.ccread.book.api"})
@ComponentScan( basePackages = {"com.ccread.comment","com.ccread.admin.api"})
public class CommentApplication {
    public static void main(String[] args) {
        SpringApplication.run(CommentApplication.class,args);
    }
}
