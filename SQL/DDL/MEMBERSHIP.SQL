--DROP TABLE IF EXISTS as141220.MEMBERSHIP;
CREATE TABLE as141220.MEMBERSHIP(
    Group_id INT,
    User_id INT,
    CONSTRAINT PK_MEMBERSHIP_GROUP_ID_USER_ID PRIMARY KEY (Group_id, User_id),
    CONSTRAINT FK_MEMBERSHIP_GROUP_ID_GROUP_GROUP_ID FOREIGN KEY (Group_id) REFERENCES as141220.[GROUP] (Group_id)
    ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT FK_MEMBERSHIP_USER_ID_USER_USER_ID FOREIGN KEY (User_id) REFERENCES as141220.[USER] (User_id)
    ON DELETE NO ACTION ON UPDATE NO ACTION -- Would like to CASCADE here, but could create multiple cascade paths, so need to deal with this at the application level
);
