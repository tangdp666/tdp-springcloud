package com.ccread.bookeack;


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
//@EnableAutoDataSourceProxy
@EnableFeignClients(basePackageClasses = {BookFeign.class})
@ComponentScan(basePackages = {"com.ccread.bookeack","com.ccread.book.api"})
public class BookeackApplication {
    public static void main(String[] args) {
        SpringApplication.run(BookeackApplication.class,args);
    }
}
