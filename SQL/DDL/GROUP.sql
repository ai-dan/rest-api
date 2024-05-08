--DROP TABLE IF EXISTS as141220.[GROUP];
CREATE TABLE as141220.[GROUP](
    Group_id INT,
    Group_name VARCHAR(64),
    Owner_id INT,
    CONSTRAINT PK_GROUP_GROUP_ID PRIMARY KEY (Group_id),
    CONSTRAINT FK_GROUP_GROUP_ID_AGENT_AGENT_ID FOREIGN KEY (Group_id) REFERENCES as141220.AGENT (Agent_id)
    ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT FK_GROUP_OWNER_ID_USER_USER_ID FOREIGN KEY (Owner_id) REFERENCES as141220.[USER] (User_id)
    ON DELETE NO ACTION ON UPDATE NO ACTION -- Would like to CASCADE here, but could create multiple cascade paths, so need to deal with this at the application level
);
