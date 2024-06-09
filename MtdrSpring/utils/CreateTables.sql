/* Create tables for the application
Load it into the OCI database actions */
/*
Author:
Herbert Euroza
Davide Dunne
*/

-- done column has been removed
CREATE TABLE TODOUSER.todoitem (
    id NUMBER GENERATED ALWAYS AS IDENTITY,
    description VARCHAR2(4000),
    creation_ts TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (id)
);

CREATE TABLE TODOUSER.user_type (
    id NUMBER GENERATED ALWAYS AS IDENTITY,
    name VARCHAR2(100),
    PRIMARY KEY (id)
);
INSERT INTO TODOUSER.user_type (name) VALUES ('Manager');
INSERT INTO TODOUSER.user_type (name) VALUES ('Developer');

CREATE TABLE TODOUSER.user (
    id NUMBER GENERATED ALWAYS AS IDENTITY,
    name VARCHAR2(100),
    telegram_name VARCHAR2(100),
    user_type_id NUMBER,
    PRIMARY KEY (id),
    FOREIGN KEY (user_type_id) REFERENCES user_type(id)
);

CREATE TABLE TODOUSER.todoitem_state (
    id NUMBER GENERATED ALWAYS AS IDENTITY,
    name VARCHAR2(100),
    PRIMARY KEY (id)
);
INSERT INTO TODOUSER.todoitem_state (name) VALUES ('TODO');
INSERT INTO TODOUSER.todoitem_state (name) VALUES ('IN_PROGRESS');
INSERT INTO TODOUSER.todoitem_state (name) VALUES ('DONE');

CREATE TABLE TODOUSER.todoitem_assignment (
    id NUMBER GENERATED ALWAYS AS IDENTITY,
    todoitem_id NUMBER,
    user_id NUMBER,
    state_id NUMBER DEFAULT 0,
    PRIMARY KEY (id),
    FOREIGN KEY (todoitem_id) REFERENCES todoitem(id),
    FOREIGN KEY (user_id) REFERENCES user(id),
    FOREIGN KEY (state_id) REFERENCES todoitem_state(id)
);

CREATE TABLE TODOUSER.team (
    id NUMBER GENERATED ALWAYS AS IDENTITY,
    name VARCHAR2(100),
    manager_id NUMBER,
    PRIMARY KEY (id),
    FOREIGN KEY (manager_id) REFERENCES user(id)
);

CREATE TABLE TODOUSER.team_member (
    id NUMBER GENERATED ALWAYS AS IDENTITY,
    team_id NUMBER,
    user_id NUMBER,
    PRIMARY KEY (id),
    FOREIGN KEY (team_id) REFERENCES team(id),
    FOREIGN KEY (user_id) REFERENCES user(id)
);