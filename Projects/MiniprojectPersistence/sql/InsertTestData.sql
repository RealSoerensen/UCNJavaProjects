use "DMA-CSD-S223_10461259";

INSERT INTO Customer (name, address, phoneNo) VALUES ('John Doe', 'Street 1', '12345678');
INSERT INTO Customer (name, address, phoneNo) VALUES ('Jane Doe', 'Street 2', '87654321');

INSERT INTO Supplier (name, address, country, phoneNo, email) VALUES ('Supplier 1', 'Street 3', 'Denmark', '12345678', ' [email protected] ');
INSERT INTO Supplier (name, address, country, phoneNo, email) VALUES ('Supplier 2', 'Street 4', 'Denmark', '87654321', ' [email protected] ');

INSERT INTO Product (name, brand, purchasePrice, salesPrice, countryOfOrigin, minStock, stock, description, category, supplierId) VALUES ('Product 1', 'Brand 1', 100, 200, 'Denmark', 10, 20, 'Description 1', 'Category 1', 1);
INSERT INTO Product (name, brand, purchasePrice, salesPrice, countryOfOrigin, minStock, stock, description, category, supplierId) VALUES ('Product 2', 'Brand 2', 200, 300, 'Denmark', 20, 30, 'Description 2', 'Category 2', 2);

INSERT INTO OrderLine (quantity, discount, productId) VALUES (1, 0, 1);
INSERT INTO OrderLine (quantity, discount, productId) VALUES (2, 0, 2);

INSERT INTO SaleOrder (date, deliveryStatus, deliveryDate, paymentDate, customerId, orderLineId) VALUES ('2020-01-01', 'Delivered', '2020-01-02', '2020-01-03', 1, 1);
INSERT INTO SaleOrder (date, deliveryStatus, deliveryDate, paymentDate, customerId, orderLineId) VALUES ('2020-01-02', 'Delivered', '2020-01-03', '2020-01-03', 2, 2);
