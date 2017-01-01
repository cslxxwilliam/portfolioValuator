CREATE TABLE STOCK(
id int primary key,
ticker varchar(255),
name varchar(255),
expected_return number not null,
annualized_sd number not null,
price number not null);

CREATE TABLE OPTION(
id int primary key,
ticker varchar(255),
type varchar(255),
underlying_id int not null,
maturity int not null,
strike number not null,
price number not null
);

CREATE TABLE INVESTMENT_POSITION(
id int primary key,
stock_id int not null,
shares_held int not null,
market_value number not null);

CREATE TABLE OPTION_POSITION(
id int primary key,
option_id int not null,
contracts_held int not null,
market_value number not null
);