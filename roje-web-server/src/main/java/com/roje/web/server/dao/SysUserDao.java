package com.roje.web.server.dao;

import com.roje.web.server.data.SysUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Ro
 */
@Repository
public interface SysUserDao extends JpaRepository<SysUser, Long> {
    SysUser getByUsername(String name);
}
