CREATE TABLE IF NOT EXISTS currency_api_ussage (
         id int8 NOT NULL,
         application_endpoint varchar(255) DEFAULT NULL,
         nbp_endpoint varchar(255) DEFAULT NULL,
         currency_from varchar(5) DEFAULT NULL,
         currency_to varchar(5) DEFAULT NULL
);
