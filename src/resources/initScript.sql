BEGIN
    BEGIN
       EXECUTE IMMEDIATE 'DROP TABLE FEEDBACK ';
       EXECUTE IMMEDIATE 'DROP TABLE PRODUCTS_PARAMETERS';
       EXECUTE IMMEDIATE 'DROP TABLE PRODUCTS_ATTRIBUTES';
       EXECUTE IMMEDIATE 'DROP TABLE ORDER_PRODUCTS';
       EXECUTE IMMEDIATE 'DROP TABLE PRODUCTS';
       EXECUTE IMMEDIATE 'DROP TABLE ORDERS';
       EXECUTE IMMEDIATE 'DROP TABLE DELIVERIES';
       EXECUTE IMMEDIATE 'DROP TABLE PAYMENTS';
    EXCEPTION
       WHEN OTHERS THEN
          IF SQLCODE !=-942 THEN
             RAISE;
          END IF;
    END;
    BEGIN
       EXECUTE IMMEDIATE 'DROP TRIGGER ' || 'BI_USERS';
       EXECUTE IMMEDIATE 'DROP TRIGGER ' || 'BI_PAYMENTS';
       EXECUTE IMMEDIATE 'DROP TRIGGER ' || 'BI_DELIVERIES';
       EXECUTE IMMEDIATE 'DROP TRIGGER ' || 'BI_ORDERS';
       EXECUTE IMMEDIATE 'DROP TRIGGER ' || 'BI_PRODUCTS';
       EXECUTE IMMEDIATE 'DROP TRIGGER ' || 'BI_PRODUCTS_ATTRIBUTES';
       EXECUTE IMMEDIATE 'DROP TRIGGER ' || 'BI_FEEDBACK';
    EXCEPTION
       WHEN OTHERS THEN
          IF SQLCODE !=-4080 THEN
             RAISE;
          END IF;
    END;

    BEGIN
       EXECUTE IMMEDIATE 'DROP SEQUENCE ' || 'PAYMENT_SEQ';
       EXECUTE IMMEDIATE 'DROP SEQUENCE ' || 'USERS_SEQ';
       EXECUTE IMMEDIATE 'DROP SEQUENCE ' || 'ORDERS_SEQ';
       EXECUTE IMMEDIATE 'DROP SEQUENCE ' || 'FEEDBACK_SEQ';
       EXECUTE IMMEDIATE 'DROP SEQUENCE ' || 'DELIVERY_SEQ';
       EXECUTE IMMEDIATE 'DROP SEQUENCE ' || 'PRODUCTS_SEQ';
       EXECUTE IMMEDIATE 'DROP SEQUENCE ' || 'PRODUCT_ATTRIBUTES_SEQ';
    EXCEPTION
       WHEN OTHERS THEN
          IF SQLCODE !=-2289 THEN
             RAISE;
          END IF;
    END;
END;
    /
    create sequence "PAYMENT_SEQ"
    start with 1000000
    increment by 1
    cache 100
    nocycle
    noorder
    /

    create sequence "USERS_SEQ"
    start with 1000000
    increment by 1
    cache 100
    nocycle
    noorder
    /

    create sequence "ORDERS_SEQ"
    start with 1000000
    increment by 1
    cache 100
    nocycle
    noorder
    /

    create sequence "FEEDBACK_SEQ"
    start with 1000000
    increment by 1
    cache 100
    nocycle
    noorder
    /

    create sequence "DELIVERY_SEQ"
    start with 1000000
    increment by 1
    cache 100
    nocycle
    noorder
    /

    create sequence "PRODUCTS_SEQ"
    start with 1000000
    increment by 1
    cache 100
    nocycle
    noorder
    /

    create sequence "PRODUCT_ATTRIBUTES_SEQ"
    start with 1000000
    increment by 1
    cache 100
    nocycle
    noorder
    /

    CREATE table "USERS" (
        "USER_ID"       NUMBER NOT NULL,
        "USER_NAME"     VARCHAR2(50) NOT NULL,
        "USER_SURNAME"  VARCHAR2(50),
        "USER_EMAIL"    VARCHAR2(100) NOT NULL,
        "USER_PHONE"    NUMBER(20),
        "USER_PASSWORD" VARCHAR2(100) NOT NULL,
        "USER_ADDRESS"  VARCHAR2(100),
        "ISADMIN"       NUMBER NOT NULL,
        constraint  "USERS_PK" primary key ("USER_ID")
    )
    /

    CREATE trigger "BI_USERS"
      before insert on "USERS"
      for each row
    begin
      if :NEW."USER_ID" is null then
        select "USERS_SEQ".nextval into :NEW."USER_ID" from dual;
      end if;
    end;
    /

    CREATE table "PAYMENTS" (
        "PAYMENT_ID"     NUMBER NOT NULL,
        "PAYMENT_TYPE"   VARCHAR2(50) NOT NULL,
        "PAYMENT_AMOUNT" NUMBER NOT NULL,
        "ISPAID"         NUMBER NOT NULL,
        constraint  "PAYMENTS_PK" primary key ("PAYMENT_ID")
    )
    /

    CREATE trigger "BI_PAYMENTS"
      before insert on "PAYMENTS"
      for each row
    begin
      if :NEW."PAYMENT_ID" is null then
        select "PAYMENT_SEQ".nextval into :NEW."PAYMENT_ID" from dual;
      end if;
    end;
    /

    CREATE table "DELIVERIES" (
        "DELIVERY_ID"      NUMBER NOT NULL,
        "RECEIVER_NAME"    VARCHAR2(50) NOT NULL,
        "RECEIVER_SURNAME" VARCHAR2(50) NOT NULL,
        "ADDRESS"          VARCHAR2(50) NOT NULL,
        "PHONE"            VARCHAR2(20) NOT NULL,
        constraint  "DELIVERIES_PK" primary key ("DELIVERY_ID")
    )
    /

    CREATE trigger "BI_DELIVERIES"
      before insert on "DELIVERIES"
      for each row
    begin
      if :NEW."DELIVERY_ID" is null then
        select "DELIVERY_SEQ".nextval into :NEW."DELIVERY_ID" from dual;
      end if;
    end;
    /

    CREATE table "ORDERS" (
        "ORDER_ID"    NUMBER NOT NULL,
        "ORDER_DATE"  TIMESTAMP NOT NULL,
        "USER_ID"     NUMBER NOT NULL,
        "PAYMENT_ID"  NUMBER NOT NULL,
        "DELIVERY_ID" NUMBER NOT NULL,
        constraint  "ORDERS_PK" primary key ("ORDER_ID")
    )
    /

    CREATE trigger "BI_ORDERS"
      before insert on "ORDERS"
      for each row
    begin
      if :NEW."ORDER_ID" is null then
        select "ORDERS_SEQ".nextval into :NEW."ORDER_ID" from dual;
      end if;
    end;
    /

    ALTER TABLE "ORDERS" ADD CONSTRAINT "ORDERS_USERS_FK"
    FOREIGN KEY ("USER_ID")
    REFERENCES "USERS" ("USER_ID")
    /

    ALTER TABLE "ORDERS" ADD CONSTRAINT "ORDERS_PAYMENTS_FK"
    FOREIGN KEY ("PAYMENT_ID")
    REFERENCES "PAYMENTS" ("PAYMENT_ID")
    /

    ALTER TABLE "ORDERS" ADD CONSTRAINT "ORDERS_DELIVERIES_FK"
    FOREIGN KEY ("DELIVERY_ID")
    REFERENCES "DELIVERIES" ("DELIVERY_ID")
    /

    CREATE table "PRODUCTS" (
        "PRODUCT_ID"   NUMBER NOT NULL,
        "PARENT_ID"    NUMBER,
        "PRODUCT_NAME" VARCHAR2(100) NOT NULL,
        "ISCATEGORY"   NUMBER NOT NULL,
        constraint  "PRODUCTS_PK" primary key ("PRODUCT_ID")
    )
    /

    CREATE trigger "BI_PRODUCTS"
      before insert on "PRODUCTS"
      for each row
    begin
      if :NEW."PRODUCT_ID" is null then
        select "PRODUCT_SEQ".nextval into :NEW."PRODUCT_ID" from dual;
      end if;
    end;
    /

    CREATE table "ORDER_PRODUCTS" (
        "ORDER_ID"   NUMBER NOT NULL,
        "PRODUCT_ID" NUMBER NOT NULL,
        "AMOUNT"     NUMBER NOT NULL,
        "PRICE"      NUMBER(6,0) NOT NULL
    )
    /

    ALTER TABLE "ORDER_PRODUCTS" ADD CONSTRAINT "ORDER_PRODUCTS_ORDER_FK"
    FOREIGN KEY ("ORDER_ID")
    REFERENCES "ORDERS" ("ORDER_ID")
    /

    ALTER TABLE "ORDER_PRODUCTS" ADD CONSTRAINT "ORDER_PRODUCTS_PRODUCT_FK"
    FOREIGN KEY ("PRODUCT_ID")
    REFERENCES "PRODUCTS" ("PRODUCT_ID")
    /

    CREATE table "PRODUCTS_ATTRIBUTES" (
        "ATTRIBUTE_ID"   NUMBER NOT NULL,
        "PRODUCT_ID"     NUMBER NOT NULL,
        "ATTRIBUTE_NAME" VARCHAR2(50) NOT NULL,
        constraint  "PRODUCTS_ATTRIBUTES_PK" primary key ("ATTRIBUTE_ID")
    )
    /

    CREATE trigger "BI_PRODUCTS_ATTRIBUTES"
      before insert on "PRODUCTS_ATTRIBUTES"
      for each row
    begin
      if :NEW."ATTRIBUTE_ID" is null then
        select "PRODUCT_ATTRIBUTES".nextval into :NEW."ATTRIBUTE_ID" from dual;
      end if;
    end;
    /

    ALTER TABLE "PRODUCTS_ATTRIBUTES" ADD CONSTRAINT "PRODUCTS_ATTRIBUTES_PRODUCT_FK"
    FOREIGN KEY ("PRODUCT_ID")
    REFERENCES "PRODUCTS" ("PRODUCT_ID")
    /

    CREATE table "PRODUCTS_PARAMETERS" (
        "PRODUCT_ID" NUMBER NOT NULL,
        "ATTR_ID"     NUMBER NOT NULL,
        "VALUE"      VARCHAR2(100) NOT NULL
    )
    /

    ALTER TABLE "PRODUCTS_PARAMETERS" ADD CONSTRAINT "PRODUCT_PARAMS_PROD_FK"
    FOREIGN KEY ("PRODUCT_ID")
    REFERENCES "PRODUCTS" ("PRODUCT_ID")
    /

    ALTER TABLE "PRODUCTS_PARAMETERS" ADD CONSTRAINT "PRODUCT_PARAMS_ATTR_FK2"
    FOREIGN KEY ("ATTR_ID")
    REFERENCES "PRODUCTS_ATTRIBUTES" ("ATTRIBUTE_ID")

    /

    CREATE table "FEEDBACK" (
        "FEEDBACK_ID"      NUMBER NOT NULL,
        "USER_ID"          NUMBER NOT NULL,
        "PRODUCT_ID"       NUMBER NOT NULL,
        "FEEDBACK_RAITING" NUMBER(1,0),
        "FEEDBACK_MESSAGE" VARCHAR2(2000),
        constraint  "FEEDBACK_PK" primary key ("FEEDBACK_ID")
    )
    /

    CREATE trigger "BI_FEEDBACK"
      before insert on "FEEDBACK"
      for each row
    begin
      if :NEW."FEEDBACK_ID" is null then
        select "FEEDBACK_SEQ".nextval into :NEW."FEEDBACK_ID" from dual;
      end if;
    end;
    /

    ALTER TABLE "FEEDBACK" ADD CONSTRAINT "FEEDBACK_FK"
    FOREIGN KEY ("USER_ID")
    REFERENCES "USERS" ("USER_ID")

    /

    ALTER TABLE "FEEDBACK" ADD CONSTRAINT "FEEDBACK_PRODUCT_FK"
    FOREIGN KEY ("PRODUCT_ID")
    REFERENCES "PRODUCTS" ("PRODUCT_ID")

    /

