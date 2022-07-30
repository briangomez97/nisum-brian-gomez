INSERT INTO REGULAR_EXP_PARAM(id, name, regular_expression, last_update) values(random_uuid(), 'EMAIL_REGEX', '^(?=.{1,64}@)[A-Za-z0-9_-]+(\.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(\.[A-Za-z0-9-]+)*(\.[A-Za-z]{2,})$', NOW());
INSERT INTO REGULAR_EXP_PARAM(id, name, regular_expression, last_update) values(random_uuid(), 'PASSWORD_REGEX', '^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;'',?/*~$^+=<>]).{8,20}$', NOW());