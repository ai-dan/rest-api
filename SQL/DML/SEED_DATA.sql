INSERT INTO as141220.AGENT (Agent_id, Agent_type)
VALUES
    (-1, 'N/A'), -- Default recipe source
    (1, 'user'),
    (2, 'user'),
    (3, 'user'),
    (4, 'user'),
    (5, 'user'),
    (6, 'user'),
    (7, 'group'),
    (8, 'group'),
    (9, 'group'),
    (10, 'group');

INSERT INTO as141220.[USER] (User_id, First_name, Last_name, Email)
VALUES
    (1, 'Aidan', 'Shackleton', 'aidan@example.com'),
    (2, 'Lisa', 'Bao', 'lisa@example.com'),
    (3, 'Matty', 'Legs', 'matthew@example.com'),
    (4, 'Vanessa', 'Sierpnerhe', 'vanessa@example.com'),
    (5, 'Donna', 'Arglbargl', 'donna@example.com'),
    (6, 'Mac', '&Cheese', 'mac@example.com');

INSERT INTO as141220.[GROUP] (Group_id, Group_name, Owner_id)
VALUES
    (7, 'Cookbook Club', 2),
    (8, 'Big Mac Test Kitchen', 6),
    (9, 'NYT Cooking', 5),
    (10, 'Date Night', 2);

INSERT INTO as141220.MEMBERSHIP (Group_id, User_id)
VALUES
    (7, 1),
    (7, 2),
    (7, 3),
    (7, 4),
    (7, 5),
    (7, 6),
    (8, 6),
    (9, 1),
    (9, 4),
    (9, 5),
    (10, 1),
    (10, 2);

INSERT INTO as141220.COLLECTION (Collection_id, Collection_name, Collection_type, Owner_id)
VALUES
    (1, 'My History', 'history', 2),
    (2, 'Recipes to Try', 'queue', 2),
    (3, 'Favorite Recipes', 'favorites', 2),
    (4, 'My History', 'history', 5),
    (5, 'Group History', 'history', 7),
    (6, 'Cinco de Mayo', 'menu', 7),
    (7, 'Group History', 'history', 8),
    (8, 'Romantic Dinners', 'queue', 10);

INSERT INTO as141220.VIEWERSHIP (Collection_id, Viewer_id)
VALUES
    (1, 2),
    (2, 2),
    (3, 2),
    (4, 5),
    (5, 7),
    (6, 7),
    (7, 8),
    (8, 10),
    (8, 5);

INSERT INTO as141220.COMPONENT (Component_id, Component_name, Component_type)
VALUES
    (-1, 'N/A', 'recipe'), -- Default recipe base
    (1, 'Shirley Temple', 'recipe'),
    (2, 'Banana Daiquiri', 'recipe'),
    (3, 'Tequila Shot', 'recipe'),
    (4, '7-up', 'ingredient'),
    (5, 'Grenadine', 'ingredient'),
    (6, 'Maraschino Cherry', 'ingredient'),
    (7, 'Lemon-Lime Soda', 'recipe'),
    (8, 'Dark Rum', 'ingredient'),
    (9, 'Coconut Rum', 'ingredient'),
    (10, 'Banana Liqueur', 'ingredient'),
    (11, 'Almond Syrup', 'ingredient'),
    (12, 'Lime Juice', 'ingredient'),
    (13, 'Lemon Juice', 'ingredient'),
    (14, 'Simple Syrup', 'ingredient'),
    (15, 'Simple Syrup', 'recipe'),
    (16, 'Sugar', 'ingredient'),
    (17, 'Water', 'ingredient'),
    (18, 'Tequila', 'ingredient'),
    (19, 'Ice cubes', 'ingredient'),
    (20, 'Cocktail shaker', 'implement'),
    (21, 'Cocktail glass', 'implement'),
    (22, 'Strainer', 'implement'),
    (23, 'Bar spoon', 'implement'),
    (24, 'Sparkling water', 'ingredient'),
    (25, 'Shot glass', 'implement'),
    (26, 'Classic Daiquiri', 'recipe');

INSERT INTO as141220.INGREDIENT (Ingredient_id, Ingredient_type)
VALUES
    (4, 'soda'),
    (5, 'syrup'),
    (6, 'condiment'),
    (8, 'alcohol'),
    (9, 'alcohol'),
    (10, 'alcohol'),
    (11, 'syrup'),
    (12, 'fruit juice'),
    (13, 'fruit juice'),
    (14, 'syrup'),
    (16, 'basics'),
    (17, 'basics'),
    (18, 'alcohol'),
    (19, 'basics'),
    (24, 'basics');

INSERT INTO as141220.IMPLEMENT (Implement_id, Implement_type)
VALUES
    (20, 'bar supplies'),
    (21, 'bar supplies'),
    (22, 'bar supplies'),
    (23, 'bar supplies'),
    (25, 'bar supplies');

INSERT INTO as141220.RECIPE (Recipe_id, Source_id, Base_id)
VALUES
    (-1, -1, -1), -- Default base
    (26, 9, -1),
    (1, 5, -1),
    (2, 9, 26),
    (3, 8, -1),
    (7, 9, -1),
    (15, 1, -1);

INSERT INTO as141220.PAIRING ([First], [Second], Source_id, Note)
VALUES
    (2, 1, 2, 'Everything in moderation!'),
    (3, 3, 8, 'YOLO');

INSERT INTO as141220.[CONTAINS] (Collection_id, Recipe_id, Time_added, Rank, Note)
VALUES
    (1, 3, '2024-05-05 07:05:00', -1, 'Gross!'),
    (2, 2, '2024-05-05 07:05:30', 1, 'Sub passion fruit for banana?'),
    (2, 15, '2024-05-05 07:15:00', 2, 'Need for misc. experimentation'),
    (3, 7, '2024-05-04 15:00:00', 1, 'Great way to cut costs'),
    (4, 2, '2024-05-04 12:00:00', -1, 'Delicious!'),
    (5, 1, '2024-05-04 16:00:00', -1, 'Matty is at it again'),
    (5, 1, '2024-05-04 16:10:00', -1, 'Here he goes'),
    (5, 1, '2024-05-04 16:23:00', -1, 'Trying to set a record?'),
    (5, 1, '2024-05-04 16:30:00', -1, 'No way this is healthy'),
    (6, 2, '2024-05-04 18:30:00', 1, 'For the sophisticated drinker'),
    (6, 3, '2024-05-04 18:31:00', 2, 'But also it is cinco de mayo'),
    (6, 1, '2024-05-04 18:33:00', 3, 'A beverage for Mr. Legs, I guess'),
    (7, 3, '2024-05-05 07:05:00', -1, 'It''s cookin'' time'),
    (7, 3, '2024-05-05 07:06:00', -1, 'It''s COOKIN'' time'),
    (7, 3, '2024-05-05 07:15:00', -1, '3 shots is peak performance, right?'),
    (7, 3, '2024-05-05 07:22:00', -1, 'Maybe 4?'),
    (7, 3, '2024-05-05 07:30:00', -1, 'It was definitely 3, but now I''m committed');

INSERT INTO as141220.STEP (Step_id, [Action], Duration)
VALUES
    (1, 'Combine', '0'),
    (2, 'Add', '0'),
    (3, 'Shake', '15 seconds'),
    (4, 'Strain', '0'),
    (5, 'Stir', '10 seconds'),
    (6, 'Pour', '0'),
    (7, 'Serve', '0'),
    (8, 'Stir', '2 minutes');

INSERT INTO as141220.INCLUDES (Recipe_id, Step_id, [Order])
VALUES
    -- Shirley Temple
    (1, 1, 1), -- "Combine ingredients in a cocktail glass"
    (1, 5, 2), -- "Stir for 10 seconds"
    (1, 2, 3), -- "Add cherry"
    (1, 7, 4), -- "Serve"
    -- Banana daiquiri
    (2, 1, 1), -- "Combine ingredients in a cocktail shaker
    (2, 2, 2), -- "Add ice"
    (2, 3, 3), -- "Shake for 15 seconds"
    (2, 4, 4), -- "Strain into cocktail glass"
    (2, 2, 5), -- "Add cherry"
    (2, 7, 6), -- "Serve"
    -- Tequila shot
    (3, 6, 1), -- "Pour into shot glass"
    (3, 7, 2), -- "Serve"
    -- Lemon-lime soda
    (7, 1, 1), -- "Combine ingredients"
    (7, 5, 2), -- "Stir for 10 seconds"
    -- Simple syrup
    (15, 1, 1), -- "Combine ingredients"
    (15, 8, 2); -- "Stir for 2 minutes"

INSERT INTO as141220.REQUIRES (Recipe_id, Step_number, Component_id, Quantity, Unit)
VALUES
    -- Shirley Temple
    (1, 1, 4, 6, 'ounces'), -- 6oz 7-up
    (1, 1, 5, 1, 'ounce'), -- 1oz grenadine
    (1, 1, 21, 1, ''), -- 1 cocktail glass
    (1, 2, 23, 1, ''), -- 1 bar spoon
    (1, 3, 6, 1, ''), -- 1 Maraschino cherry
    -- Banana daiquiri
    (2, 1, 8, 2, 'ounces'), -- 2oz dark rum
    (2, 1, 9, 1, 'ounce'), -- 1 oz coconut rum
    (2, 1, 10, 1, 'ounce'), -- 1 oz banana liqueur
    (2, 1, 11, 1, 'ounce'), -- 1 oz almond syrup
    (2, 1, 12, 1, 'ounce'), -- 1 oz lime juice
    (2, 1, 20, 1, ''), -- 1 cocktail shaker
    (2, 2, 19, 6, ''), -- 6 ice cubes
    (2, 4, 21, 1, ''), -- 1 cocktail glass
    (2, 5, 6, 1, ''), -- 1 Maraschino cherry
    -- Tequila shot
    (3, 1, 18, 2, 'ounces'), -- 2oz tequila
    (3, 1, 25, 1, ''), -- 1 shot glass
    -- Lemon-lime soda
    (7, 1, 13, 1, 'ounce'), -- 1oz lemon juice
    (7, 1, 12, 1, 'ounce'), -- 1oz lime juice
    (7, 1, 14, 1, 'ounce'), -- 1oz simple syrup
    (7, 1, 24, 9, 'ounces'), -- 9oz sparkling water
    (7, 2, 23, 1, ''), -- 1 bar spoon
    -- Simple syrup
    (15, 1, 17, 1, 'ounce'), -- 1oz water
    (15, 1, 16, 1, 'ounce'), -- 1oz sugar
    (15, 2, 23, 1, ''); -- 1 bar spoon

INSERT INTO as141220.TAG (Tag_id, Tag_type, Tag_name, Tag_detail)
VALUES
    (1, 'restriction', 'vegetarian', ''),
    (2, 'restriction', 'non-alcoholic', ''),
    (3, 'course', 'drink', 'cocktail'),
    (4, 'course', 'drink', 'mocktail'),
    (5, 'restriction', 'home-made', '');

INSERT INTO as141220.TAGGED (Recipe_id, Tag_id)
VALUES
    (1, 1),
    (1, 2),
    (1, 4),
    (2, 1),
    (2, 3),
    (3, 1),
    (7, 1),
    (7, 2),
    (15, 1),
    (15, 2);

INSERT INTO as141220.SUBSTITUTION (Sub_item, Replaced_item, Step_id, Restriction_id, Sub_ratio)
VALUES
    (7, 4, 1, 5, '1:2'),
    (15, 14, 1, 5, '1:2'),
    (17, 18, 6, 2, '1:1');

