-- Insert Customers
INSERT INTO customers (id, name) VALUES (1, 'Alice');
INSERT INTO customers (id, name) VALUES (2, 'Bob');

-- Insert Transactions
INSERT INTO transactions (id, amount, date, customer_id) VALUES (1, 120, '2026-01-15', 1);
INSERT INTO transactions (id, amount, date, customer_id) VALUES (2, 75, '2026-02-10', 1);
INSERT INTO transactions (id, amount, date, customer_id) VALUES (3, 200, '2026-01-20', 2);
INSERT INTO transactions (id, amount, date, customer_id) VALUES (4, 40, '2026-03-05', 2);
