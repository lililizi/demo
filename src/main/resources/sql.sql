CREATE TABLE redpackets;
use redpackets;

CREATE TABLE redpacket(
`r_id` bigint NOT NULL AUTO_INCREMENT ,
`name` VARCHAR(120) NOT NULL ,
`balance` FLOAT NOT NULL ,
`total` FLOAT NOT NULL ,
`start_time` TIMESTAMP NOT NULL ,
`end_time` TIMESTAMP NOT NULL ,
PRIMARY KEY (r_id),
KEY idx_start_time(start_time),
KEY idx_end_time(end_time)
)ENGINE=InnoDB AUTO_INCREMENT =1000 ;

CREATE  table successget (
   `r_id` bigint NOT NULL ,
   `user_phone` bigint NOT NULL ,
   `money` FLOAT NOT NULL  ,
  `create_time` TIMESTAMP NOT NULL ,
  PRIMARY KEY (r_id,user_phone),/*联合主键*/
  KEY idx_create_time(create_time)
)ENGINE=InnoDB ;