use "DMA-CSD-S223_10461259";

CREATE TABLE Customer (
    customerId int NOT NULL IDENTITY(1,1),
    name varchar(50) NOT NULL,
    address varchar(50) NOT NULL,
    phoneNo varchar(50) NOT NULL,

    CONSTRAINT PK_Customer PRIMARY KEY (customerId)
)

CREATE TABLE Supplier (
    supplierId int NOT NULL IDENTITY(1,1),
    name varchar(50) NOT NULL,
    address varchar(50) NOT NULL,
    country varchar(50) NOT NULL,
    phoneNo varchar(50) NOT NULL,
    email varchar(50) NOT NULL,

    CONSTRAINT PK_Supplier PRIMARY KEY (supplierId)
)

CREATE TABLE Product (
    productId int NOT NULL IDENTITY(1,1),
    name varchar(50) NOT NULL,
    brand varchar(50) NOT NULL,
    purchasePrice decimal(10,2) NOT NULL,
    salesPrice decimal(10,2) NOT NULL,
    countryOfOrigin varchar(50) NOT NULL,
    minStock int NOT NULL,
    stock int NOT NULL,
    description varchar(500) NOT NULL,
    category varchar(50) NOT NULL,
    supplierId int NOT NULL,

    CONSTRAINT PK_Product PRIMARY KEY (productId),
    CONSTRAINT FK_Product_Supplier FOREIGN KEY (supplierId) REFERENCES Supplier(supplierId)
)

CREATE TABLE OrderLine (
    orderLineId int NOT NULL IDENTITY(1,1),
    quantity int NOT NULL,
    discount decimal(10,2) NOT NULL,
    productId int NOT NULL,

    CONSTRAINT PK_OrderLine PRIMARY KEY (orderLineId),
    CONSTRAINT FK_OrderLine_Product FOREIGN KEY (productId) REFERENCES Product(productId)
)

CREATE TABLE SaleOrder (
    saleOrderId int NOT NULL IDENTITY(1,1),
    date datetime2 NOT NULL,
    deliveryStatus varchar(50) NOT NULL,
    deliveryDate datetime2 NOT NULL,
    paymentDate datetime2 NOT NULL,
    customerId int NOT NULL,
    orderLineId int NOT NULL,

    CONSTRAINT PK_SaleOrder PRIMARY KEY (saleOrderid),
    CONSTRAINT FK_SaleOrder_Customer FOREIGN KEY (customerId) REFERENCES Customer(customerId),
    CONSTRAINT FK_SaleOrder_OrderLine FOREIGN KEY (orderLineId) REFERENCES OrderLine(orderLineId)
)
