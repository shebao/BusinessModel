DROP TABLE IF EXISTS custom;
/*==============================================================*/
/* Table: custom                                                */
/*==============================================================*/
CREATE TABLE custom (
   id                   integer              NOT NULL DEFAULT nextval('custom_seq'::regclass),
   login_name           varchar(30)          NOT NULL,
   mobile               varchar(20)          NULL,
   password             varchar(100)         NOT NULL DEFAULT NULL::CHARACTER VARYING,
   trade_password       varchar(100)         NULL DEFAULT NULL::CHARACTER VARYING,
   create_time          timestamp            NOT NULL DEFAULT now(),
   last_login_time      timestamp            NULL,
   enable               boolean              NOT NULL DEFAULT '1',
   real_name            varchar(20)          NULL,
   sex                  integer              NOT NULL DEFAULT '0',
   idcard               varchar(20)          NULL DEFAULT NULL::CHARACTER VARYING,
   seat                 text                 NULL DEFAULT NULL::CHARACTER VARYING,
   email                text                 NULL DEFAULT NULL::CHARACTER VARYING,
   head_picture         text                 NULL DEFAULT NULL::CHARACTER VARYING,
   reg_recommended      integer              NULL
)
WITHOUT OIDS;

COMMENT ON TABLE custom IS '保存客户基本信息';

COMMENT ON COLUMN custom.sex IS '1：男
2：女
0：未知
其他值：未知';

COMMENT ON COLUMN custom.reg_recommended IS
   '推荐此用户注册的员工。此信息一般写入将不会变化';

ALTER TABLE custom
   ADD CONSTRAINT custom_pkey PRIMARY KEY (id);



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
   
   