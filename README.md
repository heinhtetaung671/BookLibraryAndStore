# BookLibraryAndStore
This is a Book Library and Store application, meaning that you can borrow and buy books you like with special discount offered by members and admin.

## This Application has 4 Roles

### Anonymous Users
View available books for borrowing and buying
Borrow a book for free.

### Customers

-- Borrowing

* Borrow books (free for 1st time) (can't borrow a book that hasn't been returned)
* View borrowing books
* Return borrowing book (calculate price per borrowed day)
* View borrowed books

-- Buying

* Add books to cart to purchase
* Select promotion (books might have many promotions but can only use one promotion) 
* Purchase books
* View bought voucher history
* View bought books
* Point system

-- Personal info editing

* Change profile pitcure
* Change personal info (require password to change)
* Change email (programatic relogin)
* Change password

### Member 

* All functionalities of Customer
* Add new category and edit
* Add new books, edit books and add promotions to books
* Add new promotions


### Admin

* Admin can't use application's services (Admin can only manage application)
* Add new category and edit
* Add new books, edit books and add promotions to books
* Add new promotions
* Add new accounts, lock accounts, deactive accounts, give status message 
role management, accounts' balance and points management
* Admin can check all user accounts' specific profile and bought history
