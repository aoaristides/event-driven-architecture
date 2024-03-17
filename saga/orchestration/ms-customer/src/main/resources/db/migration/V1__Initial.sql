CREATE TABLE customers
(
    id          VARCHAR(255)  NOT NULL PRIMARY KEY,
    name        VARCHAR(255) NOT NULL,
    document    VARCHAR(15) NOT NULL,
    type        VARCHAR(1) NOT NULL,
    phone       VARCHAR(15) NOT NULL,
    birthday    DATE NOT NULL,
    active      BOOLEAN      NOT NULL DEFAULT TRUE,
    created_at  DATETIME(6)  NOT NULL,
    updated_at  DATETIME(6)  NOT NULL,
    deleted_at  DATETIME(6)  NULL,
    unique(document)
) ENGINE=InnoDB CHARACTER SET UTF8;