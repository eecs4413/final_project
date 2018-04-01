/** bid: unique identifier of Book (like ISBN)
* title: title of Book
* price: unit price WHEN ordered
* author: name of authors
* category: as specified
*/
DROP TABLE if exists Book;
CREATE TABLE Book (
bid VARCHAR(20) NOT NULL,
title VARCHAR(60) NOT NULL,
price INT NOT NULL,
category ENUM('Science','Fiction','Engineering') NOT NULL,
PRIMARY KEY(bid));
#
# Adding data for table 'Book'
#
INSERT INTO Book (bid, title, price, category) VALUES ('b001', 'Little Prince', 20, 'Fiction');
INSERT INTO Book (bid, title, price, category) VALUES ('b002','Physics', 201, 'Science');
INSERT INTO Book (bid, title, price, category) VALUES ('b003','Mechanics' ,100,'Engineering');
#
/* Address
* id: address id
*
*/
DROP TABLE if exists Address;
CREATE TABLE Address (
id INT UNSIGNED NOT NULL AUTO_INCREMENT,
street VARCHAR(100) NOT NULL,
province VARCHAR(20) NOT NULL,
country VARCHAR(20) NOT NULL,
zip VARCHAR(20) NOT NULL,
phone VARCHAR(20),
PRIMARY KEY(id)
);
#
# Inserting data for table 'address'
#
INSERT INTO Address (id, street, province, country, zip, phone) VALUES (1, '123 Yonge St', 'ON',
'Canada', 'K1E 6T5' ,'647-123-4567');
INSERT INTO Address (id, street, province, country, zip, phone) VALUES (2, '445 Avenue rd', 'ON',
'Canada', 'M1C 6K5' ,'416-123-8569');
INSERT INTO Address (id, street, province, country, zip, phone) VALUES (3, '789 Keele St.', 'ON',
'Canada', 'K3C 9T5' ,'416-123-9568');
#
#

/** email : user
*	String email, String password, String lname,
String fname, AddressBean address, List<PurchaseOrderBean> orders  */

DROP TABLE if exists Account;
CREATE TABLE Account (
email VARCHAR(20) NOT NULL,
password VARCHAR(20) NOT NULL,
lname VARCHAR(20) NOT NULL,
fname VARCHAR(20) NOT NULL,
billAddress INT UNSIGNED NOT NULL,
PO INT UNSIGNED NOT NULL,
PRIMARY KEY(email));
INDEX (address),
FOREIGN KEY (address) REFERENCES Address (id) ON DELETE CASCADE
INDEX (PO),
FOREIGN KEY (PO) REFERENCES PO (id) ON DELETE CASCADE


#
# Adding data for table 'account'
#
INSERT INTO Account (email, password, lname, fname, billAddress, PO) VALUES ('paulliu@my.yorku.ca', 'ilovestarbucks', 'Liu', 'Paul', '1', '1');
INSERT INTO Account (email, password, lname, fname, billAddress, PO) VALUES ('dmnosale@my.yorku.ca','ilovedigitalmedia', 'Nosale', 'David-Mark', '2', '2');
INSERT INTO Account (email, password, lname, fname, billAddress, PO) VALUES ('michaelshortford@my.york.ca','whatsAR', 'Shortford', 'Michael', '3', '3');
#


/* Purchase Order 
* id: purchase order id
* status:status of purchase
* shipAddress
*/
DROP TABLE if exists PO;
CREATE TABLE PO (
id INT UNSIGNED NOT NULL AUTO_INCREMENT,
status ENUM('ORDERED','PROCESSED','DENIED') NOT NULL,
shipAddress INT UNSIGNED NOT NULL,
PRIMARY KEY(id),
INDEX (address),
FOREIGN KEY (address) REFERENCES Address (id) ON DELETE CASCADE
);
#
# Inserting data for table 'PO'
#
INSERT INTO PO (id, status, shipAddress) VALUES (1, 'PROCESSED', '1');
INSERT INTO PO (id, status, shipAddress) VALUES (2, 'DENIED', '2');
INSERT INTO PO (id, status, shipAddress) VALUES (3, 'ORDERED', '3');
#
#

/* Purchase orders of users
* Order Date: DD/MM/YY
* Shipping Address: Search throuh database for proper IDs that match
* Billing Address: Search throuh database for proper IDs that match
* User comment: Placed with order dates
* Items (PurchaseOrderItemBean): Search throuh database for proper IDs that match 
*/

/* Items on order
* id : purchase order id
* bid: unique identifier of Book
* price: unit price
* date:
*/
DROP TABLE if exists POItem;
CREATE TABLE POItem (
id INT UNSIGNED NOT NULL,
bid VARCHAR(20) NOT NULL,
price INT UNSIGNED NOT NULL,
day varchar(8) NOT NULL,
PRIMARY KEY(id,bid),
INDEX (id),
FOREIGN KEY(id) REFERENCES PO(id) ON DELETE CASCADE,
INDEX (bid),
FOREIGN KEY(bid) REFERENCES Book(bid) ON DELETE CASCADE
);
#
# Inserting data for table 'POitem'
#
INSERT INTO POItem (id, bid, price, day) VALUES (1, 'b001', '20','12202015',);
INSERT INTO POItem (id, bid, price, day) VALUES (2, 'b002', '201','12222015',);
INSERT INTO POItem (id, bid, price, day) VALUES (3, 'b003', '100','12262015',);
#
#

/* visit to website
* day: date
* bid: unique identifier of Book
* eventtype: status of purchase
*/
Inserting TABLE if exists VisitEvent;
CREATE TABLE VisitEvent (
day varchar(8) NOT NULL,
bid varchar(20) not null REFERENCES Book.bid,
eventtype ENUM('VIEW','CART','PURCHASE') NOT NULL,
FOREIGN KEY(bid) REFERENCES Book(bid)
);
#
# Dumping data for table 'VisitEvent'
#
INSERT INTO VisitEvent (day, bid, eventtype) VALUES ('12202015', 'b001', 'VIEW');
INSERT INTO VisitEvent (day, bid, eventtype) VALUES ('12242015', 'b001', 'CART');
INSERT INTO VisitEvent (day, bid, eventtype) VALUES ('12252015', 'b001', 'PURCHASE');
#
#


