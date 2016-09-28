create table T_MENU
(
  id             NUMBER not null,
  code       VARCHAR2(100) not null,
  remark  VARCHAR2(200)not null,
  url VARCHAR2(200)  not null,
  parent_id  NUMBER  not null,
  layers  NUMBER  not null,
  status              VARCHAR2(2),
  create_user_id      NUMBER,
  create_time         TIMESTAMP(6),
  update_user_id      NUMBER,
  update_time         TIMESTAMP(6)
);

create table T_PERMISSION
(
  id             NUMBER not null,
  code       VARCHAR2(100) not null,
  URL  VARCHAR2(200)not null,
  create_user_id      NUMBER,
  create_time         TIMESTAMP(6),
  update_user_id      NUMBER,
  update_time         TIMESTAMP(6)
);

create table T_ROLE_PERMISSION
(
  id             NUMBER not null,
  ROLE_ID       NUMBER not null,
  PERMISSION_CODE  VARCHAR2(100)not null
);

create table T_USER
(
  id             NUMBER not null,
  user_name  VARCHAR2(100)not null,
  password       VARCHAR2(100) not null,
  status  VARCHAR2(2)not null,
  last_login_time  TIMESTAMP(6),
  last_login_ip varchar2(20),
  create_user_id      NUMBER,
  create_time         TIMESTAMP(6),
  update_user_id      NUMBER,
  update_time         TIMESTAMP(6)
);

create table T_ROLE
(
  id             NUMBER not null,
  name  VARCHAR2(100)not null,
  remark       VARCHAR2(100) not null,
  create_user_id      NUMBER,
  create_time         TIMESTAMP(6),
  update_user_id      NUMBER,
  update_time         TIMESTAMP(6)
);

create table T_USER_ROLE
(
  id             NUMBER not null,
  user_id  NUMBER not null,
  role_id       VARCHAR2(50) not null
);
