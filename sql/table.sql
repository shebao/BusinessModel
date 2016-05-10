DROP TABLE IF EXISTS custom CASCADE;
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




/*==============================================================*/
/* Table: t_employee_permission                                 */
/*==============================================================*/
ALTER TABLE t_employee_permission
   DROP CONSTRAINT t_employee_permission_pkey;

DROP TABLE IF EXISTS t_employee_permission;
-------------------------------------------------------------------------
CREATE TABLE t_employee_permission (
   employee_id          integer              NOT NULL,
   permission_id        integer              NOT NULL,
   enable               integer              NOT NULL DEFAULT '0'
)
WITHOUT OIDS;

COMMENT ON COLUMN t_employee_permission.employee_id IS '员工标识id';

COMMENT ON COLUMN t_employee_permission.permission_id IS '权限标识id';

COMMENT ON COLUMN t_employee_permission.enable IS
   '是否具有权限 .1:具有权限 0:不具有权限.';

ALTER TABLE t_employee_permission
   ADD CONSTRAINT t_employee_permission_pkey PRIMARY KEY
          (employee_id, permission_id);




/*==============================================================*/
/* Table: permission                                            */
/*==============================================================*/
ALTER TABLE permission
   DROP CONSTRAINT permission_pkey CASCADE;

DROP TABLE IF EXISTS permission;
-------------------------------------------------------------------------
CREATE TABLE permission (
   id                   integer              NOT NULL DEFAULT nextval('permission_seq'::regclass),
   permission_name      varchar(20)          NOT NULL,
   remark               varchar(9999)        NOT NULL
)
WITHOUT OIDS;

COMMENT ON COLUMN permission.id IS '权限id';

COMMENT ON COLUMN permission.permission_name IS '权限名称';

COMMENT ON COLUMN permission.remark IS '备注';

ALTER TABLE permission
   ADD CONSTRAINT permission_pkey PRIMARY KEY (id);




/*==============================================================*/
/* Table: t_group_permission                                    */
/*==============================================================*/
ALTER TABLE t_group_permission
   DROP CONSTRAINT t_group_permission_pkey;

DROP TABLE IF EXISTS t_group_permission;
-------------------------------------------------------------------------
CREATE TABLE t_group_permission (
   role_group_id        integer              NOT NULL,
   permission_id        integer              NOT NULL,
   enable               bool              NOT NULL DEFAULT '0'
)
WITHOUT OIDS;

COMMENT ON COLUMN t_group_permission.role_group_id IS '权限组标识id';

COMMENT ON COLUMN t_group_permission.permission_id IS '权限标识id';

COMMENT ON COLUMN t_group_permission.enable IS
   '是否具有权限 .1:具有权限 0:不具有权限.';

ALTER TABLE t_group_permission
   ADD CONSTRAINT t_group_permission_pkey PRIMARY KEY
          (role_group_id, permission_id);




/*==============================================================*/
/* Table: role_group                                            */
/*==============================================================*/
ALTER TABLE role_group
   DROP CONSTRAINT role_group_pkey;

DROP TABLE IF EXISTS role_group;
-------------------------------------------------------------------------
CREATE TABLE role_group (
   id                   integer              NOT NULL DEFAULT nextval('role_group_seq'::regclass),
   group_name           varchar(20)          NOT NULL,
   remark               varchar(9999)        NULL
)
WITHOUT OIDS;

COMMENT ON TABLE role_group IS
   '设置组权限，以便于设置操作员权限，操作员可以并且仅属于某一个角色组当中';

COMMENT ON COLUMN role_group.id IS '标识id';

COMMENT ON COLUMN role_group.group_name IS '角色名称';

COMMENT ON COLUMN role_group.remark IS '备注';

ALTER TABLE role_group
   ADD CONSTRAINT role_group_pkey PRIMARY KEY (id);



/*==============================================================*/
/* Table: employee                                              */
/*==============================================================*/
ALTER TABLE employee
   DROP CONSTRAINT employee_pkey;

DROP TABLE IF EXISTS employee;

-------------------------------------------------------------------
create table employee (
   id                   integer              not null default nextval('department_seq'),
   e_type               integer              not null default '1',
   login_name           varchar(20)          not null,
   password             varchar(50)          not null,
   job_number           varchar(20)          not null,
   real_name            varchar(10)          not null,
   mobile               varchar(20)          not null,
   idcard               varchar(18)          null,
   sex                  integer              not null default '0',
   birthday             timestamp            null,
   email                varchar(30)          null,
   role_group_id        integer              not null default '0',
   department_id        integer              not null,
   create_time          timestamp            not null default now(),
   commission_ratio     numeric(5,2)         null,
   superior_id          integer              null,
   enable               boolean              not null default 'true',
   status               integer              not null default '1'
)
without oids;

comment on column employee.e_type is
'员工的类型
1：职员
2：兼职
3：体验店管理
4：体验店员工
99：超验管理员
默认值：职员';

comment on column employee.login_name is
'登录账号';

comment on column employee.password is
'密码';

comment on column employee.job_number is
'工号';

comment on column employee.real_name is
'真实姓名';

comment on column employee.mobile is
'手机号码';

comment on column employee.sex is
'性别 1:男,0:女';

comment on column employee.birthday is
'出生时间';

comment on column employee.email is
'邮箱地址';

comment on column employee.role_group_id is
'角色权限组id';

comment on column employee.department_id is
'所属部门id';

comment on column employee.create_time is
'添加时间';

comment on column employee.commission_ratio is
'默认的提成比例。如果为活动或者陈列指定了提成，此值失效';

comment on column employee.enable is
'此员工是否可以登录，以及是否可用';

comment on column employee.status is
'1：正常
0：未启用
2：已删除';



ALTER TABLE employee
   ADD CONSTRAINT employee_pkey PRIMARY KEY (id);
   
   
   
   
   
   
 