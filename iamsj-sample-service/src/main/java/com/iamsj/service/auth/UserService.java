package com.iamsj.service.auth;

import java.util.List;

import com.iamsj.model.User;

public interface UserService {

	List<String> queryAllPerms(Long userId);

	User queryByUserName(String username);
	
}
