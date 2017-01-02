INSERT INTO STOCK(id, ticker, name, expected_return, annualized_sd, price) VALUES(1, '0001.HK', 'CKH HOLDINGS', 0.1, 0.1, 88.1);
INSERT INTO STOCK(id, ticker, name, expected_return, annualized_sd, price) VALUES(2, '0002.HK', 'CLP HOLDINGS', 0.2, 0.2, 71.6);
INSERT INTO STOCK(id, ticker, name, expected_return, annualized_sd, price) VALUES(3, '0003.HK', 'HK & CHINA GAS', 0.9, 0.9, 13.6);

INSERT INTO OPTION(id, ticker, type, underlying_id, maturity, strike, price) VALUES(1, '0001.HK.Call','CALL', 1, 1, 80, 0);
INSERT INTO OPTION(id, ticker, type, underlying_id, maturity, strike, price) VALUES(2, '0002.HK.Put','PUT', 2, 2, 78, 0);

--INSERT INTO INVESTMENT_POSITION(id, stock_id, shares_held, market_value) VALUES(1, 1, 1000, 88100);
--INSERT INTO INVESTMENT_POSITION(id, stock_id, shares_held, market_value) VALUES(2, 2, 1000, 71600);
--INSERT INTO INVESTMENT_POSITION(id, stock_id, shares_held, market_value) VALUES(3, 3, 1000, 13600);
--
--INSERT INTO OPTION_POSITION(id, option_id, contracts_held, market_value) VALUES(1, 1, 100, 1);
--INSERT INTO OPTION_POSITION(id, option_id, contracts_held, market_value) VALUES(2, 2, 120, 2);
--INSERT INTO OPTION_POSITION(id, option_id, contracts_held, market_value) VALUES(3, 3, 200, 3);
--INSERT INTO OPTION_POSITION(id, option_id, contracts_held, market_value) VALUES(4, 4, 180, 4);