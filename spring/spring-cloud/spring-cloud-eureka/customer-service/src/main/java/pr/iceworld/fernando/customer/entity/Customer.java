package pr.iceworld.fernando.customer.entity;


//CREATE TABLE `mall_user` (
//        `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户表id',
//        `username` varchar(50) NOT NULL COMMENT '用户名',
//        `password` varchar(50) NOT NULL COMMENT '用户密码，MD5加密',
//        `email` varchar(50) DEFAULT NULL,
//  `phone` varchar(20) DEFAULT NULL,
//  `question` varchar(100) DEFAULT NULL COMMENT '找回密码问题',
//        `answer` varchar(100) DEFAULT NULL COMMENT '找回密码答案',
//        `role` int(4) NOT NULL COMMENT '角色0-管理员,1-普通用户',
//        `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
//        `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后一次更新时间',
//PRIMARY KEY (`id`),
//UNIQUE KEY `user_name_unique` (`username`) USING BTREE
//) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

/**
 *  @Entity annotation defines that a class can be mapped to a table
 *  @GeneratedValue This annotation is used to specify the primary key generation strategy to use
 */
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Getter
@Setter
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private int age;

}
