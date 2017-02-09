DELIMITER $$
CREATE PROCEDURE `redpackets`.`getmon`
(in v_r_id bigint,in v_phone bigint,
in v_money FLOAT ,out v_result int
)
BEGIN
    DECLARE insert_count int DEFAULT 0;
    START TRANSACTION ;
    INSERT ignore INTO successget
    (r_id,user_phone,money)
    VALUES (v_r_id,v_phone,v_money);
    SELECT ROW_COUNT() INTO insert_count;
    if(insert_count=0) THEN
      ROLLBACK ;
      SET v_result=-1;
    ELSEIF  (insert_count<0)THEN
      ROLLBACK ;
      set v_result=-2;
    ELSE
      UPDATE redpacket SET balance=balance-money
      WHERE r_id=v_r_id
      AND balance>=money;
      SELECT ROW_COUNT() INTO insert_count;
        IF(insert_count=0) THEN
        ROLLBACK ;
      SET v_result=0;
      ELSEIF(v_result<0)THEN
    ROLLBACK ;
    set v_result=-2;
    ELSE
    COMMIT ;
    SET v_result=1;
    END IF;
END IF;
END ;
$$