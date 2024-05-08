--DROP TABLE IF EXISTS as141220.SUBSTITUTION;
CREATE TABLE as141220.SUBSTITUTION(
    Sub_item INT,
    Replaced_item INT,
    Step_id INT,
    Restriction_id INT,
    Sub_ratio VARCHAR(8),
    CONSTRAINT PK_SUBSTITUTION_SUB_ITEM_REPLACED_ITEM_STEP_ID_RESTRICTION_ID PRIMARY KEY (Sub_item, Replaced_item, Step_id, Restriction_id),
    CONSTRAINT FK_SUBSTITUTION_SUB_ITEM_COMPONENT_COMPONENT_ID FOREIGN KEY (Sub_item) REFERENCES as141220.COMPONENT (Component_id)
    ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT FK_SUBSTITUTION_REPLACED_ITEM_COMPONENT_COMPONENT_ID FOREIGN KEY (Replaced_item) REFERENCES as141220.COMPONENT (Component_id)
    ON DELETE NO ACTION ON UPDATE NO ACTION, -- Would like to CASCADE here, but could create multiple cascade paths, so need to deal with this at the application level
    CONSTRAINT FK_SUBSTITUTION_STEP_ID_STEP_STEP_ID FOREIGN KEY (Step_id) REFERENCES as141220.STEP (Step_id)
    ON DELETE NO ACTION ON UPDATE NO ACTION, -- Would like to CASCADE here, but could create multiple cascade paths, so need to deal with this at the application level
    CONSTRAINT FK_SUBSTITUTION_RESTRICTION_ID_TAG_TAG_ID FOREIGN KEY (Restriction_id) REFERENCES as141220.TAG (Tag_id)
    ON DELETE NO ACTION ON UPDATE NO ACTION, -- Would like to CASCADE here, but could create multiple cascade paths, so need to deal with this at the application level. Another business rule is that the referenced Tag_id needs to have Tag_type "restriction"
);
