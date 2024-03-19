CREATE TABLE addresses
(
    id               VARCHAR(255)  NOT NULL PRIMARY KEY,
    street           VARCHAR(255) NOT NULL,
    street_number    VARCHAR(255) NOT NULL,
    complement       VARCHAR(255) NOT NULL,
    city             VARCHAR(255) NOT NULL,
    state            VARCHAR(2) NOT NULL,
    district         VARCHAR(255) NOT NULL,
    postal_code      VARCHAR(10) NOT NULL,
    active           BOOLEAN      NOT NULL DEFAULT TRUE,
    ad_default       BOOLEAN      NOT NULL DEFAULT FALSE,
    created_at       DATETIME(6)  NOT NULL,
    updated_at       DATETIME(6)  NOT NULL,
    deleted_at       DATETIME(6)  NULL
) ENGINE=InnoDB CHARACTER SET UTF8;