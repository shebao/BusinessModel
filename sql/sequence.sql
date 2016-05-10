/*==============================================================*/
/* DBMS name:      PostgreSQL 9.x                               */
/* Created on:     2016/5/9 18:00:32                            */
/*==============================================================*/

DROP SEQUENCE IF EXISTS activity_cash_seq CASCADE;

DROP SEQUENCE IF EXISTS adjusts_stock_order_seq CASCADE;

DROP SEQUENCE IF EXISTS ali_seq CASCADE;

DROP SEQUENCE IF EXISTS c_bank_seq CASCADE;

DROP SEQUENCE IF EXISTS c_finance_seq CASCADE;

DROP SEQUENCE IF EXISTS credit_unit_seq CASCADE;

DROP SEQUENCE IF EXISTS cup_seq CASCADE;

DROP SEQUENCE IF EXISTS custom_address_seq CASCADE;

DROP SEQUENCE IF EXISTS custom_cash_seq CASCADE;

DROP SEQUENCE IF EXISTS custom_collect_seq CASCADE;

DROP SEQUENCE IF EXISTS custom_join_activity_seq CASCADE;

DROP SEQUENCE IF EXISTS custom_pay_seq CASCADE;

DROP SEQUENCE IF EXISTS custom_seq CASCADE;

DROP SEQUENCE IF EXISTS department_seq  CASCADE;

DROP SEQUENCE IF EXISTS display_dtl_seq CASCADE;

DROP SEQUENCE IF EXISTS display_seq CASCADE;

DROP SEQUENCE IF EXISTS document_seq CASCADE;

DROP SEQUENCE IF EXISTS employee_seq CASCADE;

DROP SEQUENCE IF EXISTS into_stock_order_seq CASCADE;

DROP SEQUENCE IF EXISTS out_stock_order_seq CASCADE;

DROP SEQUENCE IF EXISTS permission_seq CASCADE;

DROP SEQUENCE IF EXISTS product_category_seq CASCADE;

DROP SEQUENCE IF EXISTS product_seq CASCADE;

DROP SEQUENCE IF EXISTS purchase_order_seq CASCADE;

DROP SEQUENCE IF EXISTS return_visit_seq CASCADE;

DROP SEQUENCE IF EXISTS role_group_seq CASCADE;

DROP SEQUENCE IF EXISTS sale_activity_category_seq CASCADE;

DROP SEQUENCE IF EXISTS sale_activity_seq CASCADE;

DROP SEQUENCE IF EXISTS sale_order_dtl_seq CASCADE;

DROP SEQUENCE IF EXISTS sale_order_seq CASCADE;

DROP SEQUENCE IF EXISTS shebei_seq CASCADE;

DROP SEQUENCE IF EXISTS specification_seq CASCADE;

DROP SEQUENCE IF EXISTS staging_seq CASCADE;

DROP SEQUENCE IF EXISTS supplier_seq CASCADE;

DROP SEQUENCE IF EXISTS transfer_order_seq CASCADE;

DROP SEQUENCE IF EXISTS warehorse_seq CASCADE;

CREATE SEQUENCE activity_cash_seq INCREMENT BY 1
                                  NO MINVALUE
                                  NO MAXVALUE
                                  START WITH 1
                                  CACHE 1;

CREATE SEQUENCE adjusts_stock_order_seq INCREMENT BY 1
                                        NO MINVALUE
                                        NO MAXVALUE
                                        START WITH 1
                                        CACHE 1;

CREATE SEQUENCE ali_seq INCREMENT BY 1
                        NO MINVALUE
                        NO MAXVALUE
                        START WITH 1
                        CACHE 1;

CREATE SEQUENCE c_bank_seq INCREMENT BY 1
                           NO MINVALUE
                           NO MAXVALUE
                           START WITH 1
                           CACHE 1;

CREATE SEQUENCE c_finance_seq INCREMENT BY 1
                              NO MINVALUE
                              NO MAXVALUE
                              START WITH 1
                              CACHE 1;

CREATE SEQUENCE credit_unit_seq INCREMENT BY 1
                                NO MINVALUE
                                NO MAXVALUE
                                START WITH 1
                                CACHE 1;

CREATE SEQUENCE cup_seq INCREMENT BY 1
                        NO MINVALUE
                        NO MAXVALUE
                        START WITH 1
                        CACHE 1;

CREATE SEQUENCE custom_address_seq INCREMENT BY 1
                                   NO MINVALUE
                                   NO MAXVALUE
                                   START WITH 1
                                   CACHE 1;

CREATE SEQUENCE custom_cash_seq INCREMENT BY 1
                                NO MINVALUE
                                NO MAXVALUE
                                START WITH 1
                                CACHE 1;

CREATE SEQUENCE custom_collect_seq INCREMENT BY 1
                                   NO MINVALUE
                                   NO MAXVALUE
                                   START WITH 1
                                   CACHE 1;

CREATE SEQUENCE custom_join_activity_seq INCREMENT BY 1
                                         NO MINVALUE
                                         NO MAXVALUE
                                         START WITH 1
                                         CACHE 1;

CREATE SEQUENCE custom_pay_seq INCREMENT BY 1
                               NO MINVALUE
                               NO MAXVALUE
                               START WITH 1
                               CACHE 1;

CREATE SEQUENCE custom_seq INCREMENT BY 1
                           NO MINVALUE
                           NO MAXVALUE
                           START WITH 1
                           CACHE 1;

CREATE SEQUENCE department_seq INCREMENT BY 1
                               NO MINVALUE
                               NO MAXVALUE
                               START WITH 1
                               CACHE 1;

CREATE SEQUENCE display_dtl_seq INCREMENT BY 1
                                NO MINVALUE
                                NO MAXVALUE
                                START WITH 1
                                CACHE 1;

CREATE SEQUENCE display_seq INCREMENT BY 1
                            NO MINVALUE
                            NO MAXVALUE
                            START WITH 1
                            CACHE 1;

CREATE SEQUENCE document_seq INCREMENT BY 1
                             NO MINVALUE
                             NO MAXVALUE
                             START WITH 1
                             CACHE 1;

CREATE SEQUENCE employee_seq INCREMENT BY 1
                             NO MINVALUE
                             NO MAXVALUE
                             START WITH 1
                             CACHE 1;

CREATE SEQUENCE into_stock_order_seq INCREMENT BY 1
                                     NO MINVALUE
                                     NO MAXVALUE
                                     START WITH 1
                                     CACHE 1;

CREATE SEQUENCE out_stock_order_seq INCREMENT BY 1
                                    NO MINVALUE
                                    NO MAXVALUE
                                    START WITH 1
                                    CACHE 1;

CREATE SEQUENCE permission_seq INCREMENT BY 1
                               NO MINVALUE
                               NO MAXVALUE
                               START WITH 1
                               CACHE 1;

CREATE SEQUENCE product_category_seq INCREMENT BY 1
                                     NO MINVALUE
                                     NO MAXVALUE
                                     START WITH 1
                                     CACHE 1;

CREATE SEQUENCE product_seq INCREMENT BY 1
                            NO MINVALUE
                            NO MAXVALUE
                            START WITH 1
                            CACHE 1;

CREATE SEQUENCE purchase_order_seq INCREMENT BY 1
                                   NO MINVALUE
                                   NO MAXVALUE
                                   START WITH 1
                                   CACHE 1;

CREATE SEQUENCE return_visit_seq INCREMENT BY 1
                                 NO MINVALUE
                                 NO MAXVALUE
                                 START WITH 1
                                 CACHE 1;

CREATE SEQUENCE role_group_seq INCREMENT BY 1
                               NO MINVALUE
                               NO MAXVALUE
                               START WITH 1
                               CACHE 1;

CREATE SEQUENCE sale_activity_category_seq INCREMENT BY 1
                                           NO MINVALUE
                                           NO MAXVALUE
                                           START WITH 1
                                           CACHE 1;

CREATE SEQUENCE sale_activity_seq INCREMENT BY 1
                                  NO MINVALUE
                                  NO MAXVALUE
                                  START WITH 1
                                  CACHE 1;

CREATE SEQUENCE sale_order_dtl_seq INCREMENT BY 1
                                   NO MINVALUE
                                   NO MAXVALUE
                                   START WITH 1
                                   CACHE 1;

CREATE SEQUENCE sale_order_seq INCREMENT BY 1
                               NO MINVALUE
                               NO MAXVALUE
                               START WITH 1
                               CACHE 1;

CREATE SEQUENCE shebei_seq INCREMENT BY 1
                           NO MINVALUE
                           NO MAXVALUE
                           START WITH 1
                           CACHE 1;

CREATE SEQUENCE specification_seq INCREMENT BY 1
                                  NO MINVALUE
                                  NO MAXVALUE
                                  START WITH 1
                                  CACHE 1;

CREATE SEQUENCE staging_seq INCREMENT BY 1
                            NO MINVALUE
                            NO MAXVALUE
                            START WITH 1
                            CACHE 1;

CREATE SEQUENCE supplier_seq INCREMENT BY 1
                             NO MINVALUE
                             NO MAXVALUE
                             START WITH 1
                             CACHE 1;

CREATE SEQUENCE transfer_order_seq INCREMENT BY 1
                                   NO MINVALUE
                                   NO MAXVALUE
                                   START WITH 1
                                   CACHE 1;

CREATE SEQUENCE warehorse_seq INCREMENT BY 1
                              NO MINVALUE
                              NO MAXVALUE
                              START WITH 1
                              CACHE 1;