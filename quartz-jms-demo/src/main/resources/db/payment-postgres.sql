DROP TABLE IF EXISTS payments;
CREATE TABLE quartz.payments(id serial PRIMARY KEY, amount double precision, status VARCHAR(20), method VARCHAR(20));
INSERT INTO quartz.payments(amount, status, method) VALUES(25.0, 'Approved', 'Cash');
INSERT INTO quartz.payments(amount, status, method) VALUES(35890.0, 'Rejected', 'Credit Card');
INSERT INTO quartz.payments(amount, status, method) VALUES(10000.0, 'Pending', 'Credit Card');
INSERT INTO quartz.payments(amount, status, method) VALUES(10000.0, 'Approved', 'Credit Card');
INSERT INTO quartz.payments(amount, status, method) VALUES(40000.0, 'Approved', 'Paypal');