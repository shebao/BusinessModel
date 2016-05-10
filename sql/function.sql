/*==============================================================
判断用户是否已经存在 	                                        
==============================================================*/

CREATE OR REPLACE FUNCTION existsCusLoginName (IN loginname varchar)
   RETURNS integer
AS
$$
DECLARE
BEGIN
  PERFORM 1 FROM custom WHERE login_name = loginname;

   IF found
   THEN
      RETURN 1;
   ELSE
      RETURN 0;
   END IF;
END;
$$
   LANGUAGE 'plpgsql'
   IMMUTABLE;

/*==============================================================
增加一个用户                                        
==============================================================*/

CREATE OR REPLACE FUNCTION addCustom (IN loginname   varchar,
                                      IN passwd      varchar)
   RETURNS integer
AS
$$
DECLARE
BEGIN
   INSERT INTO custom (login_name, password)
        VALUES (loginname, passwd);

   RETURN currval ('custom_seq');
END;
$$
   LANGUAGE 'plpgsql';

/*==============================================================
增加一个新的权限组 	                                        
==============================================================*/

CREATE OR REPLACE FUNCTION addrolegroup (IN in_group_name   varchar,
                                         IN in_remark       varchar)
   RETURNS integer
AS
$$
DECLARE
BEGIN
   INSERT INTO role_group (group_name, remark)
        VALUES (in_group_name, in_remark);

   RETURN currval ('role_group_seq');
END;
$$
   LANGUAGE 'plpgsql';



/*==============================================================
增加一个新的员工                                        
==============================================================*/

CREATE OR REPLACE FUNCTION addemployee (
   IN i_superior_id        integer,
   IN i_mobile             varchar,
   IN i_email              varchar,
   IN i_real_name          varchar,
   IN i_birthday           TIMESTAMP,
   IN i_department_id      integer,
   IN i_sex                integer,
   IN i_enable             bool,
   IN i_e_type             integer,
   IN i_job_number         varchar,
   IN i_idcard             varchar,
   IN i_status             integer,
   IN i_commission_ratio   numeric (5, 2),
   IN i_role_group_id      integer,
   IN i_login_name         varchar,
   IN i_password           varchar)
   RETURNS integer
AS
$$
DECLARE
BEGIN
   INSERT INTO employee (superior_id,
                         mobile,
                         email,
                         real_name,
                         birthday,
                         department_id,
                         sex,
                         enable,
                         e_type,
                         job_number,
                         idcard,
                         status,
                         commission_ratio,
                         create_time,
                         role_group_id,
                         login_name,
                         password)
        VALUES (i_superior_id,
                i_mobile,
                i_email,
                i_real_name,
                i_birthday,
                i_department_id,
                i_sex,
                i_enable,
                i_e_type,
                i_job_number,
                i_idcard,
                i_status,
                i_commission_ratio,
                CURRENT_TIMESTAMP,
                i_role_group_id,
                i_login_name,
                i_password);

   RETURN currval ('employee_seq');
END;
$$
   LANGUAGE 'plpgsql';