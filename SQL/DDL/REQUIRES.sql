--DROP TABLE IF EXISTS as141220.REQUIRES;
CREATE TABLE as141220.REQUIRES(
    Recipe_id INT,
    Step_number INT,
    Component_id INT,
    Quantity INT NOT NULL DEFAULT 1,
    Unit VARCHAR(64) NOT NULL DEFAULT '',
    CONSTRAINT PK_REQUIRES_RECIPE_ID_STEP_ID_COMPONENT_ID PRIMARY KEY (Recipe_id, Step_number, Component_id),
    CONSTRAINT FK_REQUIRES_RECIPE_ID_STEP_NUMBER_INCLUDES_RECIPE_ID_ORDER FOREIGN KEY (Recipe_id, Step_number) REFERENCES as141220.INCLUDES (Recipe_id, [Order])
    ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT FK_REQUIRES_COMPONENT_ID_COMPONENT_COMPONENT_ID FOREIGN KEY (Component_id) REFERENCES as141220.COMPONENT (Component_id)
    ON DELETE NO ACTION ON UPDATE NO ACTION, -- Would like to CASCADE here, but could create multiple cascade paths, so need to deal with this at the application level
);
