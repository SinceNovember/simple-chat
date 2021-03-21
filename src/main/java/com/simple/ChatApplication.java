package com.simple;

import com.simple.server.WebsocketChatServer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

import javax.annotation.Resource;

@SpringBootApplication
@MapperScan(basePackages = "com.simple.dao")
public class ChatApplication implements CommandLineRunner {

    @Value("${netty.port}")
    private int port;

    @Resource
    private WebsocketChatServer server;

    public static void main(String[] args) {
        SpringApplication.run(ChatApplication.class, args);
    }

    /**
     * 启动netty服务器
     * @param args
     * @throws Exception
     */
    @Override
    public void run(String... args) throws Exception {
        server.run(port);
    }
}
