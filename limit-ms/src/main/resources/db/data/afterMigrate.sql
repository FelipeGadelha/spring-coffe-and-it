SET session_replication_role = 'replica';

DELETE FROM daily_limits;

SET session_replication_role = 'origin';

ALTER SEQUENCE daily_limits_id_seq RESTART WITH 1;

INSERT INTO daily_limits(id, account, agency, date, value)
VALUES
(1, '007', '8080', now(), 1010.10),
(2, '008', '1001', now(), 100.10);

SELECT nextval ('daily_limits_id_seq');
SELECT nextval ('daily_limits_id_seq');