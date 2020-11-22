/**
 * 
 */
package com.example.Learnsecurity.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author rajivranjan
 *
 */
@RestController
public class PingPongController {

	@GetMapping("/test")
	public String method1() {
		return "pingpong";
	}
	
	@GetMapping("/admin/ping")
	public String method2() {
		return "Admin pingpong";
	}
}
