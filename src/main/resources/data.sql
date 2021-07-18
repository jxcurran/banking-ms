CREATE TABLE IF NOT EXISTS account (
    id INT AUTO_INCREMENT  PRIMARY KEY,
    name VARCHAR(250) NOT NULL,
    number VARCHAR(250) NOT NULL,
    code VARCHAR(250) NOT NULL,
    balance DOUBLE NOT NULL
);

-- creating initial data
insert into account select * from (
select 1, 'Account 0', '06554577', '01-02-56', 1000.00 union
select 2, 'Account 1', '13457245', '09-01-20', 2000.00 union
select 3, 'Account 2', '23457834', '14-15-16', 3000.00
) x where not exists(select * from account);


CREATE TABLE IF NOT EXISTS transactions (
    id INT AUTO_INCREMENT PRIMARY KEY,
    sender varchar(255) NOT NULL,
    receiver varchar(255) NOT NULL,
    amount DOUBLE NOT NULL
);