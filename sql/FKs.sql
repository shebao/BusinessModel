
/*==============================================================*/
/* 外键关系      		                                            */
/*==============================================================*/
alter table t_employee_permission
   add constraint FK_T_EMPLOY_REFERENCE_EMPLOYEE foreign key (employee_id)
      references employee (id)
      on delete restrict on update restrict;

alter table t_employee_permission
   add constraint FK_T_EMPLOY_REFERENCE_PERMISSI foreign key (permission_id)
      references permission (id)
      on delete restrict on update restrict;

alter table t_group_permission
   add constraint FK_T_GROUP__REFERENCE_PERMISSI foreign key (permission_id)
      references permission (id)
      on delete restrict on update restrict;

alter table t_group_permission
   add constraint FK_g_p_rolegroupid foreign key (role_group_id)
      references role_group (id);

alter table employee
   add constraint FK_EMPLOYEE_REFERENCE_DEPARTME foreign key (department_id)
      references department (id)
      on delete restrict on update restrict;

alter table employee
   add constraint FK_EMPLOYEE_REFERENCE_EMPLOYEE foreign key (superior_id)
      references employee (id)
      on delete restrict on update restrict;

alter table employee
   add constraint FK_EMPLOYEE_REFERENCE_ROLE_GRO foreign key (role_group_id)
      references role_group (id)
      on delete restrict on update restrict;

alter table custom
   add constraint FK_CUSTOM_REFERENCE_EMPLOYEE foreign key (reg_recommended)
      references employee (id)
      on delete restrict on update restrict;
	  
	  