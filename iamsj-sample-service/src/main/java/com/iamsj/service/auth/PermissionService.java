package com.iamsj.service.auth;

import java.util.HashMap;
import java.util.List;

import com.iamsj.model.Permission;

public interface PermissionService {

	List<Permission> queryList(HashMap<String, Object> hashMap);

}
