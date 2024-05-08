--DROP TABLE IF EXISTS as141220.PAIRING;
CREATE TABLE as141220.PAIRING(
    [First] INT,
    [Second] INT,
    Source_id INT,
    Note VARCHAR(256),
    CONSTRAINT PK_PAIRING_FIRST_SECOND PRIMARY KEY ([First], [Second]),
    CONSTRAINT FK_PAIRING_FIRST_RECIPE_RECIPE_ID FOREIGN KEY ([First]) REFERENCES as141220.RECIPE (Recipe_id)
    ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT FK_PAIRING_SECOND_RECIPE_RECIPE_ID FOREIGN KEY ([Second]) REFERENCES as141220.RECIPE (Recipe_id)
    ON DELETE NO ACTION ON UPDATE NO ACTION, -- Would like to CASCADE here, but could create multiple cascade paths, so need to deal with this at the application level
    CONSTRAINT FK_PAIRING_SOURCE_ID_AGENT_AGENT_ID FOREIGN KEY (Source_id) REFERENCES as141220.AGENT (Agent_id)
    ON DELETE NO ACTION ON UPDATE NO ACTION -- Would like to SET DEFAULT/CASCADE here, but could create multiple cascade paths, so need to deal with this at the application level
);
