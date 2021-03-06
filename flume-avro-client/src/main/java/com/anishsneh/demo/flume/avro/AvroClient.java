package com.anishsneh.demo.flume.avro;

import java.util.UUID;

import com.anishsneh.demo.flume.avro.vo.User;

public class AvroClient {
	public static void main(String[] args) {
		RpcClientFacade client = new RpcClientFacade();
		// Initialize client with the remote Flume agent's host and port
		client.init("192.168.126.130", 55555);
		for (int i = 1; i <= 500; i++) {
			User user = new User();
			user.setId(UUID.randomUUID().toString());
			user.setEmail("me_" + i + "@anishsneh.com");
			user.setCreatedTime(System.currentTimeMillis());
			user.setLogin("login_" + i);
			user.setName("USER_NAME_" + i);
			client.sendDataToFlume(user.toString());
			System.out.println("===============================");
			System.out.println(user.toString());
			try {
				Thread.sleep(200);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		client.cleanUp();
	}
}