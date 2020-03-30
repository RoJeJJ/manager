package com.roje.web.server.data;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author Ro
 */
@Entity
@Table(name = "sys_user", uniqueConstraints = {
        @UniqueConstraint(name = "uk_username", columnNames = "username"),
})
@EntityListeners(AuditingEntityListener.class)
@Getter@Setter
public class SysUser implements Serializable {

    private static final long serialVersionUID = -765512665431033204L;

    @Id@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    private String password;

    @CreatedDate
    @Column(name = "gmt_create", updatable = false, nullable = false)
    private Long gmtCreate;

    @LastModifiedDate
    @Column(name = "gmt_modify",nullable = false)
    private Long gmtModify;
}
