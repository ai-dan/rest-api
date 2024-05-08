--DROP TABLE IF EXISTS as141220.RECIPE;
CREATE TABLE as141220.RECIPE(
    Recipe_id INT,
    Source_id INT NOT NULL DEFAULT -1,
    Base_id INT NOT NULL DEFAULT -1,
    CONSTRAINT PK_RECIPE_RECIPE_ID PRIMARY KEY (Recipe_id),
    CONSTRAINT FK_RECIPE_SOURCE_ID_AGENT_AGENT_ID FOREIGN KEY (Source_id) REFERENCES as141220.AGENT (Agent_id)
    ON DELETE SET DEFAULT ON UPDATE CASCADE,
    CONSTRAINT FK_RECIPE_BASE_ID_RECIPE_RECIPE_ID FOREIGN KEY (Base_id) REFERENCES as141220.RECIPE (Recipe_id)
    ON DELETE NO ACTION ON UPDATE NO ACTION -- Would like to SET DEFAULT/CASCADE here, but could create multiple cascade paths, so need to deal with this at the application level
);